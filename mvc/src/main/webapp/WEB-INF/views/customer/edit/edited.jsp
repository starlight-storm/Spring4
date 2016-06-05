<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>更新完了</title>
</head>
<body>
<h1>更新完了</h1>
<dl>
  <dt>名前</dt>
  <dd><c:out value="${editCustomer.name}"/></dd>
  <dt>Eメールアドレス</dt>
  <dd><c:out value="${editCustomer.emailAddress}"/></dd>
  <dt>誕生日</dt>
  <dd><fmt:formatDate pattern="yyyy/MM/dd" value="${editCustomer.birthday}"/></dd>
  <dt>好きな数字</dt>
  <dd><c:out value="${editCustomer.favoriteNumber}"/></dd>
</dl>
<c:url var="url" value="/customer"/>
<a href="${url}">戻る</a>
</body>
</html>