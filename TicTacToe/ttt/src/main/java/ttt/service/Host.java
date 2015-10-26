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
public class Host {
	private static final ObjectMapper objectMapper = new ObjectMapper();

    List<String> hosts;
   Queue<DeferredResult<String>> hostResults;
   
   
   

    public Host()
    {
        hosts = new ArrayList<String>();
        hostResults = new LinkedList<DeferredResult<String>>();
        
    }

    public List<String> getHosts()
    {
        return hosts;
    }

    public void addHost( String username )
    {
        hosts.add( username );
        processDeferredResultsHost();
    }

    public void removeHost( String username )
    {
        hosts.remove( username );
        System.out.println(username+" is rmoved from host..");
        processDeferredResultsHost();
    }

    public void addResultHost( DeferredResult<String> result )
    {
        hostResults.add( result );
    }

  
    private void processDeferredResultsHost()
    {
        // convert username list to json
        String json = "";
        try
        {
            StringWriter sw = new StringWriter();
            objectMapper.writeValue( sw, hosts );
            json = sw.toString();
        }
        catch( Exception e )
        {
           
        }

        // process queued results
        if( !hostResults.isEmpty() )
        {
            DeferredResult<String> result = hostResults.remove();
            result.setResult( json );
        }
    }


}
