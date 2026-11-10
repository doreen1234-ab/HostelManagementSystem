<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Complaints List</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="bg-light">
    <div class="container mt-5">
        <h1 class="mb-4">Complaints</h1>
        <a href="/" class="btn btn-secondary mb-3">Dashboard</a>
        <a href="/complaints/new" class="btn btn-primary mb-3">File New Complaint</a>
        
        <table class="table table-bordered table-striped">
            <thead class="table-dark">
                <tr>
                    <th>ID</th>
                    <th>Student</th>
                    <th>Category</th>
                    <th>Status</th>
                    <th>Date Filed</th>
                    <th>Actions</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="c" items="${complaints}">
                    <tr>
                        <td>${c.complaintId}</td>
                        <td>${c.student.name}</td>
                        <td>${c.category}</td>
                        <td>
                            <span class="badge ${c.status == 'Open' ? 'bg-danger' : c.status == 'InProgress' ? 'bg-warning' : 'bg-success'}">
                                ${c.status}
                            </span>
                        </td>
                        <td>${c.createdOn}</td>
                        <td>
                            <a href="/complaints/edit/${c.complaintId}" class="btn btn-sm btn-warning">Edit</a>
                            <a href="/complaints/delete/${c.complaintId}" class="btn btn-sm btn-danger" onclick="return confirm('Delete this complaint?');">Delete</a>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>
</body>
</html>