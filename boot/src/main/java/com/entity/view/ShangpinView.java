package com.entity.view;

import org.apache.tools.ant.util.DateUtils;
import com.annotation.ColumnInfo;
import com.entity.ShangpinEntity;
import com.baomidou.mybatisplus.annotations.TableName;
import org.apache.commons.beanutils.BeanUtils;
import java.lang.reflect.InvocationTargetException;
import org.springframework.format.annotation.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.io.Serializable;
import java.util.Date;
import com.utils.DateUtil;

/**
* 商品
* 后端返回视图实体辅助类
* （通常后端关联的表或者自定义的字段需要返回使用）
*/
@TableName("shangpin")
public class ShangpinView extends ShangpinEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	//当前表
	/**
	* 商品类型的值
	*/
	@ColumnInfo(comment="商品类型的字典表值",type="varchar(200)")
	private String shangpinValue;
	/**
	* 是否上架的值
	*/
	@ColumnInfo(comment="是否上架的字典表值",type="varchar(200)")
	private String shangxiaValue;

	//级联表 仓库
		/**
		* 仓库名称
		*/

		@ColumnInfo(comment="仓库名称",type="varchar(200)")
		private String cangkuName;
		/**
		* 仓库编号
		*/

		@ColumnInfo(comment="仓库编号",type="varchar(200)")
		private String cangkuUuidNumber;
		/**
		* 仓库照片
		*/

		@ColumnInfo(comment="仓库照片",type="varchar(200)")
		private String cangkuPhoto;
		/**
		* 仓库地点
		*/

		@ColumnInfo(comment="仓库地点",type="varchar(200)")
		private String cangkuAddress;
		/**
		* 仓库类型
		*/
		@ColumnInfo(comment="仓库类型",type="int(11)")
		private Integer cangkuTypes;
			/**
			* 仓库类型的值
			*/
			@ColumnInfo(comment="仓库类型的字典表值",type="varchar(200)")
			private String cangkuValue;
		/**
		* 仓库介绍
		*/

		@ColumnInfo(comment="仓库介绍",type="longtext")
		private String cangkuContent;
		/**
		* 逻辑删除
		*/

		@ColumnInfo(comment="逻辑删除",type="int(11)")
		private Integer cangkuDelete;



	public ShangpinView() {

	}

	public ShangpinView(ShangpinEntity shangpinEntity) {
		try {
			BeanUtils.copyProperties(this, shangpinEntity);
		} catch (IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}



	//当前表的
	/**
	* 获取： 商品类型的值
	*/
	public String getShangpinValue() {
		return shangpinValue;
	}
	/**
	* 设置： 商品类型的值
	*/
	public void setShangpinValue(String shangpinValue) {
		this.shangpinValue = shangpinValue;
	}
	//当前表的
	/**
	* 获取： 是否上架的值
	*/
	public String getShangxiaValue() {
		return shangxiaValue;
	}
	/**
	* 设置： 是否上架的值
	*/
	public void setShangxiaValue(String shangxiaValue) {
		this.shangxiaValue = shangxiaValue;
	}


	//级联表的get和set 仓库

		/**
		* 获取： 仓库名称
		*/
		public String getCangkuName() {
			return cangkuName;
		}
		/**
		* 设置： 仓库名称
		*/
		public void setCangkuName(String cangkuName) {
			this.cangkuName = cangkuName;
		}

		/**
		* 获取： 仓库编号
		*/
		public String getCangkuUuidNumber() {
			return cangkuUuidNumber;
		}
		/**
		* 设置： 仓库编号
		*/
		public void setCangkuUuidNumber(String cangkuUuidNumber) {
			this.cangkuUuidNumber = cangkuUuidNumber;
		}

		/**
		* 获取： 仓库照片
		*/
		public String getCangkuPhoto() {
			return cangkuPhoto;
		}
		/**
		* 设置： 仓库照片
		*/
		public void setCangkuPhoto(String cangkuPhoto) {
			this.cangkuPhoto = cangkuPhoto;
		}

		/**
		* 获取： 仓库地点
		*/
		public String getCangkuAddress() {
			return cangkuAddress;
		}
		/**
		* 设置： 仓库地点
		*/
		public void setCangkuAddress(String cangkuAddress) {
			this.cangkuAddress = cangkuAddress;
		}
		/**
		* 获取： 仓库类型
		*/
		public Integer getCangkuTypes() {
			return cangkuTypes;
		}
		/**
		* 设置： 仓库类型
		*/
		public void setCangkuTypes(Integer cangkuTypes) {
			this.cangkuTypes = cangkuTypes;
		}


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

		/**
		* 获取： 仓库介绍
		*/
		public String getCangkuContent() {
			return cangkuContent;
		}
		/**
		* 设置： 仓库介绍
		*/
		public void setCangkuContent(String cangkuContent) {
			this.cangkuContent = cangkuContent;
		}

		/**
		* 获取： 逻辑删除
		*/
		public Integer getCangkuDelete() {
			return cangkuDelete;
		}
		/**
		* 设置： 逻辑删除
		*/
		public void setCangkuDelete(Integer cangkuDelete) {
			this.cangkuDelete = cangkuDelete;
		}


	@Override
	public String toString() {
		return "ShangpinView{" +
			", shangpinValue=" + shangpinValue +
			", shangxiaValue=" + shangxiaValue +
			", cangkuName=" + cangkuName +
			", cangkuUuidNumber=" + cangkuUuidNumber +
			", cangkuPhoto=" + cangkuPhoto +
			", cangkuAddress=" + cangkuAddress +
			", cangkuContent=" + cangkuContent +
			", cangkuDelete=" + cangkuDelete +
			"} " + super.toString();
	}
}
