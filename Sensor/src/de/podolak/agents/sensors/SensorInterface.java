package de.podolak.agents.sensors;

import de.podolak.agents.environment.AbstractEnvironment;

/**
 *
 * @version $version$
 * @author $author$
 */
public interface SensorInterface {
    void setEnvironment(AbstractEnvironment environment);
    byte getMeasurement();
}
