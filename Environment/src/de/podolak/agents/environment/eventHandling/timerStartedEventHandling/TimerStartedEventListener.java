package de.podolak.agents.environment.eventHandling.timerStartedEventHandling;

import java.util.EventListener;

/**
 *
 * @version $version$
 * @author $author$
 */
public interface TimerStartedEventListener extends EventListener {

    public void timerStarted(TimerStartedEvent timerStartedEvent);
}
