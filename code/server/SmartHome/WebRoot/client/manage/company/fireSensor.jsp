<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="s" uri="/struts-tags"%>
<script type="text/javascript">
function submitFireForm(url)
{
	var count = $("#curCount").val();
	if(count==0)
	{
		alertMsg.info("当前无数据导出！");
	}
	else
	{
	    var thisForm = document.fireSearchForm;
		thisForm.action="device/FireSensorManage!"+url;
		thisForm.submit();
 		thisForm.action="device/FireSensorManage";
	}
	
}

</script>
<style type="text/css">
.container1{
	position:relative; width: 48%; height:330px; border:1px solid #CCC; overflow:hidden;padding:5px;text-align: center;
}
#map1{
	position:absolute;margin: 0 auto;text-align: center;
}
.mark1{
	position:absolute; width:6px; height:6px; font-size:0px; background:#FF0000;
}
.mark2{
	position:absolute; width:6px; height:6px; font-size:0px; background:#0000FF;
}	
.imgContainer1 {
	position:absolute;margin: 0 auto;text-align: left;
}
</style>
<div class="pageContent" >
	<div class="tabs">

		<div class="tabsContent">
			<div>
	
				<jsp:include page="buildingTree.jsp"/>
				
				<div id="jbsxBox" class="unitBox" style="margin-left:246px;">
							<div class="pageContent">

			<div class="container1" style="float:left;">
			<div id="map1" >
				<img   src="<%=request.getContextPath()%>/upload/${sensorPlan.picPath}"/> 
			</div>

			</div>
			<div   style="float: right;position:relative; width: 48%; height:330px; border:1px solid #CCC; overflow:hidden;padding:5px;">
				<div id="imgContainer1" class="imgContainer1">
				<img  id="imageFullScreen" src="<%=request.getContextPath()%>/upload/${sensorPlan.picPath}"/> 
				</div>
			</div>
			</div>
					<div class="pageHeader" style="border:1px #B8D0D6 solid">
							<s:form  id="pagerForm"   onsubmit="return navTabSearch(this);"  action="device/FireSensorManage" method="post" name="fireSearchForm">
								<input type="hidden" name="pageNum" value="${pageNum}" />
								<input type="hidden" name="numPerPage" value="${numPerPage}" />
								
							<div class="searchBar">
								<table class="searchContent">
									<tr>
									    <td>
									    <input type="text" name="selectedID" style="display:none" value="${selectedID}"/>
									 	</td>
										<td>
										机号：<input type="text" name="machineID" value="${machineID}"/>
										</td>
										<td>
										回路号：<input type="text" name="loopID" value="${loopID}"/>
										</td>
										<td>
										点号：<input type="text" name="codeID" value="${codeID}"/>
										</td>
										<td>
										<select  name="markStatus" >
						 					<option value="">标记状态</option>
												<c:forEach var="st" items="${markStatusList}">
						  						<c:choose>		       
							   					 	<c:when test="${st.strValue == markStatus}">  
	                            		       		<option value="${st.strValue}" selected='selected'> ${st.strValue}</option>
	                            		 
	                            	 			 </c:when>
							   		  			<c:otherwise>  
							   	   			    <option value="${st.strValue}" > ${st.strValue}</option>
							   		   			</c:otherwise>
							   
						   		 			</c:choose>
						 					</c:forEach>								
										</select>
										</td>	
										<td>
										<s:submit  value="查 询" cssClass="mispButton primary"></s:submit>
										</td>
										<td>
										<s:submit  value="重 置" cssClass="mispButton primary"  onclick="resetForm(this.form) "></s:submit>
										</td>
										<td>

									</tr>
								</table>
							</div>
							</s:form>
						</div>

					<div class="pageContent" style="border-left:1px #B8D0D6 solid;border-right:1px #B8D0D6 solid">

					<div class="panelBar">
								<ul class="toolBar">
									<li><a class="add" href="FireSensorManage!show.action?selectedID=${selectedID}&operateType=create" 
									 target="dialog" mask="true" rel="fsNew" title="传感器信息"><span>新增</span></a></li>
									<li><a class="delete" href="FireSensorManage!deleteList.action" target="selectedTodo" rel="selectedIDList" title="确定要删除所选信息吗?"><span>删除</span></a></li>
									<li><a class="edit" href="FireSensorManage!show.action?selectedID={sid_user}&operateType=modify"						
									 target="dialog" mask="true" title="传感器信息" rel="sensorModify"><span>修改</span></a></li>
									<li class="line">line</li>
									<li ><a class="icon" href="<%=request.getContextPath()%>/client/resource/tableModel/fireSensorModel.xls" target="dwzExport" ><span>导出模板</span></a></li>
									<li><a class="add" href="<%=request.getContextPath()%>/client/manage/company/fireImportShow.jsp" 
									 target="dialog" mask="true" rel="fsImport" title="导入传感器"><span>数据导入</span></a></li>
									<li class="line">line</li>
									<li ><a class="icon" href="#" target="dwzExport" onclick="submitFireForm('downloadSensor')" targetType=”dialog” ><span>导出数据</span></a></li>
								</ul>
							</div>
							<table class="table" width="100%" layoutH="470">
								<thead>
									<tr>
										<th width="5%" align="center">标注</th>
										<th width="15%" align="center">传感器类型</th>
										<th width="15%" align="center">集中器编号</th>
										<th width="5%" align="center">机号</th>
										<th width="5%" align="center">回路号</th>
										<th width="5%" align="center">点号</th>
										<th width="15%" align="center">位置描述</th>
										<th width="10%" align="center">联系人</th>
										<!-- <th width="10%" align="center">关联账户</th> -->
										<th width="15%" align="center">联系电话</th>	
										<th width="5%" align="center"><input type="checkbox" group="selectedIDList" class="checkboxCtrl" >删除</th>									
									</tr>
								</thead>
								<s:form   method="POST"  >
								<tbody>

								<c:forEach var="e" items="${table.currentPageData}"> 	
									<tr target="sid_user" rel="${e.id}">
										
										<td><input name="radioGroup" value="${e.id}"  type="radio" style="margin-top:5px;" onclick="selectMark(${e.id});"></td>
										<td>${e.sensorTypeName}</td>
										<td>${e.concentratorID}</td>
										<td>${e.machineID}</td>
										<td>${e.loopID}</td>
						 				<td>${e.codeID}</td> 		 
						 				<td>${e.locationDesp}</td>
						 				<td>${e.contacts}</td>
						 				<%-- <td>${e.userName}</td> --%>
						 				<td>${e.contactPhone}</td>
						 				<td><input name="selectedIDList" value="${e.id}" type="checkbox" style="margin-top:5px;"></td>
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
								<input type="hidden" id="curCount" value="${page.count}" />
								<div class="pagination" targetType="navTab" totalCount="${page.count}" numPerPage="${page.pageSize}" pageNumShown="10" currentPage="${page.currentPage}"></div>

							</div>
					</div>


			
				</div>
	
			</div>

		</div>
		<div class="tabsFooter">
			<div class="tabsFooterContent"></div>
		</div>
	</div>
</div>
<script type="text/javascript">
//全局变量
var mark = [];
var sensorList;
var selectedID=null;

 $(document).ready(function() {        
        $('#imageFullScreen').smartZoom({'containerClass':'zoomableContainer'});        
		 try{
				var json= '<%=request.getAttribute("locationJson")%>';
				sensorList = $.parseJSON(json);
				init(); 
			}catch(e)
			{
				alert(e);
			} 
    });
//图片放大js

//初始化函数
 function init()
{
	bindEvent();
	loadMark();
}

 
function getObj(id)
{
	return document.getElementById(id);
}
function point(x, y)
{
	this.x = x;
	this.y = y;
}
function getOffset(obj)
{
	var x = 0, y = 0;
	while(obj){
		x += obj.offsetLeft;
		y += obj.offsetTop;
		
		obj = obj.offsetParent;
	}
	return {x : x, y : y, };
}
function refreshPoint(rect)
{ 
  var p=getObj("imgContainer1");
  for(var i=0; i<sensorList.length; i++)
	{

		if(getMarkByID(sensorList[i].id,"r")!=null)
		{
			p.removeChild(getMarkByID(sensorList[i].id,"r"));
		}
		var oldX=sensorList[i].locationX;
		var oldY=sensorList[i].locationY;

		var newX= oldX*rect.scale+rect.x;
 		var newY= oldY*rect.scale+rect.y;
  		addMark(p,newX,newY,sensorList[i].id,"r");
	}
  if(selectedID!=null)
  {
	//alert(selectedID);
	getMarkByID(selectedID,"r").className ="mark2";
  }
  
}
function bindEvent()
{
	getObj("imageFullScreen").ondblclick = function(oEvent){
		oEvent = oEvent || event;		 
		if(null != selectedID)
		{
		  var container = getMapContainer();
		  var p = getRightMapContainer();
		  var offset = getOffset(p);
		  var x2 = oEvent.clientX - offset.x;
		  var y2 = oEvent.clientY - offset.y;			 
		  p.removeChild(getMarkByID(selectedID,"r"));
		  addMark(p, x2, y2, selectedID,"r");

		  var x = oEvent.offsetX|| oEvent.layerX;//firefox用后者
		  var y = oEvent.offsetY|| oEvent.layerY;//firefox用后者
		  container.removeChild(getMarkByID(selectedID,"l"));
		  addMark(container, x, y, selectedID,"l");

		  selectMark(selectedID);
		  saveMark(selectedID,x,y);
		  
		}

	};
}
//side 定义方向，r;l两个变量
function getMarkByID(id,side)
{

   return getObj("mark"+side+id);
}

function getMapContainer()
{

   return getObj("map1");
}
function getRightMapContainer()
{

   return getObj("imgContainer1");
}

function selectMark(id)
{
 	if(null != selectedID)
	{
	   unSelectMark(selectedID);
	}
	selectedID = id;
	
	if(null != getMarkByID(id,"l"))
	{
		getMarkByID(id,"l").className ="mark2";
	}
	if(getMarkByID(id,"r")!=null)
	{
		getMarkByID(id,"r").className ="mark2";
	}

}

function unSelectMark(id)
{
	if(null != getMarkByID(id,"l"))
	{
		getMarkByID(id,"l").className ="mark1";	
	}
	if(getMarkByID(id,"r")!=null)
	{
		getMarkByID(id,"r").className ="mark1";
	}
}

//side 定义方向，r;l两个变量
 function addMark(p, x, y,index,side)
{
    //alert("add:x="+x+";y="+y);
 	var div = document.createElement("div");
	div.id = "mark"+side+index;
	div.className = "mark1";
	div.style.left = x + "px";
	div.style.top = y + "px";
	p.appendChild(div);
} 

function loadMark(){
 
 	 var container = getMapContainer();
	 for(var i=0; i<sensorList.length; i++)
	 {
	 
		 addMark(container, sensorList[i].locationX, sensorList[i].locationY, sensorList[i].id,"l");
		 addMark(getRightMapContainer(),sensorList[i].locationX, sensorList[i].locationY, sensorList[i].id,"r");
	 }
} 


function saveMark(sensorID,locationX,locationY)
{
	 var oldIndex=0;
	 for(var i=0; i<sensorList.length; i++)
	 { 
	    if(sensorList[i].id == sensorID)
	    {
	      oldIndex=i;
	      sensorList[i].locationX = locationX;
	      sensorList[i].locationY = locationY;
	      var sensorJson = JSON.stringify(sensorList[i]);
	      $.ajax({
					type : "POST",
					url : "device/FireSensorManage!modifyLocation.action",
					dataType : "text",
					data : {
						sensorJson : sensorJson,
					},
					success : function(json) {
						if (json == 'false')
						 {
							alert("位置修改失败");
						 } 
						else
						{
							//alert(oldIndex);
							if(oldIndex+1<sensorList.length)
							{
								$("input[type='radio'][name='radioGroup']").get(oldIndex+1).checked = true; 
								selectMark(sensorList[oldIndex+1].id);
							}
							else
							{
								alert("已标记到最后一项");
							}
							
						}
					}

				});
	    }
	  }
}


			
</script>