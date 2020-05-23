package se.iuh.e2portal.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.iuh.e2portal.model.ModuleClass;
import se.iuh.e2portal.model.TimeTable;
import se.iuh.e2portal.repository.ModuleClassRepository;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjusters;
import java.util.List;
import java.util.Optional;

@Service
public class ModuleClassService  {

	@Autowired
	private ModuleClassRepository moduleClassRepository;


	public ModuleClass save(ModuleClass moduleClass) {
		return moduleClassRepository.save(moduleClass);
	}


	public Optional<ModuleClass> findById(String id) {
		Optional<ModuleClass> moduleClass = moduleClassRepository.findById(id);
		if(moduleClass.isPresent()) {
			moduleClass.get().setTotalDay(getTotalDay(id));
		}
		return moduleClass;
	}

	public List<ModuleClass> findByName(String name) {
		return moduleClassRepository.findByName(name);
	}


	public List<ModuleClass> findAll() {
		return (List<ModuleClass>) moduleClassRepository.findAll();
	}
	public Iterable<ModuleClass> findByFacultyId(String id) {
		return  moduleClassRepository.findByFaculty_FacultyId(id);
	}


	public boolean existsById(String id) {
		return moduleClassRepository.existsById(id);
	}


	public long count() {
		return moduleClassRepository.count();
	}


	public void deleteById(String id) {
		moduleClassRepository.deleteById(id);
	}


	public void delete(ModuleClass object) {
		moduleClassRepository.delete(object);
	}

	public int getTotalDay(String id) {
		try {
			Optional<ModuleClass> moduleClass = moduleClassRepository.findById(id);
			if(moduleClass.isPresent() && moduleClass.get().getTimeTables()!=null) {
				int sum = 0;
				List<TimeTable> timeTables = moduleClass.get().getTimeTables();
				for(TimeTable timeTable : timeTables) {
					sum += noOfMondaysBetween(timeTable.getStartDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate(), timeTable.getEndDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate(), getDayOfWeek(timeTable.getDayOfWeek()));
				}
				return sum;
			}
		} catch (Exception e) {
			return 0;
		}
		return 0;
	}

	public static long noOfMondaysBetween(LocalDate first, LocalDate last, DayOfWeek dayOfWeek) {
		if (last.isBefore(first))
			throw new IllegalArgumentException("first " + first + " was after last " + last);
		LocalDate firstMonday = first.with(TemporalAdjusters.next(dayOfWeek));
		LocalDate lastMonday = last.with(TemporalAdjusters.previous(dayOfWeek));
		long number = ChronoUnit.WEEKS.between(firstMonday, lastMonday);
		return number + 1;
	}

	public static DayOfWeek getDayOfWeek(String day) {
		if(day.equalsIgnoreCase("Thứ hai"))
			return DayOfWeek.MONDAY;
		if(day.equalsIgnoreCase("Thứ ba"))
			return DayOfWeek.TUESDAY;
		if(day.equalsIgnoreCase("Thứ tư"))
			return DayOfWeek.WEDNESDAY;
		if(day.equalsIgnoreCase("Thứ năm"))
			return DayOfWeek.THURSDAY;
		if(day.equalsIgnoreCase("Thứ sáu"))
			return DayOfWeek.FRIDAY;
		if(day.equalsIgnoreCase("Thứ bảy"))
			return DayOfWeek.SATURDAY;
		if(day.equalsIgnoreCase("Chủ nhật"))
			return DayOfWeek.SUNDAY;
		return null;
	}

}
