package com.entity.model;

import com.entity.CheliangEntity;

import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;
import java.io.Serializable;


/**
 * 车辆
 * 接收传参的实体类
 *（实际开发中配合移动端接口开发手动去掉些没用的字段， 后端一般用entity就够用了）
 * 取自ModelAndView 的model名称
 */
public class CheliangModel implements Serializable {
    private static final long serialVersionUID = 1L;




    /**
     * 主键
     */
    private Integer id;


    /**
     * 车辆名称
     */
    private String cheliangName;


    /**
     * 车辆编号
     */
    private String cheliangUuidNumber;


    /**
     * 车辆照片
     */
    private String cheliangPhoto;


    /**
     * 车辆牌照
     */
    private String cheliangPaizhao;


    /**
     * 车辆类型
     */
    private Integer cheliangTypes;


    /**
     * 车辆介绍
     */
    private String cheliangContent;


    /**
     * 逻辑删除
     */
    private Integer cheliangDelete;


    /**
     * 录入时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
    private Date insertTime;


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
	 * 获取：车辆名称
	 */
    public String getCheliangName() {
        return cheliangName;
    }


    /**
	 * 设置：车辆名称
	 */
    public void setCheliangName(String cheliangName) {
        this.cheliangName = cheliangName;
    }
    /**
	 * 获取：车辆编号
	 */
    public String getCheliangUuidNumber() {
        return cheliangUuidNumber;
    }


    /**
	 * 设置：车辆编号
	 */
    public void setCheliangUuidNumber(String cheliangUuidNumber) {
        this.cheliangUuidNumber = cheliangUuidNumber;
    }
    /**
	 * 获取：车辆照片
	 */
    public String getCheliangPhoto() {
        return cheliangPhoto;
    }


    /**
	 * 设置：车辆照片
	 */
    public void setCheliangPhoto(String cheliangPhoto) {
        this.cheliangPhoto = cheliangPhoto;
    }
    /**
	 * 获取：车辆牌照
	 */
    public String getCheliangPaizhao() {
        return cheliangPaizhao;
    }


    /**
	 * 设置：车辆牌照
	 */
    public void setCheliangPaizhao(String cheliangPaizhao) {
        this.cheliangPaizhao = cheliangPaizhao;
    }
    /**
	 * 获取：车辆类型
	 */
    public Integer getCheliangTypes() {
        return cheliangTypes;
    }


    /**
	 * 设置：车辆类型
	 */
    public void setCheliangTypes(Integer cheliangTypes) {
        this.cheliangTypes = cheliangTypes;
    }
    /**
	 * 获取：车辆介绍
	 */
    public String getCheliangContent() {
        return cheliangContent;
    }


    /**
	 * 设置：车辆介绍
	 */
    public void setCheliangContent(String cheliangContent) {
        this.cheliangContent = cheliangContent;
    }
    /**
	 * 获取：逻辑删除
	 */
    public Integer getCheliangDelete() {
        return cheliangDelete;
    }


    /**
	 * 设置：逻辑删除
	 */
    public void setCheliangDelete(Integer cheliangDelete) {
        this.cheliangDelete = cheliangDelete;
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
