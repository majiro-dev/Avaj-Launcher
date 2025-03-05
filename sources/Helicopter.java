package avaj.sources;

public class Helicopter extends Aircraft
{
    public Helicopter(long id, String name, Coordinates coordinates)
    {
        super(id, name, coordinates);
    }

    @Override
    public void updateConditions()
    {
        String weather = weatherTower.getWeather(coordinates);
        String message = "Helicopter#" + name + "(" + id + "): ";

        switch (weather)
        {
            case "SUN":
                coordinates = new Coordinates(coordinates.getLongitude() + 10, coordinates.getLatitude(), coordinates.getHeight() + 2);
                message += "This is hot.";
                break;
            case "RAIN":
                coordinates = new Coordinates(coordinates.getLongitude() + 5, coordinates.getLatitude(), coordinates.getHeight());
                message += "It's raining. Better watch out for lightings.";
                break;
            case "FOG":
                coordinates = new Coordinates(coordinates.getLongitude() + 1, coordinates.getLatitude(), coordinates.getHeight());
                message += "I can't see anything.";
                break;
            case "SNOW":
                coordinates = new Coordinates(coordinates.getLongitude(), coordinates.getLatitude(), coordinates.getHeight() - 12);
                message += "My rotor is going to freeze!";
                break;
        }

        // Logger.log(message);
        Launcher.printToOutput(message, true);

        if (coordinates.getHeight() <= 0)
        {
            message = "Helicopter#" + name + "(" + id + "): ";
            message += "landing.";
            weatherTower.unregister(this);
            message += "Tower says: Helicopter#" + name + "(" + id + ") unregistered from weather tower.";
            // Logger.log(message);
            Launcher.printToOutput(message, true);
        }

    }
}
