package ch.mlz.axon.todo.app.query.task;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.With;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.UUID;

/**
 * @author albert
 */
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@ToString
@EqualsAndHashCode(of = { "id" })
public class TaskEntry {

	@Id
	private UUID id;

	private String username;
	
	@Setter
	@With
	private String title;
	
	@Setter
	@With
	private boolean completed;
	
	@Setter
	@With
	private boolean starred;
}