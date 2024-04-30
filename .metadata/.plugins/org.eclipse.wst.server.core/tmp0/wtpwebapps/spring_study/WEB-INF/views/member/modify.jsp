<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<jsp:include page="../layout/header.jsp" />
<div class="container-md">
	<h1>member Modify page</h1>
	<form action="/member/modify" method="post">
			<div class="mb-3">
			  <label for="i" class="form-label">ID</label>
			  <input type="text" class="form-control" name="id" id="i" value="${ses.id }" readonly="readonly" placeholder="id">
			</div>
			
			<div class="mb-3">
			  <label for="i" class="form-label">REG_DATE</label>
			  <input type="text" class="form-control" name="reg_date" id="i" value="${ses.reg_date }" readonly="readonly" placeholder="id">
			</div>
			
			<div class="mb-3">
			  <label for="i" class="form-label">Last_login</label>
			  <input type="text" class="form-control" name="last_login" id="i" value="${ses.last_login }" readonly="readonly" placeholder="id">
			</div>
			
			<div class="mb-3">
			  <label for="p" class="form-label">Password</label>
			  <input type="password" class="form-control" name="pw" id="p" placeholder="pw">
			</div>
			
			<div class="mb-3">
			  <label for="n" class="form-label">name</label>
			  <input type="text" class="form-control" name="name" id="n" value="${ses.name }" placeholder="name">
			</div>
			
			<div class="mb-3">
			  <label for="h" class="form-label">home</label>
			  <input type="text" class="form-control" name="home" id="h" value="${ses.home }" placeholder="home">
			</div>
			
			<div class="mb-3">
			  <label for="e" class="form-label">email</label>
			  <input type="email" class="form-control" name="email" id="e" value="${ses.email }" placeholder="email">
			</div>
			
			<div class="mb-3">
			  <label for="a" class="form-label">age</label>
			  <input type="text" class="form-control" name="age" id="a" value="${ses.age }" placeholder="age">
			</div>
			
			
			<button type="submit" class="btn btn-primary">수정</button>
			<a href="/member/remove"><button type="button" class="btn btn-danger">회원탈퇴</button></a>
</form>
	
	
	
	
	<jsp:include page="../layout/footer.jsp" />