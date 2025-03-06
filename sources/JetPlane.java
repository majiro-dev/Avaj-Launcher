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
                message += "This is the hottest day in the history of hot days!";
                break;
            case "RAIN":
                coordinates = new Coordinates(coordinates.getLongitude(), coordinates.getLatitude() + 5, coordinates.getHeight());
                message += "The rain will cleanse the world of its sins.";
                break;
            case "FOG":
                coordinates = new Coordinates(coordinates.getLongitude(), coordinates.getLatitude() + 1, coordinates.getHeight());
                message += "In the fog, you never know what's coming.";
                break;
            case "SNOW":
                coordinates = new Coordinates(coordinates.getLongitude(), coordinates.getLatitude(), coordinates.getHeight() - 7);
                message += "The snow is falling, and so are my spirits.";
                break;
        }

        if (coordinates.getHeight() > 100)
            coordinates = new Coordinates(coordinates.getLongitude(), coordinates.getLatitude(), 100);
            
        Launcher.printToOutput(message, true);

        if (coordinates.getHeight() <= 0)
        {
            message = "JetPlane#" + name + "(" + id + "): " + "landing.";
            Launcher.printToOutput(message, true);
            message = "Tower says: JetPlane#" + name + "(" + id + ") unregistered from weather tower.";
            weatherTower.unregister(this);
            Launcher.printToOutput(message, true);
        }

    }

    @Override
    public void registerTower(WeatherTower weatherTower)
    {
        this.weatherTower = weatherTower;
        weatherTower.register(this);
        String message = "Tower says: JetPlane#" + name + "(" + id + ") registered to weather tower.";
        Launcher.printToOutput(message, true);
    }
}
