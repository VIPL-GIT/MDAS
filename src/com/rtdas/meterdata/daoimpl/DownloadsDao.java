package com.rtdas.meterdata.daoimpl;

import java.io.File;
import java.io.FileFilter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.rtdas.mdm.beans.D5_data;
import com.rtdas.mdm.beans.EnergyTODBean;
import com.rtdas.mdm.models.D3_b3;
import com.rtdas.mdm.models.D3_columns;
import com.rtdas.mdm.models.D4_columns;
import com.rtdas.mdm.models.D4_data;
import com.rtdas.mdm.models.D5_columns;
import com.rtdas.mdm.models.D6_columns;
import com.rtdas.mdm.models.D6_data;
import com.rtdas.meterdata.beans.Rawdata_availabilityBean;
import com.rtdas.meterdata.beans.TotalMasterdataBean;
import com.rtdas.meterdata.models.Billing_summary;
import com.rtdas.meterdata.models.Device_summary;
import com.rtdas.meterdata.models.MobileLogs;
import com.rtdas.meterdata.models.MobileUploads;

@Repository("DownloadsDao")
@Transactional
public class DownloadsDao {

	@Autowired
	private SessionFactory sf;
	
	@Value("${database}")
	private String database;
	
	
public List<Device_summary> getDeviceSummary(List<String> meters){
		
		List<Device_summary> list=new ArrayList();
		SimpleDateFormat sdf=new SimpleDateFormat("dd-MM-yyyy");
		String curdate=sdf.format(new java.util.Date());
		try{
			Query q=sf.getCurrentSession().createQuery("From Device_summary where comm_date='"+curdate+"'");
			
			//q.setParameterList("meters", meters);
			list=q.list();
			if(list.isEmpty()){
				Device_summary ds=(Device_summary)sf.getCurrentSession().createQuery("FROM Device_summary ORDER BY id DESC").list().get(0);
				String lastDate=ds.getComm_date();
				
				q=sf.getCurrentSession().createQuery("From Device_summary where comm_date='"+lastDate+"'");				
				list=q.list();
			}
			Iterator<Device_summary> it=list.iterator();
			while(it.hasNext()){
				Device_summary ds=(Device_summary)it.next();
				if(meters.contains(ds.getMeter_serial_no())){
					
				}else{
					it.remove();
				}
			}
		System.out.println("after list------------------"+list.size());	
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return list;	
		
	}

	public List<Billing_summary> getBillingSummary(List<String> meters,List<Integer> sdids){
		
		List<Billing_summary> list=new ArrayList();
		List<Billing_summary> finallist=new ArrayList();
		Map<String,TotalMasterdataBean> map=new HashMap();
		try{
			//Query q=sf.getCurrentSession().createQuery("From Device_summary where meter_serial_no in (:meters)");
			Query q=sf.getCurrentSession().createQuery("From Billing_summary where d2!='-'");
			//q.setParameterList("meters", meters);
			list=q.list();
			
			List<TotalMasterdataBean> master=new ArrayList();
		    q=sf.getCurrentSession().createQuery("from TotalMasterdataBean where sd_id in (:sdids)");
			q.setParameterList("sdids", sdids);
			master=q.list();
			
			for(TotalMasterdataBean tmb:master){
				map.put(tmb.getMeter_sl_no(), tmb);
			}
			
			for(Billing_summary ds:list){
				if(map.containsKey(ds.getMeter_serial_no())){
					ds.setMaster(map.get(ds.getMeter_serial_no()));
					finallist.add(ds);
				}
				
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return finallist;	
		
	}
	
public List<Rawdata_availabilityBean> getDataAvailability(List<Integer> sdids,String datapath,String month){
	List<Rawdata_availabilityBean> list=new ArrayList();
	try{
		Map<String,TotalMasterdataBean> map=new HashMap();
		List<TotalMasterdataBean> master=new ArrayList();
	   Query q=sf.getCurrentSession().createQuery("from TotalMasterdataBean where sd_id in (:sdids) and meter_sl_no is not null");
		q.setParameterList("sdids", sdids);
		master=q.list();
		
		for(TotalMasterdataBean tmb:master){
			map.put(tmb.getMeter_sl_no(), tmb);
		}
		month=month.replace("/", "-");
		String filesPath=datapath+File.separator+month;
		List<String> meters=availableMeters(filesPath);
		
		for(Map.Entry em:map.entrySet()){
			
			Rawdata_availabilityBean rb=new Rawdata_availabilityBean();
			if(meters.contains(em.getKey().toString())){ 
				rb.setIsAvailable("YES");
				rb.setPath(filesPath+File.separator+em.getKey().toString());
				}
			else{ 
				rb.setIsAvailable("NO");
			    rb.setPath("");
			}
			
			rb.setTmb((TotalMasterdataBean)em.getValue());
			
			list.add(rb);
		}
		
		
	}catch(Exception e){
		e.printStackTrace();
	}
	
	return list;
}

public List<String> availableMeters(String path){
	List<String> meters=new ArrayList();
	try{
		File dir = new File(path);
	      File[] files = dir.listFiles();
	      FileFilter fileFilter = new FileFilter() {
	         public boolean accept(File file) {
	            return file.isDirectory();
	         }
	      };
	      files = dir.listFiles(fileFilter);
	      System.out.println(files.length);
	      
	      if (files.length == 0) {
	         System.out.println("Either dir does not exist or is not a directory");
	      } else {
	         for (int i = 0; i< files.length; i++) {
	            File filename = files[i];	            
	            meters.add(filename.getName());
	         }
	      }
	}catch(Exception e){
		
	}
	
	return meters;
}

public List<MobileLogs> getMobileLogs(String month){
	List<MobileLogs> list=new ArrayList();
	try{
		month=month.split("/")[1]+"-"+month.split("/")[0];
		Query q=sf.getCurrentSession().createQuery("from MobileLogs where log_timestamp like '"+month+"%'");
		list=q.list();
		
	}catch(Exception e){
		e.printStackTrace();
	}
	return list;
}

public List<MobileUploads> getMobileUploads(){
	List<MobileUploads> list=new ArrayList();
	try{
		Query q=sf.getCurrentSession().createQuery("from MobileUploads where source='MobileApp' order by recordid desc");
		list=q.list();
	}catch(Exception e){
		e.printStackTrace();
	}
	return list;
}

public TotalMasterdataBean getMeterDetails(String meter){
	TotalMasterdataBean tb=null;
	try{
		Query q=sf.getCurrentSession().createQuery("from TotalMasterdataBean where meter_sl_no='"+meter+"'");
		tb=(TotalMasterdataBean)q.uniqueResult();
		
		if(tb==null){
			tb=new TotalMasterdataBean();
			tb.setMeter_sl_no(meter);
		}
		
	}catch(Exception e){
		e.printStackTrace();
	}
	
	return tb;
			
}
public D3_columns getD3_header(String meter,String type){
	D3_columns str=null;	
	try{
		Query q=sf.getCurrentSession().createQuery("from D3_columns where meter_serial_no='"+meter+"' and billing_type='"+type+"'");
		str=(D3_columns)q.uniqueResult();
	}catch(Exception e){
		e.printStackTrace();
	}
	
	return str;
}
public D4_columns getD4_header(String meter){
	D4_columns str=null;	
	try{
		Query q=sf.getCurrentSession().createQuery("from D4_columns where meter_serial_no='"+meter+"'");
		str=(D4_columns)q.uniqueResult();
	}catch(Exception e){
		e.printStackTrace();
	}
	
	return str;
}
public D5_columns getD5_header(String meter){
	D5_columns str=null;	
	try{
		Query q=sf.getCurrentSession().createQuery("from D5_columns where meter_serial_no='"+meter+"'");
		str=(D5_columns)q.uniqueResult();
	}catch(Exception e){
		e.printStackTrace();
	}
	
	return str;
}

public D6_columns getD6_header(String meter){
	D6_columns str=null;	
	try{
		Query q=sf.getCurrentSession().createQuery("from D6_columns where meter_serial_no='"+meter+"'");
		str=(D6_columns)q.uniqueResult();
	}catch(Exception e){
		e.printStackTrace();
	}
	
	return str;
}

public List<D3_b3> getD3_values(String meter,String sdate,String edate){
	List<D3_b3> list=new ArrayList();
	try{
		Connection con=sf.getCurrentSession().connection();
		
		String sql="select date_format(d3_time_stamp,'%d-%m-%Y %H:%i:%s') billing_date,data_string from d3_b3 b3 where meter_serial_no='"+meter+"' and  str_to_date(date_format(b3.d3_time_stamp,'%d/%m/%Y'),'%d/%m/%Y')>= str_to_date('" + sdate + "','%d/%m/%Y') and str_to_date(date_format(b3.d3_time_stamp,'%d/%m/%Y'),'%d/%m/%Y') <= str_to_date('" + edate + "','%d/%m/%Y') order by d3_time_stamp desc";
		if(!database.equalsIgnoreCase("mysql"))
			sql="select to_char(d3_time_stamp,'dd-MM-yy HH:mm:ss') billing_date,data_string from d3_b3 b3 where meter_serial_no='"+meter+"' and  trunc(b3.d3_time_stamp)>= to_date('" + sdate + "','dd/MM/yy') and trunc(b3.d3_time_stamp) <= to_date('" + edate + "','dd/MM/yy') order by d3_time_stamp desc";
		ResultSet rs=con.prepareStatement(sql).executeQuery();
		while(rs.next()){
			D3_b3 b3=new D3_b3();
			
			b3.setMeter_serial_no(meter);
			b3.setBilling_day(rs.getString("billing_date"));
			b3.setData_string(rs.getString("data_string"));
			list.add(b3);
		}
		
	}catch(Exception e){
		e.printStackTrace();
	}
	return list;
}

public List<EnergyTODBean> getD3_b4_values(String meter,String sdate,String edate,int totTods){
	List<EnergyTODBean> list=new ArrayList();
	try{
		double emf=1;
		String data_string2[]=null;
	    String tod_string[]=null;
	    NumberFormat formatter = new DecimalFormat("#0.000");
		Connection con=sf.getCurrentSession().connection();
		System.out.println("Total TODs :"+totTods);
		String sql="select date_format(d3_time_stamp,'%d-%m-%Y %H:%i:%s') billing_date,"
                + "data_string,tod_value from d3_b4 b3 where meter_serial_no='"+meter+"' and  str_to_date(date_format(b3.d3_time_stamp,'%d/%m/%Y'),'%d/%m/%Y')>= str_to_date('" + sdate + "','%d/%m/%Y') and str_to_date(date_format(b3.d3_time_stamp,'%d/%m/%Y'),'%d/%m/%Y') <= str_to_date('" + edate + "','%d/%m/%Y') "
                + "order by d3_time_stamp desc ";
		if(!database.equalsIgnoreCase("mysql")){
			sql="select to_char(d3_time_stamp,'dd-MM-yy HH:mm:ss') billing_date,"
	                + "data_string,tod_value from d3_b4 b3 where meter_serial_no='"+meter+"' and  trunc(b3.d3_time_stamp)>= to_date('" + sdate + "','dd/MM/yy') and trunc(b3.d3_time_stamp) <= to_date('" + edate + "','dd/MM/yy') "
	                + "order by d3_time_stamp desc ";
		}
		System.out.println(sql);
		ResultSet rs=con.prepareStatement(sql).executeQuery();
		while (rs.next())
        {
            tod_string=rs.getString("tod_value").split(",");
           
            if (rs.getString("data_string") != null) {
                String data_string[] = rs.getString("data_string").split(",");

                String val = null;
                for (int i = 0; i < data_string.length; i++) {
                    
                    val = data_string[i];
                    if (val.length() > 1) {
                        try {
                            double d = Double.parseDouble(val);                            
                             data_string[i] = String.valueOf(formatter.format(d));   
                        } catch (Exception e) {
                        }//end catch
                    }//end if
                }//end for

    for(int i=(Arrays.asList(tod_string).contains("0")?0:1);i<=(Arrays.asList(tod_string).contains("0")?totTods-1:totTods);i++){
            try{ 
            	EnergyTODBean etb=new EnergyTODBean(); 
                  etb.setBilling_date(rs.getString("billing_date"));
                  etb.setTod((Arrays.asList(tod_string).contains("0")?i:i)+"");
                  String vals="";
	                for(int td=0;td<tod_string.length;td++){
	                    if(i==Integer.parseInt(tod_string[td])){  
	                      vals=vals+","+formatter.format(Double.parseDouble(data_string[td])*emf);    
	                   }
	                }//for 
	                vals=vals.substring(1);
	                etb.setValueString(vals);
	                
	                list.add(etb);
                } catch(Exception e){
                	//e.printStackTrace();
                } 

                 }
                 
                }//if datastring is not null
                
                
                   
            }//end of while
		
	}catch(Exception e){
		e.printStackTrace();
	}
	return list;
}

public List<D4_data> getD4_values(String meter,String sdate,String edate){
	List<D4_data> list=new ArrayList();
	try{
		Connection con=sf.getCurrentSession().connection();
		
		String sql="select *  from d4_data d4 where meter_serial_no='" + meter + "'  and str_to_date(date_format(d4_time_stamp,'%d/%m/%Y'),'%d/%m/%Y')>= str_to_date('" + sdate + "','%d/%m/%Y') and str_to_date(date_format(d4_time_stamp,'%d/%m/%Y'),'%d/%m/%Y') <= str_to_date('" + edate + "','%d/%m/%Y')"
                + " order by d4_time_stamp desc";
		if(!database.equalsIgnoreCase("mysql")){
			sql="select *  from d4_data d4 where meter_serial_no='" + meter + "'  and trunc(d4_time_stamp)>= to_date('" + sdate + "','dd/MM/yy') and trunc(d4_time_stamp) <= to_date('" + edate + "','dd/MM/yy')"
	                + " order by d4_time_stamp desc";
		}
		ResultSet rs=con.prepareStatement(sql).executeQuery();
		while(rs.next()){
			D4_data b3=new D4_data();
			
			b3.setMeter_serial_no(meter);
			b3.setD4_date(rs.getString("d4_date"));
			b3.setData_string(rs.getString("data_string"));
			list.add(b3);
		}
		
	}catch(Exception e){
		e.printStackTrace();
	}
	return list;
}

public List<D5_data> getD5_values(String meter,String sdate,String edate){
	List<D5_data> list=new ArrayList();
	try{
		Connection con=sf.getCurrentSession().connection();
		sdate=sdate.replaceAll("/", "-");
		edate=edate.replaceAll("/", "-");
		
		String sql="select event_date,e.event_type as type,event_status,data_string,d.event_type from  d5_data d,events e where d.meter_serial_no='"+meter+"' and d.event_type=e.event_code and "
                + "str_to_date(substr(event_date,1,10),'%d-%m-%Y')>=str_to_date('" + sdate + "','%d-%m-%Y') "
                + "and str_to_date(substr(event_date,1,10),'%d-%m-%Y')<=str_to_date('" + edate + "','%d-%m-%Y') order by d5_time_stamp desc ";
		if(!database.equalsIgnoreCase("mysql")){
			sql="select event_date,e.event_type as type,event_status,data_string,d.event_type from  d5_data d,events e where d.meter_serial_no='"+meter+"' and d.event_type=e.event_code and "
	                + " trunc(d5_time_stamp)>=to_date('" + sdate + "','dd-MM-yy') "
	                + "and trunc(d5_time_stamp)<to_date('" + edate + "','dd-MM-yy') order by d5_time_stamp desc ";
		}
		//System.out.println(sql);
		ResultSet rs=con.prepareStatement(sql).executeQuery();
		while(rs.next()){
			D5_data b3=new D5_data();
			
			b3.setMeter_serial_no(meter);
			b3.setEvent_date(rs.getString("event_date"));
			b3.setEvent_status(rs.getString("event_status"));
			b3.setEvent_type(rs.getString("type"));
			b3.setData_string(rs.getString("data_string"));
			list.add(b3);
		}
		
	}catch(Exception e){
		e.printStackTrace();
	}
	return list;
}

public List<D6_data> getD6_values(String meter,String sdate,String edate){
	List<D6_data> list=new ArrayList();
	try{
		Connection con=sf.getCurrentSession().connection();
		sdate=sdate.replaceAll("/", "-");
		edate=edate.replaceAll("/", "-");
		SimpleDateFormat sdf=new SimpleDateFormat("dd-MM-yyyy");
		SimpleDateFormat sdf1=new SimpleDateFormat("yyyy-MM-dd");
		sdate=sdf1.format(sdf.parse(sdate));
		edate=sdf1.format(sdf.parse(edate));
		
		String sql="select snapshot_date,data_string from d6_data where meter_serial_no='" + meter + "' and DATE(D6_TIME_STAMP) BETWEEN " + " ' " + sdate + " ' " + " and " + " '" + edate + " ' order by D6_TIME_STAMP desc";
		if(!database.equalsIgnoreCase("mysql")){
			sql="select snapshot_date,data_string from d6_data where meter_serial_no='" + meter + "' and trunc(D6_TIME_STAMP) BETWEEN to_date('" + sdate + "','yy-MM-dd') and to_date('" + edate + "','yy-MM-dd') order by D6_TIME_STAMP desc";	
		}
		System.out.println(sql);
		ResultSet rs=con.prepareStatement(sql).executeQuery();
		while(rs.next()){
			D6_data b3=new D6_data();
			
			b3.setMETER_SERIAL_NO(meter);
			b3.setSNAPSHOT_DATE(rs.getString("snapshot_date"));			
			b3.setDATA_STRING(rs.getString("data_string"));
			list.add(b3);
		}
		
	}catch(Exception e){
		e.printStackTrace();
	}
	return list;
}

}
