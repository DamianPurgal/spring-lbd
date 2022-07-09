package lbd.fissst.springlbd.Entity;

import lbd.fissst.springlbd.Entity.Enums.UserStoryStatus;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
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

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "points")
    private Integer points;

    @Column(name="status", nullable = false)
    @Enumerated(EnumType.STRING)
    private UserStoryStatus status;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "Sprint_User_Story",
            joinColumns = { @JoinColumn(name = "USER_STORY_ID") },
            inverseJoinColumns = { @JoinColumn(name = "SPRINT_ID") }
    )
    Set<Sprint> sprints = new HashSet<>();


    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "userStory")
    Set<Attachment> attachments = new HashSet<>();

}
