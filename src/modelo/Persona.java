/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

/**
 *
 * @author Comodore
 */
@Entity(name= "api_persona")
public class Persona implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name= "persona_nombre")
    private String nombre;
    
    @Column(name= "persona_apellido")
    private String apellido;
    
    @Column(name= "persona_edad")
    private int edad;
    
    //relacion uno a uno unidireccional con celular
    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name= "fk_celular")
    private Celular celular;
    
    //relacion uno a uno bidireccional con auto
    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "fk_Auto")
    private Auto auto;
    
    //relacion uno a muchos unidireccional con tareas
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "fk_notas")
    private List<Tareas> tareas= new ArrayList<Tareas>();
    
    //relacion uno a muchos bidireccional con ropa
    @OneToMany(mappedBy = "persona", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Ropa> ropa= new ArrayList<Ropa>();
    
    //relacion muchos a muchos con colectivo
    @ManyToMany(cascade = {CascadeType.PERSIST,CascadeType.MERGE })
    @JoinTable(name = "persona_colectivo",joinColumns = @JoinColumn(name="persona_id"),inverseJoinColumns = @JoinColumn(name = "colectivo_id"))
    private List<Colectivo> colectivos= new ArrayList<Colectivo>();
    
    public void addRopa(Ropa rop){
        
        ropa.add(rop);
        rop.setPersona(this);
    }
    
    public void addColectivo(Colectivo colectivo){
        
        colectivos.add(colectivo);
        colectivo.getPersonas().add(this);
    }

    public List<Colectivo> getColectivos() {
        return colectivos;
    }

    public void setColectivos(List<Colectivo> colectivos) {
        this.colectivos = colectivos;
    }
    
    

    public List<Ropa> getRopa() {
        return ropa;
    }

    public void setRopa(List<Ropa> ropa) {
        this.ropa = ropa;
    }
    
    

    public List<Tareas> getTareas() {
        return tareas;
    }

    public void setTareas(List<Tareas> tareas) {
        this.tareas = tareas;
    }
    
    

    public Auto getAuto() {
        return auto;
    }

    public void setAuto(Auto auto) {
        this.auto = auto;
    }
    
    

    public Celular getCelular() {
        return celular;
    }

    public void setCelular(Celular celular) {
        this.celular = celular;
    }
    
    

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }
    
    

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
        if (!(object instanceof Persona)) {
            return false;
        }
        Persona other = (Persona) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modelo.Persona[ id=" + id + " ]";
    }
    
}
