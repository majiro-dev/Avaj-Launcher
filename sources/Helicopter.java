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

        if (coordinates.getHeight() > 100)
            coordinates = new Coordinates(coordinates.getLongitude(), coordinates.getLatitude(), 100);

        Launcher.printToOutput(message, true);

        if (coordinates.getHeight() <= 0)
        {
            message = "Helicopter#" + name + "(" + id + "): " + "landing.";
            Launcher.printToOutput(message, true);
            message = "Tower says: Helicopter#" + name + "(" + id + ") unregistered from weather tower.";
            weatherTower.unregister(this);
            Launcher.printToOutput(message, true);
        }

    }

    @Override
    public void registerTower(WeatherTower weatherTower)
    {
        this.weatherTower = weatherTower;
        weatherTower.register(this);
        String message = "Tower says: Helicopter#" + name + "(" + id + ") registered to weather tower.";
        Launcher.printToOutput(message, true);
    }
}
