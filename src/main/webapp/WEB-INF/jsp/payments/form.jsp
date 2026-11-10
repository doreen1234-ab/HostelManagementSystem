<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
    <title>Record Payment</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="bg-light">
    <%@ include file="../navbar.jsp" %>
    <div class="container mt-5">
        <h1 class="mb-4">Record Payment</h1>
        <a href="/payments" class="btn btn-secondary mb-3">Back to List</a>

        <!-- Server-side error alert -->
        <c:if test="${not empty error}">
            <div class="alert alert-danger">${error}</div>
        </c:if>

        <div class="card">
            <div class="card-body">
                <form:form action="/payments/save" modelAttribute="payment" method="post">
                    <div class="mb-3">
                        <label class="form-label">Student</label>
                        <select name="studentId" class="form-select" required>
                            <option value="">-- Select Student --</option>
                            <c:forEach var="s" items="${students}">
                                <option value="${s.studentId}">${s.name} (${s.studentId})</option>
                            </c:forEach>
                        </select>
                    </div>

                    <div class="mb-3">
                        <label class="form-label">Amount</label>
                        <form:input path="amount" type="number" step="0.01" min="0.01" class="form-control" required="required" />
                        <form:errors path="amount" cssClass="text-danger" />
                    </div>

                    <div class="mb-3">
                        <label class="form-label">Payment Mode</label>
                        <form:select path="paymentMode" class="form-select">
                            <form:option value="Cash" label="Cash" />
                            <form:option value="Card" label="Card" />
                            <form:option value="UPI" label="UPI" />
                        </form:select>
                    </div>

                    <button type="submit" class="btn btn-success">Save Payment</button>
                </form:form>
            </div>
        </div>
    </div>
	<%@ include file="/WEB-INF/jsp/footer.jsp" %>
</body>
</html>