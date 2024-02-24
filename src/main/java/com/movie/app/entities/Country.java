package com.movie.app.entities;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "countries")
@Builder
public class Country extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "country_id")
    private Long id;
    @Column(nullable = false)
    private String name;
    @ColumnDefault("false")
    private Boolean deleted;

    @OneToMany(mappedBy = "country",cascade = CascadeType.ALL)
    private List<Movie> movies;
}
