<!doctype html>
<html lang="en">
 <head>
  <meta charset="UTF-8">
  
  <link rel="stylesheet" type="text/css" href="static/css/common.css" />
  <link rel="stylesheet" type="text/css" href="static/css/connects.css" />
  
  <script src="static/js/menu.js"> </script>
  <script src="static/js/connects.js"> </script>
  <script src="static/js/common.js"> </script>

  <title>My Connects</title>
 </head>
 <body>
  <div id="container">
	<%@ include file="header.jsp" %>

	<%@ include file="menu.jsp" %>
	<input type="hidden" id="page" value="2" />
	
	<input type="hidden" id="userid" value="${user.userId}">
	<div id="main_body">
		<div id="main_container">
			<div id="lft_dv">
				<div id="all_con" class="con_act con_act_ov">
					<span>All Connects</span>
					<img src="static/images/arrow.png" id="arr1" class="arw" />
				</div>
				<div id="new_con" class="con_act">
					<span>Find Connect</span>
					<img src="static/images/arrow.png" id="arr2" class="arw" />
				</div>
				<div id="inv_con" class="con_act">
					<span>New Invites</span>
					<img src="static/images/arrow.png" id="arr3" class="arw" />
				</div>
			</div>
			
			<div id="rht_dv">
				<div id="act_box">
					<div id="srch_bar">
						Search User: <input type="text" id="srch_fld">
						<div id="rec_box"></div>
						<input type="button" value="Find &gt;&gt;" id="find_btn">
					</div>
					<div id="recs">
						
					</div>
				</div>			
			</div>
		</div>
	</div>

	<%@ include file="footer.jsp" %>
  </div>
 </body>
</html>
