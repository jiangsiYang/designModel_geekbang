package com.design.u050.student;

public class BraveStudent implements IStudent {

    private IStudent student;

    public BraveStudent(IStudent student) {
        this.student = student;
    }

    @Override
    public String character() {
        return ("勇敢的，") + student.character();
    }

    public int charmValue() {
        //勇敢的人+3点魅力
        return student.charmValue() + 3;
    }
}
