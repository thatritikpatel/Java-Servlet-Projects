<!doctype html>
<html lang="en">
 <head>
  <meta charset="UTF-8">
  <link rel="stylesheet" type="text/css" href="static/css/common.css" />
  <link rel="stylesheet" type="text/css" href="static/css/form.css" />
  <title>User SignIn</title>
 </head>
 <body>
  <div id="container">
	<%@ include file="header.jsp" %>

	<div id="main_body">
		<% String errmsg = (String)request.getAttribute("errmsg"); %>
		
		<% if(errmsg!=null){ %>
			<div id="errbox"><%= errmsg %></div>
		<% } %>	

		<form action="signin.do" method="post">
			<table>
				<caption>User SignIn</caption>
				<tr>
					<th>Email</th>
					<td>
						<input type="email" name="email" class="fld" />
					</td>
				</tr>
				<tr>
					<th>Password</th>
					<td>
						<input type="password" name="pass" class="fld" />
					</td>
				</tr>
				<tr>
					<td colspan="2" id="btn_box">
						<input type="submit" value="SignIn" id="btn" />
					</td>
				</tr>
			</table>
		</form>
	</div>

	<%@ include file="footer.jsp" %>
  </div>
 </body>
</html>
