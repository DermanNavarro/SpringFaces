package com.aerolinea.dao;

import com.aerolinea.entidad.Pais;
import org.springframework.stereotype.Component;

@Component("PaisDaoImpl")
public class PaisDaoImpl extends GenericDaoImpl<Pais, Integer> implements PaisDao{
    
}
