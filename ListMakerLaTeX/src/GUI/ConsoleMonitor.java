package GUI;

import java.awt.Frame;
import javax.swing.JDialog;

public class ConsoleMonitor extends JDialog {

    public ConsoleMonitor(Frame parent, boolean modal, String consoleOutput) {
        super(parent, modal);
        setTitle("Salida de la consola");
        initComponents();
        taConsoleOutput.setEnabled(false);
        taConsoleOutput.setText(consoleOutput);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        JScrollPane1 = new javax.swing.JScrollPane();
        taConsoleOutput = new javax.swing.JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        taConsoleOutput.setColumns(20);
        taConsoleOutput.setRows(5);
        JScrollPane1.setViewportView(taConsoleOutput);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(JScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 617, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(JScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 346, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane JScrollPane1;
    private javax.swing.JTextArea taConsoleOutput;
    // End of variables declaration//GEN-END:variables
}
