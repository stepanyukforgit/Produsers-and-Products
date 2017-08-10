<%-- 
    Document   : ProducerList
    Created on : 03.08.2017, 19:25:42
    Author     : stepanyuk
--%>

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
                  <th>Products</th>
                </tr>
              </thead>
              <tbody>
                <tr>
                  <td>1</td>
                  <td><a href="ProducerInfo">First Producer</a></td>
                  <td>Ukrain, Odessa</td>
                  <td>very very very very very very very very very very 
                  very very very very very very very very very very very very very very good one</td>
                  <td><a href="ProducerProducts">
                          product, product, product, product, product, product,
                          product, product, product, product, product
                      </a>
                  </td>
                </tr>
                <tr>
                  <td>2</td>
                  <td>Second Producer</td>
                  <td>Ukrain, Kharkiv</td>
                  <td>normal producer</td>
                  <td>product, product, product, product, product, product,
                  product, product. </td>
                </tr>
                <tr>
                  <td>3</td>
                  <td>Third Producer</td>
                  <td>USA, NY</td>
                  <td>very very very very very very very very very very 
                  very very very very very very very very very very very 
                  very very very very very very very very very very bad one</td>
                  <td>product, product. </td>
                </tr>
              </tbody>
            </table>
          </div>
        </div>
      </div>
    </div>
   </div>   
  </body>
</html>
