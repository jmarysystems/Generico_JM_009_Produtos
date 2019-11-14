/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package produtos_beans;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

/**
 *
 * @author NewUser
 */
@Entity
@Table(name = "PRODUTOS", catalog = "", schema = "JM")
@NamedQueries({
    @NamedQuery(name = "Produtos.findAll", query = "SELECT p FROM Produtos p")
    , @NamedQuery(name = "Produtos.findById", query = "SELECT p FROM Produtos p WHERE p.id = :id")
    , @NamedQuery(name = "Produtos.findByCodigoAuxiliar", query = "SELECT p FROM Produtos p WHERE p.codigoAuxiliar = :codigoAuxiliar")
    , @NamedQuery(name = "Produtos.findByDescricao", query = "SELECT p FROM Produtos p WHERE p.descricao = :descricao")
    , @NamedQuery(name = "Produtos.findByDataCadastro", query = "SELECT p FROM Produtos p WHERE p.dataCadastro = :dataCadastro")
    , @NamedQuery(name = "Produtos.findByIdUsuarioSistemaCadastro", query = "SELECT p FROM Produtos p WHERE p.idUsuarioSistemaCadastro = :idUsuarioSistemaCadastro")
    , @NamedQuery(name = "Produtos.findByDataAlteracao", query = "SELECT p FROM Produtos p WHERE p.dataAlteracao = :dataAlteracao")
    , @NamedQuery(name = "Produtos.findByIdUsuarioSistemaAlteracao", query = "SELECT p FROM Produtos p WHERE p.idUsuarioSistemaAlteracao = :idUsuarioSistemaAlteracao")})
public class Produtos implements Serializable {

    @Transient
    private PropertyChangeSupport changeSupport = new PropertyChangeSupport(this);

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID", nullable = false)
    private Integer id;
    @Column(name = "CODIGO_AUXILIAR", length = 150)
    private String codigoAuxiliar;
    @Basic(optional = false)
    @Column(name = "DESCRICAO", nullable = false, length = 500)
    private String descricao;
    @Basic(optional = false)
    @Column(name = "DATA_CADASTRO", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataCadastro;
    @Basic(optional = false)
    @Column(name = "ID_USUARIO_SISTEMA_CADASTRO", nullable = false)
    private int idUsuarioSistemaCadastro;
    @Basic(optional = false)
    @Column(name = "DATA_ALTERACAO", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataAlteracao;
    @Basic(optional = false)
    @Column(name = "ID_USUARIO_SISTEMA_ALTERACAO", nullable = false)
    private int idUsuarioSistemaAlteracao;

    public Produtos() {
    }

    public Produtos(Integer id) {
        this.id = id;
    }

    public Produtos(Integer id, String descricao, Date dataCadastro, int idUsuarioSistemaCadastro, Date dataAlteracao, int idUsuarioSistemaAlteracao) {
        this.id = id;
        this.descricao = descricao;
        this.dataCadastro = dataCadastro;
        this.idUsuarioSistemaCadastro = idUsuarioSistemaCadastro;
        this.dataAlteracao = dataAlteracao;
        this.idUsuarioSistemaAlteracao = idUsuarioSistemaAlteracao;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        Integer oldId = this.id;
        this.id = id;
        changeSupport.firePropertyChange("id", oldId, id);
    }

    public String getCodigoAuxiliar() {
        return codigoAuxiliar;
    }

    public void setCodigoAuxiliar(String codigoAuxiliar) {
        String oldCodigoAuxiliar = this.codigoAuxiliar;
        this.codigoAuxiliar = codigoAuxiliar;
        changeSupport.firePropertyChange("codigoAuxiliar", oldCodigoAuxiliar, codigoAuxiliar);
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        String oldDescricao = this.descricao;
        this.descricao = descricao;
        changeSupport.firePropertyChange("descricao", oldDescricao, descricao);
    }

    public Date getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(Date dataCadastro) {
        Date oldDataCadastro = this.dataCadastro;
        this.dataCadastro = dataCadastro;
        changeSupport.firePropertyChange("dataCadastro", oldDataCadastro, dataCadastro);
    }

    public int getIdUsuarioSistemaCadastro() {
        return idUsuarioSistemaCadastro;
    }

    public void setIdUsuarioSistemaCadastro(int idUsuarioSistemaCadastro) {
        int oldIdUsuarioSistemaCadastro = this.idUsuarioSistemaCadastro;
        this.idUsuarioSistemaCadastro = idUsuarioSistemaCadastro;
        changeSupport.firePropertyChange("idUsuarioSistemaCadastro", oldIdUsuarioSistemaCadastro, idUsuarioSistemaCadastro);
    }

    public Date getDataAlteracao() {
        return dataAlteracao;
    }

    public void setDataAlteracao(Date dataAlteracao) {
        Date oldDataAlteracao = this.dataAlteracao;
        this.dataAlteracao = dataAlteracao;
        changeSupport.firePropertyChange("dataAlteracao", oldDataAlteracao, dataAlteracao);
    }

    public int getIdUsuarioSistemaAlteracao() {
        return idUsuarioSistemaAlteracao;
    }

    public void setIdUsuarioSistemaAlteracao(int idUsuarioSistemaAlteracao) {
        int oldIdUsuarioSistemaAlteracao = this.idUsuarioSistemaAlteracao;
        this.idUsuarioSistemaAlteracao = idUsuarioSistemaAlteracao;
        changeSupport.firePropertyChange("idUsuarioSistemaAlteracao", oldIdUsuarioSistemaAlteracao, idUsuarioSistemaAlteracao);
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Produtos)) {
            return false;
        }
        Produtos other = (Produtos) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "newpackage.Produtos[ id=" + id + " ]";
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.removePropertyChangeListener(listener);
    }
    
}
