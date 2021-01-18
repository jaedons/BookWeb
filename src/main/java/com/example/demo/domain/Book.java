package com.example.demo.domain;

import java.io.Serializable;

import org.springframework.format.annotation.NumberFormat;

public class Book implements Serializable {
    private static final long serialVersionUID = 1L;
    /** 编号 */
    @NumberFormat
    private Long id;
    /** 书名 */
    private String name;
    /** 作者 */
    private String writer;
    /** 简介 */
    private String introduction;
    /** 首版时间 */
    private String versionDate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getWriter() {
        return writer;
    }

    public void setWriter(String writer) {
        this.writer = writer;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }
    

    public String getVersionDate() {
        return versionDate;
    }

    public void setVersionDate(String versionDate) {
        this.versionDate = versionDate;
    }

    public static long getSerialversionuid() {
        return serialVersionUID;
    }

    public Book() {
    }

    public Book(Long id, String name) {
        super();
        this.id = id;
        this.name = name;
    }

    public Book(String name) {
        super();
        this.name = name;
    }

    public Book(Long id, String name, String writer, String introduction, String versionDate) {
        super();
        this.id = id;
        this.name = name;
        this.writer = writer;
        this.introduction = introduction;
        this.versionDate = versionDate;
    }

    public Book(Long id, String name, String writer, String introduction) {
        super();
        this.id = id;
        this.name = name;
        this.writer = writer;
        this.introduction = introduction;
    }

    @Override
    public String toString() {
        return "Book [id=" + id + ", name=" + name + ", writer=" + writer + ", introduction=" + introduction + "]";
    }

}
