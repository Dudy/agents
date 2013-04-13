package de.podolak.agents.test.cleanerAgent.environment;

import de.podolak.agents.environment.EnvironmentObject;
import java.awt.Point;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 *
 * @version $version$
 * @author $author$
 */
public class Area {

    private int dimensionX = 100;
    private int dimensionY = 100;
    private Map<EnvironmentObject,Point> coordinatesByObjects;
    private Random random;
    private List<Point> dirtCoordinates;

    public Area() {
        this(100, 100);
    }
    
    public Area(int dimensionX, int dimensionY) {
        this.dimensionX = dimensionX;
        this.dimensionY = dimensionY;
        
        coordinatesByObjects = new HashMap<EnvironmentObject, Point>();
        random = new Random();
        dirtCoordinates = new ArrayList<Point>();
    }

    /**
     * @return the dimensionX
     */
    public int getDimensionX() {
        return dimensionX;
    }

    /**
     * @param dimensionX the dimensionX to set
     */
    public void setDimensionX(int dimensionX) {
        this.dimensionX = dimensionX;
    }

    /**
     * @return the dimensionY
     */
    public int getDimensionY() {
        return dimensionY;
    }

    /**
     * @param dimensionY the dimensionY to set
     */
    public void setDimensionY(int dimensionY) {
        this.dimensionY = dimensionY;
    }
    
    /**
     * Add object to this area at the location (x,y). If there already is
     * an object, don't add the new one and return false. If the location
     * is empty, put the object there and return true.
     *
     * @param environmentObject the object to put into this area
     * @param x the x coordinate to put the object to
     * @param y the y coordinate to put the object to
     * @return true if the location was empty (and object has been put there)
     *         and false if the location was occupied (and the object has
     *         not been put anywhere)
     */
    public boolean addEnvironmentObject(EnvironmentObject environmentObject, Point coordinates) {
        boolean objectAdded = false;

        if (!coordinatesByObjects.containsValue(coordinates)) {
            coordinatesByObjects.put(environmentObject, coordinates);
            objectAdded = true;
        } else {
            objectAdded = false;
        }

        return objectAdded;
    }

    /**
     * Add object to this area at the location (x,y). If there already is
     * an object, don't add the new one and return false. If the location
     * is empty, put the object there and return true.
     *
     * @param environmentObject the object to put into this area
     * @param x the x coordinate to put the object to
     * @param y the y coordinate to put the object to
     * @return true if the location was empty (and object has been put there)
     *         and false if the location was occupied (and the object has
     *         not been put anywhere)
     */
    public boolean addEnvironmentObject(EnvironmentObject environmentObject, int x, int y) {
        return addEnvironmentObject(environmentObject, new Point(x, y));
    }

    /**
     * Remove the object from this area.
     *
     * @param environmentObject the object to remove
     */
    public void removeEnvironmentObject(EnvironmentObject environmentObject) {
        coordinatesByObjects.remove(environmentObject);
    }

    /**
     * Move the object to the location (x, y). If there already is
     * an object, don't move this one and return false. If the location
     * is empty, put the object there and return true.
     * If the object is not part of this area, it is added.
     *
     * @param environmentObject the object to move
     * @param x the x coordinate to put the object to
     * @param y the y coordinate to put the object to
     * @return true if the location was empty (and object has been moved there)
     *         and false if the location was occupied (and the object resides
     *         at it's place)
     */
    public boolean moveEnvironmentObjectTo(EnvironmentObject environmentObject, int x, int y) {
        removeEnvironmentObject(environmentObject);
        return addEnvironmentObject(environmentObject, x, y);
    }

    /**
     * Move the object from the current location (x_now, y_now) to the new
     * location (x_now + x, y_now + y), i.e. add <code>x</code> to the current
     * x location and <code>y</code> to the current y location.
     * If there already is an object, don't move this one and return false. If
     * the location is empty, put the object there and return true.
     * If the object is not part of this area, it is added. The x and y parameters
     * will then define the initial coordinates such that this call will be
     * equivalent to <code>addEnvironmentObject(x, y)</code>.
     *
     * @see #addEnvironmentObject(environmentObjectInterface)
     * @param environmentObject the object to move
     * @param x the x value to move the object by
     * @param y the y value to move the object by
     * @return true if the new location was empty (and object has been moved there)
     *         and false if the location was occupied (and the object resides
     *         at it's place)
     */
    public boolean moveEnvironmentObjectByOffset(EnvironmentObject environmentObject, int x, int y) {
        boolean objectMoved = false;
        Point p = coordinatesByObjects.get(environmentObject);

        if (p != null) {
            removeEnvironmentObject(environmentObject);
            objectMoved = addEnvironmentObject(environmentObject, p.x + x, p.y + y);
        }

        return objectMoved;
    }

    /**
     * Returns a randomly chosen free coordinate.
     * Warning! This method is not stable. It just randomly
     * chooses a point and checks whether the location is
     * empty. If it is not, another randomly chosen location
     * is generated and so on.
     * This is not a good choice for heavily occupied
     * areas. This method will only check for half the maximum
     * number of locations (<code>dimensionX * dimensionY / 2</code>
     * and then returns <code>null</code>.
     *
     * @return
     */
    public Point getRandomFreeCoordinates() {
        // TODO: make this more robust, a simple but possibly not
        // so performant version would be as follows:
        // - generate Points for all possible locations
        // - eliminate existing ones
        // - put the remaining Points into an array
        // - choose a random number to access the array
        // - the Point at that array position is the new one

        int counter = dimensionX * dimensionY / 2;
        int x = random.nextInt(dimensionX);
        int y = random.nextInt(dimensionY);
        Point point = new Point(x, y);

        while (coordinatesByObjects.containsValue(point)) {
            x = random.nextInt(dimensionX);
            y = random.nextInt(dimensionY);
            point = new Point(x, y);
            counter--;

            if (counter == 0) {
                point = null;
                break;
            }
        }

        return point;
    }

    public Point getRandomFreeCoordinate_TEST1() {
        // TODO: make this more robust, a simple but possibly not
        // so performant version would be as follows:
        // - generate Points for all possible locations
        // - eliminate existing ones
        // - put the remaining Points into an array
        // - choose a random number to access the array
        // - the Point at that array position is the new one

        Point p = new Point();
        ArrayList<Point> freePoints = new ArrayList<Point>(
            dimensionX * dimensionY - coordinatesByObjects.size());

        for (int x = 0; x < dimensionX; x++) {
            for (int y = 0; y < dimensionY; y++) {
                p.setLocation(x, y);

                if (coordinatesByObjects.containsValue(p)) {
                    freePoints.add(p);
                    p = new Point();
                }
            }
        }

        return freePoints.get(random.nextInt(freePoints.size()));
    }

    public Point getCoordinateOfEnvironmentObject(EnvironmentObject environmentObject) {
        return coordinatesByObjects.get(environmentObject);
    }

    public boolean isDirty(Point p) {
        return dirtCoordinates.contains(p);
    }

    public boolean isDirty(int x, int y) {
        return isDirty(new Point(x, y));
    }

    public boolean isDirty(EnvironmentObject environmentObject) {
        return isDirty(getCoordinateOfEnvironmentObject(environmentObject));
    }

    public void setDirty(Point p, boolean dirty) {
        if (dirty) {
            if (!isDirty(p)) {
                dirtCoordinates.add(p);
            }
        } else {
            if (isDirty(p)) {
                dirtCoordinates.remove(p);
            }
        }
    }

    public void setDirty(int x, int y, boolean dirty) {
        setDirty(new Point(x, y), dirty);
    }

    public void setDirty(EnvironmentObject environmentObject, boolean dirty) {
        setDirty(getCoordinateOfEnvironmentObject(environmentObject), dirty);
    }

//    public List<EnvironmentObjectInterface> getEnvironmentObjectList() {
//        return new ArrayList<EnvironmentObjectInterface>(coordinatesByObjects.keySet());
//    }
//
//    public Map<EnvironmentObjectInterface,Point> getCoordinatesByObjects() {
//        return coordinatesByObjects;
//    }
}
