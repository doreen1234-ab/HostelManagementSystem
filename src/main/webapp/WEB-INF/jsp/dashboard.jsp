<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Hostel Dashboard</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="bg-light">
    <%@ include file="navbar.jsp" %>
    <div class="container mt-5">
        <h1 class="mb-4">Hostel Management Dashboard</h1>
        
        <!-- Counts Row -->
        <div class="row">
            <div class="col-md-6 mb-4">
                <div class="card text-white bg-primary">
                    <div class="card-body">
                        <h5 class="card-title">Total Students</h5>
                        <h2 class="card-text">${totalStudents}</h2>
                    </div>
                </div>
            </div>
            <div class="col-md-6 mb-4">
                <div class="card text-white bg-success">
                    <div class="card-body">
                        <h5 class="card-title">Total Rooms</h5>
                        <h2 class="card-text">${totalRooms}</h2>
                    </div>
                </div>
            </div>
        </div>

        <!-- Recent Activity -->
        <h4 class="mt-2 mb-3">Recent Activity (Latest Complaints)</h4>
        <div class="card mb-4">
            <ul class="list-group list-group-flush">
                <c:choose>
                    <c:when test="${empty recentComplaints}">
                        <li class="list-group-item text-muted">No complaints filed yet.</li>
                    </c:when>
                    <c:otherwise>
                        <c:forEach var="c" items="${recentComplaints}">
                            <li class="list-group-item d-flex justify-content-between align-items-center">
                                <span><strong>${c.student.name}</strong> filed a complaint: ${c.category}</span>
                                <span class="badge ${c.status == 'Open' ? 'bg-danger' : c.status == 'InProgress' ? 'bg-warning' : 'bg-success'}">${c.status}</span>
                            </li>
                        </c:forEach>
                    </c:otherwise>
                </c:choose>
            </ul>
        </div>

        <!-- Navigation Buttons -->
        <div class="mt-4">
            <a href="/students" class="btn btn-outline-primary">Manage Students</a>
            <a href="/rooms" class="btn btn-outline-success">Manage Rooms</a>
            <a href="/allocations/new" class="btn btn-outline-warning">Allocate Room (AJAX)</a>
            <a href="/complaints" class="btn btn-outline-danger">Manage Complaints</a>
            <a href="/reports" class="btn btn-outline-info">View Reports</a>
            <a href="/wardens" class="btn btn-outline-dark">Wardens</a>
            <a href="/leaves" class="btn btn-outline-info">Leave Requests</a>
            <a href="/payments" class="btn btn-outline-success">Fee Payments</a>
        </div>
    </div>
	<%@ include file="footer.jsp" %>
</body>
</html>