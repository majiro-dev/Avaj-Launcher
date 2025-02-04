
package avaj.sources;

public abstract class Flyable
{
    protected WeatherTower weatherTower;

    abstract public void updateConditions();
    
    public void registerTower(WeatherTower weatherTower)
    {
        this.weatherTower = weatherTower;
    }
}