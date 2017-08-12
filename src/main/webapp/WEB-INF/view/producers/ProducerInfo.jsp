<%-- 
    Document   : ProducerInfo
    Created on : 03.08.2017, 19:25:29
    Author     : stepanyuk
--%>

<%@page import="stepanyuk.productsandproducers.model.Producer"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
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
          <a class="navbar-brand" href="../">Producers and products</a>
        </div>
        <div id="navbar" class="navbar-collapse collapse">
          <ul class="nav navbar-nav navbar-right">
            <li><a href="#">Dashboard</a></li>
            <li><a href="#">Settings</a></li>
            <li><a href="#">Help</a></li>
          </ul>
          <form class="navbar-form navbar-right">
            <input type="text" class="form-control" placeholder="Search...">
          </form>
        </div>
      </div>
    </nav>

   <div class="container-fluid">
    <div class="row">
      <div class="col-sm-3 col-md-2 sidebar">
        <ul class="nav nav-sidebar">
          <li class="active"><a href="../">Main page <span class="sr-only">(current)</span></a></li>
        </ul>
        <ul class="nav nav-sidebar">
          <li><a href="ProducersList">Producers</a></li>
        </ul>
        <ul class="nav nav-sidebar">
          <li><a href="../products/ProductsList">Products</a></li>
        </ul>
      </div>
    <% Producer producer = (Producer) request.getAttribute("producerId");%>
      <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
      <h1 class="page-header"><%= producer.getName() %></h1>
      <h4><%= producer.getAddress() %></h4>
      <h5><%= producer.getDescription() %></h5>
      <h3><a href="ProducerProducts?producerId=<%= producer.getId() %>">see all products of the producer</a></h3>
         <div class="row">
             <div class="col-md-1">
            <form action="ProducerEdit">
              <button name="producerId" class="btn btn-primary" type="submit" value="<%= producer.getId() %>">EDIT</button>
            </form>
          </div>
          <div class="col-md-1">
            <form action="ProducersList">
              <button name="producerId" class="btn btn-danger" type="submit" value="<%= producer.getId() %>">DELETE</button>
            </form>
          </div>
        </div>
      </div>
    </div>
   </div>   
  </body>
</html>
