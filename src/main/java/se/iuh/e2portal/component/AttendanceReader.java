package se.iuh.e2portal.component;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Component;
import se.iuh.e2portal.model.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Component
public class AttendanceReader {

	private static String CODE = "ATD";
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
	private static int ROW_ATTENDANCE = 4;
	private static int ROW_DATE_OFF = 3;
	private static int COL_STUDENT_ID = 1;
	private static int COL_LAST_NAME = 2;
	private static int COL_FIRST_NAME = 3;
	private static int COL_MAIN_CLASS_ID = 4;
	private static int COL_DAY_START = 5;

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
		Date startDate = new Date();
		if(DateUtil.isCellDateFormatted(row.getCell(COL_START_DATE)))
			startDate = row.getCell(COL_START_DATE).getDateCellValue();
		moduleClass.setStartDate(startDate);
		Date endDate = new Date();
		if(DateUtil.isCellDateFormatted(row.getCell(COL_END_DATE)))
			endDate = row.getCell(COL_END_DATE).getDateCellValue();
		moduleClass.setEndDate(endDate);
		lecturer.setId(getCellValue(row.getCell(COL_LECTURER_ID)));
		String fullName = getCellValue(row.getCell(COL_LECTURER_NAME));
		List<String> fullNames = Arrays.asList(fullName.split(" "));
		lecturer.setFirstName(fullNames.get(fullNames.size()-1));
		lecturer.setLastName(fullName.replace(fullNames.get(fullNames.size()-1),"").trim());
		Faculty faculty = new Faculty();
		faculty.setFacultyId(getCellValue(row.getCell(COL_FACULTY_ID)));
		moduleClass.setFaculty(faculty);
		moduleClass.setLecturer(lecturer);

		return moduleClass;
	}

	public List<Attendance> getListAttendance(Sheet sheet, ModuleClass moduleClass){
		List<Attendance> attendances = new ArrayList<>();
		for (Row currentRow : sheet) {
			if (currentRow.getRowNum() < ROW_ATTENDANCE) {
				continue;
			}
			int numOfDays = getTotalDay(sheet);
			attendances.addAll(getAttendanceListByStudent(currentRow, numOfDays,moduleClass));
		}
		return attendances;
	}

	private List<Attendance> getAttendanceListByStudent(Row row, int numOfDays, ModuleClass moduleClass){
		List<Attendance> attendances = new ArrayList<Attendance>();
		Student student = new Student();
		MainClass mainClass = new MainClass();
		student.setId(getCellValue(row.getCell(COL_STUDENT_ID)));
		student.setLastName(getCellValue(row.getCell(COL_LAST_NAME)));
		student.setFirstName(getCellValue(row.getCell(COL_FIRST_NAME)));
		mainClass.setClassId(getCellValue(row.getCell(COL_MAIN_CLASS_ID)));
		student.setMainClass(mainClass);

		for(int i = COL_DAY_START;i<=numOfDays;i++) {
			Attendance attendance = null;
			String check = getCellValue(row.getCell(i));
			if(check!=null) {
				attendance = new Attendance();
				if(check.equalsIgnoreCase("p"))
					attendance.setAllowed(true);
				attendance.setDateOff(row.getSheet().getRow(ROW_DATE_OFF).getCell(i).getDateCellValue());
				attendance.setModuleClass(moduleClass);
				attendance.setStudent(student);
			}
			else
				continue;
			if(attendance!=null)
				attendances.add(attendance);
		}
		return attendances;
	}

	private static int getTotalDay(Sheet sheet) {
		int i = COL_DAY_START;
		for (Row currentRow : sheet)
			if (currentRow.getRowNum() == ROW_DATE_OFF)
				while(true) {
					try {
						currentRow.getCell(i).getDateCellValue();
					}
					catch (Exception e) {
						return i-1;
					}
					i++;
				}
		return 0;
	}

	public Sheet readFile(File file) throws IOException {
		InputStream inputStream = new FileInputStream(file);
		Workbook workbook = getWorkbook(inputStream, file.getAbsolutePath());
		Sheet sheet = workbook.getSheetAt(0);
		return sheet;
	}

	// Get Workbook
	private Workbook getWorkbook(InputStream inputStream, String excelFilePath) throws IOException {
		Workbook workbook = null;
		if (excelFilePath.endsWith("xlsx")) {
			workbook = new XSSFWorkbook(inputStream);
		} else if (excelFilePath.endsWith("xls")) {
			workbook = new HSSFWorkbook(inputStream);
		} else {
			throw new IllegalArgumentException("The specified file is not Excel file");
		}

		return workbook;
	}

	// Get cell value
	private String getCellValue(Cell cell) {
		cell.setCellType(CellType.STRING);
		String cellValue = "";
		cellValue = cell.getStringCellValue();
		return cellValue;
	}
}
