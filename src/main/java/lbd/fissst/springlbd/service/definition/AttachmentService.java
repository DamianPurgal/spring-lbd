package lbd.fissst.springlbd.service.definition;

import lbd.fissst.springlbd.DTO.Attachment.AttachmentDTO;
import lbd.fissst.springlbd.Entity.Attachment;

import java.util.List;

public interface AttachmentService {

    AttachmentDTO addAttachmentToUserStory(AttachmentDTO attachmentDTO, Long userStoryId);

    List<AttachmentDTO> getAttachmentsOfUserStory(Long userStoryId);
}
