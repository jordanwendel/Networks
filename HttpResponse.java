import java.util.*;
import java.io.*;
import java.net.*;

public class HttpResponse 
{
    String version;
    int code;
    String message;
    String data;

    HashMap<String, String> ResponseHeader = new HashMap<String, String>();

    public HttpResponse(String res)
    {// Constructor
        //Parse into response
        //Split new lines
    }
    
    String getHeader(String header)
    { /** Return the value of the specified header */
        return ResponseHeader.get(header);
    }

    String getData() 
    { /** Return just the data from the response */
        return ResponseHeader.get(data);
    }

    public String toString() { /** Prints response formatted like we saw in class */
        
        String line = "";
        for(Map.Entry<String, String> entry : ResponseHeader.entrySet())
        {
            line += entry.getKey() + ": " + entry.getValue();
        }
        line += "\n";

        return line;
    }

}
