package DomainModel;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
import javax.persistence.*;

@Entity
@Table(name = "chamados")
public class Chamado implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String descricao;

    @Column(length = 20)
    private String status; // ABERTO, EM_ATENDIMENTO, RESOLVIDO

    @Temporal(TemporalType.TIMESTAMP)
    private Date dataAbertura;

    @ManyToOne
    @JoinColumn(name = "requisitante_id")
    private Usuario requisitante;

    @ManyToOne
    @JoinColumn(name = "tecnico_id")
    private Usuario tecnico;

    public Chamado() {
        this.status = "ABERTO";
        this.dataAbertura = new Date();
    }

    public Long getId() {
        return id;
    }

    public String getDescricao() {
        return descricao;
    }

    public String getStatus() {
        return status;
    }

    public Date getDataAbertura() {
        return dataAbertura;
    }

    public Usuario getRequisitante() {
        return requisitante;
    }

    public Usuario getTecnico() {
        return tecnico;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setDataAbertura(Date dataAbertura) {
        this.dataAbertura = dataAbertura;
    }

    public void setRequisitante(Usuario requisitante) {
        this.requisitante = requisitante;
    }

    public void setTecnico(Usuario tecnico) {
        this.tecnico = tecnico;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 83 * hash + Objects.hashCode(this.id);
        hash = 83 * hash + Objects.hashCode(this.descricao);
        hash = 83 * hash + Objects.hashCode(this.status);
        hash = 83 * hash + Objects.hashCode(this.dataAbertura);
        hash = 83 * hash + Objects.hashCode(this.requisitante);
        hash = 83 * hash + Objects.hashCode(this.tecnico);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Chamado other = (Chamado) obj;
        if (!Objects.equals(this.descricao, other.descricao)) {
            return false;
        }
        if (!Objects.equals(this.status, other.status)) {
            return false;
        }
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.dataAbertura, other.dataAbertura)) {
            return false;
        }
        if (!Objects.equals(this.requisitante, other.requisitante)) {
            return false;
        }
        return Objects.equals(this.tecnico, other.tecnico);
    }

    
}