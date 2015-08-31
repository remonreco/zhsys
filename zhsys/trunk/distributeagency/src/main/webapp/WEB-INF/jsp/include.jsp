<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE8" />
<link rel="stylesheet" type="text/css" href='<c:url value="/css/easyui/icon.css"/>' />
<link rel="stylesheet" type="text/css" href='<c:url value="/css/easyui/default/easyui.css"/>' />
<link rel="stylesheet" type="text/css" href='<c:url value="/css/main.css"/>' />
<script type="text/javascript" src='<c:url value="/js/jquery-1.11.2.min.js"/>'></script>
<script type="text/javascript" src='<c:url value="/js/jquery.easyui.min.js"/>'></script>
<script type="text/javascript" src='<c:url value="/js/easyui-lang-zh_CN.js"/>'></script>
<script type="text/javascript" src='<c:url value="/js/common/Constant.js"/>'></script>
<c:url var="ctx" value="/" />
<script type="text/javascript">
	var ctx = '${ctx}';
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
	}
</script>
<c:url var="ctx" value="/" />