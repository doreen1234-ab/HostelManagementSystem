package hostel.models;

import jakarta.persistence.*;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "Room")
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "room_id")
    private Integer roomId;

    @Column(name = "room_number")
    private String roomNumber;

    private String status;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "block_id", nullable = false)
    private HostelBlock block;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "room_type_id", nullable = false)
    @JsonIgnore
    private RoomType roomType;

    @ManyToMany
    @JoinTable(
        name = "room_facility",
        joinColumns = @JoinColumn(name = "room_id"),
        inverseJoinColumns = @JoinColumn(name = "facility_id")
    )
    @JsonIgnore
    private List<Facility> facilities;

    // Getters and Setters
    public Integer getRoomId() { return roomId; }
    public void setRoomId(Integer roomId) { this.roomId = roomId; }
    public String getRoomNumber() { return roomNumber; }
    public void setRoomNumber(String roomNumber) { this.roomNumber = roomNumber; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    public HostelBlock getBlock() { return block; }
    public void setBlock(HostelBlock block) { this.block = block; }
    public RoomType getRoomType() { return roomType; }
    public void setRoomType(RoomType roomType) { this.roomType = roomType; }
    public List<Facility> getFacilities() { return facilities; }
    public void setFacilities(List<Facility> facilities) { this.facilities = facilities; }
}