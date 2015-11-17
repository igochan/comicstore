package core.view;

import java.util.List;
import java.util.NoSuchElementException;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

import comicstore.WebApplicationBean;

import core.persistence.LookupEntity;

public abstract class LookupConverter<T extends LookupEntity> implements Converter {

	protected abstract List<T> getListElements();

	@Override
	public Object getAsObject(FacesContext context, UIComponent component,
			String value) {
		List<T> elements = getListElements();
		for (T element : elements) {
			if (element.getName().equals(value)) {
				return element;
			}
		}
		throw new NoSuchElementException(value);
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component,
			Object value) {
		return ((LookupEntity) value).getName();
	}

	protected WebApplicationBean getAppBean() {
		FacesContext context = FacesContext.getCurrentInstance();
		return context.getApplication().evaluateExpressionGet(context,
				"#{webApplicationBean}", WebApplicationBean.class);
	}
}