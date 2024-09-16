package com.entity.model;

import com.entity.ChatEntity;

import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;
import java.io.Serializable;


/**
 * 反馈信息
 * 接收传参的实体类
 *（实际开发中配合移动端接口开发手动去掉些没用的字段， 后端一般用entity就够用了）
 * 取自ModelAndView 的model名称
 */
public class ChatModel implements Serializable {
    private static final long serialVersionUID = 1L;




    /**
     * 主键
     */
    private Integer id;


    /**
     * 提问用户
     */
    private Integer yonghuId;


    /**
     * 问题
     */
    private String chatIssue;


    /**
     * 问题时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
    private Date issueTime;


    /**
     * 回复
     */
    private String chatReply;


    /**
     * 回复时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
    private Date replyTime;


    /**
     * 状态
     */
    private Integer zhuangtaiTypes;


    /**
     * 数据类型
     */
    private Integer chatTypes;


    /**
     * 创建时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
    private Date insertTime;


    /**
	 * 获取：主键
	 */
    public Integer getId() {
        return id;
    }


    /**
	 * 设置：主键
	 */
    public void setId(Integer id) {
        this.id = id;
    }
    /**
	 * 获取：提问用户
	 */
    public Integer getYonghuId() {
        return yonghuId;
    }


    /**
	 * 设置：提问用户
	 */
    public void setYonghuId(Integer yonghuId) {
        this.yonghuId = yonghuId;
    }
    /**
	 * 获取：问题
	 */
    public String getChatIssue() {
        return chatIssue;
    }


    /**
	 * 设置：问题
	 */
    public void setChatIssue(String chatIssue) {
        this.chatIssue = chatIssue;
    }
    /**
	 * 获取：问题时间
	 */
    public Date getIssueTime() {
        return issueTime;
    }


    /**
	 * 设置：问题时间
	 */
    public void setIssueTime(Date issueTime) {
        this.issueTime = issueTime;
    }
    /**
	 * 获取：回复
	 */
    public String getChatReply() {
        return chatReply;
    }


    /**
	 * 设置：回复
	 */
    public void setChatReply(String chatReply) {
        this.chatReply = chatReply;
    }
    /**
	 * 获取：回复时间
	 */
    public Date getReplyTime() {
        return replyTime;
    }


    /**
	 * 设置：回复时间
	 */
    public void setReplyTime(Date replyTime) {
        this.replyTime = replyTime;
    }
    /**
	 * 获取：状态
	 */
    public Integer getZhuangtaiTypes() {
        return zhuangtaiTypes;
    }


    /**
	 * 设置：状态
	 */
    public void setZhuangtaiTypes(Integer zhuangtaiTypes) {
        this.zhuangtaiTypes = zhuangtaiTypes;
    }
    /**
	 * 获取：数据类型
	 */
    public Integer getChatTypes() {
        return chatTypes;
    }


    /**
	 * 设置：数据类型
	 */
    public void setChatTypes(Integer chatTypes) {
        this.chatTypes = chatTypes;
    }
    /**
	 * 获取：创建时间
	 */
    public Date getInsertTime() {
        return insertTime;
    }


    /**
	 * 设置：创建时间
	 */
    public void setInsertTime(Date insertTime) {
        this.insertTime = insertTime;
    }

    }
