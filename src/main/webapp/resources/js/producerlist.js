//validation
function validateAndAdd(){
    var doSubmit = true;
    var prodName = document.getElementById("producerName");
    var prodAddr = document.getElementById("producerAddress");
    var prodDescr = document.getElementById("producerDescription");
    var form = document.getElementById("form-in-modal");
    
    if(!prodName.value || (prodName.value.length > 30)){        
        showError(prodName.parentElement);
        doSubmit = false;
    }else{
        resetError(prodName.parentElement);
    }
    
    if(prodAddr.value.length > 100){
        showError(prodAddr.parentElement);
        doSubmit = false;
    }else{
        resetError(prodAddr.parentElement);
    }
    
    if(prodDescr.value.length > 200){
        showError(prodDescr.parentElement);
        doSubmit = false;
    }else{
        resetError(prodDescr.parentElement);
    }
    
    form.onsubmit=(function(e){
        if(!doSubmit){
            e.preventDefault();
        }else{
            e.preventDefault();

            $("#add-producer-modal").modal("hide");
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
    request.open("POST", "/producers/producers_list", true);
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

function parseMessage(responseText) {
    if (responseText == null) {
        return false;
    } else {
        var producer = JSON.parse(responseText);
        appendProducer(producer);
    }
}

function appendProducer(producer){
    var producerTableBody = document.getElementById("producer-table-body");
    
    var newRow = document.createElement("tr");
    newRow.setAttribute("align","left");
    
    var cell1 = document.createElement("td");
    cell1.setAttribute("width","3%");
    cell1.innerHTML = document.getElementById("producer-table").rows.length;
    
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
    cell4.setAttribute("width","50%");
    cell4.innerHTML = producer.description;
    
    newRow.appendChild(cell1);
    newRow.appendChild(cell2);
    newRow.appendChild(cell3);
    newRow.appendChild(cell4);
    
    producerTableBody.appendChild(newRow);
}

function newFieldsForProduct(){   
    var formGroupProduct = document.getElementById("product-div");
    
    var productNameDiv = document.createElement("div");
    productNameDiv.setAttribute("class", "form-group");
    var productNameLab = document.createElement("label");
    productNameLab.innerHTML = "Product name:";
    var productNameInp = document.createElement("input");
    productNameInp.setAttribute("type","text");
    productNameInp.setAttribute("class","form-control");
    productNameInp.setAttribute("maxlength","20");
    productNameInp.setAttribute("data-toggle","tooltip");
    productNameInp.setAttribute("title","It's can't too big(more then 20 chars) or empty...");
    productNameInp.setAttribute("data-placement","right");
    productNameInp.required = true;
    productNameDiv.appendChild(productNameLab);
    productNameDiv.appendChild(productNameInp);
    
    var productPriceDiv = document.createElement("div");
    productPriceDiv.setAttribute("class", "form-group");
    var productPriceLab = document.createElement("label");
    productPriceLab.innerHTML = "Price:";
    var productPriceInp = document.createElement("input");
    productPriceInp.setAttribute("class","form-control");
    productPriceInp.setAttribute("type","number");
    productPriceInp.setAttribute("step","0.01");
    productPriceInp.setAttribute("min","0");
    productPriceInp.setAttribute("max","1000000");
    productPriceInp.required = true;
    productPriceDiv.appendChild(productPriceLab);
    productPriceDiv.appendChild(productPriceInp);
    
    var productDescrDiv = document.createElement("div");
    productDescrDiv.setAttribute("class", "form-group");
    var productDescrLab = document.createElement("label");
    productDescrLab.innerHTML = "Description:";
    var productDescrTexrAr = document.createElement("textarea");
    productDescrTexrAr.setAttribute("class","form-control");
    productDescrTexrAr.setAttribute("rows","3");
    productDescrTexrAr.setAttribute("maxlength","200");
    productDescrTexrAr.setAttribute("data-toggle","tooltip");
    productDescrTexrAr.setAttribute("title","It's can't too big(more then 200 chars)...");
    productDescrTexrAr.setAttribute("data-placement","right");
    productDescrDiv.appendChild(productDescrLab);
    productDescrDiv.appendChild(productDescrTexrAr);
    
    var productRemoveDiv = document.createElement("div");
    productRemoveDiv.setAttribute("class", "form-group");
    var productRemoveBtn = document.createElement("button");
    productRemoveBtn.setAttribute("type", "button");
    productRemoveBtn.setAttribute("class","btn btn-default btn-sm");
    productRemoveBtn.setAttribute("onclick", "cancelProductAdding(this)");
    productRemoveBtn.innerHTML = "<span class=\"glyphicon glyphicon-minus\"></span>"+"Cancel product edding";
    productRemoveDiv.appendChild(productRemoveBtn);   
    
    formGroupProduct.insertBefore(productDescrDiv, formGroupProduct.firstChild);
    formGroupProduct.insertBefore(productPriceDiv, formGroupProduct.firstChild);
    formGroupProduct.insertBefore(productNameDiv, formGroupProduct.firstChild);
    formGroupProduct.insertBefore(productRemoveDiv, formGroupProduct.firstChild);
    formGroupProduct.insertBefore(document.createElement("hr"), formGroupProduct.firstChild);
}

function cancelProductAdding(cancelBtn){
    var productCancelDiv = cancelBtn.parentElement;
    var hr = productCancelDiv.previousElementSibling;
    var productNameDiv = productCancelDiv.nextElementSibling;
    var productPriceDiv = productNameDiv.nextElementSibling;
    var productDescrDiv = productPriceDiv.nextElementSibling;
    
    hr.remove();
    productCancelDiv.remove();
    productNameDiv.remove();
    productPriceDiv.remove();
    productDescrDiv.remove();
}