package com.bai.zhouyang.tree.bean;

import com.orm.SugarRecord;
import com.zhy.zlib.utils.LogUtils;

public class Place extends SugarRecord {
    String name;
    int height;
    int width;
    int cellTotal;
    int usedCount;
    String placeId;


    public Place() {
    }

    public Place(String name, int height, int width) {
        setPlaceId(System.currentTimeMillis() + "");
        setName(name);
        setHeight(height);
        setWidth(width);
        setCellTotal(height * width);
        setUsedCount(0);
        LogUtils.e("newPlace=", toString());
    }

    @Override
    public String toString() {
        return "Place{" +
                "name='" + name + '\'' +
                ", height=" + height +
                ", width=" + width +
                ", cellTotal=" + cellTotal +
                ", usedCount=" + usedCount +
                ", placeId='" + placeId + '\'' +
                '}';
    }

    public String getPlaceId() {
        return placeId;
    }

    public void setPlaceId(String placeId) {
        this.placeId = placeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getCellTotal() {
        return cellTotal;
    }

    public void setCellTotal(int cellTotal) {
        this.cellTotal = cellTotal;
    }

    public int getUsedCount() {
        return usedCount;
    }

    public void setUsedCount(int usedCount) {
        this.usedCount = usedCount;
    }
}
