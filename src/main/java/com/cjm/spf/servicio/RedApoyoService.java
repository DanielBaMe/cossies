package com.cjm.spf.servicio;

import java.util.List;

import com.cjm.spf.domain.RedApoyo;

public interface RedApoyoService {
    
    public void guardar(RedApoyo apoyo);
    
    public RedApoyo encontrarRegistro(RedApoyo apoyo);
    
    public List<RedApoyo> findRegistros(String exp);
    
}
