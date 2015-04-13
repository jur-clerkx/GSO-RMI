/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aexbanner;

import aexbanner.RMIserver.IFonds;
import fontys.observer.RemotePropertyListener;
import java.beans.PropertyChangeEvent;
import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import javafx.beans.value.ObservableStringValue;

/**
 *
 * @author Jur
 */
public class AEXBannerListener extends UnicastRemoteObject implements RemotePropertyListener {
    FXMLBannerController parent;
    public AEXBannerListener(FXMLBannerController parent) throws RemoteException{
        this.parent = parent;
    }

    @Override
    public void propertyChange(PropertyChangeEvent pce) throws RemoteException {
        String result = "";
        for (IFonds fonds : (IFonds[]) pce.getNewValue()) {
            result += fonds.toString() + "   ";
        }
        parent.setKoersen(result);
    }
}
