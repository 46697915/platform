package com.wxsoft.util;

import jxl.Workbook;
import jxl.format.Border;
import jxl.format.BorderLineStyle;
import jxl.write.*;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.List;

public class ExcelUtil {
	
	public static void exportExcel(HttpServletResponse response,String title, String[] headers,List<LinkedHashMap<String, String>> listMap) {
		try {
			ServletOutputStream os = response.getOutputStream();
			response.reset();
			response.setContentType("application/vnd.ms-excel");
			//windows 
			response.setHeader("Content-disposition","attachment; filename=" + new String((title+".xls").getBytes(),"iso-8859-1"));
			//linux 不起作用
//			response.setHeader("Content-disposition","attachment; filename=" + new String((title+".xls").getBytes("UTF-8"),"iso-8859-1"));
			WritableWorkbook workbook = Workbook.createWorkbook(os);
			
			WritableSheet sheet = workbook.createSheet(title, 0);
			Label cell = null;
			WritableFont font1 = new WritableFont(WritableFont.COURIER, 10,WritableFont.BOLD);
			WritableCellFormat format1 = new WritableCellFormat(font1);
			format1.setAlignment(jxl.format.Alignment.CENTRE);
			format1.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);
			format1.setBorder(Border.ALL, BorderLineStyle.THIN);
			format1.setWrap(true);
			
			WritableFont font = new WritableFont(WritableFont.COURIER, 10,WritableFont.NO_BOLD);
			WritableCellFormat format = new WritableCellFormat(font);
			format.setAlignment(jxl.format.Alignment.CENTRE);
			format.setVerticalAlignment((jxl.format.VerticalAlignment.CENTRE));
			format.setBorder(Border.ALL, BorderLineStyle.THIN);      
			format.setWrap(true);
			sheet.getSettings().setDefaultColumnWidth(20);
			for(int i=0;i<headers.length;i++){
				cell = new Label(i, 0, headers[i], format1);
				sheet.addCell(cell);
			}
			for(int i=0;i<listMap.size();i++){
				LinkedHashMap<String, String> objMap = listMap.get(i);
				for(int j=0;j<headers.length;j++){
					cell = new Label(j, i+1, objMap.containsKey(headers[j])?objMap.get(headers[j]):"~~", format1);
					sheet.addCell(cell);
					
				}
			}
			workbook.write();
			workbook.close();
			os.close();
		} catch (WriteException e) {
			e.printStackTrace();
		}catch (IOException e) {
			e.printStackTrace();
		}
	}

}
