package com.odix.fr.model;

import java.io.Serializable;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

import lombok.Data;


@Data
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(
    name="DTYPE",
    discriminatorType=DiscriminatorType.STRING
    )
public class Utilisateur implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 3635172837730319055L;

	@Id
	//Attention : ne doit pas être Generated car il doit avoir le même UUID que le MS Maître
	@Column(name = "id", updatable = false, nullable = false, unique=true)
	private UUID id;

	@Column(unique = true)
	private String identite;
	
    @Column(unique = true)
	private String email;

	@Column(length = 1024)
	private String urlPhoto;


	public Utilisateur() {
		super();
	}

	public String getIdentite() {
		return identite;
	}

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public void setIdentite(String identite) {
		this.identite = identite;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getUrlPhoto() {
		return urlPhoto;
	}

	public void setUrlPhoto(String urlPhoto) {
		this.urlPhoto = urlPhoto;
	}
}
