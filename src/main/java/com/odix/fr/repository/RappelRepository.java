package com.odix.fr.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.odix.fr.model.Priorite;
import com.odix.fr.model.Projet;
import com.odix.fr.model.Rappel;
import com.odix.fr.model.Utilisateur;

@Repository
public interface RappelRepository extends JpaRepository<Rappel, UUID> {
	
	List<Rappel> findAllByUtilisateur(@Param("utilisateur") Utilisateur utilisateur);
	
	@Query("SELECT r FROM Rappel r where r.dateEcheance =:date AND r.utilisateur =:utilisateur")
	List<Rappel> findByToday(@Param("date") LocalDate date, @Param("utilisateur") Utilisateur utilisateur);
	
	@Query("SELECT r FROM Rappel r where r.dateEcheance =:date AND r.remindMe = TRUE ")
	List<Rappel> findByTodayAndAllUsers(@Param("date") LocalDate date);
	
	@Query("SELECT r FROM Rappel r WHERE r.dateEcheance > :dateDebut AND r.dateEcheance < :dateFin AND r.utilisateur =:utilisateur")
	List<Rappel> findByNext7Days(@Param("dateDebut") LocalDate dateDebut, @Param("dateFin") LocalDate dateFin, @Param("utilisateur") Utilisateur utilisateur);
	
	List<Rappel> findByProjetAndUtilisateur(@Param("projet") Projet projet, @Param("utilisateur") Utilisateur utilisateur);
	
	List<Rappel> findByPrioriteAndUtilisateur(@Param("priorite") Priorite priorite, @Param("utilisateur") Utilisateur utilisateur);
	
	Rappel findByDetailsRappel(@Param("detailsRappel") String detailsRappel);
	
	//Native Query = true : Supprimer tous les rappels appartenant à un projet
	@Modifying
	@Transactional
	@Query(value = 
			"DELETE FROM Rappel r WHERE "
			+ "r.projet_id = ?1"
			, nativeQuery = true)
	void deleteAllRappelsByProjet(@Param("projet") Projet projet);
	
}
