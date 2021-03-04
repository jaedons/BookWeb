package com.example.demo.web;

import java.util.List;

import javax.servlet.http.HttpSession;

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

import com.example.demo.constant.MessageEnum;
import com.example.demo.domain.Book;
import com.example.demo.domain.Result;
import com.example.demo.exception.ContextException;
import com.example.demo.service.BookService;
import com.example.demo.util.MessageUtil;
import com.example.demo.util.RedisUtil;

/** 控制层 */

@RestController
@RequestMapping(value = "book")
public class BookController {
    private final Logger LOG = LoggerFactory.getLogger(BookController.class);

    @Autowired
    @Qualifier("bookServiceDB")
    private BookService bookService;

    
    @Autowired
    private RedisUtil redisUtil;
    /**
     * <pre>
     *  获取 book 列表
     * </pre>
     * 
     * <pre>
     *  通过 "/book" 的 GET 请求 ，用来获取 book列表
     * </pre>
     */
    @SuppressWarnings("unchecked")
    @RequestMapping(method = RequestMethod.GET)
    public Result<List<Book>> getBookList(HttpSession session) {
        String id = session.getId();
        LOG.info("SESSION_ID:{}",id);
        Boolean isLogin = (Boolean) session.getAttribute("isLogin");
        if(null == isLogin || false ==isLogin) {
            throw new ContextException(MessageEnum.NOT_LOGIN);
        }
        List<Book> books = (List<Book>) redisUtil.get("all-book");
        if(null == books) {
            books = bookService.findAll();
            redisUtil.set("all-book", books, 60l);
        }
        if(null == books) {
            int code = MessageUtil.ERROR_CODE1;
            return MessageUtil.error(code,"数据为空");
        }
        return MessageUtil.success(books);
    }

    /**
    * 获取 Book
     * 处理 "/book/{id}" 的 GET 请求，用来获取 Book 信息
     */
    @RequestMapping(value="/{id}",method = RequestMethod.GET)
    public Result<Book> getBook(@PathVariable Long id) {
        LOG.info("查询:id{}",id);
        Book book = bookService.findById(id);
        if(null == book) {
            int code = MessageUtil.ERROR_CODE1;
            String message = String.format("未查到id - %d 对应的数据", id);
            return MessageUtil.error(code,message);
        }
        return MessageUtil.success(book);
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
    public Result<Book> putBook(@RequestBody Book book) {
        LOG.info("更改 :book-{}",book.toString());
        Book bookUpdated = bookService.update(book);
        if(null == bookUpdated) {
            int code = MessageUtil.ERROR_CODE1;
            String message = String.format("s% 插入出现错误", book.toString());
            return MessageUtil.error(code,message);
        }
        return MessageUtil.success(book);
    }
    
    /**
     * 删除 Book
     * 处理 "/book/{id}" 的 GET 请求，用来删除 Book 信息
     */
    @RequestMapping(value="/delete/{id}",method = RequestMethod.DELETE)
    public Result<Book> deleteBook(@PathVariable Long id) {
        LOG.info("删除:id-{}",id);
        Book bookDeleted = bookService.delete(id);
        if(null == bookDeleted) {
            int code = MessageUtil.ERROR_CODE1;
            String message = String.format("%s 删除出现错误", id.toString().toUpperCase());
            return MessageUtil.error(code,message);
        }
        return MessageUtil.success(bookDeleted);
    }

}
