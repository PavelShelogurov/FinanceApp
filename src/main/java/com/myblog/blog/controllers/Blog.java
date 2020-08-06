package com.myblog.blog.controllers;

import com.myblog.blog.models.Post;
import com.myblog.blog.repo.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.Optional;

@Controller
public class Blog {

    //GetMapping отслеживает сслыку
    //PostMapping отслеживате данные получаемые из формы

    @Autowired //анотация для создания переменной, которая ссылвается на репозиторий
    private PostRepository postRepository;

    @GetMapping("/blog")
    public String blog(Model model){
        Iterable<Post> posts = postRepository.findAll(); //массив данных, в котором содержаться все значения из таблицы базы данных
        model.addAttribute("posts", posts);
        return "blog";
    }

    @GetMapping("/blog/add")
    public String blogAdd(Model model){
        return "blog-add";
    }

    @PostMapping("/blog/add")
    public String blogPostAdd(@RequestParam String title,@RequestParam String anons, @RequestParam String fullText, Model model){
        //@RequestParam получает пораметры из HTML документа из поле с атрибутом "name"
        Post post = new Post (title, anons, fullText); //передаем полученные параметры в объект класса Post (для этого в классе Post надо создать конструктор с такими параметрами)
        postRepository.save(post); //сохраняет все в базу данных в табличку Post, а сохраняем мы объект post класса Post
        return "redirect:/blog";//переадресовываем пользователя на страницу /blog
    }

    @GetMapping("/blog/{id}")
    public String readArticle(@PathVariable(value = "id") long postId, Model model) {
        //@PathVariable - анатация отслеживающая динамический параметр из URL адреса (long postId - сам параметр, переменная. Long, потому что id в моделе пост имеет тип Long)
        if(!postRepository.existsById(postId)){ //existsById() возвращяет true, если запись по данному id была найдена
            return "redirect:/blog";
        }
        Optional<Post> post = postRepository.findById(postId);
        ArrayList <Post> res = new ArrayList<>();//Создаем список, чтобы не работать с Optional
        post.ifPresent(res::add);
        model.addAttribute("post", res);//передаем в шаблон. По слову "post" мы добавляем в HTML страницу данные
        return "blog-article";
    }
}
