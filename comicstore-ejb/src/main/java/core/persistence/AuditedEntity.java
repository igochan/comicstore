package core.persistence;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

@MappedSuperclass
public abstract class AuditedEntity extends AbstractEntity {

	private static final long serialVersionUID = 2330251538639907557L;

	private boolean deleted;

	@Temporal(TemporalType.DATE)
	@NotNull
	@Column(nullable = false)
	private Date modified;

	@NotNull
	@Column(nullable = false, length = 16)
	private String user;

	public boolean isDeleted() {
		return deleted;
	}

	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}

	public Date getModified() {
		return modified;
	}

	public void setModified(Date modified) {
		this.modified = modified;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	@PrePersist
	public void prePersist() {
		user = "TODO: add user";
		modified = new Date();
	}
}
