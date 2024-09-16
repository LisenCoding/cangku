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
 * 司机
 *
 * @author 
 * @email
 */
@TableName("siji")
public class SijiEntity<T> implements Serializable {
    private static final long serialVersionUID = 1L;


	public SijiEntity() {

	}

	public SijiEntity(T t) {
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
     * 司机编号
     */
    @ColumnInfo(comment="司机编号",type="varchar(200)")
    @TableField(value = "siji_uuid_number")

    private String sijiUuidNumber;


    /**
     * 司机姓名
     */
    @ColumnInfo(comment="司机姓名",type="varchar(200)")
    @TableField(value = "siji_name")

    private String sijiName;


    /**
     * 司机手机号
     */
    @ColumnInfo(comment="司机手机号",type="varchar(200)")
    @TableField(value = "siji_phone")

    private String sijiPhone;


    /**
     * 司机身份证号
     */
    @ColumnInfo(comment="司机身份证号",type="varchar(200)")
    @TableField(value = "siji_id_number")

    private String sijiIdNumber;


    /**
     * 司机头像
     */
    @ColumnInfo(comment="司机头像",type="varchar(200)")
    @TableField(value = "siji_photo")

    private String sijiPhoto;


    /**
     * 性别
     */
    @ColumnInfo(comment="性别",type="int(11)")
    @TableField(value = "sex_types")

    private Integer sexTypes;


    /**
     * 司机邮箱
     */
    @ColumnInfo(comment="司机邮箱",type="varchar(200)")
    @TableField(value = "siji_email")

    private String sijiEmail;


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
	 * 获取：司机编号
	 */
    public String getSijiUuidNumber() {
        return sijiUuidNumber;
    }
    /**
	 * 设置：司机编号
	 */

    public void setSijiUuidNumber(String sijiUuidNumber) {
        this.sijiUuidNumber = sijiUuidNumber;
    }
    /**
	 * 获取：司机姓名
	 */
    public String getSijiName() {
        return sijiName;
    }
    /**
	 * 设置：司机姓名
	 */

    public void setSijiName(String sijiName) {
        this.sijiName = sijiName;
    }
    /**
	 * 获取：司机手机号
	 */
    public String getSijiPhone() {
        return sijiPhone;
    }
    /**
	 * 设置：司机手机号
	 */

    public void setSijiPhone(String sijiPhone) {
        this.sijiPhone = sijiPhone;
    }
    /**
	 * 获取：司机身份证号
	 */
    public String getSijiIdNumber() {
        return sijiIdNumber;
    }
    /**
	 * 设置：司机身份证号
	 */

    public void setSijiIdNumber(String sijiIdNumber) {
        this.sijiIdNumber = sijiIdNumber;
    }
    /**
	 * 获取：司机头像
	 */
    public String getSijiPhoto() {
        return sijiPhoto;
    }
    /**
	 * 设置：司机头像
	 */

    public void setSijiPhoto(String sijiPhoto) {
        this.sijiPhoto = sijiPhoto;
    }
    /**
	 * 获取：性别
	 */
    public Integer getSexTypes() {
        return sexTypes;
    }
    /**
	 * 设置：性别
	 */

    public void setSexTypes(Integer sexTypes) {
        this.sexTypes = sexTypes;
    }
    /**
	 * 获取：司机邮箱
	 */
    public String getSijiEmail() {
        return sijiEmail;
    }
    /**
	 * 设置：司机邮箱
	 */

    public void setSijiEmail(String sijiEmail) {
        this.sijiEmail = sijiEmail;
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
        return "Siji{" +
            ", id=" + id +
            ", sijiUuidNumber=" + sijiUuidNumber +
            ", sijiName=" + sijiName +
            ", sijiPhone=" + sijiPhone +
            ", sijiIdNumber=" + sijiIdNumber +
            ", sijiPhoto=" + sijiPhoto +
            ", sexTypes=" + sexTypes +
            ", sijiEmail=" + sijiEmail +
            ", createTime=" + DateUtil.convertString(createTime,"yyyy-MM-dd") +
        "}";
    }
}
