package com.apiprjctport.Entities;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Entity
@Data
@Table(name = "TASK")
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "task_id")
    private long id;

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String desc;

    @Column(name = "coments")
    private String coments;

    @Column(name = "priority")
    private String priority;

    @Column(name = "finished")
    private String finished;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "fk_employee_id",referencedColumnName = "task_id")
    private List<Employee> employeeList;



}
