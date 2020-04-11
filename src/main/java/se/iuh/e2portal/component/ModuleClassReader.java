package se.iuh.e2portal.component;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.springframework.stereotype.Component;
import se.iuh.e2portal.model.Attendance;
import se.iuh.e2portal.model.ModuleClass;
import se.iuh.e2portal.model.Student;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
public class ModuleClassReader {
    private static int ROW_MODULE_CLASS = 1;
    private static int ROW_STUDENT_HEADER = 3;
    private static int ROW_STUDENT_LIST = 4;
    private static int COL_ID_MOUDLE_CLASS = 0;
    private static int COL_NAME_MOUDLE_CLASS = 1;
    private static int COL_SEMESTER_MOUDLE_CLASS =2;
    private static int COL_NUM_OF_PSESSION_MOUDLE_CLASS = 5;
    private static int COL_NUM_OF_CREDIT_MOUDLE_CLASS = 3;
    private static int COL_NUM_OF_TSESSION_MOUDLE_CLASS = 4;
    private static int COL_ATTANDANCE_RESULT = 5;
    private static int COL_ID_STUDENT = 1;
    private static String ALLOWED = "P";
    private static String NOT_ALLOWED = "K";

    public ModuleClass getModuleClass(Sheet sheet){
        Row row = sheet.getRow(ROW_MODULE_CLASS);
        ModuleClass moduleClass = new ModuleClass();
        String moduleClassId = getCellValue(row.getCell(COL_ID_MOUDLE_CLASS));
        String moduleClassName = getCellValue(row.getCell(COL_NAME_MOUDLE_CLASS));
        String semester = getCellValue(row.getCell(COL_SEMESTER_MOUDLE_CLASS));
        int numOfCredit = Integer.parseInt(getCellValue(row.getCell(COL_NUM_OF_CREDIT_MOUDLE_CLASS)));
        int numOfTSession = Integer.parseInt(getCellValue(row.getCell(COL_NUM_OF_TSESSION_MOUDLE_CLASS)));
        int numOfPSession = Integer.parseInt(getCellValue(row.getCell(COL_NUM_OF_PSESSION_MOUDLE_CLASS)));
        moduleClass.setModuleClassId(moduleClassId);
        moduleClass.setModuleClassName(moduleClassName);
        moduleClass.setSemester(semester);
        moduleClass.setNumOfCredit(numOfCredit);
        moduleClass.setNumOfTSession(numOfTSession);
        moduleClass.setNumOfPSession(numOfPSession);
        return moduleClass;
    }
    private List<Attendance> getListAttandance(Sheet sheet){
        List<Cell> listDate = getListDateCell(sheet.getRow(ROW_STUDENT_HEADER));
        List<Attendance> attendanceList = new ArrayList<>();
        ModuleClass moduleClass = getModuleClass(sheet);
        if(moduleClass.getModuleClassId() == null || moduleClass.getModuleClassId().isEmpty()) return attendanceList;
        if(listDate.isEmpty()) return null;
        for (Cell cell : listDate){
            for (Row currentRow : sheet) {
                if(currentRow.getRowNum()<ROW_STUDENT_HEADER){
                    continue;
                }
                Attendance attendance = getAttandance(currentRow,cell.getColumnIndex());
                if(attendance == null) continue;
                Date date = getDate(cell);
                if(date == null) continue;
                attendance.setDateOff(date);
                attendance.setModuleClass(moduleClass);
            }
        }
        return attendanceList;
    }
    private Attendance getAttandance(Row row,int columnDate){
        Attendance attendance = null;
        Cell cell = row.getCell(columnDate);
        String result = getCellValue(cell);
        if(!result.isEmpty()){
            attendance = new Attendance();
            Student student = new Student();
            Cell cellId = row.getCell(COL_ID_STUDENT);
            String colId = getCellValue(cellId);
            student.setId(colId);
            attendance.setStudent(student);
            if(result.equalsIgnoreCase(ALLOWED)) attendance.setAllowed(true);
            else attendance.setAllowed(false);
        }
        return attendance;
    }
    private List<Cell> getListDateCell(Row row){
        List<Cell> list = new ArrayList<>();
        for (Cell cell : row) {
            if(cell.getColumnIndex()<COL_ATTANDANCE_RESULT){
                continue;
            }
            Date date = getDate(cell);
            if(date == null) continue;
            list.add(cell);
        }
        return list;
    }
    private Date getDate(Cell cell){
        cell.setCellType(CellType.STRING);
        String sdate = cell.getStringCellValue();
        Date date = null;
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/YYYY");
        try{
           date = format.parse(sdate);
        }catch (Exception e){
        }
        return date;
    }
    public List<String> getListStudentId(Sheet sheet){
        List<String> list = new ArrayList<>();
        for (Row row : sheet) {
            if(row.getRowNum()<ROW_STUDENT_LIST){
                continue;
            }
            list.add(getStudentId(row));
        }
        return list;
    }
    private String getStudentId(Row row){
        Cell cell = row.getCell(COL_ID_STUDENT);
        return getCellValue(cell);
    }
    private String getCellValue(Cell cell) {
        CellType cellType = cell.getCellTypeEnum();
        cell.setCellType(CellType.STRING);
        String cellValue = "";
        cellValue = cell.getStringCellValue();
        return cellValue;
    }
}
