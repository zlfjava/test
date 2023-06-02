package com.dsz.util;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.stereotype.Component;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

@Component
public class ExportBeanExcel<T> {
	public byte[] exportExcel(String title, List<String> headersName, List<String> headersId, List<T> dtoList) {
		/* （一）表头--标题栏 */
		Map<Integer, String> headersNameMap = new HashMap<>();
		int key = 0;
		for (int i = 0; i < headersName.size(); i++) {
			if (!headersName.get(i).equals(null)) {
				headersNameMap.put(key, headersName.get(i));
				key++;
			}
		}
		/* （二）字段 */
		Map<Integer, String> titleFieldMap = new HashMap<>();
		int value = 0;
		for (int i = 0; i < headersId.size(); i++) {
			if (!headersId.get(i).equals(null)) {
				titleFieldMap.put(value, headersId.get(i));
				value++;
			}
		}
		/* （三）声明一个工作薄：包括构建工作簿、表格、样式 */
		@SuppressWarnings("resource")
		HSSFWorkbook wb = new HSSFWorkbook();
		HSSFSheet sheet = wb.createSheet(title);
		sheet.setDefaultColumnWidth((short) 15);
		// 生成一个样式
		HSSFCellStyle style = wb.createCellStyle();
		HSSFRow row = sheet.createRow(0);
		// style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		HSSFCell cell;
		Collection<String> c = headersNameMap.values();// 拿到表格所有标题的value的集合

		Iterator<String> it = c.iterator();// 表格标题的迭代器
		/* （四）导出数据：包括导出标题栏以及内容栏 */
		//根据选择的字段生成表头
		short size = 0;
		while (it.hasNext()) {
			cell = row.createCell(size);
			cell.setCellValue(it.next().toString());
			cell.setCellStyle(style);
			size++;
		}
		//表格标题一行的字段的集合
		@SuppressWarnings("rawtypes")
		Collection zdC = titleFieldMap.values();
		Iterator<T> labIt = dtoList.iterator();// 总记录的迭代器
		int zdRow = 0;// 列序号
		while (labIt.hasNext()) {// 记录的迭代器，遍历总记录
			int zdCell = 0;
			zdRow++;
			row = sheet.createRow(zdRow);
			T l = (T) labIt.next();
			// 利用反射，根据javabean属性的先后顺序，动态调用getXxx()方法得到属性值
			Field[] fields = l.getClass().getDeclaredFields();// 获得JavaBean全部属性
			for (short i = 0; i < fields.length; i++) {// 遍历属性，比对
				Field field = fields[i];
				String fieldName = field.getName();// 属性名
				@SuppressWarnings("unchecked")
				Iterator<String> zdIt = zdC.iterator();// 一条字段的集合的迭代器
				while (zdIt.hasNext()) {// 遍历要导出的字段集合
					if (zdIt.next().equals(fieldName)) {// 比对JavaBean的属性名，一致就写入，不一致就丢弃
						String getMethodName = "get" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);// 拿到属性的get方法
						@SuppressWarnings("rawtypes")
						Class tCls = l.getClass();// 拿到JavaBean对象
						try {
							@SuppressWarnings("unchecked")
							Method getMethod = tCls.getMethod(getMethodName, new Class[] {});// 通过JavaBean对象拿到该属性的get方法，从而进行操控
							Object val = getMethod.invoke(l, new Object[] {});// 操控该对象属性的get方法，从而拿到属性值
							String textVal = null;
							if (val != null) {
								textVal = String.valueOf(val);// 转化成String
							} else {
								textVal = null;
							}
							row.createCell((short) zdCell).setCellValue(textVal);// 写进excel对象
							zdCell++;
						} catch (SecurityException e) {
							e.printStackTrace();
						} catch (IllegalArgumentException e) {
							e.printStackTrace();
						} catch (NoSuchMethodException e) {
							e.printStackTrace();
						} catch (IllegalAccessException e) {
							e.printStackTrace();
						} catch (InvocationTargetException e) {
							e.printStackTrace();
						}
					}
				}
			}
		}
		return wb.getBytes();
	}

	/**
	 * Excel读取 操作
	 */
	public static List<List<String>> readExcel(InputStream is) throws IOException {
		Workbook wb = null;
		try {
			wb = WorkbookFactory.create(is);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (InvalidFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		/** 得到第一个sheet */
		Sheet sheet = wb.getSheetAt(0);
		/** 得到Excel的行数 */
		int totalRows = sheet.getPhysicalNumberOfRows();

		/** 得到Excel的列数 */
		int totalCells = 0;
		if (totalRows >= 1 && sheet.getRow(0) != null) {
			totalCells = sheet.getRow(0).getPhysicalNumberOfCells();
		}

		List<List<String>> dataLst = new ArrayList<List<String>>();
		/** 循环Excel的行 */
		for (int r = 0; r < totalRows; r++) {
			Row row = sheet.getRow(r);
			if (row == null)
				continue;
			List<String> rowLst = new ArrayList<String>();
			/** 循环Excel的列 */
			for (int c = 0; c < totalCells; c++) {
				Cell cell = row.getCell(c);
				String cellValue = "";
				if (null != cell) {
					/*
					 * HSSFDataFormatter hSSFDataFormatter = new HSSFDataFormatter(); cellValue=
					 * hSSFDataFormatter.formatCellValue(cell);
					 */
					// 以下是判断数据的类型
					CellType type = cell.getCellTypeEnum();

					switch (type) {
						case NUMERIC: // 数字
							cellValue = cell.getNumericCellValue() + "";
							break;
						case STRING: // 字符串
							cellValue = cell.getStringCellValue();
							break;
						case BOOLEAN: // Boolean
							cellValue = cell.getBooleanCellValue() + "";
							break;
						case FORMULA: // 公式
							try {
								cellValue = cell.getStringCellValue();
							} catch (IllegalStateException e) {
								cellValue = String.valueOf(cell.getNumericCellValue());
							}
							break;
						/*
						 * cellValue = cell.getCellFormula() + ""; break;
						 */
						case BLANK: // 空值
							cellValue = "";
							break;
						case _NONE: // 故障
							cellValue = "非法字符";
							break;
						default:
							cellValue = "未知类型";
							break;
					}
				}
				rowLst.add(cellValue);
			}
			/** 保存第r行的第c列 */
			dataLst.add(rowLst);
		}
		return dataLst;
	}

}
