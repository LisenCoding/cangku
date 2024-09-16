
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
 * 仓库
 * 后端接口
 * @author
 * @email
*/
@RestController
@Controller
@RequestMapping("/cangku")
public class CangkuController {
    private static final Logger logger = LoggerFactory.getLogger(CangkuController.class);

    private static final String TABLE_NAME = "cangku";

    @Autowired
    private CangkuService cangkuService;


    @Autowired
    private TokenService tokenService;

    @Autowired
    private AddressService addressService;//收货地址
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
        params.put("cangkuDeleteStart",1);params.put("cangkuDeleteEnd",1);
        CommonUtil.checkMap(params);
        PageUtils page = cangkuService.queryPage(params);

        //字典表数据转换
        List<CangkuView> list =(List<CangkuView>)page.getList();
        for(CangkuView c:list){
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
        CangkuEntity cangku = cangkuService.selectById(id);
        if(cangku !=null){
            //entity转view
            CangkuView view = new CangkuView();
            BeanUtils.copyProperties( cangku , view );//把实体数据重构到view中
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
    public R save(@RequestBody CangkuEntity cangku, HttpServletRequest request){
        logger.debug("save方法:,,Controller:{},,cangku:{}",this.getClass().getName(),cangku.toString());

        String role = String.valueOf(request.getSession().getAttribute("role"));
        if(false)
            return R.error(511,"永远不会进入");

        Wrapper<CangkuEntity> queryWrapper = new EntityWrapper<CangkuEntity>()
            .eq("cangku_name", cangku.getCangkuName())
            .eq("cangku_address", cangku.getCangkuAddress())
            .eq("cangku_types", cangku.getCangkuTypes())
            .eq("cangku_delete", 1)
            ;

        logger.info("sql语句:"+queryWrapper.getSqlSegment());
        CangkuEntity cangkuEntity = cangkuService.selectOne(queryWrapper);
        if(cangkuEntity==null){
            cangku.setCangkuDelete(1);
            cangku.setInsertTime(new Date());
            cangku.setCreateTime(new Date());
            cangkuService.insert(cangku);
            return R.ok();
        }else {
            return R.error(511,"表中有相同数据");
        }
    }

    /**
    * 后端修改
    */
    @RequestMapping("/update")
    public R update(@RequestBody CangkuEntity cangku, HttpServletRequest request) throws NoSuchFieldException, ClassNotFoundException, IllegalAccessException, InstantiationException {
        logger.debug("update方法:,,Controller:{},,cangku:{}",this.getClass().getName(),cangku.toString());
        CangkuEntity oldCangkuEntity = cangkuService.selectById(cangku.getId());//查询原先数据

        String role = String.valueOf(request.getSession().getAttribute("role"));
//        if(false)
//            return R.error(511,"永远不会进入");
        if("".equals(cangku.getCangkuPhoto()) || "null".equals(cangku.getCangkuPhoto())){
                cangku.setCangkuPhoto(null);
        }
        if("".equals(cangku.getCangkuContent()) || "null".equals(cangku.getCangkuContent())){
                cangku.setCangkuContent(null);
        }

            cangkuService.updateById(cangku);//根据id更新
            return R.ok();
    }



    /**
    * 删除
    */
    @RequestMapping("/delete")
    public R delete(@RequestBody Integer[] ids, HttpServletRequest request){
        logger.debug("delete:,,Controller:{},,ids:{}",this.getClass().getName(),ids.toString());
        List<CangkuEntity> oldCangkuList =cangkuService.selectBatchIds(Arrays.asList(ids));//要删除的数据
        ArrayList<CangkuEntity> list = new ArrayList<>();
        for(Integer id:ids){
            CangkuEntity cangkuEntity = new CangkuEntity();
            cangkuEntity.setId(id);
            cangkuEntity.setCangkuDelete(2);
            list.add(cangkuEntity);
        }
        if(list != null && list.size() >0){
            cangkuService.updateBatchById(list);
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
            List<CangkuEntity> cangkuList = new ArrayList<>();//上传的东西
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
                            CangkuEntity cangkuEntity = new CangkuEntity();
//                            cangkuEntity.setCangkuName(data.get(0));                    //仓库名称 要改的
//                            cangkuEntity.setCangkuUuidNumber(data.get(0));                    //仓库编号 要改的
//                            cangkuEntity.setCangkuPhoto("");//详情和图片
//                            cangkuEntity.setCangkuAddress(data.get(0));                    //仓库地点 要改的
//                            cangkuEntity.setCangkuTypes(Integer.valueOf(data.get(0)));   //仓库类型 要改的
//                            cangkuEntity.setCangkuContent("");//详情和图片
//                            cangkuEntity.setCangkuDelete(1);//逻辑删除字段
//                            cangkuEntity.setInsertTime(date);//时间
//                            cangkuEntity.setCreateTime(date);//时间
                            cangkuList.add(cangkuEntity);


                            //把要查询是否重复的字段放入map中
                                //仓库编号
                                if(seachFields.containsKey("cangkuUuidNumber")){
                                    List<String> cangkuUuidNumber = seachFields.get("cangkuUuidNumber");
                                    cangkuUuidNumber.add(data.get(0));//要改的
                                }else{
                                    List<String> cangkuUuidNumber = new ArrayList<>();
                                    cangkuUuidNumber.add(data.get(0));//要改的
                                    seachFields.put("cangkuUuidNumber",cangkuUuidNumber);
                                }
                        }

                        //查询是否重复
                         //仓库编号
                        List<CangkuEntity> cangkuEntities_cangkuUuidNumber = cangkuService.selectList(new EntityWrapper<CangkuEntity>().in("cangku_uuid_number", seachFields.get("cangkuUuidNumber")).eq("cangku_delete", 1));
                        if(cangkuEntities_cangkuUuidNumber.size() >0 ){
                            ArrayList<String> repeatFields = new ArrayList<>();
                            for(CangkuEntity s:cangkuEntities_cangkuUuidNumber){
                                repeatFields.add(s.getCangkuUuidNumber());
                            }
                            return R.error(511,"数据库的该表中的 [仓库编号] 字段已经存在 存在数据为:"+repeatFields.toString());
                        }
                        cangkuService.insertBatch(cangkuList);
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
        PageUtils page = cangkuService.queryPage(params);

        //字典表数据转换
        List<CangkuView> list =(List<CangkuView>)page.getList();
        for(CangkuView c:list)
            dictionaryService.dictionaryConvert(c, request); //修改对应字典表字段

        return R.ok().put("data", page);
    }

    /**
    * 前端详情
    */
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Integer id, HttpServletRequest request){
        logger.debug("detail方法:,,Controller:{},,id:{}",this.getClass().getName(),id);
        CangkuEntity cangku = cangkuService.selectById(id);
            if(cangku !=null){


                //entity转view
                CangkuView view = new CangkuView();
                BeanUtils.copyProperties( cangku , view );//把实体数据重构到view中

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
    public R add(@RequestBody CangkuEntity cangku, HttpServletRequest request){
        logger.debug("add方法:,,Controller:{},,cangku:{}",this.getClass().getName(),cangku.toString());
        Wrapper<CangkuEntity> queryWrapper = new EntityWrapper<CangkuEntity>()
            .eq("cangku_name", cangku.getCangkuName())
            .eq("cangku_uuid_number", cangku.getCangkuUuidNumber())
            .eq("cangku_address", cangku.getCangkuAddress())
            .eq("cangku_types", cangku.getCangkuTypes())
            .eq("cangku_delete", cangku.getCangkuDelete())
//            .notIn("cangku_types", new Integer[]{102})
            ;
        logger.info("sql语句:"+queryWrapper.getSqlSegment());
        CangkuEntity cangkuEntity = cangkuService.selectOne(queryWrapper);
        if(cangkuEntity==null){
            cangku.setCangkuDelete(1);
            cangku.setInsertTime(new Date());
            cangku.setCreateTime(new Date());
        cangkuService.insert(cangku);

            return R.ok();
        }else {
            return R.error(511,"表中有相同数据");
        }
    }

}

