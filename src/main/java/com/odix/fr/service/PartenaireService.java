package com.odix.fr.service;

import java.util.UUID;

import com.odix.fr.model.Partenaire;

public interface PartenaireService {
	
	public Partenaire addPartenaire(Partenaire partenaire);
	
	public Partenaire editPartenaire(Partenaire partenaire);
	
	public Boolean deletePartenaire(UUID id);
}
