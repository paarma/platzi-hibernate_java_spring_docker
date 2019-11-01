/**
 * 
 */
package com.platzi.ereservation.negocio.services;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.platzi.ereservation.modelo.Cliente;
import com.platzi.ereservation.negocio.repository.ClienteRepository;

/**
 * Clase para definir los servicios de cliente
 * @author pablo.manquillo
 *
 */
@Service
@Transactional(readOnly = true) //Indica que todos los métodos que no tengan la anotacion "@Transactional" serán tratados como transacción de solo lectura.
public class ClienteService {
	
	private final ClienteRepository clienteRepository;
	
	public ClienteService(ClienteRepository clienteRepository) {
		this.clienteRepository = clienteRepository;
	}
	
	/**
	 * Método para realizar la operación de guardar un cliente.
	 * Como en este caso el clente es nuevo, este objeto NO tiene un ID_CLIENTE (idCli) y se almacenaría como nuevo en la BD.
	 * 
	 * La anotación "@Transactional" se referencia cuando el método hace modificaciones en la BD (insert, update, delete)
	 * @param cliente
	 * @return
	 */
	@Transactional
	public Cliente create(Cliente cliente) {
		return this.clienteRepository.save(cliente);
	}
	
	/**
	 * Método que realiza la operación de actualizar un cliente.
	 * Como en este caso el clente ya existe, este objeto SI tiene un ID_CLIENTE (idCli) y se actualizaría en la BD.
	 * @param cliente
	 * @return
	 */
	@Transactional
	public Cliente update(Cliente cliente) {
		return this.clienteRepository.save(cliente);
	}
	
	/**
	 * Método para realizar la operación de eliminar un cliente
	 * @param cliente
	 */
	@Transactional
	public void delete(Cliente cliente) {
		this.clienteRepository.delete(cliente);
	}
	
	/**
	 * Método para consultar un cliente por si identificación.
	 * @param identificacionCli
	 * @return
	 */
	public Cliente findByIdentificacion(String identificacionCli) {
		return this.clienteRepository.findByIdentificacion(identificacionCli);
	}
	
	public List<Cliente> findAll(){
		return this.clienteRepository.findAll();
	}
	
}
