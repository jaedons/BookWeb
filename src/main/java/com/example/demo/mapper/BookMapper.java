package com.example.demo.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.example.demo.domain.Book;


@Mapper
public interface BookMapper {
    /** 查询所有 */
    List<Book> findAll();
    /** 新增 */
    int insert(Book book);
    /** 更新 */
    int update(Book book);
    /** 删除 */
    int delete(@Param(value = "id") Long id);
    /** 根据ID查找 */
    Book findById(@Param(value = "id") Long id);
    /** 搜索是否存在 */
    boolean exist(Book book);
    /** 根据名字查询 */
    Book findByName(String name);
}
