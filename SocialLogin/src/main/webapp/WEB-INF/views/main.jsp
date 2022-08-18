<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<head>
	<title>Main</title>
	<script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
</head>
<body>

<div>

<h2> accessToken</h2>

<input type="text" value="${userInfo.nickname }">
<input type="text" value="${userInfo.birthday }">
<input type="text" value="${userInfo.email }">

</div>

</body>
 <script src="https://developers.kakao.com/sdk/js/kakao.js"></script>
 
<script type="text/javascript">
	
</script>

</html>
