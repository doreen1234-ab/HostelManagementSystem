<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head><link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet"></head>
<body class="bg-light">
    <%@ include file="../navbar.jsp" %>
    <div class="container mt-5">
        <h1>New Leave Request</h1>
        <a href="/leaves" class="btn btn-secondary mb-3">Back</a>
        <div class="card"><div class="card-body">
            <form action="/leaves/save" method="post">
                <div class="mb-3">
                    <label>Student</label>
                    <select name="studentId" class="form-select" required>
                        <c:forEach var="s" items="${students}">
                            <option value="${s.studentId}">${s.name}</option>
                        </c:forEach>
                    </select>
                </div>
                <div class="mb-3"><label>From Date</label><input type="date" name="fromDate" class="form-control" required /></div>
                <div class="mb-3"><label>To Date</label><input type="date" name="toDate" class="form-control" required /></div>
                <div class="mb-3"><label>Reason</label><textarea name="reason" class="form-control" required></textarea></div>
                <button type="submit" class="btn btn-success">Submit Request</button>
            </form>
        </div></div>
    </div>
	<%@ include file="/WEB-INF/jsp/footer.jsp" %>
</body>

</html>