<!doctype html>
<html lang="en">
 <head>
  <meta charset="UTF-8">
  
  <link rel="stylesheet" type="text/css" href="static/css/common.css" />
  <link rel="stylesheet" type="text/css" href="static/css/dashboard.css" />
  
  <script src="static/js/menu.js"> </script>
  <script src="static/js/dashboard.js"> </script>
  <script src="static/js/common.js"> </script>

  <title>User Dashboard</title>
 </head>
 <body>
  <div id="container">
	<%@ include file="header.jsp" %>

	<%@ include file="menu.jsp" %>
	<input type="hidden" id="page" value="0" />

	<div id="main_body">
		<table id="dashboard_box">
			<tr>
				<td id="act_todos" class="act_recs">
					<div id="act_todos_ttl" class="_ttl">Recent ToDos</div>
					<div id="act_todos_recs"></div>
				</td>
				<td id="act_shared" class="act_recs">
					<div id="act_shared_ttl" class="_ttl">Shared ToDos</div>
					<div id="act_shared_recs"></div>
				</td>
				<td id="act_invites" class="act_recs">
					<div id="act_invites_ttl" class="_ttl">New Invites</div>
					<div id="act_invites_recs"></div>
				</td>
			</tr>
		</table>
	</div>

	<%@ include file="footer.jsp" %>
  </div>
 </body>
</html>
