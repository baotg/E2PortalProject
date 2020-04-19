package se.iuh.e2portal.component;

import org.apache.poi.ss.usermodel.*;
import org.springframework.stereotype.Component;
import se.iuh.e2portal.model.Faculty;
import se.iuh.e2portal.model.Lecturer;
import se.iuh.e2portal.model.MainClass;
import se.iuh.e2portal.model.Student;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
@Component
public class StudentReader {
    private static int ROW_MAIN_CLASS = 1;
    private static int COL_CLASS_ID_MAIN_CLASS = 0;
    private static int COL_SPECIALITY_MAIN_CLASS = 1;
    private static int COL_LEVEL_MAIN_CLASS = 2;
    private static int COL_TYPE_MAIN_CLASS = 3;
    private static int COL_FAULTY_MAIN_CLASS = 4;
    private static int COL_YEAR_MAIN_CLASS = 5;
    private static int COL_LECTURER_NAME = 6;
    private static int COL_LECTURER_ID = 7;
    private static int ROW_STUDENT = 4;
    private static int COL_ID_STUDENT = 1;
    private static int COL_FNAME_STUDENT = 2;
    private static int COL_LNAME_STUDENT = 3;
    private static int COL_GENDER_STUDENT = 4;
    private static int COL_DOB_STUDENT = 5;
    private static int COL_ADDRESS_STUDENT = 6;
    private static int COL_SPHONE_STUDENT = 7;
    private static int COL_FPHONE_STUDENT = 8;
    private static int COL_EMAIL_STUDENT = 9;
    public MainClass getMainClass(Sheet sheet){
        Row row = sheet.getRow(ROW_MAIN_CLASS);
        MainClass mainClass = new MainClass();
        Lecturer lecturer = new Lecturer();
        String classId = getCellValue(row.getCell(COL_CLASS_ID_MAIN_CLASS));
        String speciality = getCellValue(row.getCell(COL_SPECIALITY_MAIN_CLASS));
        String level = getCellValue(row.getCell(COL_LEVEL_MAIN_CLASS));
        String type = getCellValue(row.getCell(COL_TYPE_MAIN_CLASS));
        String facultyId = getCellValue(row.getCell(COL_FAULTY_MAIN_CLASS));
        lecturer.setId(getCellValue(row.getCell(COL_LECTURER_ID)));
        String fullName = getCellValue(row.getCell(COL_LECTURER_NAME));
        List<String> fullNames = Arrays.asList(fullName.split(" "));
        lecturer.setFirstName(fullNames.get(fullNames.size()-1));
        lecturer.setLastName(fullName.replace(fullNames.get(fullNames.size()-1),"").trim());
        mainClass.setLecturer(lecturer);
        Faculty faculty = new Faculty();
        faculty.setFalcultyId(facultyId);
        String year = getCellValue(row.getCell(COL_YEAR_MAIN_CLASS));
        mainClass.setClassId(classId);
        mainClass.setSpeciality(speciality);
        mainClass.setLevel(level);
        mainClass.setType(type);
        mainClass.setFaculty(faculty);
        mainClass.setYear(year);
        return mainClass;
    }
    public List<Student> getListStudent(Sheet sheet, MainClass mainClass){
        List<Student> list = new ArrayList<>();
        for (Row currentRow : sheet) {
            if (currentRow.getRowNum() < ROW_STUDENT) {
                continue;
            }
            Student student = getStudent(currentRow);
            student.setMainClass(mainClass);
            list.add(student);
        }
        return list;
    }
    private Student getStudent(Row row){
        Student student = new Student();
        String id = getCellValue(row.getCell(COL_ID_STUDENT));
        String firstName = getCellValue(row.getCell(COL_FNAME_STUDENT));
        String lastName = getCellValue(row.getCell(COL_LNAME_STUDENT));
        String gender = getCellValue(row.getCell(COL_GENDER_STUDENT));
        //
        Date dob = new Date();
        if(DateUtil.isCellDateFormatted(row.getCell(COL_DOB_STUDENT))){
            dob = row.getCell(COL_DOB_STUDENT).getDateCellValue();
        }
        String address = getCellValue(row.getCell(COL_ADDRESS_STUDENT));
        String studentNumber = getCellValue(row.getCell(COL_SPHONE_STUDENT));
        String familyNumber = getCellValue(row.getCell(COL_FPHONE_STUDENT));
        String email = getCellValue(row.getCell(COL_EMAIL_STUDENT));
        student.setId(id);
        student.setFirstName(firstName);
        student.setLastName(lastName);
        student.setGender(gender.equalsIgnoreCase("nam"));
        student.setDateOfBirth(dob);
        student.setAddress(address);
        student.setNumberPhone(studentNumber);
        student.setFamilyNumber(familyNumber);
        student.setEmail(email);
        return student;
    }
    private String getCellValue(Cell cell) {
        CellType cellType = cell.getCellTypeEnum();
        cell.setCellType(CellType.STRING);
        String cellValue = "";
        cellValue = cell.getStringCellValue();
        return cellValue;
    }
}
