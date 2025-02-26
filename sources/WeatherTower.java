package avaj.sources;


public class WeatherTower extends Tower
{

    public String getWeather(Coordinates coordinates)
    {
        return WeatherProvider.getInstance().getCurrentWeather(coordinates);
    }

    public void changeWeather()
    {
        // this will change the weather for all the aircrafts
    }
}