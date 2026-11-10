package hostel.repositories;

import hostel.models.FeePayment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface FeePaymentRepository extends JpaRepository<FeePayment, Integer> {
    // Custom Query 5: Find payments by student ID
    List<FeePayment> findByStudentStudentId(Integer studentId);
}