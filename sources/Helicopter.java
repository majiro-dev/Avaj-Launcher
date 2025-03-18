package avaj.sources;

public class Helicopter extends Aircraft
{
    public Helicopter(long id, String name, Coordinates coordinates)
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
                coordinates = new Coordinates(coordinates.getLongitude() + 10, coordinates.getLatitude(), coordinates.getHeight() + 2);
                message += "The sun is a deadly laser.";
                break;
            case "RAIN":
                coordinates = new Coordinates(coordinates.getLongitude() + 5, coordinates.getLatitude(), coordinates.getHeight());
                message += "The rain is here to wash away the past.";
                break;
            case "FOG":
                coordinates = new Coordinates(coordinates.getLongitude() + 1, coordinates.getLatitude(), coordinates.getHeight());
                message += "I can't see a thing in this fog!";
                break;
            case "SNOW":
                coordinates = new Coordinates(coordinates.getLongitude(), coordinates.getLatitude(), coordinates.getHeight() - 12);
                message += "Winter is coming.";
                break;
        }
        return message;
    }

}
