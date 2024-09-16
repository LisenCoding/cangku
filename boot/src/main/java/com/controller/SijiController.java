
package com.controller;

import java.io.File;
import java.math.BigDecimal;
import java.net.URL;
import java.text.SimpleDateFormat;
import com.alibaba.fastjson.JSONObject;
import java.util.*;
import org.springframework.beans.BeanUtils;
import javax.servlet.http.HttpServletRequest;
import org.springframework.web.context.ContextLoader;
import javax.servlet.ServletContext;
import com.service.TokenService;
import com.utils.*;
import java.lang.reflect.InvocationTargetException;

import com.service.DictionaryService;
import org.apache.commons.lang3.StringUtils;
import com.annotation.IgnoreAuth;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.entity.*;
import com.entity.view.*;
import com.service.*;
import com.utils.PageUtils;
import com.utils.R;
import com.alibaba.fastjson.*;

/**
 * 司机
 * 后端接口
 * @author
 * @email
*/
@RestController
@Controller
@RequestMapping("/siji")
public class SijiController {
    private static final Logger logger = LoggerFactory.getLogger(SijiController.class);

    private static final String TABLE_NAME = "siji";

    @Autowired
    private SijiService sijiService;


    @Autowired
    private TokenService tokenService;

    @Autowired
    private AddressService addressService;//收货地址
    @Autowired
    private CangkuService cangkuService;//仓库
    @Autowired
    private ChatService chatService;//反馈信息
    @Autowired
    private CheliangService cheliangService;//车辆
    @Autowired
    private DictionaryService dictionaryService;//字典
    @Autowired
    private GonggaoService gonggaoService;//公告
    @Autowired
    private HuowuService huowuService;//货物
    @Autowired
    private ShangpinService shangpinService;//商品
    @Autowired
    private ShangpinChuruInoutService shangpinChuruInoutService;//出入库
    @Autowired
    private ShangpinChuruInoutListService shangpinChuruInoutListService;//出入库详情
    @Autowired
    private ShangpinCollectionService shangpinCollectionService;//商品收藏
    @Autowired
    private ShangpinOrderService shangpinOrderService;//商品订单
    @Autowired
    private YonghuService yonghuService;//用户
    @Autowired
    private YuangongService yuangongService;//员工
    @Autowired
    private UsersService usersService;//管理员


    /**
    * 后端列表
    */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params, HttpServletRequest request){
        logger.debug("page方法:,,Controller:{},,params:{}",this.getClass().getName(),JSONObject.toJSONString(params));
        String role = String.valueOf(request.getSession().getAttribute("role"));
        if(false)
            return R.error(511,"永不会进入");
        else if("用户".equals(role))
            params.put("yonghuId",request.getSession().getAttribute("userId"));
        else if("员工".equals(role))
            params.put("yuangongId",request.getSession().getAttribute("userId"));
        CommonUtil.checkMap(params);
        PageUtils page = sijiService.queryPage(params);

        //字典表数据转换
        List<SijiView> list =(List<SijiView>)page.getList();
        for(SijiView c:list){
            //修改对应字典表字段
            dictionaryService.dictionaryConvert(c, request);
        }
        return R.ok().put("data", page);
    }

    /**
    * 后端详情
    */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id, HttpServletRequest request){
        logger.debug("info方法:,,Controller:{},,id:{}",this.getClass().getName(),id);
        SijiEntity siji = sijiService.selectById(id);
        if(siji !=null){
            //entity转view
            SijiView view = new SijiView();
            BeanUtils.copyProperties( siji , view );//把实体数据重构到view中
            //修改对应字典表字段
            dictionaryService.dictionaryConvert(view, request);
            return R.ok().put("data", view);
        }else {
            return R.error(511,"查不到数据");
        }

    }

    /**
    * 后端保存
    */
    @RequestMapping("/save")
    public R save(@RequestBody SijiEntity siji, HttpServletRequest request){
        logger.debug("save方法:,,Controller:{},,siji:{}",this.getClass().getName(),siji.toString());

        String role = String.valueOf(request.getSession().getAttribute("role"));
        if(false)
            return R.error(511,"永远不会进入");

        Wrapper<SijiEntity> queryWrapper = new EntityWrapper<SijiEntity>()
            .eq("siji_name", siji.getSijiName())
            .eq("siji_phone", siji.getSijiPhone())
            .eq("siji_id_number", siji.getSijiIdNumber())
            .eq("sex_types", siji.getSexTypes())
            .eq("siji_email", siji.getSijiEmail())
            ;

        logger.info("sql语句:"+queryWrapper.getSqlSegment());
        SijiEntity sijiEntity = sijiService.selectOne(queryWrapper);
        if(sijiEntity==null){
            siji.setCreateTime(new Date());
            sijiService.insert(siji);
            return R.ok();
        }else {
            return R.error(511,"表中有相同数据");
        }
    }

    /**
    * 后端修改
    */
    @RequestMapping("/update")
    public R update(@RequestBody SijiEntity siji, HttpServletRequest request) throws NoSuchFieldException, ClassNotFoundException, IllegalAccessException, InstantiationException {
        logger.debug("update方法:,,Controller:{},,siji:{}",this.getClass().getName(),siji.toString());
        SijiEntity oldSijiEntity = sijiService.selectById(siji.getId());//查询原先数据

        String role = String.valueOf(request.getSession().getAttribute("role"));
//        if(false)
//            return R.error(511,"永远不会进入");
        if("".equals(siji.getSijiPhoto()) || "null".equals(siji.getSijiPhoto())){
                siji.setSijiPhoto(null);
        }

            sijiService.updateById(siji);//根据id更新
            return R.ok();
    }



    /**
    * 删除
    */
    @RequestMapping("/delete")
    public R delete(@RequestBody Integer[] ids, HttpServletRequest request){
        logger.debug("delete:,,Controller:{},,ids:{}",this.getClass().getName(),ids.toString());
        List<SijiEntity> oldSijiList =sijiService.selectBatchIds(Arrays.asList(ids));//要删除的数据
        sijiService.deleteBatchIds(Arrays.asList(ids));

        return R.ok();
    }


    /**
     * 批量上传
     */
    @RequestMapping("/batchInsert")
    public R save( String fileName, HttpServletRequest request){
        logger.debug("batchInsert方法:,,Controller:{},,fileName:{}",this.getClass().getName(),fileName);
        Integer yonghuId = Integer.valueOf(String.valueOf(request.getSession().getAttribute("userId")));
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        //.eq("time", new SimpleDateFormat("yyyy-MM-dd").format(new Date()))
        try {
            List<SijiEntity> sijiList = new ArrayList<>();//上传的东西
            Map<String, List<String>> seachFields= new HashMap<>();//要查询的字段
            Date date = new Date();
            int lastIndexOf = fileName.lastIndexOf(".");
            if(lastIndexOf == -1){
                return R.error(511,"该文件没有后缀");
            }else{
                String suffix = fileName.substring(lastIndexOf);
                if(!".xls".equals(suffix)){
                    return R.error(511,"只支持后缀为xls的excel文件");
                }else{
                    URL resource = this.getClass().getClassLoader().getResource("static/upload/" + fileName);//获取文件路径
                    File file = new File(resource.getFile());
                    if(!file.exists()){
                        return R.error(511,"找不到上传文件，请联系管理员");
                    }else{
                        List<List<String>> dataList = PoiUtil.poiImport(file.getPath());//读取xls文件
                        dataList.remove(0);//删除第一行，因为第一行是提示
                        for(List<String> data:dataList){
                            //循环
                            SijiEntity sijiEntity = new SijiEntity();
//                            sijiEntity.setSijiUuidNumber(data.get(0));                    //司机编号 要改的
//                            sijiEntity.setSijiName(data.get(0));                    //司机姓名 要改的
//                            sijiEntity.setSijiPhone(data.get(0));                    //司机手机号 要改的
//                            sijiEntity.setSijiIdNumber(data.get(0));                    //司机身份证号 要改的
//                            sijiEntity.setSijiPhoto("");//详情和图片
//                            sijiEntity.setSexTypes(Integer.valueOf(data.get(0)));   //性别 要改的
//                            sijiEntity.setSijiEmail(data.get(0));                    //司机邮箱 要改的
//                            sijiEntity.setCreateTime(date);//时间
                            sijiList.add(sijiEntity);


                            //把要查询是否重复的字段放入map中
                                //司机编号
                                if(seachFields.containsKey("sijiUuidNumber")){
                                    List<String> sijiUuidNumber = seachFields.get("sijiUuidNumber");
                                    sijiUuidNumber.add(data.get(0));//要改的
                                }else{
                                    List<String> sijiUuidNumber = new ArrayList<>();
                                    sijiUuidNumber.add(data.get(0));//要改的
                                    seachFields.put("sijiUuidNumber",sijiUuidNumber);
                                }
                                //司机手机号
                                if(seachFields.containsKey("sijiPhone")){
                                    List<String> sijiPhone = seachFields.get("sijiPhone");
                                    sijiPhone.add(data.get(0));//要改的
                                }else{
                                    List<String> sijiPhone = new ArrayList<>();
                                    sijiPhone.add(data.get(0));//要改的
                                    seachFields.put("sijiPhone",sijiPhone);
                                }
                                //司机身份证号
                                if(seachFields.containsKey("sijiIdNumber")){
                                    List<String> sijiIdNumber = seachFields.get("sijiIdNumber");
                                    sijiIdNumber.add(data.get(0));//要改的
                                }else{
                                    List<String> sijiIdNumber = new ArrayList<>();
                                    sijiIdNumber.add(data.get(0));//要改的
                                    seachFields.put("sijiIdNumber",sijiIdNumber);
                                }
                        }

                        //查询是否重复
                         //司机编号
                        List<SijiEntity> sijiEntities_sijiUuidNumber = sijiService.selectList(new EntityWrapper<SijiEntity>().in("siji_uuid_number", seachFields.get("sijiUuidNumber")));
                        if(sijiEntities_sijiUuidNumber.size() >0 ){
                            ArrayList<String> repeatFields = new ArrayList<>();
                            for(SijiEntity s:sijiEntities_sijiUuidNumber){
                                repeatFields.add(s.getSijiUuidNumber());
                            }
                            return R.error(511,"数据库的该表中的 [司机编号] 字段已经存在 存在数据为:"+repeatFields.toString());
                        }
                         //司机手机号
                        List<SijiEntity> sijiEntities_sijiPhone = sijiService.selectList(new EntityWrapper<SijiEntity>().in("siji_phone", seachFields.get("sijiPhone")));
                        if(sijiEntities_sijiPhone.size() >0 ){
                            ArrayList<String> repeatFields = new ArrayList<>();
                            for(SijiEntity s:sijiEntities_sijiPhone){
                                repeatFields.add(s.getSijiPhone());
                            }
                            return R.error(511,"数据库的该表中的 [司机手机号] 字段已经存在 存在数据为:"+repeatFields.toString());
                        }
                         //司机身份证号
                        List<SijiEntity> sijiEntities_sijiIdNumber = sijiService.selectList(new EntityWrapper<SijiEntity>().in("siji_id_number", seachFields.get("sijiIdNumber")));
                        if(sijiEntities_sijiIdNumber.size() >0 ){
                            ArrayList<String> repeatFields = new ArrayList<>();
                            for(SijiEntity s:sijiEntities_sijiIdNumber){
                                repeatFields.add(s.getSijiIdNumber());
                            }
                            return R.error(511,"数据库的该表中的 [司机身份证号] 字段已经存在 存在数据为:"+repeatFields.toString());
                        }
                        sijiService.insertBatch(sijiList);
                        return R.ok();
                    }
                }
            }
        }catch (Exception e){
            e.printStackTrace();
            return R.error(511,"批量插入数据异常，请联系管理员");
        }
    }




    /**
    * 前端列表
    */
    @IgnoreAuth
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params, HttpServletRequest request){
        logger.debug("list方法:,,Controller:{},,params:{}",this.getClass().getName(),JSONObject.toJSONString(params));

        CommonUtil.checkMap(params);
        PageUtils page = sijiService.queryPage(params);

        //字典表数据转换
        List<SijiView> list =(List<SijiView>)page.getList();
        for(SijiView c:list)
            dictionaryService.dictionaryConvert(c, request); //修改对应字典表字段

        return R.ok().put("data", page);
    }

    /**
    * 前端详情
    */
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Integer id, HttpServletRequest request){
        logger.debug("detail方法:,,Controller:{},,id:{}",this.getClass().getName(),id);
        SijiEntity siji = sijiService.selectById(id);
            if(siji !=null){


                //entity转view
                SijiView view = new SijiView();
                BeanUtils.copyProperties( siji , view );//把实体数据重构到view中

                //修改对应字典表字段
                dictionaryService.dictionaryConvert(view, request);
                return R.ok().put("data", view);
            }else {
                return R.error(511,"查不到数据");
            }
    }


    /**
    * 前端保存
    */
    @RequestMapping("/add")
    public R add(@RequestBody SijiEntity siji, HttpServletRequest request){
        logger.debug("add方法:,,Controller:{},,siji:{}",this.getClass().getName(),siji.toString());
        Wrapper<SijiEntity> queryWrapper = new EntityWrapper<SijiEntity>()
            .eq("siji_uuid_number", siji.getSijiUuidNumber())
            .eq("siji_name", siji.getSijiName())
            .eq("siji_phone", siji.getSijiPhone())
            .eq("siji_id_number", siji.getSijiIdNumber())
            .eq("sex_types", siji.getSexTypes())
            .eq("siji_email", siji.getSijiEmail())
//            .notIn("siji_types", new Integer[]{102})
            ;
        logger.info("sql语句:"+queryWrapper.getSqlSegment());
        SijiEntity sijiEntity = sijiService.selectOne(queryWrapper);
        if(sijiEntity==null){
            siji.setCreateTime(new Date());
        sijiService.insert(siji);

            return R.ok();
        }else {
            return R.error(511,"表中有相同数据");
        }
    }

}

