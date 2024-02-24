package com.movie.app.entities;

import jakarta.persistence.*;
import lombok.*;

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
    private String name_web;
    @Column(nullable = false)
    private String email_admin;
    private String icon_web;
    private String banner_intro;
    private String facebook_page_link;
}
