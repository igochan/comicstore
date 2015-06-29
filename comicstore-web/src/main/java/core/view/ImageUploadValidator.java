package core.view;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
import javax.inject.Inject;
import javax.servlet.http.Part;

@FacesValidator("core.view.ImageUploadValidator")
public class ImageUploadValidator implements Validator {
	
	@Inject
	private Logger logger;

	private static String[] ALLOWED_CONTENT_TYPE = { "image/jpeg", "image/png" };

	private static Integer MAX_SIZE = 1024 * 1024;

	public void validate(FacesContext ctx, UIComponent comp, Object value) {
		if ( value == null ) {
			return;
		}
		List<FacesMessage> msgs = new ArrayList<FacesMessage>();
		Part file = (Part) value;
		if (file.getSize() > MAX_SIZE) {
			logger.warning("The file is too big");
			msgs.add(new FacesMessage("The file is too big"));
		}
		boolean isAllowedContentType = false;
		for (String s : ALLOWED_CONTENT_TYPE) {
			if (s.equals(file.getContentType())) {
				isAllowedContentType = true;
				break;
			}
		}
		if (!isAllowedContentType) {
			logger.warning("The file has not an allowed extension");
			msgs.add(new FacesMessage("The file has not an allowed extension"));
		}
		if (!msgs.isEmpty()) {
			throw new ValidatorException(msgs);
		}
	}
}
