package lbd.fissst.springlbd.DTO.Mappers;

import lbd.fissst.springlbd.DTO.Attachment.AttachmentDTO;
import lbd.fissst.springlbd.Entity.Attachment;
import org.mapstruct.Mapper;

@Mapper
public interface AttachmentMapper {
    Attachment mapAttachmentDTOtoAttachment(AttachmentDTO attachmentDTO);

    AttachmentDTO mapAttachmentDTOtoAttachment(Attachment attachment);

}
