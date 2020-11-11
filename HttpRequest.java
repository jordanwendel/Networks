import java.io.IOException;
import java.util.*;

public class HTTPRequest {

    String method;
    String path;
    String version = "HTTP/1.1";
    HashMap<String, String> RequestHeader = new HashMap<String, String>();
    byte[] data;

    public HTTPRequest(String msg)
    {
        //Constructor which takes an entire request message and parses it
        StringTokenizer parse = new StringTokenizer(msg, " ");
        String m = parse.nextToken();
        method = m;
        String fileRequested = parse.nextToken();
        path = fileRequested;
        data = null;
    }

    void setMethod(String m) 
    {/** Set method to "GET" or "POST" */
        method = m;
    }

    String getMethod()
    {
        return method;
    }

    void setPath(String p) 
    {/** Set the path to the target resource */
        path = p;
    }

    String getPath()
    { /** Get the path */
        return this.path;
    }


    public void setData(byte[] d) throws IOException
    { /** Add the data for POST requests */
        data = d;
    }

    void addHeader(String header, String value)
    { /** Add a header to the request */
        RequestHeader.put(header, value);
    }

    String getHeader(String header)
    {
        return "";
    }

    public String toString()
    { /** Prints the request formatted like the requests we saw in class */
        String line = method + " " + path + version + "\n";
        for(Map.Entry<String, String> entry : RequestHeader.entrySet())
        {
            line += entry.getKey() + ": " + entry.getValue() + "\n";
        }

        line += "\n" + data; 

        return line;
    }

}
