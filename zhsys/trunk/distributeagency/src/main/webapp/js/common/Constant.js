var config = {
	loadMsg : '数据加载中，请稍候',
	pageList : [ 50, 100, 300, 500 ],
	pageSize : 50,
	tableHeight : 400,
	numValidType : 'length[1,6]'
};

/**
 * 格式化金额
 * 
 * @param num
 *            要格式化的金额(单位:分)
 * @param n
 *            保留小数位
 * @returns
 */
function formatNum(num, digit) {
	num = num * 0.01;
	num = String(num.toFixed(digit));
	var re = /(-?\d+)(\d{3})/;
	while (re.test(num))
		num = num.replace(re, "$1,$2");
	return num;
}

function formatNumTo(num){
	return formatNum(num, 2);
}

$.ajaxSetup({ 
	contentType:"application/x-www-form-urlencoded;charset=utf-8", 
	complete:function(XMLHttpRequest,textStatus){ 
		var sessionstatus=XMLHttpRequest.getResponseHeader("sessionstatus"); // 通过XMLHttpRequest取得响应头，sessionstatus，
		if(sessionstatus=="timeout"){ 
			// 如果超时就处理 ，指定要跳转的页面
			window.location.replace(ctx + "/timeout.jsp"); 
		} 
	} 
});

//扩展easyui表单的验证
$.extend($.fn.validatebox.defaults.rules, {
	//验证汉字
	CHS: {
		validator: function (value) {
			return /^[\u0391-\uFFE5]+$/.test(value);
		},
		message: '只能输入汉字'
	},
	//固定电话或手机号
	mobileOrTele: {//value值为文本框中的值
		validator: function (value) {
			var reg = /(\d{11})|^((\d{7,8})|(\d{4}|\d{3})-(\d{7,8})|(\d{4}|\d{3})-(\d{7,8})-(\d{4}|\d{3}|\d{2}|\d{1})|(\d{7,8})-(\d{4}|\d{3}|\d{2}|\d{1}))$/;
			return reg.test(value);
		},
		message: '输入固定电话或手机号码格式不准确.'
	},
	//移动手机号码验证
	mobile: {//value值为文本框中的值
		validator: function (value) {
			var reg = /^1[3|4|5|8|9]\d{9}$/;
			return reg.test(value);
		},
		message: '输入手机号码格式不准确.'
	},
	//数字验证
	numberV : {
		validator: function (value) {
			var reg = /^\d{1,20}$/;
			return reg.test(value);
		},
		message: '输入编号格式不准确.请输入数字类型'
	},
	//国内邮编验证
	zipcode: {
		validator: function (value) {
			var reg = /^[1-9]\d{5}$/;
			return reg.test(value);
		},
		message: '邮编必须是非0开始的6位数字.'
	},
	//经销商编号规则提示
	dealerNum : {
		validator: function (value) {
			var reg = /^[A-Z]{2,4}-.*?$/;
			return reg.test(value);
		},
		message: 'JJZS-XXXX（一级缩写）-(数字)'
	},
	//用户账号验证(只能包括 _ 数字 字母) 
	account: {//param的值为[]中值
		validator: function (value, param) {
			if (value.length < param[0] || value.length > param[1]) {
				$.fn.validatebox.defaults.rules.account.message = '用户名长度必须在' + param[0] + '至' + param[1] + '范围';
				return false;
			} else {
				if (!/^[\w]+$/.test(value)) {
					$.fn.validatebox.defaults.rules.account.message = '用户名只能数字、字母、下划线组成.';
					return false;
				} else {
					return true;
				}
			}
		}, message: ''
	}
});

/**
 * 时间对象的格式化
 */
Date.prototype.format = function(format) {
	format = "yyyy-MM-dd hh:mm:ss";
	var o = {
		"M+" : this.getMonth() + 1,
		"d+" : this.getDate(),
		"h+" : this.getHours(),
		"m+" : this.getMinutes(),
		"s+" : this.getSeconds(),
		"q+" : Math.floor((this.getMonth() + 3) / 3),
		"S" : this.getMilliseconds()
	};

	if (/(y+)/.test(format)) {
		format = format.replace(RegExp.$1, (this.getFullYear() + "")
				.substr(4 - RegExp.$1.length));
	}

	for ( var k in o) {
		if (new RegExp("(" + k + ")").test(format)) {
			format = format.replace(RegExp.$1, RegExp.$1.length == 1 ? o[k]
					: ("00" + o[k]).substr(("" + o[k]).length));
		}
	}
	return format;
};