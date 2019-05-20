package com.vote.utils.fileupload;
/**
* @author LTT-AH-ZY
* @version 创建时间：2019年5月19日 下午6:44:10
* @version 1.0
* @类说明
*/
public class WDWUtil {
	// @描述：是否是2003的excel，返回true是2003
    public static boolean isExcel2003(String filePath)  {
        return filePath.matches("^.+\\.(?i)(xls)$");
    }

    //@描述：是否是2007的excel，返回true是2007
    public static boolean isExcel2007(String filePath)  {
        return filePath.matches("^.+\\.(?i)(xlsx)$");
    }
}
