/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package produtos_beans;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 *
 * @author NewUser
 */
@Entity
@Table(name = "PRODUTOS_IMAGENS", catalog = "", schema = "JM")
@NamedQueries({
    @NamedQuery(name = "ProdutosImagens.findAll", query = "SELECT p FROM ProdutosImagens p")
    , @NamedQuery(name = "ProdutosImagens.findById", query = "SELECT p FROM ProdutosImagens p WHERE p.id = :id")
    , @NamedQuery(name = "ProdutosImagens.findByNome", query = "SELECT p FROM ProdutosImagens p WHERE p.nome = :nome")
    , @NamedQuery(name = "ProdutosImagens.findByIdProdutos", query = "SELECT p FROM ProdutosImagens p WHERE p.idProdutos = :idProdutos")})
public class ProdutosImagens implements Serializable {

    @Transient
    private PropertyChangeSupport changeSupport = new PropertyChangeSupport(this);

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID", nullable = false)
    private Integer id;
    @Column(name = "NOME", length = 500)
    private String nome;
    @Lob
    @Column(name = "IMAGEM")
    private Serializable imagem;
    @Basic(optional = false)
    @Column(name = "ID_PRODUTOS", nullable = false)
    private int idProdutos;

    public ProdutosImagens() {
    }

    public ProdutosImagens(Integer id) {
        this.id = id;
    }

    public ProdutosImagens(Integer id, int idProdutos) {
        this.id = id;
        this.idProdutos = idProdutos;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        Integer oldId = this.id;
        this.id = id;
        changeSupport.firePropertyChange("id", oldId, id);
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        String oldNome = this.nome;
        this.nome = nome;
        changeSupport.firePropertyChange("nome", oldNome, nome);
    }

    public Serializable getImagem() {
        return imagem;
    }

    public void setImagem(Serializable imagem) {
        Serializable oldImagem = this.imagem;
        this.imagem = imagem;
        changeSupport.firePropertyChange("imagem", oldImagem, imagem);
    }

    public int getIdProdutos() {
        return idProdutos;
    }

    public void setIdProdutos(int idProdutos) {
        int oldIdProdutos = this.idProdutos;
        this.idProdutos = idProdutos;
        changeSupport.firePropertyChange("idProdutos", oldIdProdutos, idProdutos);
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
        if (!(object instanceof ProdutosImagens)) {
            return false;
        }
        ProdutosImagens other = (ProdutosImagens) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "newpackage.ProdutosImagens[ id=" + id + " ]";
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.removePropertyChangeListener(listener);
    }
    
}
