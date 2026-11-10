package hostel.repositories;

import hostel.models.LeaveRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface LeaveRequestRepository extends JpaRepository<LeaveRequest, Integer> {
    // Custom Query 6: Find leave requests by status (Pending, Approved, Rejected)
    List<LeaveRequest> findByStatus(String status);
}