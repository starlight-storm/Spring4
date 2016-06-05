<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>入力画面</title>
</head>
<body>
<h1>入力画面</h1>
<form:form modelAttribute="editCustomer">
<dl>
  <dt>名前</dt>
  <dd>
    <form:input path="name"/>
    <form:errors path="name"/>
  </dd>
  <dt>Eメールアドレス</dt>
  <dd>
    <form:input path="emailAddress"/>
    <form:errors path="emailAddress"/>
    <form:errors path="ngEmail"/>
  </dd>
  <dt>誕生日</dt>
  <dd>
    <form:input path="birthday"/>
    <form:errors path="birthday"/>
  </dd>
  <dt>好きな数字</dt>
  <dd>
    <form:input path="favoriteNumber"/>
    <form:errors path="favoriteNumber"/>
  </dd>
</dl>
<button type="submit" name="_event_proceed" value="proceed">
  次へ
</button>
</form:form>
</body>
</html>
