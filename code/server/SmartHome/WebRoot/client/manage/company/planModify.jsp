<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="s" uri="/struts-tags"%>
 
<style type="text/css">
#container1{
	position:relative; width:98%; height:350px; border:1px solid #CCC; overflow:auto;padding:5px;text-align: center;
}
#map1{
	position:absolute;margin: 0 auto;
}
.mark1{
	position:absolute; width:6px; height:6px; font-size:0px; background:#FF0000;
}
</style>

<div class="pageHeader" style="border:1px #B8D0D6 solid">
	<s:form  id="pagerForm"  onsubmit="return navTabSearch(this);" action="device/BuildingManage" method="post" name="newsSearch">
		<input type="hidden" name="pageNum" value="${pageNum}" />
	    <input type="hidden" name="numPerPage" value="${numPerPage}" />
	
	<div class="searchBar">
		<table class="searchContent">
			<tr>

				<td>
					平面图编号：<input type="text" name="keyword" />
				</td>
				<td><div class="buttonActive"><div class="buttonContent"><button type="submit">检索</button></div></div></td>
			</tr>
		</table>
	</div>
	</s:form>
</div>

<div class="pageContent" style="border-left:1px #B8D0D6 solid;border-right:1px #B8D0D6 solid">
<div class="panelBar">
		<ul class="toolBar">
			<li><a class="add" href="demo/pagination/dialog2.html" target="dialog" mask="true"><span>新增楼层</span></a></li>
			<li><a class="delete" href="demo/pagination/ajaxDone3.html?uid={sid_obj}" target="ajaxTodo" title="确定要删除吗?"><span>删除楼层</span></a></li>
			<li><a class="edit" href="demo/pagination/dialog2.html?uid={sid_obj}" target="dialog" mask="true"><span>修改</span></a></li>
			<li class="line">line</li>
			<li><a class="icon" href="demo/common/dwz-team.xls" target="dwzExport" title="实要导出这些记录吗?"><span>导出EXCEL</span></a></li>
		</ul>
	</div>
	<table class="table" width="99%"  layoutH="500" rel="jbsxBox">
		<thead>
			<tr>
				<th width="5%" align="center"><input type="checkbox" group="selectedIDList" class="checkboxCtrl" style="margin-top:5px;"></th>
				<th width="100">集中器编号</th>
				<th width="100">机号</th>
				<th width="100">回路号</th>
				<th width="100">点号</th>
				<th width="80">位置描述</th>
			</tr>
		</thead>
		<s:form  id="newsForm"  method="POST"  name="newsForm" >
		<tbody>
 		<c:forEach var="e" items="${table.currentPageData}"> 	
			<tr target="sid_user" rel="${e.id}">
				<td><input name="selectedIDList" value="${e.id}" type="checkbox" style="margin-top:5px;"></td>
				<td>${e.concentratorID}</td>
				<td>${e.machineID}</td>
				<td>${e.loopID}</td>
 				<td>${e.codeID}</td>
 				<td>${e.locationDesp}</td>
			</tr>
		</c:forEach>  	

		</tbody>
		</s:form>
	</table>
	<div class="panelBar">
		<div class="pages">
			<span>显示</span>
	        <c:set var="page" value="${table.page}" scope="request"/>
			
			<select class="combox" onchange="navTabPageBreak({numPerPage:this.value}, 'jbsxBox')">
				<c:forEach var="e" items="${page.pageSizeList}"> 	
			       <c:if test="${e==page.pageSize}">
			         <option value="${e}" selected>${e}</option>
				  </c:if>
                  <c:if test="${e!=page.pageSize}">
			         <option value="${e}">${e}</option>
				  </c:if>
				</c:forEach>
 
			</select>
			<span>条，共${page.count}条</span>
		</div>
		
		<div class="pagination" targetType="navTab" totalCount="${page.count}" numPerPage="${page.pageSize}" pageNumShown="10" currentPage="${page.currentPage}"></div>

	</div>
<div id="container1" >
<div id="map1">
		<img src="plane1.jpg" />
    </div>
</div>
	
	
	
	
</div>
 <script type="text/javascript">
var mark = [];

function setCookie(name,value)
{
    var Days = 365;
    var exp  = new Date();
    exp.setTime(exp.getTime() + Days*24*60*60*1000);
    document.cookie = name + "="+ escape (value) + ";expires=" + exp.toGMTString();
}
function getCookie(name)     
{
    var arr = document.cookie.match(new RegExp("(^| )"+name+"=([^;]*)(;|$)"));
     if(arr != null) return unescape(arr[2]); return null;

}
function delCookie(name)
{
    var exp = new Date();
    exp.setTime(exp.getTime() - 1);
    var cval=getCookie(name);
    if(cval!=null) document.cookie= name + "="+cval+";expires="+exp.toGMTString();
}

function getObj(id){
	return document.getElementById(id);
}
function point(x, y){
	this.x = x;
	this.y = y;
}
function getOffset(obj){
	var x = 0, y = 0;
	while(obj){
		x += obj.offsetLeft;
		y += obj.offsetTop;
		
		obj = obj.offsetParent;
	}
	return {x : x, y : y };
}
function addMark(p, x, y, index){
	var div = document.createElement("div");
	div.id = "mark1" + index;
	div.className = "mark1";
	div.style.left = x + "px";
	div.style.top = y + "px";
	p.appendChild(div);
}
function bindEvent(){
	getObj("map1").ondblclick = function(oEvent){
		oEvent = oEvent || event;
		var container = getObj("container1");
		var offset = getOffset(container);
		var x = oEvent.clientX - offset.x;
		var y = oEvent.clientY - offset.y;
		addMark(container, x, y, mark.length);
		mark.push(x + "," + y);
		saveMark();
	};
}

function saveMark(){
	setCookie("mark", mark.join("|"));
}
function loadMark(){
	var cookie = getCookie("mark");
	
	var json= '<%=request.getAttribute("locationJson")%>';
	var sensorList = $.parseJSON(json);
	 
 	 var container = getObj("container1");
	 for(var i=0; i<sensorList.length; i++){
		 addMark(container, sensorList[i].locationX, sensorList[i].locationY, i);
	 }
	 
}
function clearMark(){
	var container = getObj("container1");
	for(var i=0; i<mark.length; i++){
		container.removeChild(getObj("mark1"+i));
	}
	mark.length = 0;
	saveMark();
}

init();

function init()
{
	bindEvent();
	loadMark();
}
 
 
</script>
 
	