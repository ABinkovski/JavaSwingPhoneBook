package com.edu.pb.infrastructure.frame;

import javax.swing.*;
import java.awt.*;

public class MainForm extends JFrame {

    public MainForm() throws HeadlessException {
        setPreferredSize(new Dimension(400, 200));
        setTitle("Phone Book - Java Swing Limited Functionality App");

        final PasswordFrame passwordFrame = new PasswordFrame(this);
        while (!passwordFrame.isAuthSucceeded()) {
            passwordFrame.setVisible(true);
        }
    }

    private void initMainForm() {

    }
}
