package br.com.todolist.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * Entity class for TB_LISTA
 * @author Lucas Feitozas
 *
 */
@Entity
@Table(name = "TB_LISTA")
@SequenceGenerator(name= "SEQ_LISTA_ID", sequenceName = "SEQ_LISTA_ID", initialValue=4, allocationSize = 1)
public class Task implements Serializable {
	private static final long serialVersionUID = 2231164814711983883L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	private String title;

	@ManyToOne
	@JoinColumn(name = "USER_ID")
	private UserTask user;

	@OneToMany(mappedBy = "task", cascade = CascadeType.REMOVE)
	private List<Item> items;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public UserTask getUser() {
		return user;
	}

	public void setUser(UserTask user) {
		this.user = user;
	}

	public List<Item> getItems() {
		return items;
	}

	public void setItems(List<Item> items) {
		this.items = items;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Task other = (Task) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Task [title=" + title + ", user=" + user + "]";
	}

}
