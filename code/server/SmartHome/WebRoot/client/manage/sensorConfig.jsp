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
</script>
<div class="pageContent">
	<s:form method="post" action="device/HomeSensorManage" class="pageForm required-validate" name="concentForm" onsubmit="return iframeCallback(this,dialogAjaxDone);"  >
		<div class="pageFormContent" layoutH="58">
            <input type="hidden" name="obj.sensorType" value="${obj.sensorType}"/>
            <input type="hidden" name="obj.status" value="${obj.status}"/>
			<dl style="width:100%;">
				<dt style="width:15%;">传感器编号：</dt>			
				<dd style="width:80%;"><input type="text" name="obj.id"  size="25" readonly="readonly" value="${obj.id}"/></dd>		 
			</dl>

			<dl style="width:100%;">
				<dt style="width:15%;">传感器类型：</dt>			
				<dd style="width:80%;">
					<input  name="obj.sensorTypeName" type="text" size="25" value="${obj.sensorTypeName}" readonly="readonly"/>
					<a class="btnLook" href="device/SensorTypeManage" lookupGroup="obj" rel="" >类型列表</a>	
					<span class="info">(点击图标查找传感器类型)</span>
				
				</dd>
			</dl>
						
			<dl style="width:50%;">
				<dt style="width:30%;">预警值：</dt>			
				<dd style="width:65%;">
				<input type="text" name="obj.warnValue"  size="25"  value="${obj.warnValue}"/>
				</dd>
			</dl>	
			<dl style="width:50%;">
				<dt style="width:30%;">告警值：</dt>			
				<dd style="width:65%;">
				<input type="text" name="obj.errorValue"  size="25"  value="${obj.errorValue}"/>
				</dd>
			</dl>	

			<dl style="width:50%;">
				<dt style="width:30%;">联动控制终端：</dt>			
				<dd style="width:65%;">
				<input type="text" name="obj.ctrSensorID"  size="25"  value="${obj.ctrSensorID}"/>
				</dd>
			</dl>
			<dl style="width:50%;">
				<dt style="width:30%;">联动控制通道：</dt>			
				<dd style="width:65%;">
				<input type="text" name="obj.ctrChannelID"  size="25"  value="${obj.ctrChannelID}"/>
				</dd>
			</dl>
			<dl style="width:50%;">
				<dt style="width:30%;">标签：</dt>			
				<dd style="width:65%;">
				<input type="text" name="obj.mark"  size="25"  value="${obj.mark}"/>
				</dd>
			</dl>
			<dl style="width:100%;">
				<dt style="width:15%;">描述：</dt>			
				<dd style="width:80%;"><textarea name="obj.description" cols="80" rows="2" maxlength="13">${obj.description}</textarea></dd>
			</dl>					
		</div>		

		<div class="formBar"  >
			<ul style="padding:0px 10px;" >
				<li style="margin:0px 15px;" ><s:submit method="modify" value="修  改" cssClass="mispButton primary"></s:submit></li>

				<li style="margin:0px 15px;"><button type="button" class="mispButton primary close" >关  闭</button></li>			

			</ul>
		</div>
	</s:form>
</div>
