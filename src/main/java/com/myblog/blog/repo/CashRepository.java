package com.myblog.blog.repo;

import com.myblog.blog.models.Cash;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CashRepository extends CrudRepository<Cash,Long> {

    //через анатацию @Query делается запрос в базу данных. Это будет делать метод который написан под этой анотацией
    //зпрос на подсчет суммы доходов месяца
    @Query(value = "select sum(incoming) from cash where month = :currentmonth", nativeQuery = true)
    int findIncomingMonth (@Param("currentmonth") int currentMonth);

    //запрос на подсчет суммы расходов месяца
    @Query(value = "select sum(cost) from cash where month = :currentmonth", nativeQuery = true)
    int findCostMonth (@Param("currentmonth") int currentMonth);

    //Определяем минимальный имеющийся месяц у записи в таблице. с этого месяца будет начинаться цикл
    @Query(value = "select min(month) from cash", nativeQuery = true)
    int minMonth();

    //Определяем минимальный имеющийся год у записи в таблице. с этого года будет начинаться цикл
    @Query(value = "select min(year) from cash", nativeQuery = true)
    int minYear();
}


