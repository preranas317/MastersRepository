package ttt.model;

import java.sql.Timestamp;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "savedGames")
public class SavedGame {

	@Id
	@GeneratedValue
	int saved_id;
	
	 @OneToOne(cascade={CascadeType.ALL})
	 @JoinColumn(name = "board_id")
	   Board1 boards;
	   
	@ManyToOne
	@JoinColumn(name = "player_name")
	Player player_name;

	Timestamp saved_time;
	Timestamp start_time;

	public Timestamp getStart_time() {
		return start_time;
	}

	public void setStart_time(Timestamp start_time) {
		this.start_time = start_time;
	}

	public SavedGame() {
	}

	public int getSaved_id() {
		return saved_id;
	}

	public void setSaved_id(int saved_id) {
		this.saved_id = saved_id;
	}

	

	public Board1 getBoards() {
		return boards;
	}

	public void setBoards(Board1 boards) {
		this.boards = boards;
	}

	public Player getPlayer_name() {
		return player_name;
	}

	public void setPlayer_name(Player player_name) {
		this.player_name = player_name;
	}

	public Timestamp getSaved_time() {
		return saved_time;
	}

	public void setSaved_time(java.sql.Timestamp Saved_time) {
		this.saved_time = Saved_time;
	}

	@Override
	public String toString() {
		return "SavedGame [saved_id=" + saved_id + ", boards=" + boards
				+ ", player_name=" + player_name + ", saved_time=" + saved_time
				+ "]";
	}
	
	

}