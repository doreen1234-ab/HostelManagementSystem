<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Hostel Reports</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="bg-light">
    <%@ include file="../navbar.jsp" %>
    <div class="container mt-5">
        <h1 class="mb-4">System Reports</h1>


        <!-- Tab Navigation -->
        <ul class="nav nav-tabs mb-3" id="reportTabs" role="tablist">
            <li class="nav-item">
                <button class="nav-link active" id="rooms-tab" data-bs-toggle="tab" data-bs-target="#rooms" type="button" role="tab">Room Status</button>
            </li>
            <li class="nav-item">
                <button class="nav-link" id="complaints-tab" data-bs-toggle="tab" data-bs-target="#complaints" type="button" role="tab">Unresolved Complaints</button>
            </li>
            <li class="nav-item">
                <button class="nav-link" id="allocations-tab" data-bs-toggle="tab" data-bs-target="#allocations" type="button" role="tab">Active Allocations</button>
            </li>
        </ul>

        <!-- Tab Content -->
        <div class="tab-content" id="reportTabsContent">
            
            <!-- Report 1: Rooms -->
            <div class="tab-pane fade show active" id="rooms" role="tabpanel">
                <table class="table table-bordered table-striped">
                    <thead class="table-dark">
                        <tr><th>Room ID</th><th>Room Number</th><th>Block</th><th>Status</th></tr>
                    </thead>
                    <tbody>
                        <c:forEach var="r" items="${rooms}">
                            <tr><td>${r.roomId}</td><td>${r.roomNumber}</td><td>${r.block.blockName}</td><td>${r.status}</td></tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>

            <!-- Report 2: Complaints -->
            <div class="tab-pane fade" id="complaints" role="tabpanel">
                <table class="table table-bordered table-striped">
                    <thead class="table-dark">
                        <tr><th>ID</th><th>Student</th><th>Category</th><th>Status</th><th>Date</th></tr>
                    </thead>
                    <tbody>
                        <c:forEach var="c" items="${complaints}">
                            <tr><td>${c.complaintId}</td><td>${c.student.name}</td><td>${c.category}</td><td>${c.status}</td><td>${c.createdOn}</td></tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>

            <!-- Report 3: Allocations -->
            <div class="tab-pane fade" id="allocations" role="tabpanel">
                <table class="table table-bordered table-striped">
                    <thead class="table-dark">
                        <tr><th>ID</th><th>Student</th><th>Room</th><th>Date Allocated</th></tr>
                    </thead>
                    <tbody>
                        <c:forEach var="a" items="${allocations}">
                            <tr><td>${a.allocationId}</td><td>${a.student.name}</td><td>${a.room.roomNumber}</td><td>${a.allocDate}</td></tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
    
    <!-- Bootstrap JS for Tabs -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
	<%@ include file="/WEB-INF/jsp/footer.jsp" %>
	</body>

</html>