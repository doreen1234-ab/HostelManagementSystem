package hostel.repositories;

import hostel.models.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, Integer> {
    // Custom Query 1: Find students by department
    List<Student> findByDepartment(String department);
    
    // Custom Query 2: Search by name
    List<Student> findByNameContainingIgnoreCase(String name);
}