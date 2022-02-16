/**
 * Copyright (c) The openTCS Authors.
 *
 * This program is free software and subject to the MIT license. (For details,
 * see the licensing information (LICENSE.txt) you should have received with
 * this copy of the software.)
 */
package org.opentcs.guing.transport.sequences;

import com.google.inject.assistedinject.Assisted;
import static java.util.Objects.requireNonNull;
import javax.inject.Inject;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import org.opentcs.access.KernelRuntimeException;
import org.opentcs.access.SharedKernelServicePortal;
import org.opentcs.access.SharedKernelServicePortalProvider;
import org.opentcs.data.TCSObjectReference;
import org.opentcs.data.model.Vehicle;
import org.opentcs.data.order.OrderSequence;
import org.opentcs.data.order.TransportOrder;
import org.opentcs.guing.components.dialogs.DialogContent;
import org.opentcs.guing.transport.UneditableTableModel;
import org.opentcs.guing.util.I18nPlantOverviewOperating;
import org.opentcs.thirdparty.jhotdraw.util.ResourceBundleUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Displays an order sequence.
 *
 * @author Heinz Huber (Fraunhofer IML)
 * @author Stefan Walter (Fraunhofer IML)
 */
public class OrderSequenceView
    extends DialogContent {

  /**
   * This class's logger.
   */
  private static final Logger LOG = LoggerFactory.getLogger(OrderSequenceView.class);
  /**
   * The order sequence to be shown.
   */
  private final OrderSequence fOrderSequence;
  /**
   * The portal provider to be used.
   */
  private final SharedKernelServicePortalProvider portalProvider;

  /**
   * Creates new instance.
   *
   * @param sequence The order sequence.
   * @param portalProvider Provides access to a portal.
   */
  @Inject
  public OrderSequenceView(@Assisted OrderSequence sequence,
                           SharedKernelServicePortalProvider portalProvider) {
    this.fOrderSequence = requireNonNull(sequence, "sequence");
    this.portalProvider = requireNonNull(portalProvider, "portalProvider");
    initComponents();
    initFields();
  }

  /**
   * Returns the order sequence.
   *
   * @return The order sequence.
   */
  public OrderSequence getOrderSequence() {
    return fOrderSequence;
  }

  @Override
  public void update() {
    // Do nada.
  }

  @Override
  public final void initFields() {
    setDialogTitle(ResourceBundleUtil.getBundle(I18nPlantOverviewOperating.TO_SEQUENCE_PATH)
        .getString("orderSequenceView.title"));
    // Name
    String name = getOrderSequence().getName();
    textFieldName.setText(name);

    boolean complete = getOrderSequence().isComplete();
    checkBoxComplete.setSelected(complete);
    checkBoxComplete.setEnabled(!complete);

    boolean finished = getOrderSequence().isFinished();
    checkBoxFinished.setSelected(finished);

    boolean failureFatal = getOrderSequence().isFailureFatal();
    checkBoxFailureFatal.setSelected(failureFatal);

    int finishedIndex = getOrderSequence().getFinishedIndex();
    textFieldFinishedIndex.setText("" + finishedIndex);

    TCSObjectReference<Vehicle> intendedVehicle = getOrderSequence().getIntendedVehicle();

    if (intendedVehicle != null) {
      textFieldIntendedVehicle.setText(intendedVehicle.getName());
    }

    TCSObjectReference<Vehicle> processingVehicle = getOrderSequence().getProcessingVehicle();

    if (processingVehicle != null) {
      textFieldProcessingVehicle.setText(processingVehicle.getName());
    }

    textType.setText(getOrderSequence().getType());

//    TCSObjectReference<TransportOrder> nextUnfinishedOrder = getOrderSequence().getNextUnfinishedOrder();
    // TODO...
    // Transport orders
    DefaultTableModel tableModel = new UneditableTableModel();
    tableModel.setColumnIdentifiers(new String[] {"Name"});  // , "Quelle", "Ziel"...

    for (TCSObjectReference<TransportOrder> to : getOrderSequence().getOrders()) {
      String[] row = new String[1];
      row[0] = to.getName();
      tableModel.addRow(row);
    }

    transportOrdersTable.setModel(tableModel);
  }

  // CHECKSTYLE:OFF
  /**
   * This method is called from within the constructor to initialize the form.
   * WARNING: Do NOT modify this code. The content of this method is always
   * regenerated by the Form Editor.
   */
  // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
  private void initComponents() {
    java.awt.GridBagConstraints gridBagConstraints;

    generalPanel = new javax.swing.JPanel();
    jPanel1 = new javax.swing.JPanel();
    labelName = new javax.swing.JLabel();
    textFieldName = new javax.swing.JTextField();
    labelFinishedIndex = new javax.swing.JLabel();
    textFieldFinishedIndex = new javax.swing.JTextField();
    labelIntendedVehicle = new javax.swing.JLabel();
    textFieldIntendedVehicle = new javax.swing.JTextField();
    labelProcessingVehicle = new javax.swing.JLabel();
    textFieldProcessingVehicle = new javax.swing.JTextField();
    labelType = new javax.swing.JLabel();
    textType = new javax.swing.JTextField();
    jPanel2 = new javax.swing.JPanel();
    checkBoxComplete = new javax.swing.JCheckBox();
    checkBoxFinished = new javax.swing.JCheckBox();
    checkBoxFailureFatal = new javax.swing.JCheckBox();
    transportOrdersPanel = new javax.swing.JPanel();
    transportOrdersScrollPane = new javax.swing.JScrollPane();
    transportOrdersTable = new javax.swing.JTable();

    setLayout(new java.awt.GridBagLayout());

    java.util.ResourceBundle bundle = java.util.ResourceBundle.getBundle("i18n/org/opentcs/plantoverview/operating/panels/orderSequences"); // NOI18N
    generalPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(bundle.getString("orderSequenceView.panel_general.border.title"))); // NOI18N
    generalPanel.setLayout(new java.awt.GridBagLayout());

    jPanel1.setLayout(new java.awt.GridBagLayout());

    labelName.setFont(labelName.getFont());
    labelName.setText("Name:");
    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridx = 0;
    gridBagConstraints.gridy = 0;
    gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
    gridBagConstraints.insets = new java.awt.Insets(0, 4, 0, 4);
    jPanel1.add(labelName, gridBagConstraints);

    textFieldName.setEditable(false);
    textFieldName.setColumns(10);
    textFieldName.setFont(textFieldName.getFont());
    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridx = 1;
    gridBagConstraints.gridy = 0;
    gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
    gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
    gridBagConstraints.weightx = 0.5;
    jPanel1.add(textFieldName, gridBagConstraints);

    labelFinishedIndex.setFont(labelFinishedIndex.getFont());
    labelFinishedIndex.setText(bundle.getString("orderSequenceView.label_finishedIndex.text")); // NOI18N
    labelFinishedIndex.setToolTipText(bundle.getString("orderSequenceView.label_finishedIndex.tooltipText")); // NOI18N
    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridx = 0;
    gridBagConstraints.gridy = 1;
    gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
    gridBagConstraints.insets = new java.awt.Insets(0, 4, 0, 4);
    jPanel1.add(labelFinishedIndex, gridBagConstraints);

    textFieldFinishedIndex.setEditable(false);
    textFieldFinishedIndex.setColumns(10);
    textFieldFinishedIndex.setFont(textFieldFinishedIndex.getFont());
    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridx = 1;
    gridBagConstraints.gridy = 1;
    gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
    gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
    gridBagConstraints.weightx = 0.5;
    jPanel1.add(textFieldFinishedIndex, gridBagConstraints);

    labelIntendedVehicle.setFont(labelIntendedVehicle.getFont());
    labelIntendedVehicle.setText(bundle.getString("orderSequenceView.label_intendedVehicle.text")); // NOI18N
    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridx = 0;
    gridBagConstraints.gridy = 2;
    gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
    gridBagConstraints.insets = new java.awt.Insets(0, 4, 0, 4);
    jPanel1.add(labelIntendedVehicle, gridBagConstraints);

    textFieldIntendedVehicle.setEditable(false);
    textFieldIntendedVehicle.setColumns(10);
    textFieldIntendedVehicle.setFont(textFieldIntendedVehicle.getFont());
    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridx = 1;
    gridBagConstraints.gridy = 2;
    gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
    gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
    gridBagConstraints.weightx = 0.5;
    jPanel1.add(textFieldIntendedVehicle, gridBagConstraints);

    labelProcessingVehicle.setFont(labelProcessingVehicle.getFont());
    labelProcessingVehicle.setText(bundle.getString("orderSequenceView.label_processingVehicle.text")); // NOI18N
    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridx = 0;
    gridBagConstraints.gridy = 3;
    gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
    gridBagConstraints.insets = new java.awt.Insets(0, 4, 0, 4);
    jPanel1.add(labelProcessingVehicle, gridBagConstraints);

    textFieldProcessingVehicle.setEditable(false);
    textFieldProcessingVehicle.setColumns(10);
    textFieldProcessingVehicle.setFont(textFieldProcessingVehicle.getFont());
    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridx = 1;
    gridBagConstraints.gridy = 3;
    gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
    gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
    gridBagConstraints.weightx = 0.5;
    jPanel1.add(textFieldProcessingVehicle, gridBagConstraints);

    labelType.setFont(labelType.getFont());
    labelType.setText(bundle.getString("orderSequenceView.label_type.text")); // NOI18N
    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridx = 0;
    gridBagConstraints.gridy = 4;
    gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
    gridBagConstraints.insets = new java.awt.Insets(0, 4, 0, 4);
    jPanel1.add(labelType, gridBagConstraints);

    textType.setEditable(false);
    textType.setColumns(10);
    textType.setFont(textType.getFont());
    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridx = 1;
    gridBagConstraints.gridy = 4;
    gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
    gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
    gridBagConstraints.weightx = 0.5;
    jPanel1.add(textType, gridBagConstraints);

    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
    gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
    gridBagConstraints.weightx = 0.5;
    generalPanel.add(jPanel1, gridBagConstraints);

    jPanel2.setLayout(new java.awt.GridBagLayout());

    checkBoxComplete.setText(bundle.getString("orderSequenceView.checkBox_complete.text")); // NOI18N
    checkBoxComplete.setToolTipText(bundle.getString("orderSequenceView.checkBox_complete.tooltipText")); // NOI18N
    checkBoxComplete.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        checkBoxCompleteActionPerformed(evt);
      }
    });
    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridx = 2;
    gridBagConstraints.gridy = 0;
    gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
    jPanel2.add(checkBoxComplete, gridBagConstraints);

    checkBoxFinished.setText(bundle.getString("orderSequenceView.checkBox_finished.text")); // NOI18N
    checkBoxFinished.setToolTipText(bundle.getString("orderSequenceView.checkBox_finished.tooltipText")); // NOI18N
    checkBoxFinished.setEnabled(false);
    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridx = 2;
    gridBagConstraints.gridy = 1;
    gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
    jPanel2.add(checkBoxFinished, gridBagConstraints);

    checkBoxFailureFatal.setText(bundle.getString("orderSequenceView.checkBox_failureFatal.text")); // NOI18N
    checkBoxFailureFatal.setToolTipText(bundle.getString("orderSequenceView.checkBox_failureFatal.tooltipText")); // NOI18N
    checkBoxFailureFatal.setEnabled(false);
    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridx = 2;
    gridBagConstraints.gridy = 2;
    gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
    jPanel2.add(checkBoxFailureFatal, gridBagConstraints);

    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.anchor = java.awt.GridBagConstraints.FIRST_LINE_START;
    generalPanel.add(jPanel2, gridBagConstraints);

    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
    gridBagConstraints.weightx = 0.1;
    add(generalPanel, gridBagConstraints);

    transportOrdersPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(bundle.getString("orderSequenceView.panel_transportOrders.border.title"))); // NOI18N
    transportOrdersPanel.setLayout(new java.awt.GridBagLayout());

    transportOrdersScrollPane.setPreferredSize(new java.awt.Dimension(100, 100));
    transportOrdersScrollPane.setViewportView(transportOrdersTable);

    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridx = 0;
    gridBagConstraints.gridy = 0;
    gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
    gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
    gridBagConstraints.weightx = 1.0;
    gridBagConstraints.weighty = 1.0;
    transportOrdersPanel.add(transportOrdersScrollPane, gridBagConstraints);

    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridx = 0;
    gridBagConstraints.gridy = 1;
    gridBagConstraints.gridwidth = 2;
    gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
    gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
    gridBagConstraints.weightx = 1.0;
    gridBagConstraints.weighty = 1.0;
    add(transportOrdersPanel, gridBagConstraints);
  }// </editor-fold>//GEN-END:initComponents

  private void checkBoxCompleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_checkBoxCompleteActionPerformed
    ResourceBundleUtil bundle = ResourceBundleUtil.getBundle(I18nPlantOverviewOperating.TO_SEQUENCE_PATH);
    int n = JOptionPane.showConfirmDialog(this,
                                          bundle.getString("orderSequenceView.optionPane_markSequenceCompleteConfirmation.message"),
                                          bundle.getString("orderSequenceView.optionPane_markSequenceCompleteConfirmation.title"),
                                          JOptionPane.YES_NO_OPTION);

    if (n == JOptionPane.OK_OPTION) {
      try (SharedKernelServicePortal sharedPortal = portalProvider.register()) {
        sharedPortal.getPortal().getTransportOrderService()
            .markOrderSequenceComplete(fOrderSequence.getReference());
        checkBoxComplete.setEnabled(false);
      }
      catch (KernelRuntimeException exc) {
        LOG.warn("Exception setting order sequence complete", exc);
      }
    }
    else {
      checkBoxComplete.setSelected(false);
    }
  }//GEN-LAST:event_checkBoxCompleteActionPerformed
  // Variables declaration - do not modify//GEN-BEGIN:variables
  private javax.swing.JCheckBox checkBoxComplete;
  private javax.swing.JCheckBox checkBoxFailureFatal;
  private javax.swing.JCheckBox checkBoxFinished;
  private javax.swing.JPanel generalPanel;
  private javax.swing.JPanel jPanel1;
  private javax.swing.JPanel jPanel2;
  private javax.swing.JLabel labelFinishedIndex;
  private javax.swing.JLabel labelIntendedVehicle;
  private javax.swing.JLabel labelName;
  private javax.swing.JLabel labelProcessingVehicle;
  private javax.swing.JLabel labelType;
  private javax.swing.JTextField textFieldFinishedIndex;
  private javax.swing.JTextField textFieldIntendedVehicle;
  private javax.swing.JTextField textFieldName;
  private javax.swing.JTextField textFieldProcessingVehicle;
  private javax.swing.JTextField textType;
  private javax.swing.JPanel transportOrdersPanel;
  private javax.swing.JScrollPane transportOrdersScrollPane;
  private javax.swing.JTable transportOrdersTable;
  // End of variables declaration//GEN-END:variables
  // CHECKSTYLE:ON

}
