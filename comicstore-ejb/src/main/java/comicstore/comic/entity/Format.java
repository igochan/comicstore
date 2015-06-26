package comicstore.comic.entity;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.xml.bind.annotation.XmlRootElement;

import core.persistence.LookupEntity;

@Entity
@XmlRootElement
@Table(uniqueConstraints = @UniqueConstraint(columnNames = "name"))
public class Format extends LookupEntity {

	private static final long serialVersionUID = -7236593832224048904L;

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
		Format formatToCompare = (Format) obj;
		return super.equals(formatToCompare);
	}

	@Override
	public String toString() {
		return "Format [name=" + name + "]";
	}
}
