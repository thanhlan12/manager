<%@page import="model.Gallery"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
    <head>
        <title>Gallery Management Application</title>
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
                    <a href="https://www.javaguides.net" class="navbar-brand"> Gallery
                        Management App </a>
                </div>
            </nav>
        </header>
        <br>

        <div class="row">
            <!-- <div class="alert alert-success" *ngIf='message'>{{message}}</div> -->

            <div class="container">
                <h3 class="text-center">List of Gallery</h3>
                <hr>
                <div class="container text-left">

                    <a href="<%=request.getContextPath()%>/news" class="btn btn-success">Add
                        New Gallery</a>
                </div>
                <br>
                <table class="table table-bordered">
                    <thead>
                        <tr>
                            <th>ID</th>
                            <th>Name</th>
                            <th>image</th>
                            <th>Country</th>
                            <th>Actions</th>
                        </tr>
                    </thead>
                    <tbody>
                        <!--   for (Todo todo: todos) {  -->
                        <c:forEach var="gallery" items="${listGallery}">

                            <tr>
                                <td><c:out value="${gallery.id}" /></td>
                                <td><c:out value="${gallery.name}" /></td>
                                <td><img src="${gallery.image}" alt="img" width="150" height="100"/></td>
                                <td><c:out value="${gallery.country}" /></td>
                                <td><a href="edit_gallery?id=<c:out value='${gallery.id}' />">Edit</a>
                                    &nbsp;&nbsp;&nbsp;&nbsp; <a
                                        href="delete_gallery?id=<c:out value='${gallery.id}' />">Delete</a></td>
                            </tr>
                        </c:forEach>
                        <!-- } -->
                    </tbody>

                </table>
            </div>
        </div>
    </body>
</html>
