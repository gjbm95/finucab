/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PruebasModulo3;

import BaseDatosDAO.DAOPresupuesto;
import BaseDatosDAO.FabricaDAO;
import Dominio.Entidad;
import Dominio.ListaEntidad;
import Dominio.Presupuesto;
import Dominio.SimpleResponse;
import Exceptions.Presupuesto.AgregarPresupuestoException;
import Exceptions.Presupuesto.ConsultarPresupuestoException;
import Exceptions.Presupuesto.EliminarPresupuestoException;
import Exceptions.Presupuesto.ListarPresupuestoException;
import Exceptions.Presupuesto.ModificarPresupuestoException;
import Exceptions.Presupuesto.VerificarNombreException;
import Logica.Comando;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;

/**
 *
 * @author Junior
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class PruebasModulo3 {
    
    private Presupuesto p;
    private DAOPresupuesto dao;
    private Comando c;
    private static int idPresupuesto;
    private Entidad resultado;
    
    public PruebasModulo3() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        p = new Presupuesto("test presupuesto", Double.parseDouble("10000"), "Recurrente" , 2, 1, "2");
        dao = FabricaDAO.instanciasDAOPresupuesto();
    }
    
    @After
    public void tearDown() {
        p = null;
        resultado = null;
        
    }

    @Test
    public void atestAgregarPresupuesto() {
        try {
            System.out.println("Test agregar");
            SimpleResponse res = (SimpleResponse) dao.agregar(p);
            //Entidad resultado = dao.agregar(p);
            assertEquals(res.getId(), 1);
            idPresupuesto = res.getStatus();
            System.out.println(idPresupuesto);
            //Entidad otro = dao.agregar(null);
            //assertEquals(otro.getId(), 2);
        } catch (AgregarPresupuestoException ex) {
            Logger.getLogger(PruebasModulo3.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Test
    public void btestModificarPresupuesto(){
        try {
            System.out.println("Test modificar");
            p.setNombre("otro nombre");
            p.setDuracion(4);
            p.setMonto(Double.valueOf("5000"));
            p.setId(idPresupuesto);
            System.out.println(idPresupuesto);
            resultado = dao.modificar(p);
            assertEquals(resultado.getId(), 1);
        } catch (ModificarPresupuestoException ex) {
            Logger.getLogger(PruebasModulo3.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Test
    public void ctestEliminarPresupuesto(){
        try {
            System.out.println("Test eliminar");
            SimpleResponse res = (SimpleResponse) dao.agregar(p);
            resultado = dao.eliminarPresupuesto(res.getStatus());
            assertEquals(resultado.getId(),1);
        } catch (EliminarPresupuestoException ex) {
            Logger.getLogger(PruebasModulo3.class.getName()).log(Level.SEVERE, null, ex);
        } catch (AgregarPresupuestoException ex) {
            Logger.getLogger(PruebasModulo3.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Test 
    public void testConsultar(){
        try {
            System.out.println("Test consultar");
            Entidad presupuesto;
            SimpleResponse res = (SimpleResponse) dao.agregar(p);
            idPresupuesto = res.getStatus();
            System.out.println(idPresupuesto);
            presupuesto = dao.consultar(idPresupuesto);
            assertEquals(presupuesto.getId(), idPresupuesto);
        } catch (AgregarPresupuestoException ex) {
            Logger.getLogger(PruebasModulo3.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ConsultarPresupuestoException ex) {
            Logger.getLogger(PruebasModulo3.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Test
    public void aatestConsultarTodos(){
        try {
            System.out.println("Test consultar todos");
            Presupuesto pr = p;
            SimpleResponse res = (SimpleResponse) dao.agregar(p);
            SimpleResponse res1 = (SimpleResponse) dao.agregar(pr);
            ListaEntidad lista;
            lista = dao.consultarTodos(1);
            ArrayList<Entidad> lista1 =lista.getLista();
            Entidad resp =null;
            Entidad resp1 = null;
            System.out.println("id 1 "+res.getStatus()+res1.getStatus());
            for (int i = 0; i < lista1.size(); i++){
                System.out.println(" lista "+lista1.get(i).getId());
                if(res.getStatus() == lista1.get(i).getId()){
                resp =  lista1.get(i);
                }
                if(res1.getStatus() == lista1.get(i).getId()){
                    resp1 =  lista1.get(i);
                }
            }
            assertEquals(resp.getId(), res.getStatus());
            assertEquals(resp1.getId(), res1.getStatus());
        } catch (ListarPresupuestoException ex) {
            Logger.getLogger(PruebasModulo3.class.getName()).log(Level.SEVERE, null, ex);
        } catch (AgregarPresupuestoException ex) {
            Logger.getLogger(PruebasModulo3.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    @Test
    public void testVerificar(){
        try {
            System.out.println("Test verificar");
            SimpleResponse rs = (SimpleResponse) dao.agregar(p);
            Entidad e = dao.verificarNombre(p.getNombre());
            assertEquals(e.getId(), 1);
        } catch (AgregarPresupuestoException ex) {
            Logger.getLogger(PruebasModulo3.class.getName()).log(Level.SEVERE, null, ex);
        } catch (VerificarNombreException ex) {
            Logger.getLogger(PruebasModulo3.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Test(expected = AgregarPresupuestoException.class)
    public void testExceptionAgregar() throws AgregarPresupuestoException{
        System.out.println("Test Agregar Exception");
        Entidad e = dao.agregar(null);
               
    }
    
    @Test
    public void ztestFailConsultar(){
        try {
            System.out.println("Test fail consultar");
            Entidad ef = dao.consultar(2500);
            assertEquals(ef.getId(), 0);
        } catch (ConsultarPresupuestoException ex) {
            //Logger.getLogger(PruebasModulo3.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Test
    public void testFailEliminar() {
        
        try {
            System.out.println("Test Fail Eliminar ");
            Entidad e = dao.eliminarPresupuesto(1000);
            assertEquals(e.getId(), 0);
        } catch (EliminarPresupuestoException ex) {
            //Logger.getLogger(PruebasModulo3.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    @Test
    public void testFailListar() throws ListarPresupuestoException{
        System.out.println("Test Listar Exception");
        ListaEntidad comp = null;
        ArrayList<Entidad> lista = new ArrayList<>();
        ListaEntidad e = dao.consultarTodos(2000);
        
        assertEquals(e.getLista(),lista);
    }
    
    @Test
    public void testFailModificar(){
        try {
            Presupuesto pr = p;
            pr.setId(2000);
            Entidad resultado = dao.modificar(p);
            assertEquals(resultado.getId(), 0);
        } catch (ModificarPresupuestoException ex) {
            Logger.getLogger(PruebasModulo3.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Test(expected = EliminarPresupuestoException.class)
    public void testExceptionEliminar() throws EliminarPresupuestoException {
        System.out.println("Test Exception Eliminar");
        Entidad e = dao.eliminarPresupuesto(0);
        
    }
    
}
