/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Main.MainHome;
import java.awt.AWTException;
import java.awt.Font;
import java.awt.MenuItem;
import java.awt.PopupMenu;
import java.awt.SystemTray;
import java.awt.TrayIcon;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

/**
 *
 * @author Samuel
 */
public class SecondPlan {

    private ImageIcon icon;
    private TrayIcon trayIcon;
    private SystemTray systemTray;
    private PopupMenu popUp;
    private MenuItem miOpen;
    private MenuItem miClose;
    
    public void start() {
        
        loadPopUp();
        
        if(SystemTray.isSupported()){
         try {
            icon = new ImageIcon(getClass().getResource("/View/Images/manLoad.png"));
         
         trayIcon = new TrayIcon(icon.getImage(), "Notificaçoes", popUp);
         trayIcon.setImageAutoSize(true);
         
         systemTray = SystemTray.getSystemTray();
            
                systemTray.add(trayIcon);
                
            } catch (AWTException ex) {
                JOptionPane.showMessageDialog(null, "Erro ao carregar PopUp");
                Logger.getLogger(SecondPlan.class.getName()).log(Level.SEVERE, null, ex);
            }
        }else{
            JOptionPane.showMessageDialog(null, "Segundo Plano nao suportado");
        }
    }
    
    private void loadPopUp() {
     
        miOpen = new MenuItem();
        miOpen.setFont(new Font("Arial", 0, 12));
        miOpen.setLabel("Abrir");
        miOpen.addActionListener((e) -> {
        
           Platform.runLater(() -> {
           
               MainHome.getWindow().show();
               
//                MainHome home = new MainHome();
//            try {
//                home.start(new Stage());
//            } catch (Exception ex) {
//                JOptionPane.showMessageDialog(null, "Nao foi possivel reabrir a janela: "+ex);
//                Logger.getLogger(SecondPlan.class.getName()).log(Level.SEVERE, null, ex);
//            }
           
           });
           
        });
        
        miClose = new MenuItem();
        miClose.setFont(new Font("Arial",0,12));
        miClose.setLabel("Fechar Completamente");
        miClose.addActionListener((e) -> {
            
             System.exit(0);
            
        });
        
        popUp = new PopupMenu();
        popUp.setFont(new Font("Arial", 0, 12));
        popUp.setLabel("AgendaFX");
        popUp.add(miOpen);
        popUp.add(miClose);
        
    }
    
    
    
}
