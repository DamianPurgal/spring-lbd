package lbd.fissst.springlbd.controller;

import lbd.fissst.springlbd.DTO.Attachment.AttachmentDTO;
import lbd.fissst.springlbd.DTO.Mappers.AttachmentMapper;
import lbd.fissst.springlbd.Entity.Attachment;
import lbd.fissst.springlbd.service.definition.AttachmentService;
import lombok.AllArgsConstructor;
import org.mapstruct.factory.Mappers;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("attachments")
public class AttachmentController {

    private AttachmentService attachmentService;

    private final AttachmentMapper mapper = Mappers.getMapper(AttachmentMapper.class);

    @PostMapping("/userstory/{userStoryId}")
    public void addAttachmentToUserStory(@RequestBody AttachmentDTO attachmentDTO,
                                         @PathVariable("userStoryId") Long userStoryId){
        Attachment attachment = mapper.mapAttachmentDTOtoAttachment(attachmentDTO);

        attachmentService.addAttachmentToUserStory(attachment, userStoryId);
    }

    @GetMapping("/userstory/{userStoryId}")
    public List<AttachmentDTO> getAttachmentOfUserStory(@PathVariable("userStoryId") Long userStoryId){
        return attachmentService.getAttachmentsOfUserStory(userStoryId)
                .stream()
                .map(mapper::mapAttachmentDTOtoAttachment)
                .toList();
    }

}
