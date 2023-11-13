package fciencias.unam.SyL.entity;

import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
/**
 * Clase que nos permite representar un INVENTARIO de SyL,
 * es decir una lista de productos o ingredientes.
 */
@Entity
@Table(name="inventario")
public class Inventario{

	@Id
	@Column(name = "idIngrediente", unique=true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idIngrediente;
	
	@Column(name="nombre")
	@NotNull(message="El nombre es requerido")
	private String nombre;
	
	/* Tipo del producto.*/
	@ManyToOne
    @JoinColumn(name = "tipoProducto", referencedColumnName = "idTipoProducto")
    private TipoProducto tipoProducto;
    //private String productType;
	
	@NotNull(message="La fecha de adquisicion es requerida")
    @Column(name="adquisicion")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date adquisicion;
    
	@NotNull(message="La fecha de expiracion es requerida")
    @Column(name="expiracion")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date expiracion;
	
	/* Cantidad del prodcuto. */
	@Column(name="cantidad")
	@NotNull(message="La cantidad es requerido")
	@Min(value=0, message="La cantidad debe de ser positivo")
    private int cantidad;
	
	@Column(name="medida")
	@NotNull(message="La medida es requerida")
    private float medida;
	//con que etiqueta podemos acotar a que sean los gramos, etc
    
    /* Precio del producto. */
	@Column(name="precio")
	@NotNull(message="El precio es requerido")
	@Min(value=1, message="El precio debe de ser positivo, no puede ser 0")
    private float precio;
	//@Max(value=500, message="El precio no puede ser más de 500")
	
	/* Descripcion del prodcuto. */
	@Column(name="descripcion")
	private String descripcion;
	
	/* Comentario del prodcuto.
	 * Lo acotamos a 1, no queremos una lista. */
	@Column(name="comentario")
	private String Comentario;
	
	/* Provedor del prodcuto. */
	@Column(name="provedor")
    private String Provedor;

}
