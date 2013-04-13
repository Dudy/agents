package de.podolak.agents.test.cleanerAgent.agent;

import de.podolak.agents.environment.SignalType;
import de.podolak.agents.agent.AbstractAgent;
import de.podolak.agents.environment.AbstractEnvironment;
import de.podolak.agents.environment.EnvironmentObject;
import de.podolak.agents.sensors.GenericSensor;
import de.podolak.agents.sensors.Sensor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Timer;

import static de.podolak.agents.environment.SignalType.*;

/**
 *
 * @version $version$
 * @author $author$
 */
public class CleanerAgent extends AbstractAgent implements EnvironmentObject, Comparable<CleanerAgent> {

    // refractory period the timer waits before it allwos the agent
    // to do it's job
    private static final int REFRACTORY_PERIOD = 1000;
    // delay time the agent waits if the agent wanted
    // to do it's job but that was not permitted
    private static final int DELAY = 1000;

    @Sensor(signalType=DIRT)
    private GenericSensor dirtSensor;
    @Sensor(signalType=LOCATION_X)
    private GenericSensor locationXSensor;
    @Sensor(signalType=LOCATION_Y)
    private GenericSensor locationYSensor;

    private Timer timer = new Timer(REFRACTORY_PERIOD, new ActionListener() {

        @Override
        public void actionPerformed(ActionEvent e) {
            actionPermitted = true;
        }
    });

    public CleanerAgent() {
        timer.start();
    }

    @Override
    public void run() {
        while (run) {
            if (actionPermitted) {
                if (environment.measure(this, SignalType.DIRT) == 1) {
                    doClean();
                } else {
                    gotoOtherField();
                }
                
                actionPermitted = false;
            } else {
                try {
                    sleep(DELAY);
                } catch (InterruptedException ex) {
                    Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    private void doClean() {
        environment.doAction(Action.CLEAN, new Object[] { this });
    }

    private void gotoOtherField() {
        // for now go to a random field, that is one or zero
        // steps up or down and one or zero steps left or right
        move(random.nextInt(3) - 1, random.nextInt(3) - 1);
    }

    private void move(int xOffset, int yOffset) {
        environment.doAction(Action.MOVE_BY, new Object[] { xOffset, yOffset });
    }

    public int compareTo(CleanerAgent o) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public static void main(String args[]) {
        AbstractEnvironment environment = new AbstractEnvironment() {

            @Override
            public byte measure(EnvironmentObject environmentObject, SignalType signalType) {
                return (byte)signalType.ordinal();
            }

            @Override
            public void doAction(Enum type, Object... params) {
                System.out.println("action done");
            }
        };
        CleanerAgent cleanerAgent = new CleanerAgent();
        cleanerAgent.setEnvironment(environment);

        java.awt.EventQueue.invokeLater(cleanerAgent);
    }
}
