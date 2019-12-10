package com.offcn.pojo;

import java.io.Serializable;
import java.util.Date;

public class Person implements Serializable{

    private int pid;
    private String pname;
    private Date pdate;

    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    public String getPname() {
        return pname;
    }

    public void setPname(String pname) {
        this.pname = pname;
    }

    public Date getPdate(Date date) {
        return pdate;
    }

    public void setPdate(Date pdate) {
        this.pdate = pdate;
    }

    public Person(int pid, String pname, Date pdate) {
        this.pid = pid;
        this.pname = pname;
        this.pdate = pdate;
    }

    @Override
    public String toString() {
        return "Person{" +
                "pid=" + pid +
                ", pname='" + pname + '\'' +
                ", pdate=" + pdate +
                '}';
    }
}
