package com.entity.view;

import org.apache.tools.ant.util.DateUtils;
import com.annotation.ColumnInfo;
import com.entity.CangkuEntity;
import com.baomidou.mybatisplus.annotations.TableName;
import org.apache.commons.beanutils.BeanUtils;
import java.lang.reflect.InvocationTargetException;
import org.springframework.format.annotation.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.io.Serializable;
import java.util.Date;
import com.utils.DateUtil;

/**
* 仓库
* 后端返回视图实体辅助类
* （通常后端关联的表或者自定义的字段需要返回使用）
*/
@TableName("cangku")
public class CangkuView extends CangkuEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	//当前表
	/**
	* 仓库类型的值
	*/
	@ColumnInfo(comment="仓库类型的字典表值",type="varchar(200)")
	private String cangkuValue;




	public CangkuView() {

	}

	public CangkuView(CangkuEntity cangkuEntity) {
		try {
			BeanUtils.copyProperties(this, cangkuEntity);
		} catch (IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}



	//当前表的
	/**
	* 获取： 仓库类型的值
	*/
	public String getCangkuValue() {
		return cangkuValue;
	}
	/**
	* 设置： 仓库类型的值
	*/
	public void setCangkuValue(String cangkuValue) {
		this.cangkuValue = cangkuValue;
	}




	@Override
	public String toString() {
		return "CangkuView{" +
			", cangkuValue=" + cangkuValue +
			"} " + super.toString();
	}
}
