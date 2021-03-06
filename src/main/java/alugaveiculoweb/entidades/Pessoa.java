package alugaveiculoweb.entidades;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

/**
 *
 * @author davi, davydadriel
 */
@Entity
//@SecondaryTable(name = "TB_Telefone",
  //      pkJoinColumns = {
  //     @PrimaryKeyJoinColumn(name = "ID_Pessoa")}
//)
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "DISC_Pessoa",
discriminatorType = DiscriminatorType.STRING, length = 1)
@Table(name = "TB_Pessoa")
public abstract class Pessoa implements Serializable {
   
    
    @Id
    @Column(name = "ID_Pessoa")
    @GeneratedValue(strategy = GenerationType.IDENTITY)    
    protected Long id;
    
    @Size(max = 4)
    @ElementCollection
    @CollectionTable(name = "TB_Telefone",
            joinColumns = @JoinColumn(name = "ID_Pessoa"))
    //@Basic(fetch = FetchType.LAZY)
    @Column(name = "TXT_TELEFONE", table = "TB_Telefone", nullable = false)
    protected Collection<String> telefones;
    
    @NotBlank
    @Size(max = 30, min = 3)
    @Pattern(regexp = "\\p{Upper}{1}\\p{Lower}+", message = "{com.mycompany.alugaveiculo.Pessoa.nome}")
    @Column(name = "TXT_NOME", length = 30, nullable = false)
    protected String nome;
    
    @NotBlank
    @Pattern(regexp = "((?=.*\\p{Digit})(?=.*\\p{Lower})(?=.*\\p{Upper})(?=.*\\p{Punct}).{8})", 
            message = "{com.mycompany.alugaveiculo.Pessoa.senha}")
    @Column(name = "TXT_SENHA", length = 8, nullable = false)
    protected String senha;
    
    @NotNull
    @Email
    @Column(name="TXT_EMAIL", nullable = false)
    protected String email;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Collection<String> getTelefones() {
        return telefones;
    }

    public void setTelefones(Collection<String> telefones) {
        this.telefones = telefones;
    }
    
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

 
    
}
