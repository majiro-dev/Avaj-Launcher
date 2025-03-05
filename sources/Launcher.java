package avaj.sources;

import java.io.*;
import java.nio.Buffer;
import java.util.ArrayList;
import java.util.List;

public class Launcher 
{
    public static final String OUTPUT_FILE = "output/simulation.txt";
    private static int simulations = 0;
    private static WeatherTower weatherTower;

    public static void main(String[] args) 
    {
        if (args.length != 1) 
        {
            System.out.println("Usage: java Launcher <scenario file path>");
            return;
        }

        String scenarioFile = args[0];
        BufferedReader reader = null;

        try 
        {
            reader = new BufferedReader(new FileReader(scenarioFile));
            
            File outputFolder = new File("output");
            if (!outputFolder.exists()) 
            {
                outputFolder.mkdir();
            }

            readScenario(reader);

            for (int i = 0; i < simulations; i++) 
            {
                weatherTower.changeWeather();
            }

            reader.close();
        }

        catch (IOException e) 
        {
            System.out.println("Error: " + e.getMessage());
            return;
        }
        
    }

    private static void readScenario(BufferedReader reader) throws IOException 
    {
        String line;
        AircraftFactory factory = AircraftFactory.getInstance();
        weatherTower = new WeatherTower();
        while ((line = reader.readLine()) != null) 
        {
            String[] parts = line.split(" ");
            if (parts.length == 5) 
            {
                String type = parts[0];
                String name = parts[1];
                int longitude = Integer.parseInt(parts[2]);
                int latitude = Integer.parseInt(parts[3]);
                int height = Integer.parseInt(parts[4]);
                Coordinates coordinates = new Coordinates(longitude, latitude, height);
                Flyable aircraft = factory.newAircraft(type, name, coordinates);
                aircraft.registerTower(weatherTower);
            }
            else if (parts.length == 1) 
            {
                simulations = Integer.parseInt(parts[0]);
            }
        }
    }

    static public void printToOutput(String s, boolean append)
    {
        //appends s to the file

        try {
            PrintWriter writer = new PrintWriter(new FileWriter("output/simulation.txt", append));
            writer.println(s);
            writer.close();
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }
    }

}
