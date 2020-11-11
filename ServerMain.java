import java.net.*;
import java.io.*;
import java.util.*;

public class ServerMain 
{
    public static String docRoot = "./html";
    public static String indexFile = "index.html";
    public static ServerSocket s;
    public static int PORT = 8080;
    public static File file;
    public static HttpResponse res;
    public static HTTPRequest req;
    public static String fileSizeInBytes;
    public static HttpResponse handleRequest(HTTPRequest req) throws FileNotFoundException, IOException
    {

        res = new HttpResponse();
        //System.out.println(req.getPath());
        String[] parts = req.getPath().split("\\.");

        // Setting content type from the request
        if ("png".equals(parts[1]))
        {
            res.addHeader("Content-type", "image/png");
        }
        else if ("html".equals(parts[1]))
        {
            res.addHeader("Content-type", "text/html");
        }
        else if ("css".equals(parts[1]))
        {
            res.addHeader("Content-type", "text/css");
        }
        else if (("jpg".equals(parts[1])) || ("jpeg".equals(parts[1])))
        {
            res.addHeader("Content-type", "image/jpeg");
        }
        else
        {
            res.setCode(404);
        }
        
        res.addHeader("Date", new Date().toString());
        res.addHeader("Server", "myserver");

        if (!"GET".equals(req.getMethod()))
        {
            // Return method not implemented
            res.setCode(501);
            //codeMessages.get(501);
            //System.out.println(res.getCode() + codeMessages.get(501) + "\n");
        }

        file = new File(docRoot + req.getPath());
        if (file.isDirectory())
        { // 200
            // Try adding index.html to it
            file = new File(req.getPath() + indexFile);

            String fileSizeInBytes = Integer.toString((int)file.length());
            String fileLastModDate = res.getHeader("Date").toString();
            String path = parts[1];
            
            res.setCode(200);
            //System.out.println("HTTP/1.1 " + res.getCode() + codeMessages.get(200) + " " + file + "\n");
            //System.out.println("HTTP/1.1 " + res.getCode() + codeMessages.get(200) + "\n");
            //res.addHeader("Content-type", "text/html");
            res.addHeader("Content-length", fileSizeInBytes);
            res.addHeader("Last-modified", fileLastModDate);
            res.setData(file); // Tell it which file to send

        }
        else if (file.exists())
        { // 200
            fileSizeInBytes = Integer.toString((int)file.length());
            String fileLastModDate = res.getHeader("Date").toString();
            String path = parts[1];

            res.setCode(200);
            //System.out.println("HTTP/1.1 " + res.getCode() + codeMessages.get(200) + docRoot + req.getPath() + "\n");
            //System.out.println("HTTP/1.1 " + res.getCode() + codeMessages.get(200) + "\n");
            //res.addHeader("Content-type", "text/html");
            res.addHeader("Content-length", fileSizeInBytes);
            res.addHeader("Last-modified", fileLastModDate);
            res.setData(file); // Tell it which file to send
        }
        else 
        {
            // Build a response 
            res.setCode(404);
            //System.out.println("HTTP/1.1 " + res.getCode() + codeMessages.get(404) + docRoot + req.getPath() + "\n");
        }

        return res;
    }

    public static String getLog()
    {
        String date = res.getHeader("Date").toString();
        String m = req.getMethod();
        String p = req.getPath();
        String c = Integer.toString(res.getCode());
        String cLength = fileSizeInBytes;
        String line = date + " " + m + " " + p + " " + c + " " + cLength;

        return line;
    }

    public static void main(String[] args) throws IOException
    {
        //s = new ServerSocket(PORT);
        s = new ServerSocket(PORT, 10, InetAddress.getByName("10.131.17.2"));
        
        while (true)
        {
            //byte[] buffer = new byte[2048];
            Socket client = s.accept();
            String ip = s.getInetAddress().getHostAddress(); 

            //System.out.println("Connection accepted.\n");
            BufferedReader br = new BufferedReader(new InputStreamReader(client.getInputStream()));
            
            // Read all lines of the request in a loop 
            String msg = "";
            String line;
            while (((line = br.readLine()) != null && !line.isEmpty()))
            {
				msg += line + "\n";
            }

            // Pass the received string to the constructor
            req = new HTTPRequest(msg);
            res = handleRequest(req);
        
            // Send bytes to the socket
            DataOutputStream dos = new DataOutputStream(client.getOutputStream());
            dos.write(res.getBytes()); // Returns a byte[]
            System.out.println(ip + " " + getLog());
            

            try {
                // Closing streams and sockets
                dos.close();
                client.close();
                //System.out.println("Connection closed.\n");

            } catch (Exception e) {
                System.err.println("Error closing stream : " + e.getMessage());
            }
        }
    }
}
