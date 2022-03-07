/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package capitulo8.mais.streams;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author Leonel
 */
public class Grupo {
    
    private Set<Usuario> usuarios = new HashSet<>();
    
    public void add(Usuario u) {
        usuarios.add(u);
    }
    
    public Set<Usuario> getUsuarios() {
        return Collections.unmodifiableSet(this.usuarios);
    }    
}
