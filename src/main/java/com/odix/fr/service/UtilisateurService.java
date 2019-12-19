package com.odix.fr.service;

import java.util.UUID;

import com.odix.fr.model.Utilisateur;

public interface UtilisateurService {
	
	public Utilisateur getUtilisateurById(UUID id);

	public Utilisateur addUtilisateur(Utilisateur utilisateur);
	
	public Utilisateur editUtilisateur(Utilisateur utilisateur);
	
	public void deleteUtilisateur(UUID id);
	
}
