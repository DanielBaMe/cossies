package com.cjm.spf.pojo;

import java.util.List;

import com.cjm.spf.domain.RFamiliares;

public class ListFamiliares {
	
private List<RFamiliares> familiares;
    
    public void addFam(RFamiliares familiares) {
    	this.familiares.add(familiares);
    }

	public List<RFamiliares> getFamiliares() {
		return familiares;
	}

	public void setFamiliares(List<RFamiliares> familiares) {
		this.familiares = familiares;
	}
    
    

}
