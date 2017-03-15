<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<script type="text/javascript" src="${pageContext.request.contextPath }/assets/js/jquery/jquery-1.9.0.js"></script>
<script type="text/javascript">
$(document).on("click","#category_list li a", function(event) {
	var list_no = $(this).attr("data-count");
	console.log(list_no);
	if (list_no == 0) {
		alert("포스팅된 게시물이 없습니다.");
		return false;
	} 
	
})
</script>

<!DOCTYPE html>
<div id="navigation">
	<h2>카테고리</h2>
	<ul id="category_list">
		<c:forEach var="vo" items="${categoryList }">
			<li><a href="${pageContext.request.contextPath}/${blogId}?category=${vo.no }" data-count="${vo.postCount }">${vo.name }</a></li>
		</c:forEach>
	</ul>
</div>