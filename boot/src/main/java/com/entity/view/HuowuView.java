package com.entity.view;

import org.apache.tools.ant.util.DateUtils;
import com.annotation.ColumnInfo;
import com.entity.HuowuEntity;
import com.baomidou.mybatisplus.annotations.TableName;
import org.apache.commons.beanutils.BeanUtils;
import java.lang.reflect.InvocationTargetException;
import org.springframework.format.annotation.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.io.Serializable;
import java.util.Date;
import com.utils.DateUtil;

/**
* 货物
* 后端返回视图实体辅助类
* （通常后端关联的表或者自定义的字段需要返回使用）
*/
@TableName("huowu")
public class HuowuView extends HuowuEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	//当前表
	/**
	* 货物类型的值
	*/
	@ColumnInfo(comment="货物类型的字典表值",type="varchar(200)")
	private String huowuValue;

	//级联表 商品订单
		/**
		* 订单编号
		*/

		@ColumnInfo(comment="订单编号",type="varchar(200)")
		private String shangpinOrderUuidNumber;
																									 
		/**
		* 商品订单 的 用户
		*/
		@ColumnInfo(comment="用户",type="int(11)")
		private Integer shangpinOrderYonghuId;
		/**
		* 购买数量
		*/

		@ColumnInfo(comment="购买数量",type="int(11)")
		private Integer buyNumber;
		/**
		* 实付价格
		*/
		@ColumnInfo(comment="实付价格",type="decimal(10,2)")
		private Double shangpinOrderTruePrice;
		/**
		* 订单类型
		*/
		@ColumnInfo(comment="订单类型",type="int(11)")
		private Integer shangpinOrderTypes;
			/**
			* 订单类型的值
			*/
			@ColumnInfo(comment="订单类型的字典表值",type="varchar(200)")
			private String shangpinOrderValue;



	public HuowuView() {

	}

	public HuowuView(HuowuEntity huowuEntity) {
		try {
			BeanUtils.copyProperties(this, huowuEntity);
		} catch (IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}



	//当前表的
	/**
	* 获取： 货物类型的值
	*/
	public String getHuowuValue() {
		return huowuValue;
	}
	/**
	* 设置： 货物类型的值
	*/
	public void setHuowuValue(String huowuValue) {
		this.huowuValue = huowuValue;
	}


	//级联表的get和set 商品订单

		/**
		* 获取： 订单编号
		*/
		public String getShangpinOrderUuidNumber() {
			return shangpinOrderUuidNumber;
		}
		/**
		* 设置： 订单编号
		*/
		public void setShangpinOrderUuidNumber(String shangpinOrderUuidNumber) {
			this.shangpinOrderUuidNumber = shangpinOrderUuidNumber;
		}
		/**
		* 获取：商品订单 的 用户
		*/
		public Integer getShangpinOrderYonghuId() {
			return shangpinOrderYonghuId;
		}
		/**
		* 设置：商品订单 的 用户
		*/
		public void setShangpinOrderYonghuId(Integer shangpinOrderYonghuId) {
			this.shangpinOrderYonghuId = shangpinOrderYonghuId;
		}

		/**
		* 获取： 购买数量
		*/
		public Integer getBuyNumber() {
			return buyNumber;
		}
		/**
		* 设置： 购买数量
		*/
		public void setBuyNumber(Integer buyNumber) {
			this.buyNumber = buyNumber;
		}

		/**
		* 获取： 实付价格
		*/
		public Double getShangpinOrderTruePrice() {
			return shangpinOrderTruePrice;
		}
		/**
		* 设置： 实付价格
		*/
		public void setShangpinOrderTruePrice(Double shangpinOrderTruePrice) {
			this.shangpinOrderTruePrice = shangpinOrderTruePrice;
		}
		/**
		* 获取： 订单类型
		*/
		public Integer getShangpinOrderTypes() {
			return shangpinOrderTypes;
		}
		/**
		* 设置： 订单类型
		*/
		public void setShangpinOrderTypes(Integer shangpinOrderTypes) {
			this.shangpinOrderTypes = shangpinOrderTypes;
		}


			/**
			* 获取： 订单类型的值
			*/
			public String getShangpinOrderValue() {
				return shangpinOrderValue;
			}
			/**
			* 设置： 订单类型的值
			*/
			public void setShangpinOrderValue(String shangpinOrderValue) {
				this.shangpinOrderValue = shangpinOrderValue;
			}


	@Override
	public String toString() {
		return "HuowuView{" +
			", huowuValue=" + huowuValue +
			", shangpinOrderUuidNumber=" + shangpinOrderUuidNumber +
			", buyNumber=" + buyNumber +
			", shangpinOrderTruePrice=" + shangpinOrderTruePrice +
			"} " + super.toString();
	}
}
