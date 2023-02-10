package by.goncharov.kameleoonTask.entity;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "accounts")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    @Column(name = "login")
    private String login;

    @Column(name = "password")
    private String password;

    @Column(name = "email")
    private String email;


    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Quote> quoteList;
}
