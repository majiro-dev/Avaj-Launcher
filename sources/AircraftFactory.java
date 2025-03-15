package avaj.sources;

public class AircraftFactory 
{
    private static long id_counter = 0;

    public static Flyable newAircraft(String type, String name, Coordinates coordinates) 
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
