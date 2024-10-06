const btn = document.querySelector("#lgbtn");
const uname = document.querySelector("#lgname");
const password = document.querySelector("#lgpass");


btn.addEventListener("click",() => {
    if (uname.value==="admin"&&password.value==="1234"){
        alert('Login Sucessful');
        window.location = "admin.jsp"
    }else{
        alert('Login Failed');
        alert('Invalid Username and Password')
        alert('try again')
        alert('admin 1234')
    }
})
