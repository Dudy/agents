        package de.podolak.agents.environment.eventHandling;

import de.podolak.agents.environment.eventHandling.environmentObjectMovedEventHandling.EnvironmentObjectMovedEvent;
import de.podolak.agents.environment.eventHandling.environmentObjectMovedEventHandling.EnvironmentObjectMovedEventListener;
import de.podolak.agents.environment.eventHandling.timerStartedEventHandling.TimerStartedEvent;
import de.podolak.agents.environment.eventHandling.timerStartedEventHandling.TimerStartedEventListener;
import javax.swing.event.EventListenerList;

/**
 *
 * @version $version$
 * @author $author$
 */
public class EventHandler {

    private EventListenerList listenerList = new EventListenerList();

    // <editor-fold defaultstate="collapsed" desc=" timer started event handling ">
    public void addTimerStartedEventListener(TimerStartedEventListener timerStartedEventListener) {
        listenerList.add(TimerStartedEventListener.class, timerStartedEventListener);
    }

    public void removeTimerStartedEventListener(TimerStartedEventListener timerStartedEventListener) {
        listenerList.remove(TimerStartedEventListener.class, timerStartedEventListener);
    }

    public void fireTimerStarted(TimerStartedEvent timerStartedEvent) {
        // Guaranteed to return a non-null array
        Object[] listeners = listenerList.getListenerList();
        // Process the listeners last to first, notifying
        // those that are interested in this event
        for (int i = listeners.length - 2; i >= 0; i -= 2) {
            if (listeners[i] == TimerStartedEventListener.class) {
                ((TimerStartedEventListener) listeners[i + 1]).timerStarted(timerStartedEvent);
            }
        }
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc=" environment object moved event handling ">
    public void addEnvironmentObjectMovedEventListener(EnvironmentObjectMovedEventListener timerStartedEventListener) {
        listenerList.add(EnvironmentObjectMovedEventListener.class, timerStartedEventListener);
    }

    public void removeEnvironmentObjectMovedEventListener(EnvironmentObjectMovedEventListener timerStartedEventListener) {
        listenerList.remove(EnvironmentObjectMovedEventListener.class, timerStartedEventListener);
    }

    public void fireEnvironmentObjectMoved(EnvironmentObjectMovedEvent environmentObjectMovedEvent) {
        // Guaranteed to return a non-null array
        Object[] listeners = listenerList.getListenerList();
        // Process the listeners last to first, notifying
        // those that are interested in this event
        for (int i = listeners.length - 2; i >= 0; i -= 2) {
            if (listeners[i] == EnvironmentObjectMovedEventListener.class) {
                ((EnvironmentObjectMovedEventListener) listeners[i + 1]).environmentObjectMoved(environmentObjectMovedEvent);
            }
        }
    }
    // </editor-fold>

}
