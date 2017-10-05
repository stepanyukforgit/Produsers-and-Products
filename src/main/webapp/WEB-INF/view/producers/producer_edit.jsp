<%-- 
    Document   : ProducerEdit
    Created on : 03.08.2017, 19:25:06
    Author     : stepanyuk
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="stepanyuk.productsandproducers.model.Producer"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
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
    <spring:url value="/resources/js/producers_edit.js" var="prodEditdJS" />

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
        <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
          <form action="/producers/producer_info" method="POST">
            <div class="form-group">
              <label><spring:message code="prod.name"/></label>
              <input type="text" class="form-control" id="usr" name="producerName" value="${producer.name}">
            </div>
            <div class="form-group">
                <label><spring:message code="prod.address"/></label>
                <input type="text" class="form-control" id="usr" name="producerAddress" value="${producer.address}">
            </div>              
            <div class="form-group">
              <label><spring:message code="prod.description"/></label>
              <textarea class="form-control" rows="5" name="producerDescription">${producer.description}</textarea>
            </div>
            <div class="form-group" id="upload-div">
              <label class="btn btn-primary" for="upload-label-input">
                <input id="upload-label-input" type="file" style="display:none" onchange="showUpload(this)">
                  <spring:message code="prod.chooseLogo"/>
              </label>
              <span class='label label-info' id="upload-label-info"></span>
            </div>
            <div class="form-group">
                <img id="logo" src="${producer.logo.pathToLogo}" height="100" width="100" alt="Logo not found">
                <input type="hidden" name="producerLogo" value="${producer.logo.pathToLogo}" />
            </div>
            <div class="form-group">
                <button type="submit" class="btn btn-success btn-lg" name="producerId" 
                        value="${producer.id}"><spring:message code="prod.saveCanges"/></button>
            </div>
          </form>
        </div>
    </div>
   </div>   
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
    <script src="${mainJs}"></script>
    <script src="${tooltipJs}"></script>
    <script src="${prodEditdJS}"></script>
  </body>
</html>