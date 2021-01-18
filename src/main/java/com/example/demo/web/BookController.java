package com.example.demo.web;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.example.demo.domain.Book;
import com.example.demo.service.BookService;

/** 控制层 */

@RestController
@RequestMapping(value = "book")
public class BookController {
    private final Logger LOG = LoggerFactory.getLogger(BookController.class);

    @Autowired
    @Qualifier("bookServiceDB")
    private BookService bookService;

    /**
     * <pre>
     *  获取 book 列表
     * </pre>
     * 
     * <pre>
     *  通过 "/book" 的 GET 请求 ，用来获取 book列表
     * </pre>
     */
    @RequestMapping(method = RequestMethod.GET)
    public List<Book> getBookList() {
        return bookService.findAll();
    }

    /**
    * 获取 Book
     * 处理 "/book/{id}" 的 GET 请求，用来获取 Book 信息
     */
    @RequestMapping(value="/{id}",method = RequestMethod.GET)
    public Book getBook(@PathVariable Long id) {
        LOG.info("查询:id{}",id);
        return bookService.findById(id);
    }
    
    /**
     * * 创建 Book
     * 处理 "/book/create" 的 POST 请求，用来新建 Book 信息
     * 通过 @RequestBody 绑定实体参数，也通过 @RequestParam 传递参数
     * @param book
     * @param builder
     * @return
     */
    @RequestMapping(value="/create",method = RequestMethod.POST)
    public ResponseEntity<Void> postBook(@RequestBody Book book,UriComponentsBuilder builder){
        LOG.info("creating new book:{}",book);
        if(book.getName().equals("conflict")) {
            LOG.info("a book with name {} already exists.",book.getName());
            return new ResponseEntity<Void>(HttpStatus.CONFLICT);
        }
        bookService.insert(book);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(builder.path("/book/{id}").buildAndExpand(book.getId()).toUri());
        return new ResponseEntity<>(headers,HttpStatus.CREATED);
    }
    
    /**
     * 更新 Book
     * 处理 "/update" 的 PUT 请求，用来更新 Book 信息
     */
    @RequestMapping(value="/update",method = RequestMethod.PUT)
    public Book putBook(@RequestBody Book book) {
        LOG.info("更改 :book-{}",book.toString());
        return bookService.update(book);
    }
    
    /**
     * 删除 Book
     * 处理 "/book/{id}" 的 GET 请求，用来删除 Book 信息
     */
    @RequestMapping(value="/delete/{id}",method = RequestMethod.DELETE)
    public Book deleteBook(@PathVariable Long id) {
        LOG.info("删除:id-{}",id);
        return bookService.delete(id);
    }

}
