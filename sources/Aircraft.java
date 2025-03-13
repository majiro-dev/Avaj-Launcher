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
    public void registerTower(WeatherTower weatherTower)
    {
        super.registerTower(weatherTower);
        String message = "Tower says: " + this.getClass().getSimpleName() + "#" + name + "(" + id + ") registered to weather tower.";
        Launcher.printToOutput(message, true);
    }

    protected void coordinatesCheck()
    {
        String message = "";

        if (coordinates.getHeight() > 100)
            coordinates = new Coordinates(coordinates.getLongitude(), coordinates.getLatitude(), 100);

        if (coordinates.getHeight() <= 0)
        {
            message = this.getClass().getSimpleName() + "#" + name + "(" + id + "): " + "landing.";
            Launcher.printToOutput(message, true);
            message = "Tower says: " + this.getClass().getSimpleName() + "#" + name + "(" + id + ") unregistered from weather tower.";
            weatherTower.unregister(this);
            Launcher.printToOutput(message, true);
        }
    }

    @Override
    public void updateConditions()
    {
        String weather = weatherTower.getWeather(coordinates);
        String message = this.getClass().getSimpleName() + "#" + name + "(" + id + "): ";
        switch (weather)
        {
            case "SUN":
                coordinates = new Coordinates(coordinates.getLongitude() + 10, coordinates.getLatitude(), coordinates.getHeight());
                message += "THIS IS A LOVELY DAY.";
                break;
            case "RAIN":
                coordinates = new Coordinates(coordinates.getLongitude() + 5, coordinates.getLatitude(), coordinates.getHeight());
                message += "RAIN RAIN GO AWAY.";
                break;
            case "FOG":
                coordinates = new Coordinates(coordinates.getLongitude() + 1, coordinates.getLatitude(), coordinates.getHeight());
                message += "WE CAN HIDE IN THE FOG.";
                break;
            case "SNOW":
                coordinates = new Coordinates(coordinates.getLongitude(), coordinates.getLatitude(), coordinates.getHeight());
                message += "WE SHOULD BUILD A SNOWMAN.";
                break;
        }
        
        Launcher.printToOutput(message, true);
        coordinatesCheck();
    }
}
