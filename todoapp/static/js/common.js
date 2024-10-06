window.onload = initAll;

var prof_ico;
function initAll(){
	getAllElements();
	setAllActions();

	prof_ico = document.getElementById('prof_ico');
	prof_ico.onclick = function(){
						window.location = 'profile.do';
					   };
}