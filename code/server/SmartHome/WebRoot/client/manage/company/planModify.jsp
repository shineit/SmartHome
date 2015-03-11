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
.mark2{
	position:absolute; width:6px; height:6px; font-size:0px; background:#0000FF;
}
</style>

<div class="pageHeader" style="border:1px #B8D0D6 solid">
	<s:form  id="pagerForm"  onsubmit="return divSearch(this, 'jbsxBox');" action="device/FireSensorManage" method="post" >
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
			<li><a class="add" href="FireSensorManage!show.action?operateType=create"  target="dialog" mask="true" rel="fsNew"><span>新增传感器</span></a></li>
			<li><a class="delete" href="demo/pagination/ajaxDone3.html?uid={sid_obj}" target="ajaxTodo" title="确定要删除吗?"><span>删除楼层</span></a></li>
			<li><a class="edit" href="demo/pagination/dialog2.html?uid={sid_obj}" target="dialog" mask="true"><span>修改</span></a></li>
			<li class="line">line</li>
			<li><a class="icon" href="demo/common/dwz-team.xls" target="dwzExport" title="实要导出这些记录吗?"><span>导出EXCEL</span></a></li>
		</ul>
	</div>
	<table class="table" width="99%"  layoutH="500" rel="jbsxBox">
		<thead>
			<tr>
				<th width="5%" align="center"> </th>
				<th width="100">集中器编号</th>
				<th width="100">机号</th>
				<th width="100">回路号</th>
				<th width="100">点号</th>
				<th width="80">位置描述</th>
			</tr>
		</thead>
		<s:form   method="POST" rel="jbsxBox" >
		<tbody>
 		<c:forEach var="e" items="${table.currentPageData}"> 	
			<tr target="sid_user" rel="${e.id}">
				<td><input name="selectedIDList" value="${e.id}" type="radio" style="margin-top:5px;" onclick="selectMark(${e.id});"></td>
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
		
		<div class="pagination" rel="jbsxBox" totalCount="${page.count}" numPerPage="${page.pageSize}" pageNumShown="10" currentPage="${page.currentPage}"></div>

	</div>
<div id="container1" >
    <div id="map1">
		<img src="plane1.jpg" />
    </div>
</div>
	
	
	
	
</div>
 <script type="text/javascript">
var mark = [];

 
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

function bindEvent(){
	getObj("map1").ondblclick = function(oEvent){
		oEvent = oEvent || event;
		 
		if(null != selectedID)
		{
		 
		  var container = getMapContainer();
		  var offset = getOffset(container);
		  var x = oEvent.clientX - offset.x;
		  var y = oEvent.clientY - offset.y;
		
		 container.removeChild(getObj("mark1"+selectedID));
		  addMark(container, x, y, selectedID);
		  selectMark(selectedID);
 
		  saveMark(selectedID,x,y);
		}

	};
}

function getMarkByID(id)
{

   return getObj("mark1"+id);
}

function getMapContainer()
{

   return getObj("map1");
}
 
function selectMark(id)
{
 	if(null != selectedID)
	{
	   unSelectMark(selectedID);
	}
	selectedID = id;
	
	if(null != getMarkByID(id))
	{
		getMarkByID(id).className ="mark2";
	
	}

}

function unSelectMark(id)
{
	if(null != getMarkByID(id))
	{
		getMarkByID(id).className ="mark1";
	
	}
 
}

function addMark(p, x, y, index){
     
 	var div = document.createElement("div");
	div.id = "mark1" + index;
	div.className = "mark1";
	div.style.left = x + "px";
	div.style.top = y + "px";
	p.appendChild(div);
}

function loadMark(){
 
 	 var container = getMapContainer();
	 for(var i=0; i<sensorList.length; i++)
	 {
	 
		 addMark(container, sensorList[i].locationX, sensorList[i].locationY, sensorList[i].id);
	 }
}


function saveMark(sensorID,locationX,locationY)
{
	 for(var i=0; i<sensorList.length; i++)
	 { 
	    if(sensorList[i].id == sensorID)
	    {
	      sensorList[i].locationX = locationX;
	      sensorList[i].locationY = locationY;
	      var sensorJson = JSON.stringify(sensorList[i]);
	      $.ajax({
					type : "POST",
					url : "device/FireSensorManage!modifyLocation.action",
					dataType : "text",
					data : {
						sensorJson : sensorJson
					},
					success : function(json) {
						if (json == 'false') {
							$("#warn").html("验证码输入错误！");
						} else {
							$("form[name='loginForm']").submit();
						}

					}

				});
	    }
	  }
}

 
var json= '<%=request.getAttribute("locationJson")%>';
var sensorList = $.parseJSON(json);
var selectedID;
init();

function init()
{
	bindEvent();
	loadMark();
}
 
 
</script>
 
	