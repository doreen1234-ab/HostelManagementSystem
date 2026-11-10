package hostel.models;

import jakarta.persistence.*;

@Entity
@Table(name = "HostelBlock")
public class HostelBlock {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "block_id")
    private Integer blockId;

    @Column(name = "block_name")
    private String blockName;

    @Column(name = "warden_id")
    private Integer wardenId; 
    
    private String gender;

    // Getters and Setters
    public Integer getBlockId() { return blockId; }
    public void setBlockId(Integer blockId) { this.blockId = blockId; }
    public String getBlockName() { return blockName; }
    public void setBlockName(String blockName) { this.blockName = blockName; }
    public String getGender() { return gender; }
    public void setGender(String gender) { this.gender = gender; }
}