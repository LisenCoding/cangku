package com.entity.vo;

import com.entity.YuangongEntity;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;

/**
 * 员工
 * 手机端接口返回实体辅助类
 * （主要作用去除一些不必要的字段）
 */
@TableName("yuangong")
public class YuangongVO implements Serializable {
    private static final long serialVersionUID = 1L;


    /**
     * 主键
     */

    @TableField(value = "id")
    private Integer id;


    /**
     * 账户
     */

    @TableField(value = "username")
    private String username;


    /**
     * 密码
     */

    @TableField(value = "password")
    private String password;


    /**
     * 员工编号
     */

    @TableField(value = "yuangong_uuid_number")
    private String yuangongUuidNumber;


    /**
     * 员工姓名
     */

    @TableField(value = "yuangong_name")
    private String yuangongName;


    /**
     * 员工手机号
     */

    @TableField(value = "yuangong_phone")
    private String yuangongPhone;


    /**
     * 员工身份证号
     */

    @TableField(value = "yuangong_id_number")
    private String yuangongIdNumber;


    /**
     * 员工头像
     */

    @TableField(value = "yuangong_photo")
    private String yuangongPhoto;


    /**
     * 性别
     */

    @TableField(value = "sex_types")
    private Integer sexTypes;


    /**
     * 员工邮箱
     */

    @TableField(value = "yuangong_email")
    private String yuangongEmail;


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
	 * 设置：账户
	 */
    public String getUsername() {
        return username;
    }


    /**
	 * 获取：账户
	 */

    public void setUsername(String username) {
        this.username = username;
    }
    /**
	 * 设置：密码
	 */
    public String getPassword() {
        return password;
    }


    /**
	 * 获取：密码
	 */

    public void setPassword(String password) {
        this.password = password;
    }
    /**
	 * 设置：员工编号
	 */
    public String getYuangongUuidNumber() {
        return yuangongUuidNumber;
    }


    /**
	 * 获取：员工编号
	 */

    public void setYuangongUuidNumber(String yuangongUuidNumber) {
        this.yuangongUuidNumber = yuangongUuidNumber;
    }
    /**
	 * 设置：员工姓名
	 */
    public String getYuangongName() {
        return yuangongName;
    }


    /**
	 * 获取：员工姓名
	 */

    public void setYuangongName(String yuangongName) {
        this.yuangongName = yuangongName;
    }
    /**
	 * 设置：员工手机号
	 */
    public String getYuangongPhone() {
        return yuangongPhone;
    }


    /**
	 * 获取：员工手机号
	 */

    public void setYuangongPhone(String yuangongPhone) {
        this.yuangongPhone = yuangongPhone;
    }
    /**
	 * 设置：员工身份证号
	 */
    public String getYuangongIdNumber() {
        return yuangongIdNumber;
    }


    /**
	 * 获取：员工身份证号
	 */

    public void setYuangongIdNumber(String yuangongIdNumber) {
        this.yuangongIdNumber = yuangongIdNumber;
    }
    /**
	 * 设置：员工头像
	 */
    public String getYuangongPhoto() {
        return yuangongPhoto;
    }


    /**
	 * 获取：员工头像
	 */

    public void setYuangongPhoto(String yuangongPhoto) {
        this.yuangongPhoto = yuangongPhoto;
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
	 * 设置：员工邮箱
	 */
    public String getYuangongEmail() {
        return yuangongEmail;
    }


    /**
	 * 获取：员工邮箱
	 */

    public void setYuangongEmail(String yuangongEmail) {
        this.yuangongEmail = yuangongEmail;
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
