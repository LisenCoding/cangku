
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
 * 商品
 * 后端接口
 * @author
 * @email
*/
@RestController
@Controller
@RequestMapping("/shangpin")
public class ShangpinController {
    private static final Logger logger = LoggerFactory.getLogger(ShangpinController.class);

    private static final String TABLE_NAME = "shangpin";

    @Autowired
    private ShangpinService shangpinService;


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
        params.put("shangpinDeleteStart",1);params.put("shangpinDeleteEnd",1);
        CommonUtil.checkMap(params);
        PageUtils page = shangpinService.queryPage(params);

        //字典表数据转换
        List<ShangpinView> list =(List<ShangpinView>)page.getList();
        for(ShangpinView c:list){
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
        ShangpinEntity shangpin = shangpinService.selectById(id);
        if(shangpin !=null){
            //entity转view
            ShangpinView view = new ShangpinView();
            BeanUtils.copyProperties( shangpin , view );//把实体数据重构到view中
            //级联表 仓库
            //级联表
            CangkuEntity cangku = cangkuService.selectById(shangpin.getCangkuId());
            if(cangku != null){
            BeanUtils.copyProperties( cangku , view ,new String[]{ "id", "createTime", "insertTime", "updateTime", "username", "password", "newMoney"});//把级联的数据添加到view中,并排除id和创建时间字段,当前表的级联注册表
            view.setCangkuId(cangku.getId());
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
    public R save(@RequestBody ShangpinEntity shangpin, HttpServletRequest request){
        logger.debug("save方法:,,Controller:{},,shangpin:{}",this.getClass().getName(),shangpin.toString());

        String role = String.valueOf(request.getSession().getAttribute("role"));
        if(false)
            return R.error(511,"永远不会进入");

        Wrapper<ShangpinEntity> queryWrapper = new EntityWrapper<ShangpinEntity>()
            .eq("cangku_id", shangpin.getCangkuId())
            .eq("shangpin_name", shangpin.getShangpinName())
            .eq("shangpin_types", shangpin.getShangpinTypes())
            .eq("shangpin_kucun_number", shangpin.getShangpinKucunNumber())
            .eq("shangxia_types", shangpin.getShangxiaTypes())
            .eq("shangpin_delete", 1)
            ;

        logger.info("sql语句:"+queryWrapper.getSqlSegment());
        ShangpinEntity shangpinEntity = shangpinService.selectOne(queryWrapper);
        if(shangpinEntity==null){
            shangpin.setShangxiaTypes(1);
            shangpin.setShangpinDelete(1);
            shangpin.setInsertTime(new Date());
            shangpin.setCreateTime(new Date());
            shangpinService.insert(shangpin);
            return R.ok();
        }else {
            return R.error(511,"表中有相同数据");
        }
    }

    /**
    * 后端修改
    */
    @RequestMapping("/update")
    public R update(@RequestBody ShangpinEntity shangpin, HttpServletRequest request) throws NoSuchFieldException, ClassNotFoundException, IllegalAccessException, InstantiationException {
        logger.debug("update方法:,,Controller:{},,shangpin:{}",this.getClass().getName(),shangpin.toString());
        ShangpinEntity oldShangpinEntity = shangpinService.selectById(shangpin.getId());//查询原先数据

        String role = String.valueOf(request.getSession().getAttribute("role"));
//        if(false)
//            return R.error(511,"永远不会进入");
        if("".equals(shangpin.getShangpinPhoto()) || "null".equals(shangpin.getShangpinPhoto())){
                shangpin.setShangpinPhoto(null);
        }
        if("".equals(shangpin.getShangpinContent()) || "null".equals(shangpin.getShangpinContent())){
                shangpin.setShangpinContent(null);
        }

            shangpinService.updateById(shangpin);//根据id更新
            return R.ok();
    }



    /**
    * 删除
    */
    @RequestMapping("/delete")
    public R delete(@RequestBody Integer[] ids, HttpServletRequest request){
        logger.debug("delete:,,Controller:{},,ids:{}",this.getClass().getName(),ids.toString());
        List<ShangpinEntity> oldShangpinList =shangpinService.selectBatchIds(Arrays.asList(ids));//要删除的数据
        ArrayList<ShangpinEntity> list = new ArrayList<>();
        for(Integer id:ids){
            ShangpinEntity shangpinEntity = new ShangpinEntity();
            shangpinEntity.setId(id);
            shangpinEntity.setShangpinDelete(2);
            list.add(shangpinEntity);
        }
        if(list != null && list.size() >0){
            shangpinService.updateBatchById(list);
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
            List<ShangpinEntity> shangpinList = new ArrayList<>();//上传的东西
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
                            ShangpinEntity shangpinEntity = new ShangpinEntity();
//                            shangpinEntity.setCangkuId(Integer.valueOf(data.get(0)));   //仓库 要改的
//                            shangpinEntity.setShangpinName(data.get(0));                    //商品名称 要改的
//                            shangpinEntity.setShangpinUuidNumber(data.get(0));                    //商品编号 要改的
//                            shangpinEntity.setShangpinPhoto("");//详情和图片
//                            shangpinEntity.setShangpinTypes(Integer.valueOf(data.get(0)));   //商品类型 要改的
//                            shangpinEntity.setShangpinKucunNumber(Integer.valueOf(data.get(0)));   //商品库存 要改的
//                            shangpinEntity.setShangpinNewMoney(data.get(0));                    //现价/积分 要改的
//                            shangpinEntity.setShangpinContent("");//详情和图片
//                            shangpinEntity.setShangxiaTypes(Integer.valueOf(data.get(0)));   //是否上架 要改的
//                            shangpinEntity.setShangpinDelete(1);//逻辑删除字段
//                            shangpinEntity.setInsertTime(date);//时间
//                            shangpinEntity.setCreateTime(date);//时间
                            shangpinList.add(shangpinEntity);


                            //把要查询是否重复的字段放入map中
                                //商品编号
                                if(seachFields.containsKey("shangpinUuidNumber")){
                                    List<String> shangpinUuidNumber = seachFields.get("shangpinUuidNumber");
                                    shangpinUuidNumber.add(data.get(0));//要改的
                                }else{
                                    List<String> shangpinUuidNumber = new ArrayList<>();
                                    shangpinUuidNumber.add(data.get(0));//要改的
                                    seachFields.put("shangpinUuidNumber",shangpinUuidNumber);
                                }
                        }

                        //查询是否重复
                         //商品编号
                        List<ShangpinEntity> shangpinEntities_shangpinUuidNumber = shangpinService.selectList(new EntityWrapper<ShangpinEntity>().in("shangpin_uuid_number", seachFields.get("shangpinUuidNumber")).eq("shangpin_delete", 1));
                        if(shangpinEntities_shangpinUuidNumber.size() >0 ){
                            ArrayList<String> repeatFields = new ArrayList<>();
                            for(ShangpinEntity s:shangpinEntities_shangpinUuidNumber){
                                repeatFields.add(s.getShangpinUuidNumber());
                            }
                            return R.error(511,"数据库的该表中的 [商品编号] 字段已经存在 存在数据为:"+repeatFields.toString());
                        }
                        shangpinService.insertBatch(shangpinList);
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
    * 个性推荐
    */
    @IgnoreAuth
    @RequestMapping("/gexingtuijian")
    public R gexingtuijian(@RequestParam Map<String, Object> params, HttpServletRequest request){
        logger.debug("gexingtuijian方法:,,Controller:{},,params:{}",this.getClass().getName(),JSONObject.toJSONString(params));
        CommonUtil.checkMap(params);
        List<ShangpinView> returnShangpinViewList = new ArrayList<>();

        //查询订单
        Map<String, Object> params1 = new HashMap<>(params);params1.put("sort","id");params1.put("yonghuId",request.getSession().getAttribute("userId"));
        params1.put("shangxiaTypes",1);
        params1.put("shangpinYesnoTypes",2);
        PageUtils pageUtils = shangpinOrderService.queryPage(params1);
        List<ShangpinOrderView> orderViewsList =(List<ShangpinOrderView>)pageUtils.getList();
        Map<Integer,Integer> typeMap=new HashMap<>();//购买的类型list
        for(ShangpinOrderView orderView:orderViewsList){
            Integer shangpinTypes = orderView.getShangpinTypes();
            if(typeMap.containsKey(shangpinTypes)){
                typeMap.put(shangpinTypes,typeMap.get(shangpinTypes)+1);
            }else{
                typeMap.put(shangpinTypes,1);
            }
        }
        List<Integer> typeList = new ArrayList<>();//排序后的有序的类型 按最多到最少
        typeMap.entrySet().stream().sorted((o1, o2) -> o2.getValue() - o1.getValue()).forEach(e -> typeList.add(e.getKey()));//排序
        Integer limit = Integer.valueOf(String.valueOf(params.get("limit")));
        for(Integer type:typeList){
            Map<String, Object> params2 = new HashMap<>(params);params2.put("shangpinTypes",type);
            params2.put("shangxiaTypes",1);
            params2.put("shangpinYesnoTypes",2);
            PageUtils pageUtils1 = shangpinService.queryPage(params2);
            List<ShangpinView> shangpinViewList =(List<ShangpinView>)pageUtils1.getList();
            returnShangpinViewList.addAll(shangpinViewList);
            if(returnShangpinViewList.size()>= limit) break;//返回的推荐数量大于要的数量 跳出循环
        }
        params.put("shangxiaTypes",1);
        params.put("shangpinYesnoTypes",2);
        //正常查询出来商品,用于补全推荐缺少的数据
        PageUtils page = shangpinService.queryPage(params);
        if(returnShangpinViewList.size()<limit){//返回数量还是小于要求数量
            int toAddNum = limit - returnShangpinViewList.size();//要添加的数量
            List<ShangpinView> shangpinViewList =(List<ShangpinView>)page.getList();
            for(ShangpinView shangpinView:shangpinViewList){
                Boolean addFlag = true;
                for(ShangpinView returnShangpinView:returnShangpinViewList){
                    if(returnShangpinView.getId().intValue() ==shangpinView.getId().intValue()) addFlag=false;//返回的数据中已存在此商品
                }
                if(addFlag){
                    toAddNum=toAddNum-1;
                    returnShangpinViewList.add(shangpinView);
                    if(toAddNum==0) break;//够数量了
                }
            }
        }else {
            returnShangpinViewList = returnShangpinViewList.subList(0, limit);
        }

        for(ShangpinView c:returnShangpinViewList)
            dictionaryService.dictionaryConvert(c, request);
        page.setList(returnShangpinViewList);
        return R.ok().put("data", page);
    }

    /**
    * 前端列表
    */
    @IgnoreAuth
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params, HttpServletRequest request){
        logger.debug("list方法:,,Controller:{},,params:{}",this.getClass().getName(),JSONObject.toJSONString(params));

        CommonUtil.checkMap(params);
        PageUtils page = shangpinService.queryPage(params);

        //字典表数据转换
        List<ShangpinView> list =(List<ShangpinView>)page.getList();
        for(ShangpinView c:list)
            dictionaryService.dictionaryConvert(c, request); //修改对应字典表字段

        return R.ok().put("data", page);
    }

    /**
    * 前端详情
    */
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Integer id, HttpServletRequest request){
        logger.debug("detail方法:,,Controller:{},,id:{}",this.getClass().getName(),id);
        ShangpinEntity shangpin = shangpinService.selectById(id);
            if(shangpin !=null){


                //entity转view
                ShangpinView view = new ShangpinView();
                BeanUtils.copyProperties( shangpin , view );//把实体数据重构到view中

                //级联表
                    CangkuEntity cangku = cangkuService.selectById(shangpin.getCangkuId());
                if(cangku != null){
                    BeanUtils.copyProperties( cangku , view ,new String[]{ "id", "createTime", "insertTime", "updateTime", "username", "password", "newMoney"});//把级联的数据添加到view中,并排除id和创建时间字段
                    view.setCangkuId(cangku.getId());
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
    public R add(@RequestBody ShangpinEntity shangpin, HttpServletRequest request){
        logger.debug("add方法:,,Controller:{},,shangpin:{}",this.getClass().getName(),shangpin.toString());
        Wrapper<ShangpinEntity> queryWrapper = new EntityWrapper<ShangpinEntity>()
            .eq("cangku_id", shangpin.getCangkuId())
            .eq("shangpin_name", shangpin.getShangpinName())
            .eq("shangpin_uuid_number", shangpin.getShangpinUuidNumber())
            .eq("shangpin_types", shangpin.getShangpinTypes())
            .eq("shangpin_kucun_number", shangpin.getShangpinKucunNumber())
            .eq("shangxia_types", shangpin.getShangxiaTypes())
            .eq("shangpin_delete", shangpin.getShangpinDelete())
//            .notIn("shangpin_types", new Integer[]{102})
            ;
        logger.info("sql语句:"+queryWrapper.getSqlSegment());
        ShangpinEntity shangpinEntity = shangpinService.selectOne(queryWrapper);
        if(shangpinEntity==null){
            shangpin.setShangpinDelete(1);
            shangpin.setInsertTime(new Date());
            shangpin.setCreateTime(new Date());
        shangpinService.insert(shangpin);

            return R.ok();
        }else {
            return R.error(511,"表中有相同数据");
        }
    }

}

