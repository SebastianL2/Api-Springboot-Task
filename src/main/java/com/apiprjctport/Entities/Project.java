package com.apiprjctport.Entities;

import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Data
@Table(name="Project")
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "project_id")
    private long id;
    @Column(name = "name")
    private String name;
    @Column(name = "department")
    private String department;
    @Column(name = "description")
    private String description;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "fk_task_id",referencedColumnName = "project_id")
    private List<Task> tasks;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "fk_coment_id",referencedColumnName = "project_id")
    private List<Coments> comentsList;


}
