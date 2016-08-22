package com.train.chengqian.jikexutilstrain.domain;

import org.xutils.db.annotation.Column;
import org.xutils.db.annotation.Table;

/**
 * Created by houn.xu
 */
@Table(name = "student")
public class Student {
    @Column(name = "id",isId = true)
    private int id;
    @Column(name = "s_name")
    private String sName;
    @Column(name = "s_id")
    private String sId;
    @Column(name = "age")
    private int age;

    public Student() {
    }

    public Student(int age, String sId, String sName) {
        this.age = age;
        this.sId = sId;
        this.sName = sName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getsName() {
        return sName;
    }

    public void setsName(String sName) {
        this.sName = sName;
    }

    public String getsId() {
        return sId;
    }

    public void setsId(String sId) {
        this.sId = sId;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
