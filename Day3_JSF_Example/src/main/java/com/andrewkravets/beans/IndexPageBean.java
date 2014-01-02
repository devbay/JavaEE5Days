package com.andrewkravets.beans;

import com.andrewkravets.model.Patient;
import com.andrewkravets.service.PatientService;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by Andrew Kravets.
 * Date: 1/2/14.
 * Url: andrewkravets.com
 */
@ManagedBean
@ViewScoped
public class IndexPageBean {
    @EJB
    private PatientService patientService;

    public List<Patient> getPatients(){
        return patientService.getAllPatients();
    }

    public void editPatient(Patient patient) throws IOException {
        FacesContext context = FacesContext.getCurrentInstance();
        context.getExternalContext().getSessionMap().put("patient", patient);

        HttpServletResponse response = (HttpServletResponse)context.getExternalContext().getResponse();
        response.sendRedirect("edit.xhtml");
    }

    public void removePatient(Patient patient) {
        patientService.removePatient(patient);
    }
}
