package avaj.sources;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Launcher 
{
    public static final String OUTPUT_FILE = "output/simulation.txt";
    private static int simulations;
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
            if (!readScenario(reader)) 
                return;
        
            File outputFolder = new File("output");
            if (!outputFolder.exists()) 
                outputFolder.mkdir();

            for (int i = 0; i <= simulations; i++) 
                weatherTower.changeWeather();

            reader.close();

            System.out.println("Simulation complete. Output written to " + OUTPUT_FILE);
        }

        catch (IOException e) 
        {
            System.out.println("Error: " + e.getMessage());
            return;
        }
    }

    private static boolean readScenario(BufferedReader reader) throws IOException 
    {
        String line;
        List <Flyable> flyables = new ArrayList<Flyable>();
        weatherTower = new WeatherTower();
        int lineCount = 0;
        while ((line = reader.readLine()) != null) 
        {
            if (lineCount == 0 && !line.matches("\\d+")) 
            {
                System.out.println("First line must be a positive integer");
                return false;
            }
            else if (lineCount == 0) 
            {
                simulations = Integer.parseInt(line);
                lineCount++;
                continue;
            }
            String[] parts = line.split(" ");
            if (parts.length == 5) 
            {
                if (!parts[0].matches("Baloon|JetPlane|Helicopter")) 
                {
                    System.out.println("Invalid aircraft type: " + parts[0]);
                    return false;
                }
                if (!parts[2].matches("\\d+") || !parts[3].matches("\\d+") || !parts[4].matches("\\d+")) 
                {
                    System.out.println("Invalid coordinates: " + parts[2] + ", " + parts[3] + ", " + parts[4]);
                    return false;
                }
                String type = parts[0];
                String name = parts[1];
                int longitude = Integer.parseInt(parts[2]);
                int latitude = Integer.parseInt(parts[3]);
                int height = Integer.parseInt(parts[4]);
                Coordinates coordinates = new Coordinates(longitude, latitude, height);
                Flyable aircraft = AircraftFactory.newAircraft(type, name, coordinates);
                flyables.add(aircraft);
            }
            else 
            {
                System.out.println("Invalid line: " + line);
                return false;
            }
        }

        for (Flyable aircraft : flyables) 
        {
            aircraft.registerTower(weatherTower);
        }
        return true;
    }

    static public void printToOutput(String s, boolean append)
    {
        try {
            PrintWriter writer = new PrintWriter(new FileWriter(OUTPUT_FILE, append));
            writer.println(s);
            writer.close();
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }
    }

}
