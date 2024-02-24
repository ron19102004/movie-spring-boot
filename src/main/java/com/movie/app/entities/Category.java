package com.movie.app.entities;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "categories")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Category extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "category_id")
    private Long id;
    @Column(nullable = false, unique = true)
    private String name;
    @ColumnDefault("false")
    private Boolean deleted;
    @Column(nullable = false)
    private String image;

    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL)
    private List<Movie> movies;
}
