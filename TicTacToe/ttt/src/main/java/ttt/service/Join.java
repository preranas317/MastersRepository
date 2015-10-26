package ttt.service;

import org.springframework.stereotype.Service;

import java.io.StringWriter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;



import org.springframework.web.context.request.async.DeferredResult;

import com.fasterxml.jackson.databind.ObjectMapper;
@Service
public class Join {
	private static final ObjectMapper objectMapper = new ObjectMapper();

    List<String> join;

   

    Queue<DeferredResult<String>> joinResults;

    public Join()
    {
        join = new ArrayList<String>();
        joinResults = new LinkedList<DeferredResult<String>>();
    }

    public List<String> getJoin()
    {
        return join;
    }

    public void addJoin( String username )
    {
        join.add( username );
        processDeferredResultsJoin();
    }

    public void removeJoin( String username )
    {
        join.remove( username );
        System.out.println(username+" is rmoved from join..");
        processDeferredResultsJoin();
    }

    public void addResultJoin( DeferredResult<String> result )
    {
        joinResults.add( result );
    }

    private void processDeferredResultsJoin()
    {
        // convert username list to json
        String json = "";
        try
        {
            StringWriter sw = new StringWriter();
            objectMapper.writeValue( sw, join );
            json = sw.toString();
        }
        catch( Exception e )
        {
           
        }

        // process queued results
        if( !joinResults.isEmpty() )
        {
            DeferredResult<String> result = joinResults.remove();
            result.setResult( json );
        }
    }

}
