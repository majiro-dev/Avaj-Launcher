package sources;

public class AircraftFactory 
{
    long id_counter = 0;

    private static AircraftFactory instance;

    private AircraftFactory() {}

    public static AircraftFactory getInstance() 
    {
        if (instance == null) 
        {
            instance = new AircraftFactory();
        }
        return instance;
    }

    public Flyable newAircraft(String type, String name, Coordinates coordinates) 
    {
        Flyable aircraft = null;
        switch (type) 
        {
            case "Baloon":
                aircraft = new Baloon(id_counter++, name, coordinates);
                break;
            case "JetPlane":
                aircraft = new JetPlane(id_counter++, name, coordinates);
                break;
            case "Helicopter":
                aircraft = new Helicopter(id_counter++, name, coordinates);
                break;
        }
        return aircraft;
    }
    
}
