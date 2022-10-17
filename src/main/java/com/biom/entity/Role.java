package com.biom.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "role", schema = "biom")
public class Role implements GrantedAuthority {
    @Id
    @JoinTable(name = "user_role",schema = "biom",
            joinColumns = {@JoinColumn(name = "role_id")})
    private Long id;

    private String name;

    public Role(String name) {
        this.name = name;
    }

    @Override
    public String getAuthority() {
        return name;
    }
}
