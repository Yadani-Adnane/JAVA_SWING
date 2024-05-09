package Controller;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

import Model.Residant;
import Model.ResidantEtranger;
import Model.ResidantLocal;

public class ResidantController {
	
    public void addLocalResidant(String nom, String prenom, String dateDeNaissance, String tel, String adresse, String id,
                                 String email, String genre, String dateEntre, String dateSortie, String etat,
                                 String universite, String idChambre, String telGarant, String programmeDetude,
                                 int reservationNonPayees, String CNE, String cin) throws ClassNotFoundException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        ResidantLocal localResidant = new ResidantLocal(nom, prenom, dateDeNaissance, tel, adresse, cin, email, genre,
                                                        dateEntre, dateSortie, etat, universite, idChambre,
                                                        telGarant, programmeDetude, reservationNonPayees, CNE, cin);
        localResidant.creer();
        
    }
    
    public void addInternationalResidant(String nom, String prenom, String dateDeNaissance, String tel, String adresse,
                                         String id, String email, String genre, String dateEntre, String dateSortie,
                                         String etat, String universite, String idChambre, String telGarant,
                                         String programmeDetude, int reservationNonPayees, String numPassport,
                                         String pays) throws ClassNotFoundException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        ResidantEtranger internationalResidant = new ResidantEtranger(nom, prenom, dateDeNaissance, tel, adresse,
					                                                   numPassport, email, genre, dateEntre, dateSortie,
                                                                       etat, universite, idChambre, telGarant,
                                                                       programmeDetude, reservationNonPayees,
                                                                       numPassport, pays);
        internationalResidant.creer();
    }

    public List<ResidantLocal> getLocalResidents() {
        return ResidantLocal.getLocalResidents();
    }

    public List<ResidantEtranger> getInternationalResidents() {
        return ResidantEtranger.getInternationalResidents();
    }
    public void deleteLocalResidant(String id) {
        ResidantLocal localResidant = new ResidantLocal();
        localResidant.supprimer(id);
    }
    
    public void deleteInternationalResidant(String id) {
        ResidantEtranger internationalResidant = new ResidantEtranger();
        internationalResidant.supprimer(id);
    }
    public void modifyLocalResidant(String id, String nom, String prenom, String dateDeNaissance, String tel, String adresse,
            String email, String genre, String dateEntre, String dateSortie, String etat,
            String universite, String idChambre, String telGarant, String programmeDetude,
            int reservationNonPayees, String CNE, String cin) {
			ResidantLocal localResidant = new ResidantLocal(nom, prenom, dateDeNaissance, tel, adresse, id, email, genre,
			                               dateEntre, dateSortie, etat, universite, idChambre,
			                               telGarant, programmeDetude, reservationNonPayees, CNE, cin);
			localResidant.modifier(id); // Call the modifier method of the ResidantLocal class
			}
			
			// Method to modify international resident information
			public void modifyInternationalResidant(String id, String nom, String prenom, String dateDeNaissance, String tel, String adresse,
			                    String email, String genre, String dateEntre, String dateSortie, String etat,
			                    String universite, String idChambre, String telGarant, String programmeDetude,
			                    int reservationNonPayees, String numPassport, String pays) {
			ResidantEtranger internationalResidant = new ResidantEtranger(nom, prenom, dateDeNaissance, tel, adresse,
			                                              id, email, genre, dateEntre, dateSortie,
			                                              etat, universite, idChambre, telGarant,
			                                              programmeDetude, reservationNonPayees,
			                                              numPassport, pays);
			internationalResidant.modifier(id); // Call the modifier method of the ResidantEtranger class
	}
			public ResidantLocal getLocalResidantById(String id) {
		        for (ResidantLocal resident : ResidantLocal.getLocalResidents()) {
		            if (resident.getId().equals(id)) {
		                return resident;
		            }
		        }
		        return null;
		    }
			public ResidantEtranger getInternationalResidantById(String id) {
		        for (ResidantEtranger resident : ResidantEtranger.getInternationalResidents()) {
		            if (resident.getId().equals(id)) {
		                return resident;
		            }
		        }
		        return null;
		    }
			public List<ResidantLocal> searchLocalResidents(String keyword) {
		        List<ResidantLocal> matchingResidents = new ArrayList<>();
		        
		        List<ResidantLocal> localResidents = ResidantLocal.getLocalResidents();
		        
		        for (ResidantLocal resident : localResidents) {
		            if (resident.getNom().toLowerCase().contains(keyword.toLowerCase()) ||
		                resident.getPrenom().toLowerCase().contains(keyword.toLowerCase()) ||
		                resident.getDateDeNaissance().contains(keyword) ||
		                resident.getTel().contains(keyword) ||
		                resident.getAdresse().toLowerCase().contains(keyword.toLowerCase()) ||
		                resident.getId().contains(keyword) ||
		                resident.getEmail().toLowerCase().contains(keyword.toLowerCase()) ||
		                resident.getGenre().toLowerCase().contains(keyword.toLowerCase()) ||
		                resident.getDateEntre().contains(keyword) ||
		                resident.getDateSortie().contains(keyword) ||
		                resident.getUniversite().toLowerCase().contains(keyword.toLowerCase()) ||
		                resident.getIdChambre().contains(keyword) ||
		                resident.getTelGarant().contains(keyword) ||
		                resident.getProgrammeDetude().toLowerCase().contains(keyword.toLowerCase()) ||
		                resident.getCNE().contains(keyword) ||
		                resident.getCin().contains(keyword)) {
		                matchingResidents.add(resident);
		            }
		        }
		        
		        return matchingResidents;
		    }
		    
		    public List<ResidantEtranger> searchInternationalResidents(String keyword) {
		        List<ResidantEtranger> matchingResidents = new ArrayList<>();
		        
		        List<ResidantEtranger> internationalResidents = ResidantEtranger.getInternationalResidents();
		        
		        for (ResidantEtranger resident : internationalResidents) {
		            if (resident.getNom().toLowerCase().contains(keyword.toLowerCase()) ||
		                resident.getPrenom().toLowerCase().contains(keyword.toLowerCase()) ||
		                resident.getDateDeNaissance().contains(keyword) ||
		                resident.getTel().contains(keyword) ||
		                resident.getAdresse().toLowerCase().contains(keyword.toLowerCase()) ||
		                resident.getId().contains(keyword) ||
		                resident.getEmail().toLowerCase().contains(keyword.toLowerCase()) ||
		                resident.getGenre().toLowerCase().contains(keyword.toLowerCase()) ||
		                resident.getDateEntre().contains(keyword) ||
		                resident.getDateSortie().contains(keyword) ||
		                resident.getUniversite().toLowerCase().contains(keyword.toLowerCase()) ||
		                resident.getIdChambre().contains(keyword) ||
		                resident.getTelGarant().contains(keyword) ||
		                resident.getProgrammeDetude().toLowerCase().contains(keyword.toLowerCase()) ||
		                resident.getNumPassport().contains(keyword) ||
		                resident.getPays().toLowerCase().contains(keyword.toLowerCase())) {
		                matchingResidents.add(resident);
		            }
		        }
		        
		        return matchingResidents;
		    }
		
	 
}