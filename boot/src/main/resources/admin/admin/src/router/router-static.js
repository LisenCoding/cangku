import Vue from 'vue';
//配置路由
import VueRouter from 'vue-router'
Vue.use(VueRouter);
    // 解决多次点击左侧菜单报错问题
    const VueRouterPush = VueRouter.prototype.push
    VueRouter.prototype.push = function push (to) {
    return VueRouterPush.call(this, to).catch(err => err)
    }
//1.创建组件
import Index from '@/views/index'
import Home from '@/views/home'
import Login from '@/views/login'
import NotFound from '@/views/404'
import UpdatePassword from '@/views/update-password'
import pay from '@/views/pay'
import register from '@/views/register'
import center from '@/views/center'
import beifen from '@/views/modules/databaseBackup/beifen'
import huanyuan from '@/views/modules/databaseBackup/huanyuan'

     import users from '@/views/modules/users/list'
    import address from '@/views/modules/address/list'
    import cangku from '@/views/modules/cangku/list'
    import chat from '@/views/modules/chat/list'
    import cheliang from '@/views/modules/cheliang/list'
    import dictionary from '@/views/modules/dictionary/list'
    import gonggao from '@/views/modules/gonggao/list'
    import huowu from '@/views/modules/huowu/list'
    import shangpin from '@/views/modules/shangpin/list'
    import shangpinChuruInout from '@/views/modules/shangpinChuruInout/list'
    import shangpinChuruInoutList from '@/views/modules/shangpinChuruInoutList/list'
    import shangpinCollection from '@/views/modules/shangpinCollection/list'
    import shangpinOrder from '@/views/modules/shangpinOrder/list'
    import siji from '@/views/modules/siji/list'
    import yonghu from '@/views/modules/yonghu/list'
    import yuangong from '@/views/modules/yuangong/list'
    import config from '@/views/modules/config/list'
    import dictionaryCangku from '@/views/modules/dictionaryCangku/list'
    import dictionaryChat from '@/views/modules/dictionaryChat/list'
    import dictionaryCheliang from '@/views/modules/dictionaryCheliang/list'
    import dictionaryGonggao from '@/views/modules/dictionaryGonggao/list'
    import dictionaryHuowu from '@/views/modules/dictionaryHuowu/list'
    import dictionaryIsdefault from '@/views/modules/dictionaryIsdefault/list'
    import dictionarySex from '@/views/modules/dictionarySex/list'
    import dictionaryShangpin from '@/views/modules/dictionaryShangpin/list'
    import dictionaryShangpinChuruInout from '@/views/modules/dictionaryShangpinChuruInout/list'
    import dictionaryShangpinCollection from '@/views/modules/dictionaryShangpinCollection/list'
    import dictionaryShangpinOrder from '@/views/modules/dictionaryShangpinOrder/list'
    import dictionaryShangxia from '@/views/modules/dictionaryShangxia/list'
    import dictionaryZhuangtai from '@/views/modules/dictionaryZhuangtai/list'





//2.配置路由   注意：名字
const routes = [{
    path: '/index',
    name: '首页',
    component: Index,
    children: [{
      // 这里不设置值，是把main作为默认页面
      path: '/',
      name: '首页',
      component: Home,
      meta: {icon:'', title:'center'}
    }, {
      path: '/updatePassword',
      name: '修改密码',
      component: UpdatePassword,
      meta: {icon:'', title:'updatePassword'}
    }, {
      path: '/pay',
      name: '支付',
      component: pay,
      meta: {icon:'', title:'pay'}
    }, {
      path: '/center',
      name: '个人信息',
      component: center,
      meta: {icon:'', title:'center'}
    }, {
        path: '/huanyuan',
        name: '数据还原',
        component: huanyuan
    }, {
        path: '/beifen',
        name: '数据备份',
        component: beifen
    }, {
        path: '/users',
        name: '管理信息',
        component: users
    }
    ,{
        path: '/dictionaryCangku',
        name: '仓库类型',
        component: dictionaryCangku
    }
    ,{
        path: '/dictionaryChat',
        name: '数据类型',
        component: dictionaryChat
    }
    ,{
        path: '/dictionaryCheliang',
        name: '车辆类型',
        component: dictionaryCheliang
    }
    ,{
        path: '/dictionaryGonggao',
        name: '公告类型',
        component: dictionaryGonggao
    }
    ,{
        path: '/dictionaryHuowu',
        name: '货物类型',
        component: dictionaryHuowu
    }
    ,{
        path: '/dictionaryIsdefault',
        name: '是否默认地址',
        component: dictionaryIsdefault
    }
    ,{
        path: '/dictionarySex',
        name: '性别类型',
        component: dictionarySex
    }
    ,{
        path: '/dictionaryShangpin',
        name: '商品类型',
        component: dictionaryShangpin
    }
    ,{
        path: '/dictionaryShangpinChuruInout',
        name: '出入库类型',
        component: dictionaryShangpinChuruInout
    }
    ,{
        path: '/dictionaryShangpinCollection',
        name: '收藏表类型',
        component: dictionaryShangpinCollection
    }
    ,{
        path: '/dictionaryShangpinOrder',
        name: '订单类型',
        component: dictionaryShangpinOrder
    }
    ,{
        path: '/dictionaryShangxia',
        name: '上下架',
        component: dictionaryShangxia
    }
    ,{
        path: '/dictionaryZhuangtai',
        name: '状态',
        component: dictionaryZhuangtai
    }
    ,{
        path: '/config',
        name: '轮播图',
        component: config
    }


    ,{
        path: '/address',
        name: '收货地址',
        component: address
      }
    ,{
        path: '/cangku',
        name: '仓库',
        component: cangku
      }
    ,{
        path: '/chat',
        name: '反馈信息',
        component: chat
      }
    ,{
        path: '/cheliang',
        name: '车辆',
        component: cheliang
      }
    ,{
        path: '/dictionary',
        name: '字典',
        component: dictionary
      }
    ,{
        path: '/gonggao',
        name: '公告',
        component: gonggao
      }
    ,{
        path: '/huowu',
        name: '货物',
        component: huowu
      }
    ,{
        path: '/shangpin',
        name: '商品',
        component: shangpin
      }
    ,{
        path: '/shangpinChuruInout',
        name: '出入库',
        component: shangpinChuruInout
      }
    ,{
        path: '/shangpinChuruInoutList',
        name: '出入库详情',
        component: shangpinChuruInoutList
      }
    ,{
        path: '/shangpinCollection',
        name: '商品收藏',
        component: shangpinCollection
      }
    ,{
        path: '/shangpinOrder',
        name: '商品订单',
        component: shangpinOrder
      }
    ,{
        path: '/siji',
        name: '司机',
        component: siji
      }
    ,{
        path: '/yonghu',
        name: '用户',
        component: yonghu
      }
    ,{
        path: '/yuangong',
        name: '员工',
        component: yuangong
      }


    ]
  },
  {
    path: '/login',
    name: 'login',
    component: Login,
    meta: {icon:'', title:'login'}
  },
  {
    path: '/register',
    name: 'register',
    component: register,
    meta: {icon:'', title:'register'}
  },
  {
    path: '/',
    name: '首页',
    redirect: '/index'
  }, /*默认跳转路由*/
  {
    path: '*',
    component: NotFound
  }
]
//3.实例化VueRouter  注意：名字
const router = new VueRouter({
  mode: 'hash',
  /*hash模式改为history*/
  routes // （缩写）相当于 routes: routes
})

export default router;
