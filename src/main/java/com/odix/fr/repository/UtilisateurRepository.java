package com.odix.fr.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.odix.fr.model.Utilisateur;

@Repository
public interface UtilisateurRepository extends JpaRepository<Utilisateur, UUID> {
	
	Utilisateur findUtilisateurById(@Param("id") UUID id);
}
