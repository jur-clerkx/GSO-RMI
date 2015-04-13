/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aexbanner.RMIserver;

import java.awt.event.ActionEvent;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import javax.swing.Timer;

/**
 *
 * @author Jur
 */
public class AEXRMIServer {

    /**
     * @param args the command line arguments
     * @throws java.rmi.RemoteException
     */
    public static void main(String[] args) throws RemoteException {
        // Create beurs
        IEffectenbeurs beurs;
        Registry reg;
        beurs = new MockEffectenbeurs();
        reg = LocateRegistry.createRegistry(9999);
        reg.rebind("beurs", beurs);
        System.out.println("Beurs is hosted!");
        //Start updating
        Timer t = new Timer(1000, (ActionEvent ex) ->
        {
            try {
                beurs.updateKoersen();
            } catch (RemoteException e) {
                System.out.println("Problem updating beurs!");
                System.out.println(e.getMessage());
            }
        });
        t.setRepeats(true);
        t.setCoalesce(true);
        t.start();
    }
}
