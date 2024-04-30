<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:include page="../layout/header.jsp" />
	<div class="container-md">
<h1>board Detail page</h1>
<c:set value="${bdto.bvo }" var="bvo"></c:set>
			<div class="mb-3">
			  <label for="n" class="form-label">bno</label>
			  <input type="text" class="form-control" name="bno" id="n" value="${bvo.bno }" readonly="readonly" placeholder="title">
			</div>
			<div class="mb-3">
			  <label for="t" class="form-label">title</label>
			  <input type="text" class="form-control" name="title" id="t" value="${bvo.title }" readonly="readonly" placeholder="title">
			</div>
			<div class="mb-3">
			  <label for="w" class="form-label">writer</label>
			  <input type="text" class="form-control" name="writer" id="w" value="${bvo.writer }" readonly="readonly" placeholder="writer">
			</div>
			<div class="mb-3">
			  <label for="r" class="form-label">reg_date</label>
			  <input type="text" class="form-control" name="reg_date" id="r" value="${bvo.reg_date }" readonly="readonly" placeholder="title">
			</div>
			<div class="input-group">
			  <span class="input-group-text">content</span>
			  <textarea class="form-control" name="content" id="c" aria-label="With textarea" readonly="readonly">${bvo.content }</textarea>
			</div>
			<div class="mb-3">
			  <label for="u" class="form-label">read_count</label>
			  <input type="text" class="form-control" name="reg_date" id="u" value="${bvo.read_count }" readonly="readonly" placeholder="title">
			</div>
			
			<!-- file-upload 표시라인 -->
			<c:set value="${bdto.flist }" var="flist" />
			<div class="md-3">
				<ul class="list-group list-group-flush">
				<!-- 파일 개수만큼 li를 반복하여 파일 표시 타입이 1인경우만 표시 -->
				<!-- li => div => img -->
				<!-- => div => 파일 이름, 작성일, span size -->
				<c:forEach items="${flist }" var="fvo">
  				<li class="list-group-item">An item</li>
  					<c:choose>
  						<c:when test="${fvo.file_type > 0 }">
  							<div>
  								<img alt="그림 없음" src="/upload/${fvo.save_dir }/${fvo.uuid}_th_${fvo.file_name}">
  							</div>
  						</c:when>
  						<c:otherwise>
  							<div>
  								<!-- 파일 타입이 0인 경우 아이콘 모양 하나 가져와서 넣기 -->
  							</div>
  						</c:otherwise>
  					</c:choose>
  					<div>
  						<!-- 파일 이름, 작성일, 사이즈 -->
  						<div>${fvo.file_name }</div>
  						${fvo.reg_date }
  						<span class="badge rounded-pill text-bg-warning">${fvo.file_size }Byte</span>
  					</div>
				</c:forEach>
				</ul>
			</div>
			<br>
			<hr>
			
			<!-- Comment line -->
			<!-- 댓글 등록 라인 -->
			<div class="input-group mb-3">
			  <span class="input-group-text" id="cmtWriter">${ses.id }</span>
			  
			  <input type="text" id="cmtText" class="form-control" placeholder="Add Comment" aria-label="Username" aria-describedby="basic-addon1">
			  <button type="button" id="cmtAddBtn" class="btn btn-danger">Post</button>
			</div>
			
			<!-- 댓글 출력 라인 -->
			
			<div class="accordion" id="accordionExample">
  <div class="accordion-item">
    <h2 class="accordion-header">
      <button class="accordion-button" type="button" data-bs-toggle="collapse" data-bs-target="#collapseOne" aria-expanded="true" aria-controls="collapseOne">
        cno, writer, reg_date
      </button>
    </h2>
    <div id="collapseOne" class="accordion-collapse collapse show" data-bs-parent="#accordionExample">
      <div class="accordion-body">
      <strong>댓글 내용 표시</strong>
      </div>
    </div>
  </div>
</div>
<br>
			
			
			
			<c:if test="${ses.id eq bvo.writer }">
			<a href="/board/modify?bno=${bvo.bno }"><button type="button" class="btn btn-warning">수정</button></a>
			<a href="/board/remove?bno=${bvo.bno }"><button type="button" class="btn btn-secondary">삭제</button></a>
			<a href="/board/list"><button type="button" class="btn btn-success">list</button></a>
			</c:if>
</div>


<script type="text/javascript">

const bnoVal = `<c:out value="${bvo.bno}" />`;
const id = `<c:out value="${ses.id}" />`;
console.log(bnoVal);

</script>
<script type="text/javascript" src="/resources/js/boardDetailComment.js"></script>

<script type="text/javascript">
spreadCommentList(bnoVal);
</script>





<jsp:include page="../layout/footer.jsp" />
