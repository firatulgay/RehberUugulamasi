package com.firat.Buttons;

import com.vaadin.server.FontAwesome;
import com.vaadin.ui.Button;
import com.vaadin.ui.themes.ValoTheme;

/**
 * Created by FiratUlgay on 14.12.2019.
 */
public class ListButton extends Button{

   public ListButton(){
       this.setCaption("Listele");
       this.setIcon(FontAwesome.LIST);
       this.addStyleName(ValoTheme.BUTTON_DANGER);
   }
}
