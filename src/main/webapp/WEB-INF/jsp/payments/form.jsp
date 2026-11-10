<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head><link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet"></head>
<body class="bg-light">
    <div class="container mt-5">
        <h1>Record Payment</h1>
        <a href="/payments" class="btn btn-secondary mb-3">Back</a>
        <div class="card"><div class="card-body">
            <form action="/payments/save" method="post">
                <div class="mb-3">
                    <label>Student</label>
                    <select name="studentId" class="form-select" required>
                        <c:forEach var="s" items="${students}">
                            <option value="${s.studentId}">${s.name}</option>
                        </c:forEach>
                    </select>
                </div>
                <div class="mb-3"><label>Amount</label><input type="number" step="0.01" name="amount" class="form-control" required /></div>
                <div class="mb-3">
                    <label>Payment Mode</label>
                    <select name="paymentMode" class="form-select">
                        <option value="Cash">Cash</option>
                        <option value="Card">Card</option>
                        <option value="UPI">UPI</option>
                    </select>
                </div>
                <button type="submit" class="btn btn-success">Save Payment</button>
            </form>
        </div></div>
    </div>
</body>
</html>