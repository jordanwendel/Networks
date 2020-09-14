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
    
    String getHeader(String header)
    { /** Return the value of the specified header */
        return ResponseHeader.get(header);
    }

    String getData(String d) 
    { /** Return just the data from the response */
        return ResponseHeader.get(d);
    }


    public String toString()
    { /** Prints response formatted like we saw in class */
        return "";
    }

}

