<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="s" uri="/struts-tags"%>
<script type="text/javascript">
      $(function () {
			$.pdialog.resizeDialog({style: {width: 700,height:450,}}, 				
			$.pdialog.getCurrent(), "");
        });//dialog 宽度重新定义
     //图片预览js
    $("#up1").uploadPreview({ Img: "ImgPr1",ImgType:["jpg","bmp","png"] });    
    
    var operate = $("#companyShowType").val();
	if(operate=="create")
	{
		$("#csCreate").css("display", "block");
		$("#csModify").css("display", "none");
	}
	if(operate=="modify")
	{
		$("#csCreate").css("display", "none");
		$("#csModify").css("display", "block");	
	}
</script>
	
<div class="pageContent">
	<s:form method="post" action="company/CompanyManage" class="pageForm required-validate"  
	onsubmit="return iframeCallback(this,dialogAjaxDone);"  enctype="multipart/form-data" >
		<div class="pageFormContent" layoutH="58">
			<input type="hidden" name="obj.companyID" value="${obj.companyID}"/>
			<input type="hidden" name="operateType" value="${operateType}" id="companyShowType"/>
			<input type="hidden" name="oldConcentID" value="${obj.concentratorID}"/>
			<input type="hidden" name="oldCert" value="${obj.fireCert}"/>
			<input type="hidden" name="obj.org_id" value="${obj.org_id}"/>
			
			<dl style="width:50%;">
				<dt style="width:25%;">单位名称：</dt>			
				<dd style="width:70%;"><input type="text" name="obj.companyName"  size="30"  value="${obj.companyName}" class="required"/></dd>
			</dl>
			<dl style="width:50%;">
				<dt style="width:25%;">集中器编号：</dt>			
				<dd style="width:70%;"><input type="text" name="obj.concentratorID"  size="30"  value="${obj.concentratorID}" class="required"/></dd>
			</dl>
			<dl style="width:50%;">
				<dt style="width:25%;">集中器密码：</dt>			
				<dd style="width:70%;"><input type="text" name="obj.password"  size="30"  value="${obj.password}" class="required"/></dd>
			</dl>
						 
			
<%-- 			<dl style="width:50%;">
				<dt style="width:25%;">集中器编号：</dt>			
				<dd style="width:70%;">				
					<select  name="obj.concentratorID" >
					<option value="" >请选择集中器编号</option>
						 <c:forEach var="cl" items="${concentIDList}">
									<c:choose>		       
								   		 <c:when test="${cl== obj.concentratorID}">  
			                           		<option value="${cl}" selected='selected'> ${cl}</option>
			                           		 
			                           	  </c:when>
								   		  <c:otherwise>  
								   	   		<option value="${cl}" > ${cl}</option>
								   		  </c:otherwise>
					   
				   		 			</c:choose> 
						 </c:forEach>								
					</select>
				</dd>		 
			</dl> --%>
			<dl style="width:50%;">
				<dt style="width:25%;">使用名称：</dt>			
				<dd style="width:70%;"><input type="text" name="obj.applyName"  size="30" value="${obj.applyName}" /></dd>
			</dl>
			<dl style="width:100%;">
				<dt style="width:12.5%;">单位地址：</dt>			
				<dd style="width:80%;"><input type="text" name="obj.companyAddr"  size="80" value="${obj.companyAddr}" /></dd>
			</dl>
			<dl style="width:50%;">
				<dt style="width:25%;">单位类型：</dt>			
				<dd style="width:70%;"><input type="text" name="obj.companyType"  size="30"  value="${obj.companyType}" /></dd>
			</dl>
			<dl style="width:50%;">
				<dt style="width:25%;">单位电话：</dt>			
				<dd style="width:70%;"><input type="text" name="obj.companyPhone"  size="30" value="${obj.companyPhone}" /></dd>
			</dl>
			<dl style="width:50%;">
				<dt style="width:25%;">建筑面积：</dt>			
				<dd style="width:70%;"><input type="text" name="obj.buildingArea"  size="25"  value="${obj.buildingArea}" class="number"/>平方米</dd>
			</dl>
			<dl style="width:50%;">
				<dt style="width:25%;">火灾危险性：</dt>			
				<dd style="width:70%;"><input type="text" name="obj.fireRisk"  size="30" value="${obj.fireRisk}" /></dd>
			</dl>	
			<dl style="width:50%;">
				<dt style="width:25%;">法定代表人：</dt>			
				<dd style="width:70%;"><input type="text" name="obj.legalOfficer"  size="30"  value="${obj.legalOfficer}" /></dd>
			</dl>
			<dl style="width:50%;">
				<dt style="width:25%;">法人手机号：</dt>			
				<dd style="width:70%;"><input type="text" name="obj.officerPhone"  size="30" value="${obj.officerPhone}" /></dd>
			</dl>			
			<dl style="width:50%;">
				<dt style="width:25%;">消防管理人：</dt>			
				<dd style="width:70%;"><input type="text" name="obj.fireManager"  size="30" value="${obj.fireManager}" /></dd>
			</dl>	
			<dl style="width:50%;">
				<dt style="width:25%;">管理人手机：</dt>			
				<dd style="width:70%;"><input type="text" name="obj.managerPhone"  size="30" value="${obj.managerPhone}" /></dd>
			</dl>
			<dl style="width:50%;">
				<dt style="width:25%;">消防责任人：</dt>			
				<dd style="width:70%;"><input type="text" name="obj.fireDuty"  size="30" value="${obj.fireDuty}" /></dd>
			</dl>	
			<dl style="width:50%;">
				<dt style="width:25%;">责任人手机：</dt>			
				<dd style="width:70%;"><input type="text" name="obj.dutyPhone"  size="30" value="${obj.dutyPhone}" /></dd>
			</dl>
			<dl style="width:50%;">
				<dt style="width:25%;">维保单位：</dt>			
				<dd style="width:70%;"><input type="text" name="obj.maintainerUnit"  size="30" value="${obj.fireDuty}" /></dd>
			</dl>	
			<dl style="width:50%;">
				<dt style="width:25%;">维保人员：</dt>			
				<dd style="width:70%;"><input type="text" name="obj.maintainerName"  size="30" value="${obj.dutyPhone}" /></dd>
			</dl>
			<dl class="nowrap" style="height:200px !important;margin-top: 10px !important;">
				<dt style="width:12%;">消防证书：</dt>			
				<dd style="width:50%;" >
					<div style="width:300px; height:150px; border:solid 1px #CCC; line-height:21px; background:#FFF;">
						<div >
							<img id="ImgPr1" src="<%=request.getContextPath()%>/upload/${obj.fireCert}" height="150" width="300" alt="待上传图片">
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

		<div class="formBar">
			<ul style="padding:0px 10px;" >
				<li style="margin:0px 15px;" id="csCreate"><s:submit method="create" value="创 建" cssClass="mispButton primary"></s:submit></li>
				<li style="margin:0px 15px;"  id="csModify"><s:submit method="modify" value="修 改" cssClass="mispButton primary"></s:submit></li>
				<li style="margin:0px 15px;"><button type="button" class="mispButton primary close" >关  闭</button></li>			

			</ul>
		</div>
	</s:form>
</div>
