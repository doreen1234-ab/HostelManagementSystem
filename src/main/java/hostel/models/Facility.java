package hostel.models;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "Facility")
public class Facility {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer facilityId;

    @Column(nullable = false)
    private String name; 

    @ManyToMany(mappedBy = "facilities")
    private List<Room> rooms;

    // Getters and Setters
    public Integer getFacilityId() { return facilityId; }
    public void setFacilityId(Integer facilityId) { this.facilityId = facilityId; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public List<Room> getRooms() { return rooms; }
    public void setRooms(List<Room> rooms) { this.rooms = rooms; }
}