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
    <meta name="description" content="Producers and products">
    <meta name="author" content="Aleksey Stepanyuk">
    <link rel="icon" href="<spring:url value="/resources/picture/favicon.ico" />">
    <spring:url value="/resources/css/bootstrap.min.css" var="mainCss" />
    <spring:url value="/resources/css/dashboard.css" var="dashCss" />
    <spring:url value="/resources/js/bootstrap.min.js" var="mainJs" />
    <spring:url value="/resources/js/tooltip.js" var="tooltipJs" />

    <title>Producers and products</title>

    <link href="${mainCss}" rel="stylesheet" />
    <link href="${dashCss}" rel="stylesheet" />
  </head>

  <body>

    <nav class="navbar navbar-inverse navbar-fixed-top">
      <div class="container-fluid">
        <div class="navbar-header">
          <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" 
                  data-target="#navbar" aria-expanded="false" aria-controls="navbar">
            <span class="sr-only"><spring:message code="main.title"/></span>
          </button>
          <a class="navbar-brand" href="/"><spring:message code="main.title"/></a>
        </div>
        <div id="navbar" class="navbar-collapse collapse">
          <form class="navbar-form navbar-right" action="/search_results">
            <input name="search" type="text" class="form-control" placeholder="<spring:message code="search.search"/>" 
                   data-toggle="tooltip" title="<spring:message code="search.tip"/>" data-placement="bottom">
          </form>
        </div>
      </div>
    </nav>

   <div class="container-fluid">
    <div class="row">
      <div class="col-sm-3 col-md-2 sidebar">
          <ul class="nav nav-sidebar">
            <li class="active">
                <a href="/"><spring:message code="main.mainPage"/>
                    <span class="sr-only"><spring:message code="main.mainPage"/></span>
                </a>
            </li>
          </ul>
          <ul class="nav nav-sidebar">
            <li><a href="/producers/producers_list"><spring:message code="main.producers"/></a></li>
          </ul>
          <ul class="nav nav-sidebar">
            <li><a href="/products/products_list"><spring:message code="main.products"/></a></li>
          </ul>
        </div>
      <div class="col-sm-6 col-sm-offset-3 col-md-6 col-md-offset-2 main">
        <h1 class="page-header">${producer.name}</h1>
        <h4>${producer.address}</h4>
        <h5>${producer.description}</h5>
        <h3><a href="/producers/producer_products/${producer.id}"><spring:message code="prod.seeAllProds"/></a></h3>
          <div class="row">
             <div class="col-md-2 col-sm-2">
            <form action="/producers/producer_edit/${producer.id}">
              <button class="btn btn-primary" type="submit"><spring:message code="prod.edit"/></button>
            </form>
          </div>
          <div class="col-md-1 col-md-offset-1 col-sm-1 col-sm-offset-1">
            <form action="/producers/producers_list">
              <button name="producerId" class="btn btn-danger" type="submit" 
                      value="${producer.id}"><spring:message code="prod.delete"/></button>
            </form>
          </div>
        </div>
      </div>
      <div class="col-sm-3 col-md-4 main">
        <img src="${producer.logo.pathToLogo}" height="300" width="300" alt="Logo not found">
      </div>
    </div>
   </div>   
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
    <script src="${mainJs}"></script>
    <script src="${tooltipJs}"></script>
  </body>
</html>
