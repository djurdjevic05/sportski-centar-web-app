<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <title>Sportski centar - nov zahtev</title>
    <link href="${contextPath}/resources/css/bootstrap.min.css" rel="stylesheet">
    <link href="${contextPath}/resources/css/common.css" rel="stylesheet">
</head>
<body class="d-flex flex-column h-100">
    <header>
      <!-- Fixed navbar -->
      <nav class="navbar navbar-expand-md navbar-dark fixed-top bg-dark">
        <a class="navbar-brand" href="/">Sportski centar</a>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarCollapse" aria-controls="navbarCollapse" aria-expanded="false" aria-label="Toggle navigation">
          <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarCollapse">
          <ul class="navbar-nav mr-auto">
            <li class="nav-item">
              <a class="nav-link" href="/">Početna</a>
            </li>
            <sec:authorize access="hasAuthority('ADMIN')">
                <li class="nav-item">
                  <a class="nav-link" href="/korisnici">Korisnici</span></a>
                </li>
                <li class="nav-item">
                  <a class="nav-link" href="/korisnici/nov-korisnik">Dodaj novog korisnika</a>
                </li>
                <li class="nav-item">
                  <a class="nav-link" href="/usluge">Usluge</span></a>
                </li>
                <li class="nav-item">
                  <a class="nav-link" href="/usluge/nova-usluga">Nova usluga</span></a>
                </li>
            </sec:authorize>
            <li class="nav-item">
              <a class="nav-link" href="/zahtevi">Zahtev</a>
            </li>
            <li class="nav-ite">
              <a class="nav-link active" href="/zahtevi/nov-zahtev">Nov zahtev</span></a>
            </li>
          </ul>
        </div>
      </nav>
    </header>
    <main role="main" class="flex-shrink-0">
    <div class="container">
    <h2>Podnesi zahtev</h2>
    <form:form action="${contextPath}/zahtevi/add" modelAttribute="zahtev" method="post">
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
        <div class="form-group">
        <form:label for="inputNaziv" path="naziv">Naziv</form:label>
        <form:input type="text" class="form-control" id="inputNaziv" path="naziv" />
        </div>
        <div class="form-group">
          <form:label for="inputOpis" path="opis">Opis</form:label>
          <form:input type="text" class="form-control" id="inputOpis" path="opis" />
        </div>
        <div class="form-group">
          <form:label for="selectUslugu" path="usluga">Usluga</form:label>
          <form:select class="form-control" id="selectUslugu" path="usluga">
            <form:options items="${sveusluge}" id="id" itemLabel="naziv"></form:options>
          </form:select>
        </div>
        <button type="submit" class="btn btn-primary">Snimi</button>
    </form:form>
    </div>
    </main>
        <footer class="footer mt-auto py-3">
          <div class="container">
            <span class="text-muted">Place sticky footer content here.</span>
          </div>
        </footer>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
        <script src="${contextPath}/resources/js/bootstrap.min.js"></script>
</body>
</html>