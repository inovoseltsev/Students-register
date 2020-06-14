package com.inovoseltsev.studentsregister.xlsx;

import com.inovoseltsev.studentsregister.entity.Student;
import com.inovoseltsev.studentsregister.repository.StudentRepository;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

@Component
public class XlsxCreator {

    @Autowired
    private StudentRepository studentRepository;

    @Transactional(readOnly = true)
    public File createXlsxFile() throws IOException {
        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet("Students sheet");
        List<Student> students = studentRepository.findAll();
        int startRowNumber = 1;

        createXlsxHeader(sheet);
        createBodyRows(sheet, startRowNumber, students);

        File xlsxFile = new File("students.xlsx");
        FileOutputStream fileOutputStream = new FileOutputStream(xlsxFile);
        workbook.write(fileOutputStream);
        fileOutputStream.close();
        return xlsxFile;
    }


    private void createXlsxHeader(XSSFSheet sheet) {
        Row row = sheet.createRow(0);
        Cell cell;
        XSSFWorkbook workbook = sheet.getWorkbook();
        XSSFCellStyle headerStyle = createHeaderStyle(workbook);

        cell = row.createCell(0, CellType.STRING);
        cell.setCellValue("First name");
        cell.setCellStyle(headerStyle);

        cell = row.createCell(1, CellType.STRING);
        cell.setCellValue("Last name");
        cell.setCellStyle(headerStyle);

        cell = row.createCell(2, CellType.STRING);
        cell.setCellValue("Age");
        cell.setCellStyle(headerStyle);

        cell = row.createCell(3, CellType.STRING);
        cell.setCellValue("Birthday");
        cell.setCellStyle(headerStyle);

        cell = row.createCell(4, CellType.STRING);
        cell.setCellValue("Faculty");
        cell.setCellStyle(headerStyle);

    }


    private void createBodyRows(XSSFSheet sheet, int startRowNumber,
                                List<Student> students) {
        Row row;
        Cell cell;
        int rowNumber = startRowNumber;

        CellStyle dateCellStyle = createDateCellStyle(sheet);

        for (Student student : students) {
            row = sheet.createRow(rowNumber);

            cell = row.createCell(0, CellType.STRING);
            cell.setCellValue(student.getFirstName());

            cell = row.createCell(1, CellType.STRING);
            cell.setCellValue(student.getLastName());

            cell = row.createCell(2, CellType.NUMERIC);
            cell.setCellValue(student.getAge());

            cell = row.createCell(3);
            cell.setCellStyle(dateCellStyle);
            cell.setCellValue(student.getBirthday());

            cell = row.createCell(4, CellType.STRING);
            cell.setCellValue(student.getFaculty());
            rowNumber++;
        }
    }

    private XSSFCellStyle createHeaderStyle(XSSFWorkbook workbook) {
        XSSFFont font = workbook.createFont();
        font.setBold(true);
        XSSFCellStyle style = workbook.createCellStyle();
        style.setFont(font);
        return style;
    }

    private CellStyle createDateCellStyle(XSSFSheet sheet) {
        XSSFWorkbook workbook = sheet.getWorkbook();
        CellStyle dateCellStyle = workbook.createCellStyle();
        CreationHelper creationHelper = workbook.getCreationHelper();
        dateCellStyle.setDataFormat(creationHelper.createDataFormat()
                .getFormat("yyyy-MM-dd"));
        return dateCellStyle;
    }
}
