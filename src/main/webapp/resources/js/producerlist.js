var request;

function addNewProducer() {
    var json = JSON.stringify({
        name: document.getElementById("producerName").value,
        address: document.getElementById("producerAddress").value,
        description: document.getElementById("producerDescription").value
    });

    request = new XMLHttpRequest();
    request.open("POST", "/producers/producers_list", false);
    request.setRequestHeader("Content-Type", "application/json");
    request.onreadystatechange = callback;
    request.send(json);
}

function callback() {
    if (request.readyState == 4) {
        if (request.status == 200) {
            parseMessage(request.responseText);
        }
    }
}

function parseMessage(responseXML) {
    if (responseXML == null) {
        return false;
    } else {
        var producer = JSON.parse(responseText);
        appendProducer(producer);
    }
}

function appendProducer(producer){
    var producerTable = document.getElementById("producer-table");
    
    var newRow = document.createElement("tr");
    newRow.setAttribute("align","left");
    
    var cell1 = document.createElement("td");
    cell1.setAttribute("width","3%");
    cell1.innerHTML = producerTable.rows.length;
    
    var cell2 = document.createElement("td");
    cell2.setAttribute("width","17%");
    var producerLink = document.createAttribute("a");
    producerLink.setAttribute("href","/producers/producer_info/"+producer.id);
    producerLink.innerHTML = producer.name;
    cell2.appendChild(producerLink);
    
    var cell3 = document.createElement("td");
    cell3.setAttribute("width","30%");
    cell3.innerHTML = producer.address;
    
    var cell4 = document.createElement("td");
    cell3.setAttribute("width","50%");
    cell3.innerHTML = producer.description;
    
    newRow.appendChild(cell1);
    newRow.appendChild(cell2);
    newRow.appendChild(cell3);
    newRow.appendChild(cell4);
    
    producerTable.appendChild(newRow);
}