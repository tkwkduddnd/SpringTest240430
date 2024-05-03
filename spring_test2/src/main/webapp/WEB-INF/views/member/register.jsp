<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<jsp:include page="../layout/header.jsp" />
<div class="container-md">
	<h1>member join page</h1>
	<form action="/member/register" method="post">
			<div class="mb-3">
			  <label for="i" class="form-label">ID</label>
			  <input type="text" class="form-control" name="id" id="i" placeholder="id">
			</div>
			
			<div class="mb-3">
			  <label for="p" class="form-label">Password</label>
			  <input type="password" class="form-control" name="pw" id="p" placeholder="pw">
			</div>
			
			<div class="mb-3">
			  <label for="n" class="form-label">name</label>
			  <input type="text" class="form-control" name="name" id="n" placeholder="name">
			</div>
			
			<div class="mb-3">
			  <label for="h" class="form-label">home</label>
			  <input type="text" class="form-control" name="home" id="h" placeholder="home">
			</div>
			
			<div class="mb-3">
			  <label for="e" class="form-label">email</label>
			  <input type="email" class="form-control" name="email" id="e" placeholder="email">
			</div>
			
			<div class="mb-3">
			  <label for="a" class="form-label">age</label>
			  <input type="text" class="form-control" name="age" id="a" placeholder="age">
			</div>
			
			
			<button type="submit" class="btn btn-primary">가입</button>
</form>
	
	
	
	
	
	
	
	
	
	
	<jsp:include page="../layout/footer.jsp" />