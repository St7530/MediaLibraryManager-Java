package com.st7530.MediaLibraryManager.frame;

import com.st7530.MediaLibraryManager.data.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

import static com.st7530.MediaLibraryManager.Main.*;

public class AddItemFrame extends JFrame {
    public AddItemFrame() {
        super("添加物品 - 媒体库管理系统");
        this.setSize(320, 350);
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                new ShowLibraryFrame().setVisible(true);
            }
        });

        JPanel root = new JPanel();
        root.setLayout(new BorderLayout());
        this.setContentPane(root);

        // 顶部选择框
        JPanel typePanel = new JPanel(new FlowLayout());
        typePanel.add(new JLabel("类型："));
        JComboBox<String> typeBox = new JComboBox<>(new String[]{"图书", "视频光盘", "图画"});
        typeBox.setSelectedIndex(-1);
        typePanel.add(typeBox);
        root.add(typePanel, BorderLayout.NORTH);

        // 中部输入框
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new BoxLayout(inputPanel, BoxLayout.Y_AXIS));

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

        typeBox.addActionListener(e -> {
            inputPanel.removeAll();
            addInputRow(inputPanel, "编号：", idField);
            addInputRow(inputPanel, "标题：", titleField);
            addInputRow(inputPanel, "作者：", authorField);
            addInputRow(inputPanel, "评级：", rateField);
            int choice = typeBox.getSelectedIndex();
            switch (choice) {
                case 0: // 图书
                    addInputRow(inputPanel, "出版社：", pressField);
                    addInputRow(inputPanel, "ISBN 号：", isbnField);
                    addInputRow(inputPanel, "页数：", pageField);
                    break;
                case 1: // 视频光盘
                    addInputRow(inputPanel, "出品者：", nameField);
                    addInputRow(inputPanel, "出品年份：", yearField);
                    addInputRow(inputPanel, "视频时长：", periodField);
                    break;
                case 2: // 图画
                    addInputRow(inputPanel, "出口国籍：", nationField);
                    addInputRow(inputPanel, "长：", lengthField);
                    addInputRow(inputPanel, "宽：", widthField);
                    break;
            }
            isChanged = true;
            inputPanel.revalidate();
            inputPanel.repaint();
        });

        root.add(inputPanel, BorderLayout.CENTER);

        // 底部按钮
        JButton addButton = new JButton("添加");
        addButton.addActionListener(e -> {
            int choice = typeBox.getSelectedIndex();
            switch (choice) {
                case 0: // 图书
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
                case 1: // 视频光盘
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
                case 2: // 图画
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
            new ShowLibraryFrame().setVisible(true);
        });
        root.add(addButton, BorderLayout.SOUTH);
    }

    private void addInputRow(JPanel panel, String label, JTextField field) {
        JPanel row = new JPanel(new FlowLayout(FlowLayout.LEFT));
        row.add(new JLabel(label));
        row.add(field);
        panel.add(row);
    }
}
