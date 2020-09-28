import java.io.*;
import java.util.*;


public class FileManager 
{ /** All things Strings, Text, and Files */

    // =============== Other Classes =============== //
    static Scanner cin = new Scanner(System.in);
    

    // ==================== Functions ==================== //
    public static String ReadFromFile(String fileName) throws IOException
    { /** Function for reading text from a text file */
        String line = "";
        String text = "";

        // Getting name of input file
        // System.out.println("What is the name of the input file? ");
        // String fileName = cin.nextLine();

        System.out.println("Reading information from text file...");
        
        // Read the file using the Scanner class
        File file = new File(fileName);
        BufferedReader fileReader = new BufferedReader(new FileReader(file));

        while((line = fileReader.readLine()) != null) // Reads each individual line until null
        { 
            // Combines each line for the entire message
            text += line + "\n";
        }
        
        fileReader.close();
        System.out.println("Successfully read message! ");
        return text;
    }


    public static void WriteToFile(String fileName, String text) throws IOException
    { /** Function for writing to a text file */
        
        //System.out.println("What is the name of the output file? ");
        //String fileName = cin.nextLine();
        System.out.println("Writing to the file...");
        File file = new File(fileName);
        
        // If an existing file of fileName has not been created, it will be created
        if (!file.exists()) 
        {
            file.createNewFile();
        }
        
        FileWriter fw = new FileWriter(fileName);
        BufferedWriter bw = new BufferedWriter(fw);
        bw.write(text + "\n\n");
        System.out.println("Successfully wrote to the file. ");
        bw.close();
        
    }


    public static void Print(String text)
    { /** Function for printing text */
        System.out.println(text);
    }


    public static String getStringInput()
    {
        System.out.println("Enter desired text: ");
        String input = cin.nextLine();
        return input;
    }


    public static int getIntInput()
    {
        System.out.println("Enter desired integer: ");
        int input = cin.nextInt();
        return input;
    }


    public Map<String, String> CreateHashMap(int numValues, String[] keys, String[] values)
    { /** Function that creates a hash map. Uses a String array for each respective key and value */
        // Assumes each key and value are properly organized in each array

        HashMap<String, String> map = new HashMap<String, String>();
        
        for (int i = 0; i <= numValues; i++)
        { // Put each respective key to its value
            map.put(keys[i], values[i]);
        }

        return map;
    }
}
