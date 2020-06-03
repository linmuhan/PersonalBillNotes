package com.billsystem.mapper;

import com.billsystem.pojo.Category;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface CategoryMapper {

    List<Category> queryListByUid(int uid);

    int addCategory(Category category);

    int deleteCategory(Category category);

    Category queryCategory(int id);

    Category queryCategoryByIdAndUid(Category category);

    int updateCategory(Category category);

}
