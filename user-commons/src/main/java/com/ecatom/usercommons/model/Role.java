package com.ecatom.usercommons.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "roles")
public class Role implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true , length = 30)
    private String roleName;
//    @ManyToMany(fetch = FetchType.LAZY , mappedBy = "roles")
//    private List<User> users;


    public Role() {
    }

    public Role(Long id , String roleName) {
        this.id = id;
        this.roleName = "ROLE_" + roleName.toUpperCase();
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }
}
