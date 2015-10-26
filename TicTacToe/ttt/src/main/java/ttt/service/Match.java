package ttt.service;

import org.springframework.stereotype.Service;

import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;







import org.springframework.web.context.request.async.DeferredResult;

import ttt.model.Board1;
import ttt.model.Game;

import com.fasterxml.jackson.databind.ObjectMapper;
@Service
public class Match {
	private static final ObjectMapper objectMapper = new ObjectMapper();

    List<Board1> matchList;
   List<String> msgList;
    List<Game> gameList;
    List<Check> checkList;
    Queue<DeferredResult<String>> matchResults;
    Queue<DeferredResult<String>> msgResults;
    boolean flag=true;
  ///  Queue<DeferredResult<String>> gameResults;

    public boolean isFlag() {
		return flag;
	}


	public void setFlag(boolean flag) {
		this.flag = flag;
	}


	public Match()
    {
        matchList = new ArrayList<Board1>();
       msgList = new ArrayList<String>();
        gameList = new ArrayList<Game>();
        checkList = new ArrayList<Check>();
        matchResults = new LinkedList<DeferredResult<String>>();
        msgResults = new LinkedList<DeferredResult<String>>();
    //    gameResults = new LinkedList<DeferredResult<String>>();
    }

 
    public List<Board1> getMatchList() {
		return matchList;
	}


	public void setMatchList(List<Board1> matchList) {
		this.matchList = matchList;
	}


	public List<String> getMsgList() {
		return msgList;
	}


	public void setMsgList(List<String> msgList) {
		this.msgList = msgList;
	}


	public List<Game> getGameList() {
		return gameList;
	}


	public void setGameList(List<Game> gameList) {
		this.gameList = gameList;
	}


	public List<Check> getCheckList() {
		return checkList;
	}


	public void setCheckList(List<Check> checkList) {
		this.checkList = checkList;
	}


	public List<Board1> getMatch()
    {
        return matchList;
    }
    
    
    public void addMatch(Board1 b1)
    {
        matchList.add(b1);
        System.out.println("board added,....");
        processDeferredResultsMatch();
    }
    
    
    public void setMatch(Board1 b1,int i,int j,int k,String move)
    {
        matchList.get(k).setArray(i, j, move);
        System.out.println("board setted,....");
        processDeferredResultsMatch();
    }
    
  
    public void removeMatch( String username )
    {
        matchList.remove( username );
        processDeferredResultsMatch();
    }
   
  
    public void addResultMatch( DeferredResult<String> result )
    {
        matchResults.add( result );
        System.out.println("result added...");
    }

    public void addResultMsg( DeferredResult<String> result )
    {
        msgResults.add( result );
        System.out.println("msg result added...");
    }

    
    public void addMsg(String msg)
    {
        msgList.add(msg);
        System.out.println("msg added,....");
        processDeferredResultsMsg();
    }
    
    private void processDeferredResultsMatch()
    {
    	  String json = "";
          try
          {
              StringWriter sw = new StringWriter();
              objectMapper.writeValue( sw, matchList );
              json = sw.toString();
          }
          catch( Exception e )
          {
             
          }

        // process queued results
       while( !matchResults.isEmpty() )
        {
           DeferredResult<String> result = matchResults.remove();
            result.setResult(json );
            
         
            
        }
        
    }
    
    
    private void processDeferredResultsMsg()
    {
    	  String json = "";
          try
          {
              StringWriter sw = new StringWriter();
              objectMapper.writeValue( sw, msgList );
              json = sw.toString();
              
          }
          catch( Exception e )
          {
             
          }

        // process queued results
       while( !msgResults.isEmpty() )
        {
           DeferredResult<String> result = msgResults.remove();
            result.setResult(json );
     
            
         
            
        }
        
    }

}
