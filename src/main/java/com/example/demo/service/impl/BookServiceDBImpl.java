package com.example.demo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.domain.Book;
import com.example.demo.mapper.BookMapper;
import com.example.demo.service.BookService;

@Transactional
@Service("bookServiceDB")
public class BookServiceDBImpl implements BookService {

    @Autowired
    private BookMapper bookMapper;
    @Override
    public List<Book> findAll() {
        return bookMapper.findAll();
    }

    @Override
    public void insert(Book book) {
        bookMapper.insert(book);
    }

    @Override
    public Book update(Book book) {
        bookMapper.update(book);
        return bookMapper.findById(book.getId());
    }

    @Override
    public Book delete(Long id) {
        Book book = bookMapper.findById(id);
        bookMapper.delete(id);
        return book;
    }

    @Override
    public Book findById(Long id) {
        return bookMapper.findById(id);
    }

    @Override
    public boolean exist(Book book) {
        return bookMapper.findByName(book.getName()) != null;
    }

    @Override
    public Book findByName(String name) {
        return bookMapper.findByName(name);
    }

}
