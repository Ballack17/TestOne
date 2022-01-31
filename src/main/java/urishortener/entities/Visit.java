package urishortener.entities;


import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
@Table(name ="visitors")
public class Visit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "visit_ip")
    private String visitIp;

    @ManyToOne
    @JoinColumn(name = "reference_id")
    private Reference1 reference1;

    @Column(name = "created_at")
    @CreationTimestamp
    private LocalDateTime createdAt;

    public Visit() {
    }

    public Visit(String visitIp, Reference1 reference1) {
        this.visitIp = visitIp;
        this.reference1 = reference1;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getVisitIp() {
        return visitIp;
    }

    public void setVisitIp(String visitIp) {
        this.visitIp = visitIp;
    }

    public Reference1 getReference() {
        return reference1;
    }

    public void setReference(Reference1 reference) {
        this.reference1 = reference;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
