package com.entity.model;

import com.entity.GonggaoEntity;

import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;
import java.io.Serializable;


/**
 * 公告
 * 接收传参的实体类
 *（实际开发中配合移动端接口开发手动去掉些没用的字段， 后端一般用entity就够用了）
 * 取自ModelAndView 的model名称
 */
public class GonggaoModel implements Serializable {
    private static final long serialVersionUID = 1L;




    /**
     * 主键
     */
    private Integer id;


    /**
     * 公告名称
     */
    private String gonggaoName;


    /**
     * 公告图片
     */
    private String gonggaoPhoto;


    /**
     * 公告类型
     */
    private Integer gonggaoTypes;


    /**
     * 发布时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
    private Date insertTime;


    /**
     * 公告详情
     */
    private String gonggaoContent;


    /**
     * 创建时间 show1 show2 nameShow
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
    private Date createTime;


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
	 * 获取：公告名称
	 */
    public String getGonggaoName() {
        return gonggaoName;
    }


    /**
	 * 设置：公告名称
	 */
    public void setGonggaoName(String gonggaoName) {
        this.gonggaoName = gonggaoName;
    }
    /**
	 * 获取：公告图片
	 */
    public String getGonggaoPhoto() {
        return gonggaoPhoto;
    }


    /**
	 * 设置：公告图片
	 */
    public void setGonggaoPhoto(String gonggaoPhoto) {
        this.gonggaoPhoto = gonggaoPhoto;
    }
    /**
	 * 获取：公告类型
	 */
    public Integer getGonggaoTypes() {
        return gonggaoTypes;
    }


    /**
	 * 设置：公告类型
	 */
    public void setGonggaoTypes(Integer gonggaoTypes) {
        this.gonggaoTypes = gonggaoTypes;
    }
    /**
	 * 获取：发布时间
	 */
    public Date getInsertTime() {
        return insertTime;
    }


    /**
	 * 设置：发布时间
	 */
    public void setInsertTime(Date insertTime) {
        this.insertTime = insertTime;
    }
    /**
	 * 获取：公告详情
	 */
    public String getGonggaoContent() {
        return gonggaoContent;
    }


    /**
	 * 设置：公告详情
	 */
    public void setGonggaoContent(String gonggaoContent) {
        this.gonggaoContent = gonggaoContent;
    }
    /**
	 * 获取：创建时间 show1 show2 nameShow
	 */
    public Date getCreateTime() {
        return createTime;
    }


    /**
	 * 设置：创建时间 show1 show2 nameShow
	 */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    }
