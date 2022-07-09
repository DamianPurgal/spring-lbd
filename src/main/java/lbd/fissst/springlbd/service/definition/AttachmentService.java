package lbd.fissst.springlbd.service.definition;

import lbd.fissst.springlbd.Entity.Attachment;

import java.util.List;

public interface AttachmentService {

    Attachment addAttachmentToUserStory(Attachment attachment, Long userStoryId);

    List<Attachment> getAttachmentsOfUserStory(Long userStoryId);
}
