package com.entity.vo;

import com.entity.CangkuEntity;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;

/**
 * 仓库
 * 手机端接口返回实体辅助类
 * （主要作用去除一些不必要的字段）
 */
@TableName("cangku")
public class CangkuVO implements Serializable {
    private static final long serialVersionUID = 1L;


    /**
     * 主键
     */

    @TableField(value = "id")
    private Integer id;


    /**
     * 仓库名称
     */

    @TableField(value = "cangku_name")
    private String cangkuName;


    /**
     * 仓库编号
     */

    @TableField(value = "cangku_uuid_number")
    private String cangkuUuidNumber;


    /**
     * 仓库照片
     */

    @TableField(value = "cangku_photo")
    private String cangkuPhoto;


    /**
     * 仓库地点
     */

    @TableField(value = "cangku_address")
    private String cangkuAddress;


    /**
     * 仓库类型
     */

    @TableField(value = "cangku_types")
    private Integer cangkuTypes;


    /**
     * 仓库介绍
     */

    @TableField(value = "cangku_content")
    private String cangkuContent;


    /**
     * 逻辑删除
     */

    @TableField(value = "cangku_delete")
    private Integer cangkuDelete;


    /**
     * 录入时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat

    @TableField(value = "insert_time")
    private Date insertTime;


    /**
     * 创建时间  show1 show2 photoShow
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
	 * 设置：仓库名称
	 */
    public String getCangkuName() {
        return cangkuName;
    }


    /**
	 * 获取：仓库名称
	 */

    public void setCangkuName(String cangkuName) {
        this.cangkuName = cangkuName;
    }
    /**
	 * 设置：仓库编号
	 */
    public String getCangkuUuidNumber() {
        return cangkuUuidNumber;
    }


    /**
	 * 获取：仓库编号
	 */

    public void setCangkuUuidNumber(String cangkuUuidNumber) {
        this.cangkuUuidNumber = cangkuUuidNumber;
    }
    /**
	 * 设置：仓库照片
	 */
    public String getCangkuPhoto() {
        return cangkuPhoto;
    }


    /**
	 * 获取：仓库照片
	 */

    public void setCangkuPhoto(String cangkuPhoto) {
        this.cangkuPhoto = cangkuPhoto;
    }
    /**
	 * 设置：仓库地点
	 */
    public String getCangkuAddress() {
        return cangkuAddress;
    }


    /**
	 * 获取：仓库地点
	 */

    public void setCangkuAddress(String cangkuAddress) {
        this.cangkuAddress = cangkuAddress;
    }
    /**
	 * 设置：仓库类型
	 */
    public Integer getCangkuTypes() {
        return cangkuTypes;
    }


    /**
	 * 获取：仓库类型
	 */

    public void setCangkuTypes(Integer cangkuTypes) {
        this.cangkuTypes = cangkuTypes;
    }
    /**
	 * 设置：仓库介绍
	 */
    public String getCangkuContent() {
        return cangkuContent;
    }


    /**
	 * 获取：仓库介绍
	 */

    public void setCangkuContent(String cangkuContent) {
        this.cangkuContent = cangkuContent;
    }
    /**
	 * 设置：逻辑删除
	 */
    public Integer getCangkuDelete() {
        return cangkuDelete;
    }


    /**
	 * 获取：逻辑删除
	 */

    public void setCangkuDelete(Integer cangkuDelete) {
        this.cangkuDelete = cangkuDelete;
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
	 * 设置：创建时间  show1 show2 photoShow
	 */
    public Date getCreateTime() {
        return createTime;
    }


    /**
	 * 获取：创建时间  show1 show2 photoShow
	 */

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

}
