package com.entity;

import com.annotation.ColumnInfo;
import javax.validation.constraints.*;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.lang.reflect.InvocationTargetException;
import java.io.Serializable;
import java.util.*;
import org.apache.tools.ant.util.DateUtils;
import org.springframework.format.annotation.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.beanutils.BeanUtils;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.enums.FieldFill;
import com.utils.DateUtil;


/**
 * 公告
 *
 * @author 
 * @email
 */
@TableName("gonggao")
public class GonggaoEntity<T> implements Serializable {
    private static final long serialVersionUID = 1L;


	public GonggaoEntity() {

	}

	public GonggaoEntity(T t) {
		try {
			BeanUtils.copyProperties(this, t);
		} catch (IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


    /**
     * 主键
     */
    @TableId(type = IdType.AUTO)
    @ColumnInfo(comment="主键",type="int(11)")
    @TableField(value = "id")

    private Integer id;


    /**
     * 公告名称
     */
    @ColumnInfo(comment="公告名称",type="varchar(200)")
    @TableField(value = "gonggao_name")

    private String gonggaoName;


    /**
     * 公告图片
     */
    @ColumnInfo(comment="公告图片",type="varchar(200)")
    @TableField(value = "gonggao_photo")

    private String gonggaoPhoto;


    /**
     * 公告类型
     */
    @ColumnInfo(comment="公告类型",type="int(11)")
    @TableField(value = "gonggao_types")

    private Integer gonggaoTypes;


    /**
     * 发布时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
    @ColumnInfo(comment="发布时间",type="timestamp")
    @TableField(value = "insert_time",fill = FieldFill.INSERT)

    private Date insertTime;


    /**
     * 公告详情
     */
    @ColumnInfo(comment="公告详情",type="longtext")
    @TableField(value = "gonggao_content")

    private String gonggaoContent;


    /**
     * 创建时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
    @ColumnInfo(comment="创建时间",type="timestamp")
    @TableField(value = "create_time",fill = FieldFill.INSERT)

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
	 * 获取：创建时间
	 */
    public Date getCreateTime() {
        return createTime;
    }
    /**
	 * 设置：创建时间
	 */

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "Gonggao{" +
            ", id=" + id +
            ", gonggaoName=" + gonggaoName +
            ", gonggaoPhoto=" + gonggaoPhoto +
            ", gonggaoTypes=" + gonggaoTypes +
            ", insertTime=" + DateUtil.convertString(insertTime,"yyyy-MM-dd") +
            ", gonggaoContent=" + gonggaoContent +
            ", createTime=" + DateUtil.convertString(createTime,"yyyy-MM-dd") +
        "}";
    }
}
