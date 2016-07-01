<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <sec:csrfMetaTags/>
</head>
<body>
<sec:authorize access="isAnonymous()">
未ログイン
</sec:authorize>
<sec:authorize access="isAuthenticated()">
  <sec:authentication var="user" property="principal"/>
  ログインユーザ：${user.username}
</sec:authorize>
<h1>トップページ</h1>
トップページです。
<ul>
  <li><a href="user/user.jsp">一般ユーザ用ページへ</a></li>
  <sec:authorize access="hasRole('ADMIN')">
    <li><a href="admin/admin.jsp">管理者専用ページへ</a></li>
  </sec:authorize>
</ul>
<form action="logout" method="post">
  <sec:csrfInput />
  <button>ログアウト</button>
</form>
</body>
</html>
