package com.jiangsen.script.gitbook.model;

import java.nio.file.Path;

/**
 * <p>Descriptions...
 *
 * @author Johnson
 * @date 2020/2/19.
 */
public class File {
    protected Path path;
    protected int level = 0;
    
    public File(Path path, int level) {
        this.path = path;
        this.level = level;
    }
    
    public File(Path path) {
        this.path = path;
    }
    
    public Path getPath() {
        return path;
    }
    
    public File setPath(Path path) {
        this.path = path;
        return this;
    }
    
    public int getLevel() {
        return level;
    }
    
    public void setLevel(int level) {
        this.level = level;
    }
    
    /**
     * toString
     */
    @Override
    public String toString() {
        return "File{" +
                       "path=" + path +
                       ", level=" + level +
                       '}';
    }
}
