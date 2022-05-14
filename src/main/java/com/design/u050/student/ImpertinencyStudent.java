package com.design.u050.student;

public class ImpertinencyStudent implements IStudent {

    private IStudent student;

    public ImpertinencyStudent(IStudent student) {
        this.student = student;
    }

    @Override
    public String character() {
        return "鲁莽的" + student.character();
    }

    public int charmValue() {
        return student.charmValue() - 1;
    }
}
