package ttt.web.controller;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import ttt.model.Authorities;
import ttt.model.Board1;
import ttt.model.Game;
import ttt.model.Player;
import ttt.model.SavedGame;
import ttt.model.dao.UserDao;

@Controller
public class HomeController {


	@Autowired
	UserDao userDao;
	
	
	
	Game game = new Game();
	SavedGame savedGame = null;

	@RequestMapping("/user/home.html")
	public String home() {

		return "home";
	}

	@RequestMapping("/user/pause.html")
	public String pause(@RequestParam int cnty, HttpSession session) {
	//	Player player = (Player) session.getAttribute("player");
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String playername = authentication.getName();
		Player player = userDao.getPlayer(playername);
		if (cnty == 0) {
			List<SavedGame> gameList = userDao.getSavedGames(player);
			System.out.println("gameList size :" + gameList.size());
			session.setAttribute("gameList", gameList);
			return "pause";
		} else {
			System.out.println("cnty :" + cnty);
			List<SavedGame> l2 = userDao.getSavedGames(player);
			SavedGame s1 = l2.get((cnty - 1));
			Board1 b1 = s1.getBoards();
			for (int i = 0; i < 3; i++) {
				for (int j = 0; j < 3; j++) {
					System.out.println("b1[" + i + "][" + j + "] :"
							+ b1.getSarray()[i][j]);
				}
			}
			session.setAttribute("s1", s1);
			// userDao.removeSavedGame(s1.getSaved_id());
			System.out.println("s1 :" + s1.getSaved_id());
			session.setAttribute("s1", s1);

			System.out.println("in else...");
			return "redirect:tic.html?cnt=3";
		}

	}

	@RequestMapping("/user/pause1.html")
	public String pause1(HttpSession session) {
		System.out.println("in pause1.... to save ur game");
		@SuppressWarnings("unused")
		DateFormat dateFormat = new SimpleDateFormat("yyyy:MM:dd HH:mm:ss");
		Game game = (Game) session.getAttribute("game");
		//System.out.println("game start_time :" + game.getStart_time());
		//Player player = (Player) session.getAttribute("player");
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String playername = authentication.getName();
		Player player = userDao.getPlayer(playername);
		java.sql.Timestamp date = new java.sql.Timestamp(
				new java.util.Date().getTime());
		Board1 boards = (Board1) session.getAttribute("boards");
		// = userDao.getSavedGames(game.getStart_time());
		SavedGame s3 = (SavedGame) session.getAttribute("s1");
		if (s3 != null) {
			savedGame = s3;
		} else {
			savedGame = new SavedGame();
		}
		if(game!=null){
		savedGame.setPlayer_name(player);
		savedGame.setStart_time(game.getStart_time());
		savedGame.setSaved_time(date);
		userDao.addBoard(boards);
		savedGame.setBoards(boards);
		userDao.addSavedGame(savedGame);}
		System.out.println("saved game added in database...");

		return "redirect:home.html";
	}

	@RequestMapping("/user/GameHistory.html")
	public String history(ModelMap models, HttpSession session) {
	//	Player player = (Player) session.getAttribute("player");
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String playername = authentication.getName();
		Player player = userDao.getPlayer(playername);
		List<Game> history = new ArrayList<Game>();
		// all game

		history.addAll(userDao.getGames(player));
		System.out.println("history games :" + history.size());
		models.addAttribute("games", history.size());

		history.clear();

		// game against ai
		history.addAll(userDao.getGamesAgainstAI(player));
		System.out.println("history ai " + history.size());
		models.addAttribute("oneplayer", history.size());
		int oneplayer = history.size();
		history.clear();

		// game won against ai
		history.addAll(userDao.getGamesAIWon(player));
		System.out.println("history AI won size :" + history.size());
		DecimalFormat decimalFormat = new DecimalFormat("0.00");
		int oneplayerwon = history.size();
		int div = oneplayerwon * 100;
		float a = (float) oneplayerwon * 100.0f;
		float div1 = (float) a / (float) oneplayer;
		// double d1 = (double) div1 ;
		System.out.println("d1 :" + decimalFormat.format(div1));
		models.addAttribute("oneplayerwon", decimalFormat.format(div1));
		history.clear();

		// game against human player
		history.addAll(userDao.getPlayer2(player));
		System.out.println("history player2 " + history.size());
		models.addAttribute("twoplayer", history.size());
		int twoplayer = history.size();

		int twoplayerwon = 0;
		// game won againt human player
		for (int i = 0; i < history.size(); i++) {
			if(history.get(i).getResult() !=null)
			if(!(history.get(i).getResult().equalsIgnoreCase("tie"))){
			if (history.get(i).getWinner_name().equalsIgnoreCase(player.getUsername())) {
				twoplayerwon++;
			}
		}
		}
		div = twoplayerwon * 100;
		div1 = (float) div / twoplayer;
		models.addAttribute("twoplayerwon", decimalFormat.format(div1));
		history.clear();

		// history.addAll(userDao.getGamesPlayerWon(player, player2));

		history.addAll(userDao.getGames(player));
		List<Game> l1 = new ArrayList<Game>();
		DateFormat dateFormat1 = new SimpleDateFormat("MM");

		List<String> l2 = new ArrayList<String>();
		for (int i = 0; i < history.size(); i++) {

			String m = dateFormat1.format(history.get(i).getEnd_time());
			String month = dateFormat1.format(new java.util.Date());
			if (m.equalsIgnoreCase(month)) {
				l1.add(history.get(i));
				Timestamp t1 = l1.get(i).getEnd_time();
				long i1 = t1.getTime();
				// System.out.println("i1 :" + i1);

				Timestamp t2 = l1.get(i).getStart_time();
				long i2 = t2.getTime();

				long diff = i1 - i2;

				String seconds = String.valueOf(diff / 1000 % 60);
				String minutes = String.valueOf(diff / (60 * 1000) % 60);
				String hours = String.valueOf(diff / (60 * 60 * 1000) % 24);
				String days = String.valueOf(diff / (24 * 60 * 60 * 1000));
				String time = days + " days " + hours + " hours " + minutes
						+ " minutes " + seconds + " seconds";

				l2.add(time);

			}

		}
		models.addAttribute("user", player.getUsername());
		session.setAttribute("l1", l1);
		session.setAttribute("l2", l2);

		return "GameHistory";
	}

	@RequestMapping(value = "/user/login", method = RequestMethod.GET)
	public String login(ModelMap models) {
		models.put("player", new Player());
		return "login";
	}

	@RequestMapping(value = "/user/login", method = RequestMethod.POST)
	public String login(@RequestParam String username, String password,
			HttpSession session, ModelMap models) {
		Player player = null;
		if (userDao.getPlayer(username).getPassword().equals(password)) {
			player = userDao.getPlayer(username);
			session.setAttribute("player", player);
		}
		if (player == null) {

			return "redirect:login.html";
		} else {

			return "home";
		}
	}

	@SuppressWarnings("unused")
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout(HttpSession session) {

		Game game = (Game) session.getAttribute("game");
		String m1 = (String) session.getAttribute("m1");
		DateFormat dateFormat = new SimpleDateFormat("yyyy:MM:dd HH:mm:ss");
		java.sql.Timestamp date = new java.sql.Timestamp(
				new java.util.Date().getTime());
		if (m1 == null && game != null) {
			game.setResult("loss");
			game.setWinner_name("AI");
			game.setEnd_time(date);
			userDao.addGame(game);
		}
		session.invalidate();

		return "redirect:login.html";
	}

	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public String add(ModelMap models) {
		models.put("player", new Player());

		return "register";
	}

	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public String add(@ModelAttribute("player") Player player,
			BindingResult bindingResult) {

		userDao.addPlayer(player);
		Authorities a = new Authorities();
		a.setUsername(player.getUsername());
		a.setAuthority("ROLE_USER");
		userDao.addAuthority(a);

		return "redirect:/user/home.html";
	}

	@RequestMapping(value = "/user/hello")
	public String hello() {

		//userDao.addPlayer(player);

		return "hello";
	}


}