<template>
  <div class="container">
      <el-alert title="检查好账户哦" type="success" :closable="false"></el-alert>
      <el-row style="margin: 20px 0 0 20px;">
        充值金额:<el-input v-model="newMoney" placeholder="充值金额" style="width: 40%" clearable></el-input>
      </el-row>
      <div class="pay-type-content">
        <label>
          <div class="pay-type-item" :span="8">
            <el-radio v-model="type" label="微信支付"></el-radio>
            <img src="@/assets/img/test/weixin.png" alt>
          </div>
        </label>
        <label>
          <div class="pay-type-item" :span="8">
            <el-radio v-model="type" label="支付宝支付"></el-radio>
            <img src="@/assets/img/test/zhifubao.png" alt>
          </div>
        </label>
        <label>
          <div class="pay-type-item" :span="8">
            <el-radio v-model="type" label="建设银行"></el-radio>
            <img src="@/assets/img/test/jianshe.png" alt>
          </div>
        </label>
        <label>
          <div class="pay-type-item">
            <el-radio v-model="type" label="农业银行"></el-radio>
            <img src="@/assets/img/test/nongye.png" alt>
          </div>
        </label>
        <label>
          <div class="pay-type-item">
            <el-radio v-model="type" label="中国银行"></el-radio>
            <img src="@/assets/img/test/zhongguo.png" alt>
          </div>
        </label>
        <label>
          <div class="pay-type-item">
            <el-radio v-model="type" label="交通银行"></el-radio>
            <img src="@/assets/img/test/jiaotong.png" alt>
          </div>
        </label>
        <label>
          <div class="pay-type-item">
            <el-radio v-model="type" label="民生银行"></el-radio>
            <img src="@/assets/img/test/minsheng.png" alt>
          </div>
        </label>
        <label>
          <div class="pay-type-item">
            <el-radio v-model="type" label="工商银行"></el-radio>
            <img src="@/assets/img/test/gongshang.png" alt>
          </div>
        </label>
      </div>
    <div class="buton-content">
      <el-button @click="submitTap" type="primary">确认支付</el-button>
      <el-button @click="back()">返回</el-button>
    </div>
  </div>
</template>
<script>
// import { Message } from "element-ui";
export default {
  data() {
    return {
      sessionTable:"",//表
      role:"",//权限
      userId:"",//账户
      type:"支付宝支付",
      newMoney: "100",//充值金额
      user:{},
    };
  },
  mounted() {
      let _this =this;
      this.sessionTable = this.$storage.get("sessionTable");
      this.role = this.$storage.get("role");
      this.userId = this.$storage.get("userId");
      this.$http({
          url: `${this.sessionTable}/session`,
          method: "get"
      }).then(({ data }) => {
          if (data && data.code === 0) {
              _this.user = data.data;
          } else {
              _this.$message.error(data.msg);
          }
      });
  },
  methods: {
    submitTap() {
      let _this =this;
      if(_this.newMoney == null || _this.newMoney == "" || _this.newMoney <=0){
          this.$message.error("充值金额必须大于0");
          return false;
      }

      _this.$confirm(`确定充值么?`, "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning"
      }).then(() => {
        _this.$http({
          url: `${_this.sessionTable}/update`,
          method: "post",
          data: {
              id:_this.userId,
              newMoney:Number(_this.newMoney)+Number(_this.user.newMoney),
          }
        }).then(({ data }) => {
          if (data && data.code === 0) {
            _this.$message({
              message: "充值成功",
              type: "success",
              duration: 1500,
              onClose: () => {
                  _this.$router.replace({ path: "/center" });
              }
            });
          } else {
            this.$message.error(data.msg);
          }
        });
      });
    },
    back(){
      this.$router.replace({ path: "/center" });
    }
  }
};
</script>
<style lang="scss" scoped>
.container {
  margin: 10px;
  font-size: 14px;
  span {
    width: 60px;
  }
  .top-content {
    display: flex;
    align-items: center;
    padding: 20px;
  }
  .price-content {
    display: flex;
    align-items: center;
    margin-top: 20px;
    padding-bottom: 20px;
    padding: 20px;
    border-bottom: 1px solid #eeeeee;
    font-size: 20px;
    font-weight: bold;
    color: red;
  }
  .pay-type-content {
    display: flex;
    align-items: center;
    margin-top: 20px;
    flex-wrap: wrap;
    span {
      width: 100px;
    }
    .pay-type-item {
      display: flex;
      align-items: center;
      justify-content: space-between;
      width: 300px;
      margin: 20px;
      border: 1px solid #eeeeee;
      padding: 20px;
    }
  }
  .buton-content {
    margin: 20px;
  }
}
</style>
