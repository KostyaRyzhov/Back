package org.acme;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "Match")
public class Match {
    @Column(name = "match_id")
    private int matchId;
    @Column(name = "guest_id")
    private int guestId;
    @Column(name = "master_id")
    private int masterId;
    @Column(name = "date")
    private String date;
    @Column(name = "time")
    private String time;
    @Column(name = "master_score")
    private int masterScore;
    @Column(name = "guest_score")
    private int guestScore;

    public Match(int matchId, int guestId, int masterId, String date, String time, int masterScore, int guestScore) {
        this.matchId = matchId;
        this.guestId = guestId;
        this.masterId = masterId;
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

    public int getGuestId() {
        return guestId;
    }

    public void setGuestId(int guestId) {
        this.guestId = guestId;
    }

    public int getMasterId() {
        return masterId;
    }

    public void setMasterId(int masterId) {
        this.masterId = masterId;
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
