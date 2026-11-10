<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head><link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet"></head>
<body class="bg-light">
    <%@ include file="../navbar.jsp" %>
    <div class="container mt-5">
        <h1>Student Form</h1>
        <a href="/students" class="btn btn-secondary mb-3">Back</a>
        <div class="card"><div class="card-body">
            <form action="/students/save" method="post">
                <input type="hidden" name="studentId" value="${student.studentId}" />
                <div class="mb-3"><label>Name</label><input type="text" name="name" class="form-control" value="${student.name}" required /></div>
                <div class="mb-3"><label>Email</label><input type="email" name="email" class="form-control" value="${student.email}" required /></div>
                <div class="mb-3"><label>Phone</label><input type="text" name="phone" class="form-control" value="${student.phone}" required /></div>
                <div class="mb-3"><label>Gender</label>
                    <select name="gender" class="form-select">
                        <option value="Male" ${student.gender == 'Male' ? 'selected' : ''}>Male</option>
                        <option value="Female" ${student.gender == 'Female' ? 'selected' : ''}>Female</option>
                    </select>
                </div>
                <div class="mb-3"><label>Department</label><input type="text" name="department" class="form-control" value="${student.department}" required /></div>
                <div class="mb-3"><label>Year of Study</label><input type="number" name="yearOfStudy" class="form-control" value="${student.yearOfStudy}" min="1" max="5" required /></div>
                <button type="submit" class="btn btn-success">Save</button>
            </form>
        </div></div>
    </div>
	<%@ include file="/WEB-INF/jsp/footer.jsp" %>
</body>

</html>