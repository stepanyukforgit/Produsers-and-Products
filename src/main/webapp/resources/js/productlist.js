//validation
function validateAndAdd(){
    var doSubmit = true;
    var prodName = document.getElementById("productName");
    var prodDescr = document.getElementById("productDescription");
    var form = document.getElementById("form-in-modal");
    
    if(!prodName.value || (prodName.value.length > 20)){        
        showError(prodName.parentElement);
        doSubmit = false;
    }else{
        resetError(prodName.parentElement);
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
        //find out
            $("#add-product-modal").modal("hide");
            addNewProduct();
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
function addNewProduct() {
    var json = JSON.stringify({           
        name: document.getElementById("productName").value,
        price: document.getElementById("productPrice").value,
        description: document.getElementById("productDescription").value,
        producerId: document.getElementById("producerId").value
    });

    var request = new XMLHttpRequest();
    request.open("POST", "/products/products_list", true);
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
    if (responseText != null) {
        var product = JSON.parse(responseText);
        appendProduct(product);
    }
}

function appendProduct(product){
    var productTable = document.getElementById("product-table");
    
    var newRow = document.createElement("tr");
    newRow.setAttribute("align","left");
    
    var cell1 = document.createElement("td");
    cell1.setAttribute("width","3%");
    cell1.innerHTML = productTable.rows.length;
    
    var cell2 = document.createElement("td");
    cell2.setAttribute("width","17%");
    var productLink = document.createElement("a");
    productLink.setAttribute("href","/products/product_info/"+product.id);
    productLink.innerHTML = product.name;
    cell2.appendChild(productLink);
    
    var cell3 = document.createElement("td");
    cell3.setAttribute("width","10%");
    cell3.innerHTML = product.price;
    
    var cell4 = document.createElement("td");
    cell4.setAttribute("width","50%");
    cell4.innerHTML = product.description;
    
    var cell5 = document.createElement("td");
    cell5.setAttribute("width","20%");
    cell5.innerHTML = product.producerName;
    
    newRow.appendChild(cell1);
    newRow.appendChild(cell2);
    newRow.appendChild(cell3);
    newRow.appendChild(cell4);
    newRow.appendChild(cell5);
    
    productTable.appendChild(newRow);
}