<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>boardWrite</title>
</head>
<body>
<form action="/board/write" method="post">
	<input type="hidden" name="b_id" value="${mb.m_id}"><br>
	<input type="text" name="b_title" autofocus placeholder="제목"><br>
	<textarea rows="15" name="b_contents" autofocus placeholder="내용">
	</textarea>
	<input type="submit" value="W">
	<input type="reset" value="R">
</form>
</body>
</html>