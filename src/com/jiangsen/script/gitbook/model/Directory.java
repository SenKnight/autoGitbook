package com.jiangsen.script.gitbook.model;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * <p>Descriptions...
 *
 * @author Johnson
 * @date 2020/2/19.
 */
public class Directory extends File {
    protected List<File> files = new ArrayList<>();
    
    public Directory listAllFile() {
        if (!Files.isDirectory(path)) {
            return this;
        }
        try {
            Files.list(path)
                    .filter(x -> {
                        String filePath = x.toString();
                        boolean isDirectory = Files.isDirectory(x);
                        if (!isDirectory && !filePath.endsWith(".md")) {
                            return false;
                        } else if (filePath.endsWith("README.md")
                                           || filePath.endsWith("SUMMARY.md")
                                           || filePath.indexOf("node_modules") != -1
                                           || filePath.indexOf("_book") != -1
                                           || filePath.indexOf(".git") != -1) {
                            return false;
                        } else {
                            return true;
                        }
                    })
                    .sorted(Comparator.comparing(Path::toString))
                    .forEach(p -> {
                        if (Files.isDirectory(p)) {
                            Directory d = new Directory(p, level + 1);
                            addFile(d);
                            d.listAllFile();
                        } else {
                            addFile(new File(p, level + 1));
                        }
                    });
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return this;
    }
    
    public Directory(Path path) {
        super(path);
    }
    
    public Directory(Path path, int level) {
        super(path, level);
    }
    
    public Directory addFile(File file) {
        if (files == null) {
            files = new ArrayList<>();
        }
        files.add(file);
        return this;
    }
    
    public List<File> getFiles() {
        return files;
    }
    
    public Directory setFiles(List<File> files) {
        this.files = files;
        return this;
    }
    
    /**
     * toString
     */
    @Override
    public String toString() {
        return "Directory{" +
                       ", files=" + files +
                       ", level=" + level +
                       ", path=" + path +
                       '}';
    }
}
