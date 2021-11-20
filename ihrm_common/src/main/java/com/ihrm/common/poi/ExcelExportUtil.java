package com.ihrm.common.poi;

import com.ihrm.domain.poi.ExcelAttribute;
import lombok.Getter;
import lombok.Setter;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.net.URLEncoder;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author HK意境
 *  报表导出工具类：通过反射方式动态获取属性，映射报表列值
 *
 *
 */
@Getter
@Setter
public class ExcelExportUtil<T> {

    // 写入数据的起始行
    private int rowIndex;
    // 需要提取的样式的所在行号
    private int styleIndex;
    // 模板报表所在位置
    private String templatePath;
    // 对象的字节码
    private Class clazz;
    // 对象中的所有属性
    private  Field fields[];

    public ExcelExportUtil(Class clazz,int rowIndex,int styleIndex) {
        this.clazz = clazz;
        this.rowIndex = rowIndex;
        this.styleIndex = styleIndex;
        fields = clazz.getDeclaredFields();
    }

    /**
     * 基于注解导出
     */
    public void export(HttpServletResponse response,InputStream is, List<T> objs,String fileName) throws Exception {

        XSSFWorkbook workbook = new XSSFWorkbook(is);
        Sheet sheet = workbook.getSheetAt(0);

        CellStyle[] styles = getTemplateStyles(sheet.getRow(styleIndex));

        AtomicInteger datasAi = new AtomicInteger(rowIndex);
        for (T t : objs) {
            Row row = sheet.createRow(datasAi.getAndIncrement());
            for(int i=0;i<styles.length;i++) {
                Cell cell = row.createCell(i);
                cell.setCellStyle(styles[i]);
                for (Field field : fields) {
                    if(field.isAnnotationPresent(ExcelAttribute.class)){
                        field.setAccessible(true);
                        ExcelAttribute ea = field.getAnnotation(ExcelAttribute.class);
                        if(i == ea.sort()) {
                            cell.setCellValue(field.get(t).toString());
                        }
                    }
                }
            }
        }
        fileName = URLEncoder.encode(fileName, "UTF-8");
        response.setContentType("application/octet-stream");
        response.setHeader("content-disposition", "attachment;filename=" + new String(fileName.getBytes("ISO8859-1")));
        response.setHeader("filename", fileName);
        workbook.write(response.getOutputStream());
    }

    public CellStyle[] getTemplateStyles(Row row) {
        CellStyle [] styles = new CellStyle[row.getLastCellNum()];
        for(int i=0;i<row.getLastCellNum();i++) {
            styles[i] = row.getCell(i).getCellStyle();
        }
        return styles;
    }
}
