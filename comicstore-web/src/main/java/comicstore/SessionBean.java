package comicstore;

import java.io.Serializable;
import java.util.Locale;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

@Named
@SessionScoped
public class SessionBean implements Serializable{
	
	private static final long serialVersionUID = 1672017204692897284L;
	
	private Locale locale;
	
	@PostConstruct
	public void init() {
		locale = new Locale("es");
	}

	public Locale getLocale() {
		return locale;
	}

	public void setLocale(Locale locale) {
		this.locale = locale;
	}	
}
