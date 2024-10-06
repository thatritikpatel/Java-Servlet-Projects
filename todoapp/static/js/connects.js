
var connectActions,arrows,recs,srch_bar,userid;
var srch_fld,rec_box;
var acceptedStatusId=7,rejectedStatusId=8;
function getAllElements(){
	getAllMenuElements();

	connectActions = document.getElementsByClassName('con_act');
	arrows = document.getElementsByClassName('arw');
	recs = document.getElementById('recs');
	srch_bar = document.getElementById('srch_bar');	
	userid = document.getElementById('userid');	


	srch_fld = document.getElementById('srch_fld');	
	rec_box = document.getElementById('rec_box');
}

function clearMenu(){	
	for(i=0;i<3;i++){
		connectActions[i].className = 'con_act';
		connectActions[i].active = false;
		arrows[i].active = false;
		arrows[i].style.visibility = 'hidden';
	}	
}

function setMenu(menu,index){
	clearMenu();
	menu.className = 'con_act con_act_ov';
	arrows[index].active = true;
	arrows[index].style.visibility = 'visible';
	connectActions[index].active = true;
}

var arr = new Array('connects.do','#','newinvites.do');
function setConnectMenu(){
	for(i=0;i<connectActions.length;i++){
		connectActions[i].index = i;
		
		if(i==0){
			connectActions[i].active = true;
			arrows[i].active = true;
		}else{
			connectActions[i].active = false;
			arrows[i].active = false;
		}
		
		connectActions[i].onmouseover = function(){
											if(this.active!=true)
												this.className = 'con_act con_act_ov';

											for(i=0;i<arrows.length;i++){
												if(!arrows[i].active)
													arrows[i].style.visibility = 'hidden';
											}

											arrows[this.index].style.visibility = 'visible';
										};

		connectActions[i].onmouseout  = function(){
											if(this.active!=true)
												this.className = 'con_act con_act_ot';

											for(i=0;i<arrows.length;i++){
												if(!arrows[i].active)
													arrows[i].style.visibility = 'hidden';
											}
										};
		
		if(i==0){
			connectActions[0].onclick = function(){
											window.location = arr[0];			
										};	
		}else if(i==2){
			connectActions[2].onclick = collectNewInvites; 
		}
	}
}

var nwinvreq;
function collectNewInvites(){	
	setMenu(inv_con,2);

	nwinvreq = new XMLHttpRequest();
	nwinvreq.open('get',arr[2],false);
	nwinvreq.onreadystatechange = showNewInvites;
	nwinvreq.send();
}

function showNewInvites(){
	if(nwinvreq.readyState==4&&nwinvreq.status==200){
		var res = nwinvreq.responseText;
		//alert(res)
		if(res!='expired'){
			var resp = JSON.parse(res);
			
			var user;
			var invitedByMe;			
			resetRecordBox();
			for(i=0;i<resp.length;i++){
				if(resp[i].fromUser.userId==userid.value){
					invitedByMe = true;
					_showSingleRecord_(resp[i].connectId,resp[i].toUser,invitedByMe);					
				}else{
					invitedByMe = false;
					if(resp[i].status.statusId!=8){
						_showSingleRecord_(resp[i].connectId,resp[i].fromUser,invitedByMe);					
					}
				}
			}
		}else{
			window.location = 'showsignin.do';
		}
	}	
}


//---------------------------------------------------
function _showSingleRecord_(conid,obj,invitedByMe){
	//alert(obj.userName)

	var dv = document.createElement('div');
	dv.style.top = top_val+'px';
	count++;
	if(count%2==0){
		top_val += 140;
		dv.className = 'rec_ rec_rht';
	}else{
		dv.className = 'rec_ rec_lft';
	}	

	//dv.style.width = '60%'; 
	
	var pic = document.createElement('img');
	pic.className = 'otherpic';
	
	var nam = document.createElement('span');
	nam.className = 'othernam';

	var crt = document.createElement('span');
	crt.className = 'othercrt';

	var cnt = document.createElement('span');
	cnt.className = 'othercnt';

	var eml = document.createElement('span');
	eml.className = 'othereml';

	var mob = document.createElement('span');
	mob.className = 'othermob';

	var ares = document.createElement('span');	
	ares.className = 'otherares';
	

	var stts = document.createElement('span');
	stts.className = 'otherstts_';

	var whom = document.createElement('span');	
	
	
	
	//Cancel Button
	if(invitedByMe){
		var btn = document.createElement('span');
		btn.stts = stts;
		btn.className = 'otherbtn_';
		btn.conid = conid;
		btn.ares = ares;
		btn.onclick = cancelInvite;
		btn.innerHTML = 'Cancel';
		dv.appendChild(btn);
		whom.innerHTML = '(invited by me)';	
	}else{
		var btn1 = document.createElement('span');
		var btn2 = document.createElement('span');

		btn1.conid = btn2.conid = conid;
		
		btn1.statusChangeId = acceptedStatusId;
		btn2.statusChangeId = rejectedStatusId;
		
		btn1.className = 'otherbtn1';
		btn2.className = 'otherbtn2';
		
		btn1.innerHTML = 'Accept';
		btn2.innerHTML = 'Reject';
		
		btn1.onclick = btn2.onclick = invitationAction;
		
		dv.appendChild(btn1);
		dv.appendChild(btn2);

		whom.innerHTML = '(invited me)';	
	}	
	

	pic.src = 'otheruserpic.do?email='+obj.email+'&profpic='+obj.profpic;
	nam.innerHTML = obj.userName;
	nam.appendChild(whom);
	whom.className = 'otherwhom';

	crt.innerHTML = 'Joined: '+obj.created;
	cnt.innerHTML = 'Country: '+obj.country.country;
	eml.innerHTML = 'Email: '+obj.email;	
	mob.innerHTML = 'Mobile: '+obj.mobile;	
	stts.innerHTML = 'Pending';
	
	//ares.innerHTML = 'Invitation Sent Successfully...';
		
	dv.appendChild(pic);	
	dv.appendChild(nam);	
	dv.appendChild(crt);	
	dv.appendChild(cnt);	
	dv.appendChild(eml);	
	dv.appendChild(mob);	
	dv.appendChild(stts);	
	dv.appendChild(ares);
	
	
	recs.appendChild(dv);	
}


var acinvreq;
function invitationAction(){
	acinvreq = new XMLHttpRequest();
	acinvreq.open('get','invitationaction.do?conid='+this.conid+'&sttsid='+this.statusChangeId,false);
	acinvreq.onreadystatechange = afterInvitationAccepted;
	acinvreq.send();
}

function afterInvitationAccepted(){
	if(acinvreq.readyState==4&&acinvreq.status==200){
		if(acinvreq.responseText=='done'){
			collectNewInvites();
		}
	}
}
//---------------------------------------------------


function setAllActions(){
	setAllMenuActions();

	setConnectMenu();

	getAllConnects();

	srch_fld.onkeyup = searchUsers;
}

var srchreq; 
function searchUsers(){
	var len = srch_fld.value.trim().length;
	
	if(len>2){
		srchreq = new XMLHttpRequest();
		srchreq.open('get','search.do?srch_key='+srch_fld.value,false);
		srchreq.onreadystatechange = showSearchResult;
		srchreq.send();
	}
}

function showSearchResult(){
	if(srchreq.readyState==4&&srchreq.status==200){
		//alert(srchreq.responseText);
		if(srchreq.responseText!='expired'){
			rec_box.style.visibility = 'visible';
			rec_box.innerHTML = '';
			
			var resp = JSON.parse(srchreq.responseText);
		
			for(i=0;i<resp.length;i++){
				var dv = document.createElement('div');
				dv.className = 'user_rec_';
				//alert(resp[i].userName);
				dv.innerHTML = resp[i].userName;
				dv.userid = resp[i].userId;

				dv.onmouseover = function(){
									this.className = 'user_rec_ user_rec_ov';
				                 };

				dv.onmouseout = function(){
									this.className = 'user_rec_ user_rec_ot';
				                 };

				dv.onclick = getUserInfo;

				rec_box.appendChild(dv); 
			}		
		}else{ 
			window.location = 'showsignin.do';
		}
	}
}

var uinforeq;
function getUserInfo(){
	rec_box.style.visibility = 'hidden';
		
	setMenu(new_con,1);

	uinforeq = new XMLHttpRequest();
	uinforeq.userid = this.userid;
	uinforeq.open('get','userinfo.do?userid='+this.userid,false);
	uinforeq.onreadystatechange = showUserInfo;
	uinforeq.send();
}

function showUserInfo(){
	if(uinforeq.readyState==4&&uinforeq.status==200){
		var resp = uinforeq.responseText;
		//alert(resp+" ---###--")		
		
		if(resp!='norecs'){
			var resp_ = JSON.parse(resp);
			resetRecordBox();
			if(resp_.status.statusId==7){
				showSingleRecord(resp_);
			}else{				
				if(resp_.fromUser.userId==userid.value){	
					var invitedByMe = true;
					_showSingleRecord_(resp_.connectId,resp_.toUser,invitedByMe);
				}else{
					var invitedByMe = false;
					_showSingleRecord_(resp_.connectId,resp_.fromUser,invitedByMe);
				}
			}
		}else{
			userInfoIfNoConnect(uinforeq.userid);
		}
	}
}

var req2;
function userInfoIfNoConnect(uid){
	req2 = new XMLHttpRequest();
	req2.open('get','userinfoifnoconnect.do?userid='+uid,false);
	req2.onreadystatechange = showNoConnectUserInfo;
	req2.send();
}

function showNoConnectUserInfo(){
	if(req2.readyState==4&&req2.status==200){
		var res = req2.responseText;
		
		if(res!='expired'){
			var resp = JSON.parse(res);
			resetRecordBox();
			
			count = 0;
			top_val = 20;
			showSingleRecord_(resp);			
		}else{
		
		}
	}
}



function showSingleRecord_(obj){
	//alert(obj.userName)

	var dv = document.createElement('div');
	dv.style.top = top_val+'px';
	count++;
	if(count%2==0){
		top_val += 140;
		dv.className = 'rec_ rec_rht';
	}else{
		dv.className = 'rec_ rec_lft';
	}	

	dv.style.width = '60%'; 
	
	var pic = document.createElement('img');
	pic.className = 'otherpic';
	
	var nam = document.createElement('span');
	nam.className = 'othernam';

	var crt = document.createElement('span');
	crt.className = 'othercrt';

	var cnt = document.createElement('span');
	cnt.className = 'othercnt';

	var eml = document.createElement('span');
	eml.className = 'othereml';

	var mob = document.createElement('span');
	mob.className = 'othermob';

	var ares = document.createElement('span');	
	ares.className = 'otherares';
	

	var stts = document.createElement('span');
	stts.className = 'otherstts';

	var btn = document.createElement('span');
	btn.stts = stts;
	btn.className = 'otherbtn';
	btn.userid = obj.userId;
	btn.ares = ares;
	btn.onclick = inviteUser;
	
	/*var who;
	if(userid.value==obj.fromUser.userId){		
		who = obj.toUser;		
	}else{
		who = obj.fromUser;		
	}*/

	pic.src = 'otheruserpic.do?email='+obj.email+'&profpic='+obj.profpic;
	nam.innerHTML = obj.userName;
	crt.innerHTML = 'Joined: '+obj.created;
	cnt.innerHTML = 'Country: '+obj.country.country;
	eml.innerHTML = 'Email: '+obj.email;	
	mob.innerHTML = 'Mobile: '+obj.mobile;	
	stts.innerHTML = 'Not Yet Connected';
	btn.innerHTML = 'Invite';
	ares.innerHTML = 'Invitation Sent Successfully...';
		
	dv.appendChild(pic);	
	dv.appendChild(nam);	
	dv.appendChild(crt);	
	dv.appendChild(cnt);	
	dv.appendChild(eml);	
	dv.appendChild(mob);	
	dv.appendChild(stts);	
	dv.appendChild(btn);
	dv.appendChild(ares);
	
	
	recs.appendChild(dv);	
}


var invreq;
function inviteUser(){
	invreq = new XMLHttpRequest();
	
	invreq.btn = this;

	invreq.open('get','inviteuser.do?userid='+this.userid,false);
	invreq.onreadystatechange = afterInvite;
	invreq.send();
}

function afterInvite(){
	if(invreq.readyState==4&&invreq.status==200){
		var res = invreq.responseText;

		if(res!='expired'){			
			var obj = JSON.parse(res);
			var btn = invreq.btn;
			var ares = btn.ares;
			ares.style.display = 'inline-block';
			setTimeout(function(){ares.style.display = 'none';},5000);
			var stts = btn.stts;
			stts.innerHTML = 'Pending';
			btn.innerHTML = 'Cancel';
			btn.conid = obj.conid;
			btn.onclick = cancelInvite;
		}else{
			window.location = 'showsignin.do';
		}
	}
}

var canreq;
function cancelInvite(){
	canreq= new XMLHttpRequest();
	canreq.btn = this;
	canreq.open('get','cancelinvite.do?conid='+this.conid,false);
	canreq.onreadystatechange = afterCancelInvite;
	canreq.send();
}

function afterCancelInvite(){
	if(canreq.readyState==4&&canreq.status==200){
		if(canreq.responseText!='expired'){
			//var dv = canreq.btn.parentNode;
			recs.innerHTML = '<div id="can_">Connection Request Cancelled...</div>';
		}else{
			window.location = 'showsignin.do';
		}
	}
}

var conreq;
function getAllConnects(){
	conreq = new XMLHttpRequest();
	conreq.open('get','allconnections.do',true);
	conreq.onreadystatechange = showConnects;
	conreq.send();
}

function showConnects(){
	if(conreq.readyState==4&&conreq.status==200){
		var resp = conreq.responseText;

		if(resp!='expired'){
			//alert(resp)
			resetRecordBox();

			var resp_ = JSON.parse(resp);
			if(resp_.length!=0){
				for(i=0;i<resp_.length;i++){
					showSingleRecord(resp_[i]);	
				}
			}else{
				
			}
		}else{
			window.location = 'showsignin.do';
		}
	}
}

function resetRecordBox(){
	recs.innerHTML = '';
	top_val = 20;
	count = 0;
}


var top_val = 20;
var count = 0;
function showSingleRecord(obj){
	var dv = document.createElement('div');
	dv.style.top = top_val+'px';
	count++;
	if(count%2==0){
		top_val += 140;
		dv.className = 'rec_ rec_rht';
	}else{
		dv.className = 'rec_ rec_lft';
	}	
	
	var pic = document.createElement('img');
	pic.className = 'otherpic';
	
	var nam = document.createElement('span');
	nam.className = 'othernam';

	var crt = document.createElement('span');
	crt.className = 'othercrt';

	var cnt = document.createElement('span');
	cnt.className = 'othercnt';

	var eml = document.createElement('span');
	eml.className = 'othereml';

	var mob = document.createElement('span');
	mob.className = 'othermob';

	var btn = document.createElement('span');
	btn.className = 'red_btn';
	btn.innerHTML = 'Disconnect';
	btn.obj = obj;
	
	var who;
	if(userid.value==obj.fromUser.userId){		
		who = obj.toUser;		
	}else{
		who = obj.fromUser;		
	}

	pic.src = 'otheruserpic.do?email='+who.email+'&profpic='+who.profpic;
	nam.innerHTML = who.userName;
	crt.innerHTML = 'Joined: '+who.created;
	cnt.innerHTML = 'Country: '+who.country.country;
	eml.innerHTML = 'Email: '+who.email;	
	mob.innerHTML = 'Mobile: '+who.mobile;	
		
	dv.appendChild(pic);	
	dv.appendChild(nam);	
	dv.appendChild(crt);	
	dv.appendChild(cnt);	
	dv.appendChild(eml);	
	dv.appendChild(mob);	
	dv.appendChild(btn);	
	
	btn.onclick = function(){
					var res = confirm('Are you sure you want to disconnect...!');
					if(res){
						deleteConnection(this.obj.connectId);
					}
				  };
	
	recs.appendChild(dv);	
}

var relReq;
function deleteConnection(conid){
	relReq = new XMLHttpRequest();
	relReq.open('get','delconnection.do?conid='+conid,false);
	relReq.onreadystatechange = afterDelete;
	relReq.send();
}

function afterDelete(){
	if(relReq.readyState==4&&relReq.status==200){
		var res = relReq.responseText;
		
		if(res=='done'){
			var dv = document.createElement('div');
			dv.className = 'msgbox';
			dv.innerHTML = 'Disconnection Successfull...';

			recs.innerHTML = '';
			recs.appendChild(dv);
		}else{
			window.location = 'showsignin.do';
		}
	}
}