/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import java.util.Set;
import javax.ws.rs.core.Application;

/**
 *
 * @author AlejandroNegrin
 */
@javax.ws.rs.ApplicationPath("webresources")
public class ApplicationConfig extends Application {

    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> resources = new java.util.HashSet<>();
        addRestResourceClasses(resources);
        return resources;
    }

    /**
     * Do not modify addRestResourceClasses() method.
     * It is automatically populated with
     * all resources defined in the project.
     * If required, comment out calling this method in getClasses().
     */
    private void addRestResourceClasses(Set<Class<?>> resources) {
        resources.add(Services.Modulo1Resource.class);
        resources.add(Services.Modulo1sResource.class);
        resources.add(Services.Modulo2Resource.class);
        resources.add(Services.Modulo2sResource.class);
        resources.add(Services.Modulo3Resource.class);
        resources.add(Services.Modulo3sResource.class);
        resources.add(Services.Modulo4Resource.class);
        resources.add(Services.Modulo4sResource.class);
        resources.add(Services.Modulo5Resource.class);
        resources.add(Services.Modulo5sResource.class);
        resources.add(Services.Modulo6Resource.class);
        resources.add(Services.Modulo6sResource.class);
    }
    
}
