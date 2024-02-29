package com.movie.app.entities;


import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "comments")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Comment extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(nullable = false)
    private String content;
    @Temporal(TemporalType.DATE)
    @Column(nullable = false)
    private Date commentedAt;
    @Column(name = "reply_id")
    private Long reply;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "user_id", nullable = false)
    private User user;
    @ManyToOne
    @JoinColumn(name = "series_id", referencedColumnName = "series_id", nullable = false)
    private Series series;
    @ManyToOne
    @JoinColumn(name = "movie_id", referencedColumnName = "movie_id", nullable = false)
    private Movie movie;
}
