<template>
	<div class="addEdit-block">
		<el-form label-position="top" class="detail-form-content" ref="ruleForm" :model="ruleForm" :rules="rules"
			>
			<el-form-item class="input" label="MySQL安装目录" prop="mysqlUrl">
				<el-input clearable placeholder="MySQL安装目录" v-model="ruleForm.mysqlUrl"></el-input>
			</el-form-item>
			<el-form-item class="input" label="MySQL数据库所在服务器地址IP" prop="hostIP">
				<el-input clearable placeholder="MySQL数据库所在服务器地址IP" v-model="ruleForm.hostIP"></el-input>
			</el-form-item>
			<el-form-item class="input" label="进入数据库所需要的用户名" prop="userName">
				<el-input clearable placeholder="进入数据库所需要的用户名" v-model="ruleForm.userName"></el-input>
			</el-form-item>
			<el-form-item class="input" label="数据库端口" prop="hostPort">
				<el-input clearable placeholder="数据库端口" v-model="ruleForm.hostPort"></el-input>
			</el-form-item>
			<el-form-item class="input" label="进入数据库所需要的密码" prop="password">
				<el-input clearable placeholder="进入数据库所需要的密码" v-model="ruleForm.password"></el-input>
			</el-form-item>
			<el-form-item class="input" label="数据库文件保存路径" prop="savePath">
				<el-input clearable placeholder="数据库文件保存路径" v-model="ruleForm.savePath">
				</el-input>
			</el-form-item>
			<el-form-item class="input" label="数据库还原文件文件名(.sql结尾)" prop="fileName">
				<el-input clearable placeholder="数据库还原文件文件名" v-model="ruleForm.fileName"></el-input>
			</el-form-item>
			<el-form-item class="input" label="要还原的数据库名" prop="databaseName">
				<el-input clearable placeholder="要还原的数据库名" v-model="ruleForm.databaseName"></el-input>
			</el-form-item>
			<el-form-item class="btn">
				<el-button type="primary" class="btn-success" @click="huanyuan">确定</el-button>
			</el-form-item>
		</el-form>
	</div>
</template>
<script>
	// 数字，邮件，手机，url，身份证校验
	import styleJs from "../../../utils/style.js";
	import utilsJs, {getYearFormat,getMonthFormat,getDateFormat,getDatetimeFormat} from "../../../utils/utils.js";
	export default {
		data() {
			return {
				ruleForm: {
					mysqlUrl: "C:/Program Files/MySQL/MySQL Server 5.7/bin/", //MySQL安装目录
					hostIP: '127.0.0.1', //MySQL数据库所在服务器地址IP
					userName: '', //进入数据库所需要的用户名
					hostPort: '3306', //数据库端口
					password: '', //进入数据库所需要的密码
					savePath: '', //数据库还原文件保存路径
					fileName: "", //数据库还原文件文件名
					databaseName: "", //要还原的数据库名
				},
				rules: {
					mysqlUrl: [{
						required: true,
						message: 'MySQL安装目录不能为空',
						trigger: 'blur'
					}, ],
					hostIP: [{
						required: true,
						message: 'MySQL数据库所在服务器地址IP不能为空',
						trigger: 'blur'
					}, ],
					userName: [{
						required: true,
						message: '进入数据库所需要的用户名不能为空',
						trigger: 'blur'
					}, ],
					hostPort: [{
						required: true,
						message: '数据库端口不能为空',
						trigger: 'blur'
					}, ],
					password: [{
						required: true,
						message: '进入数据库所需要的密码不能为空',
						trigger: 'blur'
					}, ],
					savePath: [{
						required: true,
						message: '数据库还原文件保存路径不能为空',
						trigger: 'blur'
					}, ],
					fileName: [{
						required: true,
						message: '数据库还原文件文件名不能为空',
						trigger: 'blur'
					}, ],
					databaseName: [{
						required: true,
						message: '要还原的数据库名不能为空',
						trigger: 'blur'
					}, ],
				}
			};
		},
		props: ["parent"],
		computed: {},
		created() {
			this.addEditForm = styleJs.addStyle();
			this.addEditStyleChange()
			this.addEditUploadStyleChange()
		},
		methods: {
			huanyuan() {
				let _this = this
				_this.ruleForm.hostIP = _this.ruleForm.hostIP.replaceAll("\\", "/")
				_this.ruleForm.savePath = _this.ruleForm.savePath.replaceAll("\\", "/")
				_this.$refs["ruleForm"].validate(valid => {
					if (valid) {
						_this.$http({
							url: '/huanyuan?hostIP=' + _this.ruleForm.hostIP +
								"&userName=" + _this.ruleForm.userName +
								"&hostPort=" + _this.ruleForm.hostPort +
								"&password=" + _this.ruleForm.password +
								"&savePath=" + _this.ruleForm.savePath +
								"&fileName=" + _this.ruleForm.fileName +
								"&databaseName=" + _this.ruleForm.databaseName +
								"&mysqlUrl=" + _this.ruleForm.mysqlUrl,
							method: "get",
						}).then(({
							data
						}) => {
							if (data && data.code === 0) {
								this.$message({
									message: "操作成功",
									type: "success",
									duration: 1500,
									onClose: () => {}
								});
							} else {
								this.$message.error(data.msg);
							}
							_this.ruleForm = {
								mysqlUrl: 'C:/Program Files/MySQL/MySQL Server 5.7/bin/', //MySQL数据库所在服务器地址IP
								hostIP: '127.0.0.1', //MySQL数据库所在服务器地址IP
								userName: null, //进入数据库所需要的用户名
								hostPort: '3306', //数据库端口
								password: null, //进入数据库所需要的密码
								savePath: null, //数据库还原文件保存路径
								fileName: null, //数据库还原文件文件名
								databaseName: null, //要还原的数据库名
							}
						});
					}
				})
			},
			addEditStyleChange() {
				this.$nextTick(() => {
					// input
					document.querySelectorAll('.addEdit-block .input .el-input__inner').forEach(el => {
						el.style.height = this.addEditForm.inputHeight
						el.style.color = this.addEditForm.inputFontColor
						el.style.fontSize = this.addEditForm.inputFontSize
						el.style.borderWidth = this.addEditForm.inputBorderWidth
						el.style.borderStyle = this.addEditForm.inputBorderStyle
						el.style.borderColor = this.addEditForm.inputBorderColor
						el.style.borderRadius = this.addEditForm.inputBorderRadius
						el.style.backgroundColor = this.addEditForm.inputBgColor
					})
					document.querySelectorAll('.addEdit-block .input .el-form-item__label').forEach(el => {
						el.style.lineHeight = this.addEditForm.inputHeight
						el.style.color = this.addEditForm.inputLableColor
						el.style.fontSize = this.addEditForm.inputLableFontSize
					})
					// select
					document.querySelectorAll('.addEdit-block .select .el-input__inner').forEach(el => {
						el.style.height = this.addEditForm.selectHeight
						el.style.color = this.addEditForm.selectFontColor
						el.style.fontSize = this.addEditForm.selectFontSize
						el.style.borderWidth = this.addEditForm.selectBorderWidth
						el.style.borderStyle = this.addEditForm.selectBorderStyle
						el.style.borderColor = this.addEditForm.selectBorderColor
						el.style.borderRadius = this.addEditForm.selectBorderRadius
						el.style.backgroundColor = this.addEditForm.selectBgColor
					})
					document.querySelectorAll('.addEdit-block .select .el-form-item__label').forEach(el => {
						el.style.lineHeight = this.addEditForm.selectHeight
						el.style.color = this.addEditForm.selectLableColor
						el.style.fontSize = this.addEditForm.selectLableFontSize
					})
					document.querySelectorAll('.addEdit-block .select .el-select__caret').forEach(el => {
						el.style.color = this.addEditForm.selectIconFontColor
						el.style.fontSize = this.addEditForm.selectIconFontSize
					})
					// date
					document.querySelectorAll('.addEdit-block .date .el-input__inner').forEach(el => {
						el.style.height = this.addEditForm.dateHeight
						el.style.color = this.addEditForm.dateFontColor
						el.style.fontSize = this.addEditForm.dateFontSize
						el.style.borderWidth = this.addEditForm.dateBorderWidth
						el.style.borderStyle = this.addEditForm.dateBorderStyle
						el.style.borderColor = this.addEditForm.dateBorderColor
						el.style.borderRadius = this.addEditForm.dateBorderRadius
						el.style.backgroundColor = this.addEditForm.dateBgColor
					})
					document.querySelectorAll('.addEdit-block .date .el-form-item__label').forEach(el => {
						el.style.lineHeight = this.addEditForm.dateHeight
						el.style.color = this.addEditForm.dateLableColor
						el.style.fontSize = this.addEditForm.dateLableFontSize
					})
					document.querySelectorAll('.addEdit-block .date .el-input__icon').forEach(el => {
						el.style.color = this.addEditForm.dateIconFontColor
						el.style.fontSize = this.addEditForm.dateIconFontSize
						el.style.lineHeight = this.addEditForm.dateHeight
					})
					// upload
					let iconLineHeight = parseInt(this.addEditForm.uploadHeight) - parseInt(this.addEditForm
						.uploadBorderWidth) * 2 + 'px'
					document.querySelectorAll('.addEdit-block .upload .el-upload--picture-card').forEach(el => {
						el.style.width = this.addEditForm.uploadHeight
						el.style.height = this.addEditForm.uploadHeight
						el.style.borderWidth = this.addEditForm.uploadBorderWidth
						el.style.borderStyle = this.addEditForm.uploadBorderStyle
						el.style.borderColor = this.addEditForm.uploadBorderColor
						el.style.borderRadius = this.addEditForm.uploadBorderRadius
						el.style.backgroundColor = this.addEditForm.uploadBgColor
					})
					document.querySelectorAll('.addEdit-block .upload .el-form-item__label').forEach(el => {
						el.style.lineHeight = this.addEditForm.uploadHeight
						el.style.color = this.addEditForm.uploadLableColor
						el.style.fontSize = this.addEditForm.uploadLableFontSize
					})
					document.querySelectorAll('.addEdit-block .upload .el-icon-plus').forEach(el => {
						el.style.color = this.addEditForm.uploadIconFontColor
						el.style.fontSize = this.addEditForm.uploadIconFontSize
						el.style.lineHeight = iconLineHeight
						el.style.display = 'block'
					})
					// 多文本输入框
					document.querySelectorAll('.addEdit-block .textarea .el-textarea__inner').forEach(el => {
						el.style.height = this.addEditForm.textareaHeight
						el.style.color = this.addEditForm.textareaFontColor
						el.style.fontSize = this.addEditForm.textareaFontSize
						el.style.borderWidth = this.addEditForm.textareaBorderWidth
						el.style.borderStyle = this.addEditForm.textareaBorderStyle
						el.style.borderColor = this.addEditForm.textareaBorderColor
						el.style.borderRadius = this.addEditForm.textareaBorderRadius
						el.style.backgroundColor = this.addEditForm.textareaBgColor
					})
					document.querySelectorAll('.addEdit-block .textarea .el-form-item__label').forEach(el => {
						// el.style.lineHeight = this.addEditForm.textareaHeight
						el.style.color = this.addEditForm.textareaLableColor
						el.style.fontSize = this.addEditForm.textareaLableFontSize
					})
					// 保存
					document.querySelectorAll('.addEdit-block .btn .btn-success').forEach(el => {
						el.style.width = this.addEditForm.btnSaveWidth
						el.style.height = this.addEditForm.btnSaveHeight
						el.style.color = this.addEditForm.btnSaveFontColor
						el.style.fontSize = this.addEditForm.btnSaveFontSize
						el.style.borderWidth = this.addEditForm.btnSaveBorderWidth
						el.style.borderStyle = this.addEditForm.btnSaveBorderStyle
						el.style.borderColor = this.addEditForm.btnSaveBorderColor
						el.style.borderRadius = this.addEditForm.btnSaveBorderRadius
						el.style.backgroundColor = this.addEditForm.btnSaveBgColor
					})
					// 返回
					document.querySelectorAll('.addEdit-block .btn .btn-close').forEach(el => {
						el.style.width = this.addEditForm.btnCancelWidth
						el.style.height = this.addEditForm.btnCancelHeight
						el.style.color = this.addEditForm.btnCancelFontColor
						el.style.fontSize = this.addEditForm.btnCancelFontSize
						el.style.borderWidth = this.addEditForm.btnCancelBorderWidth
						el.style.borderStyle = this.addEditForm.btnCancelBorderStyle
						el.style.borderColor = this.addEditForm.btnCancelBorderColor
						el.style.borderRadius = this.addEditForm.btnCancelBorderRadius
						el.style.backgroundColor = this.addEditForm.btnCancelBgColor
					})
				})
			},
			addEditUploadStyleChange() {
				this.$nextTick(() => {
					document.querySelectorAll(
						'.addEdit-block .upload .el-upload-list--picture-card .el-upload-list__item').forEach(
						el => {
							el.style.width = this.addEditForm.uploadHeight
							el.style.height = this.addEditForm.uploadHeight
							el.style.borderWidth = this.addEditForm.uploadBorderWidth
							el.style.borderStyle = this.addEditForm.uploadBorderStyle
							el.style.borderColor = this.addEditForm.uploadBorderColor
							el.style.borderRadius = this.addEditForm.uploadBorderRadius
							el.style.backgroundColor = this.addEditForm.uploadBgColor
						})
				})
			},
		}
	};
</script>
<style lang="scss">
	.slt {
    margin: 0 !important;
    display: flex;
  }

  .ad {
    margin: 0 !important;
    display: flex;
  }

  .pages {
    & /deep/ el-pagination__sizes{
      & /deep/ el-input__inner {
        height: 22px;
        line-height: 22px;
      }
    }
  }


  .el-button+.el-button {
    margin:0;
  }

  .tables {
	& /deep/ .el-button--success {
		height: 40px;
		color: #333;
		font-size: 14px;
		border-width: 2px;
		border-style: dashed ;
		border-color: rgba(255, 255, 255, 1);
		border-radius: 20px;
		background-color: var(--publicSubColor);
	}

	& /deep/ .el-button--primary {
		height: 40px;
		color: #333;
		font-size: 14px;
		border-width: 2px;
		border-style: dashed ;
		border-color: rgba(255, 255, 255, 1);
		border-radius: 20px;
		background-color: var(--publicSubColor);
	}

	& /deep/ .el-button--danger {
		height: 40px;
		color: #333;
		font-size: 14px;
		border-width: 2px;
		border-style: dashed ;
		border-color: rgba(255, 255, 255, 1);
		border-radius: 20px;
		background-color: var(--publicSubColor);
	}

    & /deep/ .el-button {
      margin: 4px;
    }
  }
	.form-content {
		background: transparent;
	}
	.table-content {
		background: transparent;
	}

	.tables /deep/ .el-table__body tr {
				background-color: rgba(255, 255, 255, 1) !important;
				color: rgba(92, 93, 95, 1) !important;
	 }
	.tables /deep/ .el-table__body tr.el-table__row--striped td {
	    background: transparent;
	}
	.tables /deep/ .el-table__body tr.el-table__row--striped {
		background-color: #F5F7FA !important;
		color: #606266 !important;
	}

	.tables /deep/ .el-table__body tr:hover>td {
	   background-color: #f5f5f5 !important;
		   color: #333 !important;
 	}</style>
