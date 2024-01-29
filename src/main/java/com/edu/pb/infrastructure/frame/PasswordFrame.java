package com.edu.pb.infrastructure.frame;

import com.edu.pb.domain.model.User;
import com.edu.pb.domain.model.exception.AuthUserException;
import com.edu.pb.domain.service.AuthService;
import com.edu.pb.infrastructure.util.FormUtils;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Arrays;
import java.util.HashSet;

@Slf4j
public class PasswordFrame extends JDialog {

    private final AuthService authService = new AuthService(new HashSet<>(Arrays.asList("123", "111", "321", "1")));

    private final JTextField loginTF;
    private final JPasswordField passwordField;

    @Getter
    private boolean isAuthSucceeded;

    public PasswordFrame(final JFrame parent) throws HeadlessException {
        super(parent, "Please authorize", true);
        setPreferredSize(new Dimension(200, 130));

        final JPanel panel = new JPanel();
        add(panel, BorderLayout.NORTH);
        panel.setLayout(new GridLayout(2, 2));

        panel.add(new JLabel("Login:"));
        loginTF = (JTextField) panel.add(new JTextField());

        panel.add(new JLabel("Password:"));
        passwordField = (JPasswordField) panel.add(new JPasswordField());

        final JPanel buttonPanel = new JPanel();
        add(buttonPanel, BorderLayout.SOUTH);
        final JButton authorize = (JButton) buttonPanel.add(new JButton("Authorize"));
        authorize.addActionListener(getAuthListener());

        loginTF.addKeyListener(getKeyListener());
        passwordField.addKeyListener(getKeyListener());
    }

    private ActionListener getAuthListener() {
        return event -> authorizeUser();
    }

    private void authorizeUser() {
        try {
            authService.validateUser(getUserFromForm());
            isAuthSucceeded = true;
            this.dispose();
        } catch (final AuthUserException e) {
            log.error(e.getMessage(), e);
            showInvalidAuthPopup(e.getMessage());
        }
    }

    private KeyListener getKeyListener() {
        return new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                if (e.getKeyChar() == KeyEvent.VK_ENTER) {
                    authorizeUser();
                }
            }

            @Override
            public void keyPressed(KeyEvent e) {
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }
        };
    }

    private void showInvalidAuthPopup(final String message) {
        JOptionPane.showConfirmDialog(this, message, "Credential issues", JOptionPane.DEFAULT_OPTION);
    }

    private User getUserFromForm() {
        return User.builder()
                .userName(loginTF.getText())
                .password(passwordField.getPassword())
                .build();
    }

    public static PasswordFrame initForm(final JFrame parent) {
        final PasswordFrame passwordFrame = new PasswordFrame(parent);
        passwordFrame.pack();
        FormUtils.centerTheFrame(passwordFrame);
        passwordFrame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        passwordFrame.setVisible(true);

        return passwordFrame;
    }

    @Deprecated
    public static void main(final String[] args) {
        EventQueue.invokeLater(() -> {
            initForm(null);
        });
    }

}
