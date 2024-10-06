<!doctype html>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html lang="en">
 <head>
  <meta charset="UTF-8">
  <link rel="stylesheet" type="text/css" href="static/css/common.css" />
  <link rel="stylesheet" type="text/css" href="static/css/form.css" />
  <title>User SignUp</title>
 </head>
 <body>
  <div id="container">
	<%@ include file="header.jsp" %>

	<div id="main_body">
		
		<% String str = (String)request.getAttribute("message"); %>	

		<% if(str!=null&&!str.equals("")) {%>
		<div id="msg"><%= str %></div>
		<% } %>

		<form action="signup.do" method="post">
			<table>
				<caption>User SignUp</caption>
				<tr>
					<th>User Name<sup>*</sup></th>
					<td>
						<input type="text" name="uname" class="fld" />
					</td>
				</tr>
				<tr>
					<th>Email<sup>*</sup></th>
					<td>
						<input type="email" name="email" class="fld" />
					</td>
				</tr>
				<tr>
					<th>Password<sup>*</sup></th>
					<td>
						<input type="password" name="pass" class="fld" />
					</td>
				</tr>
				<tr>
					<th>Retype Password<sup>*</sup></th>
					<td>
						<input type="password" name="repass" class="fld" />
					</td>
				</tr>
				<tr>
					<th>Country</th>
					<td>
						<select name="country_id" class="fld">
							<option value="0">Country</option>

							<c:forEach var="country" items="${countries}">
								<option value="${country.countryId}">${country.country}</option>
							</c:forEach>
						</select>
					</td>
				</tr>
				<tr>
					<th>Mobile<sup>*</sup></th>
					<td>
						<input type="text" maxlength="10" name="mobile" class="fld" id="mobile" />
						<input type="button" value="Send OTP" id="otp_btn"  />
						<img src="static/images/loader.gif" height="20px" id="loader1" />
					</td>
				</tr>
				<tr id="otp_box">
					<th>OTP<sup>*</sup></th>
					<td>
						<input type="text" maxlength="6" id="otp" class="fld"  />
						<input type="button" value="Submit" id="otp_sbm"  />
						<img src="static/images/loader.gif" height="20px" id="loader2" />
					</td>
				</tr>
				<tr>
					<td colspan="2" align="center">
						<div class="g-recaptcha" data-sitekey="6Lcyb5IaAAAAAGCeEPMDJomRZYJntCaoCgJOomH-"></div>
					</td>
				</tr>				
				<tr>
					<td colspan="2" id="btn_box">
						<input type="submit" value="SignUp" title="First of all fillup the form then press submit!!!" id="btn" />
						<!--disabled-->
					</td>
				</tr>
			</table>
			<div id="note">*Mandatory Fields...</div>
		</form>
	</div>

	<%@ include file="footer.jsp" %>
  </div>

  <script src="static/js/signup.js"> </script>
  <script src="https://www.google.com/recaptcha/api.js" async defer></script>
 </body>
</html>
