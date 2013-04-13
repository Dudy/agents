package de.podolak.agents.environment;

import java.util.List;

/**
 *
 * @author podolak
 */
public interface Environment {
    void addEnvironmentObject(EnvironmentObject environmentObject);
    void removeEnvironmentObject(EnvironmentObject environmentObject);
    List<EnvironmentObject> getEnvironmentObjectList();
}
