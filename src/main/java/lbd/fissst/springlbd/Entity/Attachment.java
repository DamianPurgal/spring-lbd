package lbd.fissst.springlbd.Entity;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Attachments")
public class Attachment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "file", columnDefinition = "VARBINARY")
    private byte[] file;

    @ManyToOne
    @JoinColumn(name="user_story_id", nullable=false)
    UserStory userStory;

}
