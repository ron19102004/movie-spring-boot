package com.movie.app.entities;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import java.util.Date;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Builder
@Table(name = "series")
public class Series extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "series_id")
    private Long id;
    @Column(nullable = false)
    private String name;
    @ColumnDefault("false")
    private Boolean deleted;
    @Column(nullable = false)
    private String image;
    @Temporal(TemporalType.DATE)
    @Column(nullable = false)
    private Date release;
    @Column(nullable = false)
    private String description;
    @Column(nullable = false)
    private Integer episodes;
    @ColumnDefault("0")
    private Integer episodes_release;

    @OneToMany(mappedBy = "series",cascade = CascadeType.ALL)
    private List<Movie> movies;
    @OneToMany(mappedBy = "series",cascade = CascadeType.ALL)
    private List<WatchLater> watchLaters;
    @OneToMany(mappedBy = "series",cascade = CascadeType.ALL)
    private List<Comment> comments;
}
