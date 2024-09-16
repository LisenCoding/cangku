package com.entity.vo;

import com.entity.HuowuEntity;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;

/**
 * 货物
 * 手机端接口返回实体辅助类
 * （主要作用去除一些不必要的字段）
 */
@TableName("huowu")
public class HuowuVO implements Serializable {
    private static final long serialVersionUID = 1L;


    /**
     * 主键
     */

    @TableField(value = "id")
    private Integer id;


    /**
     * 商家
     */

    @TableField(value = "shangpin_order_id")
    private Integer shangpinOrderId;


    /**
     * 货物名称
     */

    @TableField(value = "huowu_name")
    private String huowuName;


    /**
     * 货物编号
     */

    @TableField(value = "huowu_uuid_number")
    private String huowuUuidNumber;


    /**
     * 货物类型
     */

    @TableField(value = "huowu_types")
    private Integer huowuTypes;


    /**
     * 货物介绍
     */

    @TableField(value = "huowu_content")
    private String huowuContent;


    /**
     * 逻辑删除
     */

    @TableField(value = "huowu_delete")
    private Integer huowuDelete;


    /**
     * 录入时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat

    @TableField(value = "insert_time")
    private Date insertTime;


    /**
     * 创建时间  show3 listShow
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
	 * 设置：商家
	 */
    public Integer getShangpinOrderId() {
        return shangpinOrderId;
    }


    /**
	 * 获取：商家
	 */

    public void setShangpinOrderId(Integer shangpinOrderId) {
        this.shangpinOrderId = shangpinOrderId;
    }
    /**
	 * 设置：货物名称
	 */
    public String getHuowuName() {
        return huowuName;
    }


    /**
	 * 获取：货物名称
	 */

    public void setHuowuName(String huowuName) {
        this.huowuName = huowuName;
    }
    /**
	 * 设置：货物编号
	 */
    public String getHuowuUuidNumber() {
        return huowuUuidNumber;
    }


    /**
	 * 获取：货物编号
	 */

    public void setHuowuUuidNumber(String huowuUuidNumber) {
        this.huowuUuidNumber = huowuUuidNumber;
    }
    /**
	 * 设置：货物类型
	 */
    public Integer getHuowuTypes() {
        return huowuTypes;
    }


    /**
	 * 获取：货物类型
	 */

    public void setHuowuTypes(Integer huowuTypes) {
        this.huowuTypes = huowuTypes;
    }
    /**
	 * 设置：货物介绍
	 */
    public String getHuowuContent() {
        return huowuContent;
    }


    /**
	 * 获取：货物介绍
	 */

    public void setHuowuContent(String huowuContent) {
        this.huowuContent = huowuContent;
    }
    /**
	 * 设置：逻辑删除
	 */
    public Integer getHuowuDelete() {
        return huowuDelete;
    }


    /**
	 * 获取：逻辑删除
	 */

    public void setHuowuDelete(Integer huowuDelete) {
        this.huowuDelete = huowuDelete;
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
	 * 设置：创建时间  show3 listShow
	 */
    public Date getCreateTime() {
        return createTime;
    }


    /**
	 * 获取：创建时间  show3 listShow
	 */

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

}
