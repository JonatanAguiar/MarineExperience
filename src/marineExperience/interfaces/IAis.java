/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package marineExperience.interfaces;

import java.util.List;

/**
 *
 * @author jonat
 * @param <T>
 */
public interface IAis<T> {
    public List<T> findByDate(String dataInicial, String dataFinal);
}
