package hr.microblink.demo.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

@Data
@Entity
@Table(name = "People")
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    @NotBlank
    @Column(name = "first_name", nullable = false)
    private String firstName;
    @NotNull
    @NotBlank
    @Column(name = "last_name", nullable = false)
    private String lastName;
    @Column(name = "gender")
    private String gender;
    @Column(name = "residence")
    private String residence;
    @NotNull
    @NotBlank
    @Size(min = 9,max = 9)
    @Column(name = "document_number", nullable = false)
    private String documentNumber;
    @Size(min=11, max = 11)
    @Column(name = "id_number")
    private String idNumber;
    @Column(name = "nationality")
    private String nationality;
    @Column(name = "date_of_birth")
    @JsonFormat(pattern="dd.MM.yyyy")
    private Date dateOfBirth;

}
