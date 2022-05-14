package com.design.u050.student;

public class GoodnessStudent implements IStudent {

    private IStudent student;

    public GoodnessStudent(IStudent student) {
        this.student = student;
    }

    @Override
    public String character() {
        return "善良的" + student.character();
    }

    public int charmValue() {
        //善良的人+1点魅力
        return student.charmValue() + 1;
    }
}
