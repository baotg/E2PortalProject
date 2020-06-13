package se.iuh.e2portal.component;

import org.apache.poi.ss.usermodel.*;
import org.springframework.stereotype.Component;
import se.iuh.e2portal.model.*;
import java.util.*;

@Component
public class TimeTableReader {
	
	private static String CODE = "TTB";
	private static int ROW_CODE = 2;
	private static int COL_CODE = 0;
	private static int ROW_MODULE_CLASS = 1;
	private static int COL_MODULE_CLASS_ID = 0;
	private static int COL_MODULE_CLASS_NAME = 1;
	private static int COL_NUM_OF_CREDIT = 2;
	private static int COL_NUM_OF_PRACTICE_SESSION = 3;
	private static int COL_NUM_OF_THEORY_SESSION = 4;
	private static int COL_START_DATE = 5;
	private static int COL_END_DATE = 6;
	private static int COL_SEMESTER = 7;
	private static int COL_LECTURER_ID = 8;
	private static int COL_LECTURER_NAME = 9;
	private static int COL_FACULTY_ID = 10;
	private static int ROW_TIME_TABLE = 4;
	private static int COL_DAY_OF_WEEK = 0;
	private static int COL_PERIOD = 1;
	private static int COL_CLASS_ROOM = 2;
	private static int COL_START = 3;
	private static int COL_END = 4;

	public boolean validdateFile(Sheet sheet) {
   	 Row row = sheet.getRow(ROW_CODE);
   	 String code =null;
   	 try {
			 code = getCellValue(row.getCell(COL_CODE));
		} catch (Exception e) {
			return false;
		}
   	return code.equalsIgnoreCase(CODE)?true:false;
   }
	
	public ModuleClass getModuleClass(Sheet sheet){
		Row row = sheet.getRow(ROW_MODULE_CLASS);
		ModuleClass moduleClass = new ModuleClass();
		Lecturer lecturer = new Lecturer();
		moduleClass.setModuleClassId(getCellValue(row.getCell(COL_MODULE_CLASS_ID)));
		moduleClass.setModuleClassName(getCellValue(row.getCell(COL_MODULE_CLASS_NAME)));
		moduleClass.setNumOfTSession(Integer.parseInt(getCellValue(row.getCell(COL_NUM_OF_THEORY_SESSION))));
		moduleClass.setNumOfPSession(Integer.parseInt(getCellValue(row.getCell(COL_NUM_OF_PRACTICE_SESSION))));
		moduleClass.setNumOfCredit(Integer.parseInt(getCellValue(row.getCell(COL_NUM_OF_CREDIT))));
		moduleClass.setSemester(getCellValue(row.getCell(COL_SEMESTER)));
		if(DateUtil.isCellDateFormatted(row.getCell(COL_START_DATE)))
			moduleClass.setStartDate(row.getCell(COL_START_DATE).getDateCellValue());
		if(DateUtil.isCellDateFormatted(row.getCell(COL_END_DATE)))
			moduleClass.setEndDate(row.getCell(COL_END_DATE).getDateCellValue());
		lecturer.setId(getCellValue(row.getCell(COL_LECTURER_ID)));
		String fullName = getCellValue(row.getCell(COL_LECTURER_NAME));
		List<String> fullNames = Arrays.asList(fullName.split(" "));
		lecturer.setFirstName(fullNames.get(fullNames.size()-1));
		lecturer.setLastName(fullName.replace(fullNames.get(fullNames.size()-1),"").trim());
		moduleClass.setLecturer(lecturer);
		Faculty faculty = new Faculty();
		faculty.setFacultyId(getCellValue(row.getCell(COL_FACULTY_ID)));
		moduleClass.setFaculty(faculty);
		return moduleClass;
	}
	
	public List<TimeTable> getListTimeTable(Sheet sheet, ModuleClass moduleClass){
		List<TimeTable> timeTableList = new ArrayList<>();
		for (Row currentRow : sheet) {
			if (currentRow.getRowNum() < ROW_TIME_TABLE) {
				continue;
			}
			TimeTable timeTable = getTimeTable(currentRow);
			timeTable.setModuleClass(moduleClass);
			if(!timeTable.getClassRoom().isEmpty())
			timeTableList.add(timeTable);
		}
		return timeTableList;
	}
	
	private TimeTable getTimeTable(Row row){
		TimeTable timeTable = new TimeTable();
		timeTable.setClassRoom(getCellValue(row.getCell(COL_CLASS_ROOM)));
		if(DateUtil.isCellDateFormatted(row.getCell(COL_START)))
			timeTable.setStartDate(row.getCell(COL_START).getDateCellValue());
		if(DateUtil.isCellDateFormatted(row.getCell(COL_END)))
			timeTable.setEndDate(row.getCell(COL_END).getDateCellValue());
		timeTable.setPeriod(getCellValue(row.getCell(COL_PERIOD)));
		timeTable.setDayOfWeek(getCellValue(row.getCell(COL_DAY_OF_WEEK)));
		return timeTable;
	}
	
	private String getCellValue(Cell cell) {
		
		cell.setCellType(CellType.STRING);
		String cellValue = "";
		cellValue = cell.getStringCellValue();
		return cellValue;
	}
}
