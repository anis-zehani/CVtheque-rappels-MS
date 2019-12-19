package com.odix.fr.service;

import java.util.UUID;

import com.odix.fr.model.Administrateur;

public interface AdministrateurService {

	public Administrateur addAdministrateur(Administrateur administrateur);
	
	public Administrateur editAdministrateur(Administrateur administrateur);
	
	public void deleteAdministrateur(UUID id);
}
