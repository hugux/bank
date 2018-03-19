<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>银行转账</title>
<script type="text/javascript" src="js/j.js"></script>
<script type="text/javascript">
	$(function() {
		var acout = false;
		var money = false;
		var acin = false;
		//校验转账账户和密码
		$("#apwd").blur(function() {
			$.post('bank',{oper:"acout",aid:$("#outid").val(),apwd:$("#apwd").val()},function(data) {
				if(data == 1) {
					$("#apwd").next().html("√").css("color","green");
					acout = true;
				}else {
					$("#apwd").next().html("×").css("color","red");
				}
			});
		});
		
		//校验金额
		$("#money").blur(function() {
			$.post('bank',{oper:"money",aid:$("#outid").val(),apwd:$("#apwd").val(),money:$("#money").val()},function(data) {
				if(data == 1) {
					$("#money").next().html("√").css("color","green");
					money = true;
				}else {
					$("#money").next().html("×").css("color","red");
				}
			});
		});
		
		//校验入账账户信息
		$("#aname").blur(function() {
			$.post('bank',{oper:"acin",inid:$("#inid").val(),aname:$("#aname").val(),aid:$("#outid").val()},function(data) {
				if(data == 1) {
					$("#aname").next().html("√").css("color","green");
					acin = true;
				}else {
					$("#aname").next().html("×").css("color","red");
				}
			});
		});
		
		//转账
		$("form").submit(function() {
			if(acout && money && acin) {
				return true;
			}else {
				return false;
			}
			if(acout && money && acin) {
				
			}
		});
	});
</script>
</head>
<body>
	<form action="bank" method="post">
		<input type="hidden" name="oper" value="transfer">
		转账账户：<input type="text" name="outid" id="outid" value=""><br>
		转账密码：<input type="password" name="apwd" id="apwd" value=""><span></span><br>
		金额：<input type="text" name="money" id="money" value=""><span></span><br> 
		入账账户:<input type="text" name="inid" id="inid" value=""><br>
		收款人姓名:<input type="text" name="aname" id="aname" value=""><span></span><br>
		<input type="submit" value="转账">
	</form>
</body>
</html>