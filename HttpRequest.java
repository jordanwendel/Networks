import java.io.IOException;
import java.util.*;

public class HttpRequest {

    String method;
    String path;
    String version = "1.1";
    String data;
    HashMap<String, String> RequestHeader = new HashMap<String, String>();

    public HttpRequest() {} // General constructor

    // Constructor for adding data
    public HttpRequest(String d)
    {
        data = "";
    }

    void setMethod(String m) 
    {/** Set method to "GET" or "POST" */
        method = m;
    }

    void setPath(String p) 
    {/** Set the path to the target resource */
        path = p;
    }

    public void setData(String d) throws IOException
    { /** Add the data for POST requests */
        data = d;
    }

    void addHeader(String header, String value)
    { /** Add a header to the request */
        RequestHeader.put(header, value);
    }

    public String toString()
    { /** Prints the request formatted like the requests we saw in class */
        String line = method + " " + path + " HTTP/" + version + "\n";
        for(Map.Entry<String, String> entry : RequestHeader.entrySet())
        {
            line += entry.getKey() + ": " + entry.getValue() + "\n";
        }

        /**  
         * Append data. 
         * If GET, it will be initialized but empty.
         * POST will show an actual message from Message class.
        */
        line += "\n" + data; 

        return line;
    }


}
