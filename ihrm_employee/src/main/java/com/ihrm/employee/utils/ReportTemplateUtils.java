package com.ihrm.employee.utils;

import com.ihrm.domain.employee.response.EmployeeReportResult;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.util.List;

/**
 * @author : HK意境
 * @ClassName : ReportTemplateUtils
 * @date : 2021/11/19 19:26
 * @description :
 * @Todo :
 * @Bug :
 * @Modified :
 * @Version : 1.0
 */
public class ReportTemplateUtils {

    public void test(){
        // 构造 Excel
        // 创建 工作簿
        XSSFWorkbook workbook = new XSSFWorkbook();

        // 构造sheet
        XSSFSheet sheet = workbook.createSheet("month" + "人事报表");

        // 创建行
        // 标题
        String[] titles = "编号,姓名,手机,最高学历,国家地区,护照号,籍贯,生日,属相,入职时间,离职类型,离职原因,离职时间".split(",");

        // 处理标题
        XSSFRow titleRow = sheet.createRow(0);
        int index = 0 ;
        for (String title : titles) {
            XSSFCell cell = titleRow.createCell(index++);
            cell.setCellValue(title);
        }

        // 创建单元格
        int rowIndex = 1 ;
        Cell cell = null ;
        List<EmployeeReportResult> list = null ;
        for (EmployeeReportResult employeeReportResult : list) {
            titleRow = sheet.createRow(rowIndex++);
            // 编号,
            cell = titleRow.createCell(0);
            cell.setCellValue(employeeReportResult.getUserId());
            // 姓名,
            cell = titleRow.createCell(1);
            cell.setCellValue(employeeReportResult.getUsername());
            // 手机,
            cell = titleRow.createCell(2);
            cell.setCellValue(employeeReportResult.getMobile());
            // 最高学历,
            cell = titleRow.createCell(3);
            cell.setCellValue(employeeReportResult.getTheHighestDegreeOfEducation());
            // 国家地区,
            cell = titleRow.createCell(4);
            cell.setCellValue(employeeReportResult.getNationalArea());
            // 护照号,
            cell = titleRow.createCell(5);
            cell.setCellValue(employeeReportResult.getPassportNo());
            // 籍贯,
            cell = titleRow.createCell(6);
            cell.setCellValue(employeeReportResult.getNativePlace());
            // 生日,
            cell = titleRow.createCell(7);
            cell.setCellValue(employeeReportResult.getBirthday());
            // 属相,
            cell = titleRow.createCell(8);
            cell.setCellValue(employeeReportResult.getZodiac());
            // 入职时间,
            cell = titleRow.createCell(9);
            cell.setCellValue(employeeReportResult.getTimeOfEntry());
            // 离职类型,
            cell = titleRow.createCell(10);
            cell.setCellValue(employeeReportResult.getTypeOfTurnover());
            // 离职原因,
            cell = titleRow.createCell(11);
            cell.setCellValue(employeeReportResult.getReasonsForLeaving());
            // 离职时间
            cell = titleRow.createCell(12);
            cell.setCellValue(employeeReportResult.getResignationTime());
        }
    }


}
