
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
 * 字典
 * 后端接口
 * @author
 * @email
*/
@RestController
@Controller
@RequestMapping("/dictionary")
public class DictionaryController {
    private static final Logger logger = LoggerFactory.getLogger(DictionaryController.class);

    private static final String TABLE_NAME = "dictionary";

    @Autowired
    private DictionaryService dictionaryService;


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
    private SijiService sijiService;//司机
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
    @IgnoreAuth
    public R page(@RequestParam Map<String, Object> params, HttpServletRequest request){
        logger.debug("page方法:,,Controller:{},,params:{}",this.getClass().getName(),JSONObject.toJSONString(params));
        CommonUtil.checkMap(params);
        PageUtils page = dictionaryService.queryPage(params);

        //字典表数据转换
        List<DictionaryView> list =(List<DictionaryView>)page.getList();
        for(DictionaryView c:list){
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
        DictionaryEntity dictionary = dictionaryService.selectById(id);
        if(dictionary !=null){
            //entity转view
            DictionaryView view = new DictionaryView();
            BeanUtils.copyProperties( dictionary , view );//把实体数据重构到view中
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
    public R save(@RequestBody DictionaryEntity dictionary, HttpServletRequest request){
        logger.debug("save方法:,,Controller:{},,dictionary:{}",this.getClass().getName(),dictionary.toString());

        String role = String.valueOf(request.getSession().getAttribute("role"));
        if(false)
            return R.error(511,"永远不会进入");

        Wrapper<DictionaryEntity> queryWrapper = new EntityWrapper<DictionaryEntity>()
            .eq("dic_code", dictionary.getDicCode())
            .eq("index_name", dictionary.getIndexName())
            ;
        if(dictionary.getDicCode().contains("_erji_types")){
            queryWrapper.eq("super_id",dictionary.getSuperId());
        }

        logger.info("sql语句:"+queryWrapper.getSqlSegment());
        DictionaryEntity dictionaryEntity = dictionaryService.selectOne(queryWrapper);
        if(dictionaryEntity==null){
            dictionary.setCreateTime(new Date());
            dictionaryService.insert(dictionary);
            //字典表新增数据,把数据再重新查出,放入监听器中
            List<DictionaryEntity> dictionaryEntities = dictionaryService.selectList(new EntityWrapper<DictionaryEntity>());
            ServletContext servletContext = request.getServletContext();
            Map<String, Map<Integer,String>> map = new HashMap<>();
            for(DictionaryEntity d :dictionaryEntities){
                Map<Integer, String> m = map.get(d.getDicCode());
                if(m ==null || m.isEmpty()){
                    m = new HashMap<>();
                }
                m.put(d.getCodeIndex(),d.getIndexName());
                map.put(d.getDicCode(),m);
            }
            servletContext.setAttribute("dictionaryMap",map);
            return R.ok();
        }else {
            return R.error(511,"表中有相同数据");
        }
    }

    /**
    * 后端修改
    */
    @RequestMapping("/update")
    public R update(@RequestBody DictionaryEntity dictionary, HttpServletRequest request) throws NoSuchFieldException, ClassNotFoundException, IllegalAccessException, InstantiationException {
        logger.debug("update方法:,,Controller:{},,dictionary:{}",this.getClass().getName(),dictionary.toString());
        DictionaryEntity oldDictionaryEntity = dictionaryService.selectById(dictionary.getId());//查询原先数据

        String role = String.valueOf(request.getSession().getAttribute("role"));
//        if(false)
//            return R.error(511,"永远不会进入");

            dictionaryService.updateById(dictionary);//根据id更新
            //如果字典表修改数据的话,把数据再重新查出,放入监听器中
            List<DictionaryEntity> dictionaryEntities = dictionaryService.selectList(new EntityWrapper<DictionaryEntity>());
            ServletContext servletContext = request.getServletContext();
            Map<String, Map<Integer,String>> map = new HashMap<>();
            for(DictionaryEntity d :dictionaryEntities){
                Map<Integer, String> m = map.get(d.getDicCode());
                if(m ==null || m.isEmpty()){
                    m = new HashMap<>();
                }
                m.put(d.getCodeIndex(),d.getIndexName());
                map.put(d.getDicCode(),m);
            }
            servletContext.setAttribute("dictionaryMap",map);
            return R.ok();
    }



    /**
    * 删除
    */
    @RequestMapping("/delete")
    public R delete(@RequestBody Integer[] ids, HttpServletRequest request){
        logger.debug("delete:,,Controller:{},,ids:{}",this.getClass().getName(),ids.toString());
        List<DictionaryEntity> oldDictionaryList =dictionaryService.selectBatchIds(Arrays.asList(ids));//要删除的数据
        dictionaryService.deleteBatchIds(Arrays.asList(ids));

        return R.ok();
    }

    /**
     * 最大值
     */
    @RequestMapping("/maxCodeIndex")
    public R maxCodeIndex(@RequestBody DictionaryEntity dictionary){
        logger.debug("maxCodeIndex:,,Controller:{},,dictionary:{}",this.getClass().getName(),dictionary.toString());
        List<String> descs = new ArrayList<>();
        descs.add("code_index");
        Wrapper<DictionaryEntity> queryWrapper = new EntityWrapper<DictionaryEntity>()
                .eq("dic_code", dictionary.getDicCode())
                .orderDesc(descs);
        logger.info("sql语句:"+queryWrapper.getSqlSegment());
        List<DictionaryEntity> dictionaryEntityList = dictionaryService.selectList(queryWrapper);
        if(dictionaryEntityList.size()>0 ){
            return R.ok().put("maxCodeIndex",dictionaryEntityList.get(0).getCodeIndex()+1);
        }else{
            return R.ok().put("maxCodeIndex",1);
        }
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
            List<DictionaryEntity> dictionaryList = new ArrayList<>();//上传的东西
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
                            DictionaryEntity dictionaryEntity = new DictionaryEntity();
//                            dictionaryEntity.setDicCode(data.get(0));                    //字段 要改的
//                            dictionaryEntity.setDicName(data.get(0));                    //字段名 要改的
//                            dictionaryEntity.setCodeIndex(Integer.valueOf(data.get(0)));   //编码 要改的
//                            dictionaryEntity.setIndexName(data.get(0));                    //编码名字 要改的
//                            dictionaryEntity.setSuperId(Integer.valueOf(data.get(0)));   //父字段id 要改的
//                            dictionaryEntity.setBeizhu(data.get(0));                    //备注 要改的
//                            dictionaryEntity.setCreateTime(date);//时间
                            dictionaryList.add(dictionaryEntity);


                            //把要查询是否重复的字段放入map中
                        }

                        //查询是否重复
                        dictionaryService.insertBatch(dictionaryList);
                        return R.ok();
                    }
                }
            }
        }catch (Exception e){
            e.printStackTrace();
            return R.error(511,"批量插入数据异常，请联系管理员");
        }
    }




}

