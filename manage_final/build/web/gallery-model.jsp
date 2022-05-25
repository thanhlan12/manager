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
				<a href="https://www.javaguides.net" class="navbar-brand"> Gallery Management App </a>
			</div>

		</nav>
	</header>
	<br>
	<div class="container col-md-5">
		<div class="card">
			<div class="card-body">
				<c:if test="${gallery != null}">
					<form action="update_gallery" method="post">
				</c:if>
				<c:if test="${gallery == null}">
					<form action="insert_gallery" method="post">
				</c:if>

				<caption>
					<h2>
						<c:if test="${gallery != null}">
            			Edit Gallery
            		</c:if>
						<c:if test="${gallery == null}">
            			Add New Gallery
            		</c:if>
					</h2>
				</caption>

				<c:if test="${gallery != null}">
					<input type="hidden" name="id" value="<c:out value='${gallery.id}' />" />
				</c:if>

				<fieldset class="form-group">
					<label>Gallery Name</label> <input type="text"
						value="<c:out value='${gallery.name}' />" class="form-control"
						name="name" required="required">
				</fieldset>

				<fieldset class="form-group">
					<label>Gallery Image</label> <input type="text"
						value="<c:out value='${gallery.image}' />" class="form-control"
						name="image">
				</fieldset>

				<fieldset class="form-group">
					<label>Gallery Country</label> <input type="text"
						value="<c:out value='${gallery.country}' />" class="form-control"
						name="country">
				</fieldset>

				<button type="submit" class="btn btn-success">Save</button>
				</form>
			</div>
		</div>
	</div>
</body>
</html>
