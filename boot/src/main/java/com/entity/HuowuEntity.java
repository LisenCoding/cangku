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
 * 货物
 *
 * @author 
 * @email
 */
@TableName("huowu")
public class HuowuEntity<T> implements Serializable {
    private static final long serialVersionUID = 1L;


	public HuowuEntity() {

	}

	public HuowuEntity(T t) {
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
     * 商家
     */
    @ColumnInfo(comment="商家",type="int(11)")
    @TableField(value = "shangpin_order_id")

    private Integer shangpinOrderId;


    /**
     * 货物名称
     */
    @ColumnInfo(comment="货物名称",type="varchar(200)")
    @TableField(value = "huowu_name")

    private String huowuName;


    /**
     * 货物编号
     */
    @ColumnInfo(comment="货物编号",type="varchar(200)")
    @TableField(value = "huowu_uuid_number")

    private String huowuUuidNumber;


    /**
     * 货物类型
     */
    @ColumnInfo(comment="货物类型",type="int(11)")
    @TableField(value = "huowu_types")

    private Integer huowuTypes;


    /**
     * 货物介绍
     */
    @ColumnInfo(comment="货物介绍",type="longtext")
    @TableField(value = "huowu_content")

    private String huowuContent;


    /**
     * 逻辑删除
     */
    @ColumnInfo(comment="逻辑删除",type="int(11)")
    @TableField(value = "huowu_delete")

    private Integer huowuDelete;


    /**
     * 录入时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
    @ColumnInfo(comment="录入时间",type="timestamp")
    @TableField(value = "insert_time",fill = FieldFill.INSERT)

    private Date insertTime;


    /**
     * 创建时间   listShow
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
	 * 获取：商家
	 */
    public Integer getShangpinOrderId() {
        return shangpinOrderId;
    }
    /**
	 * 设置：商家
	 */

    public void setShangpinOrderId(Integer shangpinOrderId) {
        this.shangpinOrderId = shangpinOrderId;
    }
    /**
	 * 获取：货物名称
	 */
    public String getHuowuName() {
        return huowuName;
    }
    /**
	 * 设置：货物名称
	 */

    public void setHuowuName(String huowuName) {
        this.huowuName = huowuName;
    }
    /**
	 * 获取：货物编号
	 */
    public String getHuowuUuidNumber() {
        return huowuUuidNumber;
    }
    /**
	 * 设置：货物编号
	 */

    public void setHuowuUuidNumber(String huowuUuidNumber) {
        this.huowuUuidNumber = huowuUuidNumber;
    }
    /**
	 * 获取：货物类型
	 */
    public Integer getHuowuTypes() {
        return huowuTypes;
    }
    /**
	 * 设置：货物类型
	 */

    public void setHuowuTypes(Integer huowuTypes) {
        this.huowuTypes = huowuTypes;
    }
    /**
	 * 获取：货物介绍
	 */
    public String getHuowuContent() {
        return huowuContent;
    }
    /**
	 * 设置：货物介绍
	 */

    public void setHuowuContent(String huowuContent) {
        this.huowuContent = huowuContent;
    }
    /**
	 * 获取：逻辑删除
	 */
    public Integer getHuowuDelete() {
        return huowuDelete;
    }
    /**
	 * 设置：逻辑删除
	 */

    public void setHuowuDelete(Integer huowuDelete) {
        this.huowuDelete = huowuDelete;
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
	 * 获取：创建时间   listShow
	 */
    public Date getCreateTime() {
        return createTime;
    }
    /**
	 * 设置：创建时间   listShow
	 */

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "Huowu{" +
            ", id=" + id +
            ", shangpinOrderId=" + shangpinOrderId +
            ", huowuName=" + huowuName +
            ", huowuUuidNumber=" + huowuUuidNumber +
            ", huowuTypes=" + huowuTypes +
            ", huowuContent=" + huowuContent +
            ", huowuDelete=" + huowuDelete +
            ", insertTime=" + DateUtil.convertString(insertTime,"yyyy-MM-dd") +
            ", createTime=" + DateUtil.convertString(createTime,"yyyy-MM-dd") +
        "}";
    }
}
