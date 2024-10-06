<!doctype html>
<html lang="en">
 <head>
  <meta charset="UTF-8">
  <link rel="stylesheet" type="text/css" href="static/css/common.css" />
  <link rel="stylesheet" type="text/css" href="static/css/index.css" />
  <title>Welcome To ToDoApp</title>
  <style>
	  body {
		/* background-color: #e4d3cf; */
	  }
	  #container {
		  color:#2f5d62;
		  
	  }
	  #greeting{
		  text-align:center;
		  margin-top: 100px;
	  }

  </style>
 </head>
 <body>
  <div id="container">
	<%@ include file="header.jsp" %>

	
	<div id="greeting">
		<h1>
			Welcome To ToDoApp
		</h1>
	</div>	

	

	<%@ include file="footer.jsp" %>
  </div>
 </body>
</html>
