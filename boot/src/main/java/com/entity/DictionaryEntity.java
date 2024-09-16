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
 * 字典
 *
 * @author 
 * @email
 */
@TableName("dictionary")
public class DictionaryEntity<T> implements Serializable {
    private static final long serialVersionUID = 1L;


	public DictionaryEntity() {

	}

	public DictionaryEntity(T t) {
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
    @ColumnInfo(comment="主键",type="bigint(20)")
    @TableField(value = "id")

    private Long id;


    /**
     * 字段
     */
    @ColumnInfo(comment="字段",type="varchar(200)")
    @TableField(value = "dic_code")

    private String dicCode;


    /**
     * 字段名
     */
    @ColumnInfo(comment="字段名",type="varchar(200)")
    @TableField(value = "dic_name")

    private String dicName;


    /**
     * 编码
     */
    @ColumnInfo(comment="编码",type="int(11)")
    @TableField(value = "code_index")

    private Integer codeIndex;


    /**
     * 编码名字
     */
    @ColumnInfo(comment="编码名字",type="varchar(200)")
    @TableField(value = "index_name")

    private String indexName;


    /**
     * 父字段id
     */
    @ColumnInfo(comment="父字段id",type="int(11)")
    @TableField(value = "super_id")

    private Integer superId;


    /**
     * 备注
     */
    @ColumnInfo(comment="备注",type="varchar(200)")
    @TableField(value = "beizhu")

    private String beizhu;


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
    public Long getId() {
        return id;
    }
    /**
	 * 设置：主键
	 */

    public void setId(Long id) {
        this.id = id;
    }
    /**
	 * 获取：字段
	 */
    public String getDicCode() {
        return dicCode;
    }
    /**
	 * 设置：字段
	 */

    public void setDicCode(String dicCode) {
        this.dicCode = dicCode;
    }
    /**
	 * 获取：字段名
	 */
    public String getDicName() {
        return dicName;
    }
    /**
	 * 设置：字段名
	 */

    public void setDicName(String dicName) {
        this.dicName = dicName;
    }
    /**
	 * 获取：编码
	 */
    public Integer getCodeIndex() {
        return codeIndex;
    }
    /**
	 * 设置：编码
	 */

    public void setCodeIndex(Integer codeIndex) {
        this.codeIndex = codeIndex;
    }
    /**
	 * 获取：编码名字
	 */
    public String getIndexName() {
        return indexName;
    }
    /**
	 * 设置：编码名字
	 */

    public void setIndexName(String indexName) {
        this.indexName = indexName;
    }
    /**
	 * 获取：父字段id
	 */
    public Integer getSuperId() {
        return superId;
    }
    /**
	 * 设置：父字段id
	 */

    public void setSuperId(Integer superId) {
        this.superId = superId;
    }
    /**
	 * 获取：备注
	 */
    public String getBeizhu() {
        return beizhu;
    }
    /**
	 * 设置：备注
	 */

    public void setBeizhu(String beizhu) {
        this.beizhu = beizhu;
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
        return "Dictionary{" +
            ", id=" + id +
            ", dicCode=" + dicCode +
            ", dicName=" + dicName +
            ", codeIndex=" + codeIndex +
            ", indexName=" + indexName +
            ", superId=" + superId +
            ", beizhu=" + beizhu +
            ", createTime=" + DateUtil.convertString(createTime,"yyyy-MM-dd") +
        "}";
    }
}
