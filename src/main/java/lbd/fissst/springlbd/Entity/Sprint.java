package lbd.fissst.springlbd.Entity;

import lbd.fissst.springlbd.Entity.Enums.SprintStatus;
import lombok.*;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Sprints")
public class Sprint {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "date_start")
    private Date dateStart;

    @Column(name = "date_end")
    private Date dateEnd;

    @Column(name = "description")
    private String description;

    @Column(name="status")
    @Enumerated(EnumType.STRING)
    private SprintStatus status;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "SprintUserStory",
            joinColumns = { @JoinColumn(name = "SPRINT_ID") },
            inverseJoinColumns = { @JoinColumn(name = "USER_STORY_ID") }
    )
    Set<UserStory> projects = new HashSet<>();

}
