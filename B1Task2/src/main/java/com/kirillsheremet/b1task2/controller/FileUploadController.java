package com.kirillsheremet.b1task2.controller;

import javax.transaction.Transactional;

import com.kirillsheremet.b1task2.dao.ExcelDaoImpl;
import com.kirillsheremet.b1task2.entity.*;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.util.*;


@Controller
@Transactional
public class FileUploadController {

    @Autowired
    private ExcelDaoImpl excelDao;

    /**
     * Метод для загрузки Excel файлов с клиента на сервер
     */
    @RequestMapping(value = "/uploadFile", method = RequestMethod.POST)
//     Передаваемую строку будем сериализовать в JSON и возвращать HTTP Response.
//     В качестве параметра передаем MultipartFile из формы.
    public @ResponseBody
    String handleFileUpload(@RequestParam("file") MultipartFile file) {
        if (!file.isEmpty()) {
            try {

                byte[] fileBytes = file.getBytes();
                String rootPath = System.getProperty("catalina.home");
                // Побайтово переписываем файл
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

    /**
     * Метод для загрузки Excel файлов определенного типа в базу данных
     */
    @RequestMapping(value = "/excel", method = RequestMethod.POST)
    public void saveAccounts() throws IOException {
        // Находим нужный файл и создаем workbook- сущность Excel страницы из Apache POI
        FileInputStream file = new FileInputStream("D:\\Java\\ОСВ для тренинга.xls");
        HSSFWorkbook workbook = new HSSFWorkbook(file);
//
        int classId = 1;
        int accountId;

        //  HSSFSheet - лист в excel документе. Берем первый лист
        HSSFSheet sheet = workbook.getSheetAt(0);
        Iterator<Row> rowIterator = sheet.iterator();
        ClassesEntity c = new ClassesEntity();
        c.setIdclasses(classId);
        // Запишем в базу данных первый класс
        excelDao.save(c);
        // Два вложенных цикла while иттерация идет по строкам -> далее по ячейкам строки
        while (rowIterator.hasNext()) {
            Row row = rowIterator.next();
            Iterator<Cell> cellIterator = row.cellIterator();

            while (cellIterator.hasNext()) {
                Cell cell = cellIterator.next();
                ClassesEntity classes = new ClassesEntity();
                classes.setIdclasses(classId);
                // Если нашли такую ячейку записываем новый класс
                if (cell.getCellType().equals(CellType.STRING) && cell.getStringCellValue().equals("ПО КЛАССУ")) {
                    classId++;
                    classes.setIdclasses(classId);
                    excelDao.save(classes);
                }
                // Парсим строку и записываем в нужные таблицы базы данных
                if (cell.getColumnIndex() == 0 && cell.getCellType().equals(CellType.NUMERIC)) {
                    AccountsEntity accounts = new AccountsEntity();
                    accountId = (int) cell.getNumericCellValue();
                    accounts.setIdaccounts(accountId);
                    excelDao.save(accounts);

                    OpeningBalanceEntity openingBalanceEntity = new OpeningBalanceEntity();
                    openingBalanceEntity.setClassesIdclasses(classId);
                    openingBalanceEntity.setAccountsIdaccounts(accountId);
                    openingBalanceEntity.setAssets(BigDecimal.valueOf(cellIterator.next().getNumericCellValue()));
                    openingBalanceEntity.setLiability(BigDecimal.valueOf(cellIterator.next().getNumericCellValue()));
                    excelDao.save(openingBalanceEntity);

                    MoneyTurnoverEntity moneyTurnoverEntity = new MoneyTurnoverEntity();
                    moneyTurnoverEntity.setAccountsIdaccounts(accountId);
                    moneyTurnoverEntity.setClassesIdclasses(classId);
                    moneyTurnoverEntity.setDebit(BigDecimal.valueOf(cellIterator.next().getNumericCellValue()));
                    moneyTurnoverEntity.setCredit(BigDecimal.valueOf(cellIterator.next().getNumericCellValue()));
                    excelDao.save(moneyTurnoverEntity);

                    ClosingBalanceEntity closingBalanceEntity = new ClosingBalanceEntity();
                    closingBalanceEntity.setAccountsIdaccounts(accountId);
                    closingBalanceEntity.setClassesIdclasses(classId);
                    closingBalanceEntity.setAssets(BigDecimal.valueOf(cellIterator.next().getNumericCellValue()));
                    closingBalanceEntity.setLiability(BigDecimal.valueOf(cellIterator.next().getNumericCellValue()));
                    excelDao.save(closingBalanceEntity);

                }

            }
        }


    }

    /**
     * Метод для демонстрации Excel файлов на web странице
     */
    @RequestMapping(value = "/showExcel", method = RequestMethod.GET)
    public String showExcel(Model model) throws IOException {

        FileInputStream file = new FileInputStream(new File("D:\\Java\\ОСВ для тренинга.xls"));
        HSSFWorkbook workbook = new HSSFWorkbook(file);
        HSSFSheet sheet = workbook.getSheetAt(0);
        Iterator<Row> rowIterator = sheet.iterator();
        Map<Integer, List<Cell>> data = new HashMap<>();
        int rowNumber = 0;
        while (rowIterator.hasNext()) {
            Row row = rowIterator.next();
            Iterator<Cell> cellIterator = row.cellIterator();
            rowNumber++;
            List<Cell> cells = new ArrayList<>();
            while (cellIterator.hasNext()) {
                cells.add(cellIterator.next());

            }
            data.put(rowNumber, cells);

        }
        model.addAttribute("data", data);
        return "showExcel";
    }


}
