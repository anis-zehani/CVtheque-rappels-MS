package com.odix.fr.messaging;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.odix.fr.model.Administrateur;
import com.odix.fr.service.UtilisateurService;

@Service
public class AdministrateurConsumers {

	private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();
	
	@Autowired
	UtilisateurService utilisateurService;
	/***** Utilisateur de DTYPE Administrateur *****/
	
    @KafkaListener(topics = "add-administrateur-topic")
    public void addAdministrateurConsumer(String message) throws IOException {
        System.out.print(String.format("#### -> addAdministrateurConsumer : Rappels-MS -> %s", message +"\n"));

        try{
        	OBJECT_MAPPER.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        	Administrateur administrateur = OBJECT_MAPPER.readValue(message, Administrateur.class);
        	
            //this.administrateurService.addAdministrateur(administrateur);
            this.utilisateurService.addUtilisateur(administrateur);
            
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
