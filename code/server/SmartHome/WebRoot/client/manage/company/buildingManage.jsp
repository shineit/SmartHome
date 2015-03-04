<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="s" uri="/struts-tags"%>

<div class="pageContent" style="padding:1px">
	<div class="tabs">

		<div class="tabsContent">
			<div>
	
				<div layoutH="12" style="float:left; display:block; overflow:auto; width:240px; border:solid 1px #CCC; line-height:21px; background:#fff">
				    <ul class="tree treeFolder">

						<li><a href="javascript">单位名称</a>
							<ul>
								<li><a href="<%=request.getContextPath()%>/client/manage/company/planManage.jsp" target="ajax" rel="jbsxBox">楼层1</a>
									<ul>
										<li><a href="<%=request.getContextPath()%>/client/manage/company/planManage.jsp" target="ajax" rel="jbsxBox">平面图1</a></li>
										<li><a href="<%=request.getContextPath()%>/client/manage/company/planManage.jsp" target="ajax" rel="jbsxBox">平面图2</a></li>
									</ul>
								</li>
								
							</ul>
						</li>
				     </ul>
				</div>
				
				<div id="jbsxBox" class="unitBox" style="margin-left:246px;">
					<!--#include virtual="list1.html" -->
				</div>
	
			</div>

		</div>
		<div class="tabsFooter">
			<div class="tabsFooterContent"></div>
		</div>
	</div>
	
</div>


	




	

