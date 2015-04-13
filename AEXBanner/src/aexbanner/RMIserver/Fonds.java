/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aexbanner.RMIserver;

import java.text.DecimalFormat;

/**
 *
 * @author Jur
 */
public class Fonds implements IFonds {

    private String naam;
    private double koers;

    public Fonds(String naam, double koers) {
        this.naam = naam;
        this.koers = koers;
    }

    @Override
    public String getNaam() {
        return this.naam;
    }

    @Override
    public double getKoers() {
        return this.koers;
    }

    @Override
    public void changeKoers(double change) {
        this.koers += change;
    }
    
    @Override
    public String toString(){
        String result = naam;
        DecimalFormat formatter = new DecimalFormat("#0.00");
        result += ": " + formatter.format(koers);
        return result;
    }
}
