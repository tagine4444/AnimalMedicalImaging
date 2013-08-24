package com.bouacheria.ami.temp;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bouacheria.ami.domain.amiservices.AmiFee;
import com.bouacheria.ami.domain.datatype.Contact;
import com.bouacheria.ami.domain.hospital.Hospital;
import com.bouacheria.ami.domain.hospital.HospitalAttribute;
import com.bouacheria.ami.domain.users.AMIUser;
import com.bouacheria.ami.factory.UserFactory;
import com.bouacheria.ami.service.amiservice.AmiFeeService;
import com.bouacheria.ami.service.amiservice.AmiServiceService;
import com.bouacheria.ami.service.datatype.AmiServiceCategory;
import com.bouacheria.ami.service.datatype.BreedService;
import com.bouacheria.ami.service.datatype.LabsService;
import com.bouacheria.ami.service.datatype.SpeciesService;
import com.bouacheria.ami.service.hospital.HospitalService;
import com.bouacheria.ami.service.request.RequestSequenceService;
import com.bouacheria.ami.service.users.UserService;

@Service
public class DeleteMeService {
	
	private static String ACRONYM = "VETT";
	
	private boolean isLoaded=false;
	

	@Autowired
	private BreedService breedService;
	
	@Autowired
	private SpeciesService speciesService;

	@Autowired
	private LabsService labsService;
	
	@Autowired
	private DeleteMeFactory deleteMeService;
	
	@Autowired
	private UserFactory userFactory;

	@Autowired
	private UserService userService;

	@Autowired
	private HospitalService hosptialService;

	@Autowired
	private RequestSequenceService requestSequenceService;
	

	@Autowired
	private AmiServiceService amiService;

	@Autowired
	private AmiFeeService amiFeeService;

	
	public void load()
	{
		if(isLoaded==true)
		{
			System.out.println("load is disabled ...");
			return;
		}
		this.isLoaded =true;
		
		int index =1;
		int maxIndex = 9;
		
		System.out.println("loading Users " + index++ + "/" +maxIndex);
		loadUsers();
		
		System.out.println("loading Services and fees "+ index++ + "/" +maxIndex);
		loadServicesAndFees();
		
		System.out.println("loading MRIServices "+ index++ + "/" +maxIndex);
		loadMRIServices();
		
		System.out.println("loading ComputedTomographyServices "+ index++ + "/" +maxIndex);
		loadComputedTomographyServices();
		
		System.out.println("loading ContrastRadiographyServices "+ index++ + "/" +maxIndex);
		loadContrastRadiographyServices();
		
		System.out.println("loading UltrasoundSvcService "+ index++ + "/" +maxIndex);
		loadUltrasoundSvcService();
		
		System.out.println("loading RadiographyFluoroscopyServices "+ index++ + "/" +maxIndex);
		loadRadiographyFluoroscopyServices();
		
		System.out.println("loading Labs "+ index++ + "/" +maxIndex);
		loadLabs();
		
		System.out.println("loading Species "+ index++ + "/" +maxIndex);
		loadSpecies();
		
		System.out.println("loading Breed "+ index++ + "/" +maxIndex);
		loadBreed();
		
		System.out.println("Done Loading: " + new Date());
	}
	private void loadUsers() {
		
		Contact contact = new Contact();
		contact.setEmail("tagine4444@yahoo.com");
		 
		 
		Hospital amiHospital = loadHospital("Animal Medical Imaging", true, "AMI",contact, Hospital.PRIORITY_NORMAL);
		userService.save(userFactory.getActiveAdmin("chuck", "chuck",amiHospital.getId()));
		userService.save(userFactory.getActiveTranscriber("joann", "joann",amiHospital.getId()));
		
		 Hospital hospital = loadHospital("Cat Clinic", true, ACRONYM,contact, Hospital.PRIORITY_HIGH);
		 long hospitalId = hospital.getId();
		 AMIUser activeVet = userFactory.getActiveVet("vet", "vet", hospitalId, "tagine4444@yahoo.com", "Yvon","Dutush", "Veterinarian");
		 if(hospital.isUnderContract())
		 {
			 requestSequenceService.save(userFactory.getRequestSequence(hospital.getName(), hospitalId));
		 }

		 Hospital hospital1 =loadHospital("Dog Hospital", false, null,contact,Hospital.PRIORITY_NORMAL);
		 long hospitalId1 = hospital1.getId();
		 AMIUser activeVet1 = userFactory.getActiveVet("vet1", "vet1", hospitalId1, "tagine4444@yahoo.com","Samantha","Bee","Veterinarian");
		 if(hospital1.isUnderContract())
		 {
			 requestSequenceService.save(userFactory.getRequestSequence(hospital1.getName(), hospitalId1));
		 }
		
		 userService.save(activeVet);
		 userService.save(activeVet1);
		
	}
	
	
	
	private Hospital loadHospital(String hospitalName, boolean underContract, String acronym , Contact contact, int priority)
	{
		Hospital h = new Hospital();
		
		HospitalAttribute hospitalAttribute = new HospitalAttribute();
		
		hospitalAttribute.setHospitalName(hospitalName);
		hospitalAttribute.setLab("PCL");
		hospitalAttribute.setLabAccount("PCL123");
		h.setHospitalAttribute(hospitalAttribute);
		h.setPriority(priority);
		h.setUnderContract(underContract);
		if(underContract){
			h.setUnderContract(underContract);
			h.setAcronym(acronym);
		}
		
		hospitalAttribute.setContact(contact);
		
		Hospital save = hosptialService.save(h);
		return save;
		
	}
	
	private void loadServicesAndFees()
	{
		
		String[] svc2 = {AmiServiceCategory.MISC_SERVICE.getName() ,AmiServiceCategory.MISC_SERVICE.getName()};
		amiService.save(deleteMeService.getAmiService(svc2[0], AmiServiceCategory.INTERPRETATION_ONLY.getCode(), 60,svc2[1]));
		
		amiService.save(deleteMeService.getAmiService("Miscellenous Service", AmiServiceCategory.MISC_SERVICE.getCode(), 0,"Service Defined by its description"));
		
		AmiFee aMilageFee = deleteMeService.getAmiFee("Milage Fee", AmiServiceCategory.MILAGE_FEE.getCode(), 5.5,0,"Fee charged when traveling occurs", false);
		amiFeeService.save(aMilageFee);
		
		AmiFee alatePaymentFee = deleteMeService.getAmiFee("Late Payment Fee", AmiServiceCategory.LATE_PAYMENT_FEE.getCode(), 1.5,0,"Fee charged when late payment occurs", true);
		amiFeeService.save(alatePaymentFee);
		
	}
	
	private void loadMRIServices()
	{
		String[] svc2 = {"Brain, with contrast","Magnetic Resonance Imaging Examination With Contrast"};
		String[] svc3 = {"Brain, with contrast and/or CSF tap","Magnetic Resonance Imaging Examination With Contrast"};
		String[] svc4 = {"Skull, nasal passages, sinuses","Magnetic Resonance Imaging Examination Without Contrast"};
		String[] svc5 = {"Spine","Magnetic Resonance Imaging Examination Without Contrast"};
		String[] svc6 = {"Spine, with contrast","Magnetic Resonance Imaging Examination With Contrast"};
		String[] svc7 = {"Spine, with contrast and/or CSF tap","Magnetic Resonance Imaging Examination With Contrast"};
		String[] svc8 = {"Abdomen","Magnetic Resonance Imaging Examination Without Contrast"};
		String[] svc9 = {"Abdomen, with contrast","Magnetic Resonance Imaging Examination With Contrast"};
		String[] svc10 = {"Thorax","Magnetic Resonance Imaging Examination Without Contrast"};
		String[] svc11 = {"Nasal passages, with biopsy","Magnetic Resonance Imaging Examination Without Contrast"};
		String[] svc12 = {"Thorax passages, with biopsy and/or contrast","Magnetic Resonance Imaging Examination With Contrast"};
		
		amiService.save(deleteMeService.getAmiService("", AmiServiceCategory.MRI.getCode(), 0,""));
		amiService.save(deleteMeService.getAmiService(svc2[0], AmiServiceCategory.MRI.getCode(), 200,svc2[1]));
		amiService.save(deleteMeService.getAmiService(svc3[0], AmiServiceCategory.MRI.getCode(), 300,svc3[1]));
		amiService.save(deleteMeService.getAmiService(svc4[0], AmiServiceCategory.MRI.getCode(), 400,svc4[1]));
		amiService.save(deleteMeService.getAmiService(svc5[0], AmiServiceCategory.MRI.getCode(), 400,svc5[1]));
		amiService.save(deleteMeService.getAmiService(svc6[0], AmiServiceCategory.MRI.getCode(), 400,svc6[1]));
		amiService.save(deleteMeService.getAmiService(svc7[0], AmiServiceCategory.MRI.getCode(), 200,svc7[1]));
		amiService.save(deleteMeService.getAmiService(svc8[0], AmiServiceCategory.MRI.getCode(), 300,svc8[1]));
		amiService.save(deleteMeService.getAmiService(svc9[0], AmiServiceCategory.MRI.getCode(), 400,svc9[1]));
		amiService.save(deleteMeService.getAmiService(svc10[0], AmiServiceCategory.MRI.getCode(), 400,svc10[1]));
		amiService.save(deleteMeService.getAmiService(svc11[0], AmiServiceCategory.MRI.getCode(), 400,svc11[1]));
		amiService.save(deleteMeService.getAmiService(svc12[0], AmiServiceCategory.MRI.getCode(), 400,svc12[1]));
		
	}
	
	private void loadRadiographyFluoroscopyServices()
	{
		String svc2 = "Thorax";
		String svc3 = "Abdomen";
		String svc4 = "Pelvis";
		
		amiService.save(deleteMeService.getAmiService("", AmiServiceCategory.RADIOGRAPHYFLUOROSCOPY.getCode(), 0,""));
		amiService.save(deleteMeService.getAmiService(svc2, AmiServiceCategory.RADIOGRAPHYFLUOROSCOPY.getCode(), 200,""));
		amiService.save(deleteMeService.getAmiService(svc3, AmiServiceCategory.RADIOGRAPHYFLUOROSCOPY.getCode(), 300,""));
		amiService.save(deleteMeService.getAmiService(svc4, AmiServiceCategory.RADIOGRAPHYFLUOROSCOPY.getCode(), 400,""));
	}
	
	private void loadUltrasoundSvcService()
	{
		String svc1 = "";
		String svc2 = "Abdomen";
		String svc3 = "Heart";
		String svc4 = "Uterus";
		
		amiService.save(deleteMeService.getAmiService(svc1, AmiServiceCategory.ULTRASOUND.getCode(), 0,""));
		amiService.save(deleteMeService.getAmiService(svc2, AmiServiceCategory.ULTRASOUND.getCode(), 250.55,""));
		amiService.save(deleteMeService.getAmiService(svc3, AmiServiceCategory.ULTRASOUND.getCode(), 665.22,""));
		amiService.save(deleteMeService.getAmiService(svc4, AmiServiceCategory.ULTRASOUND.getCode(), 122.55,""));
	}
	private void loadComputedTomographyServices()
	{
		String svc1 = "";
		String svc2 = "Brain, with contrast";
		String svc3 = "Brain, with contrast and/or CSF tap";
		
		amiService.save(deleteMeService.getAmiService(svc1, AmiServiceCategory.COMPUTEDTOMOGRAPHY.getCode(), 0,""));
		amiService.save(deleteMeService.getAmiService(svc2, AmiServiceCategory.COMPUTEDTOMOGRAPHY.getCode(), 250.55,""));
		amiService.save(deleteMeService.getAmiService(svc3, AmiServiceCategory.COMPUTEDTOMOGRAPHY.getCode(), 665.22,""));
	}
	
	private void loadContrastRadiographyServices()
	{
		String svc1 = "";
		String svc2 = "Myelogram";
		String svc3 = "Arthrogram";
		
		amiService.save(deleteMeService.getAmiService(svc1, AmiServiceCategory.CONTRASTEDRADIOGRAPHY.getCode(), 0,""));
		amiService.save(deleteMeService.getAmiService(svc2, AmiServiceCategory.CONTRASTEDRADIOGRAPHY.getCode(), 740.5,""));
		amiService.save(deleteMeService.getAmiService(svc3, AmiServiceCategory.CONTRASTEDRADIOGRAPHY.getCode(), 213.32,""));
	}
	
	private void loadLabs()
	{
		labsService.save(deleteMeService.getLabs(""));
		labsService.save(deleteMeService.getLabs("PCL"));
		labsService.save(deleteMeService.getLabs("IDEXX"));
		labsService.save(deleteMeService.getLabs("Antech"));
	}
	
	private void loadSpecies()
	{
		speciesService.save(deleteMeService.getSpecies("Canine"));
		speciesService.save(deleteMeService.getSpecies("Feline"));
		speciesService.save(deleteMeService.getSpecies("Avian"));
		speciesService.save(deleteMeService.getSpecies("Lagomorph"));
		speciesService.save(deleteMeService.getSpecies("Mustelid"));
		speciesService.save(deleteMeService.getSpecies("Ovine"));
		speciesService.save(deleteMeService.getSpecies("Caprine"));
		speciesService.save(deleteMeService.getSpecies("Reptile"));
		speciesService.save(deleteMeService.getSpecies("Amphibian"));
		speciesService.save(deleteMeService.getSpecies("Porcine"));
		speciesService.save(deleteMeService.getSpecies("Rodent"));
		speciesService.save(deleteMeService.getSpecies("Equine"));
	}
	
	private void loadBreed()
	{
		breedService.save(deleteMeService.getBreed("Affenpinscher", "Canine"));
		breedService.save(deleteMeService.getBreed("Afghan hound", "Canine"));
		breedService.save(deleteMeService.getBreed("African Pigmy Hedgehog", "Canine"));
		breedService.save(deleteMeService.getBreed("Airedale Terrier", "Canine"));
		breedService.save(deleteMeService.getBreed("Akita", "Canine"));
		breedService.save(deleteMeService.getBreed("Alaskan Malamute", "Canine"));
		breedService.save(deleteMeService.getBreed("American Bulldog", "Canine"));
		breedService.save(deleteMeService.getBreed("American Eskimo Dog", "Canine"));
		breedService.save(deleteMeService.getBreed("American Foxhound", "Canine"));
		breedService.save(deleteMeService.getBreed("American Pit Bull Terrier", "Canine"));
		breedService.save(deleteMeService.getBreed("American Staffordshire Terrier", "Canine"));
		breedService.save(deleteMeService.getBreed("American Water Spaniel", "Canine"));
		breedService.save(deleteMeService.getBreed("Anatolian Shepherd", "Canine"));
		breedService.save(deleteMeService.getBreed("Australian Cattle Dog", "Canine"));
		breedService.save(deleteMeService.getBreed("Australian Kelpie", "Canine"));
		breedService.save(deleteMeService.getBreed("Australian Shepherd", "Canine"));
		breedService.save(deleteMeService.getBreed("Australian Terrier", "Canine"));
		breedService.save(deleteMeService.getBreed("Basenji", "Canine"));
		breedService.save(deleteMeService.getBreed("Basset Hound", "Canine"));
		breedService.save(deleteMeService.getBreed("Beagle", "Canine"));
		breedService.save(deleteMeService.getBreed("Bearded Collie", "Canine"));
		breedService.save(deleteMeService.getBreed("Beauceron", "Canine"));
		breedService.save(deleteMeService.getBreed("Bedlington Terrier", "Canine"));
		breedService.save(deleteMeService.getBreed("Belgian Malinois", "Canine"));
		breedService.save(deleteMeService.getBreed("Belgian Sheepdog", "Canine"));
		breedService.save(deleteMeService.getBreed("Belgian Tervuren", "Canine"));
		breedService.save(deleteMeService.getBreed("Bernese Mountain Dog", "Canine"));
		breedService.save(deleteMeService.getBreed("Bichon Frise", "Canine"));
		breedService.save(deleteMeService.getBreed("Black and Tan Coonhound", "Canine"));
		breedService.save(deleteMeService.getBreed("Bloodhound", "Canine"));
		breedService.save(deleteMeService.getBreed("Border Collie", "Canine"));
		breedService.save(deleteMeService.getBreed("Border Terrier", "Canine"));
		breedService.save(deleteMeService.getBreed("Borzoi", "Canine"));
		breedService.save(deleteMeService.getBreed("Boston Terrier", "Canine"));
		breedService.save(deleteMeService.getBreed("Bouvier Des Flandres", "Canine"));
		breedService.save(deleteMeService.getBreed("Boxer", "Canine"));
		breedService.save(deleteMeService.getBreed("Briard", "Canine"));
		breedService.save(deleteMeService.getBreed("Brittany", "Canine"));
		breedService.save(deleteMeService.getBreed("Brussels Griffon", "Canine"));
		breedService.save(deleteMeService.getBreed("Bull Terrier", "Canine"));
		breedService.save(deleteMeService.getBreed("Bulldog", "Canine"));
		breedService.save(deleteMeService.getBreed("Bullmastiff", "Canine"));
		breedService.save(deleteMeService.getBreed("Cairn Terrier", "Canine"));
		breedService.save(deleteMeService.getBreed("Cane Corso", "Canine"));
		breedService.save(deleteMeService.getBreed("Cardigan Welsh Corgi", "Canine"));
		breedService.save(deleteMeService.getBreed("Cavalier King Charles Spaniel", "Canine"));
		breedService.save(deleteMeService.getBreed("Chesapeake Bay Retriever", "Canine"));
		breedService.save(deleteMeService.getBreed("Chihuahua", "Canine"));
		breedService.save(deleteMeService.getBreed("Chinese Crested", "Canine"));
		breedService.save(deleteMeService.getBreed("Chow Chow", "Canine"));
		breedService.save(deleteMeService.getBreed("Clumber Spaniel", "Canine"));
		breedService.save(deleteMeService.getBreed("Cocker Spaniel", "Canine"));
		breedService.save(deleteMeService.getBreed("Collie", "Canine"));
		breedService.save(deleteMeService.getBreed("Corgi", "Canine"));
		breedService.save(deleteMeService.getBreed("Cornish Rex", "Canine"));
		breedService.save(deleteMeService.getBreed("Coton de Tulear", "Canine"));
		breedService.save(deleteMeService.getBreed("Curly-Coated Retriever", "Canine"));
		breedService.save(deleteMeService.getBreed("Dachshund", "Canine"));
		breedService.save(deleteMeService.getBreed("Dalmatian", "Canine"));
		breedService.save(deleteMeService.getBreed("Dandie Dinmont Terrier", "Canine"));
		breedService.save(deleteMeService.getBreed("Doberman Pinscher", "Canine"));
		breedService.save(deleteMeService.getBreed("Dogue De Bordeaux", "Canine"));
		breedService.save(deleteMeService.getBreed("English Bulldog", "Canine"));
		breedService.save(deleteMeService.getBreed("English Cocker Spaniel", "Canine"));
		breedService.save(deleteMeService.getBreed("English Foxhound", "Canine"));
		breedService.save(deleteMeService.getBreed("English Pointer", "Canine"));
		breedService.save(deleteMeService.getBreed("English Setter", "Canine"));
		breedService.save(deleteMeService.getBreed("English Springer Spaniel", "Canine"));
		breedService.save(deleteMeService.getBreed("Entlebucher Sennenhund", "Canine"));
		breedService.save(deleteMeService.getBreed("Fila Brasileiro", "Canine"));
		breedService.save(deleteMeService.getBreed("Finnish Lapphund", "Canine"));
		breedService.save(deleteMeService.getBreed("Flat-Coated Retriever", "Canine"));
		breedService.save(deleteMeService.getBreed("Fox Terrier", "Canine"));
		breedService.save(deleteMeService.getBreed("French Bulldog", "Canine"));
		breedService.save(deleteMeService.getBreed("German Shepherd", "Canine"));
		breedService.save(deleteMeService.getBreed("German Shorthaired Pointer", "Canine"));
		breedService.save(deleteMeService.getBreed("German Wirehaired Pointer", "Canine"));
		breedService.save(deleteMeService.getBreed("Giant Schnauzer", "Canine"));
		breedService.save(deleteMeService.getBreed("Golden Retriever", "Canine"));
		breedService.save(deleteMeService.getBreed("Goldendoodle", "Canine"));
		breedService.save(deleteMeService.getBreed("Gordon Setter", "Canine"));
		breedService.save(deleteMeService.getBreed("Great Dane", "Canine"));
		breedService.save(deleteMeService.getBreed("Great Pyrenees", "Canine"));
		breedService.save(deleteMeService.getBreed("Greater Swiss Mountain Dog", "Canine"));
		breedService.save(deleteMeService.getBreed("Greyhound", "Canine"));
		breedService.save(deleteMeService.getBreed("Havanese", "Canine"));
		breedService.save(deleteMeService.getBreed("Ibizan Hound", "Canine"));
		breedService.save(deleteMeService.getBreed("Icelandic Sheepdog", "Canine"));
		breedService.save(deleteMeService.getBreed("Irish Setter", "Canine"));
		breedService.save(deleteMeService.getBreed("Irish Terrier", "Canine"));
		breedService.save(deleteMeService.getBreed("Irish Water Spaniel", "Canine"));
		breedService.save(deleteMeService.getBreed("Irish Wolfhound", "Canine"));
		breedService.save(deleteMeService.getBreed("Italian Greyhound", "Canine"));
		breedService.save(deleteMeService.getBreed("Jack Russell Terrier", "Canine"));
		breedService.save(deleteMeService.getBreed("Japanese Chin", "Canine"));
		breedService.save(deleteMeService.getBreed("Karelian Bear Dog", "Canine"));
		breedService.save(deleteMeService.getBreed("Keeshond", "Canine"));
		breedService.save(deleteMeService.getBreed("Kerry Blue Terrier", "Canine"));
		breedService.save(deleteMeService.getBreed("Komondor", "Canine"));
		breedService.save(deleteMeService.getBreed("Kuvasz", "Canine"));
		breedService.save(deleteMeService.getBreed("Labradoodle", "Canine"));
		breedService.save(deleteMeService.getBreed("Labrador Retriever", "Canine"));
		breedService.save(deleteMeService.getBreed("Lakeland Terrier", "Canine"));
		breedService.save(deleteMeService.getBreed("Leonberger", "Canine"));
		breedService.save(deleteMeService.getBreed("Lhasa Apso", "Canine"));
		breedService.save(deleteMeService.getBreed("Louisiana Catahoula", "Canine"));
		breedService.save(deleteMeService.getBreed("Lundehund", "Canine"));
		breedService.save(deleteMeService.getBreed("Maltese", "Canine"));
		breedService.save(deleteMeService.getBreed("Manchester Terrier", "Canine"));
		breedService.save(deleteMeService.getBreed("Mastiff", "Canine"));
		breedService.save(deleteMeService.getBreed("Miniature Australian Shepherd", "Canine"));
		breedService.save(deleteMeService.getBreed("Miniature Dachshund", "Canine"));
		breedService.save(deleteMeService.getBreed("Miniature Pinscher", "Canine"));
		breedService.save(deleteMeService.getBreed("Miniature Poodle", "Canine"));
		breedService.save(deleteMeService.getBreed("Miniature Schnauzer", "Canine"));
		breedService.save(deleteMeService.getBreed("Mixed breed", "Canine"));
		breedService.save(deleteMeService.getBreed("Munsterlander", "Canine"));
		breedService.save(deleteMeService.getBreed("Neapolitan Mastiff", "Canine"));
		breedService.save(deleteMeService.getBreed("Newfoundland", "Canine"));
		breedService.save(deleteMeService.getBreed("Norfolk Terrier", "Canine"));
		breedService.save(deleteMeService.getBreed("Norwegian Elkhound", "Canine"));
		breedService.save(deleteMeService.getBreed("Norwegian Forest Cat", "Canine"));
		breedService.save(deleteMeService.getBreed("Norwich Terrier", "Canine"));
		breedService.save(deleteMeService.getBreed("Nova Scotia Duck-Tolling Retriever", "Canine"));
		breedService.save(deleteMeService.getBreed("Old English Sheepdog", "Canine"));
		breedService.save(deleteMeService.getBreed("Otterhound", "Canine"));
		breedService.save(deleteMeService.getBreed("Papillon", "Canine"));
		breedService.save(deleteMeService.getBreed("Pekingese", "Canine"));
		breedService.save(deleteMeService.getBreed("Pembroke Welsh Corgi", "Canine"));
		breedService.save(deleteMeService.getBreed("Petit Basset Griffon Vendeen", "Canine"));
		breedService.save(deleteMeService.getBreed("Pharaoh Hound", "Canine"));
		breedService.save(deleteMeService.getBreed("Plott Hound", "Canine"));
		breedService.save(deleteMeService.getBreed("Pointer", "Canine"));
		breedService.save(deleteMeService.getBreed("Polish Lowland Sheepdog", "Canine"));
		breedService.save(deleteMeService.getBreed("Pomeranian", "Canine"));
		breedService.save(deleteMeService.getBreed("Poodle", "Canine"));
		breedService.save(deleteMeService.getBreed("Portuguese Water Dog", "Canine"));
		breedService.save(deleteMeService.getBreed("Pug", "Canine"));
		breedService.save(deleteMeService.getBreed("Puli", "Canine"));
		breedService.save(deleteMeService.getBreed("Rat Terrier", "Canine"));
		breedService.save(deleteMeService.getBreed("Rhodesian Ridgeback", "Canine"));
		breedService.save(deleteMeService.getBreed("Rottweiler", "Canine"));
		breedService.save(deleteMeService.getBreed("Saint Bernard", "Canine"));
		breedService.save(deleteMeService.getBreed("Saluki", "Canine"));
		breedService.save(deleteMeService.getBreed("Samoyed", "Canine"));
		breedService.save(deleteMeService.getBreed("Schipperke", "Canine"));
		breedService.save(deleteMeService.getBreed("Schnauzer", "Canine"));
		breedService.save(deleteMeService.getBreed("Scottish Deerhound", "Canine"));
		breedService.save(deleteMeService.getBreed("Scottish Terrier", "Canine"));
		breedService.save(deleteMeService.getBreed("Sealyham Terrier", "Canine"));
		breedService.save(deleteMeService.getBreed("Shar-Pei", "Canine"));
		breedService.save(deleteMeService.getBreed("Sheltie", "Canine"));
		breedService.save(deleteMeService.getBreed("Shetland Sheepdog", "Canine"));
		breedService.save(deleteMeService.getBreed("Shiba Inu", "Canine"));
		breedService.save(deleteMeService.getBreed("Shih Tzu", "Canine"));
		breedService.save(deleteMeService.getBreed("Siberian Husky", "Canine"));
		breedService.save(deleteMeService.getBreed("Silky Terrier", "Canine"));
		breedService.save(deleteMeService.getBreed("Skye Terrier", "Canine"));
		breedService.save(deleteMeService.getBreed("Smooth Fox Terrier", "Canine"));
		breedService.save(deleteMeService.getBreed("Soft-Coated Wheaten Terrier", "Canine"));
		breedService.save(deleteMeService.getBreed("Spitz", "Canine"));
		breedService.save(deleteMeService.getBreed("Springer", "Canine"));
		breedService.save(deleteMeService.getBreed("Staffordshire Bull Terrier", "Canine"));
		breedService.save(deleteMeService.getBreed("Standard Poodle", "Canine"));
		breedService.save(deleteMeService.getBreed("Standard Schnauzer", "Canine"));
		breedService.save(deleteMeService.getBreed("Terrier cross", "Canine"));
		breedService.save(deleteMeService.getBreed("Tibetan Mastiff", "Canine"));
		breedService.save(deleteMeService.getBreed("Tibetan Spaniel", "Canine"));
		breedService.save(deleteMeService.getBreed("Tibetan Terrier", "Canine"));
		breedService.save(deleteMeService.getBreed("Toy Fox Terrier", "Canine"));
		breedService.save(deleteMeService.getBreed("Toy Poodle", "Canine"));
		breedService.save(deleteMeService.getBreed("Vizsla", "Canine"));
		breedService.save(deleteMeService.getBreed("Weimaraner", "Canine"));
		breedService.save(deleteMeService.getBreed("Welsh Corgi", "Canine"));
		breedService.save(deleteMeService.getBreed("Welsh Terrier", "Canine"));
		breedService.save(deleteMeService.getBreed("West Highland White Terrier", "Canine"));
		breedService.save(deleteMeService.getBreed("Wheaton Terrier", "Canine"));
		breedService.save(deleteMeService.getBreed("Whippet", "Canine"));
		breedService.save(deleteMeService.getBreed("Wirehaired Dachshund", "Canine"));
		breedService.save(deleteMeService.getBreed("Wirehaired Fox Terrier", "Canine"));
		breedService.save(deleteMeService.getBreed("Wirehaired Pointing Griffon", "Canine"));
		breedService.save(deleteMeService.getBreed("Xoloitzcuintle", "Canine"));
		breedService.save(deleteMeService.getBreed("Yorkshire Terrier", "Canine"));
		breedService.save(deleteMeService.getBreed("Abyssinian", "Feline"));
		breedService.save(deleteMeService.getBreed("Balinese", "Feline"));
		breedService.save(deleteMeService.getBreed("Bengal", "Feline"));
		breedService.save(deleteMeService.getBreed("Burmese", "Feline"));
		breedService.save(deleteMeService.getBreed("Devon Rex", "Feline"));
		breedService.save(deleteMeService.getBreed("DLH", "Feline"));
		breedService.save(deleteMeService.getBreed("DMH", "Feline"));
		breedService.save(deleteMeService.getBreed("DSH", "Feline"));
		breedService.save(deleteMeService.getBreed("Egyptian Mau", "Feline"));
		breedService.save(deleteMeService.getBreed("Himalayan", "Feline"));
		breedService.save(deleteMeService.getBreed("Korat", "Feline"));
		breedService.save(deleteMeService.getBreed("Maine Coon Cat", "Feline"));
		breedService.save(deleteMeService.getBreed("Malayan", "Feline"));
		breedService.save(deleteMeService.getBreed("Manx", "Feline"));
		breedService.save(deleteMeService.getBreed("Mi-Ki", "Feline"));
		breedService.save(deleteMeService.getBreed("Ocicat", "Feline"));
		breedService.save(deleteMeService.getBreed("Persian", "Feline"));
		breedService.save(deleteMeService.getBreed("Pixie Bob", "Feline"));
		breedService.save(deleteMeService.getBreed("Ragdoll", "Feline"));
		breedService.save(deleteMeService.getBreed("Rex", "Feline"));
		breedService.save(deleteMeService.getBreed("Russian Blue", "Feline"));
		breedService.save(deleteMeService.getBreed("Scottish Fold", "Feline"));
		breedService.save(deleteMeService.getBreed("Selkirk Rex", "Feline"));
		breedService.save(deleteMeService.getBreed("Siamese", "Feline"));
		breedService.save(deleteMeService.getBreed("Sphynx", "Feline"));
		breedService.save(deleteMeService.getBreed("Tonkinese", "Feline"));
		breedService.save(deleteMeService.getBreed("Cockatiel", "Avian"));
		breedService.save(deleteMeService.getBreed("Cockatoo", "Avian"));
		breedService.save(deleteMeService.getBreed("Green-winged Macaw", "Avian"));
		breedService.save(deleteMeService.getBreed("Lesser Sulfur Crested Cockatoo", "Avian"));
		breedService.save(deleteMeService.getBreed("Bald Eagle", "Avian"));
		breedService.save(deleteMeService.getBreed("Red-shouldered Hawk", "Avian"));
		breedService.save(deleteMeService.getBreed("Falcon", "Avian"));
		breedService.save(deleteMeService.getBreed("Chicken", "Avian"));
		breedService.save(deleteMeService.getBreed("Rhodesian Red", "Avian"));
		breedService.save(deleteMeService.getBreed("Domestic Rabbit", "Lagomorph"));
		breedService.save(deleteMeService.getBreed("Flemmish Giant", "Lagomorph"));
		breedService.save(deleteMeService.getBreed("Miniature Lop-eared", "Lagomorph"));
		breedService.save(deleteMeService.getBreed("Domestic Ferret", "Mustelid"));
		breedService.save(deleteMeService.getBreed("Green Iguana", "Reptile"));
		breedService.save(deleteMeService.getBreed("Panther Chameleon", "Reptile"));
		breedService.save(deleteMeService.getBreed("Veiled Chameleon", "Reptile"));
		breedService.save(deleteMeService.getBreed("Box Turtle", "Amphibian"));
		breedService.save(deleteMeService.getBreed("Russian Tortoise", "Amphibian"));
		breedService.save(deleteMeService.getBreed("Asian Pot-bellied", "Porcine"));
		breedService.save(deleteMeService.getBreed("Cavy", "Rodent"));
		breedService.save(deleteMeService.getBreed("Gerbil", "Rodent"));
		breedService.save(deleteMeService.getBreed("Guinea Pig", "Rodent"));
		breedService.save(deleteMeService.getBreed("Hamster", "Rodent"));
		breedService.save(deleteMeService.getBreed("Rat", "Rodent"));
		breedService.save(deleteMeService.getBreed("American Quarterhorse", "Equine"));
		breedService.save(deleteMeService.getBreed("Morgan", "Equine"));
		breedService.save(deleteMeService.getBreed("Paint", "Equine"));
		breedService.save(deleteMeService.getBreed("Pinto", "Equine"));
		breedService.save(deleteMeService.getBreed("Pony of America", "Equine"));
		breedService.save(deleteMeService.getBreed("Quarter Horse", "Equine"));
		breedService.save(deleteMeService.getBreed("Tennessee Walker", "Equine"));
		breedService.save(deleteMeService.getBreed("Thoroughbred", "Equine"));
	}
	
	
	
}
