/**
 * Copyright (c) The openTCS Authors.
 *
 * This program is free software and subject to the MIT license. (For details,
 * see the licensing information (LICENSE.txt) you should have received with
 * this copy of the software.)
 */
package org.opentcs.guing.components.dialogs;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Insets;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.border.EmptyBorder;
import org.opentcs.util.gui.Icons;

/**
 * A dialog with an ok and a cancel button.
 *
 * @author Sebastian Naumann (ifak e.V. Magdeburg)
 */
public class StandardDialog
    extends JDialog {

  /**
   * A return status code - returned if Cancel button has been pressed
   */
  public static final int RET_CANCEL = 0;
  /**
   * A return status code - returned if OK button has been pressed
   */
  public static final int RET_OK = 1;
  /**
   * The content of this dialog.
   */
  protected JComponent fContent;
  private int returnStatus = RET_CANCEL;

  /**
   * Creates a new instance.
   *
   * @param parent The parent component.
   * @param modal Whether or not the dialog is modal.
   * @param content The content component.
   * @param title The title of the dialog.
   */
  public StandardDialog(Component parent, boolean modal, JComponent content, String title) {
    super(JOptionPane.getFrameForComponent(parent), title, modal);
    initComponents();
    initSize(content);
    setTitle(title);
    setIconImages(Icons.getOpenTCSIcons());
  }

  /**
   * Initialises the size of the dialog based on the content.
   *
   * @param content the dialog to base the size on.
   */
  protected final void initSize(JComponent content) {
    fContent = content;
    getContentPane().add(content, BorderLayout.CENTER);
    content.setBorder(new EmptyBorder(new Insets(3, 3, 3, 3)));
    getRootPane().setDefaultButton(okButton);
    pack();
  }

  /**
   * Returns the content of this dialog.
   *
   * @return the content of this dialog.
   */
  public JComponent getContent() {
    return fContent;
  }

  /**
   * Return the return status of this dialog - one of RET_OK or RET_CANCEL.
   *
   * @return the return status of this dialog - one of RET_OK or RET_CANCEL.
   */
  public int getReturnStatus() {
    return returnStatus;
  }

  // CHECKSTYLE:OFF
  /**
   * This method is called from within the constructor to initialize the form.
   * WARNING: Do NOT modify this code. The content of this method is always
   * regenerated by the Form Editor.
   */
  // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
  private void initComponents() {

    buttonPanel = new javax.swing.JPanel();
    okButton = new javax.swing.JButton();
    cancelButton = new CancelButton();

    addWindowListener(new java.awt.event.WindowAdapter() {
      public void windowClosing(java.awt.event.WindowEvent evt) {
        closeDialog(evt);
      }
    });

    okButton.setFont(okButton.getFont().deriveFont(okButton.getFont().getStyle() | java.awt.Font.BOLD));
    java.util.ResourceBundle bundle = java.util.ResourceBundle.getBundle("i18n/org/opentcs/plantoverview/system"); // NOI18N
    okButton.setText(bundle.getString("standardDialog.button_ok.text")); // NOI18N
    okButton.setOpaque(false);
    okButton.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        okButtonActionPerformed(evt);
      }
    });
    buttonPanel.add(okButton);

    cancelButton.setFont(cancelButton.getFont());
    cancelButton.setText(bundle.getString("standardDialog.button_cancel.text")); // NOI18N
    cancelButton.setOpaque(false);
    cancelButton.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        cancelButtonActionPerformed(evt);
      }
    });
    buttonPanel.add(cancelButton);

    getContentPane().add(buttonPanel, java.awt.BorderLayout.SOUTH);

    pack();
  }// </editor-fold>//GEN-END:initComponents
  // CHECKSTYLE:ON

    private void okButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_okButtonActionPerformed
      doClose(RET_OK);
    }//GEN-LAST:event_okButtonActionPerformed

    private void cancelButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelButtonActionPerformed
      doClose(RET_CANCEL);
    }//GEN-LAST:event_cancelButtonActionPerformed

    private void closeDialog(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_closeDialog
      doClose(RET_CANCEL);
    }//GEN-LAST:event_closeDialog

  /**
   * Closes the dialog.
   */
  private void doClose(int retStatus) {
    returnStatus = retStatus;
    setVisible(false);
    dispose();
  }

  // CHECKSTYLE:OFF
  // Variables declaration - do not modify//GEN-BEGIN:variables
  private javax.swing.JPanel buttonPanel;
  private javax.swing.JButton cancelButton;
  private javax.swing.JButton okButton;
  // End of variables declaration//GEN-END:variables
  // CHECKSTYLE:ON
}