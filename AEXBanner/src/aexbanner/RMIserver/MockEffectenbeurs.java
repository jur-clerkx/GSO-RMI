/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aexbanner.RMIserver;

import fontys.observer.BasicPublisher;
import fontys.observer.RemotePropertyListener;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 *
 * @author Jur
 */
public class MockEffectenbeurs extends UnicastRemoteObject implements IEffectenbeurs {

    public IFonds[] koersen;
    public BasicPublisher pub;

    public MockEffectenbeurs() throws RemoteException {
        koersen = new IFonds[6];
        koersen[0] = new Fonds("Philips", 100);
        koersen[1] = new Fonds("Tom Tom", 75);
        koersen[2] = new Fonds("Fontys", 56);
        koersen[3] = new Fonds("ING", 12);
        koersen[4] = new Fonds("RaboBank", 26);
        koersen[5] = new Fonds("ABN Amro", 75);
        pub = new BasicPublisher(new String[]{"koersen"});
    }

    @Override
    public IFonds[] getKoersen() throws RemoteException {
        return koersen;
    }
    
    @Override
    public void updateKoersen() throws RemoteException {
        IFonds[] koersenOld = koersen;
        for (IFonds fonds : koersen) {
            double change = Math.random() * 0.5 - 0.25;
            fonds.changeKoers(change);
        }
        pub.inform(koersen, "koersen", koersenOld, koersen);
    }
    
/*    @Override
    public String toString() throws RemoteException{
        String result = "";
        for(IFonds fonds : koersen){
            result += fonds.toString() + "   ";
        }
        return result;
    }*/

    @Override
    public void addListener(RemotePropertyListener rl, String string) throws RemoteException {
        pub.addListener(rl, string);
    }

    @Override
    public void removeListener(RemotePropertyListener rl, String string) throws RemoteException {
        pub.removeListener(rl, string);
    }
}
