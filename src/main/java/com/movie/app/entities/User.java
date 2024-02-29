package com.movie.app.entities;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "users", indexes = @Index(columnList = "username", name = "index_username_users"))
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class User extends BaseEntity implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "user_id", unique = true)
    private Long id;
    @Column(nullable = false)
    private String fullName;
    @Column(nullable = false, unique = true)
    private String username;
    @Column(nullable = false)
    private String password;
    @Column(nullable = false, unique = true)
    private String phone;
    @Column(nullable = false)
    private String email;
    @ColumnDefault("false")
    private Boolean isVip;
    @ColumnDefault("false")
    private Boolean confirmed;
    @Column(nullable = false)
    private Role role;

    @OneToMany(mappedBy = "user")
    private List<View> views;
    @OneToMany(mappedBy = "user")
    private List<WatchLater> watchLaters;
    @OneToMany(mappedBy = "user")
    private List<Comment> comments;
    @OneToMany(mappedBy = "user")
    private List<StarRate> starRates;

    public Map<String, Object> toMapData() {
        Map<String, Object> data = new HashMap<>();
        data.put("username", this.getUsername());
        data.put("password", this.getPassword());
        data.put("fullName", this.getFullName());
        data.put("vip", this.getIsVip());
        data.put("phone", this.getPhone());
        data.put("email", this.getEmail());
        return data;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(this.role.toString()));
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
