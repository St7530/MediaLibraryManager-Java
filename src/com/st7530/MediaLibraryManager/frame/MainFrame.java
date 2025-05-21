package com.st7530.MediaLibraryManager.frame;

import javax.swing.*;
import java.io.IOException;

public class MainFrame extends JFrame {

    public MainFrame() {
        super("媒体库管理系统");

        // 设置一个容器
        JPanel root = new JPanel();
        this.setContentPane(root);
        this.setSize(300, 200);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Buttons
        JButton showLibraryButton = new JButton("显示物品库");
        JButton showStatsButton = new JButton("统计信息");
        JButton saveLibraryButton = new JButton("保存更改");

        showLibraryButton.addActionListener(e -> {
            ShowLibraryFrame showLibraryFrame = new ShowLibraryFrame();
        });

        root.add(showLibraryButton);
        root.add(showStatsButton);
        root.add(saveLibraryButton);

        this.setVisible(true);
    }
}
