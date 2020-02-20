package com.jiangsen.script.gitbook.model;

import java.nio.file.Path;

/**
 * <p>Descriptions...
 *
 * @author Johnson
 * @date 2020/2/19.
 */
public class Readme extends AbstractFileGenerator {
    
    public Readme(Book book) {
        super(book);
        super.fileName = "README.md";
    }
    
    @Override
    protected AbstractFileGenerator structure() {
        super.structure();
        String content = this.content.toString().replace(".md", ".html");
        this.content = new StringBuffer(content);
        return this;
    }
}
