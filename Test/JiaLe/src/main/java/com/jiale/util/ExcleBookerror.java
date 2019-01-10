package com.jiale.util;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.jiale.service.ServiceJ;

import jxl.Sheet;
import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;

@Component
public class ExcleBookerror {

	@Autowired
	ServiceJ ser;

	/**
	 * 针对Book类进行导出的操作
	 * 
	 * @param list
	 */
	public void excleOut(List<Map> list) {
		WritableWorkbook book = null;
		try {
			// 创建一个excle对象
			book = Workbook.createWorkbook(new File("G:\\file\\cbd.xls"));
			// 通过excle对象创建一个选项卡对象
			WritableSheet sheet = book.createSheet("sheet1", 0);
			// 创建一个单元格对象 列 行 值
			// Label label = new Label(0, 2, "test");
			int j = 0;
			Set keySet1 = list.get(0).keySet();
			for (Object str : keySet1) {
				System.out.println(str);
				sheet.addCell(new Label(j, 0, (String) str));
				j++;
			}
			for (int i = 1; i < list.size(); i++) {
				j = 0;
				for (Object str : keySet1) {
					System.out.println(list.get(i).get(str));
					String s;
					try {
						s = list.get(i).get(str).toString();
					} catch (Exception e) {
						// TODO: handle exception
						s = null;
					}
					sheet.addCell(new Label(j, i, s));
					j++;
				}
				/*
				 * Book book2 = list.get(i); Label label1 = new Label(0, i,
				 * String.valueOf(book2.getId())); Label label2 = new Label(1, i,
				 * book2.getName()); Label label3 = new Label(2, i, book2.getAuthor());
				 */
				// 将创建好的单元格对象放入选项卡中
				/*
				 * sheet.addCell(label1); sheet.addCell(label2); sheet.addCell(label3);
				 */
			}
			// 写入目标路径
			book.write();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				book.close();
			} catch (WriteException | IOException e) {
				e.printStackTrace();
			}
		}

	}

	/**
	 * 针对Book类进行导入的操作
	 * 
	 * @return
	 */
	public List<Map> excleIn() {
		List<Map> list = new ArrayList<>();
		Workbook workbook = null;

		try {
			// 获取Ecle对象
			workbook = Workbook.getWorkbook(new File("G:\\file\\cbd.xls"));
			// 获取选项卡对象 第0个选项卡
			Sheet sheet = workbook.getSheet(0);
			// 循环选项卡中的值
			List LinkedList = new LinkedList<>();
			int columns = sheet.getColumns();
			for (int i = 0; i < columns; i++) {
				LinkedList.add(i, sheet.getCell(i, 0).getContents());
			}
			for (int i = 1; i < sheet.getRows(); i++) {
				Map map = new LinkedHashMap<>();
				for (int j = 0; j < columns; j++) {
					// 获取单元格对象
					// 取得单元格的值,并设置到对象中 j列 i行
					String contents;
					try {
						contents = sheet.getCell(j, i).getContents();
					} catch (Exception e) {
						// TODO: handle exception
						contents = null;
					}

					map.put(LinkedList.get(j), contents);

				}
				System.out.println(map);
				System.out.println(ser);
				ser.exclein(map);
				list.add(map);

			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			workbook.close();
		}
		System.out.println(list);

		return list;
	}

	/*
	 * public static void main(String[] args) { ExcleBook book = new ExcleBook();
	 * List<Book> list = new ArrayList<>(); Book book2 = new Book(); book2.setId(1);
	 * book2.setName("书本名1"); book2.setAuthor("张三"); Book book3 = new Book();
	 * book3.setId(2); book3.setName("书本名2"); book3.setAuthor("李四");
	 * list.add(book2); list.add(book3); book.excleOut(list); List<Book> books =
	 * book.excleIn(); for (Book bo : books) { System.out.println(bo.getId() + " " +
	 * bo.getName() + " " + bo.getAuthor()); }
	 * 
	 * }
	 */

}
