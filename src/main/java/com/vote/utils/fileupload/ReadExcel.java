package com.vote.utils.fileupload;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

/**
 * 
 * @ClassName:ReadExcel
 * @Description:TODO 读取EXCEL
 * @author LTT-AH-ZY https://github.com/LTT-AH-ZY-github
 * @date 2019年5月22日
 */
public class ReadExcel {
	//Excel总行数
	private Integer totalRows = 0;
	//Excel总列数
	private Integer totalCells = 0;
	//错误信息
	private String errorMsg;
	
	//构造方法
	public ReadExcel() {
		// TODO Auto-generated constructor stub
	}
	
	//获取总行数
	public Integer getTotalRows() {
		return totalRows;
	}
	//获取总列数
	public Integer getTotalCells() {
		return totalCells;
	}
	//获取错误信息
	public String getErrorMsg() {
		return errorMsg;
	}
	
	/**
	 * 验证EXCEL文件
	 * @param filePath
	 * @return
	*/
	public boolean validateExcel(String filePath){
		if (filePath == null || !(WDWUtil.isExcel2003(filePath) || WDWUtil.isExcel2007(filePath))){  
			errorMsg = "文件名不是excel格式";  
			return false;  
		}  
		return true;
	}
	
	public List<Map<String,String>> getExcelInfo(String fileName,MultipartFile Mfile){
		//把spring文件上传的MultipartFile转换成CommonsMultipartFile类型
		CommonsMultipartFile cf= (CommonsMultipartFile)Mfile; //获取本地存储路径
		File file = new  File("F:\\Project_UploadFile");
		//创建一个目录 （它的路径名由当前 File 对象指定，包括任一必须的父路径。）
		if (!file.exists()) file.mkdirs();
		//新建一个文件
		File file1 = new File("F:\\Project_UploadFile\\fileupload" + new Date().getTime() + ".xlsx"); 
		//将上传的文件写入新建的文件中
		try {
			cf.getFileItem().write(file1); 
		} catch (Exception e) {
			e.printStackTrace();
		}
		//初始化信息的集合    
		List<Map<String,String>> resultList=new ArrayList<Map<String,String>>();
		//初始化输入流
		InputStream is = null;  
		try {
			//验证文件名是否合格
			if(!validateExcel(fileName)){
				return null;
			}
			//根据文件名判断文件是2003版本还是2007版本
			boolean isExcel2003 = true; 
			if(WDWUtil.isExcel2007(fileName)){
				isExcel2003 = false;  
			}
			//根据新建的文件实例化输入流
			is = new FileInputStream(file1);
			//根据excel里面的内容读取客户信息
			resultList = getExcelInfo(is, isExcel2003); 
			is.close();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			if(is !=null){
	        	try{
	            	is.close();
	        	}catch(IOException e){
	            	is = null;    
	            	e.printStackTrace();  
	        	}
	    	}
		}
		return resultList;
	}
	/**
	 * 根据excel里面的内容读取客户信息
	 * @param is 输入流
	 * @param isExcel2003 excel是2003还是2007版本
	 * @return
	 * @throws IOException
	 */
	public List<Map<String,String>> getExcelInfo(InputStream is,boolean isExcel2003){
		List<Map<String,String>> resultList=null;
		try {
			/*根据版本选择创建Workbook的方式 */
			Workbook wb = null;
			//当excel是2003时
			if(isExcel2003){
				wb = new HSSFWorkbook(is); 
			}
			else{//当excel是2007时
				wb = new XSSFWorkbook(is); 
			}
			//读取Excel里面客户的信息
			resultList=readExcelValue(wb);
		} catch (Exception e) {
			e.printStackTrace();  
		}
		return resultList;
	}
	/**
	 * 读取Excel里面客户的信息
	 * @param wb
	 * @return
	 */
	public List<Map<String,String>> readExcelValue(Workbook wb){
		//得到第一个shell  
		Sheet sheet=wb.getSheetAt(0);
		//得到Excel的行数
		this.totalRows=sheet.getPhysicalNumberOfRows();
	       
		//得到Excel的列数(前提是有行数)
		if(totalRows>=1 && sheet.getRow(0) != null){
			this.totalCells=sheet.getRow(0).getPhysicalNumberOfCells();
		}
		//初始化信息的集合    
		List<Map<String,String>> resultList=new ArrayList<Map<String,String>>();
		/*这里的map用来添加未知列名的值，作为添加任意Excel的模板，若已知列名，可以直接用对应的pojo来set*/
		String[] Titles = new String[this.totalCells];
		Map<String,String> map;
		Row titlerow = sheet.getRow(0);
		for(int i = 0; i <this.totalCells; i++){ 
			Cell titlecell = titlerow.getCell(i);
			Titles[i] = titlecell.getStringCellValue();
		}
		//循环Excel行数,从第二行开始。标题不入库(这里从第一行开始读，也把标题读取进map中去)
		for(int r=1;r<totalRows;r++){
			Row row = sheet.getRow(r);
	        if (row == null) continue;
	        
	        map = new HashMap<String,String>();
	        //循环Excel的列
	        for(int c = 0; c <this.totalCells; c++){ 
	        	Cell cell = row.getCell(c);
	        	cell.setCellType(Cell.CELL_TYPE_STRING);
	        	if (null != cell){
	        		map.put(Titles[c], cell.getStringCellValue());
	        	}
	        }
	        resultList.add(map);
		}
		return resultList;
	}
}
