package com.example.demo.service;

import java.util.List;

import com.example.demo.domain.Book;

public interface BookService {
    /** 查询所有 */
    List<Book> findAll();
    /** 新增 */
    void insert(Book book);
    /** 更新 */
    Book update(Book book);
    /** 删除 */
    Book delete(Long id);
    /** 根据ID查找 */
    Book findById(Long id);
    /** 搜索是否存在 */
    boolean exist(Book book);
    /** 根据名字查询 */
    Book findByName(String name);
}
