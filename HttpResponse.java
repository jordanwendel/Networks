import java.util.*;
import java.io.*;

public class HttpResponse 
{

    String version = " HTTP/1.1";
    int code;
    String message;
    String path;
    byte[] data;

    HashMap<String, String> ResponseHeader = new HashMap<String, String>();


    public HttpResponse() {

    }
    
    void setCode(int c)
    { // Setting the code should set the message too
        code = c;
    }

    int getCode()
    { /** Function for returning the code */
        return code;
    }

    String getHeader(String header)
    { /** Return the value of the specified header */
        return ResponseHeader.get(header);
    }

    void addHeader(String header, String value)
    { //add a header to the response
        ResponseHeader.put(header, value);
    }

    byte[] getData()
    { /** Return just the data from the response */
        return data;
        // setData may take the file contents instead of the path if that works
        // better and sets content-type and content-length appropriately
    }

    void setData(File file) throws IOException
    { /** Function that sets the data in bytes */
        FileInputStream fis = new FileInputStream(file);
        data = new byte[(int)file.length()];
        int read = fis.read(data);
    }
    
    byte[] getBytes() throws IOException
    { /** Function that returns the byte data */
        ByteArrayOutputStream data = new ByteArrayOutputStream();
        data.write(this.toString().getBytes());
        if (this.code == 200)
        {
            data.write(this.data);
        }
        return data.toByteArray();
    }

    HashMap<Integer, String> setResponseMessage()
    {/** Function that returns a hash map filled with codes and messages */
        HashMap<Integer, String> codeMessages = new HashMap<Integer, String>(); 
        codeMessages.put(200, " OK");
        codeMessages.put(404, " Not Found");
        codeMessages.put(501, " Not Implemented");
        codeMessages.put(400, " Bad Request");
        return codeMessages;
    }

    
    public String toString() 
    { /** Prints response formatted like we saw in class */
        HashMap<Integer, String> codeMessages = new HashMap<Integer, String>();
        codeMessages = setResponseMessage();    

        String line = "HTTP/1.1 " + code + codeMessages.get(code) + "\n";
        for(Map.Entry<String, String> entry : ResponseHeader.entrySet())
        {
            line += entry.getKey() + ": " + entry.getValue() + "\n";
        }
        line += "\n";

        return line;
    }

}

