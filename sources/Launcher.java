package avaj.sources;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Launcher 
{
    private static final String OUTPUT_FILE = "output/simulation.txt";
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
        weatherTower = new WeatherTower();

        try 
        {
            reader = new BufferedReader(new FileReader(scenarioFile));
            if (!readScenario(reader))
            {
                reader.close();
                return;
            }
            reader.close();
        
            File outputFolder = new File("output");
            if (!outputFolder.exists()) 
                outputFolder.mkdir();

            for (int i = 0; i < simulations; i++) 
                weatherTower.changeWeather();
        }

        catch (Exception e)
        {
            System.out.println("Error: " + e.getMessage());
            return;
        }
    }

    private static boolean readScenario(BufferedReader reader)
    {
        String line;
        List <Flyable> flyables = new ArrayList<Flyable>();
        AircraftFactory aircraftFactory = AircraftFactory.getInstance();

        try
        {
            simulations = Integer.parseInt(reader.readLine());
            while ((line = reader.readLine()) != null) 
            {
                String[] parts = line.split(" ");
                if (parts.length == 5) 
                {
                    if (!parts[0].matches("Baloon|JetPlane|Helicopter")) 
                        throw new Exception("Invalid aircraft type: " + parts[0]);
                    String type = parts[0];
                    String name = parts[1];
                    int longitude = Integer.parseInt(parts[2]);
                    int latitude = Integer.parseInt(parts[3]);
                    int height = Integer.parseInt(parts[4]);
                    if (height < 0 || height > 100)
                        throw new Exception("Invalid height for: " + name + " " + type);
                    Coordinates coordinates = new Coordinates(longitude, latitude, height);
                    Flyable aircraft = aircraftFactory.newAircraft(type, name, coordinates);
                    flyables.add(aircraft);
                }
                else 
                    throw new Exception("Invalid line: " + line);
            }
        }
        catch (Exception e)
        {
            System.out.println("Error: " + e.getMessage());
            return false;
        }

        for (Flyable aircraft : flyables) 
            aircraft.registerTower(weatherTower);
        return true;
    }

    public static void printToOutput(String s, boolean append)
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
