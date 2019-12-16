package com.firat.Islem;

import com.firat.Person;
import com.vaadin.data.Item;
import com.vaadin.ui.Component;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Table;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by FiratUlgay on 14.12.2019.
 */
public class VeritabaniIslemleri {

    final String JDBC_CONNECTION_STR = "jdbc:mysql://localhost:3306/rehberapp?useUnicode=true&useLegacyDatetimeCode=false&serverTimezone=Turkey";
    final String USERNAME = "root";
    final String PASSWORD = "123456";

    public void kaydet(Person person) {

        try {
            if (person.getAdi().equals("") || person.getSoyadi().equals("") || person.getTelefon().equals("")) {
               throw new CustomException("Lütfen Formdaki alanları doldurunuz.");
            }

            String sql = "insert into rehber (isim, soyisim, telefon) " +
                    "values (?, ?, ?) ";

            try (Connection conn = DriverManager.getConnection(JDBC_CONNECTION_STR, USERNAME, PASSWORD);
                 PreparedStatement preparedStatement = conn.prepareStatement(sql)) {

                preparedStatement.setString(1, person.getAdi());
                preparedStatement.setString(2, person.getSoyadi());
                preparedStatement.setString(3, person.getTelefon());

                preparedStatement.executeUpdate();
                System.out.println(" Kişi rehber listesine kaydedildi.");
                Notification.show("Kişi rehber listesine kaydedildi.");

            } catch (SQLException e) {
                System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
            } catch (Exception e) {
                e.printStackTrace();
            }

        } catch (CustomException e) {
            e.getMessage();
            Notification.show(e.getMessage());
        }

    }

    public List<Person> kisileriListele() {

        List<Person> personList = new ArrayList<>();
        String sql = "select * from rehber";

        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        try (
                Connection conn = DriverManager.getConnection(JDBC_CONNECTION_STR, USERNAME, PASSWORD);
                PreparedStatement preparedStatement = conn.prepareStatement(sql)) {

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("isim");
                String surname = resultSet.getString("soyisim");
                String phone = resultSet.getString("telefon");

                Person person = new Person();
                person.setId(id);
                person.setAdi(name);
                person.setSoyadi(surname);
                person.setTelefon(phone);
                personList.add(person);
            }

        } catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }

        return personList;

    }

    public void tabloDoldur(Table table, List<Person> persons) {

        for (Person person : persons) {

            Object newItemId = table.addItem();
            Item row1 = table.getItem(newItemId);
            row1.getItemProperty("id").setValue(person.getId());
            row1.getItemProperty("isim").setValue(person.getAdi());
            row1.getItemProperty("soyisim").setValue(person.getSoyadi());
            row1.getItemProperty("telefon").setValue(person.getTelefon());
        }

        table.setPageLength(table.size());

    }

    public void kisiSil(Integer id){

        String sql = "delete from rehber where id=?";

        try (Connection conn = DriverManager.getConnection(JDBC_CONNECTION_STR, USERNAME, PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1,id);
            stmt.executeUpdate();
            Notification.show("Kayıt Silindi");

            System.out.println("Kayıt Silindi");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    }



