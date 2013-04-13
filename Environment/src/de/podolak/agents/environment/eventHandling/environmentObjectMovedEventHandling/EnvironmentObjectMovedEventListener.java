package de.podolak.agents.environment.eventHandling.environmentObjectMovedEventHandling;

import java.util.EventListener;

/**
 *
 * @version $version$
 * @author $author$
 */
public interface EnvironmentObjectMovedEventListener extends EventListener {

    public void environmentObjectMoved(EnvironmentObjectMovedEvent agentMovedEvent);
}
