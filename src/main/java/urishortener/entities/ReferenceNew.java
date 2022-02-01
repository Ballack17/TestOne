package urishortener.entities;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "references_new")
public class ReferenceNew {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "reference_user")
    private String referenceUser;

    @Column(name = "reference_short")
    private String referenceShort;

    @ManyToOne
    @JoinColumn(name = "id_user")
    private User idUser;

    @Column(name = "created_at")
    @CreationTimestamp
    private LocalDateTime createdAt;

    @Column(name = "lifetime")
    private LocalDateTime lifetime;

    @Column (name = "was_used")
    private Long wasUsed;

    @Column (name = "unique_used")
    private Long uniqueUsed;

    @Column (name = "time_bounded")
    private boolean timeBounded;

    public ReferenceNew() {
    }

    public ReferenceNew(String referenceUser, User idUser, int lifetime) {
        this.referenceUser = referenceUser;
        this.idUser = idUser;
        if (lifetime>0) {this.lifetime = LocalDateTime.now().plusDays(lifetime);}
        this.wasUsed = 0L;
        this.uniqueUsed = 0L;
        this.timeBounded = (lifetime>0);
    }

    public void wasUniqueUsed() {
        this.uniqueUsed += 1L;
    }

    public void wasUsed() {
        this.wasUsed +=1L;
    }

    public void setLifetime(int lifetime) {
        this.lifetime = this.lifetime.plusDays(lifetime);
    }

    public String GenerationShortReference () {
        String letters = "abcdefghijklmnopqrstuvwxyz0123456789";
        String generatedValue = new String();
        for (int i = 0; i < 5; i++) {
            generatedValue += String.valueOf(letters.charAt((int) (Math.random()*letters.length())));
        }
        return generatedValue;
    }

    public LocalDateTime timeLeft () {
        if (this.getLifetime() == null) {
            return LocalDateTime.now().plusYears(1000);
        } else {
            return this.getLifetime();
        }
    }

    public boolean readyToDie() {
        return (this.lifetime.compareTo(LocalDateTime.now()) < 0) ;
    }
}
