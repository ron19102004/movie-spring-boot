package com.movie.app.entities;

import jakarta.persistence.*;
import lombok.*;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "watch_laters")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class WatchLater extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "series_id",referencedColumnName = "series_id", nullable = false)
    private Series series;
    @ManyToOne
    @JoinColumn(name = "user_id",referencedColumnName = "user_id", nullable = false)
    private User user;
}
