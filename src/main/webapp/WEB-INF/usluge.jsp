<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <title>Sportski centar - usluge</title>
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
                  <a class="nav-link" href="/korisnici">Korisnici</a>
                </li>
                <li class="nav-item">
                  <a class="nav-link" href="/korisnici/nov-korisnik">Dodaj novog korisnika</a>
                </li>
                <li class="nav-item active">
                  <a class="nav-link" href="/usluge">Usluge <span class="sr-only">(current)</span></a>
                </li>
                <li class="nav-item">
                  <a class="nav-link" href="/usluge/nova-usluga">Nova usluge</span></a>
                </li>
            </sec:authorize>
            <li class="nav-item">
              <a class="nav-link" href="/zahtevi">Zahtevi</a>
            </li>
            <li class="nav-item">
              <a class="nav-link" href="/zahtevi/nov-zahtev">Nov zahtev</span></a>
            </li>
          </ul>
        </div>
      </nav>
    </header>
    <main role="main" class="flex-shrink-0">
    <div class="container">
    <c:if test="${messageOk != null && messageOk.length() > 0}">
                <div class="alert alert-success d-flex justify-content-center" role="alert">
                    <div>
                        ${messageOk}
                    </div>
                </div>
            </c:if>
            <c:if test="${messageProblem != null && messageProblem.length() > 0}">
                <div class="alert alert-danger d-flex justify-content-center" role="alert">
                    <div>
                        ${messageProblem}
                    </div>
                </div>
            </c:if>
        <table class="table table-striped table-hover">
            <caption>Usluge</caption>
            <thead>
                <tr>
                    <th scope="col">id</th>
                    <th scope="col">Naziv</th>
                    <th scope="col">Opis</th>
                    <th scope="col">Vrsta Usluge</th>
                    <th scope="col">Aktivna</th>
                    <th scope="col">Akcija</th>
                </tr>
            </thead>
            <tbody>
                    <c:forEach var="usluga" items="${usluge}">
                            <tr>
                                <th scope="row">${usluga.id}</th>
                                <td>${usluga.naziv}</td>
                                <td>${usluga.opis}</td>
                                <td>${usluga.vrstaUsluge.naziv}</td>
                                <td>${usluga.aktivna}</td>
                                <td>
                                    <a class="btn btn-link" href="/usluge/${usluga.id}">Izmeni</a>
                                    <div class="d-inline-block">
                                    <c:choose>
                                        <c:when test="${usluga.aktivna==true}">
                                            <form action="/usluge/${usluga.id}/deactivate" method="post">
                                            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
                                            <button class="btn btn-link" type="submit">Deaktiviraj</button>
                                            </form>
                                        </c:when>
                                        <c:otherwise>
                                            <form action="/usluge/${usluga.id}/activate" method="post">
                                            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                                            <button class="btn btn-link" type="submit">Aktiviraj</button>
                                            </form>
                                        </c:otherwise>
                                    </c:choose>
                                    </div>
                                </td>
                            </tr>
                        </c:forEach>
            </tbody>
        </table>
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