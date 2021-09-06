package org.acme;

import io.quarkus.hibernate.orm.panache.PanacheEntity;

import javax.persistence.*;

@Entity
@Table(name = "Team")
public class Team extends PanacheEntity {
    @OneToMany(mappedBy= "Match")
    @Column(name = "team_id")
    private int teamId;
    @Column(name = "team_name")
    private String teamName;
    @Column(name = "team_town")
    private String teamTown;

    public Team() {

    }

    public Team(int teamId, String teamName, String teamTown) {
        this.teamId = teamId;
        this.teamName = teamName;
        this.teamTown = teamTown;
    }

    public int getTeamId() {
        return teamId;
    }

    public void setTeamId(int teamId) {
        this.teamId = teamId;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public String getTeamTown() {
        return teamTown;
    }

    public void setTeamTown(String teamTown) {
        this.teamTown = teamTown;
    }
}
