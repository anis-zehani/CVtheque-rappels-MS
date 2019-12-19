package com.odix.fr.service;

import java.util.UUID;

import org.springframework.stereotype.Service;

import com.odix.fr.model.Administrateur;
import com.odix.fr.repository.AdministrateurRepository;

@Service
public class AdministrateurServiceImpl implements AdministrateurService{
	
	public final AdministrateurRepository administrateurRepository;
	
	public AdministrateurServiceImpl(AdministrateurRepository administrateurRepository) {
		super();
		this.administrateurRepository = administrateurRepository;
	}
	
	//Ajouter un Administrateur
	public Administrateur addAdministrateur(Administrateur administrateur) {
			return administrateurRepository.save(administrateur);
	}

	//Modifier un Administrateur
	public Administrateur editAdministrateur(Administrateur administrateur) {
			if(administrateurRepository.existsById(administrateur.getId()) && 
			   administrateur.getIdentite() != "" && 
			   administrateur.getEmail() != "") {
			return administrateurRepository.save(administrateur);
		}
		return null;
	}

	//Supprimer un Administrateur
	public void deleteAdministrateur(UUID id) {	
		if(administrateurRepository.existsById(id)){
			//On supprime la ligne de la base
			administrateurRepository.deleteById(id);
			}
	}	

}
