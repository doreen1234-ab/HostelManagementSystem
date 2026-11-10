package hostel.models;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "Warden")
public class Warden {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "warden_id")
    private Integer wardenId;
    
    @Column(nullable = false)
    private String name;
    @Column(unique = true, nullable = false)
    private String phone;
    @Column(unique = true)
    private String email;

    // Getters and Setters
    public Integer getWardenId() { return wardenId; }
    public void setWardenId(Integer wardenId) { this.wardenId = wardenId; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
}