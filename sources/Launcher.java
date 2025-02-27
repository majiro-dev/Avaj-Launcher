package avaj.sources;

import java.io.*;
import java.nio.Buffer;
import java.util.ArrayList;
import java.util.List;

public class Launcher 
{
    public static final String OUTPUT_FILE = "output/simulation.txt";
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

            WeatherTower weatherTower = new WeatherTower();
            List<Flyable> aircrafts = readScenario(reader);

            for (Flyable aircraft : aircrafts) 
            {
                aircraft.registerTower(weatherTower);
            }

            int simulations = 10;

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

    private static List<Flyable> readScenario(BufferedReader reader) throws IOException 
    {
        List<Flyable> aircrafts = new ArrayList<>();
        String line;
        AircraftFactory factory = AircraftFactory.getInstance();
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
                aircrafts.add(aircraft);
            }
        }
        return aircrafts;
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
