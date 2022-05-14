package com.design.u050.student;

/**
 * 一个男孩
 */
public class BoyStudent implements IStudent {
    private int charm;

    public int getCharm() {
        return charm;
    }

    public void setCharm(int charm) {
        this.charm = charm;
    }

    public String character() {
        return "男孩学生";
    }

    @Override
    public int charmValue() {
        return charm;
    }
}
