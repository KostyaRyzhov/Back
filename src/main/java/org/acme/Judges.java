package org.acme;

import javax.persistence.*;

@Entity
@Table
public class Judges {

    @ManyToOne
    @JoinColumn(name = "matchName")
    private Match match;

    @OneToMany
    @JoinColumn(name = "judgeName")
    private Judge judge;

    public Judges() {
    }

    public Judges(Match match, Judge judge) {
        this.match = match;
        this.judge = judge;
    }

    public Match getMatch() {
        return match;
    }

    public void setMatch(Match match) {
        this.match = match;
    }

    public Judge getJudge() {
        return judge;
    }

    public void setJudge(Judge judge) {
        this.judge = judge;
    }
}
