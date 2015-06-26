package comicstore.comic.entity;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.xml.bind.annotation.XmlRootElement;

import core.persistence.LookupEntity;

@Entity
@XmlRootElement
@Table(uniqueConstraints = @UniqueConstraint(columnNames = "name"))
public class Schedule extends LookupEntity {

	private static final long serialVersionUID = 5457903503616144111L;

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
		Schedule scheduleToCompare = (Schedule) obj;
		return super.equals(scheduleToCompare);
	}

	@Override
	public String toString() {
		return "Schedule [name=" + name + "]";
	}
}
