//validation
function validateAndAdd(){
    var doSubmit = true;
    var prodName = document.getElementById("producerName");
    var prodAddr = document.getElementById("producerAddress");
    var prodDescr = document.getElementById("producerDescription");
    var form = document.getElementById("form-in-modal");
    
    if(!prodName.value || (prodName.value.length > 30)){        
        prodName.placeholder = "It's can't too big(more then 20 chars) or empty...";
        showError(prodName.parentElement);
        doSubmit = false;
    }else{
        resetError(prodName.parentElement);
    }
    
    if(prodAddr.value.length > 100){
        prodAddr.placeholder = "It's can't too big(more then 100 chars)...";
        showError(prodAddr.parentElement);
        doSubmit = false;
    }else{
        resetError(prodAddr.parentElement);
    }
    
    if(prodDescr.value.length > 200){
        prodDescr.placeholder = "It's can't too big(more then 200 chars)...";
        showError(prodDescr.parentElement);
        doSubmit = false;
    }else{
        resetError(prodDescr.parentElement);
    }
    
    form.onsubmit=(function(e){
        if(!doSubmit){
            e.preventDefault();
        }else{
            addNewProducer();
        }
    });        
}

function showError(container){
    container.setAttribute("class","form-group has-error");
}

function resetError(container){
    container.setAttribute("class","form-group");
}

//AJAX
function addNewProducer() {
    var json = JSON.stringify({
        name: document.getElementById("producerName").value,
        address: document.getElementById("producerAddress").value,
        description: document.getElementById("producerDescription").value
    });

    var request = new XMLHttpRequest();
    request.open("POST", "/producers/producers_list", false);
    request.setRequestHeader("Content-Type", "application/json");
    request.onreadystatechange = function() {
                                    if (request.readyState == 4) {
                                        if (request.status == 200) {
                                            parseMessage(request.responseText);
                                        }
                                    }    
                                };
    request.send(json);
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
    var producerLink = document.createElement("a");
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