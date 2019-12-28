package com.odix.fr.service;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.odix.fr.model.Priorite;
import com.odix.fr.model.Projet;
import com.odix.fr.model.Rappel;
import com.odix.fr.model.Utilisateur;
import com.odix.fr.repository.RappelRepository;
import com.odix.fr.util.Consts;
import com.odix.fr.util.LocalStorageService;


@Service
public class RappelServiceImpl implements RappelService{
	
	private final RappelRepository rappelRepository;
	private final LocalStorageService storageService;
	private final UtilisateurService utilisateurService;

	RappelServiceImpl(RappelRepository rappelRepository, LocalStorageService storageService, UtilisateurService utilisateurService) {
		super();
		this.rappelRepository = rappelRepository;
		this.storageService = storageService;
		this.utilisateurService = utilisateurService;
	}
	
	//Retourne tous les rappels sans filtre par User
	public List<Rappel> getAllRappels(UUID idUtilisateur) {
		
		Utilisateur utilisateur = utilisateurService.getUtilisateurById(idUtilisateur);
		
	    return rappelRepository.findAllByUtilisateur(utilisateur);
	}
	
	//Retourne les rappels de Today par User
	public List<Rappel> getAllRappelsByToday(UUID idUtilisateur) {
		
		Utilisateur utilisateur = utilisateurService.getUtilisateurById(idUtilisateur);
		
		LocalDate dateToday = LocalDate.now(); 
		
	    return rappelRepository.findByToday(dateToday, utilisateur);
	}
	
	//Retourne les rappels des Next 7 Days par User
	public List<Rappel> getAllRappelsByNext7Days(UUID idUtilisateur) {
		
		Utilisateur utilisateur = utilisateurService.getUtilisateurById(idUtilisateur);
		LocalDate dateDebut = LocalDate.now(); 
		LocalDate dateFin = dateDebut.plus(Period.ofDays(7));
		
	    return rappelRepository.findByNext7Days(dateDebut, dateFin, utilisateur);
	}
	
	public List<Rappel> getAllRappelsByProjetAndUtilisateur(Projet projet, UUID idUtilisateur) {
		
		Utilisateur utilisateur = utilisateurService.getUtilisateurById(idUtilisateur);
		
	    return rappelRepository.findByProjetAndUtilisateur(projet, utilisateur);
	}
	
	public List<Rappel> getAllRappelsByPrioriteAndUtilisateur(String valeurPriorite, UUID idUtilisateur){
		
		Utilisateur utilisateur = utilisateurService.getUtilisateurById(idUtilisateur);
		
		//Il faut convertir le String en Enum via valueOf
		return rappelRepository.findByPrioriteAndUtilisateur(Priorite.valueOf(valeurPriorite), utilisateur);
	}
	
	//Retourne tous les rappels de Today : All Users
	public List<Rappel> getAllRappelsByTodayAndAllUsers() {
		
		LocalDate dateToday = LocalDate.now(); 
		
	    return rappelRepository.findByTodayAndAllUsers(dateToday);
	}
	
	public Rappel getRappel(UUID id) {
		return rappelRepository.getOne(id);
	}
	
	//Ajouter un rappel
	public Rappel addRappel(Rappel rappel) 
	{
		if(rappel.getPriorite().toString().equals("Non_Mentionee"))
		{
			rappel.setPriorite(Priorite.Basse);
		}
		
		if(rappel.getProjet().getId() == null)
		{
			//Obligatoire pour @ManyToOne
			rappel.setProjet(null);
		}
	
		if(rappelRepository.findByDetailsRappel(rappel.getDetailsRappel()) == null)
		{
			return rappelRepository.save(rappel);
		}
		return null;
	}
	
	//Affecter un fichier à un rappel (fonction appelée dans Ajout + Update)
	public Rappel addFichierToRappel(UUID id, String urlFichier, String nomFichier) {
			
		if(rappelRepository.existsById(id))
		{
			Rappel rappel = rappelRepository.getOne(id);
				
		//delete ancien fichier : s'il existe dans le cas d'un Update
		if(rappel.getUrlFichier() != null)
		{
			storageService.deleteFichier(rappel.getUrlFichier());
		}

		//update URL fichier avec nouveau nom s'il n'est pas vide
		if(urlFichier.isEmpty() == false)
		{
			rappel.setUrlFichier(urlFichier);
			rappel.setNomFichier(nomFichier);
		}
		
		return rappelRepository.save(rappel);
		
		}
		return null;
		}

	
	//Modifier un rappel
	public Rappel editRappel(Rappel rappel) 
	{
		if(rappel.getProjet().getId() == null)
		{
			//Obligatoire pour @ManyToOne
			rappel.setProjet(null);
		}
		
		if(rappelRepository.existsById(rappel.getId()))
		{
			Rappel rappelToEdit = rappelRepository.getOne(rappel.getId());
			
			rappelToEdit.setDateEcheance(rappel.getDateEcheance());
			rappelToEdit.setDetailsRappel(rappel.getDetailsRappel());
			rappelToEdit.setPriorite(rappel.getPriorite());
			rappelToEdit.setProjet(rappel.getProjet());
			rappelToEdit.setRemindMe(rappel.getRemindMe());
			
			//On modifie tout sauf urlFichier : il est géré ailleurs afin d'éviter les erreurs
			return rappelRepository.save(rappelToEdit);
		}
		return null;
	}
	
	//Supprimer un rappel
	public void deleteRappel(UUID id) 
	{
		if(rappelRepository.existsById(id))
		{
			Rappel rappel = rappelRepository.getOne(id);
			
			try
			{
				//On supprime la pièce jointe si elle existe
				if(rappel.getUrlFichier()!=null && rappel.getUrlFichier().startsWith(Consts.rootLocationFichierRappel.toString()+"/".replace("\"", ""))==true)
				{
					storageService.deleteFichier(Consts.rootLocation+rappel.getUrlFichier());
				}
			}
			catch(NoSuchElementException e) 
			{
				System.out.print("Erreur durant deleteRappel :"+e);
			}
			
			rappelRepository.deleteById(id);
		}
	}
	
	public void deleteAllRappelsByProjet(Projet projet) {
			
			rappelRepository.deleteAllRappelsByProjet(projet);
	}

}
