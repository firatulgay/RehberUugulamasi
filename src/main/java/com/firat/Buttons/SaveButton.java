package com.firat.Buttons;

import com.vaadin.server.FontAwesome;
import com.vaadin.ui.Button;
import com.vaadin.ui.themes.ValoTheme;

import java.awt.*;

/**
 * Created by FiratUlgay on 14.12.2019.
 */
public class SaveButton extends Button{

   public SaveButton(){
       this.setCaption("Kaydet");
       this.setIcon(FontAwesome.CHECK_SQUARE);
       this.addStyleName(ValoTheme.BUTTON_FRIENDLY);
   }
}
