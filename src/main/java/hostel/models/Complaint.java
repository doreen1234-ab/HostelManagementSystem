package hostel.models;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "Complaint")
public class Complaint {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "complaint_id") 
    private Integer complaintId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "student_id", nullable = false)
    private Student student;

    private String category;

    @Column(name = "description")
    private String description;

    private String status;

    @Column(name = "created_on") 
    private LocalDate createdOn;

    @Column(name = "resolved_on") 
    private LocalDate resolvedOn;

    // Getters and Setters
    public Integer getComplaintId() { return complaintId; }
    public void setComplaintId(Integer complaintId) { this.complaintId = complaintId; }
    public Student getStudent() { return student; }
    public void setStudent(Student student) { this.student = student; }
    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    public LocalDate getCreatedOn() { return createdOn; }
    public void setCreatedOn(LocalDate createdOn) { this.createdOn = createdOn; }
    public LocalDate getResolvedOn() { return resolvedOn; }
    public void setResolvedOn(LocalDate resolvedOn) { this.resolvedOn = resolvedOn; }
}