package org.example.backend.entity;

import jakarta.persistence.*;
import lombok.*;
import org.example.backend.entity.enums.RoleName;
import org.springframework.security.core.GrantedAuthority;

@Getter
@Setter
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor

@Table(name = "roles")
public class Role  implements GrantedAuthority {
    @Id
    @Column(name = "id", nullable = false)
    private Integer id;
    @Enumerated(EnumType.STRING)
    private RoleName roleName;

    @Override
    public String getAuthority() {
        return this.roleName.toString();
    }
}