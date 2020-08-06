package com.myblog.blog.repo;

import com.myblog.blog.models.Post;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository //если все сломалось, удалить эту анотацию
public interface PostRepository extends CrudRepository<Post, Long> { //CrudRepository позволяет добавлять, удалять, обновлять записи в таблице
    //в ковычках казывается класс модели и тип переменной id

}
