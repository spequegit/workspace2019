import com.report.data.RecordType;
import com.report.data.ReportRecord;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.ss.util.CellRangeAddressList;
import org.apache.poi.xssf.usermodel.*;

import java.awt.Color;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class ApachePOIExcelWrite {

    public static final Color COLOR_TITLE = new Color(113, 214, 255);
    public static final Color COLOR_DEVELOPMENT = new Color(178, 200, 208, 255);
    public static final Color COLOR_COLUMN_1 = new Color(198, 234, 255);
    public static final Color COLOR_FONT_GREY = new Color(203, 203, 203, 255);
    public static final Color COLOR_RED = new Color(255, 116, 113);
    public static final Color COLOR_GREEN = new Color(147, 255, 135);
    public static final Color COLOR_LIGHTRED = new Color(255, 233, 230, 255);
    public static final Color COLOR_LIGHTGREEN = new Color(227, 255, 215, 255);
    private final String FILE_NAME = "JenkinsTestReport.xlsx";
    private final String DIGIT = "-?\\d+?";
    private final List<Integer> COLUMNS_WO_FORMAT = Arrays.asList(3, 6, 8, 10, 12);
    private final XSSFWorkbook workbook;


    public ApachePOIExcelWrite(List<ReportRecord> list) {
        workbook = new XSSFWorkbook();
        int rowNum = 0;
        System.out.println("creating excel...");
        Collections.sort(list);
        List<Object> titles = Arrays.asList("Team", "Branch", "Build", "Total", "Unit Tests", "Oldest", "Service Tests", "Oldest", "ITests", "Oldest", "Ignored", "Week");
        ReportRecord recordTitle = new ReportRecord(titles);
        recordTitle.setTitle(true);
        list.add(0, recordTitle);
        createSheet(list, rowNum);
        try {
            FileOutputStream outputStream = new FileOutputStream(FILE_NAME);
            workbook.write(outputStream);
            workbook.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("done");
    }

    private XSSFCellStyle createStyle(Color backgound) {
        XSSFCellStyle cellStyle = workbook.createCellStyle();
        if (backgound != null) {
            cellStyle.setFillForegroundColor(new XSSFColor(backgound));
            cellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        }
        cellStyle.setFont(createFont(null));
        return cellStyle;
    }

    private void createSheet(List<ReportRecord> records, int rowNum) {
        XSSFSheet sheet = workbook.createSheet("Test Report");
        sheet.createFreezePane(0, 1);
        CellRangeAddressList cellRangeAddressList = new CellRangeAddressList();
        cellRangeAddressList.addCellRangeAddress(CellRangeAddress.valueOf("A1"));
        sheet.setAutoFilter(CellRangeAddress.valueOf("A1:L1"));

        sheet.setColumnWidth(5,5*256);  // 5 characters
        sheet.setColumnWidth(7,5*256);
        sheet.setColumnWidth(9,5*256);
        sheet.setColumnWidth(11,5*256);

        for (ReportRecord record : records) {
            rowNum = createRow(rowNum, sheet, record);
        }
    }

    private int compareLast(ReportRecord record, int column) {
// TODO: 21.05.2019
        if (COLUMNS_WO_FORMAT.contains(column)) {
            return 0;
        }
        List<Object> objects = record.asList();
        Object o = objects.get(column - 1);
// TODO: 21.05.2019
        if (o == null) {
            return 0;
        }
        String stringValue = o.toString();
        ReportRecord reportRecordLast = record.getReportRecordLast();
// TODO: 21.05.2019
        if (reportRecordLast == null) {
            return 0;
        }
        List<Object> objectsLast = reportRecordLast.asList();
        String stringValueLast = objectsLast.get(column - 1).toString();
        if (stringValue.matches(DIGIT) && stringValueLast.matches(DIGIT)) {
            int value = Integer.parseInt(stringValue);
            int valueLast = Integer.parseInt(stringValueLast);
            return value > valueLast ? 1 : (value == valueLast ? 0 : -1);
        }
        return 0;
    }

    private int createRow(int rowNum, XSSFSheet sheet, ReportRecord record) {
        Row row = sheet.createRow(rowNum++);
        int column = 0;
        for (Object value : record.asList()) {
            Cell cell = row.createCell(column++);
            XSSFCellStyle style;
            if (rowNum == 1) {
                row.setHeightInPoints(30);
                style = createStyle(COLOR_TITLE);
                style.setAlignment(HorizontalAlignment.CENTER);
                style.setVerticalAlignment(VerticalAlignment.TOP);
                style.setBorderBottom(BorderStyle.THIN);
            } else if (record.getType() == RecordType.DEVELOPMENT && column == 1) {
                style = createStyle(COLOR_COLUMN_1);
                style.setAlignment(HorizontalAlignment.CENTER);
//                style.setBorderRight(BorderStyle.THIN);
            } else if (column == 1) {
                style = createStyle(COLOR_DEVELOPMENT);
//                style.setBorderRight(BorderStyle.THIN);
                style.setAlignment(HorizontalAlignment.CENTER);
            } else if (record.getWeek() == -1) {
//                row.setHeight((short) 200);
                style = createStyle(null);
                style.setFont(createFont(COLOR_FONT_GREY));
            } else if (compareLast(record, column) == 1) {
                style = column == 4?createStyle(COLOR_RED):createStyle(COLOR_LIGHTRED);
            } else if (compareLast(record, column) == -1) {
                style = column == 4?createStyle(COLOR_GREEN):createStyle(COLOR_LIGHTGREEN);
            } else {
                style = createStyle(null);
            }
            cell.setCellStyle(style);
            setValue(value, cell);
        }
        return rowNum;
    }

    private XSSFFont createFont(Color c) {
        XSSFFont font = workbook.createFont();
        if (c != null) {
            font.setColor(new XSSFColor(c));
        }
        font.setFontHeight(8);
        return font;
    }

    private void setValue(Object value, Cell cell) {
        if (value.toString().matches(DIGIT)) {
            cell.setCellValue(Integer.parseInt(value.toString()));
        } else {
            cell.setCellValue(value.toString());
        }
    }
}