package org.acme;

import io.quarkus.hibernate.orm.panache.PanacheEntity;

import javax.persistence.*;

@Entity
@Table(name = "Judge")
public class Judge extends PanacheEntity {

    @Column(name = "judge_id")
    private int judgeId;
    @Column(name = "fio")
    private String fio;
    @Column(name = "judge_town")
    private String judgeTown;
    @OneToOne(mappedBy = "judge", cascade = CascadeType.ALL)
    private Judges judges;

    public Judge(int judgeId, String fio, String judgeTown) {
        this.judgeId = judgeId;
        this.fio = fio;
        this.judgeTown = judgeTown;
    }

    public Judge(){

    }

    public Judges getJudges() {
        return judges;
    }

    public void setJudges(Judges judges) {
        this.judges = judges;
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
