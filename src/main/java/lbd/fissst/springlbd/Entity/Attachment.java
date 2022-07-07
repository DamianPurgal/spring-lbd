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
    @GeneratedValue
    @Column(name="id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "file", columnDefinition = "VARBINARY")
    private byte[] file;

}
