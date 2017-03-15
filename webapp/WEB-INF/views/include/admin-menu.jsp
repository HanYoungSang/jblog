<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<ul class="admin-menu">
	<c:choose>
		<c:when test="${adminMenu == 'basic'}" >
			<li class="selected">기본설정</li>
		</c:when>
		<c:otherwise>
			<li><a href="${pageContext.request.contextPath}/${blogId}/admin/basic"">기본설정</a></li>
		</c:otherwise>
	</c:choose>
	<c:choose>
		<c:when test= "${adminMenu == 'category'}" >
			<li class="selected">카테고리</li>
		</c:when>
		<c:otherwise>
			<li><a href="${pageContext.request.contextPath}/${blogId}/admin/category"">카테고리</a></li>
		</c:otherwise>
	</c:choose>
	<c:choose>
		<c:when test="${adminMenu == 'write'}" >
			<li class="selected">글작성</li>
		</c:when>
		<c:otherwise>
			<li><a href="${pageContext.request.contextPath}/${blogId}/admin/writeform"">글작성</a></li>
		</c:otherwise>
	</c:choose>
</ul>