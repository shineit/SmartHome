<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!-- 去logo-->
<style type="text/css">
 .anchorBL {
            display: none; 
        }
</style>
<!-- 自适应屏幕高度 -->
<script type="text/javascript">

	//版面布局自适应
	totalHeight = document.documentElement.clientHeight;
	totalWidth = document.documentElement.clientWidth;
	colHeight = totalHeight - 74;//84
	colWidth =totalWidth -200;
	$("#accordionR").width(colWidth - 297 + "px");
	$("#accordionR").height(colHeight - 30 + "px");
	$("#all_map").height(colHeight - 45+ "px");
</script> 
<div class="pageContent">
	<div class="accordion" style="width:100%;margin:0px;" id="">

		<div class="accordionContent" style="width:280px;;float:left;" id="">

		<s:form id="pagerForm" onsubmit="return navTabSearch(this);" action="device/CustomerDistribution" method="POST" >	
				<div class="pageFormContent" style="overflow:hidden;">

					<dl id="result">
						<dt style="text-align:center;width:20%;">城市名称：</dt>
						<dd>
							<input id="cityName" type="text" size="20"  name="filter.cityName" value="${filter.cityName}"/>
						</dd>
					</dl>
					<dl>
						<dt style="text-align:center;width:20%;">集中器编号：</dt>
						<dd>
							<input id="conID" type="text" size="20"  name="filter.concentratorID" value="${filter.concentratorID}"/>
						</dd>
					</dl>
					<dl>
						<dt style="text-align:center;width:20%;">在线状态：</dt>
						<dd>
							<select  name="filter.status" style="margin-left:0px;">
						 <option value="">所有</option>

					 		<c:forEach var="st" items="${filter.concentStatusList}">

 						  		 <c:choose>		       
							   		 <c:when test="${st.strValue == filter.status}">  
	                            		       <option value="${st.strValue}" selected='selected'> ${st.strValue}</option>
	                            		 
	                            	  </c:when>
							   		  <c:otherwise>  
							   	   			    <option value="${st.strValue}" > ${st.strValue}</option>
							   		   </c:otherwise>
							   
						   		 </c:choose> 
						    </c:forEach>								
				 											

					</select>
						</dd>
					</dl>										

				</div>
				<div  align="right" style="font-size:0.9em;color:red; font-style:italic;padding-bottom:5px;">*集中器编号为优先搜寻条件</div>
			<div class="formBar">
				<ul style="margin-right:15px !important;">
					<li>
						<s:submit  value="查 询" cssClass="mispButton primary" onclick="theLocation(11)"></s:submit>
					</li>
					<li style="padding-left:15px;">
						<s:submit  value="重 置" cssClass="mispButton primary"  onclick="resetForm(this.form);"></s:submit>
					</li>
					<li style="padding-left:15px;">
						<input type="button"  value="全局预览" class="mispButton primary"  onclick="theLocation(5);"/>
					</li>
				</ul>
			</div>
</s:form>
		</div>
		<div class="accordionContent" style="float:right;" id="accordionR">
			<div id="all_map" style="width:100%;float:right;"></div>
		</div>
	</div>
</div>
<script type="text/javascript">

var map = new BMap.Map("all_map");        			  // 创建地图实例  
map.addControl(new BMap.NavigationControl());     //地图平移缩放控件
map.addControl(new BMap.ScaleControl());    	  //比例尺控件，默认位于地图左下方，显示地图的比例关系。
map.addControl(new BMap.OverviewMapControl());    //缩略地图控件  
map.enableScrollWheelZoom(true); 

//map.addControl(new BMap.MapTypeControl()); 		  //地图类型控件
	//setTimeout(function(){
	//	map.setZoom(14);   
	//}, 2000);  //2秒后放大到14级


	if($("#cityName").val()!='')
	{
		var city=$("#cityName").val();
      	map.centerAndZoom(city,11);
	}
	else if($("#conID").val()!='')
		{   
			map.centerAndZoom(new BMap.Point(obj[0].locationNS,obj[0].locationWE),15);
		}else
		{
				var myCity = new BMap.LocalCity();
				myCity.get(myFun);
				function myFun(result)
				{
					var cityName = result.name;
					//map.setCenter(cityName);
					map.centerAndZoom(cityName,11);
					//alert("当前定位城市:"+cityName);
				}	
			//map.centerAndZoom(new BMap.Point(116.404, 39.915), 11); //调试使用
			//map.setCurrentCity("北京");
		}



    var a= '<%=request.getAttribute("locationJson")%>';
    var obj = $.parseJSON(a);
 	var data_info = new Array();
 
	for(var k=0;k<obj.length;k++)
	{         
		data_info[k]=new Array();    
	    data_info[k][0]= obj[k].locationNS;        
		data_info[k][1]= obj[k].locationWE;     
        data_info[k][2]= obj[k].description;
 
	}

	var opts = {
				width : 250,     // 信息窗口宽度
				height: 80,     // 信息窗口高度
				title : "集中器信息" , // 信息窗口标题
				panel : "panel", //检索结果面板
				enableMessage:false//设置允许信息窗发送短息
			   };
	for(var i=0;i<data_info.length;i++){
	    //var icon = new BMap.Icon('pin.png', new BMap.Size(20, 32), { anchor: new BMap.Size(10, 30)});
	   // var marker = new BMap.Marker(new BMap.Point(data_info[i][0],data_info[i][1]),{icon:icon});
		var marker = new BMap.Marker(new BMap.Point(data_info[i][0],data_info[i][1]));  // 创建标注
		var content = data_info[i][2];
		map.addOverlay(marker);               // 将标注添加到地图中
		addClickHandler(content,marker);
	}
	function addClickHandler(content,marker){
		marker.addEventListener("click",function(e){
			openInfo(content,e);}
		);
	}
	function openInfo(content,e){
		var p = e.target;
		var point = new BMap.Point(p.getPosition().lng, p.getPosition().lat);
		var infoWindow = new BMap.InfoWindow(content,opts);  // 创建信息窗口对象 
		map.openInfoWindow(infoWindow,point); //开启信息窗口
	}
    function theLocation(ratio){

    	var city = $("#cityName").val();
    	if($("#conID").val()!='')
    	{
    		$("#cityName").val('');
    	}
		
		if(city != ''){
			map.centerAndZoom(city,ratio);      // 用城市名设置地图中心点
		}
		else
		{
			map.centerAndZoom(new BMap.Point(116.404, 39.915), ratio);
		}
	}

</script>  