package org.acme;

import com.fasterxml.jackson.databind.JsonMappingException;
import org.wildfly.common.ref.References;

import javax.persistence.*;

@Entity
@Table(name = "Match")
public class Match extends PageRequest {
    @Column(name = "match_id")
    private int matchId;
    @ManyToOne
    // @Column(name = "guest_id")
    @JoinColumn(name = "guestId")
    private Team teamG;
    //@Column(name = "master_id")
    @ManyToOne
    @JoinColumn(name="masterId")
    private Team teamM;
    @Column(name = "date")
    private String date;
    @Column(name = "time")
    private String time;
    @Column(name = "master_score")
    private int masterScore;
    @Column(name = "guest_score")
    private int guestScore;

    public Match(int matchId, String date, String time, int masterScore, int guestScore) {
        this.matchId = matchId;
        this.date = date;
        this.time = time;
        this.masterScore = masterScore;
        this.guestScore = guestScore;
    }

    public int getMatchId() {
        return matchId;
    }

    public void setMatchId(int matchId) {
        this.matchId = matchId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getMasterScore() {
        return masterScore;
    }

    public void setMasterScore(int masterScore) {
        this.masterScore = masterScore;
    }

    public int getGuestScore() {
        return guestScore;
    }

    public void setGuestScore(int guestScore) {
        this.guestScore = guestScore;
    }

    public Match() {

    }

}
