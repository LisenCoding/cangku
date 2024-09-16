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
 * 商品订单
 *
 * @author 
 * @email
 */
@TableName("shangpin_order")
public class ShangpinOrderEntity<T> implements Serializable {
    private static final long serialVersionUID = 1L;


	public ShangpinOrderEntity() {

	}

	public ShangpinOrderEntity(T t) {
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
     * 订单编号
     */
    @ColumnInfo(comment="订单编号",type="varchar(200)")
    @TableField(value = "shangpin_order_uuid_number")

    private String shangpinOrderUuidNumber;


    /**
     * 收货地址
     */
    @ColumnInfo(comment="收货地址",type="int(11)")
    @TableField(value = "address_id")

    private Integer addressId;


    /**
     * 商品
     */
    @ColumnInfo(comment="商品",type="int(11)")
    @TableField(value = "shangpin_id")

    private Integer shangpinId;


    /**
     * 车辆
     */
    @ColumnInfo(comment="车辆",type="int(11)")
    @TableField(value = "cheliang_id")

    private Integer cheliangId;


    /**
     * 司机
     */
    @ColumnInfo(comment="司机",type="int(11)")
    @TableField(value = "siji_id")

    private Integer sijiId;


    /**
     * 用户
     */
    @ColumnInfo(comment="用户",type="int(11)")
    @TableField(value = "yonghu_id")

    private Integer yonghuId;


    /**
     * 购买数量
     */
    @ColumnInfo(comment="购买数量",type="int(11)")
    @TableField(value = "buy_number")

    private Integer buyNumber;


    /**
     * 实付价格
     */
    @ColumnInfo(comment="实付价格",type="decimal(10,2)")
    @TableField(value = "shangpin_order_true_price")

    private Double shangpinOrderTruePrice;


    /**
     * 订单类型
     */
    @ColumnInfo(comment="订单类型",type="int(11)")
    @TableField(value = "shangpin_order_types")

    private Integer shangpinOrderTypes;


    /**
     * 订单创建时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
    @ColumnInfo(comment="订单创建时间",type="timestamp")
    @TableField(value = "insert_time",fill = FieldFill.INSERT)

    private Date insertTime;


    /**
     * 创建时间  listShow
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
	 * 获取：订单编号
	 */
    public String getShangpinOrderUuidNumber() {
        return shangpinOrderUuidNumber;
    }
    /**
	 * 设置：订单编号
	 */

    public void setShangpinOrderUuidNumber(String shangpinOrderUuidNumber) {
        this.shangpinOrderUuidNumber = shangpinOrderUuidNumber;
    }
    /**
	 * 获取：收货地址
	 */
    public Integer getAddressId() {
        return addressId;
    }
    /**
	 * 设置：收货地址
	 */

    public void setAddressId(Integer addressId) {
        this.addressId = addressId;
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
	 * 获取：车辆
	 */
    public Integer getCheliangId() {
        return cheliangId;
    }
    /**
	 * 设置：车辆
	 */

    public void setCheliangId(Integer cheliangId) {
        this.cheliangId = cheliangId;
    }
    /**
	 * 获取：司机
	 */
    public Integer getSijiId() {
        return sijiId;
    }
    /**
	 * 设置：司机
	 */

    public void setSijiId(Integer sijiId) {
        this.sijiId = sijiId;
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
	 * 获取：购买数量
	 */
    public Integer getBuyNumber() {
        return buyNumber;
    }
    /**
	 * 设置：购买数量
	 */

    public void setBuyNumber(Integer buyNumber) {
        this.buyNumber = buyNumber;
    }
    /**
	 * 获取：实付价格
	 */
    public Double getShangpinOrderTruePrice() {
        return shangpinOrderTruePrice;
    }
    /**
	 * 设置：实付价格
	 */

    public void setShangpinOrderTruePrice(Double shangpinOrderTruePrice) {
        this.shangpinOrderTruePrice = shangpinOrderTruePrice;
    }
    /**
	 * 获取：订单类型
	 */
    public Integer getShangpinOrderTypes() {
        return shangpinOrderTypes;
    }
    /**
	 * 设置：订单类型
	 */

    public void setShangpinOrderTypes(Integer shangpinOrderTypes) {
        this.shangpinOrderTypes = shangpinOrderTypes;
    }
    /**
	 * 获取：订单创建时间
	 */
    public Date getInsertTime() {
        return insertTime;
    }
    /**
	 * 设置：订单创建时间
	 */

    public void setInsertTime(Date insertTime) {
        this.insertTime = insertTime;
    }
    /**
	 * 获取：创建时间  listShow
	 */
    public Date getCreateTime() {
        return createTime;
    }
    /**
	 * 设置：创建时间  listShow
	 */

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "ShangpinOrder{" +
            ", id=" + id +
            ", shangpinOrderUuidNumber=" + shangpinOrderUuidNumber +
            ", addressId=" + addressId +
            ", shangpinId=" + shangpinId +
            ", cheliangId=" + cheliangId +
            ", sijiId=" + sijiId +
            ", yonghuId=" + yonghuId +
            ", buyNumber=" + buyNumber +
            ", shangpinOrderTruePrice=" + shangpinOrderTruePrice +
            ", shangpinOrderTypes=" + shangpinOrderTypes +
            ", insertTime=" + DateUtil.convertString(insertTime,"yyyy-MM-dd") +
            ", createTime=" + DateUtil.convertString(createTime,"yyyy-MM-dd") +
        "}";
    }
}
