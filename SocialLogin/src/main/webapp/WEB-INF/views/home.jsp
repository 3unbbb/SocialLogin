<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<head>
	<title>Home</title>
	<script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
</head>
<body>

<div>
	<a id="custom-login-btn" href="javascript:loginWithKakao()">
		<img src="../resources/image/kakao_login.png" name="kakao">
	</a>
</div>

</body>
 <script src="https://developers.kakao.com/sdk/js/kakao.js"></script>
 
<script type="text/javascript">
window.Kakao.init('a6d9405f4310c34a3488e64ffc7b21a1');


  function loginWithKakao() {		
	window.Kakao.Auth.authorize({
		scope : 'profile_nickname, profile_image, account_email, gender, birthday', 
		success : function(data){
			console.log(data);
		window.location.href='/kakao';
		}
	});
  }
	

</script>


</html>
