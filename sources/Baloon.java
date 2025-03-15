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
                message += "I'm melting! Melting!";
                break;
            case "RAIN":
                coordinates = new Coordinates(coordinates.getLongitude(), coordinates.getLatitude(), coordinates.getHeight() - 5);
                message += "I'm singing in the rain, just singing in the rain!";
                break;
            case "FOG":
                coordinates = new Coordinates(coordinates.getLongitude(), coordinates.getLatitude(), coordinates.getHeight() - 3);
                message += "The fog is so thick, it feels like another world.";
                break;
            case "SNOW":
                coordinates = new Coordinates(coordinates.getLongitude(), coordinates.getLatitude(), coordinates.getHeight() - 15);
                message += "The cold never bothered me anyway.";
                break;
        }
    
        if (coordinates.getHeight() > 100)
            coordinates = new Coordinates(coordinates.getLongitude(), coordinates.getLatitude(), 100);

        Launcher.printToOutput(message, true);

        if (coordinates.getHeight() <= 0)
        {
            message = "Baloon#" + name + "(" + id + "): " + "landing.";
            Launcher.printToOutput(message, true);
            message = "Tower says: Baloon#" + name + "(" + id + ") unregistered from weather tower.";
            weatherTower.unregister(this);
            Launcher.printToOutput(message, true);
        }
    }

    @Override
    public void registerTower(WeatherTower weatherTower)
    {
        this.weatherTower = weatherTower;
        weatherTower.register(this);
        String message = "Tower says: Baloon#" + name + "(" + id + ") registered to weather tower.";
        Launcher.printToOutput(message, true);
    }
}
