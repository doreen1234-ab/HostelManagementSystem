<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Allocate Room</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
    <!-- jQuery for AJAX -->
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
</head>
<body class="bg-light">
    <%@ include file="../navbar.jsp" %>
    <div class="container mt-5">
    
        <h1>Allocate Room</h1>

        <div class="card">
            <div class="card-body">
                <form>
                    <div class="mb-3">
                        <label class="form-label">Student ID</label>
                        <input type="number" class="form-control" id="studentId" placeholder="Enter Student ID" required>
                    </div>
                    
                    <div class="mb-3">
                        <label class="form-label">Filter by Block Name</label>
                        <input type="text" class="form-control" id="blockName" placeholder="e.g. Aravali" onkeyup="loadAvailableRooms()">
                    </div>

                    <div class="mb-3">
                        <label class="form-label">Select Available Room</label>
                        <select class="form-select" id="roomId">
                            <option value="">-- Select a Room --</option>
                          
                        </select>
                    </div>

                    <button type="button" class="btn btn-primary" onclick="submitAllocation()">Allocate Room</button>
                </form>
            </div>
        </div>
    </div>

	<script>
	    var ctx = '${pageContext.request.contextPath}';
	    var searchTimer = null;

	    function resetDropdown(message) {
	        $('#roomId').empty().append($('<option>').val('').text(message));
	    }

	  
	    function loadAvailableRooms() {
	        clearTimeout(searchTimer);
	        searchTimer = setTimeout(function () {
	            var blockName = $('#blockName').val().trim();

	            if (blockName.length === 0) {
	                resetDropdown('-- Select a Room --');
	                return;
	            }

	            $.ajax({
	                url: ctx + '/api/rooms/available',
	                type: 'GET',
	                data: { blockName: blockName },
	                dataType: 'json',
	                success: function (rooms) {
	                    console.log('Data received:', rooms);

	                    if (!rooms || rooms.length === 0) {
	                        resetDropdown('-- No available rooms found --');
	                        return;
	                    }

	                    resetDropdown('-- Select a Room --');
	                    $.each(rooms, function (i, room) {
	                        var label = room.roomNumber;
	                        if (room.block && room.block.blockName) {
	                            label += ' (' + room.block.blockName + ')';
	                        }
	                        $('#roomId').append($('<option>').val(room.roomId).text(label));
	                    });
	                },
	                error: function (xhr) {
	                    console.log('Error:', xhr.status, xhr.responseText);
	                    resetDropdown('-- Error loading rooms --');
	                }
	            });
	        }, 300);
	    }

	    function submitAllocation() {
	        var studentId = $('#studentId').val();
	        var roomId = $('#roomId').val();

	        if (studentId && roomId) {
	            $.ajax({
	                url: ctx + '/api/rooms/allocate',
	                type: 'POST',
	                data: { studentId: studentId, roomId: roomId },
	                success: function () {
	                    alert('Success! Room allocated.');
	                    window.location.href = ctx + '/';
	                },
	                error: function (xhr) {
	                    alert('Error: ' + xhr.responseText);
	                }
	            });
	        } else {
	            alert('Please fill in all fields.');
	        }
	    }
	</script>	
</body>
</html>