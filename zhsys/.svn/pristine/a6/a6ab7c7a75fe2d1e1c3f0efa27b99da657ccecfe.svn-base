function innerTable(page){
	var total = 0;
	$("#hisTabel").empty();
	$.ajax({
		  url: "historyInfo.html",
		  data:{
			  starDate:$('#begintime').val(),
			  endDate :$('#endtime').val(),
			  page : page
		  },
		  dataType: 'json',
		  type: 'POST',
		  traditional : true,
		  async : false,
		  cache: false,
		  success: function(data,textStatus){
			  data = eval("(" + data + ")");
			var html =   hisHtml(data.list);
		  $("#hisTabel").empty();
		  $("#hisTabel").append(html);
		  total = data.SumMap.total;
		  }
		});
	
	$('#pager').pager({ pagenumber: page, pagecount: total, buttonClickCallback: PageClick});
}

function PageClick(pageclickednumber){
	innerTable(pageclickednumber);
}

$(document).ready(function() {
	innerTable(1);
	});


function hisHtml(data){
	var htmlTest = "<tr class='ta-first'><td>时间</td><td>产品代码</td><td>产品名称</td><td>经销商名称</td><td>数量</td><td>总价</td><td>交易状态</td></tr>";
	for(var o=0;o<data.length;o++){
		htmlTest = htmlTest +"<tr>";
		htmlTest = htmlTest + "<td>"+data[o].orderTimeStr+"</td>";
		htmlTest = htmlTest + "<td>"+data[o].goodsNum+"</td>";
		htmlTest = htmlTest + "<td>"+data[o].goodsName+"</td>";
		htmlTest = htmlTest + "<td>"+data[o].agencyName+"</td>";
		htmlTest = htmlTest + "<td>"+data[o].distributeNum+"</td>";
		htmlTest = htmlTest + "<td>"+(data[o].totalAmt)+"</td>";
		if(data[o].orderIdenty==1){
			htmlTest = htmlTest + "<td class='bq-tdrad'>失败</td>";
		}else if(data[o].orderIdenty==0 && data[o].payState==0){
			htmlTest = htmlTest + "<td class='bq-tdgreen'>未付款</td>";
		}else if(data[o].orderIdenty==0&& data[o].payState==1){
			htmlTest = htmlTest + "<td class='bq-tdgreen'>成功</td>";
		}
		htmlTest = htmlTest +"</tr>";
	}
	return htmlTest;
}