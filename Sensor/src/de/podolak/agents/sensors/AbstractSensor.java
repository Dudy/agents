package de.podolak.agents.sensors;

import de.podolak.agents.environment.AbstractEnvironment;
import de.podolak.agents.environment.EnvironmentObject;
import de.podolak.agents.environment.SignalType;

/**
 * @version $version$
 * @author $author$
 */
public abstract class AbstractSensor implements SensorInterface {

    protected AbstractEnvironment environment;
    protected EnvironmentObject environmentObject;

    public AbstractSensor() {
    }

    public AbstractSensor(EnvironmentObject environmentObject) {
        this.environmentObject = environmentObject;
    }

    public byte getMeasurement(SignalType signalType) {
        byte value = 0;

        if (environment != null) {
            value = environment.measure(environmentObject, signalType);
        }

        return value;
    }

    public abstract byte getMeasurement();

    /**
     * @return the environment
     */
    public AbstractEnvironment getEnvironment() {
        return environment;
    }

    /**
     * @param environment the environment to set
     */
    @Override
    public void setEnvironment(AbstractEnvironment environment) {
        this.environment = environment;
    }
}
