package de.podolak.agents.agent;

import de.podolak.agents.environment.AbstractEnvironment;
import de.podolak.agents.environment.EnvironmentObject;
import de.podolak.agents.sensors.GenericSensor;
import de.podolak.agents.sensors.Sensor;
import java.lang.reflect.Field;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Implementations of this abstract class may have fields of
 * type <code>GenericSensor</code> annotated with the annotation
 * <code>Sensor</code>. The fields will then be instantiated
 * automatically by the constructor with respect to the <code>
 * SignalType</code> defined by the annotation.
 *
 * @version $version$
 * @author $author$
 */
public abstract class AbstractAgent
    extends Thread
    implements Agent, EnvironmentObject {

    protected AbstractEnvironment environment;
    protected boolean actionPermitted = true;
    protected boolean run = true;
    protected Random random = new Random();

    protected AbstractAgent() {
        initSensors();
    }

    /**
     * @return the environment
     */
    public AbstractEnvironment getEnvironment() {
        return environment;
    }

    /**
     * @param environment the environment to set
     */
    public void setEnvironment(AbstractEnvironment environment) {
        if (!AbstractEnvironment.equal(this.environment, environment)) {
            this.environment = environment;
            this.environment.addEnvironmentObject(this);
            initSensors();
        }
    }

    private void initSensors() {
        for (Field field : this.getClass().getDeclaredFields()) {
            if (field.isAnnotationPresent(Sensor.class)) {
                try {
                    field.setAccessible(true);
                    field.set(this, new GenericSensor(environment, field.getAnnotation(Sensor.class).signalType()));
                    field.setAccessible(false);
                } catch (IllegalArgumentException ex) {
                    Logger.getLogger(AbstractAgent.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IllegalAccessException ex) {
                    Logger.getLogger(AbstractAgent.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }
}
