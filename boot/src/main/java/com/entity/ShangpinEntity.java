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
 * 商品
 *
 * @author 
 * @email
 */
@TableName("shangpin")
public class ShangpinEntity<T> implements Serializable {
    private static final long serialVersionUID = 1L;


	public ShangpinEntity() {

	}

	public ShangpinEntity(T t) {
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
     * 仓库
     */
    @ColumnInfo(comment="仓库",type="int(11)")
    @TableField(value = "cangku_id")

    private Integer cangkuId;


    /**
     * 商品名称
     */
    @ColumnInfo(comment="商品名称",type="varchar(200)")
    @TableField(value = "shangpin_name")

    private String shangpinName;


    /**
     * 商品编号
     */
    @ColumnInfo(comment="商品编号",type="varchar(200)")
    @TableField(value = "shangpin_uuid_number")

    private String shangpinUuidNumber;


    /**
     * 商品照片
     */
    @ColumnInfo(comment="商品照片",type="varchar(200)")
    @TableField(value = "shangpin_photo")

    private String shangpinPhoto;


    /**
     * 商品类型
     */
    @ColumnInfo(comment="商品类型",type="int(11)")
    @TableField(value = "shangpin_types")

    private Integer shangpinTypes;


    /**
     * 商品库存
     */
    @ColumnInfo(comment="商品库存",type="int(11)")
    @TableField(value = "shangpin_kucun_number")

    private Integer shangpinKucunNumber;


    /**
     * 现价/积分
     */
    @ColumnInfo(comment="现价/积分",type="decimal(10,2)")
    @TableField(value = "shangpin_new_money")

    private Double shangpinNewMoney;


    /**
     * 商品介绍
     */
    @ColumnInfo(comment="商品介绍",type="longtext")
    @TableField(value = "shangpin_content")

    private String shangpinContent;


    /**
     * 是否上架
     */
    @ColumnInfo(comment="是否上架",type="int(11)")
    @TableField(value = "shangxia_types")

    private Integer shangxiaTypes;


    /**
     * 逻辑删除
     */
    @ColumnInfo(comment="逻辑删除",type="int(11)")
    @TableField(value = "shangpin_delete")

    private Integer shangpinDelete;


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
	 * 获取：仓库
	 */
    public Integer getCangkuId() {
        return cangkuId;
    }
    /**
	 * 设置：仓库
	 */

    public void setCangkuId(Integer cangkuId) {
        this.cangkuId = cangkuId;
    }
    /**
	 * 获取：商品名称
	 */
    public String getShangpinName() {
        return shangpinName;
    }
    /**
	 * 设置：商品名称
	 */

    public void setShangpinName(String shangpinName) {
        this.shangpinName = shangpinName;
    }
    /**
	 * 获取：商品编号
	 */
    public String getShangpinUuidNumber() {
        return shangpinUuidNumber;
    }
    /**
	 * 设置：商品编号
	 */

    public void setShangpinUuidNumber(String shangpinUuidNumber) {
        this.shangpinUuidNumber = shangpinUuidNumber;
    }
    /**
	 * 获取：商品照片
	 */
    public String getShangpinPhoto() {
        return shangpinPhoto;
    }
    /**
	 * 设置：商品照片
	 */

    public void setShangpinPhoto(String shangpinPhoto) {
        this.shangpinPhoto = shangpinPhoto;
    }
    /**
	 * 获取：商品类型
	 */
    public Integer getShangpinTypes() {
        return shangpinTypes;
    }
    /**
	 * 设置：商品类型
	 */

    public void setShangpinTypes(Integer shangpinTypes) {
        this.shangpinTypes = shangpinTypes;
    }
    /**
	 * 获取：商品库存
	 */
    public Integer getShangpinKucunNumber() {
        return shangpinKucunNumber;
    }
    /**
	 * 设置：商品库存
	 */

    public void setShangpinKucunNumber(Integer shangpinKucunNumber) {
        this.shangpinKucunNumber = shangpinKucunNumber;
    }
    /**
	 * 获取：现价/积分
	 */
    public Double getShangpinNewMoney() {
        return shangpinNewMoney;
    }
    /**
	 * 设置：现价/积分
	 */

    public void setShangpinNewMoney(Double shangpinNewMoney) {
        this.shangpinNewMoney = shangpinNewMoney;
    }
    /**
	 * 获取：商品介绍
	 */
    public String getShangpinContent() {
        return shangpinContent;
    }
    /**
	 * 设置：商品介绍
	 */

    public void setShangpinContent(String shangpinContent) {
        this.shangpinContent = shangpinContent;
    }
    /**
	 * 获取：是否上架
	 */
    public Integer getShangxiaTypes() {
        return shangxiaTypes;
    }
    /**
	 * 设置：是否上架
	 */

    public void setShangxiaTypes(Integer shangxiaTypes) {
        this.shangxiaTypes = shangxiaTypes;
    }
    /**
	 * 获取：逻辑删除
	 */
    public Integer getShangpinDelete() {
        return shangpinDelete;
    }
    /**
	 * 设置：逻辑删除
	 */

    public void setShangpinDelete(Integer shangpinDelete) {
        this.shangpinDelete = shangpinDelete;
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
        return "Shangpin{" +
            ", id=" + id +
            ", cangkuId=" + cangkuId +
            ", shangpinName=" + shangpinName +
            ", shangpinUuidNumber=" + shangpinUuidNumber +
            ", shangpinPhoto=" + shangpinPhoto +
            ", shangpinTypes=" + shangpinTypes +
            ", shangpinKucunNumber=" + shangpinKucunNumber +
            ", shangpinNewMoney=" + shangpinNewMoney +
            ", shangpinContent=" + shangpinContent +
            ", shangxiaTypes=" + shangxiaTypes +
            ", shangpinDelete=" + shangpinDelete +
            ", insertTime=" + DateUtil.convertString(insertTime,"yyyy-MM-dd") +
            ", createTime=" + DateUtil.convertString(createTime,"yyyy-MM-dd") +
        "}";
    }
}
