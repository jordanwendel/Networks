import java.io.*;
import java.util.*;
import java.net.*;

public class HttpMain
{
    public static void main(String[] args) throws IOException
    {
        int PORT = 80;
        String address = "httpbin.org";
        HashMap<String, String> header = new HashMap<String, String>();
        Message message = new Message();
        String data = message.getMessage();
        data = data.toString();
        HttpRequest request = new HttpRequest();


        // ============ GET AND SET REQUEST ============ //
        BufferedReader cin = new BufferedReader(new InputStreamReader(System.in));
        String [] input = new String[0];
        System.out.print("Enter 'GET <path>' or 'POST <path>' Request: ");
        // Reads input. Splits method and path in an array of Strings
        try 
        {
            input = cin.readLine().split(" ");
        }
        catch (IOException e)
        {
            System.out.println("Could not read input.");
            e.printStackTrace();
        }
        // Setting method and path for Http Request
        request.setMethod(input[0]); // test to see if get or post
        request.setPath(input[1]);
        System.out.println("");
        // ============ Establishing Connection ============ //
        
        System.out.println("Establishing conenction...");
        Socket socket = new Socket("httpbin.org", PORT);
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        System.out.println("Connection established"+"\n");


        // ============ SEND REQUEST ============ //
        request.addHeader("Host", "httpbin.org");
        // if post
        request.addHeader("Content-Type", "text/plain");
        request.addHeader("Content-Length", (data.length());
        // add data

        System.out.println(request.toString());
        bw.write(request.toString());

        bw.write(data);
        bw.flush();

        // ============ RESPONSE ============ //
        BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        // Data input stream

        String line;
        StringBuffer response = new StringBuffer();

        System.out.println("Fetching response...");
        while((line = br.readLine()) != null)
        {
            System.out.println(line);
            response.append(line + "\n");
        }
        System.out.println(response);

       // HttpResponse r = new HttpResponse(response);
    
        request.toString();
        header.put("content", response.toString());
        System.out.println("Header: \n" + header.get("header")+"\n");
        System.out.println("Content: \n" + header.get("content"));
        
        socket.close();
        bw.close();
        br.close();
        cin.close();
    }
}
