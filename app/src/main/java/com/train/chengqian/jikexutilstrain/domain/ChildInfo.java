package com.train.chengqian.jikexutilstrain.domain;

import org.xutils.db.annotation.Column;
import org.xutils.db.annotation.Table;

/**
 * Created by cheng.qian on 2016/8/19.
 */
@Table(name = "child_info",onCreated = "")
public class ChildInfo {
    @Column(name = "id", isId = true, autoGen = true, property = "NOT NULL")
    private int id;
    @Column(name = "c_name")
    private String cName;
    @Column(name = "age")
    private int age;

    public ChildInfo(String cName, int age) {
        this.cName = cName;
        this.age = age;
    }

    public ChildInfo() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getcName() {
        return cName;
    }

    public void setcName(String cName) {
        this.cName = cName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
