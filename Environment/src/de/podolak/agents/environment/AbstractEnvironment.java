package de.podolak.agents.environment;

import de.podolak.agents.environment.eventHandling.EventHandler;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * This is a kind of portal into the "world model" of all
 * agent suites.
 *
 * @version $version$
 * @author $author$
 */
public abstract class AbstractEnvironment implements Environment {

    protected EventHandler eventHandler;
    protected List<EnvironmentObject> environmentObjectList;
    protected Random random = new Random();

    public AbstractEnvironment() {
        eventHandler = new EventHandler();
    }

    public void addEnvironmentObject(EnvironmentObject environmentObject) {
        getEnvironmentObjectList().add(environmentObject);
    }

    public void removeEnvironmentObject(EnvironmentObject environmentObject) {
        getEnvironmentObjectList().remove(environmentObject);
    }

    public List<EnvironmentObject> getEnvironmentObjectList() {
        if (environmentObjectList == null) {
            environmentObjectList = new ArrayList<EnvironmentObject>();
        }

        return environmentObjectList;
    }

    public static boolean equal(AbstractEnvironment environment0, AbstractEnvironment environment1) {
        boolean equal = true;

        if (
            environment0 == null && environment1 != null ||
            environment0 != null && environment1 == null) {
            equal = false;
        } else if (environment0 != null) {
            equal = environment0.equals(environment1);
        }

        return equal;
    }

    public abstract byte measure(EnvironmentObject environmentObject, SignalType signalType);

    //TODO schleife EventHandler Methoden durch ?

    public abstract void doAction(Enum type, Object... params);
}
