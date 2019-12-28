package com.odix.fr.messaging;

import java.io.IOException;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.odix.fr.model.Partenaire;
import com.odix.fr.service.UtilisateurService;

@Service
public class PartenaireConsumers {

	private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();
	
	@Autowired
	UtilisateurService utilisateurService;
	/***** Utilisateur de DTYPE Partenaire *****/
	
    @KafkaListener(topics = "add-partenaire-topic")
    public void addPartenaireConsumer(String message) throws IOException {
        System.out.print(String.format("#### -> addPartenaireConsumer : Rappels-MS  -> %s", message +"\n"));

        try{
        	OBJECT_MAPPER.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        	Partenaire partenaire = OBJECT_MAPPER.readValue(message, Partenaire.class);
        	
            //this.partenaireService.addPartenaire(partenaire);
        	this.utilisateurService.addUtilisateur(partenaire);
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    @KafkaListener(topics = "edit-partenaire-topic")
    public void editPartenaireConsumer(String message) throws IOException {
        System.out.print(String.format("#### -> editPartenaireConsumer : Rappels-MS -> %s", message +"\n"));

        try{
        	OBJECT_MAPPER.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        	Partenaire partenaire = OBJECT_MAPPER.readValue(message, Partenaire.class);
        	
            //this.partenaireService.editPartenaire(partenaire);
            this.utilisateurService.editUtilisateur(partenaire);
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    
    @KafkaListener(topics = "delete-partenaire-topic")
    public void deletePartenaireConsumer(String message) throws IOException {
        System.out.print(String.format("#### -> deletePartenaireConsumer : Rappels-MS -> %s", message +"\n"));

        try{
        	//this.partenaireService.deletePartenaire(UUID.fromString(message));
        	this.utilisateurService.deleteUtilisateur(UUID.fromString(message));
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
