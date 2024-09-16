package com.entity.vo;

import com.entity.SijiEntity;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;

/**
 * 司机
 * 手机端接口返回实体辅助类
 * （主要作用去除一些不必要的字段）
 */
@TableName("siji")
public class SijiVO implements Serializable {
    private static final long serialVersionUID = 1L;


    /**
     * 主键
     */

    @TableField(value = "id")
    private Integer id;


    /**
     * 司机编号
     */

    @TableField(value = "siji_uuid_number")
    private String sijiUuidNumber;


    /**
     * 司机姓名
     */

    @TableField(value = "siji_name")
    private String sijiName;


    /**
     * 司机手机号
     */

    @TableField(value = "siji_phone")
    private String sijiPhone;


    /**
     * 司机身份证号
     */

    @TableField(value = "siji_id_number")
    private String sijiIdNumber;


    /**
     * 司机头像
     */

    @TableField(value = "siji_photo")
    private String sijiPhoto;


    /**
     * 性别
     */

    @TableField(value = "sex_types")
    private Integer sexTypes;


    /**
     * 司机邮箱
     */

    @TableField(value = "siji_email")
    private String sijiEmail;


    /**
     * 创建时间
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
	 * 设置：司机编号
	 */
    public String getSijiUuidNumber() {
        return sijiUuidNumber;
    }


    /**
	 * 获取：司机编号
	 */

    public void setSijiUuidNumber(String sijiUuidNumber) {
        this.sijiUuidNumber = sijiUuidNumber;
    }
    /**
	 * 设置：司机姓名
	 */
    public String getSijiName() {
        return sijiName;
    }


    /**
	 * 获取：司机姓名
	 */

    public void setSijiName(String sijiName) {
        this.sijiName = sijiName;
    }
    /**
	 * 设置：司机手机号
	 */
    public String getSijiPhone() {
        return sijiPhone;
    }


    /**
	 * 获取：司机手机号
	 */

    public void setSijiPhone(String sijiPhone) {
        this.sijiPhone = sijiPhone;
    }
    /**
	 * 设置：司机身份证号
	 */
    public String getSijiIdNumber() {
        return sijiIdNumber;
    }


    /**
	 * 获取：司机身份证号
	 */

    public void setSijiIdNumber(String sijiIdNumber) {
        this.sijiIdNumber = sijiIdNumber;
    }
    /**
	 * 设置：司机头像
	 */
    public String getSijiPhoto() {
        return sijiPhoto;
    }


    /**
	 * 获取：司机头像
	 */

    public void setSijiPhoto(String sijiPhoto) {
        this.sijiPhoto = sijiPhoto;
    }
    /**
	 * 设置：性别
	 */
    public Integer getSexTypes() {
        return sexTypes;
    }


    /**
	 * 获取：性别
	 */

    public void setSexTypes(Integer sexTypes) {
        this.sexTypes = sexTypes;
    }
    /**
	 * 设置：司机邮箱
	 */
    public String getSijiEmail() {
        return sijiEmail;
    }


    /**
	 * 获取：司机邮箱
	 */

    public void setSijiEmail(String sijiEmail) {
        this.sijiEmail = sijiEmail;
    }
    /**
	 * 设置：创建时间
	 */
    public Date getCreateTime() {
        return createTime;
    }


    /**
	 * 获取：创建时间
	 */

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

}
