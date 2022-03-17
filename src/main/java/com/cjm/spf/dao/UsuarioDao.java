package com.cjm.spf.dao;

import java.util.List;
import java.util.Optional;
import com.cjm.spf.domain.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioDao extends JpaRepository<Usuario, Long>{
    Optional<Usuario> findByUsername (String username);
    
    Usuario findByidUsuario(Long id);
    
    List<Usuario> findByPrivilegio(Integer privilegio);
    
    //Usuario finByUsername(String nombre);
    
//    Usuario findTopByOrderByIdDesc();
}
