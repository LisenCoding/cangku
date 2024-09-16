<template>
    <div class="main-content">
        <!-- 列表页 -->
        <div v-if="!showFlag">
            <div class="table-content">
                <el-table
                        :data="dataList"
                        empty-text="暂无需要回复的消息"
                        border
                        v-loading="dataListLoading"
                        style="width: 100%;"
                >
                    <el-table-column prop="chatIssue" header-align="center" align="center" sortable label="新消息"></el-table-column>
                    <el-table-column prop="issueTime" header-align="center" align="center" sortable label="发送时间"></el-table-column>
                    <el-table-column
                            prop="allnode"
                            header-align="center"
                            align="center"
                            sortable
                            label="状态"
                            width="150"
                    >
                        <template slot-scope="scope">
                            <el-tag v-if="" :type="scope.row.zhuangtaiTypes==1?'success':'info'">{{scope.row.zhuangtaiValue}}</el-tag>
                        </template>
                    </el-table-column>
                    <el-table-column
                            fixed="right"
                            header-align="center"
                            align="center"
                            width="150"
                            label="操作"
                    >
                        <template slot-scope="scope">
                            <el-button
                                    type="text"
                                    icon="el-icon-edit"
                                    size="small"
                                    @click="addOrUpdateHandler(scope.row)"
                            >回复</el-button>
                        </template>
                    </el-table-column>
                </el-table>
                <el-pagination
                        @size-change="sizeChangeHandle"
                        @current-change="currentChangeHandle"
                        :current-page="pageIndex"
                        :page-sizes="[10, 20, 50, 100]"
                        :page-size="pageSize"
                        :total="totalPage"
                        layout="total, sizes, prev, pager, next, jumper"
                        class="pagination-content"
                ></el-pagination>
            </div>
        </div>
        <!-- 添加/修改页面  将父组件的search方法传递给子组件-->
        <add-or-update v-else :parent="this" ref="addOrUpdate"></add-or-update>
    </div>
</template>
<script>
    import AddOrUpdate from "./add-or-update";
    import { setInterval, clearInterval } from 'timers';
    export default {
        data() {
            return {
                searchForm: {},
                dataList: [],
                pageIndex: 1,
                pageSize: 10,
                totalPage: 0,
                dataListLoading: false,
                showFlag: false,
                dataListSelections: [],
                inter: null
            };
        },
        created() {
            var that = this;
            var inter = setInterval(function(){
                that.getDataList();
            },5000);
            this.inter = inter;
        },
        destroyed(){
            clearInterval(this.inter);
        },
        components: {
            AddOrUpdate
        },
        methods: {
            getDataList() {
                this.dataListLoading = true;
                this.$http({
                    url: 'chat/page',
                    method: "get",
                    params: {
                        page: this.pageIndex,
                        limit: this.pageSize,
                        sort: 'id',
                        zhuangtaiTypes: 1,
                        chatTypes: 1,
                    }
                }).then(({ data }) => {
                    if (data && data.code === 0) {
                    this.dataList = data.data.list;
                    this.totalPage = data.data.total;
                } else {
                    this.dataList = [];
                    this.totalPage = 0;
                }
                this.dataListLoading = false;
            });
            },
            // 每页数
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
            // 回复
            addOrUpdateHandler(row) {
                this.showFlag = true;
                this.$nextTick(() => {
                    this.$refs.addOrUpdate.init(row);
            });
            }
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


    .el-button+.el-button {
        margin: 0;
    }

    .tables {
    & /deep/ .el-button--success {
          height: 36px;
          color: rgba(40, 167, 69, 1);
          font-size: 10px;
          border-width: 0px;
          border-style: solid;
          border-color: #DCDFE6;
          border-radius: 0px;
          background-color: rgba(255, 255, 255, 1);
      }

    & /deep/ .el-button--primary {
          height: 36px;
          color: rgba(255, 193, 7, 1);
          font-size: 10px;
          border-width: 0px;
          border-style: solid;
          border-color: #DCDFE6;
          border-radius: 0px;
          background-color: #fff;
      }

    & /deep/ .el-button--danger {
          height: 36px;
          color: rgba(220, 53, 69, 1);
          font-size: 10px;
          border-width: 0px;
          border-style: solid;
          border-color: #DCDFE6;
          border-radius: 0px;
          background-color: #fff;
      }

    & /deep/ .el-button {
          margin: 4px;
      }
    }
</style>


