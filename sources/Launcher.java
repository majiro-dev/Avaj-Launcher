// this is the main class that will run the simulation
// it will read the file and create the aircrafts and the tower
// it will then run the simulation and write the output to a file
// the file will be named simulation.txt and will be created in the output folder
// if the file already exists it will be overwritten

package avaj.sources;

import java.io.*;

public class Launcher {
    public static void main(String[] args) 
    {
        if (args.length != 1) 
        {
            System.out.println("Usage: java Launcher <scenario file path>");
            return;
        }

        String scenarioFile = args[0];
        BufferedReader reader = null;
        PrintWriter writer = null;

        try 
        {
            reader = new BufferedReader(new FileReader(scenarioFile));
            
            File outputFolder = new File("output");
            if (!outputFolder.exists()) 
            {
                outputFolder.mkdir();
            }

            writer = new PrintWriter(new FileWriter("output/simulation.txt"));

            writer.println("test");

            reader.close();
            writer.close();
        }

        catch (IOException e) 
        {
            System.out.println("Error: " + e.getMessage());
            return;
        }
        
    }
}
