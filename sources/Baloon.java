package avaj.sources;

public class Baloon extends Aircraft
{
    public Baloon(long id, String name, Coordinates coordinates)
    {
        super(id, name, coordinates);
    }

    @Override
    public void updateConditions()
    {
        String weather = weatherTower.getWeather(coordinates);
        String message = "Baloon#" + name + "(" + id + "): ";

        switch (weather)
        {
            case "SUN":
                coordinates = new Coordinates(coordinates.getLongitude() + 2, coordinates.getLatitude(), coordinates.getHeight() + 4);
                message += "This is hot.";
                break;
            case "RAIN":
                coordinates = new Coordinates(coordinates.getLongitude(), coordinates.getLatitude(), coordinates.getHeight() - 5);
                message += "It's raining. Better watch out for lightings.";
                break;
            case "FOG":
                coordinates = new Coordinates(coordinates.getLongitude(), coordinates.getLatitude(), coordinates.getHeight() - 3);
                message += "I can't see anything.";
                break;
            case "SNOW":
                coordinates = new Coordinates(coordinates.getLongitude(), coordinates.getLatitude(), coordinates.getHeight() - 15);
                message += "My rotor is going to freeze!";
                break;
        }
    
        if (coordinates.getHeight() > 100)
            coordinates = new Coordinates(coordinates.getLongitude(), coordinates.getLatitude(), 100);

        Launcher.printToOutput(message, true);

        if (coordinates.getHeight() <= 0)
        {
            message = "Baloon#" + name + "(" + id + "): ";
            message += "landing.";
            message += "Tower says: Baloon#" + name + "(" + id + ") unregistered from weather tower.";
            weatherTower.unregister(this);
            Launcher.printToOutput(message, true);
        }
    }
}
