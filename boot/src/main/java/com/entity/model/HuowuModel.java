package com.entity.model;

import com.entity.HuowuEntity;

import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;
import java.io.Serializable;


/**
 * 货物
 * 接收传参的实体类
 *（实际开发中配合移动端接口开发手动去掉些没用的字段， 后端一般用entity就够用了）
 * 取自ModelAndView 的model名称
 */
public class HuowuModel implements Serializable {
    private static final long serialVersionUID = 1L;




    /**
     * 主键
     */
    private Integer id;


    /**
     * 商家
     */
    private Integer shangpinOrderId;


    /**
     * 货物名称
     */
    private String huowuName;


    /**
     * 货物编号
     */
    private String huowuUuidNumber;


    /**
     * 货物类型
     */
    private Integer huowuTypes;


    /**
     * 货物介绍
     */
    private String huowuContent;


    /**
     * 逻辑删除
     */
    private Integer huowuDelete;


    /**
     * 录入时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
    private Date insertTime;


    /**
     * 创建时间  show3 listShow
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
	 * 获取：商家
	 */
    public Integer getShangpinOrderId() {
        return shangpinOrderId;
    }


    /**
	 * 设置：商家
	 */
    public void setShangpinOrderId(Integer shangpinOrderId) {
        this.shangpinOrderId = shangpinOrderId;
    }
    /**
	 * 获取：货物名称
	 */
    public String getHuowuName() {
        return huowuName;
    }


    /**
	 * 设置：货物名称
	 */
    public void setHuowuName(String huowuName) {
        this.huowuName = huowuName;
    }
    /**
	 * 获取：货物编号
	 */
    public String getHuowuUuidNumber() {
        return huowuUuidNumber;
    }


    /**
	 * 设置：货物编号
	 */
    public void setHuowuUuidNumber(String huowuUuidNumber) {
        this.huowuUuidNumber = huowuUuidNumber;
    }
    /**
	 * 获取：货物类型
	 */
    public Integer getHuowuTypes() {
        return huowuTypes;
    }


    /**
	 * 设置：货物类型
	 */
    public void setHuowuTypes(Integer huowuTypes) {
        this.huowuTypes = huowuTypes;
    }
    /**
	 * 获取：货物介绍
	 */
    public String getHuowuContent() {
        return huowuContent;
    }


    /**
	 * 设置：货物介绍
	 */
    public void setHuowuContent(String huowuContent) {
        this.huowuContent = huowuContent;
    }
    /**
	 * 获取：逻辑删除
	 */
    public Integer getHuowuDelete() {
        return huowuDelete;
    }


    /**
	 * 设置：逻辑删除
	 */
    public void setHuowuDelete(Integer huowuDelete) {
        this.huowuDelete = huowuDelete;
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
	 * 获取：创建时间  show3 listShow
	 */
    public Date getCreateTime() {
        return createTime;
    }


    /**
	 * 设置：创建时间  show3 listShow
	 */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    }
