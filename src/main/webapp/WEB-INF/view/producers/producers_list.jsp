<%-- 
    Document   : ProducerList
    Created on : 03.08.2017, 19:25:42
    Author     : stepanyuk
--%>

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
        <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
          <h1 class="page-header">Producers</h1>

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
                  <% List<Producer> producers = (List<Producer>)request.getAttribute("producers"); %>
                  <% for (int i = 0; i < producers.size(); i++){ %>
                    <tr align="left">
                      <td width="3%"><%= i + 1 %></td>
                      <td width="17%">
                        <a href="/producers/producer_info?producerId=<%= producers.get(i).getId() %>">
                            <%= producers.get(i).getName() %>
                        </a>
                      </td>
                      <td width="30%"><%= producers.get(i).getAddress() %></td>
                      <td width="50%"><%= producers.get(i).getDescription() %></td>
                    </tr>
                    <%}%>
              </tbody>
            </table>
            <button type="button" class="btn btn-success" data-toggle="modal" data-target="#add-producer-modal">Add new Producer</button>  
          </div>
        </div>
      </div>
    </div>
   </div>
              
   <!--Modal window start-->
    <div class="modal fade" id="add-producer-modal">
        <div class="modal-dialog modal-lg">
          <div class="modal-content">
              <form action="/producers/producers_list" method="POST">
                <div class="modal-header">
                  <button class="close" type="button" data-dismiss="modal">&times;</button>
                  <h4 class="maodal-title">Add new producer</h4>
                </div>
                <div class="modal-body">
                  <div class="form-group">
                    <label>Producer name:</label>
                    <input type="text" class="form-control" name="producerName" required>
                  </div>
                  <div class="form-group">
                    <label>Address:</label>
                    <input type="text" class="form-control" name="producerAddress" required>
                  </div>
                  <div class="form-group">
                    <label>Description:</label>
                    <textarea class="form-control" rows="5" name="producerDescription"></textarea>
                  </div>
                  <div class="modal-footer">
                    <button class="btn btn-success" type="submit">Add</button>
                  </div>
                </div>
            </form>
          </div>
        </div>
    </div>
    <!--Modal window end-->
   
    <!-- Bootstrap core JavaScript================================================== -->
    <!-- Placed at the end of the document so the pages load faster -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
    <script src="${mainJs}"></script>
  </body>
</html>