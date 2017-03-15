<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!doctype html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>JBlog</title>
<Link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/jblog.css">
<link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<script type="text/javascript" src="${pageContext.request.contextPath }/assets/js/jquery/jquery-1.9.0.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<script type="text/javascript">
$(function() {
	$("#join-form").submit( function() {
		//0 Validation은 @Valid로 처리
		
		//1. 약관동의
		var isChecked = $( "#agree-prov" ).is(":checked");
		if( isChecked == false ){
			alert("약관 동의를 해주세요");
			return false;
		}
		
		//2. 중복 체크 확인
    	var isVisible = $( "#img-checkemail" ).is(":visible");
    	if( isVisible == false) {
    		alert("아이디 중복 체크를 해주세요");
    		return false;
    	}

	})
	$( "input[type='button']" ).click ( function(){
// 		console.log("checked");
		var id = $( "#id" ).val();
		if( id == "" ) {
			alert( "아이디가 비여 있습니다." );
			$( "#id ").focus();
			return;
		}
		
		// Ajax 통신
		$.ajax( {
		    url : "${pageContext.request.contextPath }/user/checkid?id="+id,
		    type: "get",
		    dataType: "json",
		    data: "",
		//  contentType: "application/json",
		    success: function( response ){
		    	console.log	( response );
		       if( response.result == "failed") {
		    	   console.log( response );
		       }
		    	//통신 성공 (response.result == "success" )
		    	
		    	if( response.data == "exists" ) {
		    		alert("존재하는 아이디 입니다. 다른 이메일을 사용해 주세요");
		    		$("#id").
// 		    		val("").
		    		focus();
		    		
		    		return;
		    	}
		    	$( "#img-checkemail" ).show();
		    	$( "input[type='button']" ).hide();
		    	
		       return true;
		    },
		    error: function( XHR, status, error ){
		       console.error( status + " : " + error );
		    }

		   });

	});
	
	$( "#id" ).change( function() {
		$( "#img-checkemail" ).hide();
    	$( "input[type='button']" ).show();
	});
});

</script>
</head>
<body>
	<div class="center-content">
		<h1 class="logo">JBlog</h1>
		<c:import url="/WEB-INF/views/include/menu.jsp" />
		<form:form modelAttribute="userVo" class="join-form" id="join-form" method="post" action="${pageContext.request.contextPath}/user/join">
			<label class="block-label" for="name">이름</label>
			<form:input path="name" />
				<p style="font-weight:bold; text-align:left;padding:5px 0; color:red">
					<form:errors path="name" />
				</p>
			<label class="block-label" for="blog-id">아이디</label>
			<form:input path="id" />
			<input id="btn-checkemail" type="button" value="id 중복체크">
			<img id="img-checkemail" style="display: none;" src="${pageContext.request.contextPath}/assets/images/check.png">
				<p style="font-weight:bold; text-align:left;padding:5px 0; color:red">
					<form:errors path="id" />
				</p>
<!-- 			<input id="blog-id" name="id" type="text">  -->
			<label class="block-label" for="password">패스워드</label>
			<form:password path="password" />
				<p style="font-weight:bold; text-align:left;padding:5px 0; color:red">
					<form:errors path="password" />
				</p>
<!-- 			<input id="password" name="password" type="password" /> -->
			<fieldset>
				<legend>약관동의</legend>
				<input id="agree-prov" type="checkbox" name="agreeProv" value="y">
				<label class="l-float">서비스 약관에 동의합니다.</label>
			</fieldset>

			<input type="submit" value="가입하기">

		</form:form>
	</div>
</body>
</html>
