<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Student List</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="bg-light">
    <div class="container mt-5">
        <h1 class="mb-4">Students</h1>
        <a href="/" class="btn btn-secondary mb-3">Back to Dashboard</a>
        
        <div class="card">
            <div class="card-body">
                <table class="table table-bordered table-striped">
                    <thead class="table-dark">
                        <tr>
                            <th>
                                <a href="/students/page/1?sortField=name&sortDir=${reverseSortDir}" class="text-white text-decoration-none">
                                    Name ${sortField == 'name' ? (sortDir == 'asc' ? '↑' : '↓') : ''}
                                </a>
                            </th>
                            <th>Email</th>
                            <th>
                                <a href="/students/page/1?sortField=department&sortDir=${reverseSortDir}" class="text-white text-decoration-none">
                                    Department ${sortField == 'department' ? (sortDir == 'asc' ? '↑' : '↓') : ''}
                                </a>
                            </th>
                            <th>Year</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="student" items="${students}">
                            <tr>
                                <td>${student.name}</td>
                                <td>${student.email}</td>
                                <td>${student.department}</td>
                                <td>${student.yearOfStudy}</td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>

                <!-- Pagination Controls -->
                <nav>
                    <ul class="pagination justify-content-center">
                        <li class="page-item ${currentPage == 1 ? 'disabled' : ''}">
                            <a class="page-link" href="/students/page/${currentPage - 1}?sortField=${sortField}&sortDir=${sortDir}">Previous</a>
                        </li>
                        
                        <c:forEach begin="1" end="${totalPages}" var="i">
                            <li class="page-item ${currentPage == i ? 'active' : ''}">
                                <a class="page-link" href="/students/page/${i}?sortField=${sortField}&sortDir=${sortDir}">${i}</a>
                            </li>
                        </c:forEach>

                        <li class="page-item ${currentPage == totalPages ? 'disabled' : ''}">
                            <a class="page-link" href="/students/page/${currentPage + 1}?sortField=${sortField}&sortDir=${sortDir}">Next</a>
                        </li>
                    </ul>
                </nav>
                
                <p class="text-center text-muted">Total Students: ${totalItems}</p>
            </div>
        </div>
    </div>
</body>
</html>