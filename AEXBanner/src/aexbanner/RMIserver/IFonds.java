/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aexbanner.RMIserver;

import java.io.Serializable;

/**
 *
 * @author Jur
 */
public interface IFonds extends Serializable{
    /**
     * 
     * @return De naam van de koers
     */
    public String getNaam();
    
    /**
     * 
     * @return De waarde van de huidige koers
     */
    public double getKoers();
    
    /**
     * Verandert de koers met de gegeven waarde
     * @param change 
     */
    public void changeKoers(double change);
}
