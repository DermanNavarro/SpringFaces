package com.aerolinea.service;

import org.springframework.stereotype.Service;

@Service("ServiceHolaSpring")
public class ServiceHolaSpring {
    
    public String test(){
        return "hola desde el sevicio spring";
    }
}
