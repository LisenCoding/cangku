package com.entity.vo;

import com.entity.CheliangEntity;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;

/**
 * 车辆
 * 手机端接口返回实体辅助类
 * （主要作用去除一些不必要的字段）
 */
@TableName("cheliang")
public class CheliangVO implements Serializable {
    private static final long serialVersionUID = 1L;


    /**
     * 主键
     */

    @TableField(value = "id")
    private Integer id;


    /**
     * 车辆名称
     */

    @TableField(value = "cheliang_name")
    private String cheliangName;


    /**
     * 车辆编号
     */

    @TableField(value = "cheliang_uuid_number")
    private String cheliangUuidNumber;


    /**
     * 车辆照片
     */

    @TableField(value = "cheliang_photo")
    private String cheliangPhoto;


    /**
     * 车辆牌照
     */

    @TableField(value = "cheliang_paizhao")
    private String cheliangPaizhao;


    /**
     * 车辆类型
     */

    @TableField(value = "cheliang_types")
    private Integer cheliangTypes;


    /**
     * 车辆介绍
     */

    @TableField(value = "cheliang_content")
    private String cheliangContent;


    /**
     * 逻辑删除
     */

    @TableField(value = "cheliang_delete")
    private Integer cheliangDelete;


    /**
     * 录入时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat

    @TableField(value = "insert_time")
    private Date insertTime;


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
	 * 设置：车辆名称
	 */
    public String getCheliangName() {
        return cheliangName;
    }


    /**
	 * 获取：车辆名称
	 */

    public void setCheliangName(String cheliangName) {
        this.cheliangName = cheliangName;
    }
    /**
	 * 设置：车辆编号
	 */
    public String getCheliangUuidNumber() {
        return cheliangUuidNumber;
    }


    /**
	 * 获取：车辆编号
	 */

    public void setCheliangUuidNumber(String cheliangUuidNumber) {
        this.cheliangUuidNumber = cheliangUuidNumber;
    }
    /**
	 * 设置：车辆照片
	 */
    public String getCheliangPhoto() {
        return cheliangPhoto;
    }


    /**
	 * 获取：车辆照片
	 */

    public void setCheliangPhoto(String cheliangPhoto) {
        this.cheliangPhoto = cheliangPhoto;
    }
    /**
	 * 设置：车辆牌照
	 */
    public String getCheliangPaizhao() {
        return cheliangPaizhao;
    }


    /**
	 * 获取：车辆牌照
	 */

    public void setCheliangPaizhao(String cheliangPaizhao) {
        this.cheliangPaizhao = cheliangPaizhao;
    }
    /**
	 * 设置：车辆类型
	 */
    public Integer getCheliangTypes() {
        return cheliangTypes;
    }


    /**
	 * 获取：车辆类型
	 */

    public void setCheliangTypes(Integer cheliangTypes) {
        this.cheliangTypes = cheliangTypes;
    }
    /**
	 * 设置：车辆介绍
	 */
    public String getCheliangContent() {
        return cheliangContent;
    }


    /**
	 * 获取：车辆介绍
	 */

    public void setCheliangContent(String cheliangContent) {
        this.cheliangContent = cheliangContent;
    }
    /**
	 * 设置：逻辑删除
	 */
    public Integer getCheliangDelete() {
        return cheliangDelete;
    }


    /**
	 * 获取：逻辑删除
	 */

    public void setCheliangDelete(Integer cheliangDelete) {
        this.cheliangDelete = cheliangDelete;
    }
    /**
	 * 设置：录入时间
	 */
    public Date getInsertTime() {
        return insertTime;
    }


    /**
	 * 获取：录入时间
	 */

    public void setInsertTime(Date insertTime) {
        this.insertTime = insertTime;
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
