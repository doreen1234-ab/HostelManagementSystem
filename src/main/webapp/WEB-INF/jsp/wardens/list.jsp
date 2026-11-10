<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head><link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet"></head>
<body class="bg-light">
    <%@ include file="../navbar.jsp" %>
    <div class="container mt-5">
        <h1>Wardens</h1>

        <a href="/wardens/new" class="btn btn-primary mb-3">Add Warden</a>
        <table class="table table-bordered">
            <thead class="table-dark"><tr><th>ID</th><th>Name</th><th>Phone</th><th>Email</th><th>Actions</th></tr></thead>
            <tbody>
                <c:forEach var="w" items="${wardens}">
                    <tr>
                        <td>${w.wardenId}</td><td>${w.name}</td><td>${w.phone}</td><td>${w.email}</td>
                        <td>
                            <a href="/wardens/edit/${w.wardenId}" class="btn btn-sm btn-warning">Edit</a>
                            <a href="/wardens/delete/${w.wardenId}" class="btn btn-sm btn-danger">Delete</a>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>
	<%@ include file="/WEB-INF/jsp/footer.jsp" %>
</body>

</html>