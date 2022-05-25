<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html lang="en">
    <head>
        <title>User Management Application</title>
        <meta charset=UTF-8"/>
        <link rel="stylesheet"
              href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
              integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
              crossorigin="anonymous">
    </head>
    <body>

        <header>
            <nav class="navbar navbar-expand-md navbar-dark"
                 style="background-color: tomato">
                <div>
                    <a href="https://www.javaguides.net" class="navbar-brand"> Search-Local Management App </a>
                </div>

            </nav>
        </header>
        <br>
        <div class="container col-md-5">
            <div class="card">
                <div class="card-body">
                    <c:if test="${user != null}">
                        <form action="update" method="post">
                        </c:if>
                        <c:if test="${user == null}">
                            <form action="insert" method="post">
                            </c:if>

                            <caption>
                                <h2>
                                    <c:if test="${user != null}">
                                        Edit Search-Local
                                    </c:if>
                                    <c:if test="${user == null}">
                                        Add New Search-Local
                                    </c:if>
                                </h2>
                            </caption>

                            <c:if test="${user != null}">
                                <input type="hidden" name="id" value="<c:out value='${user.id}' />" />
                            </c:if>

                            <fieldset class="form-group">
                                <label>Search-Local Name</label> <input type="text"
                                                                        value="<c:out value='${user.name}' />" class="form-control"
                                                                        name="name" required="required">
                            </fieldset>

                            <fieldset class="form-group">
                                <label>Search-Local Image</label> <input type="text"
                                                                         value="<c:out value='${user.image}' />" class="form-control"
                                                                         name="image">
                            </fieldset>

                            <fieldset class="form-group">
                                <label>Search-Local Country</label> <input type="text"
                                                                           value="<c:out value='${user.country}' />" class="form-control"
                                                                           name="country">
                            </fieldset>

                            <button type="submit" class="btn btn-success">Save</button>
                        </form>
                </div>
            </div>
        </div>
    </body>
</html>
