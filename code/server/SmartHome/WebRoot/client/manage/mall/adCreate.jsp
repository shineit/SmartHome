<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="s" uri="/struts-tags"%>
<script type="text/javascript">
      $(function () {
			$.pdialog.resizeDialog({style: {width: 660,height:360}}, 				
			$.pdialog.getCurrent(), "");
        });//dialog 宽度重新定义
        
    $(function () 
    {
    	//图片预览js
        $("#up1").uploadPreview({ Img: "ImgPr1",ImgType:["jpg","bmp","png"] });        
     });
</script>
<div class="pageContent">
	<s:form method="post" action="mall/AdManage!create.action" class="pageForm required-validate" name="KnowledgeForm" onsubmit="return iframeCallback(this,dialogAjaxDone);" enctype="multipart/form-data" >
		<div class="pageFormContent" layoutH="58">

			<dl style="width:50%;">
				<dt style="width:20%;">标题：</dt>			
				<dd style="width:70%;"><input type="text" name="obj.adName"  size="30" value="${obj.adName}" class="required"/></dd>
			</dl>
			<dl style="width:100%;">
				<dt style="width:10%;">公告内容：</dt>			
				<dd style="width:80%;"><textarea name="obj.adInfo" cols="87" rows="8" maxlength="500">${obj.adInfo}</textarea></dd>
			</dl>	
						<div style="float:right; margin-top:5px; margin-right:6%;width:40%;">
						<div
							style="margin-bottom:5px; width:120px; height:140px; border:solid 1px #CCC; line-height:21px; background:#FFF;">
							<div>
								<img id="ImgPr1" src="<%=request.getContextPath()%>/upload/${obj.adImg}" height="140" width="120" alt="个人照片">
							</div>

						</div>
						<div>
		 
							<s:file name="upload" id="up1"  ></s:file>
						</div>
						<span class="info">上传图片大小不得超过65KB,只支持bmp与jpg格式</span>
					</div> 					
		</div>		

		<div class="formBar">
			<ul style="margin-right:250px !important;" >
				<li ><div class="buttonActive"><div class="buttonContent"><button type="submit">提 交</button></div></div></li>
				<li style="padding-left:30px;"><div class="button"><div class="buttonContent"><button type="button" class="close">取 消</button></div></div></li>			

			</ul>
		</div>
	</s:form>
</div>
