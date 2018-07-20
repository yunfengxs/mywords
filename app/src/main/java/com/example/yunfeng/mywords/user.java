package com.example.yunfeng.mywords;

/**
 * Created by yunfeng on 2018/6/14.
 */

public class user {
    private int id;
    private int age;
    private boolean sex;
    private String name;
    private String address;
    public user(int id,String name)
    {
        this.id=id;
        this.name=name;
    }


    public int getId() { return id;}

    public void setId(int id) {this.age = id;}

    public int getAge() { return age;}

    public void setAge(int age) {this.age = age;}

    public boolean getSex() {
        return sex;
    }

    public void setSex(boolean sex) {
        this.sex = sex;
    }

    public String getDesc() {
        return address;
    }

    public void setDesc(String desc) {
        this.address = desc;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.address = name;
    }
}
