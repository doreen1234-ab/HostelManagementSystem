package hostel.models;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "LeaveRequest")
public class LeaveRequest {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "leave_id") 
    private Integer leaveId;

    @ManyToOne
    @JoinColumn(name = "student_id")
    private Student student;

    @ManyToOne
    @JoinColumn(name = "warden_id")
    private Warden warden;

    @Column(name = "from_date") 
    private LocalDate fromDate;

    @Column(name = "to_date") 
    private LocalDate toDate;

    private String reason;

    private String status;

    // Getters and Setters
    public Integer getLeaveId() { return leaveId; }
    public void setLeaveId(Integer leaveId) { this.leaveId = leaveId; }
    public Student getStudent() { return student; }
    public void setStudent(Student student) { this.student = student; }
    public Warden getWarden() { return warden; }
    public void setWarden(Warden warden) { this.warden = warden; }
    public LocalDate getFromDate() { return fromDate; }
    public void setFromDate(LocalDate fromDate) { this.fromDate = fromDate; }
    public LocalDate getToDate() { return toDate; }
    public void setToDate(LocalDate toDate) { this.toDate = toDate; }
    public String getReason() { return reason; }
    public void setReason(String reason) { this.reason = reason; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}