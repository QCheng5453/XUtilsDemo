package com.train.chengqian.jikexutilstrain.domain;

import org.xutils.db.annotation.Column;
import org.xutils.db.annotation.Table;

/**
 * Created by houn.xu
 */
@Table(name = "teacher")
public class Teacher {
    @Column(name = "id",isId = true)
    private int id;
    @Column(name = "t_name")
    private String tName;
    @Column(name = "s_id")
    private String sId;

    public Teacher() {
    }

    public Teacher(String tName, String sId) {
        this.tName = tName;
        this.sId = sId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String gettName() {
        return tName;
    }

    public void settName(String tName) {
        this.tName = tName;
    }

    public String getsId() {
        return sId;
    }

    public void setsId(String sId) {
        this.sId = sId;
    }
}
