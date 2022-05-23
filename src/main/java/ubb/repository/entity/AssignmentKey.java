package ubb.repository.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.time.LocalDate;

@Data
@Embeddable
@NoArgsConstructor
@AllArgsConstructor
public class AssignmentKey implements Serializable {
    @Column(name = "id_employee",
            nullable = false,
            columnDefinition = "BIGINT")
    private Long idEmployee;

    @Column(name = "id_bug",
            nullable = false,
            columnDefinition = "BIGINT")
    private Long idBug;

}
