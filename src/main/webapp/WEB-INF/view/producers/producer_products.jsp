<%-- 
    Document   : ProducerProducts
    Created on : 03.08.2017, 19:26:02
    Author     : stepanyuk
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="java.util.ArrayList"%>
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
        <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
            <h1 class="page-header"><spring:message code="main.producers"/>: ${producer.name}</h1>
        <div class="row placeholders">
          <div class="table-responsive">
            <table class="table table-striped">
              <thead>
                <tr>
                  <th>#</th>
                  <th><spring:message code="prod.name"/></th>
                  <th><spring:message code="prod.price"/></th>
                  <th><spring:message code="prod.description"/></th>
                </tr>
              </thead>
              <tbody>
                  <c:forEach var="product" items="${producer.products}" varStatus="loop">
                    <tr align="left">
                      <td width="3%">${loop.count}</td>
                      <td width="17%">
                        <a href="/products/product_info/${product.id}">
                            ${product.name}
                        </a>
                      </td>
                      <td width="10%">${product.price}</td>
                      <td width="50%">${product.description}</td>
                    </tr>  
                  </c:forEach>                    
              </tbody>
            </table>
            <button type="button" class="btn btn-success" data-toggle="modal" data-target="#add-product-modal"><spring:message code="prod.addNew"/></button>              
          </div>
        </div>
      </div>
    </div>
   </div>
              
   <!--Modal window start-->
    <div class="modal fade" id="add-product-modal">
        <div class="modal-dialog modal-lg">
          <div class="modal-content">
              <form action="/producers/producer_products" method="POST">
                <div class="modal-header">
                  <button class="close" type="button" data-dismiss="modal">&times;</button>
                  <h4 class="maodal-title"><spring:message code="prod.addNew"/></h4>
                </div>
                <div class="modal-body">
                  <div class="form-group">
                    <label><spring:message code="prod.name"/>:</label>
                    <input type="text" class="form-control" name="productName" required>
                  </div>
                  <div class="form-group">
                    <label><spring:message code="prod.price"/>:</label>
                    <input type="text" class="form-control" name="productPrice" required>
                  </div>
                  <div class="form-group">
                    <label><spring:message code="prod.description"/>:</label>
                    <textarea class="form-control" rows="5" name="productDescription"></textarea>
                  </div>
                  <div class="modal-footer">
                      <button name="producerId" class="btn btn-success" type="submit" value="${producer.id}"><spring:message code="prod.add"/></button>
                  </div>
                </div>
            </form>
          </div>
        </div>
    </div>
    <!--Modal window end-->
   
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
    <script src="${mainJs}"></script>
    <script src="${tooltipJs}"></script>
  </body>
</html>

