package lbd.fissst.springlbd.Entity;

import lbd.fissst.springlbd.Entity.Enums.UserStoryStatus;
import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "USER_STORIES")
public class UserStory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "points")
    private Integer points;

    @Column(name="status")
    @Enumerated(EnumType.STRING)
    private UserStoryStatus status;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "Sprint_User_Story",
            joinColumns = { @JoinColumn(name = "USER_STORY_ID") },
            inverseJoinColumns = { @JoinColumn(name = "SPRINT_ID") }
    )
    Set<Sprint> sprints = new HashSet<>();

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "User_Story_Attachments",
            joinColumns = { @JoinColumn(name = "USER_STORY_ID") },
            inverseJoinColumns = { @JoinColumn(name = "ATTACHMENT_ID") }
    )
    Set<Attachment> attachments = new HashSet<>();
}
