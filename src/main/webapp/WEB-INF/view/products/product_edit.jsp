<%-- 
    Document   : ProductEdit
    Created on : 03.08.2017, 19:26:28
    Author     : stepanyuk
--%>

<%@page import="java.util.List"%>
<%@page import="stepanyuk.productsandproducers.model.Producer"%>
<%@page import="stepanyuk.productsandproducers.model.Product"%>
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
          <a class="navbar-brand" href="/">Producers and products</a>
        </div>
        <div id="navbar" class="navbar-collapse collapse">
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
            <li class="active"><a href="/">Main page <span class="sr-only">(current)</span></a></li>
          </ul>
          <ul class="nav nav-sidebar">
            <li><a href="/producers/producers_list">Producers</a></li>
          </ul>
          <ul class="nav nav-sidebar">
            <li><a href="/products/products_list">Products</a></li>
          </ul>
        </div>
        <% Product product = (Product) request.getAttribute("product");%>
        <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
          <form action="/products/product_info" method="POST">
            <div class="form-group">
              <label>Product name</label>
              <input type="text" class="form-control" id="usr" name="productName" value="<%= product.getName() %>">
            </div>
            <div class="form-group">
                <label>Price</label>
                <input type="text" class="form-control" id="usr" name="productPrice" value="<%= product.getPrice() %>">
            </div>              
            <div class="form-group">
              <label>Description</label>
              <textarea class="form-control" rows="5" name="productDescription"><%= product.getDescription() %></textarea>
            </div>
            
            <div class="form-group">
                <label for="sel1">Producer:</label>
                <select class="form-control" name="producerId" required>
                  <% List<Producer> producers = (List<Producer>)request.getAttribute("producers"); %>
                  <% for (int i = 0; i < producers.size(); i++){ %>
                  <option value="<%= producers.get(i).getId() %>"><%= producers.get(i).getName() %></option>
                  <%}%>   
                </select>                
              </div>
            <button type="submit" class="btn btn-success btn-lg" name="productId" value="<%= product.getId() %>">Save all changes</button>
          </form>
        </div>
    </div>
   </div>   
  </body>
</html>