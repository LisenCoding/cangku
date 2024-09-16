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
 * 出入库详情
 *
 * @author 
 * @email
 */
@TableName("shangpin_churu_inout_list")
public class ShangpinChuruInoutListEntity<T> implements Serializable {
    private static final long serialVersionUID = 1L;


	public ShangpinChuruInoutListEntity() {

	}

	public ShangpinChuruInoutListEntity(T t) {
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
     * 出入库
     */
    @ColumnInfo(comment="出入库",type="int(11)")
    @TableField(value = "shangpin_churu_inout_id")

    private Integer shangpinChuruInoutId;


    /**
     * 商品
     */
    @ColumnInfo(comment="商品",type="int(11)")
    @TableField(value = "shangpin_id")

    private Integer shangpinId;


    /**
     * 仓库
     */
    @ColumnInfo(comment="仓库",type="int(11)")
    @TableField(value = "cangku_id")

    private Integer cangkuId;


    /**
     * 操作数量
     */
    @ColumnInfo(comment="操作数量",type="int(11)")
    @TableField(value = "shangpin_churu_inout_list_number")

    private Integer shangpinChuruInoutListNumber;


    /**
     * 操作时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
    @ColumnInfo(comment="操作时间",type="timestamp")
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
	 * 获取：出入库
	 */
    public Integer getShangpinChuruInoutId() {
        return shangpinChuruInoutId;
    }
    /**
	 * 设置：出入库
	 */

    public void setShangpinChuruInoutId(Integer shangpinChuruInoutId) {
        this.shangpinChuruInoutId = shangpinChuruInoutId;
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
	 * 获取：操作数量
	 */
    public Integer getShangpinChuruInoutListNumber() {
        return shangpinChuruInoutListNumber;
    }
    /**
	 * 设置：操作数量
	 */

    public void setShangpinChuruInoutListNumber(Integer shangpinChuruInoutListNumber) {
        this.shangpinChuruInoutListNumber = shangpinChuruInoutListNumber;
    }
    /**
	 * 获取：操作时间
	 */
    public Date getInsertTime() {
        return insertTime;
    }
    /**
	 * 设置：操作时间
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
        return "ShangpinChuruInoutList{" +
            ", id=" + id +
            ", shangpinChuruInoutId=" + shangpinChuruInoutId +
            ", shangpinId=" + shangpinId +
            ", cangkuId=" + cangkuId +
            ", shangpinChuruInoutListNumber=" + shangpinChuruInoutListNumber +
            ", insertTime=" + DateUtil.convertString(insertTime,"yyyy-MM-dd") +
            ", createTime=" + DateUtil.convertString(createTime,"yyyy-MM-dd") +
        "}";
    }
}
