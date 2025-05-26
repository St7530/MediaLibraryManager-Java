package com.st7530.MediaLibraryManager.frame;

import com.st7530.MediaLibraryManager.data.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

import static com.st7530.MediaLibraryManager.Main.*;

public class FindItemFrame extends JFrame {

    public FindItemFrame() {
        super("查找物品 - 媒体库管理系统");
        this.setSize(1100, 400);

        JPanel root = new JPanel();
        root.setLayout(new BorderLayout());
        this.setContentPane(root);

        JTable table = new JTable();
        DefaultTableModel tableModel = new DefaultTableModel();

        // 顶部输入框、选择框和按钮
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new FlowLayout());

        JTextField idField = new JTextField(10);
        JTextField titleField = new JTextField(10);
        JComboBox<String> typeBox = new JComboBox<>(new String[]{"[不限]", "图书", "视频光盘", "图画"});
        inputPanel.add(new JLabel("编号："));
        inputPanel.add(idField);
        inputPanel.add(new JLabel("标题："));
        inputPanel.add(titleField);
        inputPanel.add(new JLabel("物品类型："));
        inputPanel.add(typeBox);
        JButton searchButton = new JButton("查找");
        searchButton.addActionListener(e -> {
            tableModel.setRowCount(0); // 清除表格内容
            int id;
            try {
                id = Integer.parseInt(idField.getText());
            } catch (NumberFormatException e1) {
                id = -1;
            }
            String title = titleField.getText();
            int type = typeBox.getSelectedIndex();

            for (Resource r : res) {
                boolean isFound = false;
                if ((id == -1 || r.getId() == id) && (title.isEmpty() || r.getTitle().equals(title))) {
                    isFound = switch (type) {
                        case 0 -> // [不限]
                                true;
                        case 1 -> // 图书
                                r instanceof Book;
                        case 2 -> // 视频光盘
                                r instanceof VCD;
                        case 3 -> // 图画
                                r instanceof Picture;
                        default -> false;
                    };
                }
                if (isFound) {
                    tableModel.addRow(r.show());
                }
            }
        });
        inputPanel.add(searchButton);
        root.add(inputPanel, BorderLayout.NORTH);

        // 中部表格
        table.setFillsViewportHeight(true);
        table.setRowSelectionAllowed(true); // 整行选择
        table.setModel(tableModel);
        root.add(new JScrollPane(table), BorderLayout.CENTER); // 滚动条支持

        tableModel.addColumn("编号");
        tableModel.addColumn("类型");
        tableModel.addColumn("标题");
        tableModel.addColumn("作者");
        tableModel.addColumn("评级");
        tableModel.addColumn("出版社/出品者/出品国籍");
        tableModel.addColumn("ISBN 号/出品年份/长");
        tableModel.addColumn("页数/视频时长/宽");
    }
}
