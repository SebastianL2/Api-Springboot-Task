package com.apiprjctport.Entities;



import jakarta.persistence.*;
import lombok.Data;

import java.util.*;

@Entity
@Data
@Table(name="Employee")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "employee_id")
    private long id;

    @Column(name = "name")
    private String name;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "charge")
    private String charge;

    @Column(name = "department")
    private String department;

    @ManyToMany
    @JoinTable(name = "employee_task",
            joinColumns = @JoinColumn(name = "employ_id", referencedColumnName = "employee_id"),
            inverseJoinColumns = @JoinColumn(name = "task_id", referencedColumnName = "task_id")
    )
    private List<Task> assignedTask;





}
