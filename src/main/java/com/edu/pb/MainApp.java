package com.edu.pb;

import com.edu.pb.infrastructure.frame.MainForm;
import com.edu.pb.infrastructure.util.FormUtils;

public class MainApp {
    public static void main(String[] args) {
        final MainForm mainForm = new MainForm();
        FormUtils.launchFrame(mainForm);
    }
}
