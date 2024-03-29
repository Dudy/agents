/*
 * EnvironmentPanel.java
 *
 * Created on 03.01.2010, 15:08:15
 */
package de.podolak.agents.test.cleanerAgent.simulation;

import de.podolak.agents.environment.AbstractEnvironment;
import de.podolak.agents.environment.EnvironmentObject;
import de.podolak.agents.environment.SignalType;
import de.podolak.agents.test.cleanerAgent.agent.CleanerAgent;
import java.awt.Color;
import java.awt.Graphics;
import java.beans.Beans;

/**
 *
 * @author podolak
 */
public class EnvironmentPanel extends javax.swing.JPanel {

    private AbstractEnvironment environment;

    public EnvironmentPanel() {
        initComponents();
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);

        if (!Beans.isDesignTime()) {
            for (EnvironmentObject environmentObject : environment.getEnvironmentObjectList()) {
                int x = environment.measure(environmentObject, SignalType.LOCATION_X);
                int y = environment.measure(environmentObject, SignalType.LOCATION_Y);

                if (environmentObject instanceof CleanerAgent) {
                    g.setColor(Color.RED);
                } else {
                    g.setColor(Color.BLACK);
                }

                g.drawLine(x, y, x, y);
            }
        }
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        setPreferredSize(new java.awt.Dimension(100, 100));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 398, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 298, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

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
        this.environment = environment;
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
