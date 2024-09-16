
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
 * 出入库
 * 后端接口
 * @author
 * @email
*/
@RestController
@Controller
@RequestMapping("/shangpinChuruInout")
public class ShangpinChuruInoutController {
    private static final Logger logger = LoggerFactory.getLogger(ShangpinChuruInoutController.class);

    private static final String TABLE_NAME = "shangpinChuruInout";

    @Autowired
    private ShangpinChuruInoutService shangpinChuruInoutService;


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
        PageUtils page = shangpinChuruInoutService.queryPage(params);

        //字典表数据转换
        List<ShangpinChuruInoutView> list =(List<ShangpinChuruInoutView>)page.getList();
        for(ShangpinChuruInoutView c:list){
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
        ShangpinChuruInoutEntity shangpinChuruInout = shangpinChuruInoutService.selectById(id);
        if(shangpinChuruInout !=null){
            //entity转view
            ShangpinChuruInoutView view = new ShangpinChuruInoutView();
            BeanUtils.copyProperties( shangpinChuruInout , view );//把实体数据重构到view中
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
    public R save(@RequestBody ShangpinChuruInoutEntity shangpinChuruInout, HttpServletRequest request){
        logger.debug("save方法:,,Controller:{},,shangpinChuruInout:{}",this.getClass().getName(),shangpinChuruInout.toString());

        String role = String.valueOf(request.getSession().getAttribute("role"));
        if(false)
            return R.error(511,"永远不会进入");

        Wrapper<ShangpinChuruInoutEntity> queryWrapper = new EntityWrapper<ShangpinChuruInoutEntity>()
            .eq("shangpin_churu_inout_name", shangpinChuruInout.getShangpinChuruInoutName())
            .eq("shangpin_churu_inout_types", shangpinChuruInout.getShangpinChuruInoutTypes())
            ;

        logger.info("sql语句:"+queryWrapper.getSqlSegment());
        ShangpinChuruInoutEntity shangpinChuruInoutEntity = shangpinChuruInoutService.selectOne(queryWrapper);
        if(shangpinChuruInoutEntity==null){
            shangpinChuruInout.setInsertTime(new Date());
            shangpinChuruInout.setCreateTime(new Date());
            shangpinChuruInoutService.insert(shangpinChuruInout);
            return R.ok();
        }else {
            return R.error(511,"表中有相同数据");
        }
    }

    /**
    * 后端修改
    */
    @RequestMapping("/update")
    public R update(@RequestBody ShangpinChuruInoutEntity shangpinChuruInout, HttpServletRequest request) throws NoSuchFieldException, ClassNotFoundException, IllegalAccessException, InstantiationException {
        logger.debug("update方法:,,Controller:{},,shangpinChuruInout:{}",this.getClass().getName(),shangpinChuruInout.toString());
        ShangpinChuruInoutEntity oldShangpinChuruInoutEntity = shangpinChuruInoutService.selectById(shangpinChuruInout.getId());//查询原先数据

        String role = String.valueOf(request.getSession().getAttribute("role"));
//        if(false)
//            return R.error(511,"永远不会进入");
        if("".equals(shangpinChuruInout.getShangpinChuruInoutContent()) || "null".equals(shangpinChuruInout.getShangpinChuruInoutContent())){
                shangpinChuruInout.setShangpinChuruInoutContent(null);
        }

            shangpinChuruInoutService.updateById(shangpinChuruInout);//根据id更新
            return R.ok();
    }


    /**
    * 出库
    */
    @RequestMapping("/outShangpinChuruInoutList")
    public R outShangpinChuruInoutList(@RequestBody  Map<String, Object> params,HttpServletRequest request){
        logger.debug("outShangpinChuruInoutList方法:,,Controller:{},,params:{}",this.getClass().getName(),JSONObject.toJSONString(params));

        String role = String.valueOf(request.getSession().getAttribute("role"));

        //取出入库名称并判断是否存在
        String shangpinChuruInoutName = String.valueOf(params.get("shangpinChuruInoutName"));
        Wrapper<ShangpinChuruInoutEntity> queryWrapper = new EntityWrapper<ShangpinChuruInoutEntity>()
            .eq("shangpin_churu_inout_name", shangpinChuruInoutName)
            ;
        ShangpinChuruInoutEntity shangpinChuruInoutSelectOne = shangpinChuruInoutService.selectOne(queryWrapper);
        if(shangpinChuruInoutSelectOne != null)
            return R.error(511,"出入库名称已被使用");



        Map<String, Integer> map = (Map<String, Integer>) params.get("map");
        if(map == null || map.size() == 0)
            return R.error(511,"列表内容不能为空");


        Set<String> ids = map.keySet();

        List<ShangpinEntity> shangpinList = shangpinService.selectBatchIds(ids);
        if(shangpinList == null || shangpinList.size() == 0){
            return R.error(511,"查数据库查不到数据");
        }else{
            for(ShangpinEntity w:shangpinList){
                Integer value = w.getShangpinKucunNumber()-map.get(String.valueOf(w.getId()));
                if(value <0){
                    return R.error(511,"出库数量大于库存数量");
                }
                w.setShangpinKucunNumber(value);
            }
        }

        //当前表
        ShangpinChuruInoutEntity shangpinChuruInoutEntity = new ShangpinChuruInoutEntity<>();
            shangpinChuruInoutEntity.setShangpinChuruInoutUuidNumber(String.valueOf(new Date().getTime()));
            shangpinChuruInoutEntity.setShangpinChuruInoutName(shangpinChuruInoutName);
            shangpinChuruInoutEntity.setShangpinChuruInoutTypes(1);
            shangpinChuruInoutEntity.setShangpinChuruInoutContent("");
            shangpinChuruInoutEntity.setInsertTime(new Date());
            shangpinChuruInoutEntity.setCreateTime(new Date());

        boolean insertShangpinChuruInout = shangpinChuruInoutService.insert(shangpinChuruInoutEntity);
        //list表
        ArrayList<ShangpinChuruInoutListEntity> shangpinChuruInoutLists = new ArrayList<>();
        if(insertShangpinChuruInout){
            for(String id:ids){
                ShangpinChuruInoutListEntity shangpinChuruInoutListEntity = new ShangpinChuruInoutListEntity();
                    shangpinChuruInoutListEntity.setShangpinChuruInoutId(shangpinChuruInoutEntity.getId());
                    shangpinChuruInoutListEntity.setShangpinId(Integer.valueOf(id));
//                    shangpinChuruInoutListEntity.setCangkuId(cangkuId);
                    shangpinChuruInoutListEntity.setShangpinChuruInoutListNumber(map.get(id));
                    shangpinChuruInoutListEntity.setInsertTime(new Date());
                    shangpinChuruInoutListEntity.setCreateTime(new Date());
                shangpinChuruInoutLists.add(shangpinChuruInoutListEntity);
                shangpinService.updateBatchById(shangpinList);
            }
            shangpinChuruInoutListService.insertBatch(shangpinChuruInoutLists);
        }

        return R.ok();
    }

    /**
    *入库
    */
    @RequestMapping("/inShangpinChuruInoutList")
    public R inShangpinChuruInoutList(@RequestBody  Map<String, Object> params,HttpServletRequest request){
        logger.debug("inShangpinChuruInoutList方法:,,Controller:{},,params:{}",this.getClass().getName(),JSONObject.toJSONString(params));
        //params:{"map":{"1":2,"2":3},"wuziOutinName":"订单1"}

        String role = String.valueOf(request.getSession().getAttribute("role"));

        //取当前表名称并判断
        String shangpinChuruInoutName = String.valueOf(params.get("shangpinChuruInoutName"));
        Wrapper<ShangpinChuruInoutEntity> queryWrapper = new EntityWrapper<ShangpinChuruInoutEntity>()
            .eq("shangpin_churu_inout_name", shangpinChuruInoutName)
            ;
        ShangpinChuruInoutEntity shangpinChuruInoutSelectOne = shangpinChuruInoutService.selectOne(queryWrapper);
        if(shangpinChuruInoutSelectOne != null)
            return R.error(511,"出入库名称已被使用");


        Map<String, Integer> map = (Map<String, Integer>) params.get("map");
        if(map == null || map.size() == 0)
            return R.error(511,"列表内容不能为空");

        Set<String> ids = map.keySet();

        List<ShangpinEntity> shangpinList = shangpinService.selectBatchIds(ids);
        if(shangpinList == null || shangpinList.size() == 0){
            return R.error(511,"查数据库查不到数据");
        }else{
            for(ShangpinEntity w:shangpinList){
                w.setShangpinKucunNumber(w.getShangpinKucunNumber()+map.get(String.valueOf(w.getId())));
            }
        }

        //当前表
        ShangpinChuruInoutEntity shangpinChuruInoutEntity = new ShangpinChuruInoutEntity<>();
            shangpinChuruInoutEntity.setShangpinChuruInoutUuidNumber(String.valueOf(new Date().getTime()));
            shangpinChuruInoutEntity.setShangpinChuruInoutName(shangpinChuruInoutName);
            shangpinChuruInoutEntity.setShangpinChuruInoutTypes(2);
            shangpinChuruInoutEntity.setShangpinChuruInoutContent("");
            shangpinChuruInoutEntity.setInsertTime(new Date());
            shangpinChuruInoutEntity.setCreateTime(new Date());


        boolean insertShangpinChuruInout = shangpinChuruInoutService.insert(shangpinChuruInoutEntity);
        //list表
        ArrayList<ShangpinChuruInoutListEntity> shangpinChuruInoutLists = new ArrayList<>();
        if(insertShangpinChuruInout){
            for(String id:ids){
                ShangpinChuruInoutListEntity shangpinChuruInoutListEntity = new ShangpinChuruInoutListEntity();
                shangpinChuruInoutListEntity.setShangpinChuruInoutId(shangpinChuruInoutEntity.getId());
                shangpinChuruInoutListEntity.setShangpinId(Integer.valueOf(id));
//                shangpinChuruInoutListEntity.setCangkuId(cangkuId);
                shangpinChuruInoutListEntity.setShangpinChuruInoutListNumber(map.get(id));
                shangpinChuruInoutListEntity.setInsertTime(new Date());
                shangpinChuruInoutListEntity.setCreateTime(new Date());
                shangpinChuruInoutLists.add(shangpinChuruInoutListEntity);
                shangpinService.updateBatchById(shangpinList);
            }
            shangpinChuruInoutListService.insertBatch(shangpinChuruInoutLists);
        }

        return R.ok();
    }
    /**
    * 删除
    */
    @RequestMapping("/delete")
    public R delete(@RequestBody Integer[] ids, HttpServletRequest request){
        logger.debug("delete:,,Controller:{},,ids:{}",this.getClass().getName(),ids.toString());
        List<ShangpinChuruInoutEntity> oldShangpinChuruInoutList =shangpinChuruInoutService.selectBatchIds(Arrays.asList(ids));//要删除的数据
        shangpinChuruInoutService.deleteBatchIds(Arrays.asList(ids));
        shangpinChuruInoutListService.delete(new EntityWrapper<ShangpinChuruInoutListEntity>().in("shangpin_churu_inout_id",ids));

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
            List<ShangpinChuruInoutEntity> shangpinChuruInoutList = new ArrayList<>();//上传的东西
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
                            ShangpinChuruInoutEntity shangpinChuruInoutEntity = new ShangpinChuruInoutEntity();
//                            shangpinChuruInoutEntity.setShangpinChuruInoutUuidNumber(data.get(0));                    //出入库流水号 要改的
//                            shangpinChuruInoutEntity.setShangpinChuruInoutName(data.get(0));                    //出入库名称 要改的
//                            shangpinChuruInoutEntity.setShangpinChuruInoutTypes(Integer.valueOf(data.get(0)));   //出入库类型 要改的
//                            shangpinChuruInoutEntity.setShangpinChuruInoutContent("");//详情和图片
//                            shangpinChuruInoutEntity.setInsertTime(date);//时间
//                            shangpinChuruInoutEntity.setCreateTime(date);//时间
                            shangpinChuruInoutList.add(shangpinChuruInoutEntity);


                            //把要查询是否重复的字段放入map中
                                //出入库流水号
                                if(seachFields.containsKey("shangpinChuruInoutUuidNumber")){
                                    List<String> shangpinChuruInoutUuidNumber = seachFields.get("shangpinChuruInoutUuidNumber");
                                    shangpinChuruInoutUuidNumber.add(data.get(0));//要改的
                                }else{
                                    List<String> shangpinChuruInoutUuidNumber = new ArrayList<>();
                                    shangpinChuruInoutUuidNumber.add(data.get(0));//要改的
                                    seachFields.put("shangpinChuruInoutUuidNumber",shangpinChuruInoutUuidNumber);
                                }
                        }

                        //查询是否重复
                         //出入库流水号
                        List<ShangpinChuruInoutEntity> shangpinChuruInoutEntities_shangpinChuruInoutUuidNumber = shangpinChuruInoutService.selectList(new EntityWrapper<ShangpinChuruInoutEntity>().in("shangpin_churu_inout_uuid_number", seachFields.get("shangpinChuruInoutUuidNumber")));
                        if(shangpinChuruInoutEntities_shangpinChuruInoutUuidNumber.size() >0 ){
                            ArrayList<String> repeatFields = new ArrayList<>();
                            for(ShangpinChuruInoutEntity s:shangpinChuruInoutEntities_shangpinChuruInoutUuidNumber){
                                repeatFields.add(s.getShangpinChuruInoutUuidNumber());
                            }
                            return R.error(511,"数据库的该表中的 [出入库流水号] 字段已经存在 存在数据为:"+repeatFields.toString());
                        }
                        shangpinChuruInoutService.insertBatch(shangpinChuruInoutList);
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
        PageUtils page = shangpinChuruInoutService.queryPage(params);

        //字典表数据转换
        List<ShangpinChuruInoutView> list =(List<ShangpinChuruInoutView>)page.getList();
        for(ShangpinChuruInoutView c:list)
            dictionaryService.dictionaryConvert(c, request); //修改对应字典表字段

        return R.ok().put("data", page);
    }

    /**
    * 前端详情
    */
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Integer id, HttpServletRequest request){
        logger.debug("detail方法:,,Controller:{},,id:{}",this.getClass().getName(),id);
        ShangpinChuruInoutEntity shangpinChuruInout = shangpinChuruInoutService.selectById(id);
            if(shangpinChuruInout !=null){


                //entity转view
                ShangpinChuruInoutView view = new ShangpinChuruInoutView();
                BeanUtils.copyProperties( shangpinChuruInout , view );//把实体数据重构到view中

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
    public R add(@RequestBody ShangpinChuruInoutEntity shangpinChuruInout, HttpServletRequest request){
        logger.debug("add方法:,,Controller:{},,shangpinChuruInout:{}",this.getClass().getName(),shangpinChuruInout.toString());
        Wrapper<ShangpinChuruInoutEntity> queryWrapper = new EntityWrapper<ShangpinChuruInoutEntity>()
            .eq("shangpin_churu_inout_uuid_number", shangpinChuruInout.getShangpinChuruInoutUuidNumber())
            .eq("shangpin_churu_inout_name", shangpinChuruInout.getShangpinChuruInoutName())
            .eq("shangpin_churu_inout_types", shangpinChuruInout.getShangpinChuruInoutTypes())
//            .notIn("shangpin_churu_inout_types", new Integer[]{102})
            ;
        logger.info("sql语句:"+queryWrapper.getSqlSegment());
        ShangpinChuruInoutEntity shangpinChuruInoutEntity = shangpinChuruInoutService.selectOne(queryWrapper);
        if(shangpinChuruInoutEntity==null){
            shangpinChuruInout.setInsertTime(new Date());
            shangpinChuruInout.setCreateTime(new Date());
        shangpinChuruInoutService.insert(shangpinChuruInout);

            return R.ok();
        }else {
            return R.error(511,"表中有相同数据");
        }
    }

}

