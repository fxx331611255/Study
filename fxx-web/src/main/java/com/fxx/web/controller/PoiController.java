package com.fxx.web.controller;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.annotation.Resource;

import com.fxx.service.BoshiService;
import com.fxx.utils.entity.Boshi;
import com.fxx.utils.vo.ResponseVo;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by FengXiaoxi on 2018/4/12.
 */
@RestController
@RequestMapping("poi")
public class PoiController {

  //要读取的excel路径
  public static final String pathPoi = "C:\\java\\poi\\123.xlsx";

  @Resource
  private BoshiService boshiService;

  /**
   * excel到mysql
   * 导入
   * 2007版
   */
  @PostMapping("importExcel")
  public ResponseVo importExcel() throws IOException {
    System.out.println(" poi.importExcel start!");
    int i = 0;
    InputStream is = new FileInputStream(pathPoi);
    Workbook workbook = new XSSFWorkbook(is);
    //获取博时
    Sheet sheet = workbook.getSheet("博时");
    //Sheet sheet1 = workbook.getSheetAt(1);
    for (int rowNum = 1; rowNum < sheet.getLastRowNum(); rowNum++) {
      //获取excel的第rowNum+1行。
      //rowNum为下标，第一行从0开始，单元格也一样
      Row row = sheet.getRow(rowNum);
      Boshi boshi = new Boshi();
      boshi.setId(row.getCell(0).getDateCellValue());
      //设置这个单元格为字符串格式
      row.getCell(1).setCellType(1);
      boshi.setA(row.getCell(1).getStringCellValue());
      row.getCell(2).setCellType(1);
      boshi.setB(row.getCell(2).getStringCellValue());
      row.getCell(3).setCellType(1);
      boshi.setC(row.getCell(3).getStringCellValue());
      boshi.setD(row.getCell(4).getStringCellValue());
      boshi.setE(row.getCell(5).getStringCellValue());
      boshiService.insertSelective(boshi);
      i++;
      System.out.println("操作第" + i + "行 success!");
    }

    System.out.println(" poi.importExcel end!");
    return null;
  }
}
