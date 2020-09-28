
/**
Program: A Message
Author: Jordan Wendel
*/
import java.util.*;
import java.io.*;
import java.net.*;


public class Message 
{

    private Date dateTime;
    private String message;
    private String contentType;
  
    public Message()
    { 
        message = "Default message";
    }

    public Message(Date dateTime, String message, String contentType)
    {
        dateTime = dateTime;
        message = message;
        contentType = contentType;
    }

    public void PrintMessage()
    { /* Function that prints the message */

        System.out.println("\n" + "Here is the message: "+ message);
        System.out.println("The message was sent at: " + dateTime);
        System.out.println("This message was of type " + contentType + "\n");
    }

    // Access to instance variables
    public String getMessage() { return message; }
    public Date getDate() { return dateTime; }
    public String getType() { return contentType; }

    
    // Methods to change the instance data individually
    public void setMessage(String m) { message = m;}
    public void setDate(Date d) { dateTime = d; }
    public void setType(String t) { contentType = t;}


    // ReadFromFile replaced with FileManager version instead of a native function to reduce overall code


    // Specific WriteToFile function different from that in FileManager class. Choosing not to replace this
    public static void WriteToFile(Date date, String message, String type, String fileName) throws IOException
    { /* Function to save the message to a text file */

        File file = new File(fileName);
        
        // If an existing file of fileName has not been created, it will be created
        if (!file.exists()) 
        {
            file.createNewFile();
        }

        // Attempt to write the message to a file 
        try
        {
            FileWriter fw = new FileWriter(fileName);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write("Message details: " + message +"\n");
            bw.write("Sent at: " + date + "\n");
            bw.write("Type: " + type + "\n");
            System.out.println("Successfully wrote to the file. ");
            bw.close();
        }
        catch (IOException e)
        {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
    
    public static void main(String [] args) throws IOException
    { /* Main function */

        // Creating the socket
        Socket socket;
        PrintStream p;
        String hostname;
        int port = 5521;

        // Setting hostname
        if (args.length > 0)
        {
            hostname = args[0];
        }
        else
        {
            hostname = "localhost";
        }
        
        Scanner cin = new Scanner(System.in);
        String fileName;

        // Message type
        //System.out.print("What is the message type? ");
        //String type = cin.next();
        String type = "text";
        
        // Name of the file we will be importing from
        //System.out.print("What is the name of the file you are reading from? ");
        //fileName = cin.next();
        fileName = "message.txt";

        // Inputs message content to file
        String m = FileManager.ReadFromFile(fileName);

        // Date of the message at the time of sending
        Date date = new Date();
    
        // Creating an instance of the Message class
        Message newMessage = new Message();
        newMessage.message = m;
        newMessage.dateTime = date;
        newMessage.contentType = type;

        // Prints message details from the Message class
        newMessage.PrintMessage();

        // Name of the file we will be exporting to
        //System.out.print("What is the name of the output file? ");
        //fileName = cin.next();
        //fileName = "message_output.txt";

        // Outputs message contents to file
        //WriteToFile(newMessage.dateTime, newMessage.message, newMessage.contentType, fileName);


        // SENDING THE MESSAGE
        try
        {
            // Creating the socket over specified port
            socket = new Socket(hostname, port);
            try 
            {
                while(true)
                {
                    // Open the print stream
                    p = new PrintStream(socket.getOutputStream());
                    
                    // Send the message object as bytes over the socket
                    p.println("You have mail: " + "\n" + newMessage.toString()); 
                    socket.close(); 
                }
            }
            catch (IOException e)
            {
                socket.close();
                System.err.println(e);
            }
        }
        catch (IOException e)
        {
            System.err.println(e);
        }

        cin.close();
    }
}
