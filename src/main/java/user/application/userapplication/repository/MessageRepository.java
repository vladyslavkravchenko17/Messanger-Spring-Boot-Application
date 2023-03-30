package user.application.userapplication.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import user.application.userapplication.model.Message;

import java.util.List;

@Repository
public interface MessageRepository extends CrudRepository<Message, Long> {

    List<Message> findAllByDialogueId(long dialogueId);

    @Query(value = "SELECT m FROM Message m " +
            "WHERE m.dialogue.id = :dialogueId " +
            "AND m.id = (SELECT MAX(m2.id) " +
            "FROM Message m2 " +
            "WHERE m2.dialogue.id = :dialogueId)")
    Message findMessageWithMaxIdByDialogueId(@Param("dialogueId") Long dialogueId);


    @Query(value = "SELECT * FROM message " +
            "WHERE dialogue_id = :dialogueId " +
            "ORDER BY id " +
            "DESC " +
            "LIMIT :pageSize " +
            "OFFSET :offset",
            nativeQuery = true)
    List<Message> findMessagesByDialogueIdAndPage(@Param("dialogueId") long dialogueId, @Param("offset") int offset,
                                                  @Param("pageSize") int pageSize);

}
