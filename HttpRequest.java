import java.util.*;

public class HttpRequest {

    String method;
    String path;
    String version = "1.1";
    HashMap<String, String> RequestHeader = new HashMap<String, String>();
    Message message = new Message();
    String data = message.getMessage();

    void setMethod(String m)
    {/** Set method to "GET" or "POST" */
        method = m;
    }

    void setPath(String p)
    {/**  Set the path to the target resource */
        path = p;
    }

    void setData()
    { /** Add the data for POST requests */
        RequestHeader.put("data", data);
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
        line += "\n";

        return line;
    }


}
