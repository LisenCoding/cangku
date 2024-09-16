package com.entity.vo;

import com.entity.ShangpinChuruInoutListEntity;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;

/**
 * 出入库详情
 * 手机端接口返回实体辅助类
 * （主要作用去除一些不必要的字段）
 */
@TableName("shangpin_churu_inout_list")
public class ShangpinChuruInoutListVO implements Serializable {
    private static final long serialVersionUID = 1L;


    /**
     * 主键
     */

    @TableField(value = "id")
    private Integer id;


    /**
     * 出入库
     */

    @TableField(value = "shangpin_churu_inout_id")
    private Integer shangpinChuruInoutId;


    /**
     * 商品
     */

    @TableField(value = "shangpin_id")
    private Integer shangpinId;


    /**
     * 仓库
     */

    @TableField(value = "cangku_id")
    private Integer cangkuId;


    /**
     * 操作数量
     */

    @TableField(value = "shangpin_churu_inout_list_number")
    private Integer shangpinChuruInoutListNumber;


    /**
     * 操作时间
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
	 * 设置：出入库
	 */
    public Integer getShangpinChuruInoutId() {
        return shangpinChuruInoutId;
    }


    /**
	 * 获取：出入库
	 */

    public void setShangpinChuruInoutId(Integer shangpinChuruInoutId) {
        this.shangpinChuruInoutId = shangpinChuruInoutId;
    }
    /**
	 * 设置：商品
	 */
    public Integer getShangpinId() {
        return shangpinId;
    }


    /**
	 * 获取：商品
	 */

    public void setShangpinId(Integer shangpinId) {
        this.shangpinId = shangpinId;
    }
    /**
	 * 设置：仓库
	 */
    public Integer getCangkuId() {
        return cangkuId;
    }


    /**
	 * 获取：仓库
	 */

    public void setCangkuId(Integer cangkuId) {
        this.cangkuId = cangkuId;
    }
    /**
	 * 设置：操作数量
	 */
    public Integer getShangpinChuruInoutListNumber() {
        return shangpinChuruInoutListNumber;
    }


    /**
	 * 获取：操作数量
	 */

    public void setShangpinChuruInoutListNumber(Integer shangpinChuruInoutListNumber) {
        this.shangpinChuruInoutListNumber = shangpinChuruInoutListNumber;
    }
    /**
	 * 设置：操作时间
	 */
    public Date getInsertTime() {
        return insertTime;
    }


    /**
	 * 获取：操作时间
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
