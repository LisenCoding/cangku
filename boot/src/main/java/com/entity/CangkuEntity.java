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
 * 仓库
 *
 * @author 
 * @email
 */
@TableName("cangku")
public class CangkuEntity<T> implements Serializable {
    private static final long serialVersionUID = 1L;


	public CangkuEntity() {

	}

	public CangkuEntity(T t) {
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
     * 仓库名称
     */
    @ColumnInfo(comment="仓库名称",type="varchar(200)")
    @TableField(value = "cangku_name")

    private String cangkuName;


    /**
     * 仓库编号
     */
    @ColumnInfo(comment="仓库编号",type="varchar(200)")
    @TableField(value = "cangku_uuid_number")

    private String cangkuUuidNumber;


    /**
     * 仓库照片
     */
    @ColumnInfo(comment="仓库照片",type="varchar(200)")
    @TableField(value = "cangku_photo")

    private String cangkuPhoto;


    /**
     * 仓库地点
     */
    @ColumnInfo(comment="仓库地点",type="varchar(200)")
    @TableField(value = "cangku_address")

    private String cangkuAddress;


    /**
     * 仓库类型
     */
    @ColumnInfo(comment="仓库类型",type="int(11)")
    @TableField(value = "cangku_types")

    private Integer cangkuTypes;


    /**
     * 仓库介绍
     */
    @ColumnInfo(comment="仓库介绍",type="longtext")
    @TableField(value = "cangku_content")

    private String cangkuContent;


    /**
     * 逻辑删除
     */
    @ColumnInfo(comment="逻辑删除",type="int(11)")
    @TableField(value = "cangku_delete")

    private Integer cangkuDelete;


    /**
     * 录入时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
    @ColumnInfo(comment="录入时间",type="timestamp")
    @TableField(value = "insert_time",fill = FieldFill.INSERT)

    private Date insertTime;


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
	 * 获取：仓库名称
	 */
    public String getCangkuName() {
        return cangkuName;
    }
    /**
	 * 设置：仓库名称
	 */

    public void setCangkuName(String cangkuName) {
        this.cangkuName = cangkuName;
    }
    /**
	 * 获取：仓库编号
	 */
    public String getCangkuUuidNumber() {
        return cangkuUuidNumber;
    }
    /**
	 * 设置：仓库编号
	 */

    public void setCangkuUuidNumber(String cangkuUuidNumber) {
        this.cangkuUuidNumber = cangkuUuidNumber;
    }
    /**
	 * 获取：仓库照片
	 */
    public String getCangkuPhoto() {
        return cangkuPhoto;
    }
    /**
	 * 设置：仓库照片
	 */

    public void setCangkuPhoto(String cangkuPhoto) {
        this.cangkuPhoto = cangkuPhoto;
    }
    /**
	 * 获取：仓库地点
	 */
    public String getCangkuAddress() {
        return cangkuAddress;
    }
    /**
	 * 设置：仓库地点
	 */

    public void setCangkuAddress(String cangkuAddress) {
        this.cangkuAddress = cangkuAddress;
    }
    /**
	 * 获取：仓库类型
	 */
    public Integer getCangkuTypes() {
        return cangkuTypes;
    }
    /**
	 * 设置：仓库类型
	 */

    public void setCangkuTypes(Integer cangkuTypes) {
        this.cangkuTypes = cangkuTypes;
    }
    /**
	 * 获取：仓库介绍
	 */
    public String getCangkuContent() {
        return cangkuContent;
    }
    /**
	 * 设置：仓库介绍
	 */

    public void setCangkuContent(String cangkuContent) {
        this.cangkuContent = cangkuContent;
    }
    /**
	 * 获取：逻辑删除
	 */
    public Integer getCangkuDelete() {
        return cangkuDelete;
    }
    /**
	 * 设置：逻辑删除
	 */

    public void setCangkuDelete(Integer cangkuDelete) {
        this.cangkuDelete = cangkuDelete;
    }
    /**
	 * 获取：录入时间
	 */
    public Date getInsertTime() {
        return insertTime;
    }
    /**
	 * 设置：录入时间
	 */

    public void setInsertTime(Date insertTime) {
        this.insertTime = insertTime;
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
        return "Cangku{" +
            ", id=" + id +
            ", cangkuName=" + cangkuName +
            ", cangkuUuidNumber=" + cangkuUuidNumber +
            ", cangkuPhoto=" + cangkuPhoto +
            ", cangkuAddress=" + cangkuAddress +
            ", cangkuTypes=" + cangkuTypes +
            ", cangkuContent=" + cangkuContent +
            ", cangkuDelete=" + cangkuDelete +
            ", insertTime=" + DateUtil.convertString(insertTime,"yyyy-MM-dd") +
            ", createTime=" + DateUtil.convertString(createTime,"yyyy-MM-dd") +
        "}";
    }
}
