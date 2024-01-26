package com.edu.pb.infrastructure.util;

import lombok.experimental.UtilityClass;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;

import static java.util.Objects.nonNull;

@UtilityClass
public class FormUtils {

    public static void launchFrame(final JFrame frame, final Component... components) {
        EventQueue.invokeLater(() -> {
            fillInstance(frame, components);

            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);
        });
    }

    public static void fillInstance(final JFrame jFrame, final Component... components) {
        if (nonNull(components)) {
            Arrays.stream(components)
                    .forEach(jFrame::add);

            jFrame.pack();

            centerTheFrame(jFrame);
        }
    }

    public static void centerTheFrame(final Window frame) {
        final Toolkit toolkit = Toolkit.getDefaultToolkit();
        final Dimension screenSize = toolkit.getScreenSize();
        final int screenWidth = screenSize.width;
        final int screenHeight = screenSize.height;

        final Dimension frameSize = frame.getSize();
        final int frameHeight = frameSize.height;
        final int frameWidth = frameSize.width;

        frame.setLocation((screenWidth - frameWidth) / 2, (screenHeight - frameHeight) / 2);
    }
}
