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
 * 商品收藏
 *
 * @author 
 * @email
 */
@TableName("shangpin_collection")
public class ShangpinCollectionEntity<T> implements Serializable {
    private static final long serialVersionUID = 1L;


	public ShangpinCollectionEntity() {

	}

	public ShangpinCollectionEntity(T t) {
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
     * 商品
     */
    @ColumnInfo(comment="商品",type="int(11)")
    @TableField(value = "shangpin_id")

    private Integer shangpinId;


    /**
     * 用户
     */
    @ColumnInfo(comment="用户",type="int(11)")
    @TableField(value = "yonghu_id")

    private Integer yonghuId;


    /**
     * 类型
     */
    @ColumnInfo(comment="类型",type="int(11)")
    @TableField(value = "shangpin_collection_types")

    private Integer shangpinCollectionTypes;


    /**
     * 收藏时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
    @ColumnInfo(comment="收藏时间",type="timestamp")
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
	 * 获取：商品
	 */
    public Integer getShangpinId() {
        return shangpinId;
    }
    /**
	 * 设置：商品
	 */

    public void setShangpinId(Integer shangpinId) {
        this.shangpinId = shangpinId;
    }
    /**
	 * 获取：用户
	 */
    public Integer getYonghuId() {
        return yonghuId;
    }
    /**
	 * 设置：用户
	 */

    public void setYonghuId(Integer yonghuId) {
        this.yonghuId = yonghuId;
    }
    /**
	 * 获取：类型
	 */
    public Integer getShangpinCollectionTypes() {
        return shangpinCollectionTypes;
    }
    /**
	 * 设置：类型
	 */

    public void setShangpinCollectionTypes(Integer shangpinCollectionTypes) {
        this.shangpinCollectionTypes = shangpinCollectionTypes;
    }
    /**
	 * 获取：收藏时间
	 */
    public Date getInsertTime() {
        return insertTime;
    }
    /**
	 * 设置：收藏时间
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
        return "ShangpinCollection{" +
            ", id=" + id +
            ", shangpinId=" + shangpinId +
            ", yonghuId=" + yonghuId +
            ", shangpinCollectionTypes=" + shangpinCollectionTypes +
            ", insertTime=" + DateUtil.convertString(insertTime,"yyyy-MM-dd") +
            ", createTime=" + DateUtil.convertString(createTime,"yyyy-MM-dd") +
        "}";
    }
}
