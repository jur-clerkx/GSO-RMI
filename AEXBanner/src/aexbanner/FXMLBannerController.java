/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aexbanner;

import aexbanner.RMIserver.IEffectenbeurs;
import java.net.URL;
import java.rmi.NotBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ResourceBundle;
import javafx.animation.AnimationTimer;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

/**
 *
 * @author Jur
 */
public class FXMLBannerController implements Initializable, Remote {
    @FXML
    private Label label;

    private AnimationTimer antimer;
    private IEffectenbeurs beurs = null;
    private Registry reg;
    private AEXBannerListener listener;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            //Setup effectenbeurs
            listener = new AEXBannerListener(this);
            reg = LocateRegistry.getRegistry("127.0.0.1", 9999);
            beurs = (IEffectenbeurs) reg.lookup("beurs");
            System.out.println("Beurs found!");
            //Setup push method
            beurs.addListener(listener, "koersen");
        } catch (RemoteException ex) {
            System.out.println(ex.getMessage());
        } catch (NotBoundException ex) {
            System.out.println(ex.getMessage());
        }
        // Start animation timer
        antimer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                //Do banner moving
                label.setLayoutX(label.getLayoutX() - 1);
                if (label.getLayoutX() + label.getWidth() < 0) {
                    label.setLayoutX(500);
                }
            }

            @Override
            public void start() {
                super.start();
            }
        };
        antimer.start();
    }

    public void setKoersen(String koersen) {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                if (koersen.isEmpty()) {
                    label.setText("Connection lost!");
                } else {
                    label.setText(koersen);
                }
            }
        });
    }
}
