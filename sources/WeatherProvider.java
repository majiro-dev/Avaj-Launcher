package avaj.sources;

//singleton class that handles the weather

public class WeatherProvider 
{
    private static WeatherProvider weatherProvider = new WeatherProvider();
    private static String[] weather = {"RAIN", "FOG", "SUN", "SNOW"};

    private WeatherProvider() {}

    public static WeatherProvider getProvider()
    {
        return weatherProvider;
    }

    public String getCurrentWeather(Coordinates coordinates)
    {
        int sum = coordinates.getLongitude() + coordinates.getLatitude() + coordinates.getHeight();
        return weather[sum % 4];
    }
}
