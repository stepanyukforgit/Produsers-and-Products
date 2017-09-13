function showUpload(uploadInp){

    document.getElementById("upload-label-info").innerHTML = uploadInp.files[0].name;
    
    var uploadBtn = document.getElementById("upload-btn");
    
    if(uploadBtn == null){
        uploadBtn = document.createElement("button");
        uploadBtn.setAttribute("class", "btn btn-success");
        uploadBtn.setAttribute("id", "upload-btn");
        uploadBtn.setAttribute("type", "button");
        uploadBtn.setAttribute("onclick", "uploadLabel()");
        uploadBtn.innerHTML = "Upload <span class=\"glyphicon glyphicon-upload\"></span>";

        var uploadDiv = document.getElementById("upload-div");
        uploadDiv.appendChild(uploadBtn);
    }
    uploadBtn.innerHTML = "Upload <span class=\"glyphicon glyphicon-upload\"></span>";
}

function uploadLabel(){
    
    var formData = new FormData();
    
    formData.append("logo",document.getElementById("upload-label-input").files[0]);
//    var producerId = new Blob([document.getElementsByName("producerId")[0].value],{type: "text/plain"});
//
//    formData.append("producerId", producerId);
    
    var request = new XMLHttpRequest();
    
    request.open("POST","/producers/producer_edit/upload_logo");
    request.onreadystatechange = 
            function(){
                if ((request.readyState == 4)&&(request.status == 200)) {
                        showMessage(request.responseText);
                }    
            };
    request.send(formData);
}

function showMessage(response){
    if(!response == ""){
        
        var uploadBtn = document.getElementById("upload-btn");
        uploadBtn.innerHTML = "Upload <span class=\"glyphicon glyphicon-ok\"></span>";

        var hiddenInp = document.getElementsByName("producerLogo")[0];        
        var logo = document.getElementById("logo");
        var logoSrc = logo.getAttribute("src");
        var newSrc = logoSrc.substring(0,(logoSrc.indexOf("upload")+7))+response;
        logo.setAttribute("src",newSrc);
        hiddenInp.value = newSrc;
    }else{
        logo.setAttribute("src","/resources/upload/noimg.jpg");
    }    
}

