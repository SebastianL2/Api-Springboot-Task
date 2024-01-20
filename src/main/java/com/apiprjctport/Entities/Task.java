package com.apiprjctport.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.util.*;

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

    @JsonIgnore
    @ManyToMany(mappedBy = "assignedTask")
    private List<Employee>employeeSet;


}
