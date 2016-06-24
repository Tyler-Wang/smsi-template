<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page language="java" import="org.apache.log4j.LogManager" %>
<%@ page language="java" import="org.apache.log4j.Level" %>
<%@ page language="java" import="org.apache.log4j.Logger" %>
<%
	String loggerName = request.getParameter("loggerName");
	String logLevel = request.getParameter("logLevel");
	
	if(loggerName != null && !loggerName.equals("")){
		Logger rootLogger = Logger.getRootLogger();
		synchronized (rootLogger) {
			Logger customLogger = Logger.getLogger(loggerName);
			customLogger.setLevel(Level.toLevel(logLevel));
		}
	}

%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>动态设置日志Logger</title>
<style type="text/css">
	#logger-name{
		width:600px;
	}
</style>
</head>
<body>
<form name="myfrom" id="myform"  post="post" action="dynamicLogger.jsp"> 
	<div><label>Logger名称:</label><input name="loggerName" id="logger-name" type="text" value="com.ake.its.vems.api.ytj.upward.cache.RecordCache"/></div>
	<div>
		<label>日志级别:</label>
		<select id="log-level" name="logLevel">
			<option value="DEBUG">DEBUG</option>
			<option value="INFO">INFO</option>
		</select>
	</div>
	<div><input type="button" id="btn" value="设置" onclick="set()"/></div>
</form>
<script type="text/javascript">
	function set(){
		var loggerName = document.getElementById("logger-name").value;
		if(loggerName){
			document.getElementById("myform").submit();
		}else{
			alert('请填写正确的Logger名称');
		}
	}
</script>
</body>
</html>