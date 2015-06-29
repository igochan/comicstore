package comicstore;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.faces.application.Application;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import comicstore.comic.LookupService;
import comicstore.comic.entity.Format;
import comicstore.comic.entity.Genre;
import comicstore.comic.entity.Schedule;

@Named
@ApplicationScoped
public class WebApplicationBean implements Serializable {

	private static final long serialVersionUID = -3355019384296653072L;
	
	private List<Format> formats;

	private List<Schedule> schedules;

	private List<Genre> genres;

	private List<Locale> locales = new ArrayList<Locale>();
	
	@Inject
	private LookupService lookupService;
	
	@Inject
	private FacesContext facesContext;
	
	@PostConstruct
	public void init(){
		formats = lookupService.selectAll(Format.class);
		schedules = lookupService.selectAll(Schedule.class);
		genres = lookupService.selectAll(Genre.class);
		loadLocales();
	}

	private void loadLocales() {
		Application app = facesContext.getApplication();
		Iterator<Locale> supportedLocales = app.getSupportedLocales();
		while (supportedLocales.hasNext()) {
			locales.add(supportedLocales.next());
		}
		Locale defaultLocale = app.getDefaultLocale();
		locales.add(defaultLocale);
	}

	public List<Format> getFormats() {
		return formats;
	}

	public List<Schedule> getSchedules() {
		return schedules;
	}

	public List<Genre> getGenres() {
		return genres;
	}

	public List<Locale> getLocales() {
		return locales;
	}
}
