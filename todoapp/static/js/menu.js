var lis,page;
function getAllMenuElements(){
	lis = document.getElementsByTagName('li');
	page = document.getElementById('page');
}

var links = new Array('dashborad.do','mytodos.do','connects.do');
function setAllMenuActions(){
	for(i=0;i<lis.length;i++){		
		lis[i].index = i;
		
		lis[i].onclick = function(){ window.location = links[this.index] };				
		
		if(i!=page.value){			
			lis[i].onmouseover = function(){ this.className = 'ulselect'; }
			lis[i].onmouseout = function(){ this.className = 'ulunselect'; }
		}else{
			lis[i].className = 'ulselect';
		}
	}
}