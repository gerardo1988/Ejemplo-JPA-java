/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nuevajpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import modelo.Auto;
import modelo.Celular;
import modelo.Colectivo;
import modelo.Persona;
import modelo.Ropa;
import modelo.Tareas;

/**
 *
 * @author Comodore
 */
public class Ejecutajpa {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("nuevajpaPU");
        EntityManager manager = factory.createEntityManager();
        
        try {
            
            manager.getTransaction().begin();
            
            Persona p1= new Persona();
            p1.setNombre("Veronica");
            p1.setApellido("Rodriguez");
            p1.setEdad(19);
            
            Persona p2= new Persona();
            p2.setNombre("Lucia");
            p2.setApellido("Fernandez");
            p2.setEdad(16);
            
            Celular cel1= new Celular();
            cel1.setMarca("Xiaomi");
            cel1.setModelo("Redmi Note 8");
            p1.setCelular(cel1);
            
            Celular cel2= new Celular();
            cel2.setMarca("Samsumg");
            cel2.setModelo("S10 plus");
            p2.setCelular(cel2);
            
            
            Auto aut1= new Auto();
            aut1.setMarca("ford");
            aut1.setAño(2018);
            p1.setAuto(aut1);
            
            Auto aut2= new Auto();
            aut2.setMarca("Tesla");
            aut2.setAño(2019);
            p2.setAuto(aut2);
            
            Tareas tar1= new Tareas();
            tar1.setNombre("ir al supermercado");
            p1.getTareas().add(tar1);
            Tareas tar2= new Tareas();
            tar2.setNombre("lavar la ropa");
            p1.getTareas().add(tar2);
            
            
                     
            Ropa rp1= new Ropa();
            rp1.setTipo("camisa");
            rp1.setTalle("40");
            p1.addRopa(rp1);
            Ropa rp2= new Ropa();
            rp2.setTipo("pantalon");
            rp2.setTalle("42");
            p1.addRopa(rp2);
            
            Ropa rp3= new Ropa();
            rp3.setTipo("blusa");
            rp3.setTalle("35");
            p2.addRopa(rp3);
            Ropa rp4= new Ropa();
            rp4.setTipo("top");
            rp4.setTalle("35");
            p2.addRopa(rp4);
            
            Colectivo col1= new Colectivo();
            col1.setGrupo("600");
            col1.setLinea("605");
            p1.addColectivo(col1);
            Colectivo col2= new Colectivo();
            col2.setGrupo("400");
            col2.setLinea("401");
            p1.addColectivo(col2);
            
            p2.addColectivo(col1);
            p2.addColectivo(col2);
            

            manager.persist(p1);
            manager.persist(p2);
            
            manager.flush();
            manager.getTransaction().commit();

        } catch (Exception e) {

            System.out.println("este es su error: "+e.getMessage());
            manager.getTransaction().rollback();
        }
    }

}
