package com.st7530.MediaLibraryManager.frame;

import com.st7530.MediaLibraryManager.data.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.Vector;

import static com.st7530.MediaLibraryManager.Main.res;

public class FindItemFrame extends JFrame {

    JTable table = new JTable();
    DefaultTableModel tableModel = new DefaultTableModel();

    public FindItemFrame() {
        super("查找物品 - 媒体库管理系统");

        JPanel root = new JPanel();
        root.setLayout(new BorderLayout());
        this.setContentPane(root);
        this.setSize(1000, 400);

        // 选择查找方式
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
        inputPanel.add(searchButton);
        root.add(inputPanel, BorderLayout.NORTH);
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
                    switch (type) {
                        case 0:
                            isFound = true;
                            break;
                        case 1:
                            if (r instanceof Book) {
                                isFound = true;
                            }
                            break;
                        case 2:
                            if (r instanceof VCD) {
                                isFound = true;
                            }
                            break;
                        case 3:
                            if (r instanceof Picture) {
                                isFound = true;
                            }
                            break;
                    }
                }
                if (isFound) {
                    Vector<Object> rowData = new Vector<>();
                    rowData.add(r.getId());
                    rowData.add(r.getTitle());
                    rowData.add(r.getAuthor());
                    rowData.add(r.getRate());
                    switch (r) {
                        case Book book -> {
                            rowData.add(book.getPress());
                            rowData.add(book.getIsbn());
                            rowData.add(book.getPage());
                        }
                        case VCD vcd -> {
                            rowData.add(vcd.getName());
                            rowData.add(vcd.getYear());
                            rowData.add(vcd.getPeriod());
                        }
                        case Picture picture -> {
                            rowData.add(picture.getNation());
                            rowData.add(picture.getLength());
                            rowData.add(picture.getWidth());
                        }
                        default -> {
                        }
                    }
                    tableModel.addRow(rowData);
                }
            }
        });

        table.setFillsViewportHeight(true);
        table.setRowSelectionAllowed(true); // 整行选择
        table.setModel(tableModel);
        root.add(new JScrollPane(table), BorderLayout.CENTER); // 滚动条支持

        tableModel.addColumn("编号");
        tableModel.addColumn("标题");
        tableModel.addColumn("作者");
        tableModel.addColumn("评级");
        tableModel.addColumn("出版社/出品者/出品国籍");
        tableModel.addColumn("ISBN 号/出品年份/长");
        tableModel.addColumn("页数/视频时长/宽");

        this.setVisible(true);
    }

    private void addInputRow(JPanel panel, String label, JComponent field) {
        JPanel row = new JPanel(new FlowLayout(FlowLayout.LEFT));
        row.add(new JLabel(label));
        row.add(field);
        panel.add(row);
    }
}
