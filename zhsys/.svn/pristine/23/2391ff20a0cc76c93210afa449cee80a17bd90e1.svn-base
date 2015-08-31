$(document).ready(function() {
	innerTable();
});


function innerTable(){
	var total = 0;
	var url = location.search;
	var comId=url.substring(7);
	document.getElementById("comId").value=comId;
	$("#exchangeGoods").empty();
	$.ajax({
		  url: "exchangeGoods.html",
		  dataType: 'json',
		  type: 'POST',
		  data: JSON.stringify({ 'comId': comId }), 
		  traditional : true,
		  contentType: "application/json; charset=utf-8",
		  async : true,
		  cache: false,
		  success: function(data){
			  data = eval("(" + data + ")");
			  document.getElementById("comName").value=data.SumMap.comName;
			  document.getElementById("currencyNum").value=data.SumMap.currencyNum;
			  document.getElementById("introduction").value=data.SumMap.introduction;
			  document.getElementById("freezeNum").value=data.SumMap.freezeNum;
			  document.getElementById("comPicture").value=data.SumMap.comPicture;
			  document.getElementById("saleUnit").value=data.SumMap.saleUnit;
			  document.getElementById("minNum").value=data.SumMap.minNum;
			  document.getElementById("shopSum").innerHTML="您必须至少兑换"+data.SumMap.minNum+"  "+data.SumMap.saleUnit+"   "+data.SumMap.comName;
			  var html =   exchangeGoods(data.crList);
		  $("#exchangeGoods").empty();
		  $("#exchangeGoods").append(html);
			document.getElementById("currencyNum1").value=document.getElementById("currencyNum").value;
			document.getElementById("freezeNum1").value=document.getElementById("freezeNum").value;
			document.getElementById("comId1").value =document.getElementById("comId").value;
			document.getElementById("comName1").value=document.getElementById("comName").value;
		  myHove();
		  }
		});

}	

function exchangeGoods(data){
	var currencyNum =document.getElementById("currencyNum").value;
	var introduction=document.getElementById("introduction").value;
	var freezeNum=document.getElementById("freezeNum").value;
	var comName=document.getElementById("comName").value;
	var saleUnit=document.getElementById("saleUnit").value;
	var comPicture= document.getElementById("comPicture").value;
	var htmlTest = "<div class='ex-changtwo-top'>";
	
		htmlTest = htmlTest +"<span><a><img class='img-class' src="+"showPicAction.html?filePath="+comPicture+"/></a></span>";
		htmlTest = htmlTest +"<form class='ex-changtwo-top-right' id='shopForm' name='shopForm' action='submitOrder.html'  method='post'>";
			
			htmlTest = htmlTest +"<input type='hidden' id='giveName2' name='giveName2' /><input type='hidden' id='giveSum1' name='giveSum1' /><input type='hidden' id='exchangeNum1' name='exchangeNum1' /></i></p><input type='hidden' id='comId1' name='comId1' /><input type='hidden' id='comName1' name='comName1' /><input type='hidden' id='freezeNum1' name='freezeNum1' /> <input type='hidden' id='currencyNum1' name='currencyNum1' /> <h3>"+comName+"</h3>";
			htmlTest = htmlTest +"<p><strong>剩 余 数 量 ：</strong> <em>"+currencyNum+"&nbsp;&nbsp;&nbsp;&nbsp;"+saleUnit+"</em></p>"
			htmlTest = htmlTest +"<p><strong>兑 换 数 量 ：</strong><input type='button' id='delete' onclick='deleteExchange()' value='-' class='inpt-reduce' /><input type='text' id='textVal' name='textVal' onchange='textChange()' onmouseout='textChange()' value='1'  class='inpt-number' />" +
									"<input type='button' onclick='addExchange()' id='add' value='+' class='inpt-add' /></p>"
			htmlTest = htmlTest +"<div class='like-secle clear'>";
				
				htmlTest = htmlTest +"<strong>选择兑换物 ：</strong>";
				htmlTest = htmlTest +"<ol id='olId' onMouseOver='myHove()'><li>请选择</li></ol><P id='testp' style='display:none;color:red'>&nbsp;&nbsp;&nbsp;您没有可用于兑换的商品</P>";
				htmlTest = htmlTest +"<div class='sect-add' id='divText'   style='display:none;'>";
				htmlTest = htmlTest +"<input type='hidden' id='dataLe' value="+data.length+">";
				htmlTest = htmlTest +"<input type='hidden' id='version' name='version' />";
					for(var i=0;i<data.length;i++){
						//得到对应exchangeNum并传给
							if(data[i].comType==1){
								htmlTest = htmlTest +"<input type='hidden' id='costNum5' name='costNum5'/>";
								htmlTest = htmlTest +"<input type='hidden' id='exId1' name='exId1'/>";
								htmlTest = htmlTest +"<input type='hidden' id='exName1' name='exName1'/>";
								htmlTest = htmlTest +"<em><a onclick=\"takeValue(\'"+data[i].exchangeId+"\',\'"+data[i].exchangeNum+"\',\'"+data[i].exchangeName+"\',\'"+data[i].saleUnit+"\');\">"+data[i].exchangeName+"</a></em>"
							}else{
									htmlTest = htmlTest +"<input type='hidden' id='exNum' name='exNums' value="+data[i].exchangeNum+">" ;
									htmlTest = htmlTest +"<input type='hidden' id='exName' name='exchangeNames' value="+data[i].exchangeName+">" ;
									htmlTest = htmlTest +"<input type='hidden' id='exchangeId' name='exchangeIds' value="+data[i].exchangeId+">" ;
									htmlTest = htmlTest +"<input type='hidden' id='exFreeze' name='exFreeeNumIds' value="+data[i].freezeNum+">" ;
									htmlTest = htmlTest +"<input type='hidden' id='exCurrecyNum' name='exCurrecyNumIds' value="+data[i].currencyNum+">" ;
								}
							//赠品
							
					}
				
				htmlTest = htmlTest +"</div>";
			htmlTest = htmlTest +"</div>";
			htmlTest = htmlTest +"<p id='test'><strong>花 费 数 量 ：</strong> <em class='currencyNum' id='costNumber'></em></p>";
			htmlTest = htmlTest +"<p style='display:none' id='giveIds'><strong>赠&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;品 ：</strong><i class='giveName' ></i></p>";
			htmlTest = htmlTest +"<p><strong>发 货 地 址 ：</strong> <input type='text' class='inpt-text' id='inpt-text' name='address' ></p>";
			htmlTest=htmlTest+"<input type='hidden' id='idFlag' name='idFlag' />";
			htmlTest = htmlTest +"<h4><input type='button' onclick='shop();' value='购买' /></h4>";
		htmlTest = htmlTest +"</form>";	
	htmlTest = htmlTest +"</div>";
	htmlTest = htmlTest +"<div class='ex-changtwo-cont'>";
		htmlTest = htmlTest +"<span></span>";
		htmlTest = htmlTest +"<b><del>商品介绍</del></b>";
		htmlTest = htmlTest +"<div class='ex-changtwo-text'>";
			htmlTest = htmlTest +"<h4>商品信息</h4>";
			htmlTest = htmlTest +" <p>"+introduction+"</p>";
		htmlTest = htmlTest +"</div>";
	htmlTest = htmlTest +"</div>";
	return htmlTest;
}

var flag=0;
function shop(){
	checkNum();
	if(isNaN(parseInt(document.getElementById("textVal").value))){
		document.getElementById("putText4").style.display='block';
	}else if(parseInt(document.getElementById("textVal").value)<parseInt(document.getElementById("minNum").value)){
		document.getElementById("putText3").style.display='block';
	}else if(document.getElementById("costNumber").innerText==""){
		document.getElementById("putText1").style.display='block';
	}else if(document.getElementById("inpt-text").value==""){
		document.getElementById("putText").style.display='block';
	}else{
			document.getElementById("bakcGround").style.display="block";
			document.getElementById("submitDiv").style.display="block";
			var costSum1=document.getElementById("costSum").value;
			var saleUnit1=document.getElementById("exSaleUnit").value;
			var name =document.getElementById("exchangeName").value;
			$('.submitDiv-class p .currencyNum1').text(costSum1+"       "+saleUnit1+"    "+name);
			var result=document.getElementById("resultFlag").value;
			$('.submitDiv-class p .giveName1 ').text(result);
			
	}
}

function closeDiv(){
	document.getElementById("putText").style.display='none';
	
	document.getElementById("putText1").style.display='none';
	document.getElementById("putText2").style.display='none';
	document.getElementById("putText3").style.display='none';
	document.getElementById("putText4").style.display='none';
	
	document.getElementById("bakcGround").style.display='none';
	document.getElementById("submitDiv").style.display='none';
}

function submitForm(value){
	if(value==1){
		if(flag=2){
			document.getElementById("bakcGround").style.display='none';
			document.getElementById("submitDiv").style.display='none';
			document.getElementById("idFlag").value=document.getElementById("test").value;
			document.getElementById("giveName2").value=document.getElementById("giveName").value;
			document.getElementById("giveSum1").value=document.getElementById("giveSum").value;
			document.getElementById("exchangeNum1").value =document.getElementById("exchangeNum").value;
			document.getElementById("costNum5").value =document.getElementById("costSum").value//
			document.getElementById("exId1").value =document.getElementById("exId").value;
			document.getElementById("exName1").value =document.getElementById("exchangeName").value;
			$("#shopForm").submit();
		}	
	}else{
		document.getElementById("bakcGround").style.display='none';
		document.getElementById("submitDiv").style.display='none';
	}
	
}

/**
 * 用于检测用户兑换数量是否大于流通数量
 */
function checkNum(){
	var comId=document.getElementById("comId").value;
	flag=1;
	$.ajax({
		  url: "checkNum.html?comId="+comId,
		  dataType: 'json',
		  type: 'POST',
		  traditional : true,
		  contentType: "application/json; charset=utf-8",
		  async : true,
		  cache: false,
		  success: function(data){
			  data = eval("(" + data + ")");
			  var currencyNum2=data.SumMap.currencyNum2;
			  var version = data.SumMap.version;
			  var currencyNum=document.getElementById("textVal").value;
			  if(parseInt(currencyNum2)<parseInt(currencyNum)){
				  document.getElementById("putText2").style.display='block';
			  }else{
				  document.getElementById("version").value = version;
				  flag=2;
			  }
			  
		  }
		});

}	

function myHove(){
    $('.like-secle ol li').click(function() {
    	if(document.getElementById("dataLe").value==0)
    		document.getElementById("testp").style.display='block';
		else{
				$(this).addClass('bordercs');
				$('.sect-add').css('display','block');
			}
  
    });
	
}

function addExchange(){
	
	var j=document.getElementById("textVal").value;
	j++;
	document.getElementById("textVal").value=j;
	takeValue(document.getElementById("exId").value,document.getElementById("exchangeNum").value,document.getElementById("exchangeName").value,document.getElementById("exSaleUnit").value);
}

function deleteExchange(){
	var j=document.getElementById("textVal").value;
	
	if(j==1){
		
		document.getElementById("textVal").value=1;
	}else{
		j--;
		document.getElementById("textVal").value=j;
	}
	
	takeValue(document.getElementById("exId").value,document.getElementById("exchangeNum").value,document.getElementById("exchangeName").value,document.getElementById("exSaleUnit").value);
}

function textChange(){
	var j=document.getElementById("textVal").value;
	if(!isNaN(parseInt(document.getElementById("textVal").value))){
		takeValue(document.getElementById("exId").value,document.getElementById("exchangeNum").value,document.getElementById("exchangeName").value,document.getElementById("exSaleUnit").value);
	}
}

/**
 * 选择兑换商品后得到赠品以及花费数量
 * @param num
 * @param name
 * @param saleUnit
 */
function  takeValue(id,num,name,saleUnit){
	 var exNames=document.getElementsByName("exchangeNames");//赠品名
	 var exIds=document.getElementsByName("exchangeIds");//赠品id
	 var exNums=document.getElementsByName("exNums");//赠品限数，也就是兑换多少商品才会送赠品
	 var exFreezes=document.getElementsByName("exFreeeNumIds");//赠品冻结数量
	 var exCurrecyNums=document.getElementsByName("exCurrecyNumIds");//赠品流通数量
	if(num>0&&name!=""){
	 $('.like-secle ol li').text(name);
	 $('.sect-add').css('display','none');
	 $('.like-secle ol li').removeClass('bordercs');
	
		 document.getElementById("exchangeNum").value=num;
		 document.getElementById("exchangeName").value=name;
		 document.getElementById("exSaleUnit").value=saleUnit;
		 document.getElementById("exId").value=id;
		 var j=document.getElementById("textVal").value;
		
			var giveSplit="";
			var excIds="";
			if(exNames.length>0){
				for(var i=0;i<exNames.length;i++){
					var result1=j/exNums[i].value;
					var result=parseInt(result1);
					document.getElementById("giveSum").value=result;
					
					checkGiftNum(exIds[i].value);
					if(result>0){
						excIds=excIds+exIds[i].value+"|"+document.getElementById("giveSum").value+"|"+exNames[i].value+"|"+exFreezes[i].value+"|"+exCurrecyNums[i].value+"=";
						giveSplit=giveSplit+document.getElementById("giveSum").value+saleUnit+exNames[i].value+"     和";
						
					}
				}
				
			}
			document.getElementById("test").value=excIds;
			var result=giveSplit.substr(0,giveSplit.length-1);
			if(document.getElementById("giveSum").value=="0"){
				result="   ";
			}
			document.getElementById("resultFlag").value=result;
			
		//花费数量
		 var sum=parseInt(num) * parseInt(j);
		 document.getElementById("costSum").value=sum;
		 $('.ex-changtwo-top-right p .currencyNum ').text(sum+"       "+saleUnit);//剩余数量
		 $('.submitDiv-class p .currencyNum1').text(sum+"            "+saleUnit);
		 if(document.getElementById("giveSum").value>0){
			 	$('.submitDiv-class p .giveName1 ').text(result);
		 }	 
		 document.getElementById("giveName").value=exchangeNames5;
	 }
}

/**
 * 检测赠品是否足够用于赠送
 * @param comId
 */
function checkGiftNum(comId){
	flag=1;
	$.ajax({
		  url: "checkGiftNum.html?comId="+comId,
		  dataType: 'json',
		  type: 'POST',
		  traditional : true,
		  contentType: "application/json; charset=utf-8",
		  async : false,
		  cache: false,
		  success: function(data){
			  data = eval("(" + data + ")");
			  var giftNum=data.SumMap.giftNum;
			  var giveSum=document.getElementById("giveSum").value;
			  if(parseInt(giftNum)<parseInt(giveSum)){
				  
				  document.getElementById("giveSum").value=giftNum;
			  }else{
				  
				  document.getElementById("giveSum").value=giveSum;
			  }
			  
		  }
		});

}




