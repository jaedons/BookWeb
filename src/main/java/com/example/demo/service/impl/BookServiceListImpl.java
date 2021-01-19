package com.example.demo.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.stereotype.Service;

import com.example.demo.domain.Book;
import com.example.demo.service.BookService;

/**
 * Book 业务实现层
 * 
 * @author Administrator
 *
 */
@Service("bookService")
public class BookServiceListImpl implements BookService {

    private static final AtomicLong counter = new AtomicLong();

    /**使用集合模拟数据库*/
    private static List<Book> books = new ArrayList<Book>(Arrays.asList(new Book(counter.incrementAndGet(), "book")));

    /** 模拟数据库，存储Book信息 */
    private static Map<String, Book> BOOK_DB = new HashMap<String, Book>();
    
    @Override
    public List<Book> findAll() {
        return new ArrayList<Book>(BOOK_DB.values());
    }

    @Override
    public void insert(Book book) {
        book.setId(BOOK_DB.size()+1L);
        BOOK_DB.put(book.getId().toString(), book);
    }

    @Override
    public Book update(Book book) {
        BOOK_DB.put(book.getId().toString(), book);
        return book;
    }

    @Override
    public Book delete(Long id) {
        return BOOK_DB.remove(id.toString());
    }

    @Override
    public Book findById(Long id) {
        return BOOK_DB.get(id.toString());
    }

    @Override
    public boolean exist(Book book) {
        return findByName(book.getName())!=null;
    }

    @Override
    public Book findByName(String name) {
        for(Book book : books) {
            if(book.getName().equals(name)) {
                return book;
            }
        }
        return null;
    }

}
