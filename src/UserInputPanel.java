import java.awt.*;
import java.awt.event.ActionEvent;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import javax.swing.JPanel;

//
// Decompiled by Procyon v0.5.36
//

class UserInputPanel extends JPanel implements ActionListener
{
    private BSTcontroller controller;
    JTextField numberField;

    public void setController(final BSTcontroller c) {
        this.controller = c;
    }

    public UserInputPanel() {
        this.numberField = new JTextField(50);
        numberField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fldInsertActionPerformed(evt);
            }
        });
        this.add(this.numberField);
        setBackground(Color.MAGENTA);
    }

    public void fldInsertActionPerformed(java.awt.event.ActionEvent evt) {
        insertValue();
    }
    public void insertValue() {
        controller.addNode(Double.parseDouble(numberField.getText()));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == numberField) {
            insertValue();
        }
    }
//    @Override
//    public void actionPerformed(final ActionEvent e) {
//        if (e.getSource() == this.addNodeButton) {
//            final String COMMA = ",";
//            final String text = this.numberField.getText();
//            if (text.equals("")) {
//                this.controller.addNode(Math.floor(Math.random() * 1000.0));
//                return;
//            }
//            if (text.contains(",")) {
//                final String[] split;
//                final String[] numbers = split = text.split(",");
//                for (final String num : split) {
//                    this.controller.addNode(Double.parseDouble(num));
//                }
//            }
//            else {
//                this.controller.addNode(Double.parseDouble(text));
//            }
//            this.numberField.setText(null);
//        }
//    }
}
