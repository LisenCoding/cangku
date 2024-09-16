package com.entity.model;

import com.entity.AddressEntity;

import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;
import java.io.Serializable;


/**
 * 收货地址
 * 接收传参的实体类
 *（实际开发中配合移动端接口开发手动去掉些没用的字段， 后端一般用entity就够用了）
 * 取自ModelAndView 的model名称
 */
public class AddressModel implements Serializable {
    private static final long serialVersionUID = 1L;




    /**
     * 主键
     */
    private Integer id;


    /**
     * 创建用户
     */
    private Integer yonghuId;


    /**
     * 收货人
     */
    private String addressName;


    /**
     * 电话
     */
    private String addressPhone;


    /**
     * 地址
     */
    private String addressDizhi;


    /**
     * 是否默认地址
     */
    private Integer isdefaultTypes;


    /**
     * 添加时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
    private Date insertTime;


    /**
     * 修改时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
    private Date updateTime;


    /**
     * 创建时间 show3
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
	 * 获取：创建用户
	 */
    public Integer getYonghuId() {
        return yonghuId;
    }


    /**
	 * 设置：创建用户
	 */
    public void setYonghuId(Integer yonghuId) {
        this.yonghuId = yonghuId;
    }
    /**
	 * 获取：收货人
	 */
    public String getAddressName() {
        return addressName;
    }


    /**
	 * 设置：收货人
	 */
    public void setAddressName(String addressName) {
        this.addressName = addressName;
    }
    /**
	 * 获取：电话
	 */
    public String getAddressPhone() {
        return addressPhone;
    }


    /**
	 * 设置：电话
	 */
    public void setAddressPhone(String addressPhone) {
        this.addressPhone = addressPhone;
    }
    /**
	 * 获取：地址
	 */
    public String getAddressDizhi() {
        return addressDizhi;
    }


    /**
	 * 设置：地址
	 */
    public void setAddressDizhi(String addressDizhi) {
        this.addressDizhi = addressDizhi;
    }
    /**
	 * 获取：是否默认地址
	 */
    public Integer getIsdefaultTypes() {
        return isdefaultTypes;
    }


    /**
	 * 设置：是否默认地址
	 */
    public void setIsdefaultTypes(Integer isdefaultTypes) {
        this.isdefaultTypes = isdefaultTypes;
    }
    /**
	 * 获取：添加时间
	 */
    public Date getInsertTime() {
        return insertTime;
    }


    /**
	 * 设置：添加时间
	 */
    public void setInsertTime(Date insertTime) {
        this.insertTime = insertTime;
    }
    /**
	 * 获取：修改时间
	 */
    public Date getUpdateTime() {
        return updateTime;
    }


    /**
	 * 设置：修改时间
	 */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
    /**
	 * 获取：创建时间 show3
	 */
    public Date getCreateTime() {
        return createTime;
    }


    /**
	 * 设置：创建时间 show3
	 */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    }
