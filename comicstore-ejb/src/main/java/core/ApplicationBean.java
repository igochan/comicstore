package core;

import java.io.IOException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class ApplicationBean {

	@Inject
	private Logger logger;

	private Properties properties;

	@PostConstruct
	public void init() {
		properties = new Properties();
		try {
			properties.load(ApplicationBean.class
					.getResourceAsStream("/comicstore/config.properties"));
		} catch (IOException e) {
			logger.log(Level.SEVERE, "Could not load properties");
		}
	}

	public Properties getProperties() {
		return properties;
	}
}
