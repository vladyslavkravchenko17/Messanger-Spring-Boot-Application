package user.application.userapplication.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import user.application.userapplication.model.Dialogue;

import java.util.List;

@Repository
public interface DialogueRepository extends CrudRepository<Dialogue, Long> {


    @Query("SELECT d FROM Dialogue d JOIN d.users u WHERE u.id = :userId")
    List<Dialogue> findAllByUserId(@Param("userId") Long userId);
    }
