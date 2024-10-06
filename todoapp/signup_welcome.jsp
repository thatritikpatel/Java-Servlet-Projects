<!doctype html>
<html lang="en">
 <head>
  <meta charset="UTF-8">
  <link rel="stylesheet" type="text/css" href="static/css/common.css" />
  <link rel="stylesheet" type="text/css" href="static/css/signup_welcome.css" />
  <title>Welcome To ToDoApp</title>
 </head>
 <body>
  <div id="container">
	<%@ include file="header.jsp" %>

	<div id="main_body">
		<div id="msg">
			Welcome!
			<hr />
			<span id="msg_txt">
				Verification mail has been sent. Please check your email and click over the activation link.
			</span>
		</div>
	</div>

	<%@ include file="footer.jsp" %>
  </div>
 </body>
</html>
