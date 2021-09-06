package org.acme;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "Judge")
public class Judge {

    @Column(name = "judge_id")
    private int judgeId;
    @Column(name = "fio")
    private String fio;
    @Column(name = "judge_town")
    private String judgeTown;

    public Judge(int judgeId, String fio, String judgeTown) {
        this.judgeId = judgeId;
        this.fio = fio;
        this.judgeTown = judgeTown;
    }

    public Judge(){

    }

    public int getJudgeId() {
        return judgeId;
    }

    public void setJudgeId(int judgeId) {
        this.judgeId = judgeId;
    }

    public String getFio() {
        return fio;
    }

    public void setFio(String fio) {
        this.fio = fio;
    }

    public String getJudgeTown() {
        return judgeTown;
    }

    public void setJudgeTown(String judgeTown) {
        this.judgeTown = judgeTown;
    }
}
