package hostel.repositories;

import hostel.models.HostelBlock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HostelBlockRepository extends JpaRepository<HostelBlock, Integer> {
}