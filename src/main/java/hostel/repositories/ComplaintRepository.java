package hostel.repositories;

import hostel.models.Complaint;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ComplaintRepository extends JpaRepository<Complaint, Integer> {
    // Custom Query 7: Find complaints that are not resolved
    List<Complaint> findByStatusNot(String status);
    
    List<Complaint> findByCategoryContainingIgnoreCase(String category);
}