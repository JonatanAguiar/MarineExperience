/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package marineExperience.interfaces;

import java.util.List;
import marineExperience.model.Usuario;

/**
 *
 * @author jonat
 */
public interface IUsuario {

    public void salvar(Usuario produto);
    
    public Usuario findUser(String nome, String senha);
    
    public List<Usuario> findAll();

    public void alterar(int id, String senha, String nome);
}
