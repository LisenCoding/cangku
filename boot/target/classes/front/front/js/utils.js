/**
 * 页面跳转
 * @param {Object} url
 */
function jump(url) {
	if (!url || url == 'null' || url == null) {
		window.location.href = './index.html';
	}
	// 路径访问设置
	localStorage.setItem('iframeUrl', url.replace('../', './pages/'));
	window.location.href = url;
}

/**
 * 返回
 */
function back(num = -1) {
	window.history.go(num)
}

/**
 * 生成订单
 */
function genTradeNo() {
	var date = new Date();
	var tradeNo = date.getFullYear().toString() + (date.getMonth() + 1).toString() +
		date.getDate().toString() + date.getHours().toString() + date.getMinutes().toString() +
		date.getSeconds().toString() + date.getMilliseconds().toString();
	for (var i = 0; i < 5; i++) //5位随机数，用以加在时间戳后面。
	{
		tradeNo += Math.floor(Math.random() * 10);
	}
	return tradeNo;
}


/*
* 分享路径
* */
function createElement(text) {
	var isRTL = document.documentElement.getAttribute('dir') === 'rtl';
	var element = document.createElement('textarea');
	// 防止在ios中产生缩放效果
	element.style.fontSize = '12pt';
	// 重置盒模型
	element.style.border = '0';
	element.style.padding = '0';
	element.style.margin = '0';
	// 将元素移到屏幕外
	element.style.position = 'absolute';
	element.style[isRTL ? 'right' : 'left'] = '-9999px';
	// 移动元素到页面底部
	let yPosition = window.pageYOffset || document.documentElement.scrollTop;
	element.style.top = `${yPosition}px`;
	//设置元素只读
	element.setAttribute('readonly', '');
	element.value = text;
	document.body.appendChild(element);
	return element;
}

/**
* 获取当前时间（yyyy-MM-dd hh:mm:ss）
*/
function getCurDateTime() {
	var currentTime = new Date(),
	year = currentTime.getFullYear(),
	month = currentTime.getMonth() + 1 < 10 ? '0' + (currentTime.getMonth() + 1) : currentTime.getMonth() + 1,
	day = currentTime.getDate() < 10 ? '0' + currentTime.getDate() : currentTime.getDate(),
	hour = currentTime.getHours(),
	minute = currentTime.getMinutes(),
	second = currentTime.getSeconds();
    return year + "-" + month + "-" + day + " " +hour +":" +minute+":"+second;
}

/**
* 获取年
*/
function getYearFormat(currentTime) {
	if(currentTime==null){
		currentTime = new Date();
	}
	var year = currentTime.getFullYear();
	return year;
}

/**
 * 获取月
 */
function getMonthFormat(currentTime) {
	if(currentTime==null){
		currentTime = new Date();
	}
	var year = currentTime.getFullYear();
	var month = currentTime.getMonth() + 1 < 10 ? '0' + (currentTime.getMonth() + 1) : currentTime.getMonth() + 1;
	return year + "-" + month;
}


/**
 *日期格式化（yyyy-MM-dd）
 */
function getDateFormat(currentTime) {
	if(currentTime==null){
		currentTime = new Date();
	}
	var year = currentTime.getFullYear();
	var month = currentTime.getMonth() + 1 < 10 ? '0' + (currentTime.getMonth() + 1) : currentTime.getMonth() + 1;
	var day = currentTime.getDate() < 10 ? '0' + currentTime.getDate() : currentTime.getDate();
	return year + "-" + month + "-" + day;
}


/**
 * 时间格式化（yyyy-MM-dd hh:mm:ss）
 */
function getDatetimeFormat(currentTime) {
	if(currentTime==null){
		currentTime = new Date();
	}
	var year = currentTime.getFullYear();
	var month = currentTime.getMonth() + 1 < 10 ? '0' + (currentTime.getMonth() + 1) : currentTime.getMonth() + 1;
	var day = currentTime.getDate() < 10 ? '0' + currentTime.getDate() : currentTime.getDate();
	var hour = currentTime.getHours();
	var minute = currentTime.getMinutes();
	var second = currentTime.getSeconds();
	return year + "-" + month + "-" + day + " " +hour +":" +minute+":"+second;
}