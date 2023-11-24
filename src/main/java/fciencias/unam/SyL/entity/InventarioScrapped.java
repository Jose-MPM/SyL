package fciencias.unam.SyL.entity;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Clase que nos permite representar un Inventario Scrappeado de SyL,
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class InventarioScrapped{
	/* Nombre del producto. */
	private String nombre;
	
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/* Precio del producto. */
    private float precio;
	
	/* Vínculo de imagen. */
    private String pathImagen;

}
