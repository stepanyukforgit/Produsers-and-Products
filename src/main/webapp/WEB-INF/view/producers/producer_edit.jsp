<%-- 
    Document   : ProducerEdit
    Created on : 03.08.2017, 19:25:06
    Author     : stepanyuk
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="stepanyuk.productsandproducers.model.Producer"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    <meta name="description" content="Producers and products">
    <meta name="author" content="Aleksey Stepanyuk">
    <link rel="icon" href="<spring:url value="/resources/picture/favicon.ico" />">
    <spring:url value="/resources/css/bootstrap.min.css" var="mainCss" />
    <spring:url value="/resources/css/dashboard.css" var="dashCss" />
    <spring:url value="/resources/js/bootstrap.min.js" var="mainJs" />
    <spring:url value="/resources/js/productsandproducers.js" var="prodJs" />
    
    <title>Producers and products</title>

    <!-- Bootstrap core CSS -->
    <link href="${mainCss}" rel="stylesheet" />

    <!-- Custom styles for this template -->
    <link href="${dashCss}" rel="stylesheet" />

  </head>

  <body>

    <nav class="navbar navbar-inverse navbar-fixed-top">
      <div class="container-fluid">
        <div class="navbar-header">
          <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
            <span class="sr-only">Producers and products</span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
          </button>
          <a class="navbar-brand" href="/">Producers and products</a>
        </div>
        <div id="navbar" class="navbar-collapse collapse">
          <form class="navbar-form navbar-right" action="/search_results">
            <input name="search" type="text" class="form-control" placeholder="Search..." data-toggle="tooltip" title="Search by Producer and Product name" data-placement="bottom">
          </form>
        </div>
      </div>
    </nav>

   <div class="container-fluid">
      <div class="row">
        <div class="col-sm-3 col-md-2 sidebar">
          <ul class="nav nav-sidebar">
            <li class="active"><a href="/">Main page <span class="sr-only">(current)</span></a></li>
          </ul>
          <ul class="nav nav-sidebar">
            <li><a href="/producers/producers_list">Producers</a></li>
          </ul>
          <ul class="nav nav-sidebar">
            <li><a href="/products/products_list">Products</a></li>
          </ul>
        </div>
        <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
          <form action="/producers/producer_info" method="POST">
            <div class="form-group">
              <label >Producer name</label>
              <input type="text" class="form-control" id="usr" name="producerName" value="${producer.name}">
            </div>
            <div class="form-group">
                <label>Address</label>
                <input type="text" class="form-control" id="usr" name="producerAddress" value="${producer.address}">
            </div>              
            <div class="form-group">
              <label>Description</label>
              <textarea class="form-control" rows="5" name="producerDescription">${producer.description}</textarea>
            </div>
            <button type="submit" class="btn btn-success btn-lg" name="producerId" value="${producer.id}">Save all changes</button>
          </form>
        </div>
    </div>
   </div>   
    <!-- Bootstrap core JavaScript================================================== -->
    <!-- Placed at the end of the document so the pages load faster -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
    <script src="${mainJs}"></script>
    <script src="${prodJs}"></script>
  </body>
</html>