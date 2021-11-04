/**
 * 
 */
window.onload = function(){
	getSessUser();
}
function getSessUser(){
	let xhttp = new XMLHttpRequest();
	xhttp.onreadystatechange = function(){
		
		
		if(xhttp.readyState == 4 && xhttp.status==200){
			let user = JSON.parse(xhttp.responseText);
			console.log(user);
			/*document.getElementById("welcomeHeader").innerText=`Welcome ${user.name}`;*/
		}
	}
	
	xhttp.open("GET", "http://localhost:8080/ERSapplication/getSessionUser.json");

	xhttp.send();
}/**
 * 
 */