package com.andrewkravets.service;

import com.andrewkravets.model.Patient;

import javax.ejb.Remote;
import java.util.List;

/**
 * Created by Andrew Kravets.
 * Date: 1/1/14.
 * Url: andrewkravets.com
 */
@Remote
public interface PatientService {
    public Patient findPatientById(Long id);
    public List<Patient> getAllPatients();
    public void removePatient(Patient patient);
    public void savePatient(Patient patient);
}
