package com.edu.pb.infrastructure.frame;

import javax.swing.*;
import java.awt.*;

public class MainForm extends JFrame {

    public MainForm() throws HeadlessException {
        setPreferredSize(new Dimension(400, 200));
        setTitle("Phone Book - Java Swing Limited Functionality App");

        final PasswordFrame passwordFrame = PasswordFrame.initForm(this);
        while (!passwordFrame.isAuthSucceeded()) {
            showMessageForAuthRequired();
            passwordFrame.setVisible(true);
        }
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

    }
}
