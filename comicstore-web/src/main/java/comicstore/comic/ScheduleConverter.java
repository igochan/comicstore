package comicstore.comic;

import java.util.List;

import javax.faces.convert.FacesConverter;

import comicstore.comic.entity.Schedule;
import core.view.LookupConverter;

@FacesConverter(forClass = Schedule.class)
public class ScheduleConverter extends LookupConverter<Schedule> {

	@Override
	protected List<Schedule> getListElements() {
		return getAppBean().getSchedules();
	}
}
