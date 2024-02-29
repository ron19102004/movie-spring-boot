package com.movie.app.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "views")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class View extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Temporal(TemporalType.DATE)
    @Column(nullable = false)
    private Date viewedAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "movie_id",nullable = false,referencedColumnName = "movie_id")
    private Movie movie;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id",nullable = false,referencedColumnName = "user_id")
    private User user;

}
