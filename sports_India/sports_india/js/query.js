const queryBtn = document.querySelector('#queryBtn');
const queryname = document.querySelector('#queryname');
const queryemail = document.querySelector('#queryemail');
const queryphone = document.querySelector('#queryphone');
const queryMessage = document.querySelector('#querymessage');

// const  = document.querySelector('#');


let request = null;
queryBtn.addEventListener('click',()=>{
	alert("Data Submiting");
	request = new XMLHttpRequest();
	
	
	request.open('post',"addquery.do?uname="+queryname.value+'&email='+queryemail.value+'&phone='+queryphone.value+'&message='+querymessage.value,true);
	request.onreadystatechange = signupResponse;
	request.send();
});

function signupResponse(){
	if(request.readyState==4&&request.status==200){
		alert("Data Submited");
		resetForm();
		window.location = "http://localhost:8080/sports_india_v1/";

	}
}

function resetForm(){
	queryMessage.value = '';
	queryemail.value = '';
	queryphone.value = '';
	queryname.value = '';
}