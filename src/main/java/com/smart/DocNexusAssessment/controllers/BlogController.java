package com.smart.DocNexusAssessment.controllers;

import com.smart.DocNexusAssessment.entity.BlogEntity;
import com.smart.DocNexusAssessment.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/blogs")
public class BlogController {
    @Autowired
    private BlogService blogService;


    @PostMapping("/create")
    public ResponseEntity<BlogEntity> createBlog(@RequestBody BlogEntity blogEntity) {
        BlogEntity createdBlog = blogService.createBlog(blogEntity);
        return new ResponseEntity<>(createdBlog, HttpStatus.CREATED);
    }


    @GetMapping("/all")
    public ResponseEntity<List<BlogEntity>> getAllBlogs() {
        List<BlogEntity> blogs = blogService.getAllBlogs();
        return new ResponseEntity<>(blogs, HttpStatus.OK);
    }

    // Retrieve a single blog post by its ID
    @GetMapping("get/{id}")
    public ResponseEntity<BlogEntity> getBlogById(@PathVariable Integer id) {
        BlogEntity blog = blogService.getBlogById(id);
        if (blog != null) {
            return new ResponseEntity<>(blog, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    @PutMapping("put/{id}")
    public ResponseEntity<BlogEntity> updateBlog(@PathVariable Integer id, @RequestBody BlogEntity updatedBlog) {
        BlogEntity blog = blogService.updateBlog(id, updatedBlog);
        if (blog != null) {
            return new ResponseEntity<>(blog, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    @DeleteMapping("delete/{id}")
    public ResponseEntity<Void> deleteBlog(@PathVariable Integer id) {
        boolean deleted = blogService.deleteBlog(id);
        if (deleted) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
