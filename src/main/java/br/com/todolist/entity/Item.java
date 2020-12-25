package br.com.todolist.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * Entity class for TB_ITEM
 * @author Lucas Feitozas
 *
 */
@Entity
@Table(name="TB_ITEM")
@SequenceGenerator(name= "SEQ_ITEM_ID", sequenceName = "SEQ_ITEM_ID", initialValue=4, allocationSize = 1)
public class Item implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	private String title;

	private String description;

	@ManyToOne
	@JoinColumn(name = "user_id")
	private UserTask user;

	@ManyToOne
	@JoinColumn(name = "lista_id")
	private Task task;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "item_id")
	private Item itemChild;

	public Item getItemChild() {
		return itemChild;
	}

	public void setItemChild(Item itemChild) {
		this.itemChild = itemChild;
	}

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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public UserTask getUser() {
		return user;
	}

	public void setUser(UserTask user) {
		this.user = user;
	}

	public Task getTask() {
		return task;
	}

	public void setTask(Task task) {
		this.task = task;
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
		Item other = (Item) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Item [id=" + id + ", title=" + title + ", description=" + description + ", user=" + user + ", task="
				+ task + ", itemChild=" + itemChild + "]";
	}

	

}
