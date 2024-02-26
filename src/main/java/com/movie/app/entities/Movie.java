package com.movie.app.entities;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import java.util.Date;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "movies",indexes = @Index(columnList = "name", name = "index_name_movies"))
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Movie extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "movie_id")
    private Long id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String description;
    @Column(nullable = false)
    private String image;
    @Temporal(TemporalType.DATE)
    @Column(nullable = false)
    private Date release;
    @Column(nullable = false)
    private Long duration;
    @Column(nullable = false,name = "_cast")
    private String cast;
    @Column(nullable = false)
    private String director;
    @ColumnDefault("false")
    private Boolean deleted;
    @ColumnDefault("false")
    private Boolean is_vip;

    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false, referencedColumnName = "category_id")
    private Category category;
    @ManyToOne
    @JoinColumn(name = "country_id", nullable = false, referencedColumnName = "country_id")
    private Country country;
    @ManyToOne
    @JoinColumn(name = "series_id", referencedColumnName = "series_id")
    private Series series;

    @OneToMany(mappedBy = "movie",fetch = FetchType.LAZY)
    private List<StarRate> starRates;
    @OneToMany(mappedBy = "movie",fetch = FetchType.LAZY)
    private List<View> views;
    @OneToMany(mappedBy = "movie",fetch = FetchType.LAZY)
    private List<Comment> comments;
}
