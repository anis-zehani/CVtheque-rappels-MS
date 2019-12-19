package com.odix.fr.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.odix.fr.model.Projet;
import com.odix.fr.model.Utilisateur;

@Repository
public interface ProjetRepository extends JpaRepository<Projet, UUID> {
	
	Projet findByNomProjet(@Param("nomProjet") String nomProjet);
	
	List<Projet> findAllByUtilisateur(@Param("utilisateur") Utilisateur utilisateur);
}
