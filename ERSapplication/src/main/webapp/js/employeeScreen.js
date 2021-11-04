
console.log("in the employee screen");
window.onload = function(){
	getSessUser();
	//getAllTickets();
	document.getElementById("retrieve").addEventListener("click",getAllTickets);
	
	
}

function getSessUser(){
	let xhttp = new XMLHttpRequest();
	xhttp.onreadystatechange = function(){
		
		
		if(xhttp.readyState == 4 && xhttp.status==200){
			let user = JSON.parse(xhttp.responseText);
			console.log(user);
			//document.getElementById("welcomeHeader").innerText=`Welcome ${user.name}`;
		}
	}
	
	xhttp.open("GET", "http://localhost:8080/ERSapplication/getSessionUser.json");

	xhttp.send();
}
function getAllTickets() {
	let xhttp = new XMLHttpRequest();
	
	xhttp.onreadystatechange = function() {
		if (xhttp.readyState==4 && xhttp.status==200) {
			let requests = JSON.parse(xhttp.responseText);
			buildTable(requests);
			console.log(requests);
		}
	}
	
	xhttp.open("GET", "http://localhost:8080/ERSapplication/getAllTickets.json");
	
	xhttp.send();


}

function buildTable(data){    
 let list = document.getElementById('ticketTable');         
     list.innerHTML = "";     
    for(let i = 0; i < data.length-1; i++){ 

               let row = `<ul scope = "row">

                <li>Reimbursement Id: ${data[i].reimbursementId}</li>
                <li>Amount requested: ${data[i].amount}</li>
                <li>Date of submission: ${new Date(data[i].submitted)}</li>
                <li>Date of Resolution: ${new Date(data[i].resolved)}</li>
                <li>Description/details: ${data[i].description}</li>
                <li>Status Id: ${data[i].statusId}</li>
</ul>`;
                list.innerHTML += row ;
}}
