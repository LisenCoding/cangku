package com.entity.model;

import com.entity.YuangongEntity;

import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;
import java.io.Serializable;


/**
 * 员工
 * 接收传参的实体类
 *（实际开发中配合移动端接口开发手动去掉些没用的字段， 后端一般用entity就够用了）
 * 取自ModelAndView 的model名称
 */
public class YuangongModel implements Serializable {
    private static final long serialVersionUID = 1L;




    /**
     * 主键
     */
    private Integer id;


    /**
     * 账户
     */
    private String username;


    /**
     * 密码
     */
    private String password;


    /**
     * 员工编号
     */
    private String yuangongUuidNumber;


    /**
     * 员工姓名
     */
    private String yuangongName;


    /**
     * 员工手机号
     */
    private String yuangongPhone;


    /**
     * 员工身份证号
     */
    private String yuangongIdNumber;


    /**
     * 员工头像
     */
    private String yuangongPhoto;


    /**
     * 性别
     */
    private Integer sexTypes;


    /**
     * 员工邮箱
     */
    private String yuangongEmail;


    /**
     * 创建时间
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
	 * 获取：账户
	 */
    public String getUsername() {
        return username;
    }


    /**
	 * 设置：账户
	 */
    public void setUsername(String username) {
        this.username = username;
    }
    /**
	 * 获取：密码
	 */
    public String getPassword() {
        return password;
    }


    /**
	 * 设置：密码
	 */
    public void setPassword(String password) {
        this.password = password;
    }
    /**
	 * 获取：员工编号
	 */
    public String getYuangongUuidNumber() {
        return yuangongUuidNumber;
    }


    /**
	 * 设置：员工编号
	 */
    public void setYuangongUuidNumber(String yuangongUuidNumber) {
        this.yuangongUuidNumber = yuangongUuidNumber;
    }
    /**
	 * 获取：员工姓名
	 */
    public String getYuangongName() {
        return yuangongName;
    }


    /**
	 * 设置：员工姓名
	 */
    public void setYuangongName(String yuangongName) {
        this.yuangongName = yuangongName;
    }
    /**
	 * 获取：员工手机号
	 */
    public String getYuangongPhone() {
        return yuangongPhone;
    }


    /**
	 * 设置：员工手机号
	 */
    public void setYuangongPhone(String yuangongPhone) {
        this.yuangongPhone = yuangongPhone;
    }
    /**
	 * 获取：员工身份证号
	 */
    public String getYuangongIdNumber() {
        return yuangongIdNumber;
    }


    /**
	 * 设置：员工身份证号
	 */
    public void setYuangongIdNumber(String yuangongIdNumber) {
        this.yuangongIdNumber = yuangongIdNumber;
    }
    /**
	 * 获取：员工头像
	 */
    public String getYuangongPhoto() {
        return yuangongPhoto;
    }


    /**
	 * 设置：员工头像
	 */
    public void setYuangongPhoto(String yuangongPhoto) {
        this.yuangongPhoto = yuangongPhoto;
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
	 * 获取：员工邮箱
	 */
    public String getYuangongEmail() {
        return yuangongEmail;
    }


    /**
	 * 设置：员工邮箱
	 */
    public void setYuangongEmail(String yuangongEmail) {
        this.yuangongEmail = yuangongEmail;
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

    }
