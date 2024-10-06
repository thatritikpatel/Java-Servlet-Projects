const maincontainer = document.querySelector("#maincontainer");
const addcontainer = document.querySelector("#addcontainer");
const allcontainer = document.querySelector("#allcontainer");
const add = document.getElementById("add");
const all = document.getElementById("all");
const checkst = document.getElementById("studentsarehere");



add.addEventListener("click", e => {
    addcontainer.style.display = 'block';
    allcontainer.style.display = 'none';
});


all.addEventListener("click", e => {
    allcontainer.style.display = 'block';
    addcontainer.style.display = 'none';
});


var tooltipTriggerList = [].slice.call(document.querySelectorAll('[data-bs-toggle="tooltip"]'))
var tooltipList = tooltipTriggerList.map(function (tooltipTriggerEl) {
  return new bootstrap.Tooltip(tooltipTriggerEl)
})