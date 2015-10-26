package ttt.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "players")
public class Player {

	@Id
    @GeneratedValue
    int player_id;
    
    private String username;

    private String password;

     private String email_id;

    private boolean enabled = true;
     
     String authority;
     
    public Player()
    {
    	this.enabled = true;
    }



	public Player(int player_id, String username, String password,
			String email_id, String authority) {
		super();
		this.player_id = player_id;
		this.username = username;
		this.password = password;
		this.email_id = email_id;
		//this.enabled = true;
		this.authority = "ROLE_USER";
	}



	public int getPlayer_id() {
		return player_id;
	}



	public void setPlayer_id(int player_id) {
		this.player_id = player_id;
	}



	public String getEmail_id() {
		return email_id;
	}



	public void setEmail_id(String email_id) {
		this.email_id = email_id;
	}



	public String getUsername() {
		return username;
	}



	public void setUsername(String username) {
		this.username = username;
	}



	public String getPassword() {
		return password;
	}



	public void setPassword(String password) {
		this.password = password;
	}



	public boolean isEnabled() {
		return enabled;
	}



	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}



	public String getAuthority() {
		return authority;
	}



	public void setAuthority(String authority) {
		this.authority = authority;
	}

   
}