<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="s" uri="/struts-tags"%>
<script type="text/javascript">
      $(function () {
			$.pdialog.resizeDialog({style: {width: 600,height:300,}}, 				
			$.pdialog.getCurrent(), "");
        });//dialog 宽度重新定义
</script>
<%
String infoContent=request.getParameter("infoContent");
 %>
<textarea style="width:98%;height:300px;font-size:1.5em;line-height:20px;" readonly="readonly">

<%=infoContent%>
</textarea>

