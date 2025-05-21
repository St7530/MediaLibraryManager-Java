package com.st7530.MediaLibraryManager.frame;

import com.st7530.MediaLibraryManager.data.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import static com.st7530.MediaLibraryManager.Main.res;

public class EditItemFrame extends JFrame {
    public EditItemFrame(int index) {
        super("编辑物品 - 媒体库管理系统");

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
        int indexType = -1;
        switch (res.get(index)) {
            case Book book -> {
                indexType = 0;
            }
            case VCD vcd -> {
                indexType = 1;
            }
            case Picture picture -> {
                indexType = 2;
            }
            default -> {
            }
        }
        typeBox.setSelectedIndex(indexType);
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
                case 0:
                    addInputRow(inputPanel, "出版社：", pressField);
                    addInputRow(inputPanel, "ISBN 号：", isbnField);
                    addInputRow(inputPanel, "页数：", pageField);
                    pressField.setText(((Book) res.get(index)).getPress());
                    isbnField.setText(((Book) res.get(index)).getIsbn());
                    pageField.setText(Integer.toString(((Book) res.get(index)).getPage()));
                    break;
                case 1:
                    addInputRow(inputPanel, "出品者：", nameField);
                    addInputRow(inputPanel, "出品年份：", yearField);
                    addInputRow(inputPanel, "视频时长：", periodField);
                    nameField.setText(((VCD) res.get(index)).getName());
                    yearField.setText(Integer.toString(((VCD) res.get(index)).getYear()));
                    periodField.setText(Integer.toString(((VCD) res.get(index)).getPeriod()));
                    break;
                case 2:
                    addInputRow(inputPanel, "出口国籍：", nationField);
                    addInputRow(inputPanel, "长：", lengthField);
                    addInputRow(inputPanel, "宽：", widthField);
                    nationField.setText(((Picture) res.get(index)).getNation());
                    lengthField.setText(Integer.toString(((Picture) res.get(index)).getLength()));
                    widthField.setText(Integer.toString(((Picture) res.get(index)).getWidth()));
                    break;
            }
            inputPanel.revalidate();
            inputPanel.repaint();
        });
        topPanel.add(new JLabel("选择类型："));
        topPanel.add(typeBox);
        root.add(topPanel, BorderLayout.NORTH);

        // 底部按钮
        JButton saveButton = new JButton("完成");
        root.add(saveButton, BorderLayout.SOUTH);
        saveButton.addActionListener(e -> {
            int choice = typeBox.getSelectedIndex();
            switch (choice) {
                case 0:
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
                case 1:
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
                case 2:
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
