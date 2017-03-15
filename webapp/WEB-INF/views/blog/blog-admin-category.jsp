<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!doctype html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>JBlog</title>
<Link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/jblog.css">
<script type="text/javascript" src="${pageContext.request.contextPath }/assets/js/jquery/jquery-1.9.0.js"></script>
<script type="text/javascript">
	var list_count = ${fn:length(list) }; 
	var render = function( vo ) {
		list_count++;
		var html = "<tr id='list_"+ vo.no +"'> " +
						"<td>"+ list_count +"</td> " +
						"<td>"+ vo.name +"</td> " +
						"<td id=postCount>0</td> " +
						"<td>"+ vo.description +"</td> " +
						"<td><a href='' data-no='" + vo.no + "''><img src='${pageContext.request.contextPath}/assets/images/delete.jpg'></a></td> " +
					"</tr>";
			$("#list").append(html);
	}

	$(function () {
		$("#cat-add").click (function() {
			event.preventDefault();
			
			// 1. 이름
	 		var name = $( "#name ").val();
	 		if ( name == "" ) {
	 			alert( "이름이 비여 있습니다." );
	 			$( "#name ").focus();
	 			return false
	 		}

			// Ajax 통신
			$.ajax( {
			    url : "/jblog/${blogId}/admin/category/add",
			    type: "get",
			    dataType: "json",
			    data: "name="+$("#name").val()+ "&" +
			    	  "description="+$("input[name='desc']").val(),
			//  contentType: "application/json",
			    success: function( response ){
			    	console.log	( response );
	 		       if( response.result == "fail") {
	 		    	   console.log( response );
	// 		    	   return;
	 		       }
			    	//통신 성공 (response.result == "success" )
			    	if(response.result == "success" ) {
			    		$( response.data ).each( function(index, vo){
//			 				console.log( index + ":" + vo );
							render( vo , true);
						})
						
						//초기화
						$("#name").val("");
			    		$("input[name='desc']").val("");
			    		
			    	}
			    		
			       return true;
			    },
			    error: function( XHR, status, error ){
			       console.error( status);
			       console.error( error );
			    }

			   });
		})
		
		$(document).on("click","#list tr td a", function(event) {
			//"#list_del"
			event.preventDefault();
			var list_no = $(this).attr("data-no");
			//console.log("test"+list_no);
			
			//포스트 수로 삭제 가능 처리
// 			var postCount = $("#postCount").text();
// 			console.log("postCount:"+postCount);
// 			return false; 
			
			// Ajax 통신
			$.ajax({
				url : "/jblog/${blogId}/admin/category/del",
				type : "get",
				dataType : "json",
				data : "no=" + list_no,
				//  contentType: "application/json",
				success : function(response) {
					console.log(response);
					if (response.result == "fail") {
						console.log(response);
						
					// 		    	   return;
					}
					//통신 성공 (response.result == "success" )
					if (response.result == "success") {
						list_count--;
						$("#list_"+list_no).remove();
					}

					return true;
				},
				error : function(XHR, status, error) {
					alert("삭제할 수 없습니다.");
					console.error(status + " : " + error);
				}
			});

		})
		
// 		$("#list_del").click( function() {
// 			event.preventDefault();
// 			var list_no = $("#list_del").attr("data-no");
// 			console.log("test"+list_no);
			
			
// 		})
		
		
	})
</script>
</head>
<body>
	<div id="container">
		<c:import url="/WEB-INF/views/include/header.jsp" />
		<div id="wrapper">
			<div id="content" class="full-screen">
				<c:import url="/WEB-INF/views/include/admin-menu.jsp" />
				<table class="admin-cat" id="list">
					<tr>
		      			<th>번호</th>
		      			<th>카테고리명</th>
		      			<th>포스트 수</th>
		      			<th>설명</th>
		      			<th>삭제</th>      			
		      		</tr>
<%-- 		      		<c:set var="countList" value="${fn:length(list) }" /> --%>
					<c:forEach var="vo" items="${list}" varStatus="status">
						<tr id="list_${vo.no }">
							<td>${status.index + 1 }</td>
							<td>${vo.name }</td>
							<td>${vo.postCount }</td>
							<td>${vo.description }</td>
							<td><a href="" id="list_del" data-no="${vo.no}"><img src="${pageContext.request.contextPath}/assets/images/delete.jpg"></a></td>
						</tr>  
					</c:forEach>
				</table>
      	
      			<h4 class="n-c">새로운 카테고리 추가</h4>
		      	<table id="admin-cat-add">
		      		<tr>
		      			<td class="t">카테고리명</td>
		      			<td><input type="text" name="name" id="name"></td>
		      		</tr>
		      		<tr>
		      			<td class="t">설명</td>
		      			<td><input type="text" name="desc"></td>
		      		</tr>
		      		<tr>
		      			<td class="s">&nbsp;</td>
		      			<td><input type="submit" value="카테고리 추가" id="cat-add"></td>
		      		</tr>      		      		
		      	</table> 
			</div>
		</div>
		<c:import url="/WEB-INF/views/include/footer.jsp" />
	</div>
</body>
</html>