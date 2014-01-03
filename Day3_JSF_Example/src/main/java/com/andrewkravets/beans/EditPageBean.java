package com.andrewkravets.beans;

import com.andrewkravets.model.Patient;
import com.andrewkravets.model.Pills;
import com.andrewkravets.service.PatientService;
import org.primefaces.model.DualListModel;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Andrew Kravets.
 * Date: 1/2/14.
 * Url: andrewkravets.com
 */
@ManagedBean
@ViewScoped
public class EditPageBean {

    @EJB
    private PatientService patientService;

    private Patient patient;

    private DualListModel<Pills> pillsDualListModel;

    private Converter pillsConverter;

    @PostConstruct
    public void init() {

        List<Pills> pillsList = new ArrayList<Pills>();
        pillsList.add(new Pills("Aspirin"));
        pillsList.add(new Pills("Vicodin"));
        pillsList.add(new Pills("Abatacept"));
        pillsList.add(new Pills("Gadodiamide"));
        pillsList.add(new Pills("Asparcam"));

        patient = (Patient) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("patient");
        if (patient == null) {
            patient = new Patient();
        }

        pillsDualListModel = new DualListModel<Pills>(pillsList, patient.getPatientsPills()==null ? new ArrayList<Pills>() : patient.getPatientsPills());

        pillsConverter = new Converter() {
            @Override
            public Object getAsObject(FacesContext context, UIComponent component, String value) {
                for (Pills p : getPillsDualListModel().getSource()) {
                    if (p.getTitle().equalsIgnoreCase(value)) {
                        return p;
                    }
                }
                return null;
            }

            @Override
            public String getAsString(FacesContext context, UIComponent component, Object value) {
                return (String) value;
            }
        };
    }

    public Converter getPillsConverter() {
        return pillsConverter;
    }

    public void setPillsConverter(Converter pillsConverter) {
        this.pillsConverter = pillsConverter;
    }

    public void save() throws IOException {
        if(FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("patient")==null)
            patientService.savePatient(patient);
        else
            patientService.merge(patient);

        FacesContext context = FacesContext.getCurrentInstance();
        context.getExternalContext().getSessionMap().put("patient", null);

        HttpServletResponse response = (HttpServletResponse) context.getExternalContext().getResponse();
        response.sendRedirect("index.xhtml");
    }


    public Patient getPatient() {
        return patient;
    }

    public DualListModel<Pills> getPillsDualListModel() {
        return pillsDualListModel;
    }

    public void setPillsDualListModel(DualListModel<Pills> pillsDualListModel) {
        this.pillsDualListModel = pillsDualListModel;
        patient.setPatientsPills(pillsDualListModel.getTarget());
    }

}
