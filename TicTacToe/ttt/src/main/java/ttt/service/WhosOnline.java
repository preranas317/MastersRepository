package ttt.service;

import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.context.request.async.DeferredResult;

import com.fasterxml.jackson.databind.ObjectMapper;

import ttt.model.Board1;
import ttt.model.Player;

@Service
public class WhosOnline {

	
	List<Player> l1;
	List<String> hostsList;
	List<String> joinList;
	List<DeferredResult<String>> hosts;
	DeferredResult<Board1> dfResult;
	List<DeferredResult<String>> join;
	private static final ObjectMapper objMapper = new ObjectMapper();
	

	public WhosOnline() {
		super();
		this.l1 = new ArrayList<Player>();
		this.hostsList = new ArrayList<String>();
		this.joinList = new ArrayList<String>();
		this.hosts = new ArrayList<DeferredResult<String>>();
		this.join = new ArrayList<DeferredResult<String>>();
		this.dfResult = new DeferredResult<Board1>();
	}

	public List<Player> getL1() {
		return l1;
	}
	
	public List<DeferredResult<String>> gethosts() {
		return this.hosts;
	}

	public void addPlayer(Player player){
		this.l1.add(player);
		System.out.println("player added...");
		
	}
	
	public void addResult(){
		
		setResult();
		System.out.println("result...");
		
	}
	
	public void addHostsList(String playernme){
		this.hostsList.add(playernme);
		System.out.println("player host added...");
		
	}
	
	public void addJoinList(String playernme){
		this.joinList.add(playernme);
		System.out.println("player join added...");
		
	}
	
	
	public void removePlayer(Player player){
		this.l1.remove(player);
		System.out.println("player removed...");
	}
	
	public void removeHostsList(String playernme){
		this.hostsList.remove(playernme);
		System.out.println("host player removed...");
	}
	
	public void removeJoinList(String playernme){
		this.joinList.remove(playernme);
		System.out.println("join player removed...");
	}
	
	public void addHost(DeferredResult<String> playername){
		hosts.add(playername);
		setHost();
		
		System.out.println("host player added in deferredResult...");
	}
	
	public void addJoin(DeferredResult<String> playername){
		join.add(playername);
		setJoin();
		//setHost();
		System.out.println("join player added in deferredResult...");
	}
	
	public void setResult(){
		
		
	dfResult.setResult(new Board1());
	System.out.println("new board set..");
	}
	
	public void setHost(){
		StringWriter sw = new StringWriter();
		/*List<String> tp = this.getL1();
		Player p1 = tp.get((tp.size()-1));*/
		try {
			objMapper.writeValue(sw, hostsList);
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		String json = sw.toString();
		for(DeferredResult<String> p : hosts){
			p.setResult(json);
		}
		hosts.clear();
	//	dfResult.setResult(json);
	}
	
	public void setJoin(){
		/*List<String> tp = this.getL1();
		Player p1 = tp.get((tp.size()-1));*/
		StringWriter sw = new StringWriter();
		try {
			objMapper.writeValue(sw,joinList);
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		String json = sw.toString();
		for(DeferredResult<String> p : join){
			p.setResult(json);
		}
		join.clear();
	}

	public List<String> getHostsList() {
		return hostsList;
	}

	public void setHostsList(List<String> hostsList) {
		this.hostsList = hostsList;
	}

	public List<String> getJoinList() {
		return joinList;
	}

	public void setJoinList(List<String> joinList) {
		this.joinList = joinList;
	}

	public List<DeferredResult<String>> getHosts() {
		return hosts;
	}

	public void setHosts(List<DeferredResult<String>> hosts) {
		this.hosts = hosts;
	}

	/*public DeferredResult<String> getDfResult() {
		return dfResult;
	}

	public void setDfResult(DeferredResult<String> dfResult) {
		this.dfResult = dfResult;
	}*/

	public List<DeferredResult<String>> getJoin() {
		return join;
	}

	public void setJoin(List<DeferredResult<String>> join) {
		this.join = join;
	}

	public void setL1(List<Player> l1) {
		this.l1 = l1;
	}
}
