package ttt.service;

import java.io.StringWriter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

import org.springframework.stereotype.Service;
import org.springframework.web.context.request.async.DeferredResult;

import com.fasterxml.jackson.databind.ObjectMapper;

import ttt.model.Board1;
import ttt.model.Game;

@Service
public class Check {
String names;
Board1 board;
boolean flag=true;
String msg;
Game game;


Queue<DeferredResult<String>> checkResults;
private static final ObjectMapper objectMapper = new ObjectMapper();

public String getNames() {
	return names;
}
public void setNames(String names) {
	this.names = names;
}


public String getMsg() {
	return msg;
}
public void setMsg(String msg) {
	this.msg = msg;
}
public boolean isFlag() {
	return flag;
}
public void setFlag(boolean flag) {
	this.flag = flag;
}
public Check()
{
      checkResults = new LinkedList<DeferredResult<String>>();
      game = new Game();
}



public Game getGame() {
	return game;
}
public void setGame(Game game) {
	this.game = game;
}
public Board1 getBoard() {
	return board;
}
public void setBoard(Board1 board) {
	this.board = board;
}



public void addResultCheck( DeferredResult<String> result )
{
    checkResults.add( result );
    System.out.println("result added...");
}

/*public void addCheck(Check c1)
{
    checkList.add(c1);
    System.out.println("board added,....");
    processDeferredResultsMatch();
}*/

public Queue<DeferredResult<String>> getCheckResults() {
	return checkResults;
}
public void setCheckResults(Queue<DeferredResult<String>> checkResults) {
	this.checkResults = checkResults;
}




/*public void processDeferredResultCheck()
{
	  String json = "";
	 // String json1 = "";
      try
      {
          StringWriter sw = new StringWriter();
          objectMapper.writeValue( sw, names );
          json = sw.toString();
       //   json1 = board.toString();
      }
      catch( Exception e )
      {
         
      }

    // process queued results
   while( !checkResults.isEmpty() )
    {
       DeferredResult<String> result = checkResults.remove();
        result.setResult(json );
    //    result.setResult(json1 );
        
     
        
    }
    
}*/
@Override
public String toString() {
	return "Check [names=" + names + ", board=" + board + ", flag=" + flag
			+ ", checkResults=" + checkResults + "]";
}




}
