package com.jiangsen.script.gitbook.model;

import java.nio.file.Path;

/**
 * <p>Descriptions...
 *
 * @author Johnson
 * @date 2020/2/19.
 */
public class Book extends Directory {
    private Readme readme;
    private Summary summary;
    
    public Book strusture() {
        summary = new Summary(this);
        readme = new Readme(this);
        return this;
    }
    
    public Book generator(){
        if (summary == null || readme == null){
            strusture();
        }
        summary.generate();
        readme.generate();
        return this;
    }
    
    public Book(Path path) {
        super(path);
    }
    
    public Readme getReadme() {
        return readme;
    }
    
    public Book setReadme(Readme readme) {
        this.readme = readme;
        return this;
    }
    
    public Summary getSummary() {
        return summary;
    }
    
    public Book setSummary(Summary summary) {
        this.summary = summary;
        return this;
    }
    
    /**
     * toString
     */
    @Override
    public String toString() {
        return "Book{" +
                       "readme=" + readme +
                       ", summary=" + summary +
                       ", files=" + files +
                       ", path=" + path +
                       '}';
    }
}
