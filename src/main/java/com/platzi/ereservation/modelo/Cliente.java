/**
 * 
 */
package com.platzi.ereservation.modelo;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import lombok.Data;

/**
 * Clase que representa la tabla Cliente
 * @author pablo.manquillo
 *
 */

@Data // @Data: Esta notación de lombok contruye automaticamente los getters y setters
@Entity
@Table(name = "cliente")
//Query en formato JPQL definido para buscar un cliente por identificacion. (el número "1" indica que es el primer atributo)
@NamedQuery(name = "Cliente.findByIdentificacion", query="Select c from Cliente c where c.identificacionCli = ?1")
public class Cliente {
	
	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid2")
	private String idCli;
	
	private String nombreCli;
	private String apellidoCli;
	private String identificacionCli;
	private String direccionCli;
	private String telefonoCli;
	private String emailCli;
	
	@OneToMany(mappedBy = "cliente") //Representa una llave foranea
	private Set<Reserva> reservas;

	public Cliente() {

	}

}
