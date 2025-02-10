package avaj.sources;
import java.util.List;

public class Tower 
{
    private List<Flyable> observers;
    
    public void register(Flyable flyable)
    {
        observers.add(flyable);
        Launcher.printToOutput("Tower says: " , true);
    }

    public void unregister(Flyable flyable)
    {
        observers.remove(flyable);
        // write message to output file
    }

    protected void conditionsChanged()
    {
        for (Flyable flyable : observers)
        {
            flyable.updateConditions();
        }
    }
}
