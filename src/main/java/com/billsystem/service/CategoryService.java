package com.billsystem.service;

import com.billsystem.mapper.CategoryMapper;
import com.billsystem.pojo.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    @Autowired
    private CategoryMapper categoryMapper;

    public void addCategory(Category category){
        categoryMapper.addCategory(category);
    }

    public void deleteCategory(Category category){
        categoryMapper.deleteCategory(category);
    }

    public Category queryCategoryByIdAndUid(Category category){
        return categoryMapper.queryCategoryByIdAndUid(category);
    }

    public Category queryCategory(int id){
        return categoryMapper.queryCategory(id);
    }

    public List<Category> queryListByUid(int uid){
        return categoryMapper.queryListByUid(uid);
    }

    public void updateCategory(Category category){
        categoryMapper.updateCategory(category);
    }

}
