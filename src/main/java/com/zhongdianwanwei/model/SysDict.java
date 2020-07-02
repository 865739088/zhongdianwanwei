package com.zhongdianwanwei.model;

import java.io.PrintWriter;

public class SysDict {
    private int id;
    private String type;
    private String dictName;
    private int dictValue;
    private String dictLabel;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDictName() {
        return dictName;
    }

    public void setDictName(String dictName) {
        this.dictName = dictName;
    }

    public int getDictValue() {
        return dictValue;
    }

    public void setDictValue(int dictValue) {
        this.dictValue = dictValue;
    }

    public String getDictLabel() {
        return dictLabel;
    }

    public void setDictLabel(String dictLabel) {
        this.dictLabel = dictLabel;
    }
}
