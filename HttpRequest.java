import java.util.*;

public class HttpRequest {

    String method;
    String path;
    String version = "1.1";
    String data;
    HashMap<String, String> RequestHeader = new HashMap<String, String>();
    Message message;

    void setMethod(String m)
    {/** Set method to "GET" or "POST" */
        method = m;
        System.out.println("Method : " + method);
    }

    void setPath(String p)
    {/**  Set the path to the target resource */
        path = p;
        System.out.println("Path :" + path);
    }

    void setData(String d)
    { /** Add the data for POST requests */
        data = d;
    }

    void addHeader(String header, String value)
    { /** Add a header to the request */
        RequestHeader.put(header, value);
    }

    public String toString()
    { /** Prints the request formatted like the requests we saw in class */
        return method + " /" + path + " HTTP/" + version;
    }

    

}
