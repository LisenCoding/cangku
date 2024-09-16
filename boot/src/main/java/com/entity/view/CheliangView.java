package com.entity.view;

import org.apache.tools.ant.util.DateUtils;
import com.annotation.ColumnInfo;
import com.entity.CheliangEntity;
import com.baomidou.mybatisplus.annotations.TableName;
import org.apache.commons.beanutils.BeanUtils;
import java.lang.reflect.InvocationTargetException;
import org.springframework.format.annotation.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.io.Serializable;
import java.util.Date;
import com.utils.DateUtil;

/**
* 车辆
* 后端返回视图实体辅助类
* （通常后端关联的表或者自定义的字段需要返回使用）
*/
@TableName("cheliang")
public class CheliangView extends CheliangEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	//当前表
	/**
	* 车辆类型的值
	*/
	@ColumnInfo(comment="车辆类型的字典表值",type="varchar(200)")
	private String cheliangValue;




	public CheliangView() {

	}

	public CheliangView(CheliangEntity cheliangEntity) {
		try {
			BeanUtils.copyProperties(this, cheliangEntity);
		} catch (IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}



	//当前表的
	/**
	* 获取： 车辆类型的值
	*/
	public String getCheliangValue() {
		return cheliangValue;
	}
	/**
	* 设置： 车辆类型的值
	*/
	public void setCheliangValue(String cheliangValue) {
		this.cheliangValue = cheliangValue;
	}




	@Override
	public String toString() {
		return "CheliangView{" +
			", cheliangValue=" + cheliangValue +
			"} " + super.toString();
	}
}
