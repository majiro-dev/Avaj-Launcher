package sources;

public abstract class Flyable
{
    protected WeatherTower weatherTower;

    public abstract void updateConditions();
    
    public void registerTower(WeatherTower weatherTower)
    {
        this.weatherTower = weatherTower;
        weatherTower.register(this);
    }
}