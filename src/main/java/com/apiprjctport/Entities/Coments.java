package com.apiprjctport.Entities;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;
@Entity
@Data
@Table(name="Coments")
public class Coments {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Column(name = "text")
    private String text;
    @Column(name = "date")
    private Date date;
    @Column(name = "userId")
    private int userId;
    @Column(name = "taskID")
    private int taskID;

}
