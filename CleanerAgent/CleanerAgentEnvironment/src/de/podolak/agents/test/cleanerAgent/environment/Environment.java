package de.podolak.agents.test.cleanerAgent.environment;

import de.podolak.agents.test.cleanerAgent.agent.Action;
import de.podolak.agents.environment.EnvironmentObject;
import de.podolak.agents.environment.SignalType;
import de.podolak.agents.test.cleanerAgent.agent.CleanerAgent;
import java.awt.Point;
import java.util.List;

/**
 *
 * @version $version$
 * @author $author$
 */
public class Environment extends de.podolak.agents.environment.AbstractEnvironment {

    private Area area = null;
    private int dimensionX = 100;
    private int dimensionY = 100;

    public Environment() {
        this(100, 100);
    }

    public Environment(int dimensionX, int dimensionY) {
        this.dimensionX = dimensionX;
        this.dimensionY = dimensionY;
        area = new Area(dimensionX, dimensionY);
    }

    public Point getRandomField() {
        return area.getRandomFreeCoordinates();
    }

    // <editor-fold defaultstate="collapsed" desc=" measurements ">
    @Override
    public byte measure(EnvironmentObject environmentObject, SignalType signalType) {
        byte value = -1;

        if (environmentObject instanceof CleanerAgent && signalType != null) {
            switch (signalType) {
                case DIRT:
                    value = measureDirt(environmentObject);
                    break;

                case LOCATION_X:
                    value = measureLocationX(environmentObject);
                    break;
                case LOCATION_Y:
                    value = measureLocationY(environmentObject);
                    break;

                default:
                    break;
            }
        }

        return value;
    }

    private byte measureDirt(EnvironmentObject environmentObject) {
        byte value = 2;

        if (area.isDirty(environmentObject)) {
            value = 1;
        }

        return value;
    }

    private byte measureLocationX(EnvironmentObject environmentObject) {
        return (byte) area.getCoordinateOfEnvironmentObject(environmentObject).x;
    }

    private byte measureLocationY(EnvironmentObject environmentObject) {
        return (byte) area.getCoordinateOfEnvironmentObject(environmentObject).y;
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc=" environment object methods ">
    @Override
    public void addEnvironmentObject(EnvironmentObject environmentObject) {
        super.addEnvironmentObject(environmentObject);
        
        area.addEnvironmentObject(environmentObject, area.getRandomFreeCoordinates());
    }

    @Override
    public void removeEnvironmentObject(EnvironmentObject environmentObject) {
        super.removeEnvironmentObject(environmentObject);
        
        area.removeEnvironmentObject(environmentObject);
    }

    @Override
    public List<EnvironmentObject> getEnvironmentObjectList() {
        return super.getEnvironmentObjectList();
    }

//    public List<CleanerAgent> getAgentList() {
//        CleanerAgent[] agents = new CleanerAgent[0];
//
//
//        //return new ArrayList<CleanerAgent>(getEnvironmentObjectList().toArray(new CleanerAgent[0]));
//
//        return null;
//    }

    public boolean moveEnvironmentObjectTo(EnvironmentObject environmentObject, int x, int y) {
        return area.moveEnvironmentObjectTo(environmentObject, x, y);
    }

    public boolean moveEnvironmentObjectByOffset(EnvironmentObject environmentObject, int x, int y) {
        return area.moveEnvironmentObjectByOffset(environmentObject, x, y);
    }

    public void cleanEnvironmentObjectsPosition(EnvironmentObject environmentObjectInterface) {
        area.setDirty(environmentObjectInterface, false);
    }

//    @Override
//    public List<EnvironmentObjectInterface> getEnvironmentObjectList() {
//        return area.getEnvironmentObjectList();
//    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc=" actions ">
    @Override
    public void doAction(Enum type, Object... params) {
        if (type instanceof Action) {
            switch ((Action)type) {
                case MOVE_TO:
                    doAction_MoveTo(params);
                    break;
                case MOVE_BY:
                    doAction_MoveBy(params);
                    break;
                case CLEAN:
                    doAction_Clean(params);
                    break;
                default:
                    break;
            }
        }
    }
    
    private void doAction_MoveTo(Object... params) {
        if (params != null
            && params.length > 2
            && params[0] instanceof EnvironmentObject
            && params[1] instanceof Number
            && params[2] instanceof Number) {
            moveEnvironmentObjectTo(
                (EnvironmentObject) params[0],
                ((Number) params[1]).intValue(),
                ((Number) params[2]).intValue());
        }
    }

    private void doAction_MoveBy(Object... params) {
        if (params != null
            && params.length > 2
            && params[0] instanceof EnvironmentObject
            && params[1] instanceof Number
            && params[2] instanceof Number) {
            moveEnvironmentObjectByOffset(
                (EnvironmentObject) params[0],
                ((Number) params[1]).intValue(),
                ((Number) params[2]).intValue());
        }
    }

    private void doAction_Clean(Object... params) {
        if (params != null
            && params.length > 0
            && params[0] instanceof EnvironmentObject) {
            cleanEnvironmentObjectsPosition(
                (EnvironmentObject) params[0]);
        }
    }
    // </editor-fold>
}
