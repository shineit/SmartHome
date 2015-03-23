<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="s" uri="/struts-tags"%>
<script type="text/javascript">

	$(function() {
		$.pdialog.resizeDialog({
			style : {
				width : 660,
				height : 360
			}
		}, $.pdialog.getCurrent(), "");
		});//dialog 宽度重新定义
		
	var operate = $("#sensorShowType").val();
	if(operate=="create")
	{
		$("#fsCreate").css("display", "block");
		$("#fsModify").css("display", "none");
	}
	if(operate=="modify")
	{
		$("#fsCreate").css("display", "none");
		$("#fsModify").css("display", "block");	
	}
</script>
<div class="pageContent">
	<s:form method="post" action="device/FireSensorManage" class="pageForm required-validate" onsubmit="return iframeCallback(this, dialogAjaxDone)"  >
		<div class="pageFormContent" layoutH="58">
		    <input type="hidden" name="obj.planNodeID" value="${obj.planNodeID}"/>
			<input type="hidden" name="obj.sensorType" value="${obj.sensorType}"/>
			<input type="hidden" name="operateType" value="${operateType}" id="sensorShowType"/>
			<dl style="width:50%;">
				<dt style="width:30%;">传感器编号：</dt>			
				<dd style="width:65%;"><input type="text" name="obj.id"  size="25" value="${obj.id}" readonly="readonly"/></dd>		 
			</dl>
			<dl style="width:50%;">
				<dt style="width:30%;">集中器编号：</dt>			
				<dd style="width:65%;"><input type="text" name="obj.concentratorID"  size="25" value="${obj.concentratorID}"/></dd>		 
			</dl>
			<dl style="width:50%;">
				<dt style="width:30%;">机号：</dt>			
				<dd style="width:65%;"><input type="text" name="obj.machineID"  size="25" value="${obj.machineID}"/></dd>		 
			</dl>
			<dl style="width:50%;">
				<dt style="width:30%;">回路号：</dt>			
				<dd style="width:65%;"><input type="text" name="obj.loopID"  size="25" value="${obj.loopID}"/></dd>		 
			</dl>
			<dl style="width:50%;">
				<dt style="width:30%;">点号：</dt>			
				<dd style="width:65%;"><input type="text" name="obj.codeID"  size="25" value="${obj.codeID}"/></dd>		 
			</dl>	
			<dl style="width:50%;">
				<dt style="width:30%;">位置描述：</dt>			
				<dd style="width:65%;">
				<input type="text" name="obj.locationDesp"  size="25"  value="${obj.locationDesp}"/>
				</dd>
			</dl>					
			<dl style="width:100%;">
				<dt style="width:15%;">传感器类型：</dt>			
				<dd style="width:80%;">
					<input  name="obj.sensorTypeName" type="text" size="25" value="${obj.sensorTypeName}" readonly="readonly"/>
					<a class="btnLook" href="device/SensorTypeManage" lookupGroup="obj" rel="" >类型列表</a>	
					<span class="info">(点击图标查找传感器类型)</span>
				
				</dd>
			</dl>
				
		</div>		

		<div class="formBar"  >
			<ul style="padding:0px 10px;" >
				<li style="margin:0px 15px;" id="fsCreate"><s:submit method="create" value="创 建" cssClass="mispButton primary"></s:submit></li>
				<li style="margin:0px 15px;"  id="fsModify"><s:submit method="modify" value="修 改" cssClass="mispButton primary"></s:submit></li>
				<li style="margin:0px 15px;"><button type="button" class="mispButton primary close" >关  闭</button></li>			

			</ul>
		</div>
	</s:form>
</div>
