<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:include page="../layout/header.jsp" />
	<div class="container-md">
<h1>board register page</h1>
		<form action="/board/insert" method="post" enctype="multipart/form-data">
			<div class="mb-3">
			  <label for="t" class="form-label">title</label>
			  <input type="text" class="form-control" name="title" id="formGroupExampleInput" placeholder="title">
			</div>
			
			<div class="mb-3">
			  <label for="w" class="form-label">writer</label>
			  <input type="text" class="form-control" name="writer" id="formGroupExampleInput" placeholder="writer" value="${ses.id }">
			</div>
			
			<div class="input-group">
			  <span class="input-group-text">content</span>
			  <textarea class="form-control" name="content" aria-label="With textarea"></textarea>
			</div>
			
			<div class="mb-3">
			  <label for="file" class="form-label">file</label>
			  <input type="file" class="form-control" name="files" id="file" multiple="multiple" style="display: none"> <br>
			  <button type="button" class="btn btn-success" id="trigger">파일 업로드</button>
			</div>
			
			 
			<div class="mb-3" id="fileZone">
			
			</div>			
			<button type="submit" class="btn btn-primary" id="regBtn">등록</button>
</form>
</div>
<script type="text/javascript" src="/resources/js/boardRegister.js">

</script>
<jsp:include page="../layout/footer.jsp" />
