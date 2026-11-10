package hostel.models;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "Allocation")
public class Allocation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "allocation_id")
    private Integer allocationId;

    @Column(name = "alloc_date")
    private LocalDate allocDate;
    
    @Column(name = "end_date")
    private LocalDate endDate;
    @ManyToOne
    @JoinColumn(name = "student_id") 
    private Student student;

    @ManyToOne
    @JoinColumn(name = "room_id") 
    private Room room;

    private Boolean active;

    // Getters and Setters
    public Integer getAllocationId() { return allocationId; }
    public void setAllocationId(Integer allocationId) { this.allocationId = allocationId; }
    public Student getStudent() { return student; }
    public void setStudent(Student student) { this.student = student; }
    public Room getRoom() { return room; }
    public void setRoom(Room room) { this.room = room; }
    public LocalDate getAllocDate() { return allocDate; }
    public void setAllocDate(LocalDate allocDate) { this.allocDate = allocDate; }
    public Boolean getActive() { return active; }
    public void setActive(Boolean active) { this.active = active; }
}