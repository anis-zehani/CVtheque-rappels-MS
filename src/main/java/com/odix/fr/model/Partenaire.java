package com.odix.fr.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;

import lombok.Data;

@Data
@Entity
@DiscriminatorValue(value="ROLE_PARTENAIRE")
public class Partenaire extends Utilisateur implements Serializable {
	
	private static final long serialVersionUID = -2652463772687522895L;

	/**Les champs remplis pour le partenaire sont uniquement :
	 * 
	 * Identite
	 * telephone
	 * email
	 * poste_occupe
	 * description_detaillee
	 * urlPhoto
	 * login
	 * password
	 * entreprise : @ManyToOne
	 * etatPartenaire : Actif/Inactif
	 * utilisateur : @ManyToOne
	 */
	
	@Column
	@Enumerated(EnumType.STRING)
	private Etat etatPartenaire;
	
	// Pour regrouper les partenaires par IdUtilisateur : qui a inséré ce partenaire (pour le moment c'est l'Administrateur)
	@ManyToOne(fetch = FetchType.LAZY, optional = true)
	private Utilisateur utilisateur;
	
	
	public Partenaire() {
		super();
	}

	public Etat getEtatPartenaire() {
		return etatPartenaire;
	}

	public void setEtatPartenaire(Etat etatPartenaire) {
		this.etatPartenaire = etatPartenaire;
	}

	public Utilisateur getUtilisateur() {
		return utilisateur;
	}

	public void setUtilisateur(Utilisateur utilisateur) {
		this.utilisateur = utilisateur;
	}

}
