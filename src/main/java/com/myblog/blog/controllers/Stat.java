package com.myblog.blog.controllers;

import com.myblog.blog.models.Cash;
import com.myblog.blog.models.Output;
import com.myblog.blog.repo.CashRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

@Controller
public class Stat {



    @Autowired
    public CashRepository cashRepository; //переменная класса CashRepository, для записи в БД

    @GetMapping("/statistics")
    public String statistics (Model model){
        Iterable<Cash> money = cashRepository.findAll(); //массив данных, в котором содержаться все значения из таблицы базы данных

        int mCurrent; //переменная которая будет принимать текущий месяц и будет рабоать с циклом
        int mMin = 0; // переменная которая будет принимать нимнимальный месяц в балицу в году
        //работа с датой - назначаем текущую дату в пременную
        Calendar calendar = new GregorianCalendar();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("M");
        mCurrent =Integer.parseInt (simpleDateFormat.format(calendar.getTime()));//текущий месяц

        mMin = cashRepository.minMonth();//записываем в эту переменную номер месяца с которого начинается статистика в базе данных

        /*
        int [] inc = new int[mCurrent]; //массив будет иметь количесво такое же сколько месяцев есть в базе данных
        int [] cost = new int[mCurrent];

         */
        List<Output> outputs = new ArrayList<>();//эту переменную передаем в model.addAttribute
        int numberOfMonth;
        int inc;
        int cost;

        for (int i = mCurrent; i >= mMin; i--){

            inc = cashRepository.findIncomingMonth(i);
            cost = cashRepository.findCostMonth(i);
            numberOfMonth = i;
            Output nomber = new Output(numberOfMonth, inc, cost);//передаем в конструктор значения принятые из базы данных
            outputs.add(nomber);//записываем это в лист outputs, чтобы передать это в модель страницы
        }
        /*
        //с этим кодом точно работает но не так как надо
        int inc = cashRepository.findIncomingMonth(mCurrent);
        int cost = cashRepository.findCostMonth(mCurrent);



         */
      //  model.addAttribute("money", money);
        model.addAttribute("outputs", outputs);


        return "statistics";
    }

    //отдельная страница с формой внесения данных

    @GetMapping("/form/add")
    public String formAdd(Model model){

        return "form-add";
    }

    @PostMapping("form/add")
    public String CashAddToBD(@RequestParam String incoming, @RequestParam String cost, Model model){
        Cash cash = new Cash(incoming, cost);
        cashRepository.save(cash);
        return "redirect:/statistics";
    }


}
