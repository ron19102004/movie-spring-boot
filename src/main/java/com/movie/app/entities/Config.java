package com.movie.app.entities;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "configs")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Config extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(nullable = false)
    private String nameWeb;
    @Column(nullable = false)
    private String emailAdmin;
    private String iconWeb;
    private String bannerIntro;
    private String facebookPageLink;
    @ColumnDefault("false")
    private Boolean isActive;
}
