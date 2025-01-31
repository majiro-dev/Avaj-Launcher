// 

package avaj.sources;




public class WeatherTower
{

    public String getWeather(Coordinates coordinates)
    {
        return WeatherProvider.getProvider().getCurrentWeather(coordinates);
    }

    public void changeWeather()
    {
        // this will change the weather for all the aircrafts
    }
}