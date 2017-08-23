<%-- 
    Document   : ProducerList
    Created on : 03.08.2017, 19:25:42
    Author     : stepanyuk
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="stepanyuk.productsandproducers.model.Producer"%>
<%@page import="java.util.List"%>
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
          <h1 class="page-header">Search results</h1>
        
        <h3>Producers</h3>
        <div class="row placeholders">
          <div class="table-responsive">
            <table class="table table-striped">
              <thead>
                <tr>
                  <th>#</th>
                  <th>Name</th>
                  <th>Address</th>
                  <th>Description</th>
                </tr>
              </thead>
              <tbody>
                  <c:forEach var="producer" items="${producers}" varStatus="loop">
                    <tr align="left">
                      <td width="3%">${loop.count}</td>
                      <td width="17%">
                        <a href="/producers/producer_info/${producer.id}">
                            ${producer.name}
                        </a>
                      </td>
                      <td width="30%">${producer.address}</td>
                      <td width="50%">${producer.description}</td>
                    </tr>  
                  </c:forEach>
              </tbody>
            </table>
          </div>
        </div>
        <h3>Producers</h3>
        <div class="row placeholders">
          <div class="table-responsive">
            <table class="table table-striped">
              <thead>
                <tr>
                  <th>#</th>
                  <th>Name</th>
                  <th>price</th>
                  <th>Description</th>
                  <th>Producer</th>
                </tr>
              </thead>
              <tbody>
                  <c:forEach var="product" items="${products}" varStatus="loop">
                      <tr align="left">
                      <td width="3%">${loop.count}</td>
                      <td width="17%">
                        <a href="/products/product_info/${product.id}">${product.name}</a>
                      </td>
                      <td width="10%">${product.price}</td>
                      <td width="50%">${product.description}</td>
                      <td width="20%">${product.producer.name}</td>
                    </tr>
                  </c:forEach>
              </tbody>
            </table>
          </div>
        </div>
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