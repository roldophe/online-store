package com.onlinestore.api.user;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "roles")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    @ManyToMany(mappedBy = "roles")
    private List<User> users;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(joinColumns =
    @JoinColumn(name = "role_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "auth_id", referencedColumnName = "id"))
    private Set<Authority> authorities;

}
