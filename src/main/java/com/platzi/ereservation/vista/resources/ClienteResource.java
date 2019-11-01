/**
 * 
 */
package com.platzi.ereservation.vista.resources;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.platzi.ereservation.modelo.Cliente;
import com.platzi.ereservation.negocio.services.ClienteService;
import com.platzi.ereservation.vista.resources.vo.ClienteVO;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

/**
 * Clase que representa el servicio web de cliente
 * 
 * @author pablo.manquillo
 *
 */
@RestController // Define que la clase será tratada como un servicio web
@RequestMapping("/api/cliente")
@Api(tags = "cliente") //Anotación para swagger (documentación). Se le fija un tag para etiquetar esta clase como "cliente"
public class ClienteResource {

	private final ClienteService clienteService;

	public ClienteResource(ClienteService clienteService) {
		this.clienteService = clienteService;
	}

	/**
	 * ResponseEntity hace referencia al obgeto generico de tipo Cliente que retorna
	 * el webService
	 * 
	 * @return
	 */
	@PostMapping // Representa operación de creación
	@ApiOperation(value = "Crear Cliente", notes = "Servicio para crear un nuevo cliente") //Documentación swagger
	@ApiResponses(value = {@ApiResponse(code = 201, message = "Cliente creado correctamente"),
		@ApiResponse(code = 400, message = "Solicitud inválida")})
	public ResponseEntity<Cliente> createCliente(@RequestBody ClienteVO clienteVo) {
		Cliente cliente = new Cliente();

		cliente.setNombreCli(clienteVo.getNombreCli());
		cliente.setApellidoCli(clienteVo.getApellidoCli());
		cliente.setDireccionCli(clienteVo.getDireccionCli());
		cliente.setTelefonoCli(clienteVo.getTelefonoCli());
		cliente.setEmailCli(clienteVo.getEmailCli());

		return new ResponseEntity<>(this.clienteService.create(cliente), HttpStatus.CREATED);
	}

	/**
	 * Este metodo referencia la anotacion: "@PutMapping" para indicar operación de
	 * actualización. Resibe un parametro el cual es la identificación del cliente
	 * 
	 * @param identificacion
	 * @return
	 */
	@PutMapping("/{identificacion}")
	@ApiOperation(value = "Actualizar Cliente", notes = "Servicio para actualizar un cliente") //Documentación swagger
	@ApiResponses(value = {@ApiResponse(code = 201, message = "Cliente actualizado correctamente"),
			@ApiResponse(code = 404, message = "Cliente no encontrado")})
	public ResponseEntity<Cliente> updateCliente(@PathVariable("identificacion") String identificacion,
			ClienteVO clienteVo) {
		Cliente cliente = this.clienteService.findByIdentificacion(identificacion);

		if (cliente == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} else {
			cliente.setNombreCli(clienteVo.getNombreCli());
			cliente.setApellidoCli(clienteVo.getApellidoCli());
			cliente.setDireccionCli(clienteVo.getDireccionCli());
			cliente.setTelefonoCli(clienteVo.getTelefonoCli());
			cliente.setEmailCli(clienteVo.getEmailCli());
		}

		return new ResponseEntity<>(this.clienteService.create(cliente), HttpStatus.OK);
	}

	/**
	 * Este metodo referencia la anotacion: "@DeleteMapping" para indicar operación
	 * de eliminar. Resibe un parametro el cual es la identificación del cliente
	 * 
	 * @param identificacion
	 * @return
	 */
	@DeleteMapping("/{identificacion}")
	@ApiOperation(value = "Eliminar Cliente", notes = "Servicio para eliminar un cliente") //Documentación swagger
	@ApiResponses(value = {@ApiResponse(code = 201, message = "Cliente eliminado correctamente"),
			@ApiResponse(code = 404, message = "Cliente no encontrado")})
	public void removeCliente(@PathVariable("identificacion") String identificacion) {
		Cliente cliente = this.clienteService.findByIdentificacion(identificacion);

		if (cliente != null) {
			this.clienteService.delete(cliente);
		}
	}

	/**
	 * Este metodo referencia la anotacion: "@GetMapping" para indicar operación de
	 * obtener datos.
	 * 
	 * @param identificacion
	 * @return
	 */
	@GetMapping
	@ApiOperation(value = "Listar Clientes", notes = "Servicio para listar todos los clientes") //Documentación swagger
	@ApiResponses(value = {@ApiResponse(code = 201, message = "Clientes encontrados"),
			@ApiResponse(code = 404, message = "Clientes no encontrados")})
	public ResponseEntity<List<Cliente>> findAll() {
		return ResponseEntity.ok(this.clienteService.findAll());
	}
}
