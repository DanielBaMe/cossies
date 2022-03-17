package com.cjm.spf.servicio;

import java.util.List;

import com.cjm.spf.domain.RFamiliares;

public interface RFamiliaresService {
    
    public void guardar(RFamiliares familiares);
    
    public RFamiliares encontrarRegistro(RFamiliares familiares);
    
    public List<RFamiliares> findRegistros(String exp);
}
