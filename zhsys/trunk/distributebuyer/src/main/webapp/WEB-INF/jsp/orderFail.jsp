<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>兑换成功</title>
<link rel="stylesheet" type="text/css" href="style/buqn.css" />
<script type="text/javascript" src="js/jquery-1.8.3.js"></script>
</head>
<script type="text/javascript">

</script>
<body>
<form name="exAction"  action="exchange.html" method="post"></form>
<div class="op-fail" >
           <div class="op-fail-cont">
               <p>尊敬的用户，对不起！您的兑换申请失败，请重新申请！</p>
               <h5><a href="javascript:document.exAction.submit();" >确认</a></h5>
           		<!-- <h5><a href="<%=request.getContextPath()%>/exchange.jsp">确认</a></h5> -->
           </div>
       </div>
       
</body>
</html>