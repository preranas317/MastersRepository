package ttt.web.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Random;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import ttt.model.Board1;
import ttt.model.Game;
import ttt.model.Player;
import ttt.model.SavedGame;
import ttt.model.dao.UserDao;

@Controller
public class TicTacToeController {

	@Autowired
	UserDao userDao;

	int cntx = 10;
	String winner;
	// Game game = new Game();
	Board1 boards = null;
	boolean flagX = false; // flag for win X
	boolean flagO = false; // flag for win O
	boolean flagtie = false; // flag for tie
	String m1 = null;
	DateFormat dateFormat = new SimpleDateFormat("yyyy:MM:dd HH:mm:ss");

	// System.out.println("date at login :"+date);

	@RequestMapping(value = "/tic.html", method = RequestMethod.GET)
	public String view(@RequestParam int cnt, ModelMap models,
			HttpSession session) {

	//s	String user = null;
		Game game = new Game();
		Player player = (Player) session.getAttribute("player");
		/*if(player!=null)
			user = player.getUsername();
		System.out.println("user :" + user);
		models.addAttribute("user", user);*/

		if (cnt == 0 || cnt == 10) {
		//	m1 = (String) session.getAttribute("m1");
			m1 = (String) models.get("m1");
			System.out.println("m1 :"+m1);
			if(player!=null){
			if (cnt == 10 && (m1 == null)) {
				game = (Game) session.getAttribute("game");
				System.out.println("in here...");
				java.sql.Timestamp date = new java.sql.Timestamp(
						new java.util.Date().getTime());
				winner = "AI";
				game.setWinner_name(winner);
				game.setEnd_time(date);
				game.setResult("loss");
				userDao.addGame(game);
			}
			
			}
			
			@SuppressWarnings("unused")
			DateFormat dateFormat = new SimpleDateFormat("yyyy:MM:dd HH:mm:ss");
			java.sql.Timestamp date = new java.sql.Timestamp(
					new java.util.Date().getTime());
			System.out.println("date at login :" + date);
			DateFormat dateFormat1 = new SimpleDateFormat("MM");
			String month = dateFormat1.format(new java.util.Date());
			System.out.println("month :" + month);
			
			if(player!=null){
				game.setStart_time(date);
				game.setPlayer1(player);
				System.out.println(game.toString());
				game.setAIplayer(true);
				session.setAttribute("game", game);
				models.put("game", game);}
				boards = new Board1();
				models.addAttribute("boards", boards);
				m1 = null;
		//	System.out.println("game :" + game.toString());
				session.setAttribute("s1", null);
				session.setAttribute("m1", m1);
			//	models.put("m1",null);
				models.addAttribute("m1", null);
			
		} else {
			if (cnt == 3) {
				// Game game=(Game) models.get("game");
				SavedGame s1 = (SavedGame) session.getAttribute("s1");
				game.setStart_time(s1.getStart_time());
				game.setAIplayer(true);
				game.setPlayer1(player);
				m1 = null;
				// game.setStart_time(date);
				session.setAttribute("game", game);
				if (s1.getBoards() != null) {
					boards = s1.getBoards();
					System.out.println("in tic...");
					for (int i = 0; i < 3; i++) {
						for (int j = 0; j < 3; j++) {
							System.out.println("b1[" + i + "][" + j + "] :"
									+ boards.getSarray()[i][j]);
						}
					}
				}
			}
			session.setAttribute("m1", m1);
		//	models.put("m1",m1);
			models.addAttribute("m1", m1);
			models.addAttribute("boards", boards);
		}
		System.out.println("in get..");
		return "tic";
	}

	@RequestMapping(value = "/tic1.html", method = RequestMethod.GET)
	public String view(@RequestParam int i, int j, ModelMap models,
			HttpSession session) {

		Game game = (Game) session.getAttribute("game");
		if (game != null) {
			System.out.println(game.toString());
			if (!game.isAIplayer())
				game.setPlayer2(null);
		}

		// cntx++;
		boolean flag = false;
		// Board1 boards1=(Board1) models.get(boards);
		boards.setValue(i, j, "X");
		flag = chkWin("X", boards);
		if (flag == false) {
			boolean temp = false;
			// AI move
			temp = makeAIMove(boards, "O");
			if (temp == false) {
				temp = makeAIMove(boards, "X");
				// No 1 is winning...
				if (temp == false && !(gameTie(boards))) {
					random(boards);
				}
			}
			flagO = chkWin("O", boards);
			if (flagO == false) {
				// either next move by player or tie..
				flagtie = gameTie(boards);
				if (flagtie == true) {
					m1 = "Game tied...Try Again !";
					models.addAttribute("m1", m1);
					System.out.println("Game tied...Try Again !");
					winner = null;
					if(game!=null){
						game.setWinner_name(null);
					java.sql.Timestamp date = new java.sql.Timestamp(
							new java.util.Date().getTime());
					game.setEnd_time(date);
					game.setResult("Tie");
					userDao.addGame(game);}
					SavedGame a1 = (SavedGame) session.getAttribute("s1");
					if (a1 != null) {
						System.out.println("game was in saved Game table");
						SavedGame s1 = userDao.getSavedGames(game
								.getStart_time());
						s1.setSaved_time(null);
						s1.setBoards(null);
						s1.setPlayer_name(null);
						s1.setSaved_time(null);
						s1.setStart_time(null);
						userDao.addSavedGame(s1);
						System.out.println("game at d end:" + game.toString());
					}
					
				}
				System.out.println("flagtie :" + flagtie);
			} else {
				m1 = "I won!";
				models.addAttribute("m1", m1);
				System.out.println("I won!");
				winner = "AI";
				if(game!=null){
				game.setWinner_name(winner);
				game.setResult("loss");

				java.sql.Timestamp date = new java.sql.Timestamp(
						new java.util.Date().getTime());
				game.setEnd_time(date);
				userDao.addGame(game);}
				SavedGame a1 = (SavedGame) session.getAttribute("s1");
				if (a1 != null) {
					System.out.println("game was in saved Game table");
					SavedGame s1 = userDao.getSavedGames(game.getStart_time());
					s1.setSaved_time(null);
					s1.setBoards(null);
					s1.setPlayer_name(null);
					s1.setSaved_time(null);
					s1.setStart_time(null);
					userDao.addSavedGame(s1);
					System.out.println("game at d end:" + game.toString());
				}
			}
		} else {
			System.out.println("Player won");
			m1 = "You won!";
			models.addAttribute("m1", m1);
if(game!=null){
			winner = game.getPlayer1().getUsername();
			game.setWinner_name(winner);

			java.sql.Timestamp date = new java.sql.Timestamp(
					new java.util.Date().getTime());
			game.setEnd_time(date);
			game.setResult("win");
			userDao.addGame(game);}
			SavedGame a1 = (SavedGame) session.getAttribute("s1");
			if (a1 != null) {
				System.out.println("game was in saved Game table");
				SavedGame s1 = userDao.getSavedGames(game.getStart_time());
				s1.setSaved_time(null);
				s1.setBoards(null);
				s1.setPlayer_name(null);
				s1.setSaved_time(null);
				s1.setStart_time(null);
				userDao.addSavedGame(s1);
				System.out.println("game at d end:" + game.toString());
			}
			
		}
		// models.addAttribute("m1", m1);
		session.setAttribute("boards", boards);
		// models.addAttribute("boards", boards);
		System.out.println("winner :" + winner);
		session.setAttribute("m1", m1);
		models.put("m1", m1);
		models.addAttribute("m1", m1);
		return "redirect:tic.html?cnt=" + (cntx + 1);

	}

	private boolean chkWin(String move, Board1 temp_board) {
		System.out.println("in chkwin...");
		for (int a = 1; a < 2; a++) {
			for (int b = 1; b < 2; b++) {
				if (temp_board.getValue(a, b).equalsIgnoreCase(move)
						&& temp_board.getValue((a + 1), b).equalsIgnoreCase(
								move)
						&& temp_board.getValue((a - 1), b).equalsIgnoreCase(
								move)) {
					return true;
				} else if (temp_board.getValue(a, b).equalsIgnoreCase(move)
						&& temp_board.getValue(a, (b + 1)).equalsIgnoreCase(
								move)
						&& temp_board.getValue(a, (b - 1)).equalsIgnoreCase(
								move)) {
					return true;
				} else if (temp_board.getValue(a, b).equalsIgnoreCase(move)
						&& temp_board.getValue((a + 1), (b - 1))
								.equalsIgnoreCase(move)
						&& temp_board.getValue((a - 1), (b + 1))
								.equalsIgnoreCase(move)) {
					return true;
				} else if (temp_board.getValue(a, b).equalsIgnoreCase(move)
						&& temp_board.getValue((a + 1), (b + 1))
								.equalsIgnoreCase(move)
						&& temp_board.getValue((a - 1), (b - 1))
								.equalsIgnoreCase(move)) {
					return true;
				}
			}
		}
		for (int d = 1; d < 2; d++) {
			if (temp_board.getValue(0, d).equalsIgnoreCase(move)
					&& temp_board.getValue(0, (d - 1)).equalsIgnoreCase(move)
					&& temp_board.getValue(0, (d + 1)).equalsIgnoreCase(move)) {
				return true;
			} else if (temp_board.getValue(2, d).equalsIgnoreCase(move)
					&& temp_board.getValue(2, (d - 1)).equalsIgnoreCase(move)
					&& temp_board.getValue(2, (d + 1)).equalsIgnoreCase(move)) {
				return true;
			}
		}

		for (int c = 1; c < 2; c++) {
			if (temp_board.getValue(c, 0).equalsIgnoreCase(move)
					&& temp_board.getValue((c - 1), 0).equalsIgnoreCase(move)
					&& temp_board.getValue((c + 1), 0).equalsIgnoreCase(move)) {
				return true;
			} else if (temp_board.getValue(c, 2).equalsIgnoreCase(move)
					&& temp_board.getValue((c - 1), 2).equalsIgnoreCase(move)
					&& temp_board.getValue((c + 1), 2).equalsIgnoreCase(move)) {
				return true;
			}
		}

		return false;
	}

	public boolean makeAIMove(Board1 temp_board, String move) {
		int cnta = 0;
		int cntb = 0;
		for (int a = 0; a < 3; a++) {
			cnta = 0;
			cntb = 0;
			for (int b = 0; b < 3; b++) {
				if (temp_board.getValue(a, b).equalsIgnoreCase(move)) {
					cnta++;
				}
				if (temp_board.getValue(b, a).equalsIgnoreCase(move)) {
					cntb++;
					System.out.println("cntb :" + cntb);
				}
			}
			if (cnta == 2) {
				for (int b = 0; b < 3; b++) {
					if (temp_board.getValue(a, b).equalsIgnoreCase("___")) {
						temp_board.setArray(a, b, "O");
						return true;
					}
				}
			}
			if (cntb == 2) {
				for (int b = 0; b < 3; b++) {
					if (temp_board.getValue(b, a).equalsIgnoreCase("___")) {
						temp_board.setArray(b, a, "O");
						return true;
					}
				}
			}
		}
		System.out.println("out of the loop...");
		// diagonal
		cnta = 0;
		for (int a = 0; a < 3; a++) {
			if (temp_board.getValue(a, a).equalsIgnoreCase(move)) {
				cnta++;
			}
		}
		if (cnta == 2) {
			for (int b = 0; b < 3; b++) {
				if (temp_board.getValue(b, b).equalsIgnoreCase("___")) {
					temp_board.setArray(b, b, "O");
					return true;
				}
			}
		}
		cnta = 0;
		int a = 0;
		int b = 2;
		if (temp_board.getValue(a, b).equalsIgnoreCase(move))
			cnta++;
		if (temp_board.getValue((a + 1), (b - 1)).equalsIgnoreCase(move))
			cnta++;
		if (temp_board.getValue((a + 2), (b - 2)).equalsIgnoreCase(move))
			cnta++;
		System.out.println("cnta in diagonal... :" + cnta);
		if (cnta == 2) {
			if (temp_board.getValue(a, b).equalsIgnoreCase("___")) {
				temp_board.setArray(a, b, "O");
				return true;
			}
			if (temp_board.getValue((a + 1), (b - 1)).equalsIgnoreCase("___")) {
				temp_board.setArray((a + 1), (b - 1), "O");
				return true;
			}
			if (temp_board.getValue((a + 2), (b - 2)).equalsIgnoreCase("___")) {
				temp_board.setArray((a + 2), (b - 2), "O");
				return true;
			}
		}
		return false;
	}

	private void random(Board1 temp_board) {
		Random randomGenerator = new Random();

		int no1 = randomGenerator.nextInt(3);
		int no2 = randomGenerator.nextInt(3);

		if (temp_board.getValue(no1, no2).equalsIgnoreCase("___")) {
			System.out.println("array[" + no1 + "][" + no2 + "]");
			temp_board.setArray(no1, no2, "O");
			return;
		} else
			random(temp_board);
	}

	public boolean gameTie(Board1 temp_board) {

		for (int a = 0; a < 3; a++) {
			for (int b = 0; b < 3; b++) {

				if (temp_board.getValue(a, b).equalsIgnoreCase("___"))
					return false;
			}
		}
		return true;
	}

}
