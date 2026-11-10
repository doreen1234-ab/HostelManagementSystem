package hostel.repositories;

import hostel.models.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface RoomRepository extends JpaRepository<Room, Integer> {
    List<Room> findByStatus(String status);
    
    @Query("SELECT r FROM Room r WHERE LOWER(r.block.blockName) LIKE LOWER(CONCAT('%', :blockName, '%')) AND r.status = 'Available'")
    List<Room> findAvailableRoomsByBlockName(@Param("blockName") String blockName);
    
    @Query("SELECT r FROM Room r WHERE r.block.blockId = :blockId")
    List<Room> findRoomsByBlockId(@Param("blockId") Integer blockId);
}