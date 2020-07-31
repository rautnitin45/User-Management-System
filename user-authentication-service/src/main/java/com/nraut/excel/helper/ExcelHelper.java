package com.nraut.excel.helper;

import java.io.InputStream;
import java.util.*;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import com.nraut.model.UserDetails;


/**
 * @author Nitin
 *
 */
public class ExcelHelper {
	public static String TYPE = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";
	static String[] HEADERs = { "id", "name", "type"};
	static String SHEET = "UserDetails";
	
	/**
	 * @param file
	 * @return
	 */
	public static boolean hasExcelFormat(MultipartFile file) {
	    if (!TYPE.equals(file.getContentType())) {
	      return false;
	    }
	    return true;
	  }
	
	/**
	 * @param inputStream
	 * @return
	 */
	public static List<UserDetails> excelToTutorials(InputStream inputStream){
		try{
			  Workbook workbook = new XSSFWorkbook(inputStream);
		      Sheet sheet = workbook.getSheet(SHEET);
		      
		    //  Sheet sheet = workbook.getSheet("UserDetails");		      
		      System.out.println("Sheet : "+ sheet);
		      
		      if (sheet == null) {
		    	   throw new IllegalArgumentException("No sheet exists with name " + SHEET);
		    	}
		      
		      Iterator<Row> rows = sheet.iterator();
		      
		      List<UserDetails> userDetails = new ArrayList<UserDetails>();

		      int rowNumber = 0;
		      while (rows.hasNext()) {
		        Row currentRow = rows.next();

		        // skip header
		        if (rowNumber == 0) {
		          rowNumber++;
		          continue;
		        }

		        Iterator<Cell> cellsInRow = currentRow.iterator();		        
		        UserDetails userDetail = new UserDetails();
		        
		        int cellIdx = 0;
		        while (cellsInRow.hasNext()) {
		          Cell currentCell = cellsInRow.next();

		          switch (cellIdx) {
		          case 0:
		        	  userDetail.setId((long) currentCell.getNumericCellValue());
		            break;

		          case 1:
		        	  userDetail.setName(currentCell.getStringCellValue());
		            break;

		          case 2:
		        	  userDetail.setType(currentCell.getStringCellValue());
		            break;

		          default:
		            break;
		          }

		          cellIdx++;
		        }
		        userDetails.add(userDetail);
		      }
		      workbook.close();    
		      return userDetails;
		      
		}catch(Exception e){
			throw new RuntimeException("fail to parse Excel file: " + e.getMessage());
		}	
	}
}
