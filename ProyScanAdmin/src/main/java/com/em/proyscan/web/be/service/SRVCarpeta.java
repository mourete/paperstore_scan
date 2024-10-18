package com.em.proyscan.web.be.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.em.proyscan.web.be.dao.DAOCarpeta;
import com.em.proyscan.web.be.model.Carpeta;
import com.em.proyscan.web.be.model.search.SearchCarpeta;

@Service("srvCarpeta")
public class SRVCarpeta {
	
	@Autowired
	DAOCarpeta daoCarpeta;
	
	
	public  void save(Carpeta carpeta )  {  
		this.daoCarpeta.save(carpeta);				   
	}
	
	public  List<Carpeta> getCarpetasByCaja( String cajaOID ){
		return this.daoCarpeta.getCarpetasByCaja(cajaOID);
	}
	
	public  List<Carpeta> search( SearchCarpeta searchCarpeta ){
		return this.daoCarpeta.search(searchCarpeta);
	}	

}
