import java.io.*;
import java.util.*;
import java.net.*;

public class HTTPMain 
{
    public static void main(String[] args) throws IOException
    {
        int PORT = 80;
        String address = "httpbin.org";
        HashMap<String, String> header = new HashMap<String, String>();
        Message message = new Message();
        String data = message.getMessage();
        HttpRequest request = new HttpRequest();


        // ============ GET AND SET REQUEST ============ //
        BufferedReader cin = new BufferedReader(new InputStreamReader(System.in));\
        String [] input = new String[0];
        System.out.print("Enter 'GET or POST <path>' Request: ");

        // Reads input. Splits method and path in an array of Strings
        try 
        {
            input = cin.readLine().split(" ");
        }
        catch (IOException e)
        {
            e.printStackTrace();;
        }
        // Setting method and path for Http Request
        request.setMethod(input[0]);
        request.setPath(input[1]);


        // ============ Establishing Connection ============ //
        System.out.println("Establishing conenction...");
        Socket socket = new Socket(address, PORT);
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        System.out.println("Connection established");


        // ============ SEND REQUEST ============ //
        bw.write(request.toString());
        System.out.println(request.toString());

        bw.write("Host: " + address);
        bw.write("Content-Length: " + data.length() + "rn");
        bw.write("Content-Type: " + message.getType());
        bw.write("");

        bw.write(data.toString());
        System.out.println("DATA: " + data.toString());
        bw.flush();


        // ============ RESPONSE ============ //
        BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        String ans;
        boolean isDone = false;
        StringBuffer response = new StringBuffer();
        System.out.println("Fetching response...");
        while((ans = br.readLine()) != null)
        {
            response.append(ans + "\n");
            if (ans.isEmpty() && !isDone)
            {
                header.put("header", response.toString());
                isDone = true;
                response = new StringBuffer();
            }
        }

        socket.close();
        bw.close();
        br.close();
        cin.close();
        System.out.println("Connection closed.");
        request.toString();
        header.put("content", response.toString());
        System.out.println("Header: \n" + header.get("header"));
        System.out.println("Content: \n" + header.get("content"));
    }
}
