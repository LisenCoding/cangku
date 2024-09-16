
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
 * 货物
 * 后端接口
 * @author
 * @email
*/
@RestController
@Controller
@RequestMapping("/huowu")
public class HuowuController {
    private static final Logger logger = LoggerFactory.getLogger(HuowuController.class);

    private static final String TABLE_NAME = "huowu";

    @Autowired
    private HuowuService huowuService;


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
    public R page(@RequestParam Map<String, Object> params, HttpServletRequest request){
        logger.debug("page方法:,,Controller:{},,params:{}",this.getClass().getName(),JSONObject.toJSONString(params));
        String role = String.valueOf(request.getSession().getAttribute("role"));
        if(false)
            return R.error(511,"永不会进入");
        else if("用户".equals(role))
            params.put("yonghuId",request.getSession().getAttribute("userId"));
        else if("员工".equals(role))
            params.put("yuangongId",request.getSession().getAttribute("userId"));
        params.put("huowuDeleteStart",1);params.put("huowuDeleteEnd",1);
        CommonUtil.checkMap(params);
        PageUtils page = huowuService.queryPage(params);

        //字典表数据转换
        List<HuowuView> list =(List<HuowuView>)page.getList();
        for(HuowuView c:list){
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
        HuowuEntity huowu = huowuService.selectById(id);
        if(huowu !=null){
            //entity转view
            HuowuView view = new HuowuView();
            BeanUtils.copyProperties( huowu , view );//把实体数据重构到view中
            //级联表 商品订单
            //级联表
            ShangpinOrderEntity shangpinOrder = shangpinOrderService.selectById(huowu.getShangpinOrderId());
            if(shangpinOrder != null){
            BeanUtils.copyProperties( shangpinOrder , view ,new String[]{ "id", "createTime", "insertTime", "updateTime", "username", "password", "newMoney"});//把级联的数据添加到view中,并排除id和创建时间字段,当前表的级联注册表
            view.setShangpinOrderId(shangpinOrder.getId());
            }
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
    public R save(@RequestBody HuowuEntity huowu, HttpServletRequest request){
        logger.debug("save方法:,,Controller:{},,huowu:{}",this.getClass().getName(),huowu.toString());

        String role = String.valueOf(request.getSession().getAttribute("role"));
        if(false)
            return R.error(511,"永远不会进入");

        Wrapper<HuowuEntity> queryWrapper = new EntityWrapper<HuowuEntity>()
            .eq("shangpin_order_id", huowu.getShangpinOrderId())
            .eq("huowu_name", huowu.getHuowuName())
            .eq("huowu_types", huowu.getHuowuTypes())
            .eq("huowu_delete", 1)
            ;

        logger.info("sql语句:"+queryWrapper.getSqlSegment());
        HuowuEntity huowuEntity = huowuService.selectOne(queryWrapper);
        if(huowuEntity==null){
            huowu.setHuowuDelete(1);
            huowu.setInsertTime(new Date());
            huowu.setCreateTime(new Date());
            huowuService.insert(huowu);
            return R.ok();
        }else {
            return R.error(511,"表中有相同数据");
        }
    }

    /**
    * 后端修改
    */
    @RequestMapping("/update")
    public R update(@RequestBody HuowuEntity huowu, HttpServletRequest request) throws NoSuchFieldException, ClassNotFoundException, IllegalAccessException, InstantiationException {
        logger.debug("update方法:,,Controller:{},,huowu:{}",this.getClass().getName(),huowu.toString());
        HuowuEntity oldHuowuEntity = huowuService.selectById(huowu.getId());//查询原先数据

        String role = String.valueOf(request.getSession().getAttribute("role"));
//        if(false)
//            return R.error(511,"永远不会进入");
        if("".equals(huowu.getHuowuContent()) || "null".equals(huowu.getHuowuContent())){
                huowu.setHuowuContent(null);
        }

            huowuService.updateById(huowu);//根据id更新
            return R.ok();
    }



    /**
    * 删除
    */
    @RequestMapping("/delete")
    public R delete(@RequestBody Integer[] ids, HttpServletRequest request){
        logger.debug("delete:,,Controller:{},,ids:{}",this.getClass().getName(),ids.toString());
        List<HuowuEntity> oldHuowuList =huowuService.selectBatchIds(Arrays.asList(ids));//要删除的数据
        ArrayList<HuowuEntity> list = new ArrayList<>();
        for(Integer id:ids){
            HuowuEntity huowuEntity = new HuowuEntity();
            huowuEntity.setId(id);
            huowuEntity.setHuowuDelete(2);
            list.add(huowuEntity);
        }
        if(list != null && list.size() >0){
            huowuService.updateBatchById(list);
        }

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
            List<HuowuEntity> huowuList = new ArrayList<>();//上传的东西
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
                            HuowuEntity huowuEntity = new HuowuEntity();
//                            huowuEntity.setShangpinOrderId(Integer.valueOf(data.get(0)));   //商家 要改的
//                            huowuEntity.setHuowuName(data.get(0));                    //货物名称 要改的
//                            huowuEntity.setHuowuUuidNumber(data.get(0));                    //货物编号 要改的
//                            huowuEntity.setHuowuTypes(Integer.valueOf(data.get(0)));   //货物类型 要改的
//                            huowuEntity.setHuowuContent("");//详情和图片
//                            huowuEntity.setHuowuDelete(1);//逻辑删除字段
//                            huowuEntity.setInsertTime(date);//时间
//                            huowuEntity.setCreateTime(date);//时间
                            huowuList.add(huowuEntity);


                            //把要查询是否重复的字段放入map中
                                //货物编号
                                if(seachFields.containsKey("huowuUuidNumber")){
                                    List<String> huowuUuidNumber = seachFields.get("huowuUuidNumber");
                                    huowuUuidNumber.add(data.get(0));//要改的
                                }else{
                                    List<String> huowuUuidNumber = new ArrayList<>();
                                    huowuUuidNumber.add(data.get(0));//要改的
                                    seachFields.put("huowuUuidNumber",huowuUuidNumber);
                                }
                        }

                        //查询是否重复
                         //货物编号
                        List<HuowuEntity> huowuEntities_huowuUuidNumber = huowuService.selectList(new EntityWrapper<HuowuEntity>().in("huowu_uuid_number", seachFields.get("huowuUuidNumber")).eq("huowu_delete", 1));
                        if(huowuEntities_huowuUuidNumber.size() >0 ){
                            ArrayList<String> repeatFields = new ArrayList<>();
                            for(HuowuEntity s:huowuEntities_huowuUuidNumber){
                                repeatFields.add(s.getHuowuUuidNumber());
                            }
                            return R.error(511,"数据库的该表中的 [货物编号] 字段已经存在 存在数据为:"+repeatFields.toString());
                        }
                        huowuService.insertBatch(huowuList);
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
        PageUtils page = huowuService.queryPage(params);

        //字典表数据转换
        List<HuowuView> list =(List<HuowuView>)page.getList();
        for(HuowuView c:list)
            dictionaryService.dictionaryConvert(c, request); //修改对应字典表字段

        return R.ok().put("data", page);
    }

    /**
    * 前端详情
    */
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Integer id, HttpServletRequest request){
        logger.debug("detail方法:,,Controller:{},,id:{}",this.getClass().getName(),id);
        HuowuEntity huowu = huowuService.selectById(id);
            if(huowu !=null){


                //entity转view
                HuowuView view = new HuowuView();
                BeanUtils.copyProperties( huowu , view );//把实体数据重构到view中

                //级联表
                    ShangpinOrderEntity shangpinOrder = shangpinOrderService.selectById(huowu.getShangpinOrderId());
                if(shangpinOrder != null){
                    BeanUtils.copyProperties( shangpinOrder , view ,new String[]{ "id", "createTime", "insertTime", "updateTime", "username", "password", "newMoney"});//把级联的数据添加到view中,并排除id和创建时间字段
                    view.setShangpinOrderId(shangpinOrder.getId());
                }
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
    public R add(@RequestBody HuowuEntity huowu, HttpServletRequest request){
        logger.debug("add方法:,,Controller:{},,huowu:{}",this.getClass().getName(),huowu.toString());
        Wrapper<HuowuEntity> queryWrapper = new EntityWrapper<HuowuEntity>()
            .eq("shangpin_order_id", huowu.getShangpinOrderId())
            .eq("huowu_name", huowu.getHuowuName())
            .eq("huowu_uuid_number", huowu.getHuowuUuidNumber())
            .eq("huowu_types", huowu.getHuowuTypes())
            .eq("huowu_delete", huowu.getHuowuDelete())
//            .notIn("huowu_types", new Integer[]{102})
            ;
        logger.info("sql语句:"+queryWrapper.getSqlSegment());
        HuowuEntity huowuEntity = huowuService.selectOne(queryWrapper);
        if(huowuEntity==null){
            huowu.setHuowuDelete(1);
            huowu.setInsertTime(new Date());
            huowu.setCreateTime(new Date());
        huowuService.insert(huowu);

            return R.ok();
        }else {
            return R.error(511,"表中有相同数据");
        }
    }

}

