<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head><link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet"></head>
<body class="bg-light">
    <div class="container mt-5">
        <h1>Leave Requests</h1>
        <a href="/" class="btn btn-secondary mb-3">Dashboard</a>
        <a href="/leaves/new" class="btn btn-primary mb-3">New Leave Request</a>
        <table class="table table-bordered">
            <thead class="table-dark"><tr><th>ID</th><th>Student</th><th>From</th><th>To</th><th>Status</th><th>Action</th></tr></thead>
            <tbody>
                <c:forEach var="l" items="${leaves}">
                    <tr>
                        <td>${l.leaveId}</td><td>${l.student.name}</td><td>${l.fromDate}</td><td>${l.toDate}</td>
                        <td><span class="badge ${l.status == 'Approved' ? 'bg-success' : 'bg-warning'}">${l.status}</span></td>
                        <td>
                            <c:if test="${l.status == 'Pending'}">
                                <a href="/leaves/approve/${l.leaveId}" class="btn btn-sm btn-success">Approve</a>
                            </c:if>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>
</body>
</html>