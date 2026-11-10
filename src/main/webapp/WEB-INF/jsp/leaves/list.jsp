<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Leave Requests</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="bg-light">
    <%@ include file="../navbar.jsp" %>
    <div class="container mt-5">
        <h1 class="mb-4">Leave Requests</h1>

        <!-- Error Alert -->
        <c:if test="${param.error == 'AlreadyProcessed'}">
            <div class="alert alert-warning">This leave request has already been approved or rejected.</div>
        </c:if>

        <a href="/leaves/new" class="btn btn-primary mb-3">New Leave Request</a>
        
        <table class="table table-bordered table-striped">
            <thead class="table-dark">
                <tr>
                    <th>ID</th>
                    <th>Student</th>
                    <th>From</th>
                    <th>To</th>
                    <th>Status</th>
                    <th>Approving Warden</th>
                    <th>Action</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="l" items="${leaves}">
                    <tr>
                        <td>${l.leaveId}</td>
                        <td>${l.student.name}</td>
                        <td>${l.fromDate}</td>
                        <td>${l.toDate}</td>
                        <td>
                            <span class="badge ${l.status == 'Approved' ? 'bg-success' : l.status == 'Rejected' ? 'bg-danger' : 'bg-warning'}">
                                ${l.status}
                            </span>
                        </td>
                        <td>${l.warden != null ? l.warden.name : '-'}</td>
                        <td>
                            <c:if test="${l.status == 'Pending'}">
                                <form action="/leaves/approve/${l.leaveId}" method="post" style="display:inline;">
                                    <input type="hidden" name="wardenId" value="1" />
                                    <button type="submit" class="btn btn-sm btn-success">Approve</button>
                                </form>
                            </c:if>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>
	<%@ include file="/WEB-INF/jsp/footer.jsp" %>
</body>
</html>