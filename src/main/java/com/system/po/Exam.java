package com.system.po;

import javafx.scene.chart.PieChart;

import java.util.Date;

public class Exam {
    private Integer examid;

    private Integer stuid;

    private Date examdata;

    private String updateperson;

    private String examtype;

    private Integer mathscore;

    private Integer math;

    private Integer chinese;

    private Integer chinesescore;

    private Integer english;

    private Integer englishscore;

    private  Integer physics;

    private Integer physcore;

    private Integer chemistry;

    private Integer chemscore;

    private Integer politics;  //   政治

    private Integer polscore;

    private Integer history;

    private Integer hisscore;

    private Integer biology;

    private Integer bioscore;

    private Integer geography;

    private Integer geoscore;

    private String examremark; //备注

    private Integer examsign;

    private Date recorddate;

    //getter


    public Integer getExamsign() {
        return examsign;
    }

    public void setExamsign(Integer examsign) {
        this.examsign = examsign;
    }

    public String getUpdateperson() {
        return updateperson;
    }

    public void setUpdateperson(String updateperson) {
        this.updateperson = updateperson;
    }

    public Integer getStuid() {
        return stuid;
    }

    public Integer getExamid() {
        return examid;
    }

    public Date getExamdata() {
        return examdata;
    }

    public String getExamtype() {
        return examtype;
    }

    public Integer getMathscore() {
        return mathscore;
    }

    public Integer getMath() {
        return math;
    }

    public Integer getChinese() {
        return chinese;
    }

    public Integer getChinesescore() {
        return chinesescore;
    }

    public Integer getEnglish() {
        return english;
    }

    public Integer getEnglishscore() {
        return englishscore;
    }

    public Integer getPhysics() {
        return physics;
    }

    public Integer getPhyscore() {
        return physcore;
    }

    public Integer getChemistry() {
        return chemistry;
    }

    public Integer getChemscore() {
        return chemscore;
    }

    public Integer getPolitics() {
        return politics;
    }

    public Integer getPolscore() {
        return polscore;
    }

    public Integer getHistory() {
        return history;
    }

    public Integer getHisscore() {
        return hisscore;
    }

    public Integer getBiology() {
        return biology;
    }

    public Integer getBioscore() {
        return bioscore;
    }

    public Integer getGeography() {
        return geography;
    }

    public Integer getGeoscore() {
        return geoscore;
    }

    public String getExamremark() {
        return examremark;
    }

    //setter

    public void setExamid(Integer examid) {
        this.examid = examid;
    }

    public void setStuid(Integer stuid) {
        this.stuid = stuid;
    }

    public void setExamdata(Date examdata) {
        this.examdata = examdata;
    }

    public void setExamtype(String examtype) {
        this.examtype = examtype;
    }

    public void setMathscore(Integer mathscore) {
        this.mathscore = mathscore;
    }

    public void setMath(Integer math) {
        this.math = math;
    }

    public void setChinese(Integer chinese) {
        this.chinese = chinese;
    }

    public void setChinesescore(Integer chinesescore) {
        this.chinesescore = chinesescore;
    }

    public void setEnglish(Integer english) {
        this.english = english;
    }

    public void setEnglishscore(Integer englishscore) {
        this.englishscore = englishscore;
    }

    public void setPhysics(Integer physics) {
        this.physics = physics;
    }

    public void setPhyscore(Integer physcore) {
        this.physcore = physcore;
    }

    public void setChemistry(Integer chemistry) {
        this.chemistry = chemistry;
    }

    public void setChemscore(Integer chemscore) {
        this.chemscore = chemscore;
    }

    public void setPolitics(Integer politics) {
        this.politics = politics;
    }

    public void setPolscore(Integer polscore) {
        this.polscore = polscore;
    }

    public void setHistory(Integer history) {
        this.history = history;
    }

    public void setHisscore(Integer hisscore) {
        this.hisscore = hisscore;
    }

    public void setBiology(Integer biology) {
        this.biology = biology;
    }

    public void setBioscore(Integer bioscore) {
        this.bioscore = bioscore;
    }

    public void setGeography(Integer geography) {
        this.geography = geography;
    }

    public void setGeoscore(Integer geoscore) {
        this.geoscore = geoscore;
    }

    public void setExamremark(String examremark) {
        this.examremark = examremark;
    }

    public Date getRecorddate() {
        return recorddate;
    }

    public void setRecorddate(Date recorddate) {
        this.recorddate = recorddate;
    }
}
