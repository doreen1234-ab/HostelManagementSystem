<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head><link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet"></head>
<body class="bg-light">
    <%@ include file="../navbar.jsp" %>
    <div class="container mt-5">
        <h1>Fee Payments</h1>
 
        <a href="/payments/new" class="btn btn-primary mb-3">Record Payment</a>
        <table class="table table-bordered">
            <thead class="table-dark"><tr><th>ID</th><th>Student</th><th>Amount</th><th>Date</th><th>Mode</th></tr></thead>
            <tbody>
                <c:forEach var="p" items="${payments}">
                    <tr><td>${p.paymentId}</td><td>${p.student.name}</td><td>$${p.amount}</td><td>${p.paymentDate}</td><td>${p.paymentMode}</td></tr>
                </c:forEach>
            </tbody>
        </table>
    </div>
</body>

</html>