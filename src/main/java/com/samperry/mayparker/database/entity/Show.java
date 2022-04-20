package com.samperry.mayparker.database.entity;

import lombok.*;

import javax.persistence.*;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "shows")
public class Show {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    //    @Temporal (TemporalType.DATE)
    @Column(name = "date")
    private Date date;

    @Column(name = "location")
    private String location;


    @Column(name = "time")
    private String time;

    @ToString.Exclude
    @ManyToMany(mappedBy = "uShows", fetch = FetchType.LAZY)
    private List<User> sUsers = new ArrayList<>();

    @ToString.Exclude
    @ManyToMany(mappedBy = "sShows", fetch = FetchType.LAZY)
    private List<Song> sSongs = new ArrayList<>();


}
