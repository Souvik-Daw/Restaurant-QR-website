package com.example.demo.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "m_user_profile")
public class m_user_profile {

	@Id
	@Column(name = "seq_no")
	int seqNo;
	
	@Column(name = "login_id")
	String loginId;
	
	@Column(name = "pswd")
	String pswd;
	
	@Column(name = "user_name")
	String userName;
	
	@Column(name = "user_age")
	String userAge;
	
	@Column(name = "user_rank")
	String userRank;
	
	@Column(name = "kill_death_ratio")
	String killDeathRatio;
	
	@Column(name = "matches_played")
	String matchPlayed;
	
	@Column(name = "matches_won")
	String matchWon;
	
	@Column(name = "total_headshots")
	String totalHeadshot;
	
	@Column(name = "highest_kill")
	String highestKills;
	
	@Column(name = "accuracy")
	String accuracy;
	
	@Column(name = "is_in_clan")
	String clan;
	
	@Column(name = "score")
	String score;

	public int getSeqNo() {
		return seqNo;
	}

	public void setSeqNo(int seqNo) {
		this.seqNo = seqNo;
	}

	public String getLoginId() {
		return loginId;
	}

	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}

	public String getPswd() {
		return pswd;
	}

	public void setPswd(String pswd) {
		this.pswd = pswd;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserAge() {
		return userAge;
	}

	public void setUserAge(String userAge) {
		this.userAge = userAge;
	}

	public String getUserRank() {
		return userRank;
	}

	public void setUserRank(String userRank) {
		this.userRank = userRank;
	}

	public String getKillDeathRatio() {
		return killDeathRatio;
	}

	public void setKillDeathRatio(String killDeathRatio) {
		this.killDeathRatio = killDeathRatio;
	}

	public String getMatchPlayed() {
		return matchPlayed;
	}

	public void setMatchPlayed(String matchPlayed) {
		this.matchPlayed = matchPlayed;
	}

	public String getMatchWon() {
		return matchWon;
	}

	public void setMatchWon(String matchWon) {
		this.matchWon = matchWon;
	}

	public String getTotalHeadshot() {
		return totalHeadshot;
	}

	public void setTotalHeadshot(String totalHeadshot) {
		this.totalHeadshot = totalHeadshot;
	}

	public String getHighestKills() {
		return highestKills;
	}

	public void setHighestKills(String highestKills) {
		this.highestKills = highestKills;
	}

	public String getAccuracy() {
		return accuracy;
	}

	public void setAccuracy(String accuracy) {
		this.accuracy = accuracy;
	}

	public String getClan() {
		return clan;
	}

	public void setClan(String clan) {
		this.clan = clan;
	}

	public String getScore() {
		return score;
	}

	public void setScore(String score) {
		this.score = score;
	}

}
