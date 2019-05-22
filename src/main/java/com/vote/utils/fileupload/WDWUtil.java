package com.vote.utils.fileupload;
/**
 * 
 * @ClassName:WDWUtil
 * @Description:TODO 用来判断Excel使用的版本
 * @author LTT-AH-ZY https://github.com/LTT-AH-ZY-github
 * @date 2019年5月22日
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
