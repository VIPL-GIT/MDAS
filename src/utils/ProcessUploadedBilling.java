package utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.rtdas.mdm.models.AutoResetEnergies_Cesc;

import jdk.management.resource.internal.inst.SocketOutputStreamRMHooks;



public class ProcessUploadedBilling
{  
	
	 Connection con=null;
	 Statement stmt=null;
	 PreparedStatement ps=null;
	 String sql="";
	 
	 DecimalFormat threeDec=new DecimalFormat("#0.000");
	
	 
	 public void InsertRowInDB(String KNO,String METER,String KWHIMP,String KVAHIMP,String KVAIMP,String KWIMP,String KWHEXP,String KVAHEXP,String KVAEXP,String KWEXP,String PFIMP,String record_type,String mdate1,String mdate)throws SQLException, IOException{	  
	 con=DBUtil.getConnection();
	 stmt=con.createStatement();
	// sql="Insert into auto_reset_energies_cesc(OFFICE CODE,TOKEN_NO,KNO,BINDER NO,ACCOUNT NO,NAME,ADDRESS,METER,Make,TYPE,STATUS,KWH IMP,KVAH IMP,KVA IMP,KW IMP,PF IMP,KWH EXP,KVAH EXP,KVA EXP,KW EXP,PF EXP,REMARKS) values("+OFFICECODE+","+TOKEN_NO+","+KNO+","+BINDERNO+","+ACCOUNTNO+","+NAME+","+ADDRESS+","+METER+","+Make+","+TYPE+","+STATUS+","+KWHIMP+","+KVAHIMP+","+KVAIMP+","+KWIMP+","+PFIMP+","+KWHEXP+","+KVAHEXP+","+KVAEXP+","+KWEXP+","+PFEXP+","+REMARKS+")";
	 sql="Insert into auto_reset_energies_cesc(CONSUMERNO,meter_serial_no,kwh_i,kvah_i,mdkva_i,mdkva_e,kwh_e,kvah_e,mdkw_i,mdkw_e,pf,record_type,billing_timestamp,datetime) values('"+KNO+"','"+METER+"','"+KWHIMP+"','"+KVAHIMP+"','"+KVAIMP+"','"+KWIMP+"','"+KWHEXP+"','"+KVAHEXP+"','"+KVAEXP+"','"+KWEXP+"','"+PFIMP+"','"+record_type+"','"+mdate1+"','"+mdate+"')";
	 ps=con.prepareStatement(sql);
	 ps.executeUpdate();
	 System.out.println("Values Inserted Successfully");
	 } 
	
	 public void insertIntoDb(List<AutoResetEnergies_Cesc> list){
		 try{
			 SimpleDateFormat sdf =new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
			 SimpleDateFormat sdf1 =new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			 if(!list.isEmpty()){
			  for(AutoResetEnergies_Cesc are:list){
				     con=DBUtil.getConnection();
				     sql="Insert into auto_reset_energies_cesc(meter_serial_no,kwh_i,kvah_i,mdkva_i,record_type,billing_timestamp,datetime,office_code,token_no,bccd,kno,consumer_name,consumer_address,remarks,pf) "
				     		+ "values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
					 ps=con.prepareStatement(sql);										 
                     ps.setString(1,are.getMeter_serial_no());
                     ps.setString(2,are.getKWH_I());
                     ps.setString(3,are.getKVAH_I());
                     ps.setString(4,are.getMDKVA_I());
                     ps.setString(5,"M");
                     ps.setString(6,sdf1.format(new java.util.Date()));
                     ps.setString(7,sdf.format(new java.util.Date()));
                     ps.setString(8,are.getOffice_code());
                     ps.setString(9,are.getToken_no());
                     ps.setString(10,are.getBccd());
                     ps.setString(11,are.getKno());
                     ps.setString(12,are.getConsumer_name());
                     ps.setString(13,are.getConsumer_address());
                     ps.setString(14,are.getRemarks());
                     ps.setString(15, threeDec.format(Double.parseDouble(are.getKWH_I())/Double.parseDouble(are.getKVAH_I())));
                     
					 ps.executeUpdate();
					 System.out.println("Values Inserted Successfully");
					 } 
			        con.close();
			  }
			 
		 }catch(Exception e){
			 e.printStackTrace();
		 }
		 
	 }
	 
	 public void BackUP(String fileloc,String filename,HttpSession session) {
		 
		 try {
			 String path = session.getServletContext().getInitParameter("file-upload-server");
			// String dest = dir.getCanonicalPath() + File.separator + filetobackup;
			/* String dest = path + filename;
			 File fin = new File(fileloc+""+filename);
			 FileInputStream fis = new FileInputStream(fileloc+""+filename);
			 BufferedReader in = new BufferedReader(new InputStreamReader(fis));
			 File f=new File(dest);
			 FileWriter fstream = new FileWriter(f, true);
			 BufferedWriter out = new BufferedWriter(fstream);
			 String aLine = null;
			 while ((aLine = in.readLine()) != null) {
			 //Process each line and add output to Dest.txt file
			 out.write(aLine);
			 out.newLine();*/
			 
			
			 
			 
			 String source = fileloc+""+filename;
			 System.out.println("souce -- "+source);
			 
			 String dest =path+""+filename;
			 System.out.println("souce -- "+source+"  dest  -- "+dest);
			 File fin = new File(source);
			 FileInputStream fis = new FileInputStream(fin);
			 BufferedReader in = new BufferedReader(new InputStreamReader(fis));
			 FileWriter fstream = new FileWriter(dest, true);
			 BufferedWriter out = new BufferedWriter(fstream);
			 String aLine = null;
			 while ((aLine = in.readLine()) != null) {
			 //Process each line and add output to Dest.txt file
			 out.write(aLine);
			 out.newLine();
			 
			 
			 
			 }
			 
			 in.close();
				// close buffer writer
			 out.close();
		     fin.delete();   
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();} 
		 }
	 
	 
	 
public List<AutoResetEnergies_Cesc> readBillingFile(String filename,String fileloc,HttpSession session) {
	ProcessUploadedBilling xl=new ProcessUploadedBilling();
	SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
	SimpleDateFormat formatter1 = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
	
	Date date = new Date();
	System.out.println(formatter.format(date));
	String mydate=formatter.format(date).substring(0,10).replace("/","-")+" 00:00:00";
	String mydate1=formatter1.format(date).substring(0,10).replace("/","-")+" 00:00:00";
	
	
	System.out.println(mydate+"  "+mydate1);
	
	List<AutoResetEnergies_Cesc> list=new ArrayList<AutoResetEnergies_Cesc>();
	try {
		  
      //  FileInputStream file = new FileInputStream(new File("f://BIKANER CITY  DATA.xlsx"));
		File f=new File(filename+""+fileloc);
		FileInputStream file = new FileInputStream(f);
        XSSFWorkbook workbook = new XSSFWorkbook(file);
        XSSFSheet sheet = workbook.getSheetAt(0);
        Iterator<Row> rowIterator = sheet.iterator();
        rowIterator.next();
        while(rowIterator.hasNext())
        {
            Row row = rowIterator.next();
            //For each row, iterate through each columns
            Iterator<Cell> cellIterator = row.cellIterator();
            AutoResetEnergies_Cesc are=new AutoResetEnergies_Cesc();	
            while(cellIterator.hasNext())
            {
                Cell cell = cellIterator.next();
                if(!cell.getCellType().name().equals("STRING"))
                  cell.setCellType(CellType.STRING);
                
            }        
            
            are.setOffice_code(getCellValue(row.getCell(0)));
            are.setToken_no(getCellValue(row.getCell(1)));
            are.setBccd(getCellValue(row.getCell(2)));
            are.setKno(getCellValue(row.getCell(3)));
            are.setConsumer_name(getCellValue(row.getCell(4)));
            are.setConsumer_address(getCellValue(row.getCell(5)));
            are.setMeter_serial_no(getCellValue(row.getCell(6)));
            are.setKWH_I(getCellValue(row.getCell(7)));
            are.setKVAH_I(getCellValue(row.getCell(8)));
            are.setMDKVA_I(getCellValue(row.getCell(9)));
            are.setRemarks(getCellValue(row.getCell(10)));
            
          
          //System.out.println(row.getCell(10).getCellType());
          
            list.add(are); 
        }
        //System.out.println(list.toString());  ;
        file.close();
        f.delete();
    } catch (Exception e) {
        e.printStackTrace();
        }	
	return list;
	
}
public String getCellValue(Cell cell){
	String val="";
	if(cell!=null){
	switch(cell.getCellType().name()){
	  case "NUMERIC":
		  val=""+cell.getNumericCellValue();
		  break;
	  case "STRING" :
		  val=cell.getStringCellValue();
		  break;
	}
	}
	
	return val;
}
public void processBillingFile(String filename,String fileloc,HttpSession session) {
	ProcessUploadedBilling xl=new ProcessUploadedBilling();
	SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
	SimpleDateFormat formatter1 = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
	
	Date date = new Date();
	System.out.println(formatter.format(date));
	String mydate=formatter.format(date).substring(0,10).replace("/","-")+" 00:00:00";
	String mydate1=formatter1.format(date).substring(0,10).replace("/","-")+" 00:00:00";
	
	
	List<AutoResetEnergies_Cesc> list=new ArrayList<AutoResetEnergies_Cesc>();
	try {
		  
      //  FileInputStream file = new FileInputStream(new File("f://BIKANER CITY  DATA.xlsx"));
		FileInputStream file = new FileInputStream(new File(filename+""+fileloc));
        XSSFWorkbook workbook = new XSSFWorkbook(file);
        XSSFSheet sheet = workbook.getSheetAt(0);
        Iterator<Row> rowIterator = sheet.iterator();
        
        rowIterator.next();
        while(rowIterator.hasNext())
        {
        	AutoResetEnergies_Cesc are=new AutoResetEnergies_Cesc();	
            Row row = rowIterator.next();
            //For each row, iterate through each columns
            Iterator<Cell> cellIterator = row.cellIterator();

            while(cellIterator.hasNext())
            {
                Cell cell = cellIterator.next();
               if(!cell.getCellType().name().equals("STRING"))
                  cell.setCellType(CellType.STRING);
                
            }
          
            
            are.setOffice_code(getCellValue(row.getCell(0)));
            are.setToken_no(getCellValue(row.getCell(1)));
            are.setBccd(getCellValue(row.getCell(2)));
            are.setKno(getCellValue(row.getCell(3)));
            are.setConsumer_name(getCellValue(row.getCell(4)));
            are.setConsumer_address(getCellValue(row.getCell(5)));
            are.setMeter_serial_no(getCellValue(row.getCell(6)));
            are.setKWH_I(getCellValue(row.getCell(7)));
            are.setKVAH_I(getCellValue(row.getCell(8)));
            are.setMDKVA_I(getCellValue(row.getCell(9)));
            are.setRemarks(getCellValue(row.getCell(10)));
            
            list.add(are);           
          
            
        }
        xl.insertIntoDb(list);
        //System.out.println(list.toString());  ;
        file.close();
        BackUP(filename,fileloc,session);
    } catch (Exception e) {
        e.printStackTrace();
  
  }	
	
	
}
	
	
public static void main(String[] args)   
{  
		
}
}  
