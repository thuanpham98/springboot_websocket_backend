package com.thuannek.services.project;

import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.TypedQuery;

@Entity 
@Table(name = "projects")
public class ProjectEntity {

    @Id
    @Column(name = "id",nullable = false ,length= 127)
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private String id;

    @Column(name = "note", nullable = false ,length=255)
    private String notes;

    @Column(name = "name", nullable = false ,length=127)
    private String name;

    @Column(name = "parentId", nullable = true ,length=127)
    private String parentId;

    @Column(name = "type", nullable = false)
    private int type;

    // @Type(type = "jsonb")
    // @Column(name = "access", nullable = false)
    // private Map<String, Integer> access;
}
