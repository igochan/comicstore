package core.util;

import java.util.logging.Logger;

import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import core.ApplicationBean;

public class Resources {

	@Inject
	private ApplicationBean appBean;

	@Produces
	@PersistenceContext
	private EntityManager em;

	@Produces
	public Logger produceLog(InjectionPoint injectionPoint) {
		return Logger.getLogger(injectionPoint.getMember().getDeclaringClass()
				.getName());
	}

	@Produces
	@ConfigurationProperty
	public String produceProperty(InjectionPoint ip) {
		ConfigurationProperty annotation = ip.getAnnotated().getAnnotation(
				ConfigurationProperty.class);
		String key = annotation.value();
		String value = appBean.getProperties().getProperty(key);
		if (value == null) {
			boolean valueRequired = annotation.required();
			if (valueRequired) {
				throw new IllegalStateException("Property " + key
						+ " not found");
			}
		}
		return value;
	}
}
