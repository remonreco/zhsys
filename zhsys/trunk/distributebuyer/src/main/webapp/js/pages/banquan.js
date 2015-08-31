/**
 * 
 * @authors Your Name (you@example.org)
 * @date    2015-03-13 10:53:31
 * @version $Id$
 */
/*历史页面jq*/
$(function(){
    var i="";  //全局变量
       $("table td a")
         .mouseover(function(t){  //鼠标移入
             i=$(this).attr("title") //定义一个变量
             var tip=$("<div id='box'>"+i+"</div>")  //给tip添加属性
             tip.appendTo($("body"))  //把title放到文本中
             $(this).attr("title","");//清空title
             $("#box").css({           //添加鼠标的css属性
                "left":t.pageX+10+"px", //
                "top":t.pageY+10+"px"
              })
        
          })
         .mouseout(function(){ //鼠标移出
            $("#box").remove()  //清空box
            $(this).attr("title",i) //更改title的属性
          })
         .mousemove(function(t){  //鼠标移动
              $("#box").css({  
                "left":t.pageX+10+"px",
                "top":t.pageY+20+"px"
              })
          }) 
})


/* 支付平台*/
$(function(){
    
    var ulen = $('.user-bank li:gt(10):not(:last)')
        ulen.hide();

    $('.user-bank li:last').click(function(){
        if(ulen.is(":hidden")){
            ulen.show();
            $(this).css('background-position',' right -22px');
        }
        else{
            ulen.hide();
            $(this).css('background-position',' right 4px');
        }
    })
    
 
})

/* 商品详情页*/


$(function(){
     $('.bq-goods-contmd-boot a').click(function(){
        $('.bq-zhezhao').show();

        $('.bq-zhezhaocont-top img').click(function(){
           $('.bq-zhezhao').hide(); 
        })
       
    })

      $('.bq-zhezhaocont-fot input').click(function(){
        
            $('.bq-zhezhao').hide();
            $('#intruedice').attr("checked",true);
            var check=$('#intruedice').is(':checked');
            if(check){
             $('.chang-button input').removeClass('bq-backgrdto');
             $('.chang-button input').addClass('bq-backgrd');
             $('.bq-goods-contmd-boot em').hide();
            }
           
        })

})

function checkBox(){
    var chec = $('#intruedice').attr("checked");
    if(chec){
      $('.chang-button input').removeClass('bq-backgrdto');
      $('.chang-button input').addClass('bq-backgrd');
      $('.bq-goods-contmd-boot em').hide();
    }
    else{
        $('.chang-button input').removeClass('bq-backgrd');
        $('.chang-button input').addClass('bq-backgrdto');
        $('.bq-goods-contmd-boot em').show();
    }
}

/*立即购买*/
function nowBuy(){
	if(!document.getElementById("intruedice").checked){
		alert("您需要同意该协议，继续支付操作！");
		return;
	}
	document.getElementById("myform").submit();
}

/*我要退单*/
function notBuy(){
		if(confirm('确定退单?')){
			document.getElementById("returnform").submit();
		}
}

function selectByTime(){
	var begintime=$("#begintime").val();
	var endtime=$("#endtime").val();
	$("#begintimehid").val(begintime);
	$("#endtimehid").val(endtime);
	document.historyfm.submit();
	/*$.ajax({
		type: 'POST',
		url: $("#historyfm").val(),
		data: {
			begintime: begintime,
			endtime:endtime
		},
		dataType: 'text',
		async: false,
		cache : false,
		traditional: true,
		success: function(data) {
			
		}
	});*/
}


/*下一步*/
function next(){
	if(!(document.getElementById("yue-pay").checked || document.getElementById("bank-pay").checked)){
		alert("请选择一种支付方式！");
		return ;
	}
	if(confirm("是否确认支付?")) { 
		document.getElementById("payform").submit();
	}
}