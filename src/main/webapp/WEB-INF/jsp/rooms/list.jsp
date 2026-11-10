<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Room List</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="bg-light">
    <div class="container mt-5">
        <h1 class="mb-4">Rooms</h1>
        <a href="/" class="btn btn-secondary mb-3">Back to Dashboard</a>
        <a href="/rooms/new" class="btn btn-primary mb-3">Add New Room</a>
        
        <table class="table table-bordered table-striped">
            <thead class="table-dark">
                <tr>
                    <th>ID</th>
                    <th>Room Number</th>
                    <th>Block</th>
                    <th>Status</th>
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
</body>
</html>