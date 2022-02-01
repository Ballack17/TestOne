package urishortener.entities;


import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "user_role")
public class Role {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    public Role() {
    }

    public Role(String roleName) {
        this.name = roleName;
    }

    public String getRoleName() {
        return name;
    }
}
