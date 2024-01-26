package com.edu.pb.infrastructure.frame;

import com.edu.pb.domain.model.User;
import com.edu.pb.domain.model.exception.AuthUserException;
import com.edu.pb.domain.service.AuthService;
import com.edu.pb.infrastructure.util.FormUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.Collections;

public class PasswordFrame extends JFrame {

    private static final String password = "123";

    private final AuthService authService = new AuthService(Collections.singleton(password));

    private final JTextField loginTF;
    private final JPasswordField passwordField;

    public PasswordFrame() throws HeadlessException {
        setTitle("Please authorize");
        setPreferredSize(new Dimension(200,130));

        final JPanel panel = new JPanel();
        add(panel, BorderLayout.NORTH);
        panel.setLayout(new GridLayout(2, 2));

        panel.add(new JLabel("Login:"));
        loginTF = (JTextField) panel.add(new JTextField());

        panel.add(new JLabel("Password"));
        passwordField = (JPasswordField) panel.add(new JPasswordField());

        final JPanel buttonPanel = new JPanel();
        add(buttonPanel, BorderLayout.SOUTH);
        final JButton authorize = (JButton) buttonPanel.add(new JButton("Authorize"));
        authorize.addActionListener(getAuthListener());


    }

    private ActionListener getAuthListener() {
        return event -> {
            try {
                authService.validateUser(getUserFromForm());
            } catch (final AuthUserException e) {
                // TODO show popup
            }
        };
    }

    private User getUserFromForm() {
        return User.builder()
                .userName(loginTF.getName())
                .password(passwordField.getPassword())
                .build();
    }

    @Deprecated
    public static void main(String[] args) {

        FormUtils.launchFrame(new PasswordFrame());
    }

}
