package hostel.repositories;

import hostel.models.Allocation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface AllocationRepository extends JpaRepository<Allocation, Integer> {
    // Custom Query 4: Find all active allocations
    List<Allocation> findByActiveTrue();
}