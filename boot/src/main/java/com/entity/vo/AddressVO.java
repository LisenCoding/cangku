package com.entity.vo;

import com.entity.AddressEntity;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;

/**
 * 收货地址
 * 手机端接口返回实体辅助类
 * （主要作用去除一些不必要的字段）
 */
@TableName("address")
public class AddressVO implements Serializable {
    private static final long serialVersionUID = 1L;


    /**
     * 主键
     */

    @TableField(value = "id")
    private Integer id;


    /**
     * 创建用户
     */

    @TableField(value = "yonghu_id")
    private Integer yonghuId;


    /**
     * 收货人
     */

    @TableField(value = "address_name")
    private String addressName;


    /**
     * 电话
     */

    @TableField(value = "address_phone")
    private String addressPhone;


    /**
     * 地址
     */

    @TableField(value = "address_dizhi")
    private String addressDizhi;


    /**
     * 是否默认地址
     */

    @TableField(value = "isdefault_types")
    private Integer isdefaultTypes;


    /**
     * 添加时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat

    @TableField(value = "insert_time")
    private Date insertTime;


    /**
     * 修改时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat

    @TableField(value = "update_time")
    private Date updateTime;


    /**
     * 创建时间 show3
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat

    @TableField(value = "create_time")
    private Date createTime;


    /**
	 * 设置：主键
	 */
    public Integer getId() {
        return id;
    }


    /**
	 * 获取：主键
	 */

    public void setId(Integer id) {
        this.id = id;
    }
    /**
	 * 设置：创建用户
	 */
    public Integer getYonghuId() {
        return yonghuId;
    }


    /**
	 * 获取：创建用户
	 */

    public void setYonghuId(Integer yonghuId) {
        this.yonghuId = yonghuId;
    }
    /**
	 * 设置：收货人
	 */
    public String getAddressName() {
        return addressName;
    }


    /**
	 * 获取：收货人
	 */

    public void setAddressName(String addressName) {
        this.addressName = addressName;
    }
    /**
	 * 设置：电话
	 */
    public String getAddressPhone() {
        return addressPhone;
    }


    /**
	 * 获取：电话
	 */

    public void setAddressPhone(String addressPhone) {
        this.addressPhone = addressPhone;
    }
    /**
	 * 设置：地址
	 */
    public String getAddressDizhi() {
        return addressDizhi;
    }


    /**
	 * 获取：地址
	 */

    public void setAddressDizhi(String addressDizhi) {
        this.addressDizhi = addressDizhi;
    }
    /**
	 * 设置：是否默认地址
	 */
    public Integer getIsdefaultTypes() {
        return isdefaultTypes;
    }


    /**
	 * 获取：是否默认地址
	 */

    public void setIsdefaultTypes(Integer isdefaultTypes) {
        this.isdefaultTypes = isdefaultTypes;
    }
    /**
	 * 设置：添加时间
	 */
    public Date getInsertTime() {
        return insertTime;
    }


    /**
	 * 获取：添加时间
	 */

    public void setInsertTime(Date insertTime) {
        this.insertTime = insertTime;
    }
    /**
	 * 设置：修改时间
	 */
    public Date getUpdateTime() {
        return updateTime;
    }


    /**
	 * 获取：修改时间
	 */

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
    /**
	 * 设置：创建时间 show3
	 */
    public Date getCreateTime() {
        return createTime;
    }


    /**
	 * 获取：创建时间 show3
	 */

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

}
