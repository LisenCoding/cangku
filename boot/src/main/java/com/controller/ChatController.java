
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
 * 反馈信息
 * 后端接口
 * @author
 * @email
*/
@RestController
@Controller
@RequestMapping("/chat")
public class ChatController {
    private static final Logger logger = LoggerFactory.getLogger(ChatController.class);

    private static final String TABLE_NAME = "chat";

    @Autowired
    private ChatService chatService;


    @Autowired
    private TokenService tokenService;

    @Autowired
    private AddressService addressService;//收货地址
    @Autowired
    private CangkuService cangkuService;//仓库
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
        CommonUtil.checkMap(params);
        PageUtils page = chatService.queryPage(params);

        //字典表数据转换
        List<ChatView> list =(List<ChatView>)page.getList();
        for(ChatView c:list){
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
        ChatEntity chat = chatService.selectById(id);
        if(chat !=null){
            //entity转view
            ChatView view = new ChatView();
            BeanUtils.copyProperties( chat , view );//把实体数据重构到view中
            //级联表 用户
            //级联表
            YonghuEntity yonghu = yonghuService.selectById(chat.getYonghuId());
            if(yonghu != null){
            BeanUtils.copyProperties( yonghu , view ,new String[]{ "id", "createTime", "insertTime", "updateTime", "username", "password", "newMoney", "yonghuId"});//把级联的数据添加到view中,并排除id和创建时间字段,当前表的级联注册表
            view.setYonghuId(yonghu.getId());
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
    public R save(@RequestBody ChatEntity chat, HttpServletRequest request){
        logger.debug("save方法:,,Controller:{},,chat:{}",this.getClass().getName(),chat.toString());

        String role = String.valueOf(request.getSession().getAttribute("role"));
        if(false)
            return R.error(511,"永远不会进入");
        else if("用户".equals(role))
            chat.setYonghuId(Integer.valueOf(String.valueOf(request.getSession().getAttribute("userId"))));

        Wrapper<ChatEntity> queryWrapper = new EntityWrapper<ChatEntity>()
            .eq("yonghu_id", chat.getYonghuId())
            .eq("chat_issue", chat.getChatIssue())
            .eq("chat_reply", chat.getChatReply())
            .eq("zhuangtai_types", chat.getZhuangtaiTypes())
            .eq("chat_types", chat.getChatTypes())
            ;

        logger.info("sql语句:"+queryWrapper.getSqlSegment());
        ChatEntity chatEntity = chatService.selectOne(queryWrapper);
        if(chatEntity==null){
            chat.setInsertTime(new Date());
            chatService.insert(chat);
            return R.ok();
        }else {
            return R.error(511,"表中有相同数据");
        }
    }

    /**
    * 后端修改
    */
    @RequestMapping("/update")
    public R update(@RequestBody ChatEntity chat, HttpServletRequest request) throws NoSuchFieldException, ClassNotFoundException, IllegalAccessException, InstantiationException {
        logger.debug("update方法:,,Controller:{},,chat:{}",this.getClass().getName(),chat.toString());
        ChatEntity oldChatEntity = chatService.selectById(chat.getId());//查询原先数据

        String role = String.valueOf(request.getSession().getAttribute("role"));
//        if(false)
//            return R.error(511,"永远不会进入");
//        else if("用户".equals(role))
//            chat.setYonghuId(Integer.valueOf(String.valueOf(request.getSession().getAttribute("userId"))));

            chatService.updateById(chat);//根据id更新
            return R.ok();
    }



    /**
    * 删除
    */
    @RequestMapping("/delete")
    public R delete(@RequestBody Integer[] ids, HttpServletRequest request){
        logger.debug("delete:,,Controller:{},,ids:{}",this.getClass().getName(),ids.toString());
        List<ChatEntity> oldChatList =chatService.selectBatchIds(Arrays.asList(ids));//要删除的数据
        chatService.deleteBatchIds(Arrays.asList(ids));

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
            List<ChatEntity> chatList = new ArrayList<>();//上传的东西
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
                            ChatEntity chatEntity = new ChatEntity();
//                            chatEntity.setYonghuId(Integer.valueOf(data.get(0)));   //提问用户 要改的
//                            chatEntity.setChatIssue(data.get(0));                    //问题 要改的
//                            chatEntity.setIssueTime(sdf.parse(data.get(0)));          //问题时间 要改的
//                            chatEntity.setChatReply(data.get(0));                    //回复 要改的
//                            chatEntity.setReplyTime(sdf.parse(data.get(0)));          //回复时间 要改的
//                            chatEntity.setZhuangtaiTypes(Integer.valueOf(data.get(0)));   //状态 要改的
//                            chatEntity.setChatTypes(Integer.valueOf(data.get(0)));   //数据类型 要改的
//                            chatEntity.setInsertTime(date);//时间
                            chatList.add(chatEntity);


                            //把要查询是否重复的字段放入map中
                        }

                        //查询是否重复
                        chatService.insertBatch(chatList);
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
        PageUtils page = chatService.queryPage(params);

        //字典表数据转换
        List<ChatView> list =(List<ChatView>)page.getList();
        for(ChatView c:list)
            dictionaryService.dictionaryConvert(c, request); //修改对应字典表字段

        return R.ok().put("data", page);
    }

    /**
    * 前端详情
    */
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Integer id, HttpServletRequest request){
        logger.debug("detail方法:,,Controller:{},,id:{}",this.getClass().getName(),id);
        ChatEntity chat = chatService.selectById(id);
            if(chat !=null){


                //entity转view
                ChatView view = new ChatView();
                BeanUtils.copyProperties( chat , view );//把实体数据重构到view中

                //级联表
                    YonghuEntity yonghu = yonghuService.selectById(chat.getYonghuId());
                if(yonghu != null){
                    BeanUtils.copyProperties( yonghu , view ,new String[]{ "id", "createTime", "insertTime", "updateTime", "username", "password", "newMoney", "yonghuId"});//把级联的数据添加到view中,并排除id和创建时间字段
                    view.setYonghuId(yonghu.getId());
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
    public R add(@RequestBody ChatEntity chat, HttpServletRequest request){
        logger.debug("add方法:,,Controller:{},,chat:{}",this.getClass().getName(),chat.toString());
        Wrapper<ChatEntity> queryWrapper = new EntityWrapper<ChatEntity>()
            .eq("yonghu_id", chat.getYonghuId())
            .eq("chat_issue", chat.getChatIssue())
            .eq("chat_reply", chat.getChatReply())
            .eq("zhuangtai_types", chat.getZhuangtaiTypes())
            .eq("chat_types", chat.getChatTypes())
//            .notIn("chat_types", new Integer[]{102})
            ;
        logger.info("sql语句:"+queryWrapper.getSqlSegment());
        ChatEntity chatEntity = chatService.selectOne(queryWrapper);
        if(chatEntity==null){
            chat.setInsertTime(new Date());
        chatService.insert(chat);

            return R.ok();
        }else {
            return R.error(511,"表中有相同数据");
        }
    }

}

