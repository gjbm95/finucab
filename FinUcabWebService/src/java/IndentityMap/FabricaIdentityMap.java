/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IndentityMap;

/**
 *
 * @author Ramon
 */
public class FabricaIdentityMap {
    
    public static IdentityMap obtenerIdentityMap(){
       return IdentityMap.getInstance();
    }   
}
