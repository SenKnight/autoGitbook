package com.jiangsen.script.gitbook;

import com.jiangsen.script.gitbook.model.Book;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.*;

/**
 * 为gitbook自动生成SUMMARY.md
 *
 * @author Johnson
 * @date 2020/2/18.
 */
public class AutoStructure {
    public static void main(String[] args) throws URISyntaxException, IOException {
        URI uri = AutoStructure.class.getProtectionDomain().getCodeSource().getLocation().toURI();
        System.out.println("uri=" + uri);
        Path path = Paths.get(uri);
        if (path.toString().endsWith(".jar")){
            path = path.getParent();
        }
        Book book = new Book(path);
        book.listAllFile();
        book.generator();
        System.out.println("SUMMARY.md和README.md生成成功");
    }
}
