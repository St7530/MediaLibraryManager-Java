package com.st7530.MediaLibraryManager.frame;

import com.st7530.MediaLibraryManager.data.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

import static com.st7530.MediaLibraryManager.Main.res;

public class AddItemFrame extends JFrame {
    public AddItemFrame() {
        super("添加物品 - 媒体库管理系统");
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                ShowLibraryFrame frame = new ShowLibraryFrame();
            }
        });
        this.setSize(320, 350);

        JPanel root = new JPanel();
        this.setContentPane(root);
        root.setLayout(new BorderLayout());

        // 输入区域
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new BoxLayout(inputPanel, BoxLayout.Y_AXIS));
        root.add(inputPanel, BorderLayout.CENTER);
        JTextField idField = new JTextField(20);
        JTextField titleField = new JTextField(20);
        JTextField authorField = new JTextField(20);
        JTextField rateField = new JTextField(20);
        JTextField pressField = new JTextField(20);
        JTextField isbnField = new JTextField(20);
        JTextField pageField = new JTextField(20);
        JTextField nameField = new JTextField(20);
        JTextField yearField = new JTextField(20);
        JTextField periodField = new JTextField(20);
        JTextField nationField = new JTextField(20);
        JTextField lengthField = new JTextField(20);
        JTextField widthField = new JTextField(20);

        // 顶部选择类型
        JPanel topPanel = new JPanel(new FlowLayout());
        JComboBox<String> typeBox;
        typeBox = new JComboBox<>(new String[]{"图书", "视频光盘", "图画"});
        typeBox.setSelectedIndex(-1);
        typeBox.addActionListener(e -> {
            inputPanel.removeAll();
            addInputRow(inputPanel, "编号：", idField);
            addInputRow(inputPanel, "标题：", titleField);
            addInputRow(inputPanel, "作者：", authorField);
            addInputRow(inputPanel, "评级：", rateField);
            int choice = typeBox.getSelectedIndex();
            switch (choice) {
                case 0:
                    addInputRow(inputPanel, "出版社：", pressField);
                    addInputRow(inputPanel, "ISBN 号：", isbnField);
                    addInputRow(inputPanel, "页数：", pageField);
                    break;
                case 1:
                    addInputRow(inputPanel, "出品者：", nameField);
                    addInputRow(inputPanel, "出品年份：", yearField);
                    addInputRow(inputPanel, "视频时长：", periodField);
                    break;
                case 2:
                    addInputRow(inputPanel, "出口国籍：", nationField);
                    addInputRow(inputPanel, "长：", lengthField);
                    addInputRow(inputPanel, "宽：", widthField);
                    break;
            }
            inputPanel.revalidate();
            inputPanel.repaint();
        });
        topPanel.add(new JLabel("选择类型："));
        topPanel.add(typeBox);
        root.add(topPanel, BorderLayout.NORTH);

        // 底部按钮
        JButton saveButton = new JButton("添加");
        root.add(saveButton, BorderLayout.SOUTH);
        saveButton.addActionListener(e -> {
            int choice = typeBox.getSelectedIndex();
            switch (choice) {
                case 0:
                    res.add(new Book(
                            Integer.parseInt(idField.getText()),
                            titleField.getText(),
                            authorField.getText(),
                            rateField.getText(),
                            pressField.getText(),
                            isbnField.getText(),
                            Integer.parseInt(pageField.getText())
                    ));
                    break;
                case 1:
                    res.add(new VCD(
                            Integer.parseInt(idField.getText()),
                            titleField.getText(),
                            authorField.getText(),
                            rateField.getText(),
                            nameField.getText(),
                            Integer.parseInt(yearField.getText()),
                            Integer.parseInt(periodField.getText())
                    ));
                    break;
                case 2:
                    res.add(new Picture(
                            Integer.parseInt(idField.getText()),
                            titleField.getText(),
                            authorField.getText(),
                            rateField.getText(),
                            nationField.getText(),
                            Integer.parseInt(lengthField.getText()),
                            Integer.parseInt(widthField.getText())
                    ));
                    break;
            }
            this.setVisible(false);
            ShowLibraryFrame showLibraryFrame = new ShowLibraryFrame();
        });

        this.setVisible(true);
    }

    private void addInputRow(JPanel panel, String label, JComponent field) {
        JPanel row = new JPanel(new FlowLayout(FlowLayout.LEFT));
        row.add(new JLabel(label));
        row.add(field);
        panel.add(row);
    }
}
