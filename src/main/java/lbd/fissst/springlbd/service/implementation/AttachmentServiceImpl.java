package lbd.fissst.springlbd.service.implementation;

import lbd.fissst.springlbd.DTO.Attachment.AttachmentDTO;
import lbd.fissst.springlbd.DTO.Mappers.AttachmentMapper;
import lbd.fissst.springlbd.Entity.Attachment;
import lbd.fissst.springlbd.Entity.UserStory;
import lbd.fissst.springlbd.exception.AppEntityNotFoundException;
import lbd.fissst.springlbd.exception.AttachmentNotValidException;
import lbd.fissst.springlbd.repository.AttachmentRepository;
import lbd.fissst.springlbd.repository.UserStoryRepository;
import lbd.fissst.springlbd.service.definition.AttachmentService;
import lombok.AllArgsConstructor;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class AttachmentServiceImpl implements AttachmentService {

    private AttachmentRepository attachmentRepository;

    private UserStoryRepository userStoryRepository;

    private final AttachmentMapper mapper = Mappers.getMapper(AttachmentMapper.class);

    @Override
    public AttachmentDTO addAttachmentToUserStory(AttachmentDTO attachmentDTO, Long userStoryId) {
        Attachment attachment = mapper.mapAttachmentDtoToAttachment(attachmentDTO);

        if(isAttachmentValid(attachment)){
            UserStory userStory = userStoryRepository.findById(userStoryId)
                    .orElseThrow(
                            () -> new AppEntityNotFoundException("UserStory not found")
                    );

            attachment.setUserStory(userStory);

            return mapper.mapAttachmentToAttachmentDto(
                    attachmentRepository.save(attachment)
            );
        }else{
            throw new AttachmentNotValidException("Attachment validation failed");
        }

    }

    @Override
    public List<AttachmentDTO> getAttachmentsOfUserStory(Long userStoryId) {
        return attachmentRepository.findAllByUserStoryId(userStoryId)
                .stream()
                .map(mapper::mapAttachmentToAttachmentDto)
                .toList();
    }

    //Utility
    private boolean isAttachmentValid(Attachment attachment){
        if(attachment.getName() == null){
            return false;
        }
        if(attachment.getName().isBlank()){
            return false;
        }
        if(attachment.getFile().length == 0){
            return false;
        }

        return true;
    }
}
