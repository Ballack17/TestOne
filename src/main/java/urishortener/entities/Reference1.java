package urishortener.entities;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "references1")
public class Reference1 {

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

    public Reference1() {
    }

    public Long getUniqueUsed() {
        return uniqueUsed;
    }

    public void setUniqueUsed(Long uniqueUsed) {
        this.uniqueUsed = uniqueUsed;
    }

    public void wasUniqueUsed() {
        this.uniqueUsed += 1L;
    }

    public Reference1(String referenceUser, User idUser, int lifetime) {
        this.referenceUser = referenceUser;
        this.idUser = idUser;
        if (lifetime>0) {this.lifetime = LocalDateTime.now().plusDays(lifetime);}
        this.wasUsed = 0L;
        this.uniqueUsed = 0L;
        this.timeBounded = (lifetime>0);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getReferenceUser() {
        return referenceUser;
    }

    public void setReferenceUser(String referenceUser) {
        this.referenceUser = referenceUser;
    }

    public String getReferenceShort() {
        return referenceShort;
    }

    public void setReferenceShort(String referenceShort) {
        this.referenceShort = referenceShort;
    }

    public void setLifetime(LocalDateTime lifetime) {
        this.lifetime = lifetime;
    }

    public Long getWasUsed() {
        return this.wasUsed;
    }

    public void setWasUsed(Long wasUsed) {
        this.wasUsed = wasUsed;
    }

    public void wasUsed() {
        this.wasUsed +=1L;
    }

    public User getIdUser() {
        return idUser;
    }

    public void setIdUser(User idUser) {
        this.idUser = idUser;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getLifetime() {
        return lifetime;
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

    public boolean isTimeBounded() {
        return timeBounded;
    }

    public void setTimeBounded(boolean timeBounded) {
        this.timeBounded = timeBounded;
    }

    public boolean readyToDie() {
        return (this.lifetime.compareTo(LocalDateTime.now()) < 0) ;
    }
}
