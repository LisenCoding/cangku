<template>
  <div class="main-content">

    <!-- 条件查询 -->
    <div v-if="showFlag">
      <el-form :inline="true" :model="searchForm" class="form-content">
        <el-row :gutter="20" class="slt"
                :style="{justifyContent:contents.searchBoxPosition=='1'?'flex-start':contents.searchBoxPosition=='2'?'center':'flex-end'}">

          <el-form-item :label="contents.inputTitle == 1 ? '订单编号' : ''">
            <el-input prefix-icon="el-icon-search" v-model="searchForm.shangpinOrderUuidNumber" placeholder="订单编号"
                      clearable></el-input>
          </el-form-item>

          <el-form-item :label="contents.inputTitle == 1 ? '订单类型' : ''">
            <el-select v-model="searchForm.shangpinOrderTypes" placeholder="请选择订单类型">
              <el-option label="=-请选择-=" value=""></el-option>
              <el-option
                  v-for="(item,index) in shangpinOrderTypesSelectSearch"
                  v-bind:key="index"
                  :label="item.indexName"
                  :value="item.codeIndex">
                <!--lable是要展示的名称-->
                <!--value是值-->
              </el-option>
            </el-select>
          </el-form-item>


          <el-form-item :label="contents.inputTitle == 1 ? '车辆名称' : ''">
            <el-input prefix-icon="el-icon-search" v-model="searchForm.cheliangName" placeholder="车辆名称"
                      clearable></el-input>
          </el-form-item>

          <el-form-item :label="contents.inputTitle == 1 ? '车辆类型' : ''">
            <el-select v-model="searchForm.cheliangTypes" placeholder="请选择车辆类型">
              <el-option label="=-请选择-=" value=""></el-option>
              <el-option
                  v-for="(item,index) in cheliangTypesSelectSearch"
                  v-bind:key="index"
                  :label="item.indexName"
                  :value="item.codeIndex">
                <!--lable是要展示的名称-->
                <!--value是值-->
              </el-option>
            </el-select>
          </el-form-item>

          <el-form-item :label="contents.inputTitle == 1 ? '商品名称' : ''">
            <el-input prefix-icon="el-icon-search" v-model="searchForm.shangpinName" placeholder="商品名称"
                      clearable></el-input>
          </el-form-item>

          <el-form-item :label="contents.inputTitle == 1 ? '商品类型' : ''">
            <el-select v-model="searchForm.shangpinTypes" placeholder="请选择商品类型">
              <el-option label="=-请选择-=" value=""></el-option>
              <el-option
                  v-for="(item,index) in shangpinTypesSelectSearch"
                  v-bind:key="index"
                  :label="item.indexName"
                  :value="item.codeIndex">
                <!--lable是要展示的名称-->
                <!--value是值-->
              </el-option>
            </el-select>
          </el-form-item>

          <el-form-item :label="contents.inputTitle == 1 ? '司机编号' : ''">
            <el-input prefix-icon="el-icon-search" v-model="searchForm.sijiUuidNumber" placeholder="司机编号"
                      clearable></el-input>
          </el-form-item>

          <el-form-item :label="contents.inputTitle == 1 ? '司机姓名' : ''">
            <el-input prefix-icon="el-icon-search" v-model="searchForm.sijiName" placeholder="司机姓名"
                      clearable></el-input>
          </el-form-item>


          <el-form-item :label="contents.inputTitle == 1 ? '用户姓名' : ''">
            <el-input prefix-icon="el-icon-search" v-model="searchForm.yonghuName" placeholder="用户姓名"
                      clearable></el-input>
          </el-form-item>


          <el-form-item>
            <el-button type="success" @click="search()">查询<i class="el-icon-search el-icon--right"/></el-button>
          </el-form-item>
        </el-row>
        <el-row class="ad"
                :style="{justifyContent:contents.btnAdAllBoxPosition=='1'?'flex-start':contents.btnAdAllBoxPosition=='2'?'center':'flex-end'}">
          <el-form-item>
            <el-button
                v-if="isAuth('shangpinOrder','新增')"
                type="success"
                icon="el-icon-plus"
                @click="addOrUpdateHandler()"
            >新增
            </el-button>
            &nbsp;
            <el-button
                v-if="isAuth('shangpinOrder','删除')"
                :disabled="dataListSelections.length <= 0"
                type="danger"
                icon="el-icon-delete"
                @click="deleteHandler()"
            >删除
            </el-button>
            &nbsp;
            <el-button
                v-if="isAuth('shangpinOrder','报表')"
                type="success"
                icon="el-icon-pie-chart"
                @click="chartDialog()"
            >报表
            </el-button>
            &nbsp;
<!--            <a style="text-decoration:none" class="el-button el-button&#45;&#45;success"-->
<!--               v-if="isAuth('shangpinOrder','导入导出')"-->
<!--               icon="el-icon-download"-->
<!--               href="http://localhost:8080/wuliuguanlixitong/upload/shangpinOrderMuBan.xls"-->
<!--            >批量导入商品订单数据模板</a>-->
<!--            &nbsp;-->
<!--            <el-upload-->
<!--                v-if="isAuth('shangpinOrder','导入导出')"-->
<!--                style="display: inline-block"-->
<!--                :action="getActionUrl"-->
<!--                :on-success="shangpinOrderUploadSuccess"-->
<!--                :on-error="shangpinOrderUploadError"-->
<!--                :show-file-list=false>-->
<!--              <el-button-->
<!--                  v-if="isAuth('shangpinOrder','导入导出')"-->
<!--                  type="success"-->
<!--                  icon="el-icon-upload2"-->
<!--              >批量导入商品订单数据-->
<!--              </el-button>-->
<!--            </el-upload>-->
            &nbsp;
            <!-- 导出excel -->
            <download-excel v-if="isAuth('shangpinOrder','导入导出')" style="display: inline-block"
                            class="export-excel-wrapper" :data="dataList" :fields="json_fields"
                            name="shangpinOrder.xls">
              <!-- 导出excel -->
              <el-button
                  type="success"
                  icon="el-icon-download"
              >导出
              </el-button>
            </download-excel>
            &nbsp;
          </el-form-item>
        </el-row>
      </el-form>
      <div class="table-content">
        <el-table class="tables" :size="contents.tableSize" :show-header="contents.tableShowHeader"
                  :header-row-style="headerRowStyle" :header-cell-style="headerCellStyle"
                  :border="contents.tableBorder"
                  :fit="contents.tableFit"
                  :stripe="contents.tableStripe"
                  :row-style="rowStyle"
                  :cell-style="cellStyle"
                  :style="{width: '100%',fontSize:contents.tableContentFontSize,color:contents.tableContentFontColor}"
                  v-if="isAuth('shangpinOrder','查看')"
                  :data="dataList"
                  v-loading="dataListLoading"
                  @selection-change="selectionChangeHandler">
          <el-table-column v-if="contents.tableSelection"
                           type="selection"
                           header-align="center"
                           align="center"
                           width="50">
          </el-table-column>
          <el-table-column label="索引" v-if="contents.tableIndex" type="index" width="50"/>
          <el-table-column :sortable="contents.tableSortable" :align="contents.tableAlign"
                           prop="addressName"
                           header-align="center"
                           label="收货人">
            <template slot-scope="scope">
              {{ scope.row.addressName }}
            </template>
          </el-table-column>
          <el-table-column :sortable="contents.tableSortable" :align="contents.tableAlign"
                           prop="addressPhone"
                           header-align="center"
                           label="电话">
            <template slot-scope="scope">
              {{ scope.row.addressPhone }}
            </template>
          </el-table-column>
          <el-table-column :sortable="contents.tableSortable" :align="contents.tableAlign"
                           prop="addressDizhi"
                           header-align="center"
                           label="地址">
            <template slot-scope="scope">
              {{ scope.row.addressDizhi }}
            </template>
          </el-table-column>
          <el-table-column :sortable="contents.tableSortable" :align="contents.tableAlign"
                           prop="cheliangName"
                           header-align="center"
                           label="车辆名称">
            <template slot-scope="scope">
              {{ scope.row.cheliangName }}
            </template>
          </el-table-column>
          <el-table-column :sortable="contents.tableSortable" :align="contents.tableAlign"
                           prop="cheliangUuidNumber"
                           header-align="center"
                           label="车辆编号">
            <template slot-scope="scope">
              {{ scope.row.cheliangUuidNumber }}
            </template>
          </el-table-column>
          <el-table-column :sortable="contents.tableSortable" :align="contents.tableAlign" prop="cheliangPhoto"
                           header-align="center"
                           width="200"
                           label="车辆照片">
            <template slot-scope="scope">
              <div v-if="scope.row.cheliangPhoto">
                <img :src="$base.url+scope.row.cheliangPhoto" width="100" height="100">
              </div>
              <div v-else>无图片</div>
            </template>
          </el-table-column>
          <el-table-column :sortable="contents.tableSortable" :align="contents.tableAlign"
                           prop="cheliangTypes"
                           header-align="center"
                           label="车辆类型">
            <template slot-scope="scope">
              {{ scope.row.cheliangValue }}
            </template>
          </el-table-column>
          <el-table-column :sortable="contents.tableSortable" :align="contents.tableAlign"
                           prop="shangpinName"
                           header-align="center"
                           label="商品名称">
            <template slot-scope="scope">
              {{ scope.row.shangpinName }}
            </template>
          </el-table-column>
          <el-table-column :sortable="contents.tableSortable" :align="contents.tableAlign" prop="shangpinPhoto"
                           header-align="center"
                           width="200"
                           label="商品照片">
            <template slot-scope="scope">
              <div v-if="scope.row.shangpinPhoto">
                <img :src="$base.url+scope.row.shangpinPhoto" width="100" height="100">
              </div>
              <div v-else>无图片</div>
            </template>
          </el-table-column>
          <el-table-column :sortable="contents.tableSortable" :align="contents.tableAlign"
                           prop="shangpinTypes"
                           header-align="center"
                           label="商品类型">
            <template slot-scope="scope">
              {{ scope.row.shangpinValue }}
            </template>
          </el-table-column>
          <el-table-column :sortable="contents.tableSortable" :align="contents.tableAlign"
                           prop="sijiUuidNumber"
                           header-align="center"
                           label="司机编号">
            <template slot-scope="scope">
              {{ scope.row.sijiUuidNumber }}
            </template>
          </el-table-column>
          <el-table-column :sortable="contents.tableSortable" :align="contents.tableAlign"
                           prop="sijiName"
                           header-align="center"
                           label="司机姓名">
            <template slot-scope="scope">
              {{ scope.row.sijiName }}
            </template>
          </el-table-column>
          <el-table-column :sortable="contents.tableSortable" :align="contents.tableAlign"
                           prop="sijiPhone"
                           header-align="center"
                           label="司机手机号">
            <template slot-scope="scope">
              {{ scope.row.sijiPhone }}
            </template>
          </el-table-column>
          <el-table-column :sortable="contents.tableSortable" :align="contents.tableAlign" prop="sijiPhoto"
                           header-align="center"
                           width="200"
                           label="司机头像">
            <template slot-scope="scope">
              <div v-if="scope.row.sijiPhoto">
                <img :src="$base.url+scope.row.sijiPhoto" width="100" height="100">
              </div>
              <div v-else>无图片</div>
            </template>
          </el-table-column>
          <el-table-column :sortable="contents.tableSortable" :align="contents.tableAlign"
                           prop="yonghuName"
                           header-align="center"
                           label="用户姓名">
            <template slot-scope="scope">
              {{ scope.row.yonghuName }}
            </template>
          </el-table-column>

          <el-table-column :sortable="contents.tableSortable" :align="contents.tableAlign"
                           prop="shangpinOrderUuidNumber"
                           header-align="center"
                           label="订单编号">
            <template slot-scope="scope">
              {{ scope.row.shangpinOrderUuidNumber }}
            </template>
          </el-table-column>
          <el-table-column :sortable="contents.tableSortable" :align="contents.tableAlign"
                           prop="buyNumber"
                           header-align="center"
                           label="购买数量">
            <template slot-scope="scope">
              {{ scope.row.buyNumber }}
            </template>
          </el-table-column>

          <el-table-column :sortable="contents.tableSortable" :align="contents.tableAlign"
                           prop="shangpinOrderTruePrice"
                           header-align="center"
                           label="实付价格">
            <template slot-scope="scope">
              {{ scope.row.shangpinOrderTruePrice }}
            </template>
          </el-table-column>
          <el-table-column :sortable="contents.tableSortable" :align="contents.tableAlign"
                           prop="shangpinOrderTypes"
                           header-align="center"
                           label="订单类型">
            <template slot-scope="scope">
              {{ scope.row.shangpinOrderValue }}
            </template>
          </el-table-column>

          <el-table-column :sortable="contents.tableSortable" :align="contents.tableAlign"
                           prop="insertTime"
                           header-align="center"
                           label="订单创建时间">
            <template slot-scope="scope">
              {{ scope.row.insertTime }}
            </template>
          </el-table-column>

          <el-table-column width="300" :align="contents.tableAlign"
                           header-align="center"
                           label="操作">
            <template slot-scope="scope">
              <el-button v-if="sessionTable=='false'" type="primary" size="mini" @click="wuyong(scope.row.id)">
                无用按钮
              </el-button>
              <el-button v-if="sessionTable1111=='true'" type="success" icon="el-icon-printer" size="mini"
                         @click="dayinOpen(scope.row)">打印
              </el-button>
              <el-button v-if="isAuth('shangpinOrder','查看')" type="success" icon="el-icon-tickets" size="mini"
                         @click="addOrUpdateHandler(scope.row.id,'info')">详情
              </el-button>
              <el-button v-if="isAuth('shangpinOrder','修改')" type="primary" icon="el-icon-edit" size="mini"
                         @click="addOrUpdateHandler(scope.row.id)">修改
              </el-button>
              <el-button v-if="isAuth('shangpinOrder','删除')" type="danger" icon="el-icon-delete" size="mini"
                         @click="deleteHandler(scope.row.id)">删除
              </el-button>
              <el-button
                  v-if="isAuth('shangpinOrder','订单') && scope.row.shangpinOrderTypes == 101 && sessionTable=='yonghu' && userId==scope.row.yonghuId"
                  type="primary" icon="el-icon-sold-out" size="mini" @click="refund(scope.row.id)">退款
              </el-button>
              <el-button v-if="isAuth('shangpinOrder','订单') && scope.row.shangpinOrderTypes == 101
&& sessionTable=='users' && scope.row.sijiId!=null" type="primary" icon="el-icon-reading" size="mini"
                         @click="deliver(scope.row.id)"
              >发出
              </el-button>
              <el-button v-if="isAuth('shangpinOrder','订单') && scope.row.shangpinOrderTypes == 103
&& sessionTable=='yuangong' && scope.row.sijiId!=null" type="primary" icon="el-icon-reading" size="mini"
                         @click="songda(scope.row.id)"
              >送达
              </el-button>
              <el-button v-if="isAuth('shangpinOrder','订单') && scope.row.sijiId==null" type="primary" icon="el-icon-reading" size="mini"
                         @click="tanchuOpen(scope.row.id)"
              >安排车辆和司机
              </el-button>
              <el-button
                  v-if="isAuth('shangpinOrder','订单') && scope.row.shangpinOrderTypes == 103 && sessionTable=='yonghu' && userId==scope.row.yonghuId"
                  type="primary" icon="el-icon-reading" size="mini" @click="receiving(scope.row.id)">验收
              </el-button>

            </template>
          </el-table-column>
        </el-table>
        <el-pagination
            clsss="pages"
            :layout="layouts"
            @size-change="sizeChangeHandle"
            @current-change="currentChangeHandle"
            :current-page="pageIndex"
            :page-sizes="[10, 20, 50, 100]"
            :page-size="Number(contents.pageEachNum)"
            :total="totalPage"
            :small="contents.pageStyle"
            class="pagination-content"
            :background="contents.pageBtnBG"
            :style="{textAlign:contents.pagePosition==1?'left':contents.pagePosition==2?'center':'right'}"
        ></el-pagination>
      </div>
    </div>
    <!-- 添加/修改页面  将父组件的search方法传递给子组件-->
    <add-or-update v-if="addOrUpdateFlag" :parent="this" ref="addOrUpdate"></add-or-update>


    <el-dialog title="打印" :visible.sync="printVisiable" width="500px">
      <el-form ref="printContent" :model="printFrom" label-width="auto">
        <el-form-item label="发件人">
          <div style="display: flex">
            <el-input v-model="printFrom.kuaidiFajianName" disabled></el-input>
            :
            <el-input v-model="printFrom.kuaidiFajianShoujihao" disabled></el-input>
          </div>
        </el-form-item>
        <el-form-item label="发件人地址">
          <el-input v-model="printFrom.kuaidiFajianAddress" disabled></el-input>
        </el-form-item>
      </el-form>
      <span slot="footer" class="dialog-footer">
              <el-button @click="printContent()" icon="el-icon-printer" type="success">打印</el-button>
              <el-button @click="printVisiable = false">关闭</el-button>
            </span>
    </el-dialog>

    <el-dialog title="选择车辆和司机" :visible.sync="printVisiable" width="500px">
      <div>
        <span >
                选择车辆:
                <el-select v-model="cheliangId" filterable placeholder="请选择商品">
                    <el-option
                        v-for="(item,index) in cheliangOptions"
                        v-bind:key="item.id"
                        :label="item.cheliangName"
                        :value="item.id">
                    </el-option>
                </el-select>
       </span>
      </div>

      <div>
        <span >
                选择司机:
                <el-select v-model="sijiId" filterable placeholder="请选择商品">
                    <el-option
                        v-for="(item,index) in sijiOptions"
                        v-bind:key="item.id"
                        :label="item.sijiName"
                        :value="item.id">
                    </el-option>
                </el-select>
       </span>
      </div>


      <span slot="footer" class="dialog-footer">
              <el-button @click="chakan"  >确认</el-button>
              <el-button @click="printVisiable = false">关闭</el-button>
            </span>
    </el-dialog>

    <el-dialog title="统计报表" :visible.sync="chartVisiable" width="80%">
      <el-date-picker v-model="echartsDate" type="month" placeholder="选择年月"></el-date-picker>
      <el-button @click="chartDialog()">查询</el-button>
      <div id="statistic" style="width:100%;height:600px;"></div>

      <span slot="footer" class="dialog-footer">
				<el-button @click="chartVisiable = false">关闭</el-button>
			</span>
    </el-dialog>

  </div>
</template>
<script>
import AddOrUpdate from "./add-or-update";
import styleJs from "../../../utils/style.js";
import utilsJs, {getYearFormat, getMonthFormat, getDateFormat, getDatetimeFormat} from "../../../utils/utils.js";

export default {
  data() {
    return {
      //打印开始
      printFrom: {
        name1: "",
      },
      printVisiable: false,
      printVisiable1111: false,
      //打印结束
      searchForm: {
        key: ""
      },
      sessionTable: "",//登录账户所在表名
      role: "",//权限
      userId: "",//当前登录人的id
      //级联表下拉框搜索条件
      cheliangTypesSelectSearch: [],
      shangpinTypesSelectSearch: [],



      cheliangOptions: [],
      sijiOptions: [],
      cheliangId:null,
      sijiId:null,
      xinjiande:null,



      //当前表下拉框搜索条件
      shangpinOrderTypesSelectSearch: [],
      form: {
        id: null,
        shangpinOrderUuidNumber: null,
        addressId: null,
        shangpinId: null,
        cheliangId: null,
        sijiId: null,
        yonghuId: null,
        buyNumber: null,
        shangpinOrderTruePrice: null,
        shangpinOrderTypes: null,
        insertTime: null,
        createTime: null,
      },
      dataList: [],
      pageIndex: 1,
      pageSize: 10,
      totalPage: 0,
      dataListLoading: false,
      dataListSelections: [],
      showFlag: true,
      sfshVisiable: false,
      shForm: {},
      chartVisiable: false,
      echartsDate: new Date(),//echarts的时间查询字段
      addOrUpdateFlag: false,
      contents: null,
      layouts: '',

      //导出excel
      json_fields: {
        //级联表字段
        '收货人': 'addressName',
        '电话': 'addressPhone',
        '地址': 'addressDizhi',
        '是否默认地址': 'isdefaultTypes',
        '车辆名称': 'cheliangName',
        '车辆编号': 'cheliangUuidNumber',
        '车辆照片': 'cheliangPhoto',
        '车辆牌照': 'cheliangPaizhao',
        '车辆类型': 'cheliangTypes',
        '商品名称': 'shangpinName',
        '商品编号': 'shangpinUuidNumber',
        '商品照片': 'shangpinPhoto',
        '商品类型': 'shangpinTypes',
        '商品库存': 'shangpinKucunNumber',
        '现价/积分': 'shangpinNewMoney',
        '是否上架': 'shangxiaTypes',
        '司机编号': 'sijiUuidNumber',
        '司机姓名': 'sijiName',
        '司机手机号': 'sijiPhone',
        '司机身份证号': 'sijiIdNumber',
        '司机头像': 'sijiPhoto',
        '性别': 'sexTypes',
        '司机邮箱': 'sijiEmail',
        '用户编号': 'yonghuUuidNumber',
        '用户姓名': 'yonghuName',
        '用户手机号': 'yonghuPhone',
        '用户身份证号': 'yonghuIdNumber',
        '用户头像': 'yonghuPhoto',
        '余额': 'newMoney',
        '用户邮箱': 'yonghuEmail',
        //本表字段
        '订单编号': "shangpinOrderUuidNumber",
        '购买数量': "buyNumber",
        '实付价格': "shangpinOrderTruePrice",
        '订单类型': "shangpinOrderValue",
        '订单创建时间': "insertTime",
      },

    };
  },
  created() {
    this.contents = styleJs.listStyle();
    this.init();
    this.getDataList();
    this.contentStyleChange()
  },
  mounted() {
    //获取当前登录用户的信息
    this.sessionTable = this.$storage.get("sessionTable");
    this.role = this.$storage.get("role");
    this.userId = this.$storage.get("userId");


    this.$http({
      url: `cheliang/page?page=1&limit=100&cheliangDelete=1`,
      method: "get"
    }).then(({data}) => {
      if (data && data.code === 0) {
        this.cheliangOptions = data.data.list;
      }
    });
    this.$http({
      url: `siji/page?page=1&limit=100&sijiDelete=1`,
      method: "get"
    }).then(({data}) => {
      if (data && data.code === 0) {
        this.sijiOptions = data.data.list;

      }
    });

  },
  filters: {
    htmlfilter: function (val) {
      return val.replace(/<[^>]*>/g).replace(/undefined/g, '');
    }
  },
  components: {
    AddOrUpdate,
  },
  computed: {
    getActionUrl: function () {
      return `/` + this.$base.name + `/file/upload`;
    }
  },
  methods: {
    chartDialog() {
      let _this = this;
      let params = {
        dateFormat: "%Y-%m", //%Y-%m
        // riqi: getYearFormat(_this.echartsDate),//年
        riqi :getMonthFormat(_this.echartsDate),//年月
        thisTable: {//当前表
          tableName: 'shangpin_order',//当前表表名,
          sumColum: 'shangpin_order_true_price', //求和字段
          date: 'insert_time',//分组日期字段
          // string : 'shangpin_order_name',//分组字符串字段
          // types : 'shangpin_order_types',//分组下拉框字段
        },
        joinTable : {//级联表（可以不存在）
            tableName :'yonghu',//级联表表名
            // date : 'insert_time',//分组日期字段
            string : 'yonghu_name',//分组字符串字段
            // types : 'yonghu_types',//分组下拉框字段
        }
      }
      _this.chartVisiable = true;
      _this.$nextTick(() => {
        var statistic = this.$echarts.init(document.getElementById("statistic"), 'macarons');
        this.$http({
          url: "barSum",//barCountOne barCountTwo barSumOne barSumTwo
          method: "get",
          params: params
        }).then(({data}) => {
          if (data && data.code === 0) {
            let yAxisName = "数值";//y轴
            let xAxisName = "月份";//x轴
            let series = [];//具体数据值
            data.data.yAxis.forEach(function (item, index) {//点击可关闭的按钮字符串在后台方法中
              let tempMap = {};
              tempMap.name = data.data.legend[index];
              tempMap.type = 'bar';
              tempMap.data = item;
              series.push(tempMap);
            })

            var option = {
              tooltip: {
                trigger: 'axis',
                axisPointer: {
                  type: 'cross',
                  crossStyle: {
                    color: '#999'
                  }
                }
              },
              toolbox: {
                feature: {
                  dataView: { show: true, readOnly: false },  // 数据查看
                  magicType: {show: true, type: ['line', 'bar']},//切换图形展示方式
                  restore: { show: true }, // 刷新
                  saveAsImage: {show: true}//保存
                }
              },
              legend: {
                data: data.data.legend//标题  可以点击导致某一列数据消失
              },
              xAxis: [
                {
                  type: 'category',
                  axisLabel: {interval: 0},
                  name: xAxisName,
                  data: data.data.xAxis,
                  axisPointer: {
                    type: 'shadow'
                  }
                }
              ],
              yAxis: [
                {
                  type: 'value',//不能改
                  name: yAxisName,//y轴单位
                  axisLabel: {
                    formatter: '{value}' // 后缀
                  }
                }
              ],
              series: series//具体数据
            };
            statistic.setOption(option, true);
            window.onresize = function () {
              statistic.resize();
            };
          } else {
            this.$message({
              message: "报表未查询到数据",
              type: "success",
              duration: 1500,
              onClose: () => {
                this.search();
              }
            });
          }
        });
      });
      ////饼状图
      //_this.chartVisiable = true;
      // this.$nextTick(()=>{
      //     var statistic = this.$echarts.init(document.getElementById("statistic"),'macarons');
      //     let params = {
      //         tableName: "shangpin_order",
      //         groupColumn: "shangpin_order_types",
      //// riqi :getMonthFormat(_this.echartsDate),//年月
      //     }
      //     this.$http({
      //         url: "newSelectGroupCount",//pieSum pieCount
      //         method: "get",
      //         params: params
      //     }).then(({data}) => {
      //         if (data && data.code === 0) {
      //             let res = data.data;
      //             let xAxis = [];
      //             let yAxis = [];
      //             let pArray = []
      //             var option = {};
      //             for(let i=0;i<res.length;i++){
      //                 xAxis.push(res[i].name);
      //                 yAxis.push(res[i].value);
      //                 pArray.push({
      //                     value: res[i].value,
      //                     name: res[i].name
      //                 })
      //                 option = {
      //                     title: {
      //                         text: '保险合同类型统计',
      //                         left: 'center'
      //                     },
      //                     tooltip: {
      //                         trigger: 'item',
      //                         formatter: '{b} : {c} ({d}%)'
      //                     },
      //                     legend: {
      //                         orient: 'vertical',
      //                         left: 'left'
      //                     },
      //                     series: [
      //                         {
      //                             type: 'pie',
      //                             radius: '55%',
      //                             center: ['50%', '60%'],
      //                             data: pArray,
      //                             emphasis: {
      //                                 itemStyle: {
      //                                     shadowBlur: 10,
      //                                     shadowOffsetX: 0,
      //                                     shadowColor: 'rgba(0, 0, 0, 0.5)'
      //                                 }
      //                             }
      //                         }
      //                     ]
      //                 };
      //             }
      //             statistic.setOption(option,true);
      //             window.onresize = function() {
      //                 statistic.resize();
      //             };
      //         }
      //     });
      // })
    },
    contentStyleChange() {
      this.contentSearchStyleChange()
      this.contentBtnAdAllStyleChange()
      this.contentSearchBtnStyleChange()
      this.contentTableBtnStyleChange()
      this.contentPageStyleChange()
    },
    contentSearchStyleChange() {
      this.$nextTick(() => {
        document.querySelectorAll('.form-content .slt .el-input__inner').forEach(el => {
          let textAlign = 'left'
          if (this.contents.inputFontPosition == 2)
            textAlign = 'center'
          if (this.contents.inputFontPosition == 3) textAlign = 'right'
          el.style.textAlign = textAlign
          el.style.height = this.contents.inputHeight
          el.style.lineHeight = this.contents.inputHeight
          el.style.color = this.contents.inputFontColor
          el.style.fontSize = this.contents.inputFontSize
          el.style.borderWidth = this.contents.inputBorderWidth
          el.style.borderStyle = this.contents.inputBorderStyle
          el.style.borderColor = this.contents.inputBorderColor
          el.style.borderRadius = this.contents.inputBorderRadius
          el.style.backgroundColor = this.contents.inputBgColor
        })
        if (this.contents.inputTitle) {
          document.querySelectorAll('.form-content .slt .el-form-item__label').forEach(el => {
            el.style.color = this.contents.inputTitleColor
            el.style.fontSize = this.contents.inputTitleSize
            el.style.lineHeight = this.contents.inputHeight
          })
        }
        setTimeout(() => {
          document.querySelectorAll('.form-content .slt .el-input__prefix').forEach(el => {
            el.style.color = this.contents.inputIconColor
            el.style.lineHeight = this.contents.inputHeight
          })
          document.querySelectorAll('.form-content .slt .el-input__suffix').forEach(el => {
            el.style.color = this.contents.inputIconColor
            el.style.lineHeight = this.contents.inputHeight
          })
          document.querySelectorAll('.form-content .slt .el-input__icon').forEach(el => {
            el.style.lineHeight = this.contents.inputHeight
          })
        }, 10)
      })
    },
    // 搜索按钮
    contentSearchBtnStyleChange() {
      this.$nextTick(() => {
        document.querySelectorAll('.form-content .slt .el-button--success').forEach(el => {
          el.style.height = this.contents.searchBtnHeight
          el.style.color = this.contents.searchBtnFontColor
          el.style.fontSize = this.contents.searchBtnFontSize
          el.style.borderWidth = this.contents.searchBtnBorderWidth
          el.style.borderStyle = this.contents.searchBtnBorderStyle
          el.style.borderColor = this.contents.searchBtnBorderColor
          el.style.borderRadius = this.contents.searchBtnBorderRadius
          el.style.backgroundColor = this.contents.searchBtnBgColor
        })
      })
    },
    // 新增、批量删除
    contentBtnAdAllStyleChange() {
      this.$nextTick(() => {
        document.querySelectorAll('.form-content .ad .el-button--success').forEach(el => {
          el.style.height = this.contents.btnAdAllHeight
          el.style.color = this.contents.btnAdAllAddFontColor
          el.style.fontSize = this.contents.btnAdAllFontSize
          el.style.borderWidth = this.contents.btnAdAllBorderWidth
          el.style.borderStyle = this.contents.btnAdAllBorderStyle
          el.style.borderColor = this.contents.btnAdAllBorderColor
          el.style.borderRadius = this.contents.btnAdAllBorderRadius
          el.style.backgroundColor = this.contents.btnAdAllAddBgColor
        })
        document.querySelectorAll('.form-content .ad .el-button--danger').forEach(el => {
          el.style.height = this.contents.btnAdAllHeight
          el.style.color = this.contents.btnAdAllDelFontColor
          el.style.fontSize = this.contents.btnAdAllFontSize
          el.style.borderWidth = this.contents.btnAdAllBorderWidth
          el.style.borderStyle = this.contents.btnAdAllBorderStyle
          el.style.borderColor = this.contents.btnAdAllBorderColor
          el.style.borderRadius = this.contents.btnAdAllBorderRadius
          el.style.backgroundColor = this.contents.btnAdAllDelBgColor
        })
        document.querySelectorAll('.form-content .ad .el-button--warning').forEach(el => {
          el.style.height = this.contents.btnAdAllHeight
          el.style.color = this.contents.btnAdAllWarnFontColor
          el.style.fontSize = this.contents.btnAdAllFontSize
          el.style.borderWidth = this.contents.btnAdAllBorderWidth
          el.style.borderStyle = this.contents.btnAdAllBorderStyle
          el.style.borderColor = this.contents.btnAdAllBorderColor
          el.style.borderRadius = this.contents.btnAdAllBorderRadius
          el.style.backgroundColor = this.contents.btnAdAllWarnBgColor
        })
      })
    },
    // 表格
    rowStyle({row, rowIndex}) {
      if (rowIndex % 2 == 1) {
        if (this.contents.tableStripe) {
          return {color: this.contents.tableStripeFontColor}
        }
      } else {
        return ''
      }
    },
    cellStyle({row, rowIndex}) {
      if (rowIndex % 2 == 1) {
        if (this.contents.tableStripe) {
          return {backgroundColor: this.contents.tableStripeBgColor}
        }
      } else {
        return ''
      }
    },
    headerRowStyle({row, rowIndex}) {
      return {color: this.contents.tableHeaderFontColor}
    },
    headerCellStyle({row, rowIndex}) {
      return {backgroundColor: this.contents.tableHeaderBgColor}
    },
    // 表格按钮
    contentTableBtnStyleChange() {
      // this.$nextTick(()=>{
      //   setTimeout(()=>{
      //     document.querySelectorAll('.table-content .tables .el-table__body .el-button--success').forEach(el=>{
      //       el.style.height = this.contents.tableBtnHeight
      //       el.style.color = this.contents.tableBtnDetailFontColor
      //       el.style.fontSize = this.contents.tableBtnFontSize
      //       el.style.borderWidth = this.contents.tableBtnBorderWidth
      //       el.style.borderStyle = this.contents.tableBtnBorderStyle
      //       el.style.borderColor = this.contents.tableBtnBorderColor
      //       el.style.borderRadius = this.contents.tableBtnBorderRadius
      //       el.style.backgroundColor = this.contents.tableBtnDetailBgColor
      //     })
      //     document.querySelectorAll('.table-content .tables .el-table__body .el-button--primary').forEach(el=>{
      //       el.style.height = this.contents.tableBtnHeight
      //       el.style.color = this.contents.tableBtnEditFontColor
      //       el.style.fontSize = this.contents.tableBtnFontSize
      //       el.style.borderWidth = this.contents.tableBtnBorderWidth
      //       el.style.borderStyle = this.contents.tableBtnBorderStyle
      //       el.style.borderColor = this.contents.tableBtnBorderColor
      //       el.style.borderRadius = this.contents.tableBtnBorderRadius
      //       el.style.backgroundColor = this.contents.tableBtnEditBgColor
      //     })
      //     document.querySelectorAll('.table-content .tables .el-table__body .el-button--danger').forEach(el=>{
      //       el.style.height = this.contents.tableBtnHeight
      //       el.style.color = this.contents.tableBtnDelFontColor
      //       el.style.fontSize = this.contents.tableBtnFontSize
      //       el.style.borderWidth = this.contents.tableBtnBorderWidth
      //       el.style.borderStyle = this.contents.tableBtnBorderStyle
      //       el.style.borderColor = this.contents.tableBtnBorderColor
      //       el.style.borderRadius = this.contents.tableBtnBorderRadius
      //       el.style.backgroundColor = this.contents.tableBtnDelBgColor
      //     })

      //   }, 50)
      // })
    },
    // 分页
    contentPageStyleChange() {
      let arr = []
      if (this.contents.pageTotal) arr.push('total')
      if (this.contents.pageSizes) arr.push('sizes')
      if (this.contents.pagePrevNext) {
        arr.push('prev')
        if (this.contents.pagePager) arr.push('pager')
        arr.push('next')
      }
      if (this.contents.pageJumper) arr.push('jumper')
      this.layouts = arr.join()
      this.contents.pageEachNum = 10
    },

    init() {
    },
    search() {
      this.pageIndex = 1;
      this.getDataList();
    },
    // 获取数据列表
    getDataList() {
      this.dataListLoading = true;
      let params = {
        page: this.pageIndex,
        limit: this.pageSize,
        sort: 'id',
      }


      if (this.searchForm.cheliangName != '' && this.searchForm.cheliangName != undefined) {
        params['cheliangName'] = '%' + this.searchForm.cheliangName + '%'
      }

      if (this.searchForm.cheliangTypes != '' && this.searchForm.cheliangTypes != undefined) {
        params['cheliangTypes'] = this.searchForm.cheliangTypes
      }

      if (this.searchForm.shangpinName != '' && this.searchForm.shangpinName != undefined) {
        params['shangpinName'] = '%' + this.searchForm.shangpinName + '%'
      }

      if (this.searchForm.shangpinTypes != '' && this.searchForm.shangpinTypes != undefined) {
        params['shangpinTypes'] = this.searchForm.shangpinTypes
      }

      if (this.searchForm.sijiUuidNumber != '' && this.searchForm.sijiUuidNumber != undefined) {
        params['sijiUuidNumber'] = '%' + this.searchForm.sijiUuidNumber + '%'
      }

      if (this.searchForm.sijiName != '' && this.searchForm.sijiName != undefined) {
        params['sijiName'] = '%' + this.searchForm.sijiName + '%'
      }

      if (this.searchForm.yonghuUuidNumber != '' && this.searchForm.yonghuUuidNumber != undefined) {
        params['yonghuUuidNumber'] = '%' + this.searchForm.yonghuUuidNumber + '%'
      }

      if (this.searchForm.yonghuName != '' && this.searchForm.yonghuName != undefined) {
        params['yonghuName'] = '%' + this.searchForm.yonghuName + '%'
      }

      if (this.searchForm.shangpinOrderUuidNumber != '' && this.searchForm.shangpinOrderUuidNumber != undefined) {
        params['shangpinOrderUuidNumber'] = '%' + this.searchForm.shangpinOrderUuidNumber + '%'
      }

      if (this.searchForm.shangpinOrderTypes != '' && this.searchForm.shangpinOrderTypes != undefined) {
        params['shangpinOrderTypes'] = this.searchForm.shangpinOrderTypes
      }

      params['shangpinOrderDelete'] = 1// 逻辑删除字段 1 未删除 2 删除


      this.$http({
        url: "shangpinOrder/page",
        method: "get",
        params: params
      }).then(({data}) => {
        if (data && data.code === 0) {
          this.dataList = data.data.list;
          this.totalPage = data.data.total;
        } else {
          this.dataList = [];
          this.totalPage = 0;
        }
        this.dataListLoading = false;
      });

      //查询级联表搜索条件所有列表
      this.$http({
        url: "dictionary/page?dicCode=cheliang_types&page=1&limit=100",
        method: "get",
        page: 1,
        limit: 100,
      }).then(({data}) => {
        if (data && data.code === 0) {
          this.cheliangTypesSelectSearch = data.data.list;
        }
      });
      this.$http({
        url: "dictionary/page?dicCode=shangpin_types&page=1&limit=100",
        method: "get",
        page: 1,
        limit: 100,
      }).then(({data}) => {
        if (data && data.code === 0) {
          this.shangpinTypesSelectSearch = data.data.list;
        }
      });
      //查询当前表搜索条件所有列表
      //填充下拉框选项
      this.$http({
        url: "dictionary/page?dicCode=shangpin_order_types&page=1&limit=100",
        method: "get",
      }).then(({data}) => {
        if (data && data.code === 0) {
          this.shangpinOrderTypesSelectSearch = data.data.list;
        }
      });
    },
    //每页数
    sizeChangeHandle(val) {
      this.pageSize = val;
      this.pageIndex = 1;
      this.getDataList();
    },
    // 当前页
    currentChangeHandle(val) {
      this.pageIndex = val;
      this.getDataList();
    },
    // 多选
    selectionChangeHandler(val) {
      this.dataListSelections = val;
    },
    // 添加/修改
    addOrUpdateHandler(id, type) {
      this.showFlag = false;
      this.addOrUpdateFlag = true;
      this.crossAddOrUpdateFlag = false;
      if (type != 'info') {
        type = 'else';
      }
      this.$nextTick(() => {
        this.$refs.addOrUpdate.init(id, type);
      });
    },
    // 下载
    download(file) {
      window.open(" ${file} ")
    },
    // // 弹出打印模态框
    // dayinOpen(item) {
    //   let _this = this;
    //   _this.printVisiable = true;
    //   _this.printFrom = item;
    // },

    // 弹出打印模态框
    tanchuOpen(id) {
      let _this = this;
      _this.xinjiande=id

      _this.printVisiable = true;
    },
    chakan(){
      // this.id=this.xinjiande

      let _this = this;
      _this.$confirm(`确定   选择车辆和司机 操作?`, "提示", {
        confirmButtonText: "确定", cancelButtonText: "取消", type: "warning"
      }).then(() => {
        _this.$http({
          url: "shangpinOrder/update",
          method: "post",
          data: {
            id: _this.xinjiande,
            cheliangId: _this.cheliangId,
            sijiId:_this.sijiId
//                            shangpinOrderTypes:shangpinOrderTypes,
          }
        }).then(({data}) => {
          if (data && data.code === 0) {
            _this.$message({
              message: "操作成功", type: "success", duration: 1500, onClose: () => {
                _this.search();
              }
            });
          } else {
            _this.$message.error(data.msg);
          }
        });
      });

      this.printVisiable = false;
    },
    // 打印内容
    printContent() {
      let _this = this;
      _this.$print(_this.$refs.printContent)
      _this.printVisiable = false;
    },
    // 删除
    deleteHandler(id) {
      var ids = id ? [Number(id)] : this.dataListSelections.map(item => {
        return Number(item.id);
      });

      this.$confirm(`确定进行[${id ? "删除" : "批量删除"}]操作?`, "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning"
      }).then(() => {
        this.$http({
          url: "shangpinOrder/delete",
          method: "post",
          data: ids
        }).then(({data}) => {
          if (data && data.code === 0) {
            this.$message({
              message: "操作成功",
              type: "success",
              duration: 1500,
              onClose: () => {
                this.search();
              }
            });
          } else {
            this.$message.error(data.msg);
          }
        });
      });
    },
    // 导入功能上传文件成功后调用导入方法
    shangpinOrderUploadSuccess(data) {
      let _this = this;
      _this.$http({
        url: "shangpinOrder/batchInsert?fileName=" + data.file,
        method: "get"
      }).then(({data}) => {
        if (data && data.code === 0) {
          _this.$message({
            message: "导入商品订单数据成功",
            type: "success",
            duration: 1500,
            onClose: () => {
              _this.search();
            }
          });
        } else {
          _this.$message.error(data.msg);
        }
      });

    },
    // 导入功能上传文件失败后调用导入方法
    shangpinOrderUploadError(data) {
      this.$message.error('上传失败');
    },

    //退款
    refund(id) {
      this.$confirm(`确定要退款吗?`, "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning"
      }).then(() => {
        this.$http({
          url: "shangpinOrder/refund?id=" + id,
          method: "post",
        }).then(({data}) => {
          if (data && data.code === 0) {
            this.$message({
              message: "操作成功",
              type: "success",
              duration: 1500,
              onClose: () => {
                this.search();
              }
            });
          } else {
            this.$message.error(data.msg);
          }
        });
      });
    },

    songda(id){
      let _this = this;
      _this.$confirm(`确定要送达吗?`, "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning"
      }).then(() => {
        _this.$http({
          url: "shangpinOrder/songda?id=" + id,
          method: "post",
        }).then(({data}) => {
          if (data && data.code === 0) {
            _this.$message({
              message: "操作成功",
              type: "success",
              duration: 1500,
              onClose: () => {
                this.search();
              }
            });
          } else {
            _this.$message.error(data.msg);
          }
        });
      });
    },

    //发出
    deliver(id) {
      let _this = this;
      _this.$confirm(`确定要发出吗?`, "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning"
      }).then(() => {
        _this.$http({
          url: "shangpinOrder/deliver?id=" + id,
          method: "post",
        }).then(({data}) => {
          if (data && data.code === 0) {
            _this.$message({
              message: "操作成功",
              type: "success",
              duration: 1500,
              onClose: () => {
                this.search();
              }
            });
          } else {
            _this.$message.error(data.msg);
          }
        });
      });
    },
    //验收
    receiving(id) {
      this.$confirm(`确定要验收吗?`, "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning"
      }).then(() => {
        this.$http({
          url: "shangpinOrder/receiving?id=" + id,
          method: "post",
        }).then(({data}) => {
          if (data && data.code === 0) {
            this.$message({
              message: "操作成功",
              type: "success",
              duration: 1500,
              onClose: () => {
                this.search();
              }
            });
          } else {
            this.$message.error(data.msg);
          }
        });
      });
    },
    //无用
    wuyong(id) {
      let _this = this;
      _this.$confirm(`确定    操作?`, "提示", {
        confirmButtonText: "确定", cancelButtonText: "取消", type: "warning"
      }).then(() => {
        _this.$http({
          url: "shangpinOrder/update",
          method: "post",
          data: {
            id: id,
//                            shangpinOrderTypes:shangpinOrderTypes,
          }
        }).then(({data}) => {
          if (data && data.code === 0) {
            _this.$message({
              message: "操作成功", type: "success", duration: 1500, onClose: () => {
                _this.search();
              }
            });
          } else {
            _this.$message.error(data.msg);
          }
        });
      });
    },
  }
};
</script>
<style lang="scss" scoped>
.slt {
  margin: 0 !important;
  display: flex;
}

.ad {
  margin: 0 !important;
  display: flex;
}

.pages {
  & /deep/ el-pagination__sizes {
    & /deep/ el-input__inner {
      height: 22px;
      line-height: 22px;
    }
  }
}


.el-button + .el-button {
  margin: 0;
}

.tables {
  & /deep/ .el-button--success {
    height: 40px;
    color: #333;
    font-size: 14px;
    border-width: 2px;
    border-style: dashed;
    border-color: rgba(255, 255, 255, 1);
    border-radius: 20px;
    background-color: var(--publicSubColor);
  }

  & /deep/ .el-button--primary {
    height: 40px;
    color: #333;
    font-size: 14px;
    border-width: 2px;
    border-style: dashed;
    border-color: rgba(255, 255, 255, 1);
    border-radius: 20px;
    background-color: var(--publicSubColor);
  }

  & /deep/ .el-button--danger {
    height: 40px;
    color: #333;
    font-size: 14px;
    border-width: 2px;
    border-style: dashed;
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

.tables /deep/ .el-table__body tr:hover > td {
  background-color: #f5f5f5 !important;
  color: #333 !important;
}</style>


