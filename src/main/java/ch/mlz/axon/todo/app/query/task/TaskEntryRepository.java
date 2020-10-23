package ch.mlz.axon.todo.app.query.task;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

/**
 * @author albert
 */
public interface TaskEntryRepository extends CrudRepository<TaskEntry, UUID> {
	Page<TaskEntry> findByUsernameAndCompleted(String username, boolean completed, Pageable pageable);

	Page<TaskEntry> findByCompleted(boolean completed, Pageable pageable);

}
