<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="s" uri="/struts-tags"%>
<script type="text/javascript">
      $(function () 
      {
      	//dialog 宽度重新定义
		$.pdialog.resizeDialog({style: {width: 600,height:400,}}, 				
		$.pdialog.getCurrent(), "");
			
		//图片预览js
        $("#up1").uploadPreview({ Img: "ImgPr1",ImgType:["jpg","bmp","png"] });   
      });
</script>

<%
String infoContent=request.getParameter("infoContent");
String picUrl=request.getParameter("picUrl");
 %>
 
 <div class="pageContent">
<div class="pageFormContent" layoutH="28">
 
			<dl class="nowrap">
				<dt style="width:15%;">异常描述：</dt>			
				<dd style="width:70%;"><textarea name="" cols="70" rows="4" ><%=infoContent%></textarea></dd>
			</dl>

			<dl class="nowrap">
				<dt style="width:15%;">异常图片：</dt>			
				<dd style="width:70%;">
						<div style="width:450px; height:240px; border:solid 1px #CCC; line-height:21px; background:#FFF;">
							<div>
								<img id="ImgPr1" src="<%=request.getContextPath()%>/upload/<%=picUrl%>" height="240" width="450" alt="异常图片">
							</div>
						</div>
				</dd>		 
			</dl>	
		
		</div>

</div>


