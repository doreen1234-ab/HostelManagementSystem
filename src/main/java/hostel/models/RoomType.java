package hostel.models;

import jakarta.persistence.*;

@Entity
@Table(name = "RoomType")
public class RoomType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "room_type_id")
    private Integer roomTypeId;

    @Column(name = "type_name")
    private String typeName;

    private Integer capacity;

    @Column(name = "monthly_fee") 
    private Double monthlyFee;

    // Getters and Setters
    public Integer getRoomTypeId() { return roomTypeId; }
    public void setRoomTypeId(Integer roomTypeId) { this.roomTypeId = roomTypeId; }
    public String getTypeName() { return typeName; }
    public void setTypeName(String typeName) { this.typeName = typeName; }
    public Integer getCapacity() { return capacity; }
    public void setCapacity(Integer capacity) { this.capacity = capacity; }
    public Double getMonthlyFee() { return monthlyFee; }
    public void setMonthlyFee(Double monthlyFee) { this.monthlyFee = monthlyFee; }
}