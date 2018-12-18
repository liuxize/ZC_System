package com.system.po;

import java.util.Date;

public class LessonCustom extends Lesson{


    private String typename;

    private String subjectname;

    public String getTypename() {
        return typename;
    }

    public void setTypename(String typename) {
        this.typename = typename;
    }

    public String getSubjectname() {
        return subjectname;
    }

    public void setSubjectname(String subjectname) {
        this.subjectname = subjectname;
    }
}
