
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
 * 用户
 * 后端接口
 * @author
 * @email
*/
@RestController
@Controller
@RequestMapping("/yonghu")
public class YonghuController {
    private static final Logger logger = LoggerFactory.getLogger(YonghuController.class);

    private static final String TABLE_NAME = "yonghu";

    @Autowired
    private YonghuService yonghuService;


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
    private SijiService sijiService;//司机
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
        PageUtils page = yonghuService.queryPage(params);

        //字典表数据转换
        List<YonghuView> list =(List<YonghuView>)page.getList();
        for(YonghuView c:list){
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
        YonghuEntity yonghu = yonghuService.selectById(id);
        if(yonghu !=null){
            //entity转view
            YonghuView view = new YonghuView();
            BeanUtils.copyProperties( yonghu , view );//把实体数据重构到view中
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
    public R save(@RequestBody YonghuEntity yonghu, HttpServletRequest request){
        logger.debug("save方法:,,Controller:{},,yonghu:{}",this.getClass().getName(),yonghu.toString());

        String role = String.valueOf(request.getSession().getAttribute("role"));
        if(false)
            return R.error(511,"永远不会进入");

        Wrapper<YonghuEntity> queryWrapper = new EntityWrapper<YonghuEntity>()
            .eq("username", yonghu.getUsername())
            .or()
            .eq("yonghu_phone", yonghu.getYonghuPhone())
            .or()
            .eq("yonghu_id_number", yonghu.getYonghuIdNumber())
            ;

        logger.info("sql语句:"+queryWrapper.getSqlSegment());
        YonghuEntity yonghuEntity = yonghuService.selectOne(queryWrapper);
        if(yonghuEntity==null){
            yonghu.setCreateTime(new Date());
            yonghu.setPassword("123456");
            yonghuService.insert(yonghu);
            return R.ok();
        }else {
            return R.error(511,"账户或者用户手机号或者用户身份证号已经被使用");
        }
    }

    /**
    * 后端修改
    */
    @RequestMapping("/update")
    public R update(@RequestBody YonghuEntity yonghu, HttpServletRequest request) throws NoSuchFieldException, ClassNotFoundException, IllegalAccessException, InstantiationException {
        logger.debug("update方法:,,Controller:{},,yonghu:{}",this.getClass().getName(),yonghu.toString());
        YonghuEntity oldYonghuEntity = yonghuService.selectById(yonghu.getId());//查询原先数据

        String role = String.valueOf(request.getSession().getAttribute("role"));
//        if(false)
//            return R.error(511,"永远不会进入");
        if("".equals(yonghu.getYonghuPhoto()) || "null".equals(yonghu.getYonghuPhoto())){
                yonghu.setYonghuPhoto(null);
        }

            yonghuService.updateById(yonghu);//根据id更新
            return R.ok();
    }



    /**
    * 删除
    */
    @RequestMapping("/delete")
    public R delete(@RequestBody Integer[] ids, HttpServletRequest request){
        logger.debug("delete:,,Controller:{},,ids:{}",this.getClass().getName(),ids.toString());
        List<YonghuEntity> oldYonghuList =yonghuService.selectBatchIds(Arrays.asList(ids));//要删除的数据
        yonghuService.deleteBatchIds(Arrays.asList(ids));

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
            List<YonghuEntity> yonghuList = new ArrayList<>();//上传的东西
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
                            YonghuEntity yonghuEntity = new YonghuEntity();
//                            yonghuEntity.setUsername(data.get(0));                    //账户 要改的
//                            yonghuEntity.setPassword("123456");//密码
//                            yonghuEntity.setYonghuUuidNumber(data.get(0));                    //用户编号 要改的
//                            yonghuEntity.setYonghuName(data.get(0));                    //用户姓名 要改的
//                            yonghuEntity.setYonghuPhone(data.get(0));                    //用户手机号 要改的
//                            yonghuEntity.setYonghuIdNumber(data.get(0));                    //用户身份证号 要改的
//                            yonghuEntity.setYonghuPhoto("");//详情和图片
//                            yonghuEntity.setSexTypes(Integer.valueOf(data.get(0)));   //性别 要改的
//                            yonghuEntity.setNewMoney(data.get(0));                    //余额 要改的
//                            yonghuEntity.setYonghuEmail(data.get(0));                    //用户邮箱 要改的
//                            yonghuEntity.setCreateTime(date);//时间
                            yonghuList.add(yonghuEntity);


                            //把要查询是否重复的字段放入map中
                                //账户
                                if(seachFields.containsKey("username")){
                                    List<String> username = seachFields.get("username");
                                    username.add(data.get(0));//要改的
                                }else{
                                    List<String> username = new ArrayList<>();
                                    username.add(data.get(0));//要改的
                                    seachFields.put("username",username);
                                }
                                //用户编号
                                if(seachFields.containsKey("yonghuUuidNumber")){
                                    List<String> yonghuUuidNumber = seachFields.get("yonghuUuidNumber");
                                    yonghuUuidNumber.add(data.get(0));//要改的
                                }else{
                                    List<String> yonghuUuidNumber = new ArrayList<>();
                                    yonghuUuidNumber.add(data.get(0));//要改的
                                    seachFields.put("yonghuUuidNumber",yonghuUuidNumber);
                                }
                                //用户手机号
                                if(seachFields.containsKey("yonghuPhone")){
                                    List<String> yonghuPhone = seachFields.get("yonghuPhone");
                                    yonghuPhone.add(data.get(0));//要改的
                                }else{
                                    List<String> yonghuPhone = new ArrayList<>();
                                    yonghuPhone.add(data.get(0));//要改的
                                    seachFields.put("yonghuPhone",yonghuPhone);
                                }
                                //用户身份证号
                                if(seachFields.containsKey("yonghuIdNumber")){
                                    List<String> yonghuIdNumber = seachFields.get("yonghuIdNumber");
                                    yonghuIdNumber.add(data.get(0));//要改的
                                }else{
                                    List<String> yonghuIdNumber = new ArrayList<>();
                                    yonghuIdNumber.add(data.get(0));//要改的
                                    seachFields.put("yonghuIdNumber",yonghuIdNumber);
                                }
                        }

                        //查询是否重复
                         //账户
                        List<YonghuEntity> yonghuEntities_username = yonghuService.selectList(new EntityWrapper<YonghuEntity>().in("username", seachFields.get("username")));
                        if(yonghuEntities_username.size() >0 ){
                            ArrayList<String> repeatFields = new ArrayList<>();
                            for(YonghuEntity s:yonghuEntities_username){
                                repeatFields.add(s.getUsername());
                            }
                            return R.error(511,"数据库的该表中的 [账户] 字段已经存在 存在数据为:"+repeatFields.toString());
                        }
                         //用户编号
                        List<YonghuEntity> yonghuEntities_yonghuUuidNumber = yonghuService.selectList(new EntityWrapper<YonghuEntity>().in("yonghu_uuid_number", seachFields.get("yonghuUuidNumber")));
                        if(yonghuEntities_yonghuUuidNumber.size() >0 ){
                            ArrayList<String> repeatFields = new ArrayList<>();
                            for(YonghuEntity s:yonghuEntities_yonghuUuidNumber){
                                repeatFields.add(s.getYonghuUuidNumber());
                            }
                            return R.error(511,"数据库的该表中的 [用户编号] 字段已经存在 存在数据为:"+repeatFields.toString());
                        }
                         //用户手机号
                        List<YonghuEntity> yonghuEntities_yonghuPhone = yonghuService.selectList(new EntityWrapper<YonghuEntity>().in("yonghu_phone", seachFields.get("yonghuPhone")));
                        if(yonghuEntities_yonghuPhone.size() >0 ){
                            ArrayList<String> repeatFields = new ArrayList<>();
                            for(YonghuEntity s:yonghuEntities_yonghuPhone){
                                repeatFields.add(s.getYonghuPhone());
                            }
                            return R.error(511,"数据库的该表中的 [用户手机号] 字段已经存在 存在数据为:"+repeatFields.toString());
                        }
                         //用户身份证号
                        List<YonghuEntity> yonghuEntities_yonghuIdNumber = yonghuService.selectList(new EntityWrapper<YonghuEntity>().in("yonghu_id_number", seachFields.get("yonghuIdNumber")));
                        if(yonghuEntities_yonghuIdNumber.size() >0 ){
                            ArrayList<String> repeatFields = new ArrayList<>();
                            for(YonghuEntity s:yonghuEntities_yonghuIdNumber){
                                repeatFields.add(s.getYonghuIdNumber());
                            }
                            return R.error(511,"数据库的该表中的 [用户身份证号] 字段已经存在 存在数据为:"+repeatFields.toString());
                        }
                        yonghuService.insertBatch(yonghuList);
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
    * 登录
    */
    @IgnoreAuth
    @RequestMapping(value = "/login")
    public R login(String username, String password, String captcha, HttpServletRequest request) {
        YonghuEntity yonghu = yonghuService.selectOne(new EntityWrapper<YonghuEntity>().eq("username", username));
        if(yonghu==null || !yonghu.getPassword().equals(password))
            return R.error("账号或密码不正确");
        String token = tokenService.generateToken(yonghu.getId(),username, "yonghu", "用户");
        R r = R.ok();
        r.put("token", token);
        r.put("role","用户");
        r.put("username",yonghu.getYonghuName());
        r.put("tableName","yonghu");
        r.put("userId",yonghu.getId());
        return r;
    }

    /**
    * 注册
    */
    @IgnoreAuth
    @PostMapping(value = "/register")
    public R register(@RequestBody YonghuEntity yonghu, HttpServletRequest request) {
//    	ValidatorUtils.validateEntity(user);
        Wrapper<YonghuEntity> queryWrapper = new EntityWrapper<YonghuEntity>()
            .eq("username", yonghu.getUsername())
            .or()
            .eq("yonghu_phone", yonghu.getYonghuPhone())
            .or()
            .eq("yonghu_id_number", yonghu.getYonghuIdNumber())
            ;
        YonghuEntity yonghuEntity = yonghuService.selectOne(queryWrapper);
        if(yonghuEntity != null)
            return R.error("账户或者用户手机号或者用户身份证号已经被使用");
        yonghu.setYonghuUuidNumber(String.valueOf(new Date().getTime()));
        yonghu.setNewMoney(0.0);
        yonghu.setCreateTime(new Date());
        yonghuService.insert(yonghu);

        return R.ok();
    }

    /**
     * 重置密码
     */
    @GetMapping(value = "/resetPassword")
    public R resetPassword(Integer  id, HttpServletRequest request) {
        YonghuEntity yonghu = yonghuService.selectById(id);
        yonghu.setPassword("123456");
        yonghuService.updateById(yonghu);
        return R.ok();
    }

	/**
	 * 修改密码
	 */
	@GetMapping(value = "/updatePassword")
	public R updatePassword(String  oldPassword, String  newPassword, HttpServletRequest request) {
        YonghuEntity yonghu = yonghuService.selectById((Integer)request.getSession().getAttribute("userId"));
		if(newPassword == null){
			return R.error("新密码不能为空") ;
		}
		if(!oldPassword.equals(yonghu.getPassword())){
			return R.error("原密码输入错误");
		}
		if(newPassword.equals(yonghu.getPassword())){
			return R.error("新密码不能和原密码一致") ;
		}
        yonghu.setPassword(newPassword);
		yonghuService.updateById(yonghu);
		return R.ok();
	}



    /**
     * 忘记密码
     */
    @IgnoreAuth
    @RequestMapping(value = "/resetPass")
    public R resetPass(String username, HttpServletRequest request) {
        YonghuEntity yonghu = yonghuService.selectOne(new EntityWrapper<YonghuEntity>().eq("username", username));
        if(yonghu!=null){
            yonghu.setPassword("123456");
            yonghuService.updateById(yonghu);
            return R.ok();
        }else{
           return R.error("账号不存在");
        }
    }


    /**
    * 获取用户的session用户信息
    */
    @RequestMapping("/session")
    public R getCurrYonghu(HttpServletRequest request){
        Integer id = (Integer)request.getSession().getAttribute("userId");
        YonghuEntity yonghu = yonghuService.selectById(id);
        if(yonghu !=null){
            //entity转view
            YonghuView view = new YonghuView();
            BeanUtils.copyProperties( yonghu , view );//把实体数据重构到view中

            //修改对应字典表字段
            dictionaryService.dictionaryConvert(view, request);
            return R.ok().put("data", view);
        }else {
            return R.error(511,"查不到数据");
        }
    }


    /**
    * 退出
    */
    @GetMapping(value = "logout")
    public R logout(HttpServletRequest request) {
        request.getSession().invalidate();
        return R.ok("退出成功");
    }



    /**
    * 前端列表
    */
    @IgnoreAuth
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params, HttpServletRequest request){
        logger.debug("list方法:,,Controller:{},,params:{}",this.getClass().getName(),JSONObject.toJSONString(params));

        CommonUtil.checkMap(params);
        PageUtils page = yonghuService.queryPage(params);

        //字典表数据转换
        List<YonghuView> list =(List<YonghuView>)page.getList();
        for(YonghuView c:list)
            dictionaryService.dictionaryConvert(c, request); //修改对应字典表字段

        return R.ok().put("data", page);
    }

    /**
    * 前端详情
    */
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Integer id, HttpServletRequest request){
        logger.debug("detail方法:,,Controller:{},,id:{}",this.getClass().getName(),id);
        YonghuEntity yonghu = yonghuService.selectById(id);
            if(yonghu !=null){


                //entity转view
                YonghuView view = new YonghuView();
                BeanUtils.copyProperties( yonghu , view );//把实体数据重构到view中

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
    public R add(@RequestBody YonghuEntity yonghu, HttpServletRequest request){
        logger.debug("add方法:,,Controller:{},,yonghu:{}",this.getClass().getName(),yonghu.toString());
        Wrapper<YonghuEntity> queryWrapper = new EntityWrapper<YonghuEntity>()
            .eq("username", yonghu.getUsername())
            .or()
            .eq("yonghu_phone", yonghu.getYonghuPhone())
            .or()
            .eq("yonghu_id_number", yonghu.getYonghuIdNumber())
//            .notIn("yonghu_types", new Integer[]{102})
            ;
        logger.info("sql语句:"+queryWrapper.getSqlSegment());
        YonghuEntity yonghuEntity = yonghuService.selectOne(queryWrapper);
        if(yonghuEntity==null){
            yonghu.setCreateTime(new Date());
            yonghu.setPassword("123456");
        yonghuService.insert(yonghu);

            return R.ok();
        }else {
            return R.error(511,"账户或者用户手机号或者用户身份证号已经被使用");
        }
    }

}

