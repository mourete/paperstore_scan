package com.em.proyscan.web.be.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.em.proyscan.web.be.model.Caja;
import com.em.proyscan.web.be.model.Carpeta;
import com.em.proyscan.web.be.model.search.SearchCaja;
import com.em.proyscan.web.be.model.search.SearchCarpeta;

@Repository( "daoCarpeta")
public class DAOCarpeta  {
	
	@Autowired
	DataSource dataSource;
	
	
	
	
	   public  void save(Carpeta carpeta )  {  
		    Connection conn=null;    
		    CallableStatement cstmt = null;
		    Date date=new Date();
		    
		    try {  
		        conn=dataSource.getConnection();
		        cstmt = conn.prepareCall("{call dbo.carpeta_save(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");
		                 
		        if( carpeta.getCarpetaOID()==null || carpeta.getCarpetaOID().isEmpty() ){
		           cstmt.setNull(1, java.sql.Types.VARCHAR  );
		        }else{
		           cstmt.setString(1, carpeta.getCarpetaOID() );
		        }
		        cstmt.setString(2, carpeta.getCajaOID() );                 
		        cstmt.setString(3 , carpeta.getFileName() );           
		        cstmt.setString(4, carpeta.getCodigoBarras() );           
		        cstmt.setInt( 5 , carpeta.getNumPages());
		        cstmt.setString( 6 , carpeta.getFileNameFP());
		        cstmt.registerOutParameter(1 , java.sql.Types.VARCHAR );        
		        
		        
		         if( carpeta.getAlfa1()!=null ){
		             cstmt.setString( 7 , carpeta.getAlfa1() );
		         }else {
		             cstmt.setNull( 7 ,  Types.VARCHAR );
		         }
		         
		         if( carpeta.getAlfa2()!=null ){
		             cstmt.setString( 8 , carpeta.getAlfa2() );
		         }else {
		             cstmt.setNull( 8 ,  Types.VARCHAR );
		         }         
		         
		         if( carpeta.getAlfa3()!=null ){
		             cstmt.setString( 9 , carpeta.getAlfa3() );
		         }else {
		             cstmt.setNull( 9 ,  Types.VARCHAR );
		         }    
		         
		         if( carpeta.getAlfa4()!=null ){
		             cstmt.setString( 10 , carpeta.getAlfa4() );
		         }else {
		             cstmt.setNull( 10 ,  Types.VARCHAR );
		         }               
		         
		         if( carpeta.getAlfa5()!=null ){
		             cstmt.setString( 11 , carpeta.getAlfa5() );
		         }else {
		             cstmt.setNull( 11 ,  Types.VARCHAR );
		         }                    
		         
		        
		         cstmt.setInt( 12 , carpeta.getInt1() );
		         cstmt.setInt( 13 , carpeta.getInt2() );
		         cstmt.setInt( 14 , carpeta.getInt3() );     
		         
		         if( carpeta.getDate1()!=null ){
		             cstmt.setDate( 15 ,   new java.sql.Date(carpeta.getDate1().getTime()  ) );
		         }else {
		             cstmt.setNull( 15 ,  Types.DATE );
		         }               
		         
		         if( carpeta.getDate2()!=null ){
		             cstmt.setDate( 16 ,   new java.sql.Date(carpeta.getDate2().getTime()  ) );
		         }else {
		             cstmt.setNull( 16 ,  Types.DATE );
		         }                 
		        
		        
		        
		        cstmt.execute();  
		        System.out.println("MANAGER ID: " + cstmt.getString(1));  
		        carpeta.setCarpetaOID( cstmt.getString(1) );
		        
		        System.out.println("Se inserto la carpeta :"+  carpeta.getCarpetaOID()  );
		        
		    }catch(Exception e){
		       e.printStackTrace();
		    }finally{
		        try{
		            if( cstmt!=null ){
		                cstmt.close();
		            }
		            
		            if(conn!=null ){
		                conn.close();
		            }
		            
		        }catch(Exception e){
		            
		        }
		    }  
		}

		    
		    
		   public  List<Carpeta> getCarpetasByCaja( String cajaOID ){
		       List<Carpeta> carpetas=null;
		        if(cajaOID==null || cajaOID.isEmpty() ){
		            return null;
		        }
		        
		        Carpeta carpeta=null;
		       Connection conn=null;
		        Statement st=null;
		        ResultSet rs=null;
		        try{
		            
		 
		           conn=dataSource.getConnection();
		            if(conn==null){
		                return null;
		            }
		            
		            st=conn.createStatement();
		            rs=st.executeQuery("select * from dbo.carpeta where caja_OID='"+ cajaOID +"'" );
		            while(rs.next()){
		                 carpeta=new Carpeta();
		                 fillDatosCarpeta( rs , carpeta );
		                 if( carpetas==null ){
		                     carpetas=new ArrayList<Carpeta>();
		                 }
		                 
		                 carpetas.add(carpeta);
		                 
		            }
		            
		            
		        }catch(Exception e){
		            e.printStackTrace();
		        }finally{
		            try{
		               if(rs!=null){
		                   rs.close();
		               } 
		                
		               if(st!=null){
		                   st.close();
		               }
		               
		               if(conn!=null){
		                   conn.close();
		               }
		                
		            }catch(Exception e){
		                
		            }
		            
		            
		        }
		        
		        return carpetas;
		        
		        
		    }
		    
		       
		    
		    
		    
		    protected static synchronized void fillDatosCarpeta( ResultSet rs , Carpeta carpeta )throws Exception {
		        
		            carpeta.setCarpetaOID( rs.getString("carpeta_OID") );
		            carpeta.setCodigoBarras( rs.getString("codigo_barras") ); 
		            carpeta.setFileName(rs.getString("file_name"));
		            carpeta.setCajaOID( rs.getString("caja_OID") );
		            carpeta.setNumPages(rs.getInt("num_pages"));   
		            carpeta.setFileNameFP(rs.getString("file_name_fp"));
		            carpeta.setAlfa1(rs.getString("alfa1"));
		            carpeta.setAlfa2(rs.getString("alfa2"));
		            carpeta.setAlfa3(rs.getString("alfa3"));
		            carpeta.setAlfa4(rs.getString("alfa4"));
		            carpeta.setAlfa5(rs.getString("alfa5"));
		            carpeta.setDate1(rs.getDate("date1"));
		            carpeta.setDate2(rs.getDate("date2"));
		            carpeta.setInt1(rs.getInt("int1"));
		            carpeta.setInt2(rs.getInt("int2"));
		            carpeta.setInt3(rs.getInt("int3"));		            
		        
		    }    
		        
	
	
	
	
		    public  List<Carpeta> search(SearchCarpeta searchCarpeta){
		    	List<Carpeta> carpetas=null;
		        Connection conn=null;
		        Carpeta carpeta=null;
		        Statement st=null;
		        ResultSet rs=null;
		        try{
		            
		     
		           conn=dataSource.getConnection();
		            if(conn==null){
		                return null;
		            }
		            
		            st=conn.createStatement();
		            String query="    select * from dbo.carpeta carp " +
		             "   where carp.carpeta_OID is not null  " ;
		            
		            if(  searchCarpeta.getAlfa1()!=null && !searchCarpeta.getAlfa1().isEmpty() ) {
		            	query+=" and carp.alfa1 like '%" + searchCarpeta.getAlfa1()+ "%' ";        	
		            }
		            
		            if(  searchCarpeta.getAlfa2()!=null && !searchCarpeta.getAlfa1().isEmpty() ) {
		            	query+=" and carp.alfa2 like '%" + searchCarpeta.getAlfa2()+ "%' ";        	
		            }
		            
		            if(  searchCarpeta.getAlfa3()!=null && !searchCarpeta.getAlfa1().isEmpty() ) {
		            	query+=" and carp.alfa3 like '%" + searchCarpeta.getAlfa3()+ "%' ";        	
		            }
		            
		            if(  searchCarpeta.getAlfa4()!=null && !searchCarpeta.getAlfa1().isEmpty() ) {
		            	query+=" and carp.alfa4 like '%" + searchCarpeta.getAlfa4()+ "%' ";        	
		            }
		            
		            if(  searchCarpeta.getAlfa5()!=null && !searchCarpeta.getAlfa1().isEmpty() ) {
		            	query+=" and carp.alfa5 like '%" + searchCarpeta.getAlfa5()+ "%' ";        	
		            }
		                    
		            
		            if(  searchCarpeta.getInt1()!=0   ) {
		            	query+=" and carp.int1=" + searchCarpeta.getInt1() + " ";        	
		            }
		                    
		            if( searchCarpeta.getInt2()!=0  ) {
		            	query+=" and carp.int2=" + searchCarpeta.getInt2() + " ";        	
		            }
		                    
		            if( searchCarpeta.getInt3()!=0  ) {
		            	query+=" and carp.int3=" + searchCarpeta.getInt3() + " ";        	
		            }
		                            
		            
		            
		            rs=st.executeQuery(query );
		     
		            
		            while(rs.next()){   
		            	if(carpetas==null) {
		            		carpetas=new ArrayList<Carpeta>();
		            	}
		            	
		                 carpeta=new Carpeta();
		                 fillDatosCarpeta( rs , carpeta );     
		                 carpetas.add(carpeta);
		            }
		            
		            
		        }catch(Exception e){
		            e.printStackTrace();
		        }finally{
		            try{
		               if(rs!=null){
		                   rs.close();
		               } 
		                
		               if(st!=null){
		                   st.close();
		               }
		               
		               if(conn!=null){
		                   conn.close();
		               }
		                
		            }catch(Exception e){
		                
		            }
		            
		            
		        }
		        
		        return carpetas;
		        
		    }    




	
	
	
	
	
	

}
