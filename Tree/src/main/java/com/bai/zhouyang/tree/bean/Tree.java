package com.bai.zhouyang.tree.bean;

import com.orm.SugarRecord;
import com.orm.dsl.Unique;

public class Tree extends SugarRecord {

    String name;
    String treeId;
    int status;
    int x;
    int y;
    long age;
    String content;
    String placeId;
    public Tree() {
    }

    public Tree(String name, int status, int x, int y, long age, String content, String placeId) {
        this.name = name;
        this.status = status;
        this.x = x;
        this.y = y;
        this.age = age;
        this.content = content;
        this.placeId = placeId;
    }
    public String getTreeId() {
        return treeId;
    }

    public void setTreeId(String treeId) {
        this.treeId = treeId;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public long getAge() {
        return age;
    }

    public void setAge(long age) {
        this.age = age;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getPlaceId() {
        return placeId;
    }

    public void setPlaceId(String placeId) {
        this.placeId = placeId;
    }
}
