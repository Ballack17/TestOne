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
    private ReferenceNew referenceNew;

    @Column(name = "created_at")
    @CreationTimestamp
    private LocalDateTime createdAt;

    public Visit() {
    }

    public Visit(String visitIp, ReferenceNew referenceNew) {
        this.visitIp = visitIp;
        this.referenceNew = referenceNew;
    }

}
