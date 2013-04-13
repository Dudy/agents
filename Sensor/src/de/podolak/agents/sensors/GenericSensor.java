package de.podolak.agents.sensors;

import de.podolak.agents.environment.AbstractEnvironment;
import de.podolak.agents.environment.SignalType;

/**
 *
 * @version $version$
 * @author $author$
 */
public class GenericSensor extends AbstractSensor {
    
    private SignalType signalType;

    public GenericSensor(AbstractEnvironment environment) {
        super();
        setEnvironment(environment);
    }
    
    public GenericSensor(AbstractEnvironment environment, SignalType signalType) {
        super();
        setEnvironment(environment);
        
        this.signalType = signalType;
    }
    
    /**
     * @return the signalType
     */
    public SignalType getSignalType() {
        return signalType;
    }

    @Override
    public byte getMeasurement() {
        return getMeasurement(signalType);
    }

    @Override
    public String toString() {
        System.out.println("class                : " + this.getClass());
        System.out.println("class name           : " + this.getClass().getName());
        System.out.println("number of annotations: " + this.getClass().getAnnotations().length);

        return super.toString();
    }

}
