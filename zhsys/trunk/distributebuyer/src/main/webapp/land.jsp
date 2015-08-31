<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<title>登陆页面</title>
<meta name="description" content="">
<meta name="keywords" content="">
<script type="text/javascript" src="js/jquery-1.8.3.js"></script>
<script type="text/javascript" src="js/jquery.cookie.js"></script>
<script type="text/javascript" src="js/pages/land.js"></script>
<script type="text/javascript" src='<c:url value="/js/jquery-1.11.2.min.js"/>'></script>
<script type="text/javascript" src='<c:url value="/js/jquery.easyui.min.js"/>'></script>
<script type="text/javascript" src='<c:url value="/js/easyui-lang-zh_CN.js"/>'></script>
<script type="text/javascript">
$(document).ready(function(){
	var isSavePwd=$.cookie("is-save-pwd");
	if(isSavePwd=="1"){
		$("#coks").attr("checked",true);
		$("#assetsAccount").val($.cookie("user-name"));
	}
	$("#assetsAccount").keyup(function(){
		var user_name=$("#assetsAccount").val();
		if(isSavePwd=="1"){
			var cun=$.cookie("user-name");
			if(user_name==cun){
				$("#assetsAccount").val($.cookie("user-name"));
			}
		}
	});
	$("#assetsAccount").change(function(){
		var user_name=$("#assetsAccount").val();
		$("#assetsAccount").val(user_name);
	});
	$(function(){
		document.onkeydown = function(e){ 
		    var ev = document.all ? window.event : e;
		    if(ev.keyCode==13) {
		    	loginCallback();
		     }
		};
	});
	
});

var loginCallback=function(){
	var user_name=$("#assetsAccount").val();
	var pwd=$("#salesCode").val();
	if(user_name != "" && pwd != ""){
		if($("#coks").attr("checked")==true || $("#coks").attr("checked")=="checked"){
			var expires={expires:7};
			$.cookie("user-name", user_name , expires);
			$.cookie("is-save-pwd", "1", expires);
		}else{
			$.cookie("user-name", null );
			$.cookie("is-save-pwd", null);
		}
		$("#sf").submit();
	}
	else{
		if($("#assetsAccount").val()==""){
			$("#assetsAccount").focus();
		}else{
			$("#salesCode").focus();
		}
	}
};

function cleanError(){
	document.getElementById("convertError").innerHTML="";
}
</script>
    <link rel="stylesheet" type="text/css" href="style/index.css" />
    </head>
    <body style="background-color:#f9f9f9;">
    <div class="bq-header">
        <div class="bq-headerc clear">
            <p><a href="/">网站地图 </a> | <a href="/"> 帮助中心</a></p>
            <span></span>
        </div>
    </div>
    <div class="bq-logo">
        <img src="images/logo.png" />
    </div>
    <div class="bq-header-bottom">
        <div class="bq-header-nav">
            <ul class="clear">
                <li class="first"><a href="<%=request.getContextPath()%>/land.jsp">线上认购登记系统</a></li>
                <li class="two">
                	<c:if test="${not empty sessionScope.username}">
                	<a href="<%=request.getContextPath()%>/convertList.jsp">线上兑换系统</a>
                	</c:if>
                	<a href="<%=request.getContextPath()%>/convert.jsp">线上兑换系统</a>
                </li>
         		<li><a href="http://www.cbice.com/" target="_black">国际版权网</a></li>
                <li><a href="http://www.e-artist.cn/" target="_black">艺术+</a></li>
                <li><a href="http://www.artcbice.com/" target="_black">投资+</a></li>
                <li><a href="http://www.piao.cbice.com/" target="_black">消费+</a></li>
                <li><a href="http://www.cbice.com/article/wsyyt/" target="_black">收藏+</a></li>
            </ul>
        </div>
    </div>
    <form id="sf" action="BuyerLogin.html" method="post">
    <div class="bq-lend-cont">
        <h2>线上认购登记系统</h2>
        <span>用户登录User Login</span>    
        <p><img src="images/username.png" ><input type="text" id="assetsAccount" name="assetsAccount" placeholder="请输入资产账号" data-placeholder="请输入资产账号" value="${sessionScope.assetsAccount}" onfocus="cleanError();"/></p>
        <p class="bq-password"><img src="images/password.png" ><input type="text" name="salesCode"  id="salesCode" placeholder="请输入销售码" data-placeholder="请输入销售码" value="" onfocus="cleanError();"/></p>
        <span style="text-align: center; padding: 5px">
             <input type="checkbox" class="loginFormCbx" style="vertical-align: middle;" id="coks" value="on" name="remember" />
              <label class="logintip" for="coks">记住账号</label></dt>
		</span>
         <c:if test="${not empty sessionScope.error}">
			<span style="color:red;text-align: center; padding: 5px">
                                  	  登录失败：
				${sessionScope.error}
			</span>
			<% request.getSession().removeAttribute("error"); %>
		</c:if>
        <h3><input type="button" onclick="loginCallback();" value="登录" /></h3>
    </div>  
    </form>
     <div class="bq-footer">
            <p><a>关于我们 </a>|<a> 在线咨询 </a>|<a> 法律声明 </a>|<a> 关于我们 </a>|<a> 人才招聘 </a>|<a> 联系我们 </a>|<a> 网站地图 </a>|<a> 站长统计 </a></p>
            <p>北京东方雍和国际版权交易中心版权所有 京ICP证140774号 京ICP备13013729号 京公网安备110105002453号</p>
    </div> 
    
    	
    </body>
</html>