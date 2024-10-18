package com.em.proyscan.web.mb;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.primefaces.PrimeFaces;

import com.em.proyscan.web.be.app.Constants;
import com.em.proyscan.web.be.hlp.HlpApp;
import com.em.proyscan.web.be.model.Caja;
import com.em.proyscan.web.be.model.Carpeta;
import com.em.proyscan.web.be.model.search.SearchCaja;
import com.em.proyscan.web.be.model.search.SearchCarpeta;
import com.em.proyscan.web.be.service.SRVCaja;
import com.em.proyscan.web.be.service.SRVCarpeta;

@ManagedBean (name="mbCaja")
@ViewScoped
public class MBCaja {
	
	private final int TIPO_BUSQUEDA_AVANZADA_CAJA=1;
	private final int TIPO_BUSQUEDA_AVANZADA_CARPETA=2;
	
	String codigoBarras;
	SearchCaja searchCaja;
	SearchCarpeta searchCarpeta;
	Caja caja;
	Carpeta selectedCarpeta;
	Carpeta selectedCarpetaAvanzada;	
	Carpeta carpeta;
	String urlResources;
	String message;
	String error;
	List<Caja> cajasEncontradas;
	List<Carpeta> carpetasEncontradas;	
	Caja selectedCaja;
	boolean editingCaja;
	int tipoBusquedaAvanzada;
	boolean esCarpeta;
	
	@ManagedProperty("#{srvCaja}")
	private SRVCaja srvCaja;
	
	@ManagedProperty("#{srvCarpeta}")
	private SRVCarpeta srvCarpeta;	
	
	
	@PostConstruct
	public void init  (){
		System.out.println("Init MBNavigation ....."); 		 
		searchCaja=new SearchCaja();
		this.searchCarpeta=new SearchCarpeta();
		this.urlResources=HlpApp.getURLResources() ;
		System.out.println("URL de recursos:"+ this.urlResources );
		tipoBusquedaAvanzada=TIPO_BUSQUEDA_AVANZADA_CAJA;
		this.esCarpeta=false;
	 
	}		
	
	
  public void	searchByCodigo() {
	  if(this.codigoBarras==null) {
		  this.message="Favor de proporcionar el codigo de barras de la caja o carpeta";
		  return;
	  }
	  
 	  
	  this.editingCaja=false;
	  esCarpeta=false;
	  this.caja=this.srvCaja.getCajaByCodigoBarras(codigoBarras);
	  if( this.caja==null  ) {
		  this.caja=this.srvCaja.getByCarpeta(codigoBarras);	
		  esCarpeta=true;
	  } 
	  
	  if( caja!=null ) {		
		  displayCaja();		  
	  }else {
		  this.message="No se encontro la caja";
	  }
	  
	  
      if(esCarpeta) {
    	  PrimeFaces.current().executeScript("PF('dlgCarpetaCaja').show()");
      }
 			  
	  
  }
  
  protected void displayCaja() {
	  
	  this.message=null;
	  List<Carpeta> lstCarpetas=this.srvCarpeta.getCarpetasByCaja(caja.getCajaOID());
	  caja.setCarpetas(lstCarpetas);	
	  this.selectedCarpeta=null;
	  if(lstCarpetas!=null&& lstCarpetas.size()>0) {
		  for( Carpeta  carp : lstCarpetas  ) {
			  if( carp.getFileNameFP()==null ) {
				  carp.setUrlFileNameFP(null);
				  continue;
			  }
			  
			  carp.setUrlFileNameFP( this.urlResources+ carp.getFileNameFP().trim()  );
			  if(carp.getCodigoBarras().contentEquals( this.codigoBarras   )  ) {
				  this.selectedCarpeta=carp;
				  this.carpeta=carp;
			  }
		  }
		  			  			  
		  if(  this.selectedCarpeta==null ) {
		    this.selectedCarpeta=lstCarpetas.get(0);
		    this.carpeta=lstCarpetas.get(0);
		  }
		  
	  }
	  
	  
	  
  }
  
  
  public void doSelectCaja(Carpeta carp) {
	  Caja cajaTmp=this.srvCaja.getByCarpeta(carp.getCodigoBarras());
	  if(cajaTmp!=null) {
		  this.codigoBarras=cajaTmp.getCodigoBarras();
		  this.searchByCodigo();
	  }
  }
  
  public void selectCaja() {
	  this.caja=this.selectedCaja;
	  this.displayCaja();	  
	  PrimeFaces.current().executeScript("PF('dlgSearchCaja').hide()");
	  
  }
  
  
  public void doSelectCarpeta( Carpeta carp ) {
	  this.codigoBarras=carp.getCodigoBarras();
	  this.selectedCarpetaAvanzada=carp;
	  this.selectCarpeta();
  }
  
  public void selectCarpeta() {
	  this.codigoBarras=this.selectedCarpetaAvanzada.getCodigoBarras();
      this.searchByCodigo();  	 
      PrimeFaces.current().executeScript("PF('dlgSearchCaja').hide()");
  }  
  
  public void cancelarCaja() {
	  this.editingCaja=false;	  
  }  
  
  public void initEditCaja() {
	  this.editingCaja=true;	  
  }    
  
  
  public void initSearch() {
	  this.searchCaja=new SearchCaja();
  }
  
  
  public void busquedaAvanzada() {
	  if(this.tipoBusquedaAvanzada==TIPO_BUSQUEDA_AVANZADA_CAJA) {
		  busquedaAvanzadaCajas();
	  }else if(  this.tipoBusquedaAvanzada==TIPO_BUSQUEDA_AVANZADA_CARPETA ) {
		  busquedaAvanzadaCarpetas();
	  }
	  
	  
	 	    	  
  }
  
  public void busquedaAvanzadaCajas() {
	  if(this.searchCaja==null) {
		  this.cajasEncontradas=null;
		  return;
	  }
	 
	  this.cajasEncontradas=this.srvCaja.search(searchCaja);	  
	  
  }
  
  
  
  public void busquedaAvanzadaCarpetas() {
	  if(this.searchCarpeta==null) {
		  this.carpetasEncontradas=null;
		  return;
	  }
	 
	  this.carpetasEncontradas=this.srvCarpeta.search(searchCarpeta);	  
	  
  }  
  
  
  public void selectTipoBusqueda() {
	  System.out.println("Select tipo busqueda ");
	  
  }
  
  public void guardarCarpeta() {
	  if( this.carpeta==null ) {
		  return;
	  }
	  
	  this.srvCarpeta.save(carpeta);	 
	  PrimeFaces.current().executeScript("PF('dlgCarpetaCaja').hide()");
  }

  public void refreshCarepta() {
	  System.out.println("refresh carpeta :" + this.selectedCarpeta.getCodigoBarras() );
	  this.carpeta=selectedCarpeta;
  }
 
  public void editCarpeta(Carpeta fld) {
	  this.selectedCarpeta=fld;
	  this.carpeta=fld;
	 	  
  }
  
  public void guardarCaja() {
	  this.srvCaja.save(caja);
	  this.message="Los datos de la caja se guardaron correctamente";
	  this.editingCaja=false;
	  
  }
  
  
  public void refreshCareptaBusqueda() {
	  System.out.println("refreshCareptaBusqueda");
  }
  
  

public String getCodigoBarras() {
	return codigoBarras;
}


public void setCodigoBarras(String codigoBarras) {
	this.codigoBarras = codigoBarras;
}


 

public SRVCaja getSrvCaja() {
	return srvCaja;
}


public void setSrvCaja(SRVCaja srvCaja) {
	this.srvCaja = srvCaja;
}


public Caja getCaja() {
	return caja;
}


public void setCaja(Caja caja) {
	this.caja = caja;
}


public SRVCarpeta getSrvCarpeta() {
	return srvCarpeta;
}


public void setSrvCarpeta(SRVCarpeta srvCarpeta) {
	this.srvCarpeta = srvCarpeta;
}


public Carpeta getSelectedCarpeta() {
	return selectedCarpeta;
}


public void setSelectedCarpeta(Carpeta selectedCarpeta) {
	this.selectedCarpeta = selectedCarpeta;
}


public Carpeta getCarpeta() {
	return carpeta;
}


public void setCarpeta(Carpeta carpeta) {
	this.carpeta = carpeta;
}


public String getUrlResources() {
	return urlResources;
}


public void setUrlResources(String urlResources) {
	this.urlResources = urlResources;
}


public String getMessage() {
	return message;
}


public void setMessage(String message) {
	this.message = message;
}


public String getError() {
	return error;
}


public void setError(String error) {
	this.error = error;
}


public List<Caja> getCajasEncontradas() {
	return cajasEncontradas;
}


public void setCajasEncontradas(List<Caja> cajasEncontradas) {
	this.cajasEncontradas = cajasEncontradas;
}


public void setSearchCaja(SearchCaja searchCaja) {
	this.searchCaja = searchCaja;
}


public SearchCaja getSearchCaja() {
	return searchCaja;
}


public Caja getSelectedCaja() {
	return selectedCaja;
}


public void setSelectedCaja(Caja selectedCaja) {
	this.selectedCaja = selectedCaja;
}


public boolean isEditingCaja() {
	return editingCaja;
}


public void setEditingCaja(boolean editingCaja) {
	this.editingCaja = editingCaja;
}


public int getTipoBusquedaAvanzada() {
	return tipoBusquedaAvanzada;
}


public void setTipoBusquedaAvanzada(int tipoBusquedaAvanzada) {
	this.tipoBusquedaAvanzada = tipoBusquedaAvanzada;
}


public SearchCarpeta getSearchCarpeta() {
	return searchCarpeta;
}


public void setSearchCarpeta(SearchCarpeta searchCarpeta) {
	this.searchCarpeta = searchCarpeta;
}


public List<Carpeta> getCarpetasEncontradas() {
	return carpetasEncontradas;
}


public void setCarpetasEncontradas(List<Carpeta> carpetasEncontradas) {
	this.carpetasEncontradas = carpetasEncontradas;
}


public Carpeta getSelectedCarpetaAvanzada() {
	return selectedCarpetaAvanzada;
}


public void setSelectedCarpetaAvanzada(Carpeta selectedCarpetaAvanzada) {
	this.selectedCarpetaAvanzada = selectedCarpetaAvanzada;
}


 
	

}