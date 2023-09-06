package com.smart.DocNexusAssessment.service;

import com.smart.DocNexusAssessment.entity.BlogEntity;
import com.smart.DocNexusAssessment.rapo.BlogRapo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BlogService {
    private final BlogRapo blogRapo;

    @Autowired
    public BlogService(BlogRapo blogRapo) {
        this.blogRapo = blogRapo;
    }

    // Create a new blog post
    public BlogEntity createBlog(BlogEntity blogEntity) {
        return blogRapo.save(blogEntity);
    }

    // Retrieve all blog posts
    public List<BlogEntity> getAllBlogs() {
        return (List<BlogEntity>) blogRapo.findAll();
    }

    // Retrieve a single blog post by its ID
    public BlogEntity getBlogById(Integer id) {
        Optional<BlogEntity> optionalBlog = blogRapo.findById(id);
        return optionalBlog.orElse(null);
    }

    // Update an existing blog post
    public BlogEntity updateBlog(Integer id, BlogEntity updatedBlog) {
        Optional<BlogEntity> optionalBlog = blogRapo.findById(id);
        if (optionalBlog.isPresent()) {
            BlogEntity existingBlog = optionalBlog.get();
            existingBlog.setTitle(updatedBlog.getTitle());
            existingBlog.setContent(updatedBlog.getContent());
            // You can also update other fields here as needed
            return blogRapo.save(existingBlog);
        }
        return null;
    }

    // Delete a blog post by its ID
    public boolean deleteBlog(Integer id) {
        Optional<BlogEntity> optionalBlog = blogRapo.findById(id);
        if (optionalBlog.isPresent()) {
            blogRapo.deleteById(id);
            return true;
        }
        return false;
    }
}

