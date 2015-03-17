<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<div class="pageContent" layoutH="5">
<div  class="panel" >
	
	<div align="center">
			<c:choose>
				<c:when test="${newsContent==null || fn:length(newsContent)==0}">
					<div style="padding:5px 50px 10px 5px;">
						<span style="text-align:center;font-size:1.7em;color:orange;">今日未发布公告
						</span>

					</div>
				</c:when>
				<c:otherwise>
					<c:forEach var="n" items="${newsContent}" >
					<div style="padding:5px 50px 10px 5px;">
						<span style="text-align:center;font-size:1.7em;color:orange;">${n.title}</span>
						<span style="float:right;">${n.date}</span>
					</div>
					<br>
					<div
						style="text-align:justify;font-size:1.2em;text-indent: 2em;line-height:25px;">
						${n.content}</div>
					<div class="divider"></div>	
					</c:forEach>
				</c:otherwise>
			</c:choose>
	</div>

</div>
</div>
