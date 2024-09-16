package com.controller;

import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.*;

import javax.servlet.http.HttpServletRequest;

import com.alibaba.fastjson.JSON;
import com.utils.StringUtil;
import org.apache.commons.lang3.StringUtils;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.annotation.IgnoreAuth;
import com.baidu.aip.face.AipFace;
import com.baidu.aip.face.MatchRequest;
import com.baidu.aip.util.Base64Util;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.entity.ConfigEntity;
import com.service.CommonService;
import com.service.ConfigService;
import com.utils.BaiduUtil;
import com.utils.FileUtil;
import com.utils.R;

/**
 * 通用接口
 */
@RestController
public class CommonController {
    private static final Logger logger = LoggerFactory.getLogger(CommonController.class);
    @Autowired
    private CommonService commonService;


    /**
     * Java代码实现MySQL数据库导出
     *
     * @param mysqlUrl     MySQL安装路径
     * @param hostIP       MySQL数据库所在服务器地址IP
     * @param userName     进入数据库所需要的用户名
     * @param hostPort     数据库端口
     * @param password     进入数据库所需要的密码
     * @param savePath     数据库文件保存路径
     * @param fileName     数据库导出文件文件名
     * @param databaseName 要导出的数据库名
     * @return 返回true表示导出成功，否则返回false。
     */
    @IgnoreAuth
    @RequestMapping("/beifen")
    public R beifen(String mysqlUrl, String hostIP, String userName, String hostPort, String password, String savePath, String fileName, String databaseName) {
        File saveFile = new File(savePath);
        if (!saveFile.exists()) {// 如果目录不存在 
            saveFile.mkdirs();// 创建文件夹 
        }
        if (!savePath.endsWith(File.separator)) {
            savePath = savePath + File.separator;
        }
        PrintWriter printWriter = null;
        BufferedReader bufferedReader = null;
        try {
            Runtime runtime = Runtime.getRuntime();
            String cmd = mysqlUrl + "mysqldump -h" + hostIP + " -u" + userName + " -P" + hostPort + " -p" + password + " " + databaseName;
            runtime.exec(cmd);
            Process process = runtime.exec(cmd);
            InputStreamReader inputStreamReader = new InputStreamReader(process.getInputStream(), "utf8");
            bufferedReader = new BufferedReader(inputStreamReader);
            printWriter = new PrintWriter(new OutputStreamWriter(new FileOutputStream(savePath + fileName), "utf8"));
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                printWriter.println(line);
            }
            printWriter.flush();
        } catch (Exception e) {
            e.printStackTrace();
            return R.error("备份数据出错");
        } finally {
            try {
                if (bufferedReader != null) {
                    bufferedReader.close();
                }
                if (printWriter != null) {
                    printWriter.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return R.ok();
    }

    /**
     * Java实现MySQL数据库导入
     *
     * @param mysqlUrl     MySQL安装路径
     * @param hostIP       MySQL数据库所在服务器地址IP
     * @param userName     进入数据库所需要的用户名
     * @param hostPort     数据库端口
     * @param password     进入数据库所需要的密码
     * @param savePath     数据库文件保存路径
     * @param fileName     数据库导出文件文件名
     * @param databaseName 要导出的数据库名
     */
    @IgnoreAuth
    @RequestMapping("/huanyuan")
    public R huanyuan(String mysqlUrl, String hostIP, String userName, String hostPort, String password, String savePath, String fileName, String databaseName) {
        try {
            Runtime rt = Runtime.getRuntime();
            Process child1 = rt.exec(mysqlUrl+"mysql.exe  -h" + hostIP + " -u" + userName + " -P" + hostPort + " -p" + password + " " + databaseName);
            OutputStream out = child1.getOutputStream();//控制台的输入信息作为输出流
            String inStr;
            StringBuffer sb = new StringBuffer("");
            String outStr;
            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(savePath+"/"+fileName), "utf-8"));
            while ((inStr = br.readLine()) != null) {
                sb.append(inStr + "\r\n");
            }
            outStr = sb.toString();
            OutputStreamWriter writer = new OutputStreamWriter(out, "utf8");
            writer.write(outStr);
// 注：这里如果用缓冲方式写入文件的话，会导致中文乱码，用flush()方法则可以避免
            writer.flush();
            out.close();
            br.close();
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
            return R.error("数据导入出错");
        }
        return R.ok();
    }


    /**
     * 饼状图求和
     * @return
     */
    @RequestMapping("/pieSum")
    public R pieSum(@RequestParam Map<String,Object> params) {
        logger.debug("饼状图求和:,,Controller:{},,params:{}",this.getClass().getName(),params);
        List<Map<String, Object>> result = commonService.pieSum(params);
        return R.ok().put("data", result);
    }

    /**
     * 饼状图统计
     * @return
     */
    @RequestMapping("/pieCount")
    public R pieCount(@RequestParam Map<String,Object> params) {
        logger.debug("饼状图统计:,,Controller:{},,params:{}",this.getClass().getName(),params);
        List<Map<String, Object>> result = commonService.pieCount(params);
        return R.ok().put("data", result);
    }

    /**
     * 柱状图求和单列
     * @return
     */
    @RequestMapping("/barSumOne")
    public R barSumOne(@RequestParam Map<String,Object> params) {
        logger.debug("柱状图求和单列:,,Controller:{},,params:{}",this.getClass().getName(),params);
        List<Map<String, Object>> result = commonService.barSumOne(params);

        List<String> xAxis = new ArrayList<>();//报表x轴
        List<List<String>> yAxis = new ArrayList<>();//y轴
        List<String> legend = new ArrayList<>();//标题
        List<String> yAxis0 = new ArrayList<>();
        yAxis.add(yAxis0);
        legend.add("");
        for(Map<String, Object> map :result){
            String oneValue = String.valueOf(map.get("name"));
            String value = String.valueOf(map.get("value"));
            xAxis.add(oneValue);
            yAxis0.add(value);
        }
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("xAxis",xAxis);
        resultMap.put("yAxis",yAxis);
        resultMap.put("legend",legend);
        return R.ok().put("data", resultMap);
    }
    /**
     * 柱状图统计单列
     * @return
     */
    @RequestMapping("/barCountOne")
    public R barCountOne(@RequestParam Map<String,Object> params) {
        logger.debug("柱状图统计单列:,,Controller:{},,params:{}",this.getClass().getName(),params);
        List<Map<String, Object>> result = commonService.barCountOne(params);

        List<String> xAxis = new ArrayList<>();//报表x轴
        List<List<String>> yAxis = new ArrayList<>();//y轴
        List<String> legend = new ArrayList<>();//标题

        List<String> yAxis0 = new ArrayList<>();
        yAxis.add(yAxis0);
        legend.add("");
        for(Map<String, Object> map :result){
            String oneValue = String.valueOf(map.get("name"));
            String value = String.valueOf(map.get("value"));
            xAxis.add(oneValue);
            yAxis0.add(value);
        }
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("xAxis",xAxis);
        resultMap.put("yAxis",yAxis);
        resultMap.put("legend",legend);
        return R.ok().put("data", resultMap);
    }

    /**
     * 柱状图统计双列
     * @return
     */
    @RequestMapping("/barSumTwo")
    public R barSumTwo(@RequestParam Map<String,Object> params) {
        logger.debug("柱状图统计双列:,,Controller:{},,params:{}",this.getClass().getName(),params);
        List<Map<String, Object>> result = commonService.barSumTwo(params);
        List<String> xAxis = new ArrayList<>();//报表x轴
        List<List<String>> yAxis = new ArrayList<>();//y轴
        List<String> legend = new ArrayList<>();//标题

        Map<String, HashMap<String, String>> dataMap = new LinkedHashMap<>();
        for(Map<String, Object> map :result){
            String name1Value = String.valueOf(map.get("name1"));
            String name2Value = String.valueOf(map.get("name2"));
            String value = String.valueOf(map.get("value"));
            if(!legend.contains(name2Value)){
                legend.add(name2Value);//添加完成后 就是最全的第二列的类型
            }
            if(dataMap.containsKey(name1Value)){
                dataMap.get(name1Value).put(name2Value,value);
            }else{
                HashMap<String, String> name1Data = new HashMap<>();
                name1Data.put(name2Value,value);
                dataMap.put(name1Value,name1Data);
            }

        }

        for(int i =0; i<legend.size(); i++){
            yAxis.add(new ArrayList<String>());
        }

        Set<String> keys = dataMap.keySet();
        for(String key:keys){
            xAxis.add(key);
            HashMap<String, String> map = dataMap.get(key);
            for(int i =0; i<legend.size(); i++){
                List<String> data = yAxis.get(i);
                if(StringUtil.isNotEmpty(map.get(legend.get(i)))){
                    data.add(map.get(legend.get(i)));
                }else{
                    data.add("0");
                }
            }
        }
        System.out.println();

        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("xAxis",xAxis);
        resultMap.put("yAxis",yAxis);
        resultMap.put("legend",legend);
        return R.ok().put("data", resultMap);
    }
    /**
     * 柱状图统计双列
     * @return
     */
    @RequestMapping("/barCountTwo")
    public R barCountTwo(@RequestParam Map<String,Object> params) {
        logger.debug("柱状图统计双列:,,Controller:{},,params:{}",this.getClass().getName(),params);
        List<Map<String, Object>> result = commonService.barCountTwo(params);
        List<String> xAxis = new ArrayList<>();//报表x轴
        List<List<String>> yAxis = new ArrayList<>();//y轴
        List<String> legend = new ArrayList<>();//标题

        Map<String, HashMap<String, String>> dataMap = new LinkedHashMap<>();
        for(Map<String, Object> map :result){
            String name1Value = String.valueOf(map.get("name1"));
            String name2Value = String.valueOf(map.get("name2"));
            String value = String.valueOf(map.get("value"));
            if(!legend.contains(name2Value)){
                legend.add(name2Value);//添加完成后 就是最全的第二列的类型
            }
            if(dataMap.containsKey(name1Value)){
                dataMap.get(name1Value).put(name2Value,value);
            }else{
                HashMap<String, String> name1Data = new HashMap<>();
                name1Data.put(name2Value,value);
                dataMap.put(name1Value,name1Data);
            }

        }

        for(int i =0; i<legend.size(); i++){
            yAxis.add(new ArrayList<String>());
        }

        Set<String> keys = dataMap.keySet();
        for(String key:keys){
            xAxis.add(key);
            HashMap<String, String> map = dataMap.get(key);
            for(int i =0; i<legend.size(); i++){
                List<String> data = yAxis.get(i);
                if(StringUtil.isNotEmpty(map.get(legend.get(i)))){
                    data.add(map.get(legend.get(i)));
                }else{
                    data.add("0");
                }
            }
        }
        System.out.println();

        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("xAxis",xAxis);
        resultMap.put("yAxis",yAxis);
        resultMap.put("legend",legend);
        return R.ok().put("data", resultMap);
    }

    /**
     tableName 查询表
     condition1 条件1
     condition1Value 条件1值
     average 计算平均评分

     取值
     有值 Number(res.data.value.toFixed(1))
     无值 if(res.data){}
     * */
    @IgnoreAuth
    @RequestMapping("/queryScore")
    public R queryScore(@RequestParam Map<String, Object> params) {
        logger.debug("queryScore:,,Controller:{},,params:{}",this.getClass().getName(),params);
        Map<String, Object> queryScore = commonService.queryScore(params);
        return R.ok().put("data", queryScore);
    }

    /**
     * 查询字典表的分组统计总条数
     *  tableName  		表名
     *	groupColumn  	分组字段
     * @return
     */
    @RequestMapping("/newSelectGroupCount")
    public R newSelectGroupCount(@RequestParam Map<String,Object> params) {
        logger.debug("newSelectGroupCount:,,Controller:{},,params:{}",this.getClass().getName(),params);
        List<Map<String, Object>> result = commonService.newSelectGroupCount(params);
        return R.ok().put("data", result);
    }

    /**
     * 查询字典表的分组求和
     * tableName  		表名
     * groupColumn  		分组字段
     * sumCloum			统计字段
     * @return
     */
    @RequestMapping("/newSelectGroupSum")
    public R newSelectGroupSum(@RequestParam Map<String,Object> params) {
        logger.debug("newSelectGroupSum:,,Controller:{},,params:{}",this.getClass().getName(),params);
        List<Map<String, Object>> result = commonService.newSelectGroupSum(params);
        return R.ok().put("data", result);
    }

    /**
     * 柱状图求和 老的
     */
    @RequestMapping("/barSum")
    public R barSum(@RequestParam Map<String,Object> params) {
        logger.debug("barSum方法:,,Controller:{},,params:{}",this.getClass().getName(), com.alibaba.fastjson.JSONObject.toJSONString(params));
        Boolean isJoinTableFlag =  false;//是否有级联表相关
        String one =  "";//第一优先
        String two =  "";//第二优先

        //处理thisTable和joinTable 处理内容是把json字符串转为Map并把带有,的切割为数组
        //当前表
        Map<String,Object> thisTable = JSON.parseObject(String.valueOf(params.get("thisTable")),Map.class);
        params.put("thisTable",thisTable);

        //级联表
        String joinTableString = String.valueOf(params.get("joinTable"));
        if(StringUtil.isNotEmpty(joinTableString)) {
            Map<String, Object> joinTable = JSON.parseObject(joinTableString, Map.class);
            params.put("joinTable", joinTable);
            isJoinTableFlag = true;
        }

        if(StringUtil.isNotEmpty(String.valueOf(thisTable.get("date")))){//当前表日期
            thisTable.put("date",String.valueOf(thisTable.get("date")).split(","));
            one = "thisDate0";
        }
        if(isJoinTableFlag){//级联表日期
            Map<String, Object> joinTable = (Map<String, Object>) params.get("joinTable");
            if(StringUtil.isNotEmpty(String.valueOf(joinTable.get("date")))){
                joinTable.put("date",String.valueOf(joinTable.get("date")).split(","));
                if(StringUtil.isEmpty(one)){
                    one ="joinDate0";
                }else{
                    if(StringUtil.isEmpty(two)){
                        two ="joinDate0";
                    }
                }
            }
        }
        if(StringUtil.isNotEmpty(String.valueOf(thisTable.get("string")))){//当前表字符串
            thisTable.put("string",String.valueOf(thisTable.get("string")).split(","));
            if(StringUtil.isEmpty(one)){
                one ="thisString0";
            }else{
                if(StringUtil.isEmpty(two)){
                    two ="thisString0";
                }
            }
        }
        if(isJoinTableFlag){//级联表字符串
            Map<String, Object> joinTable = (Map<String, Object>) params.get("joinTable");
            if(StringUtil.isNotEmpty(String.valueOf(joinTable.get("string")))){
                joinTable.put("string",String.valueOf(joinTable.get("string")).split(","));
                if(StringUtil.isEmpty(one)){
                    one ="joinString0";
                }else{
                    if(StringUtil.isEmpty(two)){
                        two ="joinString0";
                    }
                }
            }
        }
        if(StringUtil.isNotEmpty(String.valueOf(thisTable.get("types")))){//当前表类型
            thisTable.put("types",String.valueOf(thisTable.get("types")).split(","));
            if(StringUtil.isEmpty(one)){
                one ="thisTypes0";
            }else{
                if(StringUtil.isEmpty(two)){
                    two ="thisTypes0";
                }
            }
        }
        if(isJoinTableFlag){//级联表类型
            Map<String, Object> joinTable = (Map<String, Object>) params.get("joinTable");
            if(StringUtil.isNotEmpty(String.valueOf(joinTable.get("types")))){
                joinTable.put("types",String.valueOf(joinTable.get("types")).split(","));
                if(StringUtil.isEmpty(one)){
                    one ="joinTypes0";
                }else{
                    if(StringUtil.isEmpty(two)){
                        two ="joinTypes0";
                    }
                }

            }
        }

        List<Map<String, Object>> result = commonService.barSum(params);

        List<String> xAxis = new ArrayList<>();//报表x轴
        List<List<String>> yAxis = new ArrayList<>();//y轴
        List<String> legend = new ArrayList<>();//标题

        if(StringUtil.isEmpty(two)){//不包含第二列
            List<String> yAxis0 = new ArrayList<>();
            yAxis.add(yAxis0);
            legend.add("");
            for(Map<String, Object> map :result){
                String oneValue = String.valueOf(map.get(one));
                String value = String.valueOf(map.get("value"));
                xAxis.add(oneValue);
                yAxis0.add(value);
            }
        }else{//包含第二列
            Map<String, HashMap<String, String>> dataMap = new LinkedHashMap<>();
            if(StringUtil.isNotEmpty(two)){
                for(Map<String, Object> map :result){
                    String oneValue = String.valueOf(map.get(one));
                    String twoValue = String.valueOf(map.get(two));
                    String value = String.valueOf(map.get("value"));
                    if(!legend.contains(twoValue)){
                        legend.add(twoValue);//添加完成后 就是最全的第二列的类型
                    }
                    if(dataMap.containsKey(oneValue)){
                        dataMap.get(oneValue).put(twoValue,value);
                    }else{
                        HashMap<String, String> oneData = new HashMap<>();
                        oneData.put(twoValue,value);
                        dataMap.put(oneValue,oneData);
                    }

                }
            }

            for(int i =0; i<legend.size(); i++){
                yAxis.add(new ArrayList<String>());
            }

            Set<String> keys = dataMap.keySet();
            for(String key:keys){
                xAxis.add(key);
                HashMap<String, String> map = dataMap.get(key);
                for(int i =0; i<legend.size(); i++){
                    List<String> data = yAxis.get(i);
                    if(StringUtil.isNotEmpty(map.get(legend.get(i)))){
                        data.add(map.get(legend.get(i)));
                    }else{
                        data.add("0");
                    }
                }
            }
            System.out.println();
        }

        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("xAxis",xAxis);
        resultMap.put("yAxis",yAxis);
        resultMap.put("legend",legend);
        return R.ok().put("data", resultMap);
    }

    /**
     * 柱状图统计 老的
     */
    @RequestMapping("/barCount")
    public R barCount(@RequestParam Map<String,Object> params) {
        logger.debug("barCount方法:,,Controller:{},,params:{}",this.getClass().getName(), com.alibaba.fastjson.JSONObject.toJSONString(params));
        Boolean isJoinTableFlag =  false;//是否有级联表相关
        String one =  "";//第一优先
        String two =  "";//第二优先

        //处理thisTable和joinTable 处理内容是把json字符串转为Map并把带有,的切割为数组
        //当前表
        Map<String,Object> thisTable = JSON.parseObject(String.valueOf(params.get("thisTable")),Map.class);
        params.put("thisTable",thisTable);

        //级联表
        String joinTableString = String.valueOf(params.get("joinTable"));
        if(StringUtil.isNotEmpty(joinTableString)) {
            Map<String, Object> joinTable = JSON.parseObject(joinTableString, Map.class);
            params.put("joinTable", joinTable);
            isJoinTableFlag = true;
        }

        if(StringUtil.isNotEmpty(String.valueOf(thisTable.get("date")))){//当前表日期
            thisTable.put("date",String.valueOf(thisTable.get("date")).split(","));
            one = "thisDate0";
        }
        if(isJoinTableFlag){//级联表日期
            Map<String, Object> joinTable = (Map<String, Object>) params.get("joinTable");
            if(StringUtil.isNotEmpty(String.valueOf(joinTable.get("date")))){
                joinTable.put("date",String.valueOf(joinTable.get("date")).split(","));
                if(StringUtil.isEmpty(one)){
                    one ="joinDate0";
                }else{
                    if(StringUtil.isEmpty(two)){
                        two ="joinDate0";
                    }
                }
            }
        }
        if(StringUtil.isNotEmpty(String.valueOf(thisTable.get("string")))){//当前表字符串
            thisTable.put("string",String.valueOf(thisTable.get("string")).split(","));
            if(StringUtil.isEmpty(one)){
                one ="thisString0";
            }else{
                if(StringUtil.isEmpty(two)){
                    two ="thisString0";
                }
            }
        }
        if(isJoinTableFlag){//级联表字符串
            Map<String, Object> joinTable = (Map<String, Object>) params.get("joinTable");
            if(StringUtil.isNotEmpty(String.valueOf(joinTable.get("string")))){
                joinTable.put("string",String.valueOf(joinTable.get("string")).split(","));
                if(StringUtil.isEmpty(one)){
                    one ="joinString0";
                }else{
                    if(StringUtil.isEmpty(two)){
                        two ="joinString0";
                    }
                }
            }
        }
        if(StringUtil.isNotEmpty(String.valueOf(thisTable.get("types")))){//当前表类型
            thisTable.put("types",String.valueOf(thisTable.get("types")).split(","));
            if(StringUtil.isEmpty(one)){
                one ="thisTypes0";
            }else{
                if(StringUtil.isEmpty(two)){
                    two ="thisTypes0";
                }
            }
        }
        if(isJoinTableFlag){//级联表类型
            Map<String, Object> joinTable = (Map<String, Object>) params.get("joinTable");
            if(StringUtil.isNotEmpty(String.valueOf(joinTable.get("types")))){
                joinTable.put("types",String.valueOf(joinTable.get("types")).split(","));
                if(StringUtil.isEmpty(one)){
                    one ="joinTypes0";
                }else{
                    if(StringUtil.isEmpty(two)){
                        two ="joinTypes0";
                    }
                }

            }
        }

        List<Map<String, Object>> result = commonService.barCount(params);

        List<String> xAxis = new ArrayList<>();//报表x轴
        List<List<String>> yAxis = new ArrayList<>();//y轴
        List<String> legend = new ArrayList<>();//标题

        if(StringUtil.isEmpty(two)){//不包含第二列
            List<String> yAxis0 = new ArrayList<>();
            yAxis.add(yAxis0);
            legend.add("");
            for(Map<String, Object> map :result){
                String oneValue = String.valueOf(map.get(one));
                String value = String.valueOf(map.get("value"));
                xAxis.add(oneValue);
                yAxis0.add(value);
            }
        }else{//包含第二列
            Map<String, HashMap<String, String>> dataMap = new LinkedHashMap<>();
            if(StringUtil.isNotEmpty(two)){
                for(Map<String, Object> map :result){
                    String oneValue = String.valueOf(map.get(one));
                    String twoValue = String.valueOf(map.get(two));
                    String value = String.valueOf(map.get("value"));
                    if(!legend.contains(twoValue)){
                        legend.add(twoValue);//添加完成后 就是最全的第二列的类型
                    }
                    if(dataMap.containsKey(oneValue)){
                        dataMap.get(oneValue).put(twoValue,value);
                    }else{
                        HashMap<String, String> oneData = new HashMap<>();
                        oneData.put(twoValue,value);
                        dataMap.put(oneValue,oneData);
                    }

                }
            }

            for(int i =0; i<legend.size(); i++){
                yAxis.add(new ArrayList<String>());
            }

            Set<String> keys = dataMap.keySet();
            for(String key:keys){
                xAxis.add(key);
                HashMap<String, String> map = dataMap.get(key);
                for(int i =0; i<legend.size(); i++){
                    List<String> data = yAxis.get(i);
                    if(StringUtil.isNotEmpty(map.get(legend.get(i)))){
                        data.add(map.get(legend.get(i)));
                    }else{
                        data.add("0");
                    }
                }
            }
            System.out.println();
        }

        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("xAxis",xAxis);
        resultMap.put("yAxis",yAxis);
        resultMap.put("legend",legend);
        return R.ok().put("data", resultMap);
    }
}
