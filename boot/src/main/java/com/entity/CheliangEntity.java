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
 * 车辆
 *
 * @author 
 * @email
 */
@TableName("cheliang")
public class CheliangEntity<T> implements Serializable {
    private static final long serialVersionUID = 1L;


	public CheliangEntity() {

	}

	public CheliangEntity(T t) {
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
     * 车辆名称
     */
    @ColumnInfo(comment="车辆名称",type="varchar(200)")
    @TableField(value = "cheliang_name")

    private String cheliangName;


    /**
     * 车辆编号
     */
    @ColumnInfo(comment="车辆编号",type="varchar(200)")
    @TableField(value = "cheliang_uuid_number")

    private String cheliangUuidNumber;


    /**
     * 车辆照片
     */
    @ColumnInfo(comment="车辆照片",type="varchar(200)")
    @TableField(value = "cheliang_photo")

    private String cheliangPhoto;


    /**
     * 车辆牌照
     */
    @ColumnInfo(comment="车辆牌照",type="varchar(200)")
    @TableField(value = "cheliang_paizhao")

    private String cheliangPaizhao;


    /**
     * 车辆类型
     */
    @ColumnInfo(comment="车辆类型",type="int(11)")
    @TableField(value = "cheliang_types")

    private Integer cheliangTypes;


    /**
     * 车辆介绍
     */
    @ColumnInfo(comment="车辆介绍",type="longtext")
    @TableField(value = "cheliang_content")

    private String cheliangContent;


    /**
     * 逻辑删除
     */
    @ColumnInfo(comment="逻辑删除",type="int(11)")
    @TableField(value = "cheliang_delete")

    private Integer cheliangDelete;


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
	 * 获取：车辆名称
	 */
    public String getCheliangName() {
        return cheliangName;
    }
    /**
	 * 设置：车辆名称
	 */

    public void setCheliangName(String cheliangName) {
        this.cheliangName = cheliangName;
    }
    /**
	 * 获取：车辆编号
	 */
    public String getCheliangUuidNumber() {
        return cheliangUuidNumber;
    }
    /**
	 * 设置：车辆编号
	 */

    public void setCheliangUuidNumber(String cheliangUuidNumber) {
        this.cheliangUuidNumber = cheliangUuidNumber;
    }
    /**
	 * 获取：车辆照片
	 */
    public String getCheliangPhoto() {
        return cheliangPhoto;
    }
    /**
	 * 设置：车辆照片
	 */

    public void setCheliangPhoto(String cheliangPhoto) {
        this.cheliangPhoto = cheliangPhoto;
    }
    /**
	 * 获取：车辆牌照
	 */
    public String getCheliangPaizhao() {
        return cheliangPaizhao;
    }
    /**
	 * 设置：车辆牌照
	 */

    public void setCheliangPaizhao(String cheliangPaizhao) {
        this.cheliangPaizhao = cheliangPaizhao;
    }
    /**
	 * 获取：车辆类型
	 */
    public Integer getCheliangTypes() {
        return cheliangTypes;
    }
    /**
	 * 设置：车辆类型
	 */

    public void setCheliangTypes(Integer cheliangTypes) {
        this.cheliangTypes = cheliangTypes;
    }
    /**
	 * 获取：车辆介绍
	 */
    public String getCheliangContent() {
        return cheliangContent;
    }
    /**
	 * 设置：车辆介绍
	 */

    public void setCheliangContent(String cheliangContent) {
        this.cheliangContent = cheliangContent;
    }
    /**
	 * 获取：逻辑删除
	 */
    public Integer getCheliangDelete() {
        return cheliangDelete;
    }
    /**
	 * 设置：逻辑删除
	 */

    public void setCheliangDelete(Integer cheliangDelete) {
        this.cheliangDelete = cheliangDelete;
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
        return "Cheliang{" +
            ", id=" + id +
            ", cheliangName=" + cheliangName +
            ", cheliangUuidNumber=" + cheliangUuidNumber +
            ", cheliangPhoto=" + cheliangPhoto +
            ", cheliangPaizhao=" + cheliangPaizhao +
            ", cheliangTypes=" + cheliangTypes +
            ", cheliangContent=" + cheliangContent +
            ", cheliangDelete=" + cheliangDelete +
            ", insertTime=" + DateUtil.convertString(insertTime,"yyyy-MM-dd") +
            ", createTime=" + DateUtil.convertString(createTime,"yyyy-MM-dd") +
        "}";
    }
}
