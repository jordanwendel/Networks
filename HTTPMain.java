import java.io.*;
import java.util.*;
import java.net.*;

public class HttpMain
{
    public static void main(String[] args) throws IOException
    {
        //FileManager fileManager = new FileManager();
        int PORT = 80;
        String address = "httpbin.org";
        HashMap<String, String> header = new HashMap<String, String>();

        // Using the message class to get the message 
        Message message = new Message();
        String data = "";
        HttpRequest request = new HttpRequest(data);

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
        request.setMethod(input[0]);
        request.setPath(input[1]);
        System.out.println("");
        

        // ============ Establishing Connection ============ //
        System.out.println("Establishing conenction...");
        Socket socket = new Socket(address, PORT);
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        System.out.println("Connection established"+"\n");


        // ============ SEND REQUEST ============ //
        request.addHeader("Host", "httpbin.org");
        String contentType;
        // IF GET
        if(input[0].equals("GET")) // If GET
        {
            // Set content type
            contentType = "text/html";
        }
        else // If POST
        {
            // Sets data to String passed from Message class
            String m = FileManager.ReadFromFile("message.txt");
            message.setMessage(m);
            data = message.getMessage();
            data = data.toString();
        
            
            contentType = "text/plain"; // Set content type
            
            request.addHeader("Content-Type", contentType); 
            request.addHeader("Content-Length", (Integer.toString(data.length())));
            request.setData(data);
        }

        // Print request header
        System.out.print(request.toString());

        if(input[0].equals("GET"))
        {
            // If GET, don't add the data
            bw.write(request.toString());
        }
        else // If post
        {
            // Add the data into the request header and print the whole header
            request.setData(data);
            bw.write(request.toString());
        }
    
        bw.flush();

        // ============ RESPONSE ============ //
        BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        StringBuffer response = new StringBuffer();

        String line;
        System.out.println("Fetching response...");
        while((line = br.readLine()) != null)
        {
            //System.out.println(line);
            response.append(line + "\n");
        }
        System.out.println(response);

        response.toString();

        // If POST, add content
        if(input[0].equals("POST"))
        {
            header.put("content", response.toString());
            System.out.println("Content: \n" + header.get("content"));
        }
        
        socket.close();
        bw.close();
        br.close();
        cin.close();
    }
}

