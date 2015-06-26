package comicstore.comic.entity;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.xml.bind.annotation.XmlRootElement;

import core.persistence.LookupEntity;

@Entity
@XmlRootElement
@Table(uniqueConstraints = @UniqueConstraint(columnNames = "name"))
public class Genre extends LookupEntity {

	private static final long serialVersionUID = 1058836968157477576L;

	
	@Override
	public int hashCode() {
		return super.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		Genre genreToCompare = (Genre) obj;
		return super.equals(genreToCompare);
	}

	@Override
	public String toString() {
		return "Genre [name=" + name + "]";
	}
}
