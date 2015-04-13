/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aexbanner.RMIserver;

import fontys.observer.RemotePublisher;
import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 *
 * @author Jur
 */
public interface IEffectenbeurs extends Remote, RemotePublisher {

    /**
     *
     * @return Alle koersen van de effectenbeurs
     * @throws java.rmi.RemoteException
     */
    public IFonds[] getKoersen() throws RemoteException;

    /**
     * Update de koersen
     * @throws java.rmi.RemoteException
     */
    public void updateKoersen() throws RemoteException;
}
