package com.aerolinea.control;

import com.aerolinea.service.ServiceHolaSpring;
import java.io.Serializable;

/*JSF (Faces)*/
import javax.faces.bean.ManagedBean;                            
import javax.faces.bean.ManagedProperty;
//import javax.faces.bean.RequestScoped;

/*Spring*/                   
import org.springframework.beans.factory.annotation.Autowired;  
import org.springframework.context.annotation.Scope;           
import org.springframework.stereotype.Controller; 

/*CDI*/  
//import javax.enterprise.context.RequestScoped;
//import javax.inject.Named;

/*JSF (Faces)*/
//@ManagedBean                                                  
//@RequestScoped

/*Spring*/
@Controller                                                     
@Scope("request")

/*CDI*/
//@Named
//@RequestScoped
public class ControlHola implements Serializable{
//    @ManagedProperty("#{ServiceHolaSpring}")                  //Faces
    @Autowired                                                  //Spring y CDI:@Inject
    private ServiceHolaSpring serviceHolaSpring;
    
    public String saludar(){
        return "hola desde el managed bean";
    }
    public ServiceHolaSpring getServiceHolaSring(){
        return serviceHolaSpring;
    }
    public void setServiceHolaSpring(ServiceHolaSpring serviceHolaSpring){
        this.serviceHolaSpring = serviceHolaSpring;
    }
    public String hola(){
        return serviceHolaSpring.test();
    }
}
