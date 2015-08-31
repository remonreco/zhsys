var flag=0;

function innerTable(){
	var page=document.getElementById("page").value;
	var orderId=document.getElementById("orderId").value;
	 $("#dataList").empty();
	$.ajax({
//		  url: "exQueryList.html?page="+page+"&flag="+flag+"&orderId="+orderId,
		url:"exQueryList.html",
		 data: JSON.stringify({ 'page': page, 'flag': flag, 'orderId': orderId }), 
		  dataType: 'json',
		  type: 'POST',
		  traditional : true,
		  contentType: "application/json; charset=utf-8",
		  async : true,
		  cache: false,
		  success: function(data){
			  data = eval("(" + data + ")");
			  var htmlText=orderList(data.orderList);
			  document.getElementById("totalPage").value=data.SumMap.totalPage;
			  document.getElementById("totalCount").innerHTML="共"+data.SumMap.count+"条";
			  document.getElementById("pageNum").innerHTML="第"+document.getElementById("pageNums").value+"页";
			  $("#dataList").empty();
			  $("#dataList").append(htmlText);
			  var trs = document.getElementsByTagName("tr");
			  for (var i = 0; i < trs.length; i++) {
	              if (i % 2 == 0) {
	                  trs[i].style.backgroundColor = "#f6f6f6";
	              }
	          }
		  
		  }
		});

}	
function firstPage(){
	document.getElementById("page").value=1;
	document.getElementById("pageNums").value=document.getElementById("page").value
	document.getElementById("pageNum").innerHTML="第"+document.getElementById("page").value+"页";
	innerTable();
}

function previousPage(){
	
	var textVal=document.getElementById("page").value;
	if(textVal>1){
		var t1=textVal--;
		document.getElementById("page").value=textVal--;
		document.getElementById("pageNums").value=document.getElementById("page").value;
		innerTable();
	}
	
}

function nextPage(){
	var textVal=parseInt(document.getElementById("page").value);
	var t1=textVal++;
	document.getElementById("page").value=textVal++;
	if(document.getElementById("page").value <= document.getElementById("totalPage").value){
		document.getElementById("pageNums").value=document.getElementById("page").value
		innerTable();
	}else{
		document.getElementById("page").value=document.getElementById("totalPage").value;
	}	
}

function lastPage(){
	var totalPage=document.getElementById("totalPage").value;
	document.getElementById("page").value=totalPage;
	document.getElementById("pageNums").value=document.getElementById("page").value
	innerTable();
}
function orderList(data){
	var seq=1;
	var htmlTest = "<tr class='ta-first'><td style='width:10px;'></td><td>商品名称</td><td>商品数量</td><td>兑换商品名称</td><td>兑换商品数量</td><td>兑换时间</td><td>订单状态</td><td>操作</td></tr>";
	if(data.length>0){
		for(var o=0;o<data.length;o++){
			htmlTest =htmlTest+"<tr>";
			htmlTest=htmlTest+"<td>"+ (seq+(document.getElementById("pageNums").value-1)*9)+"</td>";
			htmlTest=htmlTest+"<td>"+ data[o].beExchangeName+"</td>";
			htmlTest=htmlTest+"<td>"+ data[o].beExchangeNum+"</td>";
			htmlTest=htmlTest+"<td>"+ data[o].exchangeName+"</td>";
			htmlTest=htmlTest+"<td>"+ data[o].exchangeNum+"</td>";
			htmlTest=htmlTest+"<td>"+ data[o].orderTime1+"</td>";
			htmlTest=htmlTest+"<td>"+ data[o].orderState1+"</td>";
			if(data[o].orderState1=="申请中")
				htmlTest=htmlTest+"<td><a onclick=\"changeState(\'"+data[o].id+"\');\" class='sub'>撤单</td>";
			else
				htmlTest=htmlTest+"<td><a class='sub'></td>";
			htmlTest =htmlTest+"</tr>";
			seq++;
		}
	}	
	return htmlTest;
}


function changeState(id){
	
	$.ajax({
		  url: "checkState.html?id="+id,
		  dataType: 'json',
		  type: 'POST',
		  traditional : true,
		  contentType: "application/json; charset=utf-8",
		  async : true,
		  cache: false,
		  success: function(data){
			  data = eval("(" + data + ")");
			  var state=data.SumMap.orderState;
			  if(state!=1){
				  document.getElementById("putText2").style.display='block';
			  }else{
				  document.getElementById("putText").style.display='block';
			  }
			  
		  }
		});
	
	document.getElementById("orderId").value=id;
	
	
}

function closeDiv(){
	flag=1;
	document.getElementById("putText").style.display='none';
	innerTable();
}

function closeDiv1(){
	document.getElementById("putText").style.display='none';
}

function closeDiv2(){
	document.getElementById("putText2").style.display='none';
}

$(document).ready(function() {
	innerTable();
});

