var request;

function addNewProduct() {
    var json = JSON.stringify({
        name: document.getElementById("productName").value,
        price: document.getElementById("productPrice").value,
        description: document.getElementById("productDescription").value,
        producerId: document.getElementById("producerId").value
    });

    request = new XMLHttpRequest();
    request.open("POST", "/products/products_list", false);
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
    var productLink = document.createAttribute("a");
    productLink.setAttribute("href","/products/product_info/"+product.id);
    productLink.innerHTML = product.name;
    cell2.appendChild(productLink);
    
    var cell3 = document.createElement("td");
    cell3.setAttribute("width","10%");
    cell3.innerHTML = product.price;
    
    var cell4 = document.createElement("td");
    cell3.setAttribute("width","50%");
    cell3.innerHTML = product.description;
    
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