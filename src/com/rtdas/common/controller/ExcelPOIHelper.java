package com.rtdas.common.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.springframework.stereotype.Component;

import utils.MyCell;

@Component 
public class ExcelPOIHelper {

	 private String readCellContent(Cell cell) {
    	 DateFormat df = new SimpleDateFormat("dd-MM-yyyy");
        String content;
        switch (cell.getCellTypeEnum()) {
        case STRING:
            content = cell.getStringCellValue();
            break;
        case NUMERIC:
            if (HSSFDateUtil.isCellDateFormatted(cell)) {
            	
                content = df.format(cell.getDateCellValue());
            } else {
            	cell.setCellType(CellType.STRING);
            	
                content = cell.getStringCellValue();                
            }
            break;
        case BOOLEAN:
            content = cell.getBooleanCellValue() + "";
            break;
        case FORMULA:
            content = cell.getCellFormula() + "";
            break;
        default:
            content = "";
        }
        return content;
    }

	 
	 
	 public Map<Integer, List<MyCell>> readHSSFWorkbook(FileInputStream fis) throws IOException {
	    	
	        Map<Integer, List<MyCell>> data = new HashMap<>();
	        HSSFWorkbook workbook = null;
	        try {
	            workbook = new HSSFWorkbook(fis);
	            HSSFSheet sheet = workbook.getSheetAt(0);
	            
	           // System.out.println(sheet+"  sheet "+sheet.getFirstRowNum()+" rows "+sheet.getLastRowNum()+" physical number "+sheet.getPhysicalNumberOfRows());
	            
	            
	            
	            
	            for (int i = sheet.getFirstRowNum(); i <= sheet.getLastRowNum(); i++) {
	                HSSFRow row = sheet.getRow(i);
	                
	                
	                //System.out.println("row --> "+row);
	 

	                
	                if (row != null) {
	                	data.put(i, new ArrayList<>());
	                	//System.out.println("last cell number "+row.getLastCellNum());
	                    for (int j = 0; j < row.getLastCellNum(); j++) {
	                        HSSFCell cell = row.getCell(j);
	                        //System.out.println("cell --> "+cell);
	                        
	                        
	                        if (cell != null) {
	                            HSSFCellStyle cellStyle = cell.getCellStyle();
	                            MyCell myCell = new MyCell();
	                            HSSFColor bgColor = cellStyle.getFillForegroundColorColor();
	                          //  System.out.println("bgColor --> "+bgColor);
	                            if (bgColor != null) {
	                                short[] rgbColor = bgColor.getTriplet();
	                                //myCell.setBgColor("rgb(" + rgbColor[0] + "," + rgbColor[1] + "," + rgbColor[2] + ")");
	                            }
	                            HSSFFont font = cell.getCellStyle().getFont(workbook);
	                            myCell.setTextSize(font.getFontHeightInPoints() + "");
	                            if (font.getBold()) {
	                                myCell.setTextWeight("bold");
	                            }
	                            HSSFColor textColor = font.getHSSFColor(workbook);
	                            if (textColor != null) {
	                                short[] rgbColor = textColor.getTriplet();
	                                myCell.setTextColor("rgb(" + rgbColor[0] + "," + rgbColor[1] + "," + rgbColor[2] + ")");
	                            }
	                            myCell.setContent(readCellContent(cell));
	                            data.get(i).add(myCell);
	                            } else {
	                        	//System.out.println("else ");
	                        	 MyCell myCell = new MyCell("");
	                        	 myCell.setBgColor("red");
	                             data.get(i).add(myCell);
	                        }
	                    }
	                }
	            }
	        } finally {
	            if (workbook != null) {
	                workbook.close();
	            }
	        }
	        return data;
	    }

	 

    /*public Map<Integer, List<MyCell>> readExcel(String fileLocation) throws IOException {

        Map<Integer, List<MyCell>> data = new HashMap<>();
        FileInputStream fis = new FileInputStream(new File(fileLocation));
       

        if (fileLocation.endsWith(".xls")) {
            data = readHSSFWorkbook(fis);
        } else if (fileLocation.endsWith(".xlsx")) {
            
        }

         int maxNrCols = data.values().stream()
          .mapToInt(List::size)
          .max()
          .orElse(0);

       
        data.values().stream()
          .filter(ls -> ls.size() < maxNrCols)
          .forEach(ls -> {
              IntStream.range(ls.size(), maxNrCols)
                .forEach(i -> ls.add(new MyCell("")));
          });

        return data;
    }
*/    
}
