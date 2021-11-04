/**
 * 
 */
window.onload = function(){
	getAllTickets();
	
	//document.getElementById("manager").addEventListener("click",getAllTickets);//to retrieve all th etickets
	document.getElementById("retrieve").addEventListener("click",function(){getAllTickets()});
}

function getAllTickets() {
	let xhttp = new XMLHttpRequest();
	console.log("here");
	
	xhttp.onreadystatechange = function() {
		
		if (xhttp.readyState==4 && xhttp.status==200) {
			let requests = JSON.parse(xhttp.responseText);
			buildTable(requests);
			console.log(requests);
		}
	}
	
	xhttp.open("GET", "http://localhost:8080/ERSapplication/getAllTicketsManager.json");
	
	xhttp.send();

}

function buildTable(data){ 
let list = document.getElementById('ticketTable');     
      
     list.innerHTML = "";  
 
     let filter = document.getElementById("manager").value;
  
    for(let i = 0; i < data.length-1; i++){ 
	if(filter == 0|| data[i].statusId == filter){

               let row = `<ul scope = "row">

                <li>Reimbursement Id: ${data[i].reimbursementId}</li>
                <li>Amount requested: ${data[i].amount}</li>
                <li>Date of submission: ${new Date(data[i].submitted)}</li>
                <li>Date of Resolution: ${new Date(data[i].resolved)}</li>
                <li>Description/details: ${data[i].description}</li>
                <li>Status: ${data[i].statusId}</li>
</ul>`;
                list.innerHTML += row ;
}
}} 




