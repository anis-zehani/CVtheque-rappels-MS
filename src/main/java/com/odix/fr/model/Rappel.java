package com.odix.fr.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import lombok.Data;

@Data
@Entity
public class Rappel implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -9007698563424820307L;

	@Id
	@GeneratedValue
	@Column(name = "id", updatable = false, nullable = false, unique=true)
	private UUID id;
	
	@Column
	private boolean remindMe;
	
	@Column(length = 1024)
	private String detailsRappel;
	
	@Column
	private LocalDate dateEcheance;
	
	@Column
	@Enumerated(EnumType.STRING)
	private Priorite priorite;
	
	@Column
	private String urlFichier;
	
	@Column
	private String nomFichier;
	
	@ManyToOne(fetch = FetchType.EAGER, optional = true)
	Projet projet;
	
	// Pour regrouper les rappels par IdUtilisateur : qui a inséré ce rappel
	@ManyToOne(fetch = FetchType.EAGER, optional = true)
	private Utilisateur utilisateur;

	public Rappel() {
		super();
	}

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public boolean getRemindMe() {
		return remindMe;
	}

	public void setRemindMe(boolean remindMe) {
		this.remindMe = remindMe;
	}

	public String getDetailsRappel() {
		return detailsRappel;
	}

	public void setDetailsRappel(String detailsRappel) {
		this.detailsRappel = detailsRappel;
	}

	public LocalDate getDateEcheance() {
		return dateEcheance;
	}

	public void setDateEcheance(LocalDate dateEcheance) {
		this.dateEcheance = dateEcheance;
	}

	public Priorite getPriorite() {
		return priorite;
	}

	public void setPriorite(Priorite priorite) {
		this.priorite = priorite;
	}

	public String getUrlFichier() {
		return urlFichier;
	}

	public void setUrlFichier(String urlFichier) {
		this.urlFichier = urlFichier;
	}

	public String getNomFichier() {
		return nomFichier;
	}

	public void setNomFichier(String nomFichier) {
		this.nomFichier = nomFichier;
	}

	public Projet getProjet() {
		return projet;
	}

	public void setProjet(Projet projet) {
		this.projet = projet;
	}

	public Utilisateur getUtilisateur() {
		return utilisateur;
	}

	public void setUtilisateur(Utilisateur utilisateur) {
		this.utilisateur = utilisateur;
	}
}
