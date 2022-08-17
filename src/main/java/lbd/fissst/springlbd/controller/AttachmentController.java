package lbd.fissst.springlbd.controller;

import lbd.fissst.springlbd.DTO.Attachment.AttachmentDTO;
import lbd.fissst.springlbd.service.definition.AttachmentService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("attachments")
public class AttachmentController {

    private AttachmentService attachmentService;

    @PostMapping("/userstory/{userStoryId}")
    public void addAttachmentToUserStory(@RequestBody AttachmentDTO attachmentDTO,
                                         @PathVariable("userStoryId") Long userStoryId){
        attachmentService.addAttachmentToUserStory(attachmentDTO, userStoryId);
    }

    @GetMapping("/userstory/{userStoryId}")
    public List<AttachmentDTO> getAttachmentOfUserStory(@PathVariable("userStoryId") Long userStoryId){
        return attachmentService.getAttachmentsOfUserStory(userStoryId);
    }

}
