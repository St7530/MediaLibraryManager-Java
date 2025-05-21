package com.st7530.MediaLibraryManager.frame;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.st7530.MediaLibraryManager.ResourceSerializerModule;
import com.st7530.MediaLibraryManager.data.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Vector;

import static com.st7530.MediaLibraryManager.Main.res;

public class ShowLibraryFrame extends JFrame {
    // 表格控件
    JTable table = new JTable();
    // Model
    DefaultTableModel tableModel = new DefaultTableModel();

    public ShowLibraryFrame() {
        super("物品库 - 媒体库管理系统");
        JPanel root = new JPanel();
        root.setLayout(new BorderLayout());
        this.setContentPane(root);
        this.setSize(1000, 400);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        table.setFillsViewportHeight(true);
        table.setRowSelectionAllowed(true); // 整行选择
        table.setModel(this.tableModel);
        root.add(new JScrollPane(table), BorderLayout.CENTER); // 滚动条支持

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());
        JButton findButton = new JButton("查找物品");
        JButton addButton = new JButton("添加物品");
        JButton editButton = new JButton("编辑选中");
        JButton deleteButton = new JButton("删除选中");
        JButton saveLibraryButton = new JButton("保存更改");
        findButton.addActionListener(e -> {
            FindItemFrame findItemFrame = new FindItemFrame();
        });
        addButton.addActionListener(e -> {
            this.setVisible(false);
            AddItemFrame addItemFrame = new AddItemFrame();
        });
        editButton.addActionListener(e -> {
            if (table.getSelectedRow() != -1) {
                this.setVisible(false);
                EditItemFrame editItemFrame = new EditItemFrame(table.getSelectedRow());
            }
        });
        deleteButton.addActionListener(e -> {
            int[] rows = table.getSelectedRows();
            for (int i = rows.length - 1; i >= 0; i--) {
                res.remove(rows[i]);
            }
            this.setVisible(false);
            ShowLibraryFrame showLibraryFrame = new ShowLibraryFrame();
        });
        saveLibraryButton.addActionListener(e -> {
            ObjectMapper mapper = new ObjectMapper();
            try {
                mapper.registerModule(new ResourceSerializerModule());
                mapper.writerWithDefaultPrettyPrinter().writeValue(new File("Library.json"), res);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });
        buttonPanel.add(findButton);
        buttonPanel.add(addButton);
        buttonPanel.add(editButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(saveLibraryButton);
        root.add(buttonPanel, BorderLayout.NORTH);
        // 表头
        tableModel.addColumn("编号");
        tableModel.addColumn("标题");
        tableModel.addColumn("作者");
        tableModel.addColumn("评级");
        tableModel.addColumn("出版社/出品者/出品国籍");
        tableModel.addColumn("ISBN 号/出品年份/长");
        tableModel.addColumn("页数/视频时长/宽");

        int bookCount = 0, vcdCount = 0, pictureCount = 0;
        for (Resource r : res) {
            // java.util.Vector 类似于 List
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
                    bookCount++;
                }
                case VCD vcd -> {
                    rowData.add(vcd.getName());
                    rowData.add(vcd.getYear());
                    rowData.add(vcd.getPeriod());
                    vcdCount++;
                }
                case Picture picture -> {
                    rowData.add(picture.getNation());
                    rowData.add(picture.getLength());
                    rowData.add(picture.getWidth());
                    pictureCount++;
                }
                default -> {
                }
            }
            tableModel.addRow(rowData); // 添加一行
        }

        JPanel statsPanel = new JPanel();
        statsPanel.add(new JLabel("总物品数：" + (bookCount + vcdCount + pictureCount) + "，图书数：" + bookCount + "，视频光盘数：" + vcdCount + "，图画数：" + pictureCount));
        root.add(statsPanel, BorderLayout.SOUTH);

        this.setVisible(true);
    }
}
