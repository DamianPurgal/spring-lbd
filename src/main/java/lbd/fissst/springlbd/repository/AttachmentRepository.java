package lbd.fissst.springlbd.repository;

import lbd.fissst.springlbd.Entity.Attachment;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface AttachmentRepository extends CrudRepository<Attachment, Long> {

    List<Attachment> findAllByUserStoryId(Long userStoryId);
}
