package com.edu.pb.infrastructure.frame;

import com.edu.pb.domain.service.PhoneBookService;
import lombok.extern.slf4j.Slf4j;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

@Slf4j
public class MainForm extends JFrame {

    private JPanel phoneTablePanel;
    private JPanel buttonPanel;

    private final PhoneBookService phoneBookService = new PhoneBookService();

    public MainForm() throws HeadlessException {
        log.debug("Preparing for Auth and init");

        setPreferredSize(new Dimension(400, 200));
        setTitle("Phone Book - Java Swing Limited Functionality App");

        final PasswordFrame passwordFrame = PasswordFrame.initForm(this);
        while (!passwordFrame.isAuthSucceeded()) {
//            showMessageForAuthRequired();
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

        revalidate();
        pack();
    }

    private void initPhoneTable() {
        phoneTablePanel = new JPanel();
        add(phoneTablePanel, BorderLayout.CENTER);

        final JTable jTable = new JTable(phoneBookService.getTableModel());
        phoneTablePanel.add(new JScrollPane(jTable));
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
}
