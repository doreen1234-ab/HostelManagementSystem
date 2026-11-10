<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head><link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet"></head>

<body class="bg-light">
    <%@ include file="../navbar.jsp" %>
    <div class="container mt-5">
        <h1>Warden Form</h1>
        <a href="/wardens" class="btn btn-secondary mb-3">Back</a>
        <div class="card"><div class="card-body">
            <form action="/wardens/save" method="post">
                <input type="hidden" name="wardenId" value="${warden.wardenId}" />
                <div class="mb-3"><label>Name</label><input type="text" name="name" class="form-control" value="${warden.name}" required /></div>
                <div class="mb-3"><label>Phone</label><input type="text" name="phone" class="form-control" value="${warden.phone}" required /></div>
                <div class="mb-3"><label>Email</label><input type="email" name="email" class="form-control" value="${warden.email}" required /></div>
                <button type="submit" class="btn btn-success">Save</button>
            </form>
        </div></div>
    </div>
	<%@ include file="/WEB-INF/jsp/footer.jsp" %>
</body>
</html>