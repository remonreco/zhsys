function innerTable(itemName){
	var total = 0;
	var page =document.getElementById("textVal").value;
	var rows=document.getElementById("selectVal").value;
	
	$("#exchange").empty();
	$.ajax({
	//	?itemName="+itemName+"&page="+page+"&rows="+rows
		  url:"exchangeInfo.html",
		  type: 'POST',
		  traditional : true,
		  contentType: "application/json",
		  dataType: 'json',
		  data: JSON.stringify({ 'itemName': itemName, 'page': page,'rows':rows}),
		  async : true,
		  cache: false,
		  success: function(data){
			  data = eval("(" + data + ")");
			  
			  var htmlText=query(data.typeList);
			  var html =   convertList2(data.list,data.SumMap.picPath);
		  $("#exchange").empty();
		  $("#productType").empty();
		  $("#productType").append(htmlText);
		  $("#exchange").append(html);
		  total = data.SumMap.total;
		  document.getElementById("totalPage").innerText=data.SumMap.totalPage;
		  if(page>data.SumMap.totalPage){
			  document.getElementById("textVal").value=data.SumMap.totalPage;
		  }
		  document.getElementById("total").innerText="共计"+total+"条记录";
		  }
		});

}	

/**
 *按项目名称查询
 * @param data
 * @returns {String}
 */
function query(data){
	var htmlTest=""
	if(data.length>0){
		htmlTest = htmlTest +"<p>";
		htmlTest = htmlTest +"<b>项目名称：</b><strong>";
			for(var o=0;o<data.length;o++){
//				htmlTest = htmlTest +"<a href="+"#"+" onclick="+"innerTable(1);>"+data[o].productType+"</a>";
				htmlTest = htmlTest +"<a class='dataStyle' href="+"#"+" onclick=\"innerTable(\'"+data[o].itemName+"\');\">"+data[o].itemName+"</a>";
			}
			htmlTest = htmlTest +"<a class='xiangStyle' href="+"#"+" onclick="+"innerTable('');>"+"不限</a></b>";	
		htmlTest = htmlTest +"</strong></p>";
	}
	return htmlTest;
}

function convertList2(data,picPath){
//	alert(picPath);
var seq=1;
var textVal=document.getElementById("textVal").value;
var selectVal=document.getElementById("selectVal").value;
var htmlTest = "<ul>";
if(data.length>0){
	for(var o=0;o<data.length;o++){
//		htmlTest = htmlTest +"<li><span><a href="+"#"+" onclick=\"exchangeDetail(\'"+data[o].comId+"\');\"><img class='img-class' src ="+picPath+data[o].comPicture+"></a></span>";
		htmlTest = htmlTest +"<li><span><a href="+"#"+" onclick=\"exchangeDetail(\'"+data[o].comId+"\');\"><img class='img-class' src ="+"showPicAction.html?filePath="+data[o].comPicture+"></a></span>";
		//htmlTest = htmlTest +"<li><span><a href="+"#"+" onclick=\"exchangeDetail(\'"+data[o].comId+"\');\"><img class='img-class' src ="+"images/tea.jpg></a></span>";
		htmlTest = htmlTest +"<div class='ex-changcot-mid-right'>";
		htmlTest = htmlTest +"<h4>"+data[o].comName+"<em>"+ (seq+parseInt((textVal-1))*parseInt(selectVal))+"</em></h4>";
		htmlTest = htmlTest +"<p><strong>时间：</strong><i>"+data[o].startDate1+"  至     "+data[o].endDate1+"</i></p>";
		htmlTest = htmlTest +"<p><strong>发售金额：</strong><i>"+data[o].saleMoney+"</i></p>";
		htmlTest = htmlTest +"<p><strong>流通数量：</strong><i>"+data[o].currencyNum+"</i></p>";
		htmlTest = htmlTest +"<p><strong>发售单位：</strong><i>"+data[o].saleUnit+"</i></p>";
		htmlTest = htmlTest +"<p><b><a href="+"#"+" onclick=\"exchangeDetail(\'"+data[o].comId+"\');\">兑换</b></a></p>";
//		htmlTest = htmlTest +"<p><b><a href="+"exchangeDetail.jsp?comId="+data[o].comId+">兑换</b></a></p>";
		htmlTest = htmlTest +"</div></li>";
		seq++;
	}
}	
htmlTest = htmlTest +"</ul>";
return htmlTest;
}


$(document).ready(function() {
	innerTable("");
});

function firstPage(){
	document.getElementById("textVal").value=1;
	innerTable("");
}

function previousPage(){
	var textVal=document.getElementById("textVal").value;
	if(textVal>1){
		var t1=textVal--;
		document.getElementById("textVal").value=textVal--;
		innerTable("");
	}
	
}

function nextPage(){
	var textVal=parseInt(document.getElementById("textVal").value);
	if(textVal<document.getElementById("totalPage").innerText){
		var t1=textVal++;
		document.getElementById("textVal").value=textVal++;
		innerTable("");
	}
}

function lastPage(){
	var totalPage=document.getElementById("totalPage").innerText;
	document.getElementById("textVal").value=totalPage;
	innerTable("");
}

function refresh(){
	innerTable("");
}

function selectChange(){
	
	innerTable("");
}	

function exchangeDetail(comId){
	$.ajax({
		  url: "exchangeDetail.html",
		  dataType: 'json',
		  type: 'POST',
		  traditional : true,
		  contentType: "application/json; charset=utf-8",
		  async : true,
		  cache: false,
		  success: function(data){
			  window.location.href="exchangeDetail.jsp?comId="+comId;
		  }
	});
}