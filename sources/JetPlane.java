package avaj.sources;

public class JetPlane extends Aircraft
{
    public JetPlane(long id, String name, Coordinates coordinates)
    {
        super(id, name, coordinates);
    }

    @Override
    public void updateConditions()
    {
        String weather = weatherTower.getWeather(coordinates);
        String message = "JetPlane#" + name + "(" + id + "): ";

        switch (weather)
        {
            case "SUN":
                coordinates = new Coordinates(coordinates.getLongitude(), coordinates.getLatitude() + 10, coordinates.getHeight() + 2);
                message += "This is hot.";
                break;
            case "RAIN":
                coordinates = new Coordinates(coordinates.getLongitude(), coordinates.getLatitude() + 5, coordinates.getHeight());
                message += "It's raining. Better watch out for lightings.";
                break;
            case "FOG":
                coordinates = new Coordinates(coordinates.getLongitude(), coordinates.getLatitude() + 1, coordinates.getHeight());
                message += "I can't see anything.";
                break;
            case "SNOW":
                coordinates = new Coordinates(coordinates.getLongitude(), coordinates.getLatitude(), coordinates.getHeight() - 7);
                message += "My rotor is going to freeze!";
                break;
        }

        if (coordinates.getHeight() > 100)
            coordinates = new Coordinates(coordinates.getLongitude(), coordinates.getLatitude(), 100);
            
        Launcher.printToOutput(message, true);

        if (coordinates.getHeight() <= 0)
        {
            message = "JetPlane#" + name + "(" + id + "): ";
            message += "landing.";
            weatherTower.unregister(this);
            message += "Tower says: JetPlane#" + name + "(" + id + ") unregistered from weather tower.";
            Launcher.printToOutput(message, true);
        }

    }
}
