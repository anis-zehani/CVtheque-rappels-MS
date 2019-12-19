package com.odix.fr.service;

import java.util.UUID;

import org.springframework.stereotype.Service;

import com.odix.fr.model.Partenaire;
import com.odix.fr.repository.PartenaireRepository;

@Service
public class PartenaireServiceImpl implements PartenaireService{

	private final PartenaireRepository partenaireRepository;

	PartenaireServiceImpl(PartenaireRepository partenaireRepository) {
		super();
		this.partenaireRepository = partenaireRepository;
	}


	//Ajouter un partenaire
	public Partenaire addPartenaire(Partenaire partenaire) {	
		return partenaireRepository.save(partenaire);
	}

	//Modifier un partenaire
	public Partenaire editPartenaire(Partenaire partenaire) {
		if(partenaireRepository.existsById(partenaire.getId())) {
			return partenaireRepository.save(partenaire);
		}
		return null;
	}

	
	//Supprimer un partenaire
	public Boolean deletePartenaire(UUID id) {
		if(partenaireRepository.existsById(id))
		{
			try
			{
				partenaireRepository.deleteById(id);
				return true;
			}
			catch(Exception e) 
			{
				System.out.print("Erreur durant deletePartenaire :"+e);
				return false;	
			}
		}
		return null;
	}
}
