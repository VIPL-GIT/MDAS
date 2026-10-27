package com.rtdas.mdm.dao.impl;

import java.text.SimpleDateFormat;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


import com.rtdas.mdm.models.D2_curr;
import com.rtdas.mdm.models.D3_b3;
import com.rtdas.mdm.models.D3_b5;
import com.rtdas.mdm.models.D3_columns;
import com.rtdas.mdm.models.D4_columns;
import com.rtdas.mdm.models.D4_data;
import com.rtdas.mdm.models.D5_columns;
import com.rtdas.mdm.models.D5_data;
import com.rtdas.mdm.models.D5_data_all;
import com.rtdas.meterdata.beans.AutoResetEnergies;

@Repository("MeterDataDao")
public class MeterDataDaoImpl implements com.rtdas.mdm.dao.MeterDataDao {
	
	@Autowired
	private SessionFactory sf;
	SimpleDateFormat sdf=new SimpleDateFormat("dd-MM-yyyy");

	@Override
	public D2_curr getLatestInstant(String meter,String date) {
		D2_curr d2=null;
		try {
			System.out.println(meter+"\t"+date);
			String sql="from D2_curr where METER_SERIAL_NO=:meter and G2 like '"+date+"%' order by METER_TIME_STAMP desc";
			Query q=sf.getCurrentSession().createQuery(sql);
			q.setString("meter", meter);
			q.setMaxResults(1);
			
			List<D2_curr> d2_list=q.list();
			
			if(!d2_list.isEmpty()) {				
				d2=d2_list.get(0);
			}
			
		}catch(Exception e) {
			 e.printStackTrace();
		}
		return d2;
	}

	@Override
	public D3_columns getBillingHeaders(String billing_type,String meter) {
		D3_columns header=null;
		try {
			String sql="from D3_columns where meter_serial_no=:meter and billing_type='"+billing_type+"'";
			Query q=sf.getCurrentSession().createQuery(sql);
			q.setString("meter", meter);
			header=(D3_columns)q.uniqueResult();
			
		}catch(Exception e) {}
		
		return header;
	}

	@Override
	public D4_columns getLoadHeaders(String meter) {
		D4_columns header=null;
		try {
			String sql="from D4_columns where meter_serial_no=:meter";
			Query q=sf.getCurrentSession().createQuery(sql);
			q.setString("meter", meter);
			header=(D4_columns)q.uniqueResult();
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return header;
	}

	@Override
	public D5_columns getEventsHeaders(String meter) {
		D5_columns header=null;
		try {
			String sql="from D5_columns where meter_serial_no=:meter";
			Query q=sf.getCurrentSession().createQuery(sql);
			q.setString("meter", meter);
			header=(D5_columns)q.uniqueResult();
			
		}catch(Exception e) {}
		
		return header;
	}

	@Override
	public List<D3_b3> getBillingEnergies(String meter,String date) {
		List<D3_b3> d3list=null;
		try {
			
			String sql="from D3_b3 where meter_serial_no=:meter and date(d3_time_stamp)=:date1";
			Query q=sf.getCurrentSession().createQuery(sql);
			q.setString("meter", meter);
			q.setDate("date1", sdf.parse(date));
            
			
			d3list=q.list();
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return d3list;
	}

	@Override
	public List<D3_b5> getBillingMds(String meter,String date) {

		List<D3_b5> list=null; 
        try {
        	String sql="from D3_b5 where meter_serial_no=:meter and date(d3_time_stamp)=:date1 and data_string is not null and data_string!=''";
			Query q=sf.getCurrentSession().createQuery(sql);
			q.setString("meter", meter);
			q.setDate("date1", sdf.parse(date));
            
			
			list=q.list();
			
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public List<D4_data> getLoadProfile(String meter,String date) {
     
		List<D4_data> list=null;
		try {
			String sql="from D4_data where meter_serial_no=:meter and date(d4_time_stamp)=:date1";
			Query q=sf.getCurrentSession().createQuery(sql);
			q.setString("meter", meter);
			q.setDate("date1", sdf.parse(date));
            
			
			list=q.list();
			
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public List<D5_data> getEvents(String meter,String date) {
		List<D5_data> list=null;
       try {
    	   //String sql="from D5_data where meter_serial_no=:meter and date(d5_time_stamp)=:date1";
    	   String sql="from D5_data where meter_serial_no=:meter and event_date LIKE '%"+date.split("-")[2]+"%' order by event_date desc";
			Query q=sf.getCurrentSession().createQuery(sql);
			q.setString("meter", meter);
			//q.setDate("date1", sdf.parse(date));
           
			
			list=q.list();
			
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public List<AutoResetEnergies> getAutoResetEnergies(String meter) {

		List<AutoResetEnergies> arenergies=null;
		try {
			System.out.println("===================="+meter);
	    	   String sql="from AutoResetEnergies where meter_serial_no=:meter order by billing_timestamp asc";
				Query q=sf.getCurrentSession().createQuery(sql);
				q.setString("meter", meter);
				
				arenergies=q.list();
				
				
			}catch(Exception e) {
				e.printStackTrace();
			}
		
		return arenergies;
	}

	@Override
	public List<D5_data_all> getAllEvents(String meter) {
		List<D5_data_all> list=null;
	       try {
	    	   String sql="from D5_data_all where meter_serial_no=:meter order by d5_time_stamp";
				Query q=sf.getCurrentSession().createQuery(sql);
				q.setString("meter", meter);
				
	           
				
				list=q.list();
				
				
			}catch(Exception e) {
				e.printStackTrace();
			}
			return list;
	}

}
