package com.jiangsen.script.gitbook.model;

import java.io.FileOutputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

/**
 * <p>Descriptions...
 *
 * @author Johnson
 * @date 2020/2/19.
 */
public abstract class AbstractFileGenerator {
    protected Book book;
    protected StringBuffer content;
    protected String fileName = "SUMMARY.md";
    
    public AbstractFileGenerator(Book book) {
        this.book = book;
        structure();
    }
    
    protected String getFileName(Path path) {
        return path.toString().substring(book.getPath().toString().length() + 1);
    }
    
    public AbstractFileGenerator generate() {
        if (content == null) {
            structure();
        }
        try (OutputStream out = new FileOutputStream(book.getPath().resolve(fileName).toFile())) {
            out.write(content.toString().getBytes("UTF-8"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return this;
    }
    
    protected AbstractFileGenerator structure() {
        content = new StringBuffer("# 目录\n\n");
        loop(book.getFiles());
        return this;
    }
    
    protected AbstractFileGenerator loop(List<File> files) {
        for (File file : files) {
            addLink(file);
            if (file instanceof Directory){
                loop(((Directory)file).getFiles());
            }
        }
        return this;
    }
    
    protected AbstractFileGenerator addLink(File file) {
        int level = file.getLevel();
        String relativePath = getFileName(file.getPath());
        if (file instanceof Directory) {
            relativePath = "";
        }
        String fileName = file.getPath().getFileName().toString().replace(".md", "");
        StringBuffer star = new StringBuffer();
        for (int i = 1; i < level; i++) {
            star.append("  ");
        }
        content.append(star)
                .append("*")
                .append(" ")
                .append("[")
                .append(fileName)
                .append("](")
                .append(relativePath)
                .append(")\n");
        return this;
    }
    
    public StringBuffer getContent() {
        return content;
    }
    
    public void setContent(StringBuffer content) {
        this.content = content;
    }
}
