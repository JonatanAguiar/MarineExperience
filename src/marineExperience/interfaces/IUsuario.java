/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package marineExperience.interfaces;

/**
 *
 * @author jonat
 * @param <T>
 */
public interface IUsuario<T> {

    public void salvar(String nome, String senha);
    
    public T findUser(String nome, String senha);

}
