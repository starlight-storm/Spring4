<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>指定された顧客は見つかりません</title>
</head>
<body>
<h1>指定された顧客は見つかりません</h1>
<c:url value="/customer" var="url"/>
<a href="${url}">一覧画面へ戻る</a>
</body>
</html>