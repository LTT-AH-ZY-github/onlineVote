package com.test.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.vote.utils.fileupload.ReadExcel;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;

/**
* @author LTT-AH-ZY
* @version 创建时间：2019年5月19日 下午11:27:58
* @version 1.0
* @类说明
*/
@Controller
@RequestMapping(value="/test/fileload")
public class Test_fileloadController {
	
	@RequestMapping(value="/index")
	@ApiOperation(value = "上传EXCEL文件", httpMethod = "POST",notes = "上传EXCEL文件，读取到数据库中")
	public String index() {
		return "test/index";
	}
	
	@RequestMapping(value = "/upload", method = RequestMethod.POST)
	@ApiOperation(value = "上传EXCEL文件", httpMethod = "POST",notes = "上传EXCEL文件，读取到数据库中")
	public String upload(@ApiParam(name = "filename", value = "文件") @RequestParam(value="filename") MultipartFile file,HttpServletRequest request,HttpServletResponse response) throws IOException{
		//判断文件是否为空
        if(file==null) return null;
        //获取文件名
        String name=file.getOriginalFilename();
        //进一步判断文件是否为空（即判断其大小是否为0或其名称是否为null）
        long size=file.getSize();
        if(name==null || ("").equals(name) && size==0) return null;
        //创建处理EXCEL
        ReadExcel readExcel=new ReadExcel();
        //解析excel，获取客户信息集合。
        List<Map<String,String>> resultList = readExcel.getExcelInfo(name ,file);
        Map<String,String> map = resultList.get(1);
        for(String key : map.keySet()){
        	String value = map.get(key);
        	System.out.println(key+"--------"+value);
        }
        //System.out.println(map.get("nickname"));
		return "test/success";
	}
}
