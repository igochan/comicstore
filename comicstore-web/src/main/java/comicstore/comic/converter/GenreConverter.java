package comicstore.comic.converter;

import java.util.List;

import javax.faces.convert.FacesConverter;

import comicstore.comic.entity.Genre;

import core.view.LookupConverter;

@FacesConverter(forClass = Genre.class)
public class GenreConverter extends LookupConverter<Genre> {

	@Override
	protected List<Genre> getListElements() {
		return getAppBean().getGenres();
	}
}
