/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package marineExperience.interfaces;

import java.util.List;
import marineExperience.model.Ais;

/**
 *
 * @author jonat
 */
public interface IAis {

    public Ais findUser(int id);
    
    //public List<Ais> findAll();
    
    public List<Ais> findByDate(String dataInicial, String dataFinal);
}
