/**
 * 
 */
package com.platzi.ereservation.negocio.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.platzi.ereservation.modelo.Cliente;

/**
 * Interface para definir las operaciones de la BD relacionadas con el cliente
 * @author pablo.manquillo
 *
 */

//<Cliente, String> String hace referencia al ID de la clase Cliente (campo "idCli")
public interface ClienteRepository extends JpaRepository<Cliente, String> {
	
	/**
	 * Busca cliente por su apellido
	 * El nombre del metodo se contruye con la palabra reservada "findBy" seguida del atributo apellido de la clase cliente
	 * @param apellidoCli
	 * @return
	 */
	public List<Cliente> findByApellidoCli(String apellidoCli);
	
	public Cliente findByIdentificacion(String identificacionCli);
	
}
