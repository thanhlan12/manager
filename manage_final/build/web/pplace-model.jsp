<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
    <head>
        <title>Local Management Application</title>
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
                    <a href="https://www.javaguides.net" class="navbar-brand"> Popular Place Management App </a>
                </div>

            </nav>
        </header>
        <br>
        <div class="container col-md-5">
            <div class="card">
                <div class="card-body">
                    <c:if test="${pplace != null}">
                        <form action="update_place" method="post">
                        </c:if>
                        <c:if test="${pplace == null}">
                            <form action="insert_place" method="post">
                            </c:if>

                            <caption>
                                <h2>
                                    <c:if test="${pplace != null}">
                                        Edit Popular Place
                                    </c:if>
                                    <c:if test="${pplace == null}">
                                        Add New Popular Place
                                    </c:if>
                                </h2>
                            </caption>

                            <c:if test="${pplace != null}">
                                <input type="hidden" name="id" value="<c:out value='${pplace.id}' />" />
                            </c:if>

                            <fieldset class="form-group">
                                <label>Popular Place Name</label> <input type="text"
                                                                         value="<c:out value='${pplace.name}' />" class="form-control"
                                                                         name="name" required="required">
                            </fieldset>

                            <fieldset class="form-group">
                                <label>Popular Place Image</label> <input type="text"
                                                                          value="<c:out value='${pplace.image}' />" class="form-control"
                                                                          name="image">
                            </fieldset>

                            <fieldset class="form-group">
                                <label>Popular Place Status</label> <input type="text"
                                                                           value="<c:out value='${pplace.status}' />" class="form-control"
                                                                           name="status">
                            </fieldset>

                            <button type="submit" class="btn btn-success">Save</button>
                        </form>
                </div>
            </div>
        </div>
    </body>
</html>
