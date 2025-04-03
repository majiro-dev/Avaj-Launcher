package sources;

public class Baloon extends Aircraft
{
    public Baloon(long id, String name, Coordinates coordinates)
    {
        super(id, name, coordinates);
    }

    @Override
    public void updateConditions()
    {
        super.updateConditions();
    }

    @Override
    protected String move(String weather)
    {
        String message = "";
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
        return message;
    }

}
