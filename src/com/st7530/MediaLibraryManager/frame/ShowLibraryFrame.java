package com.st7530.MediaLibraryManager.frame;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.st7530.MediaLibraryManager.ResourceSerializerModule;
import com.st7530.MediaLibraryManager.data.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;

import static com.st7530.MediaLibraryManager.Main.*;

public class ShowLibraryFrame extends JFrame {

    public ShowLibraryFrame() {
        super("物品库 - 媒体库管理系统");
        this.setSize(1100, 400);
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                if (isChanged) {
                    int result = JOptionPane.showConfirmDialog(
                            ShowLibraryFrame.this,
                            "有更改尚未保存！仍要退出吗？",
                            "媒体库管理系统",
                            JOptionPane.YES_NO_OPTION
                    );
                    if (result == JOptionPane.YES_OPTION) {
                        System.exit(0);
                    }
                } else {
                    System.exit(0);
                }
            }
        });

        JPanel root = new JPanel();
        root.setLayout(new BorderLayout());
        this.setContentPane(root);

        JTable table = new JTable();
        DefaultTableModel tableModel = new DefaultTableModel();
        table.setFillsViewportHeight(true);
        table.setRowSelectionAllowed(true); // 整行选择
        table.setModel(tableModel);
        root.add(new JScrollPane(table), BorderLayout.CENTER); // 滚动条支持

        // 顶部按钮
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());
        JButton findButton = new JButton("查找物品");
        JButton addButton = new JButton("添加物品");
        JButton editButton = new JButton("编辑选中");
        JButton deleteButton = new JButton("删除选中");
        JButton saveLibraryButton = new JButton("保存更改");

        findButton.addActionListener(e -> {
            new FindItemFrame().setVisible(true);
        });
        addButton.addActionListener(e -> {
            this.setVisible(false);
            new AddItemFrame().setVisible(true);
        });
        editButton.addActionListener(e -> {
            if (table.getSelectedRow() != -1) {
                this.setVisible(false);
                new EditItemFrame(table.getSelectedRow()).setVisible(true);
            }
        });
        deleteButton.addActionListener(e -> {
            int[] rows = table.getSelectedRows();
            for (int i = rows.length - 1; i >= 0; i--) { // 倒序删除，保证索引不变
                res.remove(rows[i]);
            }
            isChanged = true;
            this.setVisible(false);
            new ShowLibraryFrame().setVisible(true);
        });
        saveLibraryButton.addActionListener(e -> {
            ObjectMapper mapper = new ObjectMapper();
            mapper.registerModule(new ResourceSerializerModule());
            try {
                mapper.writerWithDefaultPrettyPrinter().writeValue(new File("Library.json"), res);
                isChanged = false;
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

        // 中部表格
        tableModel.addColumn("编号");
        tableModel.addColumn("类型");
        tableModel.addColumn("标题");
        tableModel.addColumn("作者");
        tableModel.addColumn("评级");
        tableModel.addColumn("出版社/出品者/出品国籍");
        tableModel.addColumn("ISBN 号/出品年份/长");
        tableModel.addColumn("页数/视频时长/宽");

        int bookCount = 0, vcdCount = 0, pictureCount = 0;
        for (Resource r : res) {
            switch (r) {
                case Book book:
                    bookCount++;
                    break;
                case VCD vcd:
                    vcdCount++;
                    break;
                case Picture picture:
                    pictureCount++;
                    break;
                default:
                    break;
            }
            tableModel.addRow(r.show());
        }

        // 底部统计数据
        JPanel statsPanel = new JPanel();
        statsPanel.add(new JLabel("总物品数：" + (bookCount + vcdCount + pictureCount) + "，图书数：" + bookCount + "，视频光盘数：" + vcdCount + "，图画数：" + pictureCount));
        root.add(statsPanel, BorderLayout.SOUTH);
    }
}
