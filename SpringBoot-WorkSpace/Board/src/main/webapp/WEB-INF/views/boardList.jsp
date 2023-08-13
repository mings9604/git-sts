<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시글 목록</title>
<script src="/js/jquery-3.7.0.min.js"></script>
<link rel="stylesheet" href="/css/style.css">
<script>
let m='${msg}';
if(m!=''){
	alert(m)
}
	$(()=>{
		let loginName='${mb.m_name}';
		$('#m_name').html(loginName+' 님');
		$('.suc').css('display', 'block');
		$('.bef').css('display', 'none');
	})
</script>
</head>
<body>


	<%-- <c:forEach var="bitem" items="${bList}"> --%>
	<%-- 	${bitem.b_num}, ${bitem.b_title}, ${bitem.b_name}, ${bitem.b_date}, ${bitem.b_views} <br> --%>
	<%-- </c:forEach> --%>

	<div class="wrap">
		<header>
			<jsp:include page="header.jsp"></jsp:include>
		</header>
		<section>
			<div class="data-area">
				<div class="title-row">
					<div class="t-no p-10">번호</div>
					<div class="t-title p-30">제목</div>
					<div class="t-name p-15">작성자</div>
					<div class="t-date p-30">작성일</div>
					<div class="t-view p-15">조회수</div>
				</div>
				<div class="data-row">
					<c:if test="${empty bList}">
						<div style="width: 100%">게시글이 없습니다.</div>
					</c:if>
					<c:if test="${!empty bList}">
						<c:forEach var="bitem" items="${bList}">
							<div class="t-no p-10">${bitem.b_num}</div>
							<div class="t-title p-30">
								<a href="/board/contents?b_num=${bitem.b_num}">
									${bitem.b_title} </a>
							</div>
							<div class="t-name p-15">${bitem.b_name}</div>
							<div class="t-date p-30">
								<fmt:formatDate value="${bitem.b_date}"
									pattern="yyyy-MM-dd HH:mm:ss"></fmt:formatDate>
							</div>
							<div class="t-view p-15">${bitem.b_views}</div>
						</c:forEach>
					</c:if>
				</div>
			</div>
			<div class="btn-area">
				<div class="paging">${paging}</div>
				<button class="wr-btn" onclick="location.href='/board/write'">
					글쓰기</button>
			</div>
			<footer>
				<jsp:include page="footer.jsp"></jsp:include>
			</footer>
		</section>
	</div>


</body>
</html>