package com.train.chengqian.jikexutilstrain.domain;

import org.xutils.db.annotation.Column;
import org.xutils.db.annotation.Table;

/**
 * Created by houn.xu
 */
@Table(name = "r_teacher_student")
public class RTeacherStudent {
    @Column(name = "id",isId = true)
    private int id;
    @Column(name = "s_id")
    private String sId;
    @Column(name = "t_id")
    private String tId;

    public RTeacherStudent() {
    }

    public RTeacherStudent(String sId, String tId) {
        this.sId = sId;
        this.tId = tId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getsId() {
        return sId;
    }

    public void setsId(String sId) {
        this.sId = sId;
    }

    public String gettId() {
        return tId;
    }

    public void settId(String tId) {
        this.tId = tId;
    }
}
