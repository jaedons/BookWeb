package com.example.demo;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.demo.domain.Book;
import com.example.demo.service.BookService;

/**
 * 业务层测试
 * @author Administrator
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class BookServiceTest {
    @Autowired
    @Qualifier("bookServiceDB")
    private BookService  bookService;
    
    @Test
    public void findById() {
        Book book = bookService.findById(2L);
        // 断言
        Assert.assertSame("《钢铁是怎么炼成的》", book.getName());
    }
}
