<!doctype html>

<%@ taglib prefix="app" uri="todoapp_v1" %>

<html lang="en">
 <head>
  <meta charset="UTF-8">
  
  <link rel="stylesheet" type="text/css" href="static/css/common.css" />
  <link rel="stylesheet" type="text/css" href="static/css/profile.css" />
  
  <script src="static/js/menu.js"> </script>
  <script src="static/js/profile.js"> </script>
  <script src="static/js/common.js"> </script>

  <title>User Profile</title>
 </head>
 <body>
  <div id="container">
	<%@ include file="header.jsp" %>

	<%@ include file="menu.jsp" %>
	<input type="hidden" id="page" value="0" />

	<div id="main_body">
		<table id="prof_box">
			<tr>
				<td id="lft_box">
					<img src="${empty user.profpic?'static/images/user2.png':'showpic.do'}" id="userpic">
					<form action="uploadpic.do" method="post" enctype="multipart/form-data">
						<input type="file" name="profpic"><br><br>
						<input type="submit" value="Upload">
					</form>
				</td>
				<td id="rht_box">
					<table id="prof_rec">
						<tr>
							<th>User Name :</th>
							<td>
								<input type="text" id="unm" value="${user.userName}">
							</td>
						</tr>
						<tr>
							<th>Email :</th>
							<td>
								<input type="email" id="eml" value="${user.email}" readonly>
							</td>
						</tr>
						<tr>
							<th>Password :</th>
							<td>
								<input type="password" id="pwd" value="123456">
							</td>
						</tr>
						<tr>
							<th>Account Created :</th>
							<td>
								<app:date value="${user.created}" />
							</td>
						</tr>
						<tr>
							<th>Country :</th>
							<td>
								${user.country.country}
							</td>
						</tr>
						<tr>
							<th>Mobile :</th>
							<td>
								<input type="text" id="mobi" value="${user.mobile}">
							</td>
						</tr>
					</table>				
				</td>
			</tr>
		</table>
	</div>

	<%@ include file="footer.jsp" %>
  </div>
 </body>
</html>
