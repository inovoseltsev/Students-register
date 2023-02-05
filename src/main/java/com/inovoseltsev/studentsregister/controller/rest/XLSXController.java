package com.inovoseltsev.studentsregister.controller.rest;

import com.inovoseltsev.studentsregister.xlsx.XlsxCreator;
import org.apache.poi.util.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

@RestController
public class XLSXController {
    @Autowired
    private XlsxCreator xlsxCreator;

    @GetMapping("/xlsx")
    public void createXlsFile(HttpServletResponse response) throws IOException {
        InputStream inputStream = new FileInputStream(xlsxCreator.createXlsxFile());
        try {
            IOUtils.copy(inputStream, response.getOutputStream());
            response.setContentType("application/xlslx");
            response.setHeader("Content-Disposition", "attachment; " + "filename=\"students.xlsx\"");
        } catch (IOException e) {
            throw new RuntimeException("IOError writing file to output stream");
        }
    }
}
