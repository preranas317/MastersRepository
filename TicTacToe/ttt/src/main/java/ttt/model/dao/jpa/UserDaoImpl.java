package ttt.model.dao.jpa;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import ttt.model.Authorities;
import ttt.model.Board1;
import ttt.model.Game;
import ttt.model.SavedGame;
import ttt.model.Player;
import ttt.model.dao.UserDao;

@Repository
public class UserDaoImpl implements UserDao {

    @PersistenceContext
    private EntityManager entityManager;

	@Override
	public Player getPlayer(String name) {
		
		
		return  entityManager.createQuery( "from Player where username=?",Player.class ).setParameter(1,name).getSingleResult();
	}

	@Override
	public List<Game> getGamesAgainstAI(Player player_name) {
		
		return  entityManager.createQuery("FROM Game where aiplayer is true and  player1=?",Game.class).setParameter(1, player_name).getResultList();
	}

	@Override
	public List<SavedGame> getSavedGames(Player player_name) {
		
		return  entityManager.createQuery(" FROM SavedGame where player_name=? ",SavedGame.class).setParameter(1, player_name).getResultList();
	}

	@Override
	@Transactional
	public Player addPlayer(Player player) {

		
		return entityManager.merge(player);
		
		
	}

	@Override
	@Transactional
	public Game addGame(Game game) {
		
		return entityManager.merge(game);
	}

	@Override
	@Transactional
	public Board1 addBoard(Board1 boards) {
		
		return entityManager.merge(boards);
	}

	@Override
	public Board1 getBoard(int id) {
		
		return entityManager.createQuery("from Board1 where board_id=?",Board1.class).setParameter(1, id).getSingleResult();
	}

	@Override
	public List<Game> getGamesAgainstPlayer(Player player1,Player player2 ) {
		
		return entityManager.createQuery("FROM Game where player1=? and player2=?",Game.class).setParameter(1, player1).setParameter(2, player2).getResultList();
	}

	@Override
	public List<Game> getGames(Player player1) {
		
		return entityManager.createQuery("FROM Game where player1=? or player2=?", Game.class).setParameter(1, player1).setParameter(2, player1).getResultList();
	}

	@Override
	public List<Game> getGamesAIWon(Player player1) {
		
		return entityManager.createQuery("FROM Game where aiplayer is true and player1=? and result='win'", Game.class).setParameter(1, player1).getResultList();
	}

	
	@Override
	public List<Game> getGamesplayerWon(Player player1) {
		
		return entityManager.createQuery("FROM Game where aiplayer is false and player1=? and result='win'", Game.class).setParameter(1, player1).getResultList();
	}
	@Override
	@Transactional
	public SavedGame addSavedGame(SavedGame savedGame) {
		
		return entityManager.merge(savedGame);
	}

	@Override
	public SavedGame resumeGame(int id) {
		
		return entityManager.createQuery("From SavedGame where saved_id=?", SavedGame.class).setParameter(1, id).getSingleResult();
	}

	

	@Override
	public SavedGame resumeGame(Board1 boards) {
		
		return entityManager.createQuery("From SavedGame where saved_id=?", SavedGame.class).setParameter(1, boards).getSingleResult();
	}

	@Override
	public SavedGame getSavedGames(Timestamp start_time) {
		
		return entityManager.createQuery("From SavedGame where start_time=?", SavedGame.class).setParameter(1, start_time).getSingleResult();
	}

	@Override
	public List<Game> getPlayer2(Player player) {
		
		return entityManager.createQuery("From Game where aiplayer = 'f' and player1=? or player2=?",Game.class ).setParameter(1, player).setParameter(2, player).getResultList();
	}

	@Override
	@Transactional
	public Authorities addAuthority(Authorities a) {
		
		return entityManager.merge(a);
	}

    
}