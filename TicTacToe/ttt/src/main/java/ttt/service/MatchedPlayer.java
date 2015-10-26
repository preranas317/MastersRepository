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



import com.fasterxml.jackson.databind.ObjectMapper;
@Service
public class MatchedPlayer {
	private static final ObjectMapper objectMapper = new ObjectMapper();

    List<String> matchPlayerList;
    Queue<DeferredResult<String>> matchPlayerResults;

    public MatchedPlayer()
    {
        matchPlayerList = new ArrayList<String>();
        matchPlayerResults = new LinkedList<DeferredResult<String>>();
    }

    public List<String> getMatch()
    {
        return matchPlayerList;
    }

    public void addMatchPlayer(String username)
    {
        matchPlayerList.add(username);
        System.out.println("board added,....");
        processDeferredResultsMatch();
    }

    public void removeMatch( int index )
    {
        matchPlayerList.remove(index);
        processDeferredResultsMatch();
    }

    public void addResultMatch( DeferredResult<String> result )
    {
        matchPlayerResults.add( result );
    }

    private void processDeferredResultsMatch()
    {
    	  String json = "";
          try
          {
              StringWriter sw = new StringWriter();
              objectMapper.writeValue( sw, matchPlayerList );
              json = sw.toString();
          }
          catch( Exception e )
          {
             
          }

        // process queued results
       while( !matchPlayerResults.isEmpty() )
        {
           DeferredResult<String> result = matchPlayerResults.remove();
            result.setResult(json);
            
         
            
        }
        
    }

    
}
