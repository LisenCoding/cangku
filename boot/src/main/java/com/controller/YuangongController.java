
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
 * 员工
 * 后端接口
 * @author
 * @email
*/
@RestController
@Controller
@RequestMapping("/yuangong")
public class YuangongController {
    private static final Logger logger = LoggerFactory.getLogger(YuangongController.class);

    private static final String TABLE_NAME = "yuangong";

    @Autowired
    private YuangongService yuangongService;


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
    private YonghuService yonghuService;//用户
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
        PageUtils page = yuangongService.queryPage(params);

        //字典表数据转换
        List<YuangongView> list =(List<YuangongView>)page.getList();
        for(YuangongView c:list){
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
        YuangongEntity yuangong = yuangongService.selectById(id);
        if(yuangong !=null){
            //entity转view
            YuangongView view = new YuangongView();
            BeanUtils.copyProperties( yuangong , view );//把实体数据重构到view中
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
    public R save(@RequestBody YuangongEntity yuangong, HttpServletRequest request){
        logger.debug("save方法:,,Controller:{},,yuangong:{}",this.getClass().getName(),yuangong.toString());

        String role = String.valueOf(request.getSession().getAttribute("role"));
        if(false)
            return R.error(511,"永远不会进入");

        Wrapper<YuangongEntity> queryWrapper = new EntityWrapper<YuangongEntity>()
            .eq("username", yuangong.getUsername())
            .or()
            .eq("yuangong_phone", yuangong.getYuangongPhone())
            .or()
            .eq("yuangong_id_number", yuangong.getYuangongIdNumber())
            ;

        logger.info("sql语句:"+queryWrapper.getSqlSegment());
        YuangongEntity yuangongEntity = yuangongService.selectOne(queryWrapper);
        if(yuangongEntity==null){
            yuangong.setCreateTime(new Date());
            yuangong.setPassword("123456");
            yuangongService.insert(yuangong);
            return R.ok();
        }else {
            return R.error(511,"账户或者员工手机号或者员工身份证号已经被使用");
        }
    }

    /**
    * 后端修改
    */
    @RequestMapping("/update")
    public R update(@RequestBody YuangongEntity yuangong, HttpServletRequest request) throws NoSuchFieldException, ClassNotFoundException, IllegalAccessException, InstantiationException {
        logger.debug("update方法:,,Controller:{},,yuangong:{}",this.getClass().getName(),yuangong.toString());
        YuangongEntity oldYuangongEntity = yuangongService.selectById(yuangong.getId());//查询原先数据

        String role = String.valueOf(request.getSession().getAttribute("role"));
//        if(false)
//            return R.error(511,"永远不会进入");
        if("".equals(yuangong.getYuangongPhoto()) || "null".equals(yuangong.getYuangongPhoto())){
                yuangong.setYuangongPhoto(null);
        }

            yuangongService.updateById(yuangong);//根据id更新
            return R.ok();
    }



    /**
    * 删除
    */
    @RequestMapping("/delete")
    public R delete(@RequestBody Integer[] ids, HttpServletRequest request){
        logger.debug("delete:,,Controller:{},,ids:{}",this.getClass().getName(),ids.toString());
        List<YuangongEntity> oldYuangongList =yuangongService.selectBatchIds(Arrays.asList(ids));//要删除的数据
        yuangongService.deleteBatchIds(Arrays.asList(ids));

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
            List<YuangongEntity> yuangongList = new ArrayList<>();//上传的东西
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
                            YuangongEntity yuangongEntity = new YuangongEntity();
//                            yuangongEntity.setUsername(data.get(0));                    //账户 要改的
//                            yuangongEntity.setPassword("123456");//密码
//                            yuangongEntity.setYuangongUuidNumber(data.get(0));                    //员工编号 要改的
//                            yuangongEntity.setYuangongName(data.get(0));                    //员工姓名 要改的
//                            yuangongEntity.setYuangongPhone(data.get(0));                    //员工手机号 要改的
//                            yuangongEntity.setYuangongIdNumber(data.get(0));                    //员工身份证号 要改的
//                            yuangongEntity.setYuangongPhoto("");//详情和图片
//                            yuangongEntity.setSexTypes(Integer.valueOf(data.get(0)));   //性别 要改的
//                            yuangongEntity.setYuangongEmail(data.get(0));                    //员工邮箱 要改的
//                            yuangongEntity.setCreateTime(date);//时间
                            yuangongList.add(yuangongEntity);


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
                                //员工编号
                                if(seachFields.containsKey("yuangongUuidNumber")){
                                    List<String> yuangongUuidNumber = seachFields.get("yuangongUuidNumber");
                                    yuangongUuidNumber.add(data.get(0));//要改的
                                }else{
                                    List<String> yuangongUuidNumber = new ArrayList<>();
                                    yuangongUuidNumber.add(data.get(0));//要改的
                                    seachFields.put("yuangongUuidNumber",yuangongUuidNumber);
                                }
                                //员工手机号
                                if(seachFields.containsKey("yuangongPhone")){
                                    List<String> yuangongPhone = seachFields.get("yuangongPhone");
                                    yuangongPhone.add(data.get(0));//要改的
                                }else{
                                    List<String> yuangongPhone = new ArrayList<>();
                                    yuangongPhone.add(data.get(0));//要改的
                                    seachFields.put("yuangongPhone",yuangongPhone);
                                }
                                //员工身份证号
                                if(seachFields.containsKey("yuangongIdNumber")){
                                    List<String> yuangongIdNumber = seachFields.get("yuangongIdNumber");
                                    yuangongIdNumber.add(data.get(0));//要改的
                                }else{
                                    List<String> yuangongIdNumber = new ArrayList<>();
                                    yuangongIdNumber.add(data.get(0));//要改的
                                    seachFields.put("yuangongIdNumber",yuangongIdNumber);
                                }
                        }

                        //查询是否重复
                         //账户
                        List<YuangongEntity> yuangongEntities_username = yuangongService.selectList(new EntityWrapper<YuangongEntity>().in("username", seachFields.get("username")));
                        if(yuangongEntities_username.size() >0 ){
                            ArrayList<String> repeatFields = new ArrayList<>();
                            for(YuangongEntity s:yuangongEntities_username){
                                repeatFields.add(s.getUsername());
                            }
                            return R.error(511,"数据库的该表中的 [账户] 字段已经存在 存在数据为:"+repeatFields.toString());
                        }
                         //员工编号
                        List<YuangongEntity> yuangongEntities_yuangongUuidNumber = yuangongService.selectList(new EntityWrapper<YuangongEntity>().in("yuangong_uuid_number", seachFields.get("yuangongUuidNumber")));
                        if(yuangongEntities_yuangongUuidNumber.size() >0 ){
                            ArrayList<String> repeatFields = new ArrayList<>();
                            for(YuangongEntity s:yuangongEntities_yuangongUuidNumber){
                                repeatFields.add(s.getYuangongUuidNumber());
                            }
                            return R.error(511,"数据库的该表中的 [员工编号] 字段已经存在 存在数据为:"+repeatFields.toString());
                        }
                         //员工手机号
                        List<YuangongEntity> yuangongEntities_yuangongPhone = yuangongService.selectList(new EntityWrapper<YuangongEntity>().in("yuangong_phone", seachFields.get("yuangongPhone")));
                        if(yuangongEntities_yuangongPhone.size() >0 ){
                            ArrayList<String> repeatFields = new ArrayList<>();
                            for(YuangongEntity s:yuangongEntities_yuangongPhone){
                                repeatFields.add(s.getYuangongPhone());
                            }
                            return R.error(511,"数据库的该表中的 [员工手机号] 字段已经存在 存在数据为:"+repeatFields.toString());
                        }
                         //员工身份证号
                        List<YuangongEntity> yuangongEntities_yuangongIdNumber = yuangongService.selectList(new EntityWrapper<YuangongEntity>().in("yuangong_id_number", seachFields.get("yuangongIdNumber")));
                        if(yuangongEntities_yuangongIdNumber.size() >0 ){
                            ArrayList<String> repeatFields = new ArrayList<>();
                            for(YuangongEntity s:yuangongEntities_yuangongIdNumber){
                                repeatFields.add(s.getYuangongIdNumber());
                            }
                            return R.error(511,"数据库的该表中的 [员工身份证号] 字段已经存在 存在数据为:"+repeatFields.toString());
                        }
                        yuangongService.insertBatch(yuangongList);
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
        YuangongEntity yuangong = yuangongService.selectOne(new EntityWrapper<YuangongEntity>().eq("username", username));
        if(yuangong==null || !yuangong.getPassword().equals(password))
            return R.error("账号或密码不正确");
        String token = tokenService.generateToken(yuangong.getId(),username, "yuangong", "员工");
        R r = R.ok();
        r.put("token", token);
        r.put("role","员工");
        r.put("username",yuangong.getYuangongName());
        r.put("tableName","yuangong");
        r.put("userId",yuangong.getId());
        return r;
    }

    /**
    * 注册
    */
    @IgnoreAuth
    @PostMapping(value = "/register")
    public R register(@RequestBody YuangongEntity yuangong, HttpServletRequest request) {
//    	ValidatorUtils.validateEntity(user);
        Wrapper<YuangongEntity> queryWrapper = new EntityWrapper<YuangongEntity>()
            .eq("username", yuangong.getUsername())
            .or()
            .eq("yuangong_phone", yuangong.getYuangongPhone())
            .or()
            .eq("yuangong_id_number", yuangong.getYuangongIdNumber())
            ;
        YuangongEntity yuangongEntity = yuangongService.selectOne(queryWrapper);
        if(yuangongEntity != null)
            return R.error("账户或者员工手机号或者员工身份证号已经被使用");
        yuangong.setYuangongUuidNumber(String.valueOf(new Date().getTime()));
        yuangong.setCreateTime(new Date());
        yuangongService.insert(yuangong);

        return R.ok();
    }

    /**
     * 重置密码
     */
    @GetMapping(value = "/resetPassword")
    public R resetPassword(Integer  id, HttpServletRequest request) {
        YuangongEntity yuangong = yuangongService.selectById(id);
        yuangong.setPassword("123456");
        yuangongService.updateById(yuangong);
        return R.ok();
    }

	/**
	 * 修改密码
	 */
	@GetMapping(value = "/updatePassword")
	public R updatePassword(String  oldPassword, String  newPassword, HttpServletRequest request) {
        YuangongEntity yuangong = yuangongService.selectById((Integer)request.getSession().getAttribute("userId"));
		if(newPassword == null){
			return R.error("新密码不能为空") ;
		}
		if(!oldPassword.equals(yuangong.getPassword())){
			return R.error("原密码输入错误");
		}
		if(newPassword.equals(yuangong.getPassword())){
			return R.error("新密码不能和原密码一致") ;
		}
        yuangong.setPassword(newPassword);
		yuangongService.updateById(yuangong);
		return R.ok();
	}



    /**
     * 忘记密码
     */
    @IgnoreAuth
    @RequestMapping(value = "/resetPass")
    public R resetPass(String username, HttpServletRequest request) {
        YuangongEntity yuangong = yuangongService.selectOne(new EntityWrapper<YuangongEntity>().eq("username", username));
        if(yuangong!=null){
            yuangong.setPassword("123456");
            yuangongService.updateById(yuangong);
            return R.ok();
        }else{
           return R.error("账号不存在");
        }
    }


    /**
    * 获取用户的session用户信息
    */
    @RequestMapping("/session")
    public R getCurrYuangong(HttpServletRequest request){
        Integer id = (Integer)request.getSession().getAttribute("userId");
        YuangongEntity yuangong = yuangongService.selectById(id);
        if(yuangong !=null){
            //entity转view
            YuangongView view = new YuangongView();
            BeanUtils.copyProperties( yuangong , view );//把实体数据重构到view中

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
        PageUtils page = yuangongService.queryPage(params);

        //字典表数据转换
        List<YuangongView> list =(List<YuangongView>)page.getList();
        for(YuangongView c:list)
            dictionaryService.dictionaryConvert(c, request); //修改对应字典表字段

        return R.ok().put("data", page);
    }

    /**
    * 前端详情
    */
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Integer id, HttpServletRequest request){
        logger.debug("detail方法:,,Controller:{},,id:{}",this.getClass().getName(),id);
        YuangongEntity yuangong = yuangongService.selectById(id);
            if(yuangong !=null){


                //entity转view
                YuangongView view = new YuangongView();
                BeanUtils.copyProperties( yuangong , view );//把实体数据重构到view中

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
    public R add(@RequestBody YuangongEntity yuangong, HttpServletRequest request){
        logger.debug("add方法:,,Controller:{},,yuangong:{}",this.getClass().getName(),yuangong.toString());
        Wrapper<YuangongEntity> queryWrapper = new EntityWrapper<YuangongEntity>()
            .eq("username", yuangong.getUsername())
            .or()
            .eq("yuangong_phone", yuangong.getYuangongPhone())
            .or()
            .eq("yuangong_id_number", yuangong.getYuangongIdNumber())
//            .notIn("yuangong_types", new Integer[]{102})
            ;
        logger.info("sql语句:"+queryWrapper.getSqlSegment());
        YuangongEntity yuangongEntity = yuangongService.selectOne(queryWrapper);
        if(yuangongEntity==null){
            yuangong.setCreateTime(new Date());
            yuangong.setPassword("123456");
        yuangongService.insert(yuangong);

            return R.ok();
        }else {
            return R.error(511,"账户或者员工手机号或者员工身份证号已经被使用");
        }
    }

}

