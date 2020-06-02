package com.billsystem.controller;


import com.billsystem.pojo.Category;
import com.billsystem.service.CategoryService;
import com.billsystem.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @PostMapping("/addCategory")
    public Object addCategory(@RequestBody Category category){
        try{
            //System.out.println(category.getName()+" "+category.getUid());
            categoryService.addCategory(category);
            return Result.success();
        }catch (Exception e){
            e.printStackTrace();
            return Result.fail("error");
        }
    }

    @GetMapping("/listCategory/{uid}")
    public Object listCategory(@PathVariable int uid){
        List<Category> lists = categoryService.queryListByUid(uid);
        try{
            return Result.success(lists);
        }catch (Exception e){
            return Result.fail("error");
        }
    }

    //因为数据量大，所以就没有用delete的请求来操作
    @PostMapping("/deleteCategory")
    public Object deleteCategory(@RequestBody Category category){
        try{
            categoryService.deleteCategory(category);
            return Result.success();
        }catch (Exception e){
            e.printStackTrace();
            return Result.fail("error");
        }
    }

    @PutMapping("/updateCategory")
    public Object updateCategory(@RequestBody Category category){
        try{
            categoryService.updateCategory(category);
            return Result.success();
        }catch (Exception e){
            e.printStackTrace();
            return Result.fail("error");
        }
    }

}
