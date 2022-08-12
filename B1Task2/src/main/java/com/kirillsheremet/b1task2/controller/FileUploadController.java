package com.kirillsheremet.b1task2.controller;

import javax.transaction.Transactional;
import com.kirillsheremet.b1task2.entity.*;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.util.Iterator;


@RestController
@Transactional
public class FileUploadController {

    @Autowired
    private SessionFactory factory;

@PostMapping("/uploadFile")
    public @ResponseBody
    String handleFileUpload(@RequestParam("file") MultipartFile file) {
       System.setProperty("file.encoding", "UTF-8");
        if (!file.isEmpty()) {

            try {

                byte[] fileBytes = file.getBytes();
                String rootPath = System.getProperty("catalina.home");
                System.out.println("Server rootPath: " + rootPath);
                System.out.println("File original name: " + file.getOriginalFilename());
                System.out.println("File content type: " + file.getContentType());

                File newFile = new File(rootPath + File.separator + file.getOriginalFilename());
                BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(newFile));
                stream.write(fileBytes);
                stream.close();

                System.out.println("File is saved under: " + rootPath + File.separator + file.getOriginalFilename());
                return "File is saved under: " + rootPath + File.separator + file.getOriginalFilename();

            } catch (Exception e) {
                e.printStackTrace();
                return "File upload is failed: " + e.getMessage();
            }
        } else {
            return "File upload is failed: File is empty";
        }

    }


    @RequestMapping(value = "/excel", method = RequestMethod.POST)
    public void saveAccounts() throws IOException {
        FileInputStream file = new FileInputStream("D:\\Task2\\ОСВ для тренинга.xls");
        HSSFWorkbook workbook = new HSSFWorkbook(file);
        int classId = 1;
        int accountId;



        Session session = factory.getCurrentSession();


        HSSFSheet sheet = workbook.getSheetAt(0);
        Iterator<Row> rowIterator = sheet.iterator();
        ClassesEntity c = new ClassesEntity();
        c.setIdclasses(classId);

        session.save(c);

        while (rowIterator.hasNext()) {
            Row row = rowIterator.next();
            Iterator<Cell> cellIterator = row.cellIterator();

            while (cellIterator.hasNext()) {
                Cell cell = cellIterator.next();
                ClassesEntity classes = new ClassesEntity();
                classes.setIdclasses(classId);

                if (cell.getCellType().equals(CellType.STRING) && cell.getStringCellValue().equals("ПО КЛАССУ")) {
                    classId++;
                    classes.setIdclasses(classId);
                    session.save(classes);
                }

                if (cell.getColumnIndex() == 0 && cell.getCellType().equals(CellType.NUMERIC)) {
                    AccountsEntity accounts = new AccountsEntity();
                    accountId = (int) cell.getNumericCellValue();
                    accounts.setIdaccounts(accountId);

                    session.save(accounts);

                    OpeningBalanceEntity openingBalanceEntity = new OpeningBalanceEntity();
                    openingBalanceEntity.setClassesIdclasses(classId);
                    openingBalanceEntity.setAccountsIdaccounts(accountId);
                    openingBalanceEntity.setAssets(BigDecimal.valueOf(cellIterator.next().getNumericCellValue()));
                    openingBalanceEntity.setLiability(BigDecimal.valueOf(cellIterator.next().getNumericCellValue()));


                    session.save(openingBalanceEntity);

                    MoneyTurnoverEntity moneyTurnoverEntity = new MoneyTurnoverEntity();
                    moneyTurnoverEntity.setAccountsIdaccounts(accountId);
                    moneyTurnoverEntity.setClassesIdclasses(classId);
                    moneyTurnoverEntity.setDebit(BigDecimal.valueOf(cellIterator.next().getNumericCellValue()));
                    moneyTurnoverEntity.setCredit(BigDecimal.valueOf(cellIterator.next().getNumericCellValue()));


                    session.save(moneyTurnoverEntity);

                    ClosingBalanceEntity closingBalanceEntity = new ClosingBalanceEntity();
                    closingBalanceEntity.setAccountsIdaccounts(accountId);
                    closingBalanceEntity.setClassesIdclasses(classId);
                    closingBalanceEntity.setAssets(BigDecimal.valueOf(cellIterator.next().getNumericCellValue()));
                    closingBalanceEntity.setLiability(BigDecimal.valueOf(cellIterator.next().getNumericCellValue()));

                    session.save(closingBalanceEntity);

                }

            }
        }



    }

    @RequestMapping(value = "/showExcel", method = RequestMethod.GET)
    public void showExcel(HttpServletResponse res, HttpServletRequest req) throws IOException {

        FileInputStream file = new FileInputStream(new File("D:\\Task2\\ОСВ для тренинга.xls"));
        HSSFWorkbook workbook = new HSSFWorkbook(file);
        HSSFSheet sheet = workbook.getSheetAt(0);
        Iterator<Row> rowIterator = sheet.iterator();

        res.setContentType("text/html; charset=UTF-8");
        PrintWriter out = new PrintWriter(
                new OutputStreamWriter(res.getOutputStream(), StandardCharsets.UTF_8));
        while (rowIterator.hasNext()) {
            Row row = rowIterator.next();
            Iterator<Cell> cellIterator = row.cellIterator();

            while (cellIterator.hasNext()) {
                Cell cell = cellIterator.next();

                out.write(String.valueOf(cell));
                out.write("\u00A0" + "\u00A0" );
            }
            out.print("<br>");
        }
        out.close();

    }

}
