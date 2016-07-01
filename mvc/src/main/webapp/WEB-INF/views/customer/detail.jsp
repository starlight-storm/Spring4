<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>顧客詳細画面</title>
</head>
<body>
<h1>顧客詳細画面</h1>
<dl>
  <dt>名前</dt>
  <dd><c:out value="${customer.name}"/></dd>
  <dt>Eメールアドレス</dt>
  <dd><c:out value="${customer.emailAddress}"/></dd>
  <dt>誕生日</dt>
  <dd><fmt:formatDate pattern="yyyy/MM/dd" value="${customer.birthday}"/></dd>
  <dt>好きな数字</dt>
  <dd><c:out value="${customer.favoriteNumber}"/></dd>
</dl>
<c:url value="/customer" var="url"/>
<a href="${url}">一覧</a>
</body>
</html>