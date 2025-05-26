package com.st7530.MediaLibraryManager;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.jsontype.impl.LaissezFaireSubTypeValidator;
import com.st7530.MediaLibraryManager.data.*;
import com.st7530.MediaLibraryManager.frame.ShowLibraryFrame;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;

public class Main {
    public static List<Resource> res = new ArrayList<>();
    public static boolean isChanged = false;
    public static void main(String[] args) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.activateDefaultTyping(LaissezFaireSubTypeValidator.instance, ObjectMapper.DefaultTyping.NON_FINAL);

        try {
            System.out.println("Loading items...");
            List<Resource> tmp = mapper.readValue(
                    new File("Library.json"),
                    new TypeReference<List<Resource>>() {
                    }
            );
            res.addAll(tmp);
        } catch (IOException ex) {
            System.out.println("No existing library found, generating an example one...");
            Resource tmp = new Book(1, "示例标题", "示例作者", "adult", "示例出版社", "978-3-16-148410-0", 75);
            Resource tmp2 = new VCD(2, "示例标题", "示例作者", "child", "示例出品者", 2000, 100);
            mapper.writerWithDefaultPrettyPrinter().writeValue(new File("Library.json"), Arrays.asList(tmp, tmp2));
            System.out.println("You need to restart the app manually.");
            System.exit(0);
        }
        System.out.println("Items loaded!");

        new ShowLibraryFrame().setVisible(true); // 显示物品库窗口
    }
}