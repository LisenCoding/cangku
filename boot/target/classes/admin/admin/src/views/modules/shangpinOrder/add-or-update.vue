<template>
    <div class="addEdit-block">
        <el-form
                class="detail-form-content"
                ref="ruleForm"
                :model="ruleForm"
                :rules="rules"
                label-width="auto"
                >
            <el-row>
                <el-col :span="12"  v-if="sessionTable !='address'">
                    <el-form-item class="select" v-if="type!='info'"  label="收货地址" prop="addressId">
                        <el-select v-model="ruleForm.addressId" :disabled="ro.addressId" filterable placeholder="请选择收货地址" @change="addressChange">
                            <el-option
                                    v-for="(item,index) in addressOptions"
                                    v-bind:key="item.id"
                                    :label="item.addressName"
                                    :value="item.id">
                            </el-option>
                        </el-select>
                    </el-form-item>
                </el-col>

                <el-col :span="12"  v-if="sessionTable !='address' ">
                    <el-form-item class="input" v-if="type!='info'"  label="收货人" prop="addressName">
                        <el-input v-model="addressForm.addressName"
                                  placeholder="收货人" clearable readonly></el-input>
                    </el-form-item>
                    <div v-else-if="type=='info'">
                        <el-form-item class="input" label="收货人" prop="addressName">
                            <el-input v-model="ruleForm.addressName"
                                      placeholder="收货人" readonly></el-input>
                        </el-form-item>
                    </div>
                </el-col>
                <el-col :span="12"  v-if="sessionTable !='address' ">
                    <el-form-item class="input" v-if="type!='info'"  label="电话" prop="addressPhone">
                        <el-input v-model="addressForm.addressPhone"
                                  placeholder="电话" clearable readonly></el-input>
                    </el-form-item>
                    <div v-else-if="type=='info'">
                        <el-form-item class="input" label="电话" prop="addressPhone">
                            <el-input v-model="ruleForm.addressPhone"
                                      placeholder="电话" readonly></el-input>
                        </el-form-item>
                    </div>
                </el-col>
                <el-col :span="12"  v-if="sessionTable !='address' ">
                    <el-form-item class="input" v-if="type!='info'"  label="地址" prop="addressDizhi">
                        <el-input v-model="addressForm.addressDizhi"
                                  placeholder="地址" clearable readonly></el-input>
                    </el-form-item>
                    <div v-else-if="type=='info'">
                        <el-form-item class="input" label="地址" prop="addressDizhi">
                            <el-input v-model="ruleForm.addressDizhi"
                                      placeholder="地址" readonly></el-input>
                        </el-form-item>
                    </div>
                </el-col>
                <el-col :span="12"  v-if="sessionTable !='cheliang'">
                    <el-form-item class="select" v-if="type!='info'"  label="车辆" prop="cheliangId">
                        <el-select v-model="ruleForm.cheliangId" :disabled="ro.cheliangId" filterable placeholder="请选择车辆" @change="cheliangChange">
                            <el-option
                                    v-for="(item,index) in cheliangOptions"
                                    v-bind:key="item.id"
                                    :label="item.cheliangName"
                                    :value="item.id">
                            </el-option>
                        </el-select>
                    </el-form-item>
                </el-col>

                <el-col :span="12"  v-if="sessionTable !='cheliang' ">
                    <el-form-item class="input" v-if="type!='info'"  label="车辆名称" prop="cheliangName">
                        <el-input v-model="cheliangForm.cheliangName"
                                  placeholder="车辆名称" clearable readonly></el-input>
                    </el-form-item>
                    <div v-else-if="type=='info'">
                        <el-form-item class="input" label="车辆名称" prop="cheliangName">
                            <el-input v-model="ruleForm.cheliangName"
                                      placeholder="车辆名称" readonly></el-input>
                        </el-form-item>
                    </div>
                </el-col>
                <el-col :span="12"  v-if="sessionTable !='cheliang' ">
                    <el-form-item class="input" v-if="type!='info'"  label="车辆编号" prop="cheliangUuidNumber">
                        <el-input v-model="cheliangForm.cheliangUuidNumber"
                                  placeholder="车辆编号" clearable readonly></el-input>
                    </el-form-item>
                    <div v-else-if="type=='info'">
                        <el-form-item class="input" label="车辆编号" prop="cheliangUuidNumber">
                            <el-input v-model="ruleForm.cheliangUuidNumber"
                                      placeholder="车辆编号" readonly></el-input>
                        </el-form-item>
                    </div>
                </el-col>
                <el-col :span="12" v-if="sessionTable !='cheliang' ">
                    <el-form-item class="upload" v-if="type!='info' && !ro.cheliangPhoto" label="车辆照片" prop="cheliangPhoto">
                        <img style="margin-right:20px;" v-bind:key="index" v-for="(item,index) in (cheliangForm.cheliangPhoto || '').split(',')" :src="$base.url+item" width="100" height="100">
                    </el-form-item>
                    <div v-else-if="type=='info'">
                        <el-form-item v-if="ruleForm.cheliangPhoto" label="车辆照片" prop="cheliangPhoto">
                            <img style="margin-right:20px;" v-bind:key="index" v-for="(item,index) in (ruleForm.cheliangPhoto || '').split(',')" :src="$base.url+item" width="100" height="100">
                        </el-form-item>
                    </div>
                </el-col>
                <el-col :span="12"  v-if="sessionTable !='cheliang' ">
                    <el-form-item class="input" v-if="type!='info'"  label="车辆牌照" prop="cheliangPaizhao">
                        <el-input v-model="cheliangForm.cheliangPaizhao"
                                  placeholder="车辆牌照" clearable readonly></el-input>
                    </el-form-item>
                    <div v-else-if="type=='info'">
                        <el-form-item class="input" label="车辆牌照" prop="cheliangPaizhao">
                            <el-input v-model="ruleForm.cheliangPaizhao"
                                      placeholder="车辆牌照" readonly></el-input>
                        </el-form-item>
                    </div>
                </el-col>
                <el-col :span="12"  v-if="sessionTable !='cheliang' ">
                    <el-form-item class="input" v-if="type!='info'"  label="车辆类型" prop="cheliangValue">
                        <el-input v-model="cheliangForm.cheliangValue"
                                  placeholder="车辆类型" clearable readonly></el-input>
                    </el-form-item>
                    <div v-else-if="type=='info'">
                        <el-form-item class="input" label="车辆类型" prop="cheliangValue">
                            <el-input v-model="ruleForm.cheliangValue"
                                      placeholder="车辆类型" readonly></el-input>
                        </el-form-item>
                    </div>
                </el-col>
                <el-col :span="12"  v-if="sessionTable !='shangpin'">
                    <el-form-item class="select" v-if="type!='info'"  label="商品" prop="shangpinId">
                        <el-select v-model="ruleForm.shangpinId" :disabled="ro.shangpinId" filterable placeholder="请选择商品" @change="shangpinChange">
                            <el-option
                                    v-for="(item,index) in shangpinOptions"
                                    v-bind:key="item.id"
                                    :label="item.shangpinName"
                                    :value="item.id">
                            </el-option>
                        </el-select>
                    </el-form-item>
                </el-col>

                <el-col :span="12"  v-if="sessionTable !='shangpin' ">
                    <el-form-item class="input" v-if="type!='info'"  label="商品名称" prop="shangpinName">
                        <el-input v-model="shangpinForm.shangpinName"
                                  placeholder="商品名称" clearable readonly></el-input>
                    </el-form-item>
                    <div v-else-if="type=='info'">
                        <el-form-item class="input" label="商品名称" prop="shangpinName">
                            <el-input v-model="ruleForm.shangpinName"
                                      placeholder="商品名称" readonly></el-input>
                        </el-form-item>
                    </div>
                </el-col>
                <el-col :span="12" v-if="sessionTable !='shangpin' ">
                    <el-form-item class="upload" v-if="type!='info' && !ro.shangpinPhoto" label="商品照片" prop="shangpinPhoto">
                        <img style="margin-right:20px;" v-bind:key="index" v-for="(item,index) in (shangpinForm.shangpinPhoto || '').split(',')" :src="$base.url+item" width="100" height="100">
                    </el-form-item>
                    <div v-else-if="type=='info'">
                        <el-form-item v-if="ruleForm.shangpinPhoto" label="商品照片" prop="shangpinPhoto">
                            <img style="margin-right:20px;" v-bind:key="index" v-for="(item,index) in (ruleForm.shangpinPhoto || '').split(',')" :src="$base.url+item" width="100" height="100">
                        </el-form-item>
                    </div>
                </el-col>
                <el-col :span="12"  v-if="sessionTable !='shangpin' ">
                    <el-form-item class="input" v-if="type!='info'"  label="商品类型" prop="shangpinValue">
                        <el-input v-model="shangpinForm.shangpinValue"
                                  placeholder="商品类型" clearable readonly></el-input>
                    </el-form-item>
                    <div v-else-if="type=='info'">
                        <el-form-item class="input" label="商品类型" prop="shangpinValue">
                            <el-input v-model="ruleForm.shangpinValue"
                                      placeholder="商品类型" readonly></el-input>
                        </el-form-item>
                    </div>
                </el-col>
                <el-col :span="12"  v-if="sessionTable !='siji'">
                    <el-form-item class="select" v-if="type!='info'"  label="司机" prop="sijiId">
                        <el-select v-model="ruleForm.sijiId" :disabled="ro.sijiId" filterable placeholder="请选择司机" @change="sijiChange">
                            <el-option
                                    v-for="(item,index) in sijiOptions"
                                    v-bind:key="item.id"
                                    :label="item.sijiName"
                                    :value="item.id">
                            </el-option>
                        </el-select>
                    </el-form-item>
                </el-col>

                <el-col :span="12"  v-if="sessionTable !='siji' ">
                    <el-form-item class="input" v-if="type!='info'"  label="司机编号" prop="sijiUuidNumber">
                        <el-input v-model="sijiForm.sijiUuidNumber"
                                  placeholder="司机编号" clearable readonly></el-input>
                    </el-form-item>
                    <div v-else-if="type=='info'">
                        <el-form-item class="input" label="司机编号" prop="sijiUuidNumber">
                            <el-input v-model="ruleForm.sijiUuidNumber"
                                      placeholder="司机编号" readonly></el-input>
                        </el-form-item>
                    </div>
                </el-col>
                <el-col :span="12"  v-if="sessionTable !='siji' ">
                    <el-form-item class="input" v-if="type!='info'"  label="司机姓名" prop="sijiName">
                        <el-input v-model="sijiForm.sijiName"
                                  placeholder="司机姓名" clearable readonly></el-input>
                    </el-form-item>
                    <div v-else-if="type=='info'">
                        <el-form-item class="input" label="司机姓名" prop="sijiName">
                            <el-input v-model="ruleForm.sijiName"
                                      placeholder="司机姓名" readonly></el-input>
                        </el-form-item>
                    </div>
                </el-col>
                <el-col :span="12"  v-if="sessionTable !='siji' ">
                    <el-form-item class="input" v-if="type!='info'"  label="司机手机号" prop="sijiPhone">
                        <el-input v-model="sijiForm.sijiPhone"
                                  placeholder="司机手机号" clearable readonly></el-input>
                    </el-form-item>
                    <div v-else-if="type=='info'">
                        <el-form-item class="input" label="司机手机号" prop="sijiPhone">
                            <el-input v-model="ruleForm.sijiPhone"
                                      placeholder="司机手机号" readonly></el-input>
                        </el-form-item>
                    </div>
                </el-col>
                <el-col :span="12"  v-if="sessionTable !='siji' ">
                    <el-form-item class="input" v-if="type!='info'"  label="司机身份证号" prop="sijiIdNumber">
                        <el-input v-model="sijiForm.sijiIdNumber"
                                  placeholder="司机身份证号" clearable readonly></el-input>
                    </el-form-item>
                    <div v-else-if="type=='info'">
                        <el-form-item class="input" label="司机身份证号" prop="sijiIdNumber">
                            <el-input v-model="ruleForm.sijiIdNumber"
                                      placeholder="司机身份证号" readonly></el-input>
                        </el-form-item>
                    </div>
                </el-col>
                <el-col :span="12" v-if="sessionTable !='siji' ">
                    <el-form-item class="upload" v-if="type!='info' && !ro.sijiPhoto" label="司机头像" prop="sijiPhoto">
                        <img style="margin-right:20px;" v-bind:key="index" v-for="(item,index) in (sijiForm.sijiPhoto || '').split(',')" :src="$base.url+item" width="100" height="100">
                    </el-form-item>
                    <div v-else-if="type=='info'">
                        <el-form-item v-if="ruleForm.sijiPhoto" label="司机头像" prop="sijiPhoto">
                            <img style="margin-right:20px;" v-bind:key="index" v-for="(item,index) in (ruleForm.sijiPhoto || '').split(',')" :src="$base.url+item" width="100" height="100">
                        </el-form-item>
                    </div>
                </el-col>
                <el-col :span="12"  v-if="sessionTable !='siji' ">
                    <el-form-item class="input" v-if="type!='info'"  label="性别" prop="sexValue">
                        <el-input v-model="sijiForm.sexValue"
                                  placeholder="性别" clearable readonly></el-input>
                    </el-form-item>
                    <div v-else-if="type=='info'">
                        <el-form-item class="input" label="性别" prop="sexValue">
                            <el-input v-model="ruleForm.sexValue"
                                      placeholder="性别" readonly></el-input>
                        </el-form-item>
                    </div>
                </el-col>
                <el-col :span="12"  v-if="sessionTable !='siji' ">
                    <el-form-item class="input" v-if="type!='info'"  label="司机邮箱" prop="sijiEmail">
                        <el-input v-model="sijiForm.sijiEmail"
                                  placeholder="司机邮箱" clearable readonly></el-input>
                    </el-form-item>
                    <div v-else-if="type=='info'">
                        <el-form-item class="input" label="司机邮箱" prop="sijiEmail">
                            <el-input v-model="ruleForm.sijiEmail"
                                      placeholder="司机邮箱" readonly></el-input>
                        </el-form-item>
                    </div>
                </el-col>
                <el-col :span="12"  v-if="sessionTable !='yonghu'">
                    <el-form-item class="select" v-if="type!='info'"  label="用户" prop="yonghuId">
                        <el-select v-model="ruleForm.yonghuId" :disabled="ro.yonghuId" filterable placeholder="请选择用户" @change="yonghuChange">
                            <el-option
                                    v-for="(item,index) in yonghuOptions"
                                    v-bind:key="item.id"
                                    :label="item.yonghuName"
                                    :value="item.id">
                            </el-option>
                        </el-select>
                    </el-form-item>
                </el-col>

                <el-col :span="12"  v-if="sessionTable !='yonghu' ">
                    <el-form-item class="input" v-if="type!='info'"  label="用户姓名" prop="yonghuName">
                        <el-input v-model="yonghuForm.yonghuName"
                                  placeholder="用户姓名" clearable readonly></el-input>
                    </el-form-item>
                    <div v-else-if="type=='info'">
                        <el-form-item class="input" label="用户姓名" prop="yonghuName">
                            <el-input v-model="ruleForm.yonghuName"
                                      placeholder="用户姓名" readonly></el-input>
                        </el-form-item>
                    </div>
                </el-col>
                <input id="updateId" name="id" type="hidden">
               <el-col :span="12">
                   <el-form-item class="input" v-if="type!='info'"  label="订单编号" prop="shangpinOrderUuidNumber">
                       <el-input v-model="ruleForm.shangpinOrderUuidNumber"
                                 placeholder="订单编号" clearable  :readonly="ro.shangpinOrderUuidNumber"></el-input>
                   </el-form-item>
                   <div v-else-if="type=='info'">
                       <el-form-item class="input" label="订单编号" prop="shangpinOrderUuidNumber">
                           <el-input v-model="ruleForm.shangpinOrderUuidNumber"
                                     placeholder="订单编号" readonly></el-input>
                       </el-form-item>
                   </div>
               </el-col>
            <input id="addressId" name="addressId" type="hidden">
            <input id="shangpinId" name="shangpinId" type="hidden">
            <input id="cheliangId" name="cheliangId" type="hidden">
            <input id="sijiId" name="sijiId" type="hidden">
            <input id="yonghuId" name="yonghuId" type="hidden">
               <el-col :span="12">
                   <el-form-item class="input" v-if="type!='info'"  label="购买数量" prop="buyNumber">
                       <el-input v-model="ruleForm.buyNumber"
                                 placeholder="购买数量" clearable  :readonly="ro.buyNumber"></el-input>
                   </el-form-item>
                   <div v-else-if="type=='info'">
                       <el-form-item class="input" label="购买数量" prop="buyNumber">
                           <el-input v-model="ruleForm.buyNumber"
                                     placeholder="购买数量" readonly></el-input>
                       </el-form-item>
                   </div>
               </el-col>
               <el-col :span="12">
                   <el-form-item class="input" v-if="type!='info'"  label="实付价格" prop="shangpinOrderTruePrice">
                       <el-input v-model="ruleForm.shangpinOrderTruePrice"
                                 placeholder="实付价格" clearable  :readonly="ro.shangpinOrderTruePrice"></el-input>
                   </el-form-item>
                   <div v-else-if="type=='info'">
                       <el-form-item class="input" label="实付价格" prop="shangpinOrderTruePrice">
                           <el-input v-model="ruleForm.shangpinOrderTruePrice"
                                     placeholder="实付价格" readonly></el-input>
                       </el-form-item>
                   </div>
               </el-col>
                <el-col :span="12">
                    <el-form-item class="select" v-if="type!='info'"  label="订单类型" prop="shangpinOrderTypes">
                        <el-select v-model="ruleForm.shangpinOrderTypes" :disabled="ro.shangpinOrderTypes" placeholder="请选择订单类型">
                            <el-option
                                v-for="(item,index) in shangpinOrderTypesOptions"
                                v-bind:key="item.codeIndex"
                                :label="item.indexName"
                                :value="item.codeIndex">
                            </el-option>
                        </el-select>
                    </el-form-item>
                    <div v-else-if="type=='info'">
                        <el-form-item class="input" label="订单类型" prop="shangpinOrderValue">
                        <el-input v-model="ruleForm.shangpinOrderValue"
                            placeholder="订单类型" readonly></el-input>
                        </el-form-item>
                    </div>
                </el-col>
            </el-row>
            <el-form-item class="btn">
                <el-button v-if="type!='info'" type="primary" class="btn-success" @click="onSubmit">提交</el-button>
                <el-button v-if="type!='info'" class="btn-close" @click="back()">取消</el-button>
                <el-button v-if="type=='info'" class="btn-close" @click="back()">返回</el-button>
            </el-form-item>
        </el-form>
    </div>
</template>
<script>
    import styleJs from "../../../utils/style.js";
    // 数字，邮件，手机，url，身份证校验
    import { isNumber,isIntNumer,isEmail,isPhone, isMobile,isURL,checkIdCard } from "@/utils/validate";
    import utilsJs, {getYearFormat,getMonthFormat,getDateFormat,getDatetimeFormat} from "../../../utils/utils.js";
    export default {
        data() {
            return {
                addEditForm:null,
                id: '',
                type: '',
                sessionTable : "",//登录账户所在表名
                role : "",//权限
                userId:"",//当前登录人的id
                addressForm: {},
                cheliangForm: {},
                shangpinForm: {},
                sijiForm: {},
                yonghuForm: {},
                ro:{
                    shangpinOrderUuidNumber: true,
                    addressId: false,
                    shangpinId: false,
                    cheliangId: false,
                    sijiId: false,
                    yonghuId: false,
                    buyNumber: false,
                    shangpinOrderTruePrice: false,
                    shangpinOrderTypes: false,
                    insertTime: false,
                },
                ruleForm: {
                    shangpinOrderUuidNumber: new Date().getTime(),
                    addressId: '',
                    shangpinId: '',
                    cheliangId: '',
                    sijiId: '',
                    yonghuId: '',
                    buyNumber: '',
                    shangpinOrderTruePrice: '',
                    shangpinOrderTypes: '',
                    insertTime: '',
                },
                shangpinOrderTypesOptions : [],
                addressOptions : [],
                cheliangOptions : [],
                shangpinOptions : [],
                sijiOptions : [],
                yonghuOptions : [],
                rules: {
                   shangpinOrderUuidNumber: [
                              { required: true, message: '订单编号不能为空', trigger: 'blur' },
                          ],
                   addressId: [
                              { required: true, message: '收货地址不能为空', trigger: 'blur' },
                              {  pattern: /^[0-9]*$/,
                                  message: '只允许输入整数',
                                  trigger: 'blur'
                              }
                          ],
                   shangpinId: [
                              { required: true, message: '商品不能为空', trigger: 'blur' },
                              {  pattern: /^[0-9]*$/,
                                  message: '只允许输入整数',
                                  trigger: 'blur'
                              }
                          ],
                   cheliangId: [
                              { required: true, message: '车辆不能为空', trigger: 'blur' },
                              {  pattern: /^[0-9]*$/,
                                  message: '只允许输入整数',
                                  trigger: 'blur'
                              }
                          ],
                   sijiId: [
                              { required: true, message: '司机不能为空', trigger: 'blur' },
                              {  pattern: /^[0-9]*$/,
                                  message: '只允许输入整数',
                                  trigger: 'blur'
                              }
                          ],
                   yonghuId: [
                              { required: true, message: '用户不能为空', trigger: 'blur' },
                              {  pattern: /^[0-9]*$/,
                                  message: '只允许输入整数',
                                  trigger: 'blur'
                              }
                          ],
                   buyNumber: [
                              { required: true, message: '购买数量不能为空', trigger: 'blur' },
                              {  pattern: /^[0-9]*$/,
                                  message: '只允许输入整数',
                                  trigger: 'blur'
                              }
                          ],
                   shangpinOrderTruePrice: [
                              { required: true, message: '实付价格不能为空', trigger: 'blur' },
                              {  pattern: /^[0-9]{0,6}(\.[0-9]{1,2})?$/,
                                  message: '只允许输入整数6位,小数2位的数字',
                                  trigger: 'blur'
                              }
                          ],
                   shangpinOrderTypes: [
                              { required: true, message: '订单类型不能为空', trigger: 'blur' },
                              {  pattern: /^[0-9]*$/,
                                  message: '只允许输入整数',
                                  trigger: 'blur'
                              }
                          ],
                   insertTime: [
                              { required: true, message: '订单创建时间不能为空', trigger: 'blur' },
                          ],
                }
            };
        },
        props: ["parent"],
        computed: {
        },
        created() {
            //获取当前登录用户的信息
            this.sessionTable = this.$storage.get("sessionTable");
            this.role = this.$storage.get("role");
            this.userId = this.$storage.get("userId");


            if (this.role != "管理员"){
            }else{
            }
            this.addEditForm = styleJs.addStyle();
            this.addEditStyleChange()
            this.addEditUploadStyleChange()
            //获取下拉框信息
                this.$http({
                    url:`dictionary/page?page=1&limit=100&sort=&order=&dicCode=shangpin_order_types`,
                    method: "get"
                }).then(({ data }) => {
                    if (data && data.code === 0) {
                        this.shangpinOrderTypesOptions = data.data.list;
                    }
                });

         this.$http({
             url: `address/page?page=1&limit=100`,
             method: "get"
         }).then(({ data }) => {
             if (data && data.code === 0) {
                this.addressOptions = data.data.list;
            }
         });
         this.$http({
             url: `cheliang/page?page=1&limit=100`,
             method: "get"
         }).then(({ data }) => {
             if (data && data.code === 0) {
                this.cheliangOptions = data.data.list;
            }
         });
         this.$http({
             url: `shangpin/page?page=1&limit=100`,
             method: "get"
         }).then(({ data }) => {
             if (data && data.code === 0) {
                this.shangpinOptions = data.data.list;
            }
         });
         this.$http({
             url: `siji/page?page=1&limit=100`,
             method: "get"
         }).then(({ data }) => {
             if (data && data.code === 0) {
                this.sijiOptions = data.data.list;
            }
         });
         this.$http({
             url: `yonghu/page?page=1&limit=100`,
             method: "get"
         }).then(({ data }) => {
             if (data && data.code === 0) {
                this.yonghuOptions = data.data.list;
            }
         });

        },
        mounted() {
        },
        methods: {
            // 下载
            download(file){
                window.open(`${file}`)
            },
            // 初始化
            init(id,type) {
                if (id) {
                    this.id = id;
                    this.type = type;
                }
                if(this.type=='info'||this.type=='else'){
                    this.info(id);
                }
                // 获取用户信息
                this.$http({
                    url:`${this.$storage.get("sessionTable")}/session`,
                    method: "get"
                }).then(({ data }) => {
                    if (data && data.code === 0) {
                        var json = data.data;
                    } else {
                        this.$message.error(data.msg);
                    }
                });
            },
            addressChange(id){
                this.$http({
                    url: `address/info/`+id,
                    method: "get"
                }).then(({ data }) => {
                    if (data && data.code === 0) {
                        this.addressForm = data.data;
                    }
                });
            },
            cheliangChange(id){
                this.$http({
                    url: `cheliang/info/`+id,
                    method: "get"
                }).then(({ data }) => {
                    if (data && data.code === 0) {
                        this.cheliangForm = data.data;
                    }
                });
            },
            shangpinChange(id){
                this.$http({
                    url: `shangpin/info/`+id,
                    method: "get"
                }).then(({ data }) => {
                    if (data && data.code === 0) {
                        this.shangpinForm = data.data;
                    }
                });
            },
            sijiChange(id){
                this.$http({
                    url: `siji/info/`+id,
                    method: "get"
                }).then(({ data }) => {
                    if (data && data.code === 0) {
                        this.sijiForm = data.data;
                    }
                });
            },
            yonghuChange(id){
                this.$http({
                    url: `yonghu/info/`+id,
                    method: "get"
                }).then(({ data }) => {
                    if (data && data.code === 0) {
                        this.yonghuForm = data.data;
                    }
                });
            },
            // 多级联动参数
            info(id) {
                let _this =this;
                _this.$http({
                    url: `shangpinOrder/info/${id}`,
                    method: 'get'
                }).then(({ data }) => {
                    if (data && data.code === 0) {
                        _this.ruleForm = data.data;
                        _this.addressChange(data.data.addressId)
                        _this.cheliangChange(data.data.cheliangId)
                        _this.shangpinChange(data.data.shangpinId)
                        _this.sijiChange(data.data.sijiId)
                        _this.yonghuChange(data.data.yonghuId)
                    } else {
                        _this.$message.error(data.msg);
                    }
                });
            },
            // 提交
            onSubmit() {
                this.$refs["ruleForm"].validate(valid => {
                    if (valid) {
                        this.$http({
                            url:`shangpinOrder/${!this.ruleForm.id ? "save" : "update"}`,
                            method: "post",
                            data: this.ruleForm
                        }).then(({ data }) => {
                            if (data && data.code === 0) {
                                this.$message({
                                    message: "操作成功",
                                    type: "success",
                                    duration: 1500,
                                    onClose: () => {
                                        this.parent.showFlag = true;
                                        this.parent.addOrUpdateFlag = false;
                                        this.parent.shangpinOrderCrossAddOrUpdateFlag = false;
                                        this.parent.search();
                                        this.parent.contentStyleChange();
                                    }
                                });
                            } else {
                                this.$message.error(data.msg);
                            }
                        });
                    }
                });
            },
            // 获取uuid
            getUUID () {
                return new Date().getTime();
            },
            // 返回
            back() {
                this.parent.showFlag = true;
                this.parent.addOrUpdateFlag = false;
                this.parent.shangpinOrderCrossAddOrUpdateFlag = false;
                this.parent.contentStyleChange();
            },
            //图片

            addEditStyleChange() {
                this.$nextTick(()=>{
                    // input
                    document.querySelectorAll('.addEdit-block .input .el-input__inner').forEach(el=>{
                        el.style.height = this.addEditForm.inputHeight
                        el.style.color = this.addEditForm.inputFontColor
                        el.style.fontSize = this.addEditForm.inputFontSize
                        el.style.borderWidth = this.addEditForm.inputBorderWidth
                        el.style.borderStyle = this.addEditForm.inputBorderStyle
                        el.style.borderColor = this.addEditForm.inputBorderColor
                        el.style.borderRadius = this.addEditForm.inputBorderRadius
                        el.style.backgroundColor = this.addEditForm.inputBgColor
                    })
                    document.querySelectorAll('.addEdit-block .input .el-form-item__label').forEach(el=>{
                        el.style.lineHeight = this.addEditForm.inputHeight
                        el.style.color = this.addEditForm.inputLableColor
                        el.style.fontSize = this.addEditForm.inputLableFontSize
                    })
                    // select
                    document.querySelectorAll('.addEdit-block .select .el-input__inner').forEach(el=>{
                        el.style.height = this.addEditForm.selectHeight
                        el.style.color = this.addEditForm.selectFontColor
                        el.style.fontSize = this.addEditForm.selectFontSize
                        el.style.borderWidth = this.addEditForm.selectBorderWidth
                        el.style.borderStyle = this.addEditForm.selectBorderStyle
                        el.style.borderColor = this.addEditForm.selectBorderColor
                        el.style.borderRadius = this.addEditForm.selectBorderRadius
                        el.style.backgroundColor = this.addEditForm.selectBgColor
                    })
                    document.querySelectorAll('.addEdit-block .select .el-form-item__label').forEach(el=>{
                        el.style.lineHeight = this.addEditForm.selectHeight
                        el.style.color = this.addEditForm.selectLableColor
                        el.style.fontSize = this.addEditForm.selectLableFontSize
                    })
                    document.querySelectorAll('.addEdit-block .select .el-select__caret').forEach(el=>{
                        el.style.color = this.addEditForm.selectIconFontColor
                        el.style.fontSize = this.addEditForm.selectIconFontSize
                    })
                    // date
                    document.querySelectorAll('.addEdit-block .date .el-input__inner').forEach(el=>{
                        el.style.height = this.addEditForm.dateHeight
                        el.style.color = this.addEditForm.dateFontColor
                        el.style.fontSize = this.addEditForm.dateFontSize
                        el.style.borderWidth = this.addEditForm.dateBorderWidth
                        el.style.borderStyle = this.addEditForm.dateBorderStyle
                        el.style.borderColor = this.addEditForm.dateBorderColor
                        el.style.borderRadius = this.addEditForm.dateBorderRadius
                        el.style.backgroundColor = this.addEditForm.dateBgColor
                    })
                    document.querySelectorAll('.addEdit-block .date .el-form-item__label').forEach(el=>{
                        el.style.lineHeight = this.addEditForm.dateHeight
                        el.style.color = this.addEditForm.dateLableColor
                        el.style.fontSize = this.addEditForm.dateLableFontSize
                    })
                    document.querySelectorAll('.addEdit-block .date .el-input__icon').forEach(el=>{
                        el.style.color = this.addEditForm.dateIconFontColor
                        el.style.fontSize = this.addEditForm.dateIconFontSize
                        el.style.lineHeight = this.addEditForm.dateHeight
                    })
                    // upload
                    let iconLineHeight = parseInt(this.addEditForm.uploadHeight) - parseInt(this.addEditForm.uploadBorderWidth) * 2 + 'px'
                    document.querySelectorAll('.addEdit-block .upload .el-upload--picture-card').forEach(el=>{
                        el.style.width = this.addEditForm.uploadHeight
                        el.style.height = this.addEditForm.uploadHeight
                        el.style.borderWidth = this.addEditForm.uploadBorderWidth
                        el.style.borderStyle = this.addEditForm.uploadBorderStyle
                        el.style.borderColor = this.addEditForm.uploadBorderColor
                        el.style.borderRadius = this.addEditForm.uploadBorderRadius
                        el.style.backgroundColor = this.addEditForm.uploadBgColor
                    })
                    document.querySelectorAll('.addEdit-block .upload .el-form-item__label').forEach(el=>{
                        el.style.lineHeight = this.addEditForm.uploadHeight
                        el.style.color = this.addEditForm.uploadLableColor
                        el.style.fontSize = this.addEditForm.uploadLableFontSize
                    })
                    document.querySelectorAll('.addEdit-block .upload .el-icon-plus').forEach(el=>{
                        el.style.color = this.addEditForm.uploadIconFontColor
                        el.style.fontSize = this.addEditForm.uploadIconFontSize
                        el.style.lineHeight = iconLineHeight
                        el.style.display = 'block'
                    })
                    // 多文本输入框
                    document.querySelectorAll('.addEdit-block .textarea .el-textarea__inner').forEach(el=>{
                        el.style.height = this.addEditForm.textareaHeight
                        el.style.color = this.addEditForm.textareaFontColor
                        el.style.fontSize = this.addEditForm.textareaFontSize
                        el.style.borderWidth = this.addEditForm.textareaBorderWidth
                        el.style.borderStyle = this.addEditForm.textareaBorderStyle
                        el.style.borderColor = this.addEditForm.textareaBorderColor
                        el.style.borderRadius = this.addEditForm.textareaBorderRadius
                        el.style.backgroundColor = this.addEditForm.textareaBgColor
                    })
                    document.querySelectorAll('.addEdit-block .textarea .el-form-item__label').forEach(el=>{
                        // el.style.lineHeight = this.addEditForm.textareaHeight
                        el.style.color = this.addEditForm.textareaLableColor
                        el.style.fontSize = this.addEditForm.textareaLableFontSize
                    })
                    // 保存
                    document.querySelectorAll('.addEdit-block .btn .btn-success').forEach(el=>{
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
                    document.querySelectorAll('.addEdit-block .btn .btn-close').forEach(el=>{
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
                this.$nextTick(()=>{
                    document.querySelectorAll('.addEdit-block .upload .el-upload-list--picture-card .el-upload-list__item').forEach(el=>{
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
.editor{
  height: 500px;

  & /deep/ .ql-container {
	  height: 310px;
  }
}
.amap-wrapper {
  width: 100%;
  height: 500px;
}
.search-box {
  position: absolute;
}
.addEdit-block {
	margin: -10px;
}
.detail-form-content {
	padding: 12px;
	background-color: transparent;
}
.btn .el-button {
  padding: 0;
}</style>

