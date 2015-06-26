package comicstore.comic;

import java.util.List;

import javax.faces.convert.FacesConverter;

import comicstore.comic.entity.Format;

import core.view.LookupConverter;

@FacesConverter(forClass = Format.class)
public class FormatConverter extends LookupConverter<Format> {

	@Override
	protected List<Format> getListElements() {
		return getAppBean().getFormats();
	}
}
