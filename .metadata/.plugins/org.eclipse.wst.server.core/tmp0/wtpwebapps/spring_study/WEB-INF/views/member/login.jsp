<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

    <jsp:include page="../layout/header.jsp" />
<div class="container-md">
	<h1>member join page</h1>
	<form action="/member/login" method="post">
			<div class="mb-3">
			  <label for="i" class="form-label">ID</label>
			  <input type="text" class="form-control" name="id" id="i" placeholder="id">
			</div>
			
			<div class="mb-3">
			  <label for="p" class="form-label">Password</label>
			  <input type="password" class="form-control" name="pw" id="p" placeholder="pw">
			</div>
			
			
			<button type="submit" class="btn btn-primary">로그인</button>
</form>
	
	
	
	
	
	<jsp:include page="../layout/footer.jsp" />