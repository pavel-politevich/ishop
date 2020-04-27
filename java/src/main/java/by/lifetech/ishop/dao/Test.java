package by.lifetech.ishop.dao;

import by.lifetech.ishop.bean.AuthorizedUser;
import by.lifetech.ishop.bean.InfoUser;
import by.lifetech.ishop.bean.Item;
import by.lifetech.ishop.bean.User;
import by.lifetech.ishop.dao.exception.DAOException;
import by.lifetech.ishop.dao.factory.DAOFactory;
import by.lifetech.ishop.dao.impl.ItemDAOImpl;
import by.lifetech.ishop.dao.impl.UserDAOImpl;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

public class Test {
    public static void main(String[] args) {


        Calendar calendar = new GregorianCalendar(2001, 0 , 31);
        DAOFactory factory = DAOFactory.getInstance();

        try {
            UserDAO userDAO = new UserDAOImpl();
            ItemDAO itemDAO = new ItemDAOImpl();

            // Регистрация пользователя
            //userDAO.registration("ivan1", "1234567".getBytes(), "Иван", "Иванов", "tes8885@mail.com", "+375259000377", "г.Минск", calendar.getTime());

            //Авторизация пользователя
            AuthorizedUser u = userDAO.signIn("petr001", "P@ssw0rd".getBytes());
            if (u != null) {
                System.out.println("Пользователь авторизован. Имя: " + u.getName());
            }
            else System.out.println("Пользователя не существует");


            // Поиск пользователей по статусу
            List<InfoUser> infoUserList = userDAO.findUsersByState(1);
            if (infoUserList != null) {
                for (InfoUser infoUser : infoUserList) {
                    System.out.println("Найден пользователь: " + infoUser.getLogin() + " " + infoUser.getName() + " " + infoUser.getSurname() + ". Статус: " + infoUser.getState());
                }

            }
            else System.out.println("Поиск пользователя не дал результатов");


            //Добавить товар
            int id = itemDAO.addItem(1,"Колбаса из свинины", "Колбаса св.", "Вес 1 кг.", "Брестский МК", new BigDecimal("3.15"), 1, 10);
            if (id > 0) {
                System.out.println("ID товара: " + String.valueOf(id));

                //Изменение статуса товара
                itemDAO.setItemStatus(id, 2);

                //Изменение количества
                itemDAO.updateItemBalance(id, -3);
            }
            else {
                System.out.println("Товар не добавлен "  + String.valueOf(id));
            }


            // Поиск товаров по категории
            List<Item> itemList = itemDAO.findItemsByCategory(1);
            if (itemList != null) {
                for (Item item : itemList) {
                    System.out.println("Кроткое название: " + item.getNameShort());
                }

            }
            else System.out.println("Поиск товаров не дал результатов");



        } catch (DAOException e) {
            e.printStackTrace();
        }
    }
}
