<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="s" uri="/struts-tags"%>
<script type="text/javascript">
      $(function () {
			$.pdialog.resizeDialog({style: {width: 660,height:420}}, 				
			$.pdialog.getCurrent(), "");
        });//dialog 宽度重新定义
        
    $(function () 
    {
    	//图片预览js
        $("#up1").uploadPreview({ Img: "ImgPr1",ImgType:["jpg","bmp","png"] });   
        var operate = $("#adShowType").val();
	if(operate=="create")
	{
		$("#adCreate").css("display", "block");
		$("#adModify").css("display", "none");
	}
	if(operate=="modify")
	{
		$("#adCreate").css("display", "none");
		$("#adModify").css("display", "block");	
	}     
     });
</script>
<div class="pageContent">
	<s:form method="post" action="mall/AdManage" class="pageForm required-validate"  onsubmit="return iframeCallback(this,dialogAjaxDone);" enctype="multipart/form-data" >
		<div class="pageFormContent" layoutH="58">
			<input type="hidden" name="obj.adID" value="${obj.adID}"/>
			<input type="hidden" name="operateType" value="${operateType}" id="adShowType"/>
			<input type="hidden" name="oldPicName" value="${obj.adImg}"/>

			<dl style="width:50%;">
				<dt style="width:20%;">广告名称：</dt>			
				<dd style="width:70%;"><input type="text" name="obj.adName"  size="30" value="${obj.adName}" class="required"/></dd>
			</dl>
 
			<dl class="nowrap">
				<dt style="width:10%;">广告描述：</dt>			
				<dd style="width:80%;"><textarea name="obj.adInfo" cols="80" rows="4" maxlength="200">${obj.adInfo}</textarea></dd>
			</dl>
			<dl class="nowrap">
				<dt style="width:10%;">链接地址：</dt>			
				<dd style="width:80%;"><textarea name="obj.adURL" cols="80" rows="2" maxlength="200">${obj.adURL}</textarea></dd>
			</dl>
			<dl class="nowrap">
				<dt style="width:10%;">广告图片：</dt>			
				<dd style="width:50%;">
						<div style="width:250px; height:150px; border:solid 1px #CCC; line-height:21px; background:#FFF;">
							<div>
								<img id="ImgPr1" src="<%=request.getContextPath()%>/upload/${obj.adImg}" height="150" width="250" alt="待上传图片">
							</div>
						</div>
				</dd>
				<dd style="width:30%;">
						<div>
							<s:file name="upload" id="up1"  ></s:file>
						</div>
						<span class="info">上传图片只支持bmp与jpg格式</span>
				</dd>		 
			</dl>	
		
		</div>
		<div class="formBar"  >
			<ul style="padding:0px 10px;" >
				<li style="margin:0px 15px;" id="adCreate"><s:submit method="create" value="创 建" cssClass="mispButton primary"></s:submit></li>
				<li style="margin:0px 15px;"  id="adModify"><s:submit method="modify" value="修 改" cssClass="mispButton primary"></s:submit></li>
				<li style="margin:0px 15px;"><button type="button" class="mispButton primary close" >关  闭</button></li>			

			</ul>
		</div>
	</s:form>
</div>
