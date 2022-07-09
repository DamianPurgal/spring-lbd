package lbd.fissst.springlbd.service.implementation;

import lbd.fissst.springlbd.Entity.Attachment;
import lbd.fissst.springlbd.Entity.UserStory;
import lbd.fissst.springlbd.repository.AttachmentRepository;
import lbd.fissst.springlbd.repository.UserStoryRepository;
import lbd.fissst.springlbd.service.definition.AttachmentService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@AllArgsConstructor
public class AttachmentServiceImpl implements AttachmentService {

    private AttachmentRepository attachmentRepository;

    private UserStoryRepository userStoryRepository;

    @Override
    public Attachment addAttachmentToUserStory(Attachment attachment, Long userStoryId) {
        UserStory userStory = userStoryRepository.findById(userStoryId)
                .orElseThrow(
                        () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "UserStory not found")
        );

        attachment.setUserStory(userStory);

        return attachmentRepository.save(attachment);
    }

    @Override
    public List<Attachment> getAttachmentsOfUserStory(Long userStoryId) {
        return attachmentRepository.findAllByUserStoryId(userStoryId);
    }
}
