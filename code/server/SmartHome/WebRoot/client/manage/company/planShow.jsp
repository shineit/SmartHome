<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="s" uri="/struts-tags"%>
<script type="text/javascript">

	$(function() {
		$.pdialog.resizeDialog({
			style : {
				width : 660,
				height : 400
			}
		}, $.pdialog.getCurrent(), "");
		//dialog 宽度重新定义
    	//图片预览js
        $("#up1").uploadPreview({ Img: "ImgPr1",ImgType:["jpg","bmp","png"] });  
        		
		var operate = $("#planShowType").val();
		if(operate=="create")
		{
			$("#psCreate").css("display", "block");
			$("#psModify").css("display", "none");
		}
		if(operate=="modify")
		{
			$("#psCreate").css("display", "none");
			$("#psModify").css("display", "block");	
		}		
		});

</script>
<div class="pageContent">
	<s:form method="post" action="device/PlanManage" class="pageForm required-validate" onsubmit="return iframeCallback(this, dialogAjaxDone)" enctype="multipart/form-data"  >
		<div class="pageFormContent" layoutH="58">
		<input type="hidden" name="obj.planID" value="${obj.planID}"/>
			<input type="hidden" name="obj.buildingID" value="${obj.buildingID}"/>
			<input type="hidden" name="operateType" value="${operateType}" id="planShowType"/>
			<input type="hidden" name="oldPicName" value="${obj.picPath}"/>
			
			<dl style="width:50%;">
				<dt style="width:20%;">名称：</dt>			
				<dd style="width:65%;"><input type="text" name="obj.name"  size="25" value="${obj.name}" /></dd>		 
			</dl>
			<dl style="width:50%;">
				<dt style="width:20%;">楼层：</dt>			
				<dd style="width:65%;"><input type="text" name="obj.floor"  size="25" value="${obj.floor}"/></dd>		 
			</dl>
		 	<dl class="nowrap">
				<dt style="width:10%;">描述：</dt>			
				<dd style="width:80%;"><textarea name="obj.desp" cols="80" rows="3" maxlength="200">${obj.desp}</textarea></dd>
			</dl>
 
			<dl class="nowrap">
				<dt style="width:10%;">平面图：</dt>			
				<dd style="width:50%;">
						<div style="margin-bottom:5px; width:300px; height:180px; border:solid 1px #CCC; line-height:21px; background:#FFF;">
							<div>
								<img id="ImgPr1" src="<%=request.getContextPath()%>/upload/${obj.picPath}" height="180" width="300" alt="待上传图片">
							</div>
						</div>
				</dd>
				<dd style="width:25%;">
						<div>
							<s:file name="upload" id="up1"  ></s:file>
						</div>
						<span class="info">请上传jpg、bmp、png格式图片</span>
				</dd>		 
			</dl>

				
		</div>		
		<div class="formBar"  >
			<ul style="padding:0px 10px;" >
				<li style="margin:0px 15px;" id="psCreate"><s:submit method="create" value="创 建" cssClass="mispButton primary"></s:submit></li>
				<li style="margin:0px 15px;"  id="psModify"><s:submit method="modify" value="修 改" cssClass="mispButton primary"></s:submit></li>
				<li style="margin:0px 15px;"><button type="button" class="mispButton primary close" >关  闭</button></li>			

			</ul>
		</div>

	</s:form>
</div>
