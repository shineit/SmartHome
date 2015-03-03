<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="s" uri="/struts-tags"%>

<div class="pageContent" style="padding：1px;">
	<div class="panelBar">
		<ul class="toolBar">
			<li><a class="add" href="NewsManage!show.action?operateType=create" target="dialog" mask="true" title="新增公告"><span>新增公告</span></a></li>
			<li><a class="delete" href="NewsManage!deleteList.action"  target="selectedTodo" rel="selectedIDList" title="确定要删除所选信息吗?"><span>删除公告</span></a></li>
		</ul>
	</div>
	<div class="tabs">
	<div class="tabsContent">

				<div layoutH="40" style="float:left; display:block; overflow:auto; width:240px; border:solid 1px #CCC; line-height:21px; background:#fff">
				    <ul class="tree treeFolder">
						<li><a href="javascript">公司名称</a>
							<ul>
								<li><a href="demo/pagination/list1.html" target="ajax" rel="jbsxBox">尿检</a></li>
								<li><a href="demo/pagination/list1.html" target="ajax" rel="jbsxBox">HIV检测</a></li>
								<li><a href="demo/pagination/list1.html" target="ajax" rel="jbsxBox">HCV检测</a></li>
								<li><a href="demo/pagination/list1.html" target="ajax" rel="jbsxBox">TB检测</a></li>
							</ul>
						</li>
						
				     </ul>
				</div>
				
				<div id="jbsxBox" class="unitBox" style="margin-left:246px;">
					<!--#include virtual="list1.html" -->
				</div>
		</div>
		<div class="tabsFooter">
			<div class="tabsFooterContent"></div>
		</div>
	</div>
	
</div>


	

