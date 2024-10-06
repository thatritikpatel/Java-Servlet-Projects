<!doctype html>
<html lang="en">
 <head>
  <meta charset="UTF-8">
  
  <link rel="stylesheet" type="text/css" href="static/css/common.css" />
  <link rel="stylesheet" type="text/css" href="static/css/mytodos.css" />
  
  <script src="static/js/menu.js"> </script>
  <script src="static/js/mytodos.js"> </script>
  <script src="static/js/common.js"> </script>	

  <title>My ToDos</title>
 </head>
 <body>
  <div id="container">
	<%@ include file="header.jsp" %>

	<%@ include file="menu.jsp" %>
	<input type="hidden" id="page" value="1" />
	<input type="hidden" id="uid" value="${user.userId}" />

	<div id="main_body">
		<div id="todos_ctrl_box">
			<div id="ctrl_header">
				<span id="new_lbl">New:</span>				
				<img src="static/images/textnote3.png" title="New Text-Note" id="nw_txnote" />
				<img src="static/images/todo3.png" title="New Check List"  id="nw_chklist" />
				<img src="static/images/numbered.png" title="New Numbered List" id="nw_numlist" />
			</div>

			<div id="msg_"></div>
			<div id="txnote_ttl_box">
				<span id="author"></span>
				<img src="static/images/title_arrow.png" id="ttl_arrw" />
				<div id="samp_ttl">Here goes your title...</div>
				<input type="text" id="title" maxlength="45" />
				<img src="static/images/edit.png" id="ttl_edit" title="Set/Change Title" />
				<span id="sv_todo">Save</span>
				<span id="srch_blk">
					Share with: 
					<select id="srchkey">
						<option value="0">Select</option>
					</select>
					<!-- <input list="myconnects" type="text" id="srchkey">
					<datalist id="myconnects">
						
					</datalist> -->
					<input type="button" value="Share" id="share_">
				</span>
				<img src="static/images/delete.png" id="del_rec">
				<input type="hidden" id="todotype" />
				<input type="hidden" id="_todoid_" />
			</div>

			<!-- ###################Text Note##################### -->
			<div id="todo_tnote_box">				
				<textarea id="txarea" name="txnote"></textarea>
			</div>
			<!-- ####################Text Note################### -->


			<!-- ###################Itemed List##################### -->
			<div id="todo_itemed_list_box">
				<table id="itemed_rec_box">
					<caption class="cap _cap">Active Steps:</caption>		
				</table>

				<table id="done_rec_box">
					<caption class="cap _cap_">Steps Done:</caption>
				</table>
			</div>			
			<!-- ###################Itemed List##################### -->


			<!-- ####################No Recs################## -->
			<div id="norecs">No Records to Show...!</div>
			<!-- ####################No Recs#################### -->

			<!-- ####################All Recs#################### -->
			<div id="all_recs_box"><div>
			<!-- ####################All Recs################### -->
		</div>
	</div>

	<%@ include file="footer.jsp" %>
  </div>
 </body>
</html>
