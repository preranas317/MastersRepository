package ttt.model.dao;

import java.sql.Timestamp;
import java.util.Collection;
import java.util.List;

import ttt.model.Authorities;
import ttt.model.Board1;
import ttt.model.Game;
import ttt.model.SavedGame;
import ttt.model.Player;

public interface UserDao {

  
    List<Game> getGamesAgainstAI(Player playerName );
    List<Game> getGames(Player player1);
    List<Game> getGamesAgainstPlayer(Player playerName,Player player2 );
	List<Game> getGamesAIWon(Player player1);
	 List<Game> getGamesplayerWon(Player player1);

    Game addGame(Game game);
	
    List<SavedGame> getSavedGames( Player playerName );
    SavedGame addSavedGame(SavedGame savedGame);
    SavedGame resumeGame(int id);
	
    
	Player getPlayer(String name);
	Player addPlayer(Player player);

	Board1 addBoard(Board1 boards);
	Board1 getBoard(int id);
	SavedGame resumeGame(Board1 boards);
	SavedGame getSavedGames(Timestamp start_time);
	List<Game> getPlayer2(Player player);
	Authorities addAuthority(Authorities a);
	

	
}