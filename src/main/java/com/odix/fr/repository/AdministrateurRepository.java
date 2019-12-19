package com.odix.fr.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.odix.fr.model.Administrateur;

@Repository
public interface AdministrateurRepository extends JpaRepository<Administrateur, UUID> {

}
