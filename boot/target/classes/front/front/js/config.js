var projectName = '仓库物流管理系统';
/**
 * 轮播图配置
 */
var swiper = {
	// 设定轮播容器宽度，支持像素和百分比
	width: '100%',
	height: '400px',
	// hover（悬停显示）
	// always（始终显示）
	// none（始终不显示）
	arrow: 'none',
	// default（左右切换）
	// updown（上下切换）
	// fade（渐隐渐显切换）
	anim: 'default',
	// 自动切换的时间间隔
	// 默认3000
	interval: 2000,
	// 指示器位置
	// inside（容器内部）
	// outside（容器外部）
	// none（不显示）
	indicator: 'outside'
}

/**
 * 个人中心菜单
 */
var centerMenu = [
    {
        name: '个人中心',
        url: '../' + localStorage.getItem('userTable') + '/center.html'
    }
    ,{
        name: '收货地址',
        url: '../address/list.html'
    }
    ,{
        name: '货物',
        url: '../huowu/list.html'
    }
    ,{
        name: '商品收藏',
        url: '../shangpinCollection/list.html'
    }
    ,{
        name: '商品订单',
        url: '../shangpinOrder/list.html'
    }

]


var indexNav = [

    {
        name: '仓库',
        url: './pages/cangku/list.html'
    },
    {
        name: '公告',
        url: './pages/gonggao/list.html'
    },
    {
        name: '商品',
        url: './pages/shangpin/list.html'
    },

]

// 已废弃,不用了
var adminurl =  "http://localhost:8080/wuliuguanlixitong/admin/dist/index.html#/login";



// 后期要改
var menu = [
{"backMenu":[{"child":[{"buttons":["新增","查看","修改","删除"],"menu":"用户","menuJump":"列表","tableName":"yonghu"}],"menu":"用户管理"},{"child":[{"buttons":["新增","查看","修改","删除","查看评论"],"menu":"歌曲信息","menuJump":"列表","tableName":"gequxinxi"}],"menu":"歌曲信息管理"},{"child":[{"buttons":["查看","删除","查看评论"],"menu":"推荐信息","menuJump":"列表","tableName":"tuijianxinxi"}],"menu":"推荐信息管理"},{"child":[{"buttons":["新增","查看","修改","删除"],"menu":"我的收藏管理","tableName":"storeup"}],"menu":"我的收藏管理"},{"child":[{"buttons":["新增","查看","修改","删除"],"menu":"管理员","tableName":"users"}],"menu":"管理员管理"},{"child":[{"buttons":["新增","查看","修改","删除"],"menu":"轮播图管理","tableName":"config"}],"menu":"系统管理"}],"frontMenu":[{"child":[{"buttons":["查看","歌曲推荐","查看评论"],"menu":"歌曲信息列表","menuJump":"列表","tableName":"gequxinxi"}],"menu":"歌曲信息模块"},{"child":[{"buttons":["查看","查看评论"],"menu":"推荐信息列表","menuJump":"列表","tableName":"tuijianxinxi"}],"menu":"推荐信息模块"}],"hasBackLogin":"是","hasBackRegister":"否","hasFrontLogin":"否","hasFrontRegister":"否","roleName":"管理员","tableName":"users"},{"backMenu":[{"child":[{"buttons":["查看","歌曲推荐"],"menu":"歌曲信息","menuJump":"列表","tableName":"gequxinxi"}],"menu":"歌曲信息管理"},{"child":[{"buttons":["查看"],"menu":"推荐信息","menuJump":"列表","tableName":"tuijianxinxi"}],"menu":"推荐信息管理"},{"child":[{"buttons":["查看"],"menu":"我的收藏管理","tableName":"storeup"}],"menu":"我的收藏管理"}],"frontMenu":[{"child":[{"buttons":["查看","歌曲推荐","查看评论"],"menu":"歌曲信息列表","menuJump":"列表","tableName":"gequxinxi"}],"menu":"歌曲信息模块"},{"child":[{"buttons":["查看","查看评论"],"menu":"推荐信息列表","menuJump":"列表","tableName":"tuijianxinxi"}],"menu":"推荐信息模块"}],"hasBackLogin":"是","hasBackRegister":"否","hasFrontLogin":"是","hasFrontRegister":"是","roleName":"用户","tableName":"yonghu"}
]


var isAuth = function (tableName,key) {
    let role = localStorage.getItem("userTable");
    let menus = menu;
    for(let i=0;i<menus.length;i++){
        if(menus[i].tableName==role){
            for(let j=0;j<menus[i].backMenu.length;j++){
                for(let k=0;k<menus[i].backMenu[j].child.length;k++){
                    if(tableName==menus[i].backMenu[j].child[k].tableName){
                        let buttons = menus[i].backMenu[j].child[k].buttons.join(',');
                        return buttons.indexOf(key) !== -1 || false
                    }
                }
            }
        }
    }
    return false;
}

var isFrontAuth = function (tableName,key) {
    let role = localStorage.getItem("userTable");
    let menus = menu;
    for(let i=0;i<menus.length;i++){
        if(menus[i].tableName==role){
            for(let j=0;j<menus[i].frontMenu.length;j++){
                for(let k=0;k<menus[i].frontMenu[j].child.length;k++){
                    if(tableName==menus[i].frontMenu[j].child[k].tableName){
                        let buttons = menus[i].frontMenu[j].child[k].buttons.join(',');
                        return buttons.indexOf(key) !== -1 || false
                    }
                }
            }
        }
    }
    return false;
}
