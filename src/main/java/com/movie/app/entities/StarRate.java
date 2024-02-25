package com.movie.app.entities;

import jakarta.persistence.*;
import lombok.*;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "star_rates")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class StarRate extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(nullable = false)
    private Integer value;

    @ManyToOne
    @JoinColumn(name = "movie_id",nullable = false,referencedColumnName = "movie_id")
    private Movie movie;
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false, referencedColumnName = "user_id")
    private User user;
}
