<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Room Form</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="bg-light">
    <%@ include file="../navbar.jsp" %>
    <div class="container mt-5">
        <h1 class="mb-4">Room Details</h1>
        <a href="${pageContext.request.contextPath}/rooms" class="btn btn-secondary mb-3">Back to List</a>

        <c:if test="${not empty error}">
            <div class="alert alert-danger">${error}</div>
        </c:if>

        <div class="card">
            <div class="card-body">
                <form action="${pageContext.request.contextPath}/rooms/save" method="post">
                    <input type="hidden" name="roomId" value="${room.roomId}" />

                    <div class="mb-3">
                        <label class="form-label">Room Number</label>
                        <input type="text" name="roomNumber" class="form-control"
                               value="${room.roomNumber}" required />
                    </div>

                    <div class="mb-3">
                        <label class="form-label">Hostel Block</label>
                        <select name="blockId" class="form-select" required>
                            <option value="">-- Select block --</option>
                            <c:forEach var="b" items="${blocks}">
                                <option value="${b.blockId}"
                                    ${room.block != null && room.block.blockId == b.blockId ? 'selected' : ''}>
                                    ${b.blockName}
                                </option>
                            </c:forEach>
                        </select>
                    </div>

                    <div class="mb-3">
                        <label class="form-label">Room Type</label>
                        <select name="roomTypeId" class="form-select" required>
                            <option value="">-- Select room type --</option>
                            <c:forEach var="t" items="${roomTypes}">
                                <option value="${t.roomTypeId}"
                                    ${room.roomType != null && room.roomType.roomTypeId == t.roomTypeId ? 'selected' : ''}>
                                    ${t.typeName}
                                </option>
                            </c:forEach>
                        </select>
                    </div>

                    <div class="mb-3">
                        <label class="form-label">Status</label>
                        <select name="status" class="form-select">
                            <option value="Available" ${room.status == 'Available' ? 'selected' : ''}>Available</option>
                            <option value="Full" ${room.status == 'Full' ? 'selected' : ''}>Full</option>
                            <option value="Maintenance" ${room.status == 'Maintenance' ? 'selected' : ''}>Maintenance</option>
                        </select>
                    </div>

                    <button type="submit" class="btn btn-success">Save Room</button>
                </form>
            </div>
        </div>
    </div>
	<%@ include file="/WEB-INF/jsp/footer.jsp" %>
</body>

</html>
