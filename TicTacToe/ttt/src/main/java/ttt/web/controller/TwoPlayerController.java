package ttt.web.controller;

import java.io.StringWriter;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.async.DeferredResult;

import com.fasterxml.jackson.databind.ObjectMapper;

import ttt.model.Board1;
import ttt.model.Game;
import ttt.model.Player;
import ttt.model.dao.UserDao;
import ttt.service.Check;
import ttt.service.Host;
import ttt.service.Join;
import ttt.service.Match;
import ttt.service.MatchedPlayer;

@Controller
public class TwoPlayerController {
	private static final ObjectMapper objectMapper = new ObjectMapper();
	@Autowired
	Host h;

	@Autowired
	Join j;

	@Autowired
	MatchedPlayer matchPlayer;

	@Autowired
	Match m;

	@Autowired
	UserDao userDao;

	@Autowired
	List<Check> c = new ArrayList<Check>();

	Queue<DeferredResult<String>> checkResults = new LinkedList<DeferredResult<String>>();

	@RequestMapping("/user/host.json")
	public String hostJson(ModelMap models) {

		models.put("hosts", h.getHosts());
		return "jsonView";
	}

	@RequestMapping("/user/host.deferred.json")
	@ResponseBody
	public DeferredResult<String> hostDeferred() {
		DeferredResult<String> result = new DeferredResult<String>();
		h.addResultHost(result);
		return result;
	}

	@RequestMapping("/user/host.html")
	public String host(HttpSession session, ModelMap models) {
		//Player p = (Player) session.getAttribute("player");
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String playername = authentication.getName();
		Player p = userDao.getPlayer(playername);
		int a = 99;
		h.addHost(p.getUsername());
		if (j.getJoin().size() != 0) {
			System.out.println("Sum one is to join...");
			h.removeHost(p.getUsername());
			String name = j.getJoin().get(0);
			j.removeJoin(name);
			String naame1 = p.getUsername() + "," + name;
			matchPlayer.addMatchPlayer(naame1);
			models.put("username", name);
			session.setAttribute("waiting", "abcd");
			Board1 b1 = new Board1();
			userDao.addBoard(b1);
			System.out.println("b1 id :" + b1.getBoard_id());
			m.addMatch(b1);
			// m.addMsg(name + " had joined a game...waiting for his move..");
			session.setAttribute("size", m.getMatch().size());
			session.setAttribute("board", new Board1());
			Check c1 = new Check();
			c1.setNames(naame1);
			c1.setBoard(b1);
			c.add(c1);
		//	int cSize = c.size();
			// a = m.getMsg().size();
			System.out.println("board added from host....");
			// return "redirect:host.html";
		} else {
			System.out.println("no one to join..waiting..");
			session.setAttribute("waiting", "a");

		}
		models.put("a", a);
		models.put("p", p);
		return "hostJSP";
	}

	@RequestMapping("/user/join.json")
	public String joinJson(ModelMap models) {

		models.put("join", j.getJoin());
		return "jsonView";
	}

	@RequestMapping("/user/join.deferred.json")
	@ResponseBody
	public DeferredResult<String> joinDeferred() {
		DeferredResult<String> result = new DeferredResult<String>();
		j.addResultJoin(result);
		return result;
	}

	@RequestMapping("/user/join.html")
	public String join(HttpSession session, ModelMap models) {
		//Player p = (Player) session.getAttribute("player");
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String playername = authentication.getName();
		Player p = userDao.getPlayer(playername);
		int a = 99;
		// session.setAttribute("size", m.getMatch().size());
		j.addJoin(p.getUsername());
		if (h.getHosts().size() != 0) {
			System.out.println("Sum one is to host...");
			j.removeJoin(p.getUsername());
			String name = h.getHosts().get(0);
			h.removeHost(name);
			models.put("username", name);
			String naame1 = name + "," + p.getUsername();
			matchPlayer.addMatchPlayer(naame1);
			System.out.println("board added from join....");
			Board1 b1 = new Board1();
			// b1.setArray(1, 0, "X");
			userDao.addBoard(b1);
			System.out.println("b1 id :" + b1.getBoard_id());
			session.setAttribute("size", m.getMatch().size());
			m.addMatch(b1);
			// m.addMsg(name + " had hosted a game...waiting for his move..");
			session.setAttribute("board", new Board1());
			Check c1 = new Check();
			c1.setNames(naame1);
			c1.setBoard(b1);
			c.add(c1);
		//	int cSize = c.size();
			// c1.
			m.setCheckList(c);

			// a = m.getMsg().size();

		} else {
			System.out.println("no one to join..waiting..");
			// session.setAttribute("waiting", );

		}
		models.put("a", a);
		models.put("p", p);
		return "joinJSP";
	}

	@RequestMapping("/user/matchPlayer.json")
	public String matchPlayerJson(ModelMap models) {
		models.put("matchPlayerList", matchPlayer.getMatch());
		// models.put("boardList", m.getMatch());
		return "jsonView";
	}

	@RequestMapping("/user/matchPlayer.deferred.json")
	@ResponseBody
	public DeferredResult<String> matchPlayerDeferred() {

		DeferredResult<String> result = new DeferredResult<String>();
		matchPlayer.addResultMatch(result);
		return result;
	}

	@RequestMapping("/user/checkPlayer.json")
	public String checkPlayerJson(ModelMap models) {
		models.put("checkList", c);
		
		return "jsonView";
	}

	@RequestMapping("/user/checkPlayer.deferred.json")
	@ResponseBody
	public DeferredResult<String> checkPlayerDeferred() {

		DeferredResult<String> result = new DeferredResult<String>();
		addResultCheck(result);
		return result;
	}

	@RequestMapping("/user/board.json")
	public String boardJson(ModelMap models) {
		models.put("boardList", m.getMatch());
		// models.put("msgList", m.getMsg());
		//models.put("flag", m.isFlag());
		return "jsonView";
	}

	@RequestMapping("/user/board.deferred.json")
	@ResponseBody
	public DeferredResult<String> boardDeferred() {
		System.out.println("in result...");
		DeferredResult<String> result = new DeferredResult<String>();
		m.addResultMatch(result);
		return result;
	}

	@RequestMapping(value = "/user/twoplayer")
	public String twoplayer() {

		return "twoplayer";
	}

	boolean flag = true;
	String msg = null;

/*	@RequestMapping(value = "/tic2.html")
	public String tic2(@RequestParam int k, int i, int j, HttpSession session,
			ModelMap models) {
		Player player = (Player) session.getAttribute("player");
		Check check = c.get(k);
		if (i == 99) {

			String string[] = matchPlayer.getMatch().get(k).split(",");
			java.sql.Timestamp date = new java.sql.Timestamp(
					new java.util.Date().getTime());
			Game g1 = new Game();
			g1.setAIplayer(false);
			g1.setStart_time(date);
			g1.setPlayer1(userDao.getPlayer(string[0]));
			g1.setPlayer2(userDao.getPlayer(string[1]));
			// m.addGame(g1);

		} else {
			m.getMatch().size();
		//	Board1 b = m.getMatch().get(k);
			//System.out.println("b :" + b.toString());

			if (check.isFlag()) {
			//	m.setMatch(b, i, j, k, "X");
				check.getBoard().setArray(i, j, "X");
				//processDeferredResultCheck();
				flag = false;
				check.setFlag(false);
				processDeferredResultCheck();
				boolean flag1 = chkWin(check.getBoard(), "X");
				if (flag1 == true) {
					models.put("win", "Player 1 won...");
					String string[] = matchPlayer.getMatch().get(k-1).split(",");
					m.addMsg(string[0] + " won... :) ");
					new java.sql.Timestamp(new java.util.Date().getTime());
					// a = (m.getMsg().size()-1);
					// Game g1 = m.getGame().get(k);
					
					 * g1.setResult("win"); g1.setWinner_name(string[0]);
					 * g1.setEnd_time(date); userDao.addGame(g1);
					 
					// matchPlayer.removeMatch(k);
					flag1 = false;

					// System.out.println("a :"+a);
				}
			} else if (!(check.isFlag())) {
				//m.setMatch(b, i, j, k, "O");
				check.getBoard().setArray(i, j, "X");
				//processDeferredResultCheck();
				flag = true;
				check.setFlag(true);
				processDeferredResultCheck();
				boolean flag1 = chkWin(check.getBoard(), "O");
				if (flag1 == true) {
					models.put("win", "Player 2 won...");
					String string[] = matchPlayer.getMatch().get(k).split(",");
					m.addMsg(string[1] + " won... :) ");
					// a = (m.getMsg().size()-1);
					// Game g1 = m.getGame().get(k);
					
					 * g1.setResult("loss"); g1.setWinner_name(string[1]);
					 * matchPlayer.removeMatch(k); userDao.addGame(g1);
					 
					// System.out.println("a :"+a);
					flag1 = false;

				}
			}

		//	models.put("b1", b);

		}
		models.put("k", k + 1);
		

		//models.put("flag", flag);
		models.put("player", player);
		return "tic2";
	}
*/
	public boolean chkWin(Board1 temp_board, String move) {
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

	private void processDeferredResultCheck() {
		String json = "";

		try {
			StringWriter sw = new StringWriter();
			objectMapper.writeValue(sw, c);
			json = sw.toString();
			// json1 = board.toString();
		} catch (Exception e) {

		}

		// process queued results
		while (!checkResults.isEmpty()) {
			DeferredResult<String> result = checkResults.remove();
			result.setResult(json);
		}

	}

	public void addResultCheck(DeferredResult<String> result) {
		checkResults.add(result);
		System.out.println("check added...");
	}

	@RequestMapping(value = "/user/h2.html")
	public String h2(@RequestParam int k, int i, int j, ModelMap models,HttpSession session) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String playername = authentication.getName();
		Player p = userDao.getPlayer(playername);
		Check check = c.get(k);
		boolean flag = true;
	if(i!=99){
		
		
		check.getBoard().setArray(i, j, "X");
		
		flag = false;
		check.setFlag(flag);
	
		boolean flag1 =chkWin(check.getBoard(), "X");
		Game game = (Game) session.getAttribute("game"); 	
		check.setGame(game);
		if(!(flag1)){
				flag1 = gameTie(check.getBoard());
	
		if (flag1) {
			System.out.println("game tied...");
			check.setMsg("Game Tied... ");
			Timestamp date = new java.sql.Timestamp(new java.util.Date().getTime());
			check.getGame().setEnd_time(date);
			check.getGame().setResult("Tie");
			//check.setGame(new Game());
			userDao.addGame(check.getGame());
		}
		
	
		}
		else{
			models.put("win", "Player 1 won...");
			String string[] = matchPlayer.getMatch().get(k-1).split(",");
			check.setMsg(string[0] + " won... :) ");
		
			Timestamp date = new java.sql.Timestamp(new java.util.Date().getTime());
			check.getGame().setEnd_time(date);
check.getGame().setWinner_name(string[0]);
check.getGame().setResult("win");
			flag1 = false;
			userDao.addGame(check.getGame());
		}
		processDeferredResultCheck();
		models.put("k", k);
		
	}
	else{
		String string[] = matchPlayer.getMatch().get(k).split(",");
		
		Timestamp date = new java.sql.Timestamp(new java.util.Date().getTime());
		check.getGame().setAIplayer(false);
		check.getGame().setPlayer1(userDao.getPlayer(string[0]));
		check.getGame().setPlayer2(userDao.getPlayer(string[1]));
		check.getGame().setStart_time(date);
		//userDao.addGame(check.getGame());
		session.setAttribute("game", check.getGame());
	//	c.set(k, check);
		processDeferredResultCheck();
		models.put("k", k + 1);
	}
		//models.put("flag", flag);
	models.put("p", p);
		return "h2";
	}

	@RequestMapping(value = "/user/j2.html")
	public String j2(@RequestParam int k, int i, int j, ModelMap models) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String playername = authentication.getName();
		Player p = userDao.getPlayer(playername);
		Check check = c.get(k);
		boolean flag = false;
	if(i!=99){
		//m.setMatch(check.getBoard(), i, j, k, "O");
		check.getBoard().setArray(i, j, "O");
		
		flag = true;
		check.setFlag(flag);
	//	processDeferredResultCheck();
		
		boolean flag1 =chkWin(check.getBoard(), "O");
				
		if(!(flag1)){
				
				flag1 =gameTie(check.getBoard()); 
		if (flag1) {
			check.setMsg("Game tied... ");
			Timestamp date = new java.sql.Timestamp(new java.util.Date().getTime());
			check.getGame().setEnd_time(date);
			check.getGame().setResult("Tie");
			userDao.addGame(check.getGame());
		}
		
		
	
		}
		else{
			models.put("win", "Player 1 won...");
			String string[] = matchPlayer.getMatch().get(k-1).split(",");
			check.setMsg(string[1] + " won... :) ");
			check.getGame().setResult("loss");
			check.getGame().setWinner_name(string[1]);
			
			Timestamp date = new java.sql.Timestamp(new java.util.Date().getTime());
			check.getGame().setEnd_time(date);

			flag1 = false;
			userDao.addGame(check.getGame());
		}
		processDeferredResultCheck();
		models.put("k", k);
	}
	else
	{
		String string[] = matchPlayer.getMatch().get(k).split(",");
	//	Game g = new Game();
		Timestamp date = new java.sql.Timestamp(new java.util.Date().getTime());
		check.getGame().setStart_time(date);
		check.getGame().setPlayer1(userDao.getPlayer(string[0]));
		check.getGame().setPlayer2(userDao.getPlayer(string[1]));
		check.getGame().setAIplayer(false);
		c.set(k, check);
	//	check.setGame(g);
		processDeferredResultCheck();
		models.put("k", k + 1);
	}
		models.put("flag", flag);
	
		System.out.println("k :"+k);
		models.put("p", p);
		
		return "j2";
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
