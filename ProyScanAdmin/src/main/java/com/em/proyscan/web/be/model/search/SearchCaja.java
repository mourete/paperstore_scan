package com.em.proyscan.web.be.model.search;

import java.util.Date;
import java.util.List;

import com.em.proyscan.web.be.model.Carpeta;

public class SearchCaja {
	
	   private String txtSearch;
	   private String cajaOID;
	    private String codigoBarras;
	    private String cajaName;
	    private Date   fechaIni;
	    private Date   fechaFin;
	    private String alfa1;
	    private String alfa2;
	    private String alfa3;
	    private String alfa4;
	    private String alfa5;
	    private int    int1;
	    private int    int2;
	    private int    int3;
	    private Date   date1;
	    private Date   date2;
	    private Date   date3;
	    private String   nombreEstatus;
	    
	    List<Carpeta> carpetas;

	    public String getCajaOID() {
	        return cajaOID;
	    }

	    public void setCajaOID(String cajaOID) {
	        this.cajaOID = cajaOID;
	    }

	    public String getCodigoBarras() {
	        return codigoBarras;
	    }

	    public void setCodigoBarras(String codigoBarras) {
	        this.codigoBarras = codigoBarras;
	    }

	    public String getCajaName() {
	        return cajaName;
	    }

	    public void setCajaName(String cajaName) {
	        this.cajaName = cajaName;
	    }

	    public Date getFechaIni() {
	        return fechaIni;
	    }

	    public void setFechaIni(Date fechaIni) {
	        this.fechaIni = fechaIni;
	    }

	    public Date getFechaFin() {
	        return fechaFin;
	    }

	    public void setFechaFin(Date fechaFin) {
	        this.fechaFin = fechaFin;
	    }

	    public String getAlfa1() {
	        return alfa1;
	    }

	    public void setAlfa1(String alfa1) {
	        this.alfa1 = alfa1;
	    }

	    public String getAlfa2() {
	        return alfa2;
	    }

	    public void setAlfa2(String alfa2) {
	        this.alfa2 = alfa2;
	    }

	    public String getAlfa3() {
	        return alfa3;
	    }

	    public void setAlfa3(String alfa3) {
	        this.alfa3 = alfa3;
	    }

	    public int getInt1() {
	        return int1;
	    }

	    public void setInt1(int int1) {
	        this.int1 = int1;
	    }

	    public int getInt2() {
	        return int2;
	    }

	    public void setInt2(int int2) {
	        this.int2 = int2;
	    }

	    public int getInt3() {
	        return int3;
	    }

	    public void setInt3(int int3) {
	        this.int3 = int3;
	    }

	    public Date getDate1() {
	        return date1;
	    }

	    public void setDate1(Date date1) {
	        this.date1 = date1;
	    }

	    public Date getDate2() {
	        return date2;
	    }

	    public void setDate2(Date date2) {
	        this.date2 = date2;
	    }

	    public Date getDate3() {
	        return date3;
	    }

	    public void setDate3(Date date3) {
	        this.date3 = date3;
	    }
	 

	    public String getNombreEstatus() {
	        return nombreEstatus;
	    }

	    public void setNombreEstatus(String nombreEstatus) {
	        this.nombreEstatus = nombreEstatus;
	    }

	    public List<Carpeta> getCarpetas() {
	        return carpetas;
	    }

	    public void setCarpetas(List<Carpeta> carpetas) {
	        this.carpetas = carpetas;
	    }

		public String getAlfa4() {
			return alfa4;
		}

		public void setAlfa4(String alfa4) {
			this.alfa4 = alfa4;
		}

		public String getAlfa5() {
			return alfa5;
		}

		public void setAlfa5(String alfa5) {
			this.alfa5 = alfa5;
		}

		public String getTxtSearch() {
			return txtSearch;
		}

		public void setTxtSearch(String txtSearch) {
			this.txtSearch = txtSearch;
		}
	  
	    
	    	
	

}