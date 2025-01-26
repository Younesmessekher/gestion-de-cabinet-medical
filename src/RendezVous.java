import java.time.LocalDateTime;

public class RendezVous {
    private Patient patient;
    private LocalDateTime dateHeure;

    //Constructeur
    public RendezVous(Patient patient, LocalDateTime dateHeure) {
        this.patient = patient;
        this.dateHeure = dateHeure;
    }

    public Patient getPatient() {
        return this.patient;
    }
    public void setPatient(Patient patient) {
        this.patient = patient;
    }
    public LocalDateTime getDateHeure() {
        return this.dateHeure;
    }
    public void setDateHeure(LocalDateTime dateHeure) {
        this.dateHeure = dateHeure;
    }

}
