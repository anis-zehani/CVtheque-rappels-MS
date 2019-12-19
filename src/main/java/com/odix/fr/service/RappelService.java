package com.odix.fr.service;

import java.util.List;
import java.util.UUID;

import com.odix.fr.model.Projet;
import com.odix.fr.model.Rappel;

public interface RappelService {
	
	public List<Rappel> getAllRappels(UUID idUtilisateur);
	
	public List<Rappel> getAllRappelsByToday(UUID idUtilisateur);
	
	public List<Rappel> getAllRappelsByNext7Days(UUID idUtilisateur);
	
	public List<Rappel> getAllRappelsByProjetAndUtilisateur(Projet projet, UUID idUtilisateur);
	
	public List<Rappel> getAllRappelsByPrioriteAndUtilisateur(String valeurPriorite, UUID idUtilisateur);
	
	public List<Rappel> getAllRappelsByTodayAndAllUsers();
	
	public Rappel getRappel(UUID id);
	
	public Rappel addRappel(Rappel rappel);
	
	public Rappel editRappel(Rappel rappel);
	
	public void deleteRappel(UUID id);
	
	public void deleteAllRappelsByProjet(Projet projet);
	
	public Rappel addFichierToRappel(UUID id, String urlFichier, String nomFichier);

}
