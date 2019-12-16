package com.firat.Buttons;

import com.vaadin.server.FontAwesome;
import com.vaadin.ui.Button;
import com.vaadin.ui.themes.ValoTheme;

/**
 * Created by FiratUlgay on 16.12.2019.
 */
public class DeleteButton extends Button {

    public DeleteButton(){
        this.setCaption("Kayıt Sil");
        this.setIcon(FontAwesome.TRASH);
        this.addStyleName(ValoTheme.BUTTON_FRIENDLY);
    }
}
