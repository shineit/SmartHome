<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="s" uri="/struts-tags"%>

<div class="pageContent">
	<s:form method="post" action="device/FireSensorManage" class="pageForm required-validate" onsubmit="return iframeCallback(this, dialogAjaxDone)" enctype="multipart/form-data"  >
		<div class="pageFormContent" layoutH="58">
 
			<div>
					 <s:file name="upload"></s:file>
			 </div>
 									
				
		</div>		

		<div class="formBar"  >
			<ul style="padding:0px 10px;" >
				<li style="margin:0px 15px;" id="fsCreate"><s:submit method="uploadSensor" value="导入" cssClass="mispButton primary"></s:submit></li>
 				<li style="margin:0px 15px;"><button type="button" class="mispButton primary close" >关  闭</button></li>			

			</ul>
		</div>
	</s:form>
</div>
