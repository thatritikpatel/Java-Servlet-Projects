
var act_todos_recs,act_shared_recs,act_invites_recs;

function getAllElements(){
	getAllMenuElements();

	act_todos_recs = document.getElementById('act_todos_recs');
	act_shared_recs = document.getElementById('act_shared_recs');
	act_invites_recs = document.getElementById('act_invites_recs');
}

function setAllActions(){
	setAllMenuActions();

	collectRecentToDos();
	
	collectSharedToDos();
	
	collectNewInvites();
}

var req2;
function collectSharedToDos(){
	
	req2 = new XMLHttpRequest();
	req2.open('get','actvsharedtodos.do',false);
	req2.onreadystatechange = showSharedToDos;
	req2.send();
}

function showSharedToDos(){
	//alert(req2.readyState+' - '+req2.status)
	if(req2.readyState==4&&req2.status==200){
		if(req2.responseText!='expired'){
			//alert(req2.responseText)
			var res = JSON.parse(req2.responseText);

			showActiveRecords(res,act_shared_recs);
		}else{
			window.location = "showsignin.do";
		}
	}
}

var req1;
function collectRecentToDos(){
	req1 = new XMLHttpRequest();
	req1.open('get','actvrecenttodos.do',false);
	req1.onreadystatechange = showRecentToDos;
	req1.send();
}

function showRecentToDos(){
	if(req1.readyState==4&&req1.status==200){
		if(req1.responseText!='expired'){
			//alert(req1.responseText)
			var res = JSON.parse(req1.responseText);

			showActiveRecords(res,act_todos_recs);
		}else{
			window.location = "showsignin.do";
		}
	}
}


function showActiveRecords(res,act_rec_box){
	if(res.length!=0){
		for(i=0;i<res.length;i++){
			var dv = document.createElement('div');
			dv.className = 'mytodorec_';
			dv.todoid = res[i].todoListId;
			dv.onclick = function(){ window.location = 'mytodos.do';}
			
			var img = document.createElement('img');
			img.className = 'img_';
			img.src = res[i].toDoType.toDoTypeId==1?'static/images/textnote3.png':'static/images/todo3.png';
			dv.appendChild(img);					

			var ttl = document.createElement('div');
			ttl.className = 'ttl_';
			ttl.innerHTML = res[i].title;
			dv.appendChild(ttl);

			var crt = document.createElement('div');
			crt.className = 'crt_';
			crt.innerHTML = res[i].created;
			dv.appendChild(crt);

			var aut = document.createElement('div');
			aut.className = 'aut_';
			aut.innerHTML = 'Author: '+res[i].user.userName;
			dv.appendChild(aut);					

			act_rec_box.appendChild(dv);
		}

		var more = document.createElement('div');
		more.innerHTML = '<a href="mytodos.do">more...</a>';
		more.style.textAlign = 'right';
		more.style.paddingRight = '20px';
		act_rec_box.appendChild(more);
	}
}


var invreq;
function collectNewInvites(){
	invreq = new XMLHttpRequest();
	invreq.open('get','activeinvites.do',false);
	invreq.onreadystatechange = showActiveInvites;
	invreq.send();
}

function showActiveInvites(){
	if(invreq.readyState==4&&invreq.status==200){
		var resp = invreq.responseText;

		if(resp!='expired'){
			var res = JSON.parse(resp);

			showActiveRecords_(res,act_invites_recs);		
		}else{
			window.location = 'showsignin.do';
		}
	}
}

function showActiveRecords_(res,act_rec_box){
	if(res.length!=0){
		for(i=0;i<res.length;i++){
			var dv = document.createElement('div');
			dv.className = 'mytodorec_';
			//dv.todoid = res[i].todoListId;
			//dv.onclick = function(){ window.location = 'mytodos.do';}
			
			var img = document.createElement('img');
			img.className = 'img_';
			img.src = 'showpic.do?uid='+res[i].fromUser.userId+'&eml='+res[i].fromUser.email+'&picpath='+res[i].fromUser.profpic;
			dv.appendChild(img);					

			var ttl = document.createElement('div');
			ttl.className = 'ttl_';
			ttl.innerHTML = res[i].fromUser.userName;
			dv.appendChild(ttl);

			var crt = document.createElement('div');
			crt.className = 'crt_';
			crt.innerHTML = res[i].fromUser.created;
			dv.appendChild(crt);

			var aut = document.createElement('div');
			aut.className = 'aut_';
			aut.innerHTML = res[i].fromUser.email;
			dv.appendChild(aut);					

			act_rec_box.appendChild(dv);
		}

		var more = document.createElement('div');
		more.innerHTML = '<a href="connects.do">more...</a>';
		more.style.textAlign = 'right';
		more.style.paddingRight = '20px';
		act_rec_box.appendChild(more);
	}
}
