package avaj.sources;

public class Aircraft extends Flyable
{
    protected long id;
    protected String name;
    protected Coordinates coordinates;

    protected Aircraft(long id, String name, Coordinates coordinates)
    {
        this.id = id;
        this.name = name;
        this.coordinates = coordinates;
    }

    @Override
    public void updateConditions()
    {
        String weather = weatherTower.getWeather(coordinates);
        String message = this.getClass().getSimpleName() + "#" + name + "(" + id + "): ";

        message += move(weather);
        Launcher.printToOutput(message, true);

        landingCheck();
    }

    @Override
    public void registerTower(WeatherTower weatherTower)
    {
        super.registerTower(weatherTower);
        String message = "Tower says: " + this.getClass().getSimpleName() + "#" + name + "(" + id + ") registered to weather tower.";
        Launcher.printToOutput(message, true);
    }

    protected void landingCheck()
    {
        if (coordinates.getHeight() <= 0)
        {
            String message = this.getClass().getSimpleName() + "#" + name + "(" + id + "): " + "landing.";
            message += " Coordinates: " + coordinates.getLongitude() + " " + coordinates.getLatitude() + " " + coordinates.getHeight();
            Launcher.printToOutput(message, true);
            message = "Tower says: " + this.getClass().getSimpleName() + "#" + name + "(" + id + ") unregistered from weather tower.";
            weatherTower.unregister(this);
            Launcher.printToOutput(message, true);
        }
    }

    protected String move(String weather)
    {
        String message = "";
        switch (weather)
        {
            case "SUN":
                coordinates = new Coordinates(coordinates.getLongitude(), coordinates.getLatitude() + 10, coordinates.getHeight() + 2);
                message += "THIS IS HOT";
                break;
            case "RAIN":
                coordinates = new Coordinates(coordinates.getLongitude(), coordinates.getLatitude() + 5, coordinates.getHeight());
                message += "I LOVE RAIN";
                break;
            case "FOG":
                coordinates = new Coordinates(coordinates.getLongitude(), coordinates.getLatitude() + 1, coordinates.getHeight());
                message += "I CAN'T SEE ANYTHING";
                break;
            case "SNOW":
                coordinates = new Coordinates(coordinates.getLongitude(), coordinates.getLatitude(), coordinates.getHeight() - 7);
                message += "I'M COLD HERE HELP";
                break;
        }
        return message;
    }
}
