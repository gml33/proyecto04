package com.gml.gestionCursos.entities;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name="cursos")
@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class Curso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 128, nullable = false)
    private String titulo;

    @Column(length = 256, nullable = true)
    private String descripcion;

    @Column(nullable = false)
    private int nivel;

    @Column(name="estado_publicacion")
    private boolean isPublicado;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public int getNivel() {
		return nivel;
	}

	public void setNivel(int nivel) {
		this.nivel = nivel;
	}

	public boolean isPublicado() {
		return isPublicado;
	}

	public void setPublicado(boolean isPublicado) {
		this.isPublicado = isPublicado;
	}
    
    
}
