<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Room List</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="bg-light">
    <%@ include file="../navbar.jsp" %>
    <div class="container mt-5">
        <h1 class="mb-4">Rooms</h1>
        <a href="/rooms/new" class="btn btn-primary mb-3">Add New Room</a>
        
        <table class="table table-bordered table-striped">
            <thead class="table-dark">
                <tr>
                    <th>
                        <a href="/rooms?sortField=roomId&sortDir=${reverseSortDir}" class="text-white text-decoration-none">
                            ID ${sortField == 'roomId' ? (sortDir == 'asc' ? '↑' : '↓') : ''}
                        </a>
                    </th>
                    <th>
                        <a href="/rooms?sortField=roomNumber&sortDir=${reverseSortDir}" class="text-white text-decoration-none">
                            Room Number ${sortField == 'roomNumber' ? (sortDir == 'asc' ? '↑' : '↓') : ''}
                        </a>
                    </th>
                    <th>Block</th>
                    <th>
                        <a href="/rooms?sortField=status&sortDir=${reverseSortDir}" class="text-white text-decoration-none">
                            Status ${sortField == 'status' ? (sortDir == 'asc' ? '↑' : '↓') : ''}
                        </a>
                    </th>
                    <th>Actions</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="room" items="${rooms}">
                    <tr id="room-row-${room.roomId}">
                        <td>${room.roomId}</td>
                        <td>${room.roomNumber}</td>
                        <td>${room.block.blockName}</td>
                        <td>${room.status}</td>
                        <td>
                            <a href="/rooms/edit/${room.roomId}" class="btn btn-sm btn-warning">Edit</a>
                            <button class="btn btn-sm btn-danger" onclick="deleteRoomAjax(${room.roomId})">Delete</button>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>

        <!-- Pagination Controls -->
        <nav>
            <ul class="pagination justify-content-center mt-3">
                <li class="page-item ${currentPage == 1 ? 'disabled' : ''}">
                    <a class="page-link" href="/rooms?pageNo=${currentPage - 1}&sortField=${sortField}&sortDir=${sortDir}">Previous</a>
                </li>
                
                <c:forEach begin="1" end="${totalPages}" var="i">
                    <li class="page-item ${currentPage == i ? 'active' : ''}">
                        <a class="page-link" href="/rooms?pageNo=${i}&sortField=${sortField}&sortDir=${sortDir}">${i}</a>
                    </li>
                </c:forEach>

                <li class="page-item ${currentPage == totalPages ? 'disabled' : ''}">
                    <a class="page-link" href="/rooms?pageNo=${currentPage + 1}&sortField=${sortField}&sortDir=${sortDir}">Next</a>
                </li>
            </ul>
        </nav>
        
        <p class="text-center text-muted">Total Rooms: ${totalItems}</p>
    </div>

    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <script>
        function deleteRoomAjax(id) {
            if(confirm("Are you sure you want to delete this room?")) {
                $.ajax({
                    url: '/api/rooms/delete/' + id,
                    type: 'DELETE',
                    success: function(response) {
                        $("#room-row-" + id).remove();
                    },
                    error: function() {
                        alert("Error deleting room.");
                    }
                });
            }
        }
    </script>
    <%@ include file="../footer.jsp" %>
</body>
</html>