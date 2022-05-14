package com.design.u050.student;

public class CalmStudent implements IStudent {
    private IStudent student;

    public CalmStudent(IStudent student) {
        this.student = student;
    }

    @Override
    public String character() {
        return "冷静的" + student.character();
    }

    public int charmValue() {
        //冷静的人+2点魅力
        return student.charmValue() + 2;
    }
}
