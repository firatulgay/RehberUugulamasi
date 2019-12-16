package com.firat;

import javax.servlet.annotation.WebServlet;
import javax.swing.*;

import com.firat.Buttons.DeleteButton;
import com.firat.Buttons.ListButton;
import com.firat.Buttons.SaveButton;
import com.firat.Islem.VeritabaniIslemleri;
import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.annotations.Widgetset;
import com.vaadin.event.ItemClickEvent;
import com.vaadin.server.FontAwesome;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;

import java.util.List;

/**
 * This UI is the application entry point. A UI may either represent a browser window
 * (or tab) or some part of a html page where a Vaadin application is embedded.
 * <p>
 * The UI is initialized using {@link #init(VaadinRequest)}. This method is intended to be
 * overridden to add component to the user interface and initialize non-component functionality.
 */
@Theme("mytheme")
@Widgetset("com.firat.MyAppWidgetset")
public class MyUI extends UI {

    Person person = new Person();
    VeritabaniIslemleri veritabaniIslemleri = new VeritabaniIslemleri();
    Table table;
    Integer id;
    Button btnDelete;

    @Override
    protected void init(VaadinRequest vaadinRequest) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        FormLayout formLayout = new FormLayout();

        TextField nameField = new TextField();
        nameField.setCaption("Adı");
        nameField.setIcon(FontAwesome.EDIT);
        formLayout.addComponent(nameField);

        TextField surnameField = new TextField();
        surnameField.setCaption("Soyadı");
        surnameField.setIcon(FontAwesome.EDIT);
        formLayout.addComponent(surnameField);

        TextField phoneField = new TextField();
        phoneField.setCaption("Telefon");
        phoneField.setIcon(FontAwesome.EDIT);
        formLayout.addComponent(phoneField);

        Button btnKaydet = new SaveButton();
        btnKaydet.addClickListener(new Button.ClickListener() {

            public void buttonClick(Button.ClickEvent clickEvent) {
                person.setAdi(nameField.getValue());
                person.setSoyadi(surnameField.getValue());
                person.setTelefon(phoneField.getValue());
                veritabaniIslemleri.kaydet(person);
            }
        });
        formLayout.addComponent(btnKaydet);

        Button btnListele = new ListButton();
        btnListele.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent clickEvent) {


                rehberTablosuOlustur(formLayout);

                List<Person> persons = veritabaniIslemleri.kisileriListele();
                veritabaniIslemleri.tabloDoldur(table, persons);


            }
        });
        formLayout.addComponent(btnListele);
        Label label = new Label("Kişi Listesi");
        label.addStyleName(ValoTheme.LABEL_H3);
        formLayout.addComponent(label);
        setContent(formLayout);

    }

    private void rehberTablosuOlustur(FormLayout formLayout) {

        if (table!= null){
            formLayout.removeComponent(table);
        }
        table = new Table();
        table.setSelectable(true);

        table.addItemClickListener(new ItemClickEvent.ItemClickListener() {
            @Override
            public void itemClick(ItemClickEvent ıtemClickEvent) {
                id = (Integer) ıtemClickEvent.getItem().getItemProperty("id").getValue();

            }
        });
        table.addContainerProperty("id", Integer.class, null);
        table.addContainerProperty("isim", String.class, null);
        table.addContainerProperty("soyisim", String.class, null);
        table.addContainerProperty("telefon", String.class, null);

        if (btnDelete!= null){
            formLayout.removeComponent(btnDelete);
        }
        btnDelete = new DeleteButton();
        btnDelete.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent clickEvent) {

                veritabaniIslemleri.kisiSil(id);


            }
        });

        formLayout.addComponent(table);
        formLayout.addComponent(btnDelete);

    }


    @WebServlet(urlPatterns = "/*", name = "MyUIServlet", asyncSupported = true)
    @VaadinServletConfiguration(ui = MyUI.class, productionMode = false)
    public static class MyUIServlet extends VaadinServlet {
    }
}
