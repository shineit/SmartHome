<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="s" uri="/struts-tags"%>
<script src="http://code.jquery.com/jquery-2.1.3.min.js" type="text/javascript"></script> 

<style type="text/css">
#mainArea{
	position:relative; width:98%; height:350px; border:1px solid #CCC; overflow:auto;padding:5px;text-align: center;
}
#map{
	position:absolute;margin: 0 auto;
}
.mark{
	position:absolute; width:6px; height:6px; font-size:0px; background:#FF0000;
}
</style>

<div class="pageHeader" style="border:1px #B8D0D6 solid">
	<form id="pagerForm" onsubmit="return divSearch(this, 'jbsxBox');" action="demo/pagination/list1.html" method="post">
	<input type="hidden" name="pageNum" value="1" />
	<input type="hidden" name="numPerPage" value="${model.numPerPage}" />
	<input type="hidden" name="orderField" value="${param.orderField}" />
	<input type="hidden" name="orderDirection" value="${param.orderDirection}" />
	<div class="searchBar">
		<table class="searchContent">
			<tr>

				<td>
					平面图编号：<input type="text" name="keyword" />
				</td>
				<td><div class="buttonActive"><div class="buttonContent"><button type="button" onclick="bindEvent()">检索</button></div></div></td>
			</tr>
		</table>
	</div>
	</form>
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
				<th width="80">序号</th>
				<th width="120" >诊所编号</th>
				<th >诊所名称</th>
				<th width="100">病人编号</th>
				<th width="100">病人姓名</th>
				<th width="120">尿检日期</th>
				<th width="100">尿检结果</th>
				<th width="80">检验次数</th>
			</tr>
		</thead>
		<tbody>
			<tr target="sid_obj" rel="1">
				<td>1</td>
				<td>bj0001</td>
				<td>xxx诊所</td>
				<td>xxx</td>
				<td>张三</td>
				<td>2011-9-6</td>
				<td>xxx</td>
				<td>1</td>
			</tr>

		</tbody>
	</table>
	<div class="panelBar">
		<div class="pages">
			<span>显示</span>
			<select class="combox" name="numPerPage" onchange="navTabPageBreak({numPerPage:this.value}, 'jbsxBox')">
				<option value="20">20</option>
				<option value="50">50</option>
				<option value="100">100</option>
				<option value="200">200</option>
			</select>
			<span>条，共50条</span>
		</div>
		
		<div class="pagination" rel="jbsxBox" totalCount="200" numPerPage="20" pageNumShown="5" currentPage="1"></div>

	</div>
<div id="mainArea" >
	<div id="map" ondblclick="dbcl(oEvent);">
		<img src="<%=request.getContextPath()%>/client/manage/company/plane1.jpg" />
    </div>
</div>
	
	
	
	
</div>
<script type="text/javascript">
	//bindEvent();
	//loadMark();

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
	div.id = "mark" + index;
	div.className = "mark";
	div.style.left = x + "px";
	div.style.top = y + "px";
	p.appendChild(div);
}
function bindEvent(){
alert("OK");
alert($("#map"));
	$("#map").ondblclick = function(oEvent){
		alert("OK");
		oEvent = oEvent || event;
		var container = $("#mainArea");
		var offset = getOffset(container);
		var x = oEvent.clientX - offset.x;
		var y = oEvent.clientY - offset.y;
		addMark(container, x, y, mark.length);
		mark.push(x + "," + y);
		saveMark();
	};
}
function dbcl (oEvent){
		alert("OK");
		oEvent = oEvent || event;
		var container = $("#mainArea");
		var offset = getOffset(container);
		var x = oEvent.clientX - offset.x;
		var y = oEvent.clientY - offset.y;
		addMark(container, x, y, mark.length);
		mark.push(x + "," + y);
		saveMark();
	};
function saveMark(){
	setCookie("mark", mark.join("|"));
}
function loadMark(){
	var cookie = getCookie("mark");
	if(cookie){
		mark = cookie.split("|");
		var container = $("#mainArea");
		for(var i=0; i<mark.length; i++){
			addMark(container, mark[i].split(",")[0], mark[i].split(",")[1], i);
		}
	}
}
function clearMark(){
	var container = $("#mainArea");
	for(var i=0; i<mark.length; i++){
		container.removeChild($("#mark"+i));
	}
	mark.length = 0;
	saveMark();
}

</script>
	