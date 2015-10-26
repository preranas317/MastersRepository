package ttt.model;

import java.sql.Timestamp;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "games")
public class Game {

    @Id
    @GeneratedValue
    int game_id;
   
   boolean AIplayer ;
   
   @ManyToOne
	@JoinColumn(name = "player1")
	Player player1;
   
  
	String winner_name;
   
   @ManyToOne
	@JoinColumn(name = "player2")
	Player player2;
   
   Timestamp start_time;
   
    Timestamp end_time;
   
   String result;
    
	public int getGame_id() {
		return game_id;
	}
	public void setGame_id(int game_id) {
		this.game_id = game_id;
	}
	public boolean isAIplayer() {
		return AIplayer;
	}
	public void setAIplayer(boolean aIplayer) {
		AIplayer = aIplayer;
	}
	
	public Player getPlayer1() {
		return player1;
	}
	public void setPlayer1(Player player1) {
		this.player1 = player1;
	}
	public Player getPlayer2() {
		return player2;
	}
	public void setPlayer2(Player player2) {
		this.player2 = player2;
	}
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
	public String getWinner_name() {
		return winner_name;
	}
	public void setWinner_name(String winner_name) {
		this.winner_name = winner_name;
	}
	public Timestamp getStart_time() {
		return start_time;
	}
	public void setStart_time(Timestamp start_time) {
		this.start_time = start_time;
	}
	public Timestamp getEnd_time() {
		return end_time;
	}
	public void setEnd_time(Timestamp end_time) {
		this.end_time = end_time;
	}
	
	@Override
	public String toString() {
		return "Game [game_id=" + game_id + ", AIplayer=" + AIplayer
				+ ", player1=" + player1 + ", winner_name=" + winner_name
				+ ", player2=" + player2 + ", start_time=" + start_time
				+ ", end_time=" + end_time + ", result=" + result + "]";
	}
	
    
     
}