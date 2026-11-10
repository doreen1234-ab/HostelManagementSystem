<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Complaint Form</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="bg-light">
    <%@ include file="../navbar.jsp" %>

    <div class="container mt-5">
        <h1 class="mb-4">Complaint Details</h1>
        <a href="/complaints" class="btn btn-secondary mb-3">Back to List</a>

        <div class="card">
            <div class="card-body">
                <form action="/complaints/save" method="post">
                    <input type="hidden" name="complaintId" value="${complaint.complaintId}" />
                    
                    <div class="mb-3">
                        <label class="form-label">Student</label>
                        <select name="studentId" class="form-select" required>
                            <option value="">-- Select Student --</option>
                            <c:forEach var="s" items="${students}">
                                <option value="${s.studentId}" ${complaint.student != null && complaint.student.studentId == s.studentId ? 'selected' : ''}>
                                    ${s.name} (${s.studentId})
                                </option>
                            </c:forEach>
                        </select>
                    </div>

                    <div class="mb-3">
                        <label class="form-label">Category</label>
                        <input type="text" name="category" class="form-control" value="${complaint.category}" required />
                    </div>

                    <div class="mb-3">
                        <label class="form-label">Description</label>
                        <textarea name="description" class="form-control" rows="3" required>${complaint.description}</textarea>
                    </div>
                    
                    <div class="mb-3">
                        <label class="form-label">Status</label>
                        <select name="status" class="form-select">
                            <option value="Open" ${complaint.status == 'Open' ? 'selected' : ''}>Open</option>
                            <option value="InProgress" ${complaint.status == 'InProgress' ? 'selected' : ''}>In Progress</option>
                            <option value="Resolved" ${complaint.status == 'Resolved' ? 'selected' : ''}>Resolved</option>
                        </select>
                    </div>

                    <button type="submit" class="btn btn-success">Save Complaint</button>
                </form>
            </div>
        </div>
    </div>
	<%@ include file="/WEB-INF/jsp/footer.jsp" %>
</body>
</html>