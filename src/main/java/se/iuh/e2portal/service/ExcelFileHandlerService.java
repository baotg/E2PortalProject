package se.iuh.e2portal.service;

import java.io.InputStream;
import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import se.iuh.e2portal.model.Attendance;
import se.iuh.e2portal.model.GradingResult;
import se.iuh.e2portal.model.ModuleClass;
import se.iuh.e2portal.model.Student;
import se.iuh.e2portal.model.TimeTable;
@Scope(value = 	WebApplicationContext.SCOPE_SESSION, proxyMode = ScopedProxyMode.TARGET_CLASS)
@Transactional
@Service
public class ExcelFileHandlerService {
	
	private InputStream inputStream;
	private List<Student> students;
	private List<TimeTable> timeTables;
	private List<GradingResult> gradingResults;
	private List<Attendance> attendances;
	private ModuleClass moduleClass;
	
	public InputStream getInputStream() {
		return inputStream;
	}

	public void setInputStream(InputStream inputStream) {
		this.inputStream = inputStream;
	}

	public List<Student> getStudents() {
		return students;
	}

	public void setStudents(List<Student> students) {
		this.students = students;
	}

	public List<TimeTable> getTimeTables() {
		return timeTables;
	}

	public void setTimeTables(List<TimeTable> timeTables) {
		this.timeTables = timeTables;
	}

	public List<GradingResult> getGradingResults() {
		return gradingResults;
	}

	public void setGradingResults(List<GradingResult> gradingResults) {
		this.gradingResults = gradingResults;
	}

	public List<Attendance> getAttendances() {
		return attendances;
	}

	public void setAttendances(List<Attendance> attendances) {
		this.attendances = attendances;
	}

	public ModuleClass getModuleClass() {
		return moduleClass;
	}

	public void setModuleClass(ModuleClass moduleClass) {
		this.moduleClass = moduleClass;
	}
	
	
	
}
