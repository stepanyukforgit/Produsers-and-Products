<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

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
    <spring:url value="/resources/css/login.css" var="loginCss" />
    <spring:url value="/resources/js/bootstrap.min.js" var="mainJs" />
    <spring:url value="/resources/js/tooltip.js" var="tooltipJs" />

    <title>Producers and products</title>
    <link href="${mainCss}" rel="stylesheet" />
    <link href="${dashCss}" rel="stylesheet" />
    <link href="${loginCss}" rel="stylesheet" />
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
            <form class="navbar-form navbar-right" action="/logout" method="POST">
                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                <button type="submit" class="btn btn-default">Log out</button>
            </form>
            <div class="navbar-right">
              <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" 
                      data-target="#navbar" aria-expanded="false" aria-controls="navbar">
                <span class="sr-only">EN</span>
              </button>
              <a class="navbar-brand" href="/home?locale=en">EN</a>
            </div>
            <div class="navbar-right">
              <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" 
                      data-target="#navbar" aria-expanded="false" aria-controls="navbar">
                <span class="sr-only">RU</span>
              </button>
              <a class="navbar-brand" href="/home?locale=ru">RU</a>
            </div>
        </div>
      </div>
    </nav>
    <div class="container">
        
        <form class="form-signin" action="/login" method="POST">
            <h2 class="form-signin-heading">Please log in</h2>

            <c:if test="${param.error != null}">
                <h4 style="color: red">Invalid user name or password.</h4>
            </c:if>
            <c:if test="${param.logout != null}">
                <h4 style="color: red">You've been logged out.</h4>
            </c:if>

            <label for="inputEmail" class="sr-only">User name</label>
            <input type="text" class="form-control" placeholder="Login"  name="username"required autofocus>

            <label for="inputPassword" class="sr-only">Password</label>
            <input type="password" class="form-control" placeholder="Password" name="password" required>

            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
            <button class="btn btn-lg btn-primary btn-block" type="submit">Log in</button>
        </form>
        <form class="form-signin" action="/login" method="POST">
            <input type="hidden" name="username" value="user">
            <input type="hidden" name="password" value="password">
            <label for="inputPassword" class="sr-only">Password</label>
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
            <button class="btn btn-lg btn-default btn-block" type="submit">Continue without log in</button>
        </form>  
        <form class="form-signin" action="/logout" method="POST">
            <button class="btn btn-lg btn-danger btn-block" type="submit">Log out</button>
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
        </form>
    </div>
    <script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
    <script type="text/javascript" src="${mainJs}"></script>
    <script type="text/javascript" src="${tooltipJs}"></script>
  </body>
</html>
