window.onload = initAll;

function initAll(){
	getAllElements();
	setAllActions();
}


var mobile,otp_btn,otp_box,otp,otp_sbm;
var btn,loader1,loader2;
function getAllElements(){
	mobile = document.getElementById("mobile");
	otp_btn = document.getElementById("otp_btn");
	otp_box = document.getElementById("otp_box");
	otp = document.getElementById("otp");
	otp_sbm = document.getElementById("otp_sbm");
	btn = document.getElementById("btn");
	loader1 = document.getElementById("loader1");
	loader2 = document.getElementById("loader2");
}

function setAllActions(){
	otp_btn.onclick = sendOtp;
	//otp_sbm.onclick = mobileVerification;
}

var req2;
function mobileVerification(){
	otp_sbm.style.display = 'none';
	loader2.style.display = 'inline';

	req2 = new XMLHttpRequest();
	
	req2.open('get','checkotp.do?otp='+otp.value,true);
	req2.onreadystatechange = checkOTPResp;
	req2.send();
}

function checkOTPResp(){
	if(req2.readyState==4&&req2.status==200){
		if(req2.responseText=='true'){
			btn.disabled = false;
			otp_box.style.display = 'none';
			alert('OTP Matched!! Now press SignUp Button...')
		}else{
			otp.style.border = '1px solid red';
			otp.onfocus = function(){ otp.value = '';};
			otp.onkeyup = function(){ 
										if(otp.value.length==6){
											otp.style.border = '1px solid #d6d5d3';
										}
									};
			otp_sbm.style.display = 'inline';
			loader2.style.display = 'none';
			alert('Please Re-enter the OTP...')
		}
	}
}

var req1;
function sendOtp(){	
	otp_btn.style.display = 'none';
	loader1.style.display = 'inline';
	
	req1 = new XMLHttpRequest();
	
	req1.open('get','sendotp.do?mobile='+mobile.value,true);
	req1.onreadystatechange = otpStep2;
	//req1.send();
}

function  otpStep2(){
	if(req1.readyState==4&&req1.status==200){
		//alert(req1.responseText);

		if(req1.responseText=='done'){
			otp_box.style.visibility = 'visible';
			loader1.style.display = 'none';
		}
	}
}