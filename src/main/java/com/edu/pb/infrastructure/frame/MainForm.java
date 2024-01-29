package com.edu.pb.infrastructure.frame;

import com.edu.pb.domain.service.PhoneBookService;
import lombok.extern.slf4j.Slf4j;

import javax.swing.*;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeListener;
import java.util.Objects;

@Slf4j
public class MainForm extends JFrame {

    private JPanel phoneTablePanel;
    private JPanel buttonPanel;

    private final PhoneBookService phoneBookService = new PhoneBookService();

    private TableModel tableModel;

    public MainForm() throws HeadlessException {
        log.debug("Preparing for Auth and init");

        setPreferredSize(new Dimension(600, 200));
        setTitle("Phone Book - Java Swing Limited Functionality App");

        final PasswordFrame passwordFrame = PasswordFrame.initForm(this);
        while (!passwordFrame.isAuthSucceeded()) {
            showMessageForAuthRequired();
            passwordFrame.setVisible(true);
        }

        log.debug("Main form initialization");
        initMainForm();
    }

    private void showMessageForAuthRequired() {
        final int authException = JOptionPane.showConfirmDialog(this, "If You want to continue, You have to be authorized.", "Auth exception", JOptionPane.YES_NO_OPTION);
        switch (authException) {
            case JOptionPane.YES_OPTION:
                // continue
                break;
            case JOptionPane.NO_OPTION:
            default:
                System.exit(-1);
        }
    }

    private void initMainForm() {
        initPhoneTable();
        initButtons();

        updateView();
    }

    private void updateView() {
        revalidate();
        repaint();
    }

    private void initPhoneTable() {
        phoneTablePanel = new JPanel();
        phoneTablePanel.setLayout(new GridLayout(1, 1)); // Fixing JScrollPane out of the main JFrame
        add(phoneTablePanel, BorderLayout.CENTER);

        tableModel = phoneBookService.getTableModel();
        final JTable jTable = new JTable(tableModel);
        final JScrollPane jScrollPane = new JScrollPane(jTable);
        phoneTablePanel.add(jScrollPane);

        jTable.addPropertyChangeListener(getPropertyChangeListener());
    }

    private void initButtons() {
        buttonPanel = new JPanel();
        add(buttonPanel, BorderLayout.EAST);

        buttonPanel.setLayout(new GridLayout(3, 1));
        final JButton addRecord = (JButton) buttonPanel.add(new JButton("Add record"));
        final JButton editRecord = (JButton) buttonPanel.add(new JButton("Edit record"));
        final JButton deleteRecord = (JButton) buttonPanel.add(new JButton("Delete Record"));

        addRecord.addActionListener(getAddRecordAction());
        editRecord.addActionListener(getEditRecordAction());
        deleteRecord.addActionListener(getDeleteRecordAction());
    }

    private ActionListener getAddRecordAction() {
        return event -> {
            log.debug("Adding new record");
            // TODO
        };
    }


    private ActionListener getEditRecordAction() {
        return event -> {
            log.debug("Start editing");
            // TODO
        };
    }


    private ActionListener getDeleteRecordAction() {
        return event -> {
            log.debug("Deleting record");
            // TODO
        };
    }

    private PropertyChangeListener getPropertyChangeListener() {
        return event -> {
            final JTable source = (JTable) event.getSource();
            final int editingRow = source.getEditingRow();
            final int editingColumn = source.getEditingColumn();
            if (Objects.nonNull(event.getOldValue())) {
                final DefaultCellEditor oldValue = (DefaultCellEditor) event.getOldValue();
                log.info("Table value is changed to: \"{}\", location: [{},{}]", oldValue.getCellEditorValue(), editingRow, editingColumn);
                log.info("Table model value: \"{}\"", tableModel.getValueAt(editingRow, editingColumn));
            } else {
//                log.info("Start table value editing location: [{},{}]", editingRow, editingColumn); // [-1,-1]
            }
        };
    }
}
