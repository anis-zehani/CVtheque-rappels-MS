package com.odix.fr.service;

import java.util.List;
import java.util.UUID;

import com.odix.fr.model.Projet;

public interface ProjetService {
	
	public List<Projet> getAllProjets(UUID idUtilisateur);
	
	public Projet getProjet(UUID id);
	
	public Projet addProjet(Projet projet);
	
	public Projet editProjet(Projet projet);
	
	public void deleteProjet(UUID id);

}
