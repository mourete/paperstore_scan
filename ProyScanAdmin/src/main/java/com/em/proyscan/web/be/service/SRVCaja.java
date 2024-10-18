package com.em.proyscan.web.be.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.em.proyscan.web.be.dao.DAOCaja;
import com.em.proyscan.web.be.model.Caja;
import com.em.proyscan.web.be.model.search.SearchCaja;

@Service("srvCaja")
public class SRVCaja {
	
	@Autowired
	DAOCaja daoCaja;
	
	
    public  Caja getCajaByCodigoBarras( String codigoBarras ){
	   return daoCaja.getCajaByCodigoBarras(codigoBarras);
    }
   
    
    public  List<Caja> search(SearchCaja searchCaja){
    	return daoCaja.search(searchCaja);
    }
   	   
    public  void save(Caja caja )  {  
    	this.daoCaja.save(caja);
    }
	
    
    public  Caja getByCarpeta(String carpetaBarCode){
    	return this.daoCaja.getByCarpeta(carpetaBarCode);
    }
		

}
