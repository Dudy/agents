package de.podolak.agents.environment.eventHandling.environmentObjectMovedEventHandling;

import de.podolak.agents.environment.EnvironmentObject;

/**
 *
 * @version $version$
 * @author $author$
 */
public class EnvironmentObjectMovedEvent {

    private int xStep;
    private int yStep;
    private EnvironmentObject environmentObject;

    public EnvironmentObjectMovedEvent(EnvironmentObject environmentObject, int xStep, int yStep) {
        this.environmentObject = environmentObject;
        this.xStep = xStep;
        this.yStep = yStep;
    }

    /**
     * @return the xStep
     */
    public int getXStep() {
        return xStep;
    }

    /**
     * @param xStep the xStep to set
     */
    public void setXStep(int xStep) {
        this.xStep = xStep;
    }

    /**
     * @return the yStep
     */
    public int getYStep() {
        return yStep;
    }

    /**
     * @param yStep the yStep to set
     */
    public void setYStep(int yStep) {
        this.yStep = yStep;
    }

    /**
     * @return the environmentObject
     */
    public EnvironmentObject getEnvironmentObject() {
        return environmentObject;
    }

    /**
     * @param environmentObject the environmentObject to set
     */
    public void setEnvironmentObject(EnvironmentObject environmentObject) {
        this.environmentObject = environmentObject;
    }
}
