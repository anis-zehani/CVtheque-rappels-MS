package com.odix.fr.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.odix.fr.model.Rappel;
import com.odix.fr.service.ProjetService;
import com.odix.fr.service.RappelService;
import com.odix.fr.util.LocalStorageService;


@RestController
@RequestMapping("/api/rappel")
public class RappelController {
	
	@Autowired
	LocalStorageService storageService;
	
	@Autowired
	private final RappelService rappelService;
	
	@Autowired
	private final ProjetService projetService;
	
	RappelController(RappelService rappelService, ProjetService projetService) {
		this.rappelService = rappelService;
		this.projetService = projetService;
	}

	// Tous les rappels par idUtilisateur
	@GetMapping("/allRappelsByIdUtilisateur/{idUtilisateur}")
	public List<Rappel> getAlllRappelsByIdUtilisateur(@PathVariable UUID idUtilisateur) {
		
		List<Rappel> rappels = rappelService.getAllRappels(idUtilisateur);
	    return rappels;
	}
	
	@GetMapping("/allRappelsByToday/{idUtilisateur}")
	public List<Rappel> getAllRappelsByToday(@PathVariable UUID idUtilisateur) {
		
		List<Rappel> rappels = rappelService.getAllRappelsByToday(idUtilisateur);
		return rappels;
	}
	
	@GetMapping("/allRappelsByNext7Days/{idUtilisateur}")
	public List<Rappel> getAllRappelsByNext7Days(@PathVariable UUID idUtilisateur) {
		
		List<Rappel> rappels = rappelService.getAllRappelsByNext7Days(idUtilisateur);
		return rappels;
	}
	
	@GetMapping("/allRappelsByProjet/{idProjet}/{idUtilisateur}")
	public List<Rappel> getAllRappelsByProjet(@PathVariable UUID idProjet, @PathVariable UUID idUtilisateur) {
		
		List<Rappel> rappels = rappelService.getAllRappelsByProjetAndUtilisateur(projetService.getProjet(idProjet), idUtilisateur);
		return rappels;
	}
	
	@GetMapping("/allRappelsByPriorite/{valeurPriorite}/{idUtilisateur}")
	public List<Rappel> getAllRappelsByPriorite(@PathVariable String valeurPriorite, @PathVariable UUID idUtilisateur) {
		
		List<Rappel> rappels = rappelService.getAllRappelsByPrioriteAndUtilisateur(valeurPriorite, idUtilisateur);
	    return rappels;
	}
	
	@GetMapping("{id}")
	public Rappel getRappel(@PathVariable UUID id) {
		return rappelService.getRappel(id);
	}
	
	@PostMapping()
	public Rappel addRappel(@Valid @RequestBody Rappel rappel) {
		return rappelService.addRappel(rappel);
	}
	
	//Ajouter un Fichier à un rappel :
	@PostMapping("addFichier/{id}")
	public Rappel addFichier(@PathVariable UUID id, @RequestParam("file") MultipartFile file) {
	
	//le Fichier est placé sur le serveur : on récupére un ArrayList avec le nom original + le nom modifié
	ArrayList<String> files = storageService.addFichierRappel(file);
	try {
		String urlFichier =  files.get(0);
		String nomFichier =  files.get(1);
		//le Fichier est affecté au rappel via son id
		return rappelService.addFichierToRappel(id, urlFichier, nomFichier);
	}catch (Exception e) {
		return null;
	}
	}
	
	@PutMapping()
	public Rappel editRappel(@Valid @RequestBody Rappel rappel) {
		return rappelService.editRappel(rappel);
	}
	
	@DeleteMapping("{id}")
	public void deleteRappel(@PathVariable UUID id) {
		rappelService.deleteRappel(id);
	}

}
