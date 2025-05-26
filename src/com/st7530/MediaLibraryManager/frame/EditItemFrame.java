package com.st7530.MediaLibraryManager.frame;

import com.st7530.MediaLibraryManager.data.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import static com.st7530.MediaLibraryManager.Main.isChanged;
import static com.st7530.MediaLibraryManager.Main.res;

public class EditItemFrame extends JFrame {
    public EditItemFrame(int index) {
        super("编辑物品 - 媒体库管理系统");
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
        int indexType = switch (res.get(index)) {
            case Book book -> // 图书
                indexType = 0;
            case VCD vcd -> // 视频光盘
                indexType = 1;
            case Picture picture -> // 图画
                indexType = 2;
            default -> -1; // 未选择
        };
        typeBox.setSelectedIndex(indexType);
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
            idField.setText(Integer.toString(res.get(index).getId()));
            titleField.setText(res.get(index).getTitle());
            authorField.setText(res.get(index).getAuthor());
            rateField.setText(res.get(index).getRate());
            int choice = typeBox.getSelectedIndex();
            switch (choice) {
                case 0: // 图书
                    addInputRow(inputPanel, "出版社：", pressField);
                    addInputRow(inputPanel, "ISBN 号：", isbnField);
                    addInputRow(inputPanel, "页数：", pageField);
                    pressField.setText(((Book) res.get(index)).getPress());
                    isbnField.setText(((Book) res.get(index)).getIsbn());
                    pageField.setText(Integer.toString(((Book) res.get(index)).getPage()));
                    break;
                case 1: // 视频光盘
                    addInputRow(inputPanel, "出品者：", nameField);
                    addInputRow(inputPanel, "出品年份：", yearField);
                    addInputRow(inputPanel, "视频时长：", periodField);
                    nameField.setText(((VCD) res.get(index)).getName());
                    yearField.setText(Integer.toString(((VCD) res.get(index)).getYear()));
                    periodField.setText(Integer.toString(((VCD) res.get(index)).getPeriod()));
                    break;
                case 2: // 图画
                    addInputRow(inputPanel, "出口国籍：", nationField);
                    addInputRow(inputPanel, "长：", lengthField);
                    addInputRow(inputPanel, "宽：", widthField);
                    nationField.setText(((Picture) res.get(index)).getNation());
                    lengthField.setText(Integer.toString(((Picture) res.get(index)).getLength()));
                    widthField.setText(Integer.toString(((Picture) res.get(index)).getWidth()));
                    break;
            }
            isChanged = true;
            inputPanel.revalidate();
            inputPanel.repaint();
        });
        root.add(inputPanel, BorderLayout.CENTER);

        // 底部按钮
        JButton saveButton = new JButton("完成");
        saveButton.addActionListener(e -> {
            int choice = typeBox.getSelectedIndex();
            switch (choice) {
                case 0: // 图画
                    res.set(index, new Book(
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
                    res.set(index, new VCD(
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
                    res.set(index, new Picture(
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
        root.add(saveButton, BorderLayout.SOUTH);
    }

    private void addInputRow(JPanel panel, String label, JTextField field) {
        JPanel row = new JPanel(new FlowLayout(FlowLayout.LEFT));
        row.add(new JLabel(label));
        row.add(field);
        panel.add(row);
    }
}
