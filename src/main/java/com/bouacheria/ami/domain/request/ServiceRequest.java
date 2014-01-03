package com.bouacheria.ami.domain.request;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.apache.commons.lang.StringUtils;
import org.hibernate.annotations.Type;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;
import org.joda.time.DateTime;
import org.springframework.format.annotation.DateTimeFormat;

import com.bouacheria.ami.domain.Services;
import com.bouacheria.ami.domain.client.ClientAttribute;
import com.bouacheria.ami.domain.exam.ExamAttribute;
import com.bouacheria.ami.domain.hospital.Hospital;
import com.bouacheria.ami.domain.hospital.HospitalAttribute;
import com.bouacheria.ami.domain.pet.PetAttribute;
import com.bouacheria.ami.service.datatype.AmiServiceCategory;

@Entity
@Table(schema = "amischema", name = "SERVICE_REQUEST")
public class ServiceRequest {

	@Id
	@GeneratedValue
	protected Long id = null;
	
	@Transient
	public final static int PRIORITY_NORMAL =Hospital.PRIORITY_NORMAL;
	
	@Transient
	public final static int PRIORITY_HIGH  =Hospital.PRIORITY_HIGH;
	
	
	@Column(name="REQUEST_NUMBER", length=200)
	private String requestNumber;
	
	@Column(name="UNDER_CONTRACT", nullable=false)
	private boolean underContract=false;
	
	@Column(name="priority", length=50 )
	private int priority =0;
	
	@Column(name="REQUEST_DATE")
//	@Type(type="org.joda.time.contrib.hibernate.PersistentDateTime")
	@Type(type="org.jadira.usertype.dateandtime.joda.PersistentDateTime")
	private DateTime requestDate = null;
	
	@NotEmpty(message="may not be empty")
	@Column(name="REQUEST_BY")
	private String requestBy = null;
	
	@NotEmpty(message="may not be empty")
	@Column(name="CONSULTATION", length=3000)
	private String consultation= null;
	
	@NotEmpty(message="may not be empty")
	@Column(name="TENTATIVE_DIAGNOSIS", length=3000)
	private String tentativeDiagnosis= null;
	
	@NotEmpty(message="may not be empty")
	@Column(name="VETERINARIAN", length=300)
	private String veterinarian= null;
	
	@Column(name="INTERPRETATION_ONLY")
	private boolean interpretationOnly = true; //default value for the UI.
	
	@Column(name="STAT")
	private boolean stat = false;
	
	@Column(name="INPROGRESS_DATE")
	@Type(type="org.jadira.usertype.dateandtime.joda.PersistentDateTime")
	private DateTime inProgressDate= null;
	
	@Column(name="READING_COMPLETE_DATE")
	@Type(type="org.jadira.usertype.dateandtime.joda.PersistentDateTime")
	private DateTime readingInCompleteDate= null;
	
	
	@Column(name="TRANSCRIBED_DATE")
	@Type(type="org.jadira.usertype.dateandtime.joda.PersistentDateTime")
	private DateTime trascribedDate= null;
	
	@Embedded
	private HospitalAttribute hospitalAttribute;
	
	
	@Column(name="HOSPITAL_ID", nullable = false)
	private Long hospitalId = null;
	
	@Column(name="CASE_ID")
	private Long caseId = null;

	@Column(name="CASE_STATUS")
	private String caseStatus = "";
	
	@Column(name="HAS_DOCUMENTS")
	private boolean hasDocuments = false;
	
	
	@Embedded
	private ClientAttribute clientAttribute;
	
	@Embedded
	private PetAttribute petAttribute;
	
	@Embedded
	private Services services;

	@Embedded
	private ExamAttribute examAttribute;
	
	@Column(name="PAPER_DOC")
	private boolean paperDocs = false;
	
	@Column(name="PAPER_DOCS_NOTES", length=1000)
	private String docsNotes= "";
	
	@Column(name="DOC_BY_EMAIL")
	private boolean docByEmail= false;
	
	@Column(name="DOC_BY_UPLOAD")
	private boolean docByUpload= false;
	
	@Column(name="DOC_BY_CARRIER")
	private boolean docByCarrier= false;
	
//	@Column(name="DIGITAL_DOC")
//	private boolean digitalDocs = false;
//	
//	
	@Column(name="DONE_UPLOADING_DIGITAL_DOC")
	private boolean doneUploadingDocs = false;
	
	
	
	public ServiceRequest() {
		this.hospitalAttribute = new HospitalAttribute();
		this.clientAttribute = new ClientAttribute();
		this.petAttribute = new PetAttribute();
		this.services = new Services();
		this.examAttribute = new ExamAttribute();
	}
	

	public Long getId() {
		return id;
	}

	public HospitalAttribute getHospitalAttribute() {
		return hospitalAttribute;
	}

	public void setHospitalAttribute(HospitalAttribute hospitalAttribute) {
		this.hospitalAttribute = hospitalAttribute;
	}


	public PetAttribute getPetAttribute() {
		return petAttribute;
	}

	public void setPetAttribute(PetAttribute petAttribute) {
		this.petAttribute = petAttribute;
	}

	public DateTime getRequestDate() {
		return requestDate;
	}

	public void setRequestDate(DateTime requestDate) {
		this.requestDate = requestDate;
	}

	public String getRequestBy() {
		return requestBy;
	}

	public void setRequestBy(String requestBy) {
		this.requestBy = requestBy;
	}

	public String getConsultation() {
		return consultation;
	}

	public void setConsultation(String consultation) {
		this.consultation = consultation;
	}

	public String getTentativeDiagnosis() {
		return tentativeDiagnosis;
	}

	public void setTentativeDiagnosis(String tentativeDiagnosis) {
		this.tentativeDiagnosis = tentativeDiagnosis;
	}


	public ClientAttribute getClientAttribute() {
		return clientAttribute;
	}

	public void setClientAttribute(ClientAttribute clientAttribute) {
		this.clientAttribute = clientAttribute;
	}

	public String getVeterinarian() {
		return veterinarian;
	}

	public void setVeterinarian(String veterinarian) {
		this.veterinarian = veterinarian;
	}

	@Transient
	public boolean isAnesthesized() {
		return examAttribute.isAnesthesized();
	}

	@Transient
	public void setAnesthesized(boolean anesthesized) {
		this.examAttribute.setAnesthesized(anesthesized);
	}

	@Transient
	public boolean isSedated() {
		return examAttribute.isSedated();
	}

	@Transient
	public void setSedated(boolean sedated) {
		this.examAttribute.setSedated(sedated);
	}

	@Transient
	public boolean isFasted() {
		return examAttribute.isFasted();
	}

	@Transient
	public void setFasted(boolean fasted) {
		this.examAttribute.setFasted(fasted);
	}

	@Transient
	public boolean isEnema() {
		return examAttribute.isEnema();
	}

	@Transient
	public void setEnema(boolean enema) {
		this.examAttribute.setEnema(enema);
	}

	@Transient
	public boolean isPainful() {
		return examAttribute.isPainful();
	}

	@Transient
	public void setPainful(boolean painful) {
		this.examAttribute.setPainful(painful);
	}

	@Transient
	public boolean isFractious() {
		return examAttribute.isFractious();
	}

	@Transient
	public void setFractious(boolean fractious) {
		this.examAttribute.setFractious(fractious);
	}

	@Transient
	public boolean isShocky() {
		return examAttribute.isShocky();
	}

	public void setShocky(boolean shocky) {
		this.examAttribute.setShocky(shocky);
	}

	@Transient
	public boolean isDyspneic() {
		return examAttribute.isDyspneic();
	}

	@Transient
	public void setDyspneic(boolean dyspneic) {
		this.examAttribute.setDyspneic(dyspneic);
	}

	@Transient
	public boolean isDied() {
		return examAttribute.isDied();
	}

	@Transient
	public void setDied(boolean died) {
		this.examAttribute.setDied(died);
	}

	@Transient
	public boolean isEuthanized() {
		return examAttribute.isEuthanized();
	}

	@Transient
	public void setEuthanized(boolean euthanized) {
		this.examAttribute.setEuthanized(euthanized);
	}
	
	@Transient
	public String getClientId() {
		return clientAttribute.getClientId();
	}

	public void setClientId(String clientId) {
		this.clientAttribute.setClientId(clientId);
	}
	
	@Transient
	public String getClientAddress() {
		return clientAttribute.getAddress().getAddress();
	}
	
	public void setClientAddress(String clientAddress) {
		this.clientAttribute.getAddress().setAddress(clientAddress);
	}
	
	@Transient
	public String getClientCell() {
		return clientAttribute.getContact().getCell();
	}
	
	public void setClientCell(String clientCell) {
		this.clientAttribute.getContact().setCell(clientCell);
	}
	
	@Transient
	public String getClientHomePhone() {
		return clientAttribute.getContact().getHomePhone();
	}
	
	public void setClientHomePhone(String homePhone) {
		this.clientAttribute.getContact().setHomePhone(homePhone);
	}
	
	@Transient
	public boolean getClientHospitalEmployee() {
		return clientAttribute.isHospitalEmployee();
	}
	
	public void setClientHospitalEmployee(boolean hospitalEmployee) {
		this.clientAttribute.setHospitalEmployee(hospitalEmployee);
	}
	
	@Transient
	public String getHospitalName()
	{
		return hospitalAttribute.getHospitalName();
	}
	
	
	
	@Transient
	public String getLab()
	{
		return hospitalAttribute.getLab();
	}
	@Transient
	public String getLabAccount()
	{
		return hospitalAttribute.getLabAccount();
	}
	
	@Transient
	public String getAddress()
	{
		return hospitalAttribute.getAddress().getAddress();
	}
	
	@Transient
	public String getCity()
	{
		return hospitalAttribute.getAddress().getCity();
	}
	@Transient
	public String getStateProvince()
	{
		return hospitalAttribute.getAddress().getStateProvince();
	}
	@Transient
	public int getZip()
	{
		return hospitalAttribute.getAddress().getZip();
	}
	@Transient
	public String getCountry()
	{
		return hospitalAttribute.getAddress().getCountry();
	}
	
	@Transient
	public String getCell()
	{
		return hospitalAttribute.getContact().getCell();
	}
	
	@Transient
	public String getEmail1()
	{
		return hospitalAttribute.getContact().getEmail();
	}
	
	
	@Transient
	public String getFax()
	{
		return hospitalAttribute.getContact().getFax();
	}	
	
	@Transient
	public String getOffice()
	{
		return hospitalAttribute.getContact().getOffice();
	}	
	
	@Transient
	public String getBackLine()
	{
		return hospitalAttribute.getContact().getBackLine();
	}	
	
	@Transient
	public void setHospitalName(String hospitalName)
	{
		hospitalAttribute.setHospitalName(hospitalName);
	}
	@Transient
	public void setLab(String lab)
	{
		hospitalAttribute.setLab(lab);
	}
	@Transient
	public void setLabAccount(String account)
	{
		hospitalAttribute.setLabAccount(account);
	}
	
	@Transient
	public void setAddress(String address)
	{
		hospitalAttribute.getAddress().setAddress(address);
	}
	
	@Transient
	public void setCity(String city)
	{
		hospitalAttribute.getAddress().setCity(city);
	}
	@Transient
	public void setStateProvince(String stateProvince)
	{
		hospitalAttribute.getAddress().setStateProvince(stateProvince);
	}
	@Transient
	public void setZip(int zip)
	{
		hospitalAttribute.getAddress().setZip(zip);
	}
	@Transient
	public void setCountry(String country)
	{
		hospitalAttribute.getAddress().setCountry(country);
	}
	
	@Transient
	public void setCell(String cell)
	{
		hospitalAttribute.getContact().setCell(cell);
	}
	
	@Transient
	public void setEmail1(String email1)
	{
		hospitalAttribute.getContact().setEmail(email1);
	}
	
	@Transient
	public void setFax(String fax)
	{
		hospitalAttribute.getContact().setFax(fax);
	}	
	
	@Transient
	public void setOffice(String office)
	{
		hospitalAttribute.getContact().setOffice(office);
	}	
	@Transient
	public void setBackLine(String backLine)
	{
		hospitalAttribute.getContact().setBackLine(backLine);
	}
	
	
	@NotBlank(message="may not be empty")
	@Transient
	public String getPatientName() {
		return petAttribute.getPetName();
	}

	@Transient
	public void setPatientName(String name) {
		this.petAttribute.setPetName(name);
	}

	@NotBlank(message="may not be empty")
	@Transient
	public String getPatientSex() {
		return this.petAttribute.getSex();
	}

	@Transient
	public void setPatientSex(String sex) {
		this.petAttribute.setSex(sex);
	}

	@NotBlank(message="may not be empty")
	@Transient
	public String getPatientSpecies() {
		return this.petAttribute.getSpecies();
	}

	@Transient
	public void setPatientSpecies(String species) {
		this.petAttribute.setSpecies(species);
	}

	@NotBlank(message="may not be empty")
	@Transient
	public String getPatientBreed() {
		return this.petAttribute.getBreed();
	}

	@Transient
	public void setPatientBreed(String breed) {
		this.petAttribute.setBreed(breed);
	}

	@NotBlank(message="may not be empty")
	@Transient
	public String getPatientAge() {
		return this.petAttribute.getPatientAge();
	}
	@Transient
	public void setPatientAge(String patientAge) {
		 this.petAttribute.setPatientAge(patientAge);
	}
	
	@DateTimeFormat(pattern="MM/dd/yyyy")
	@Transient
	public DateTime getPatientDob() {
		return this.petAttribute.getDob();
	}

	@Transient
	public void setPatientDob(DateTime dob) {
		this.petAttribute.setDob(dob);
	}

	
	@NotEmpty(message="may not be empty")
	@Transient
	public String getPatientWeight() {
		return this.petAttribute.getWeight().getValue();
	}

	@Transient
	public void setPatientWeight(String weight) {
		this.petAttribute.getWeight().setValue(weight);
	}
	
	@Transient
	public String getPatientWeightUom() {
		return this.petAttribute.getWeight().getUom();
	}
	
	@Transient
	public void setPatientWeightUom(String uom) {
		this.petAttribute.getWeight().setUom(uom);
	}
	
	@NotEmpty(message="may not be empty")
	@Transient
	public String getClientFirstName() {
		return this.clientAttribute.getFirstName();
	}

	@Transient
	public void setClientFirstName(String firstName) {
		this.clientAttribute.setFirstName(firstName);
	}

	@NotEmpty(message="may not be empty")
	@Transient
	public String getClientLastName() {
		return this.clientAttribute.getLastName();
	}

	@Transient
	public void setClientLastName(String lastName) {
		this.clientAttribute.setLastName(lastName);
	}
	

	@Transient
	public String getMriSvc() {
		return this.services.getMriSvc();
	}
	
	@Transient
	public void setMriSvc(String mriSvc) {
		this.services.setMriSvc(mriSvc);
	}

	@Transient
	public String getRadiographyFluoroscopy() {
		return services.getRadiographyFluoroscopy();
	}

	@Transient
	public void setRadiographyFluoroscopy(String radiographyFluoroscopy) {
		this.services.setRadiographyFluoroscopy(radiographyFluoroscopy);
	}

	@Transient
	public String getContrastRadiographySvc() {
		return services.getContrastRadiographySvc();
	}

	@Transient
	public void setContrastRadiographySvc(String contrastRadiographySvc) {
		this.services.setContrastRadiographySvc(contrastRadiographySvc);
	}

	@Transient
	public String getComputedTomographySvc() {
		return services.getComputedTomographySvc();
	}
	
	@Transient
	public void setComputedTomographySvc(String computedTomographySvc) {
		this.services.setComputedTomographySvc(computedTomographySvc);
	}

	@Transient
	public String getUltrasoundSvc() {
		return services.getUltrasoundSvc();
	}

	@Transient
	public void setUltrasoundSvc(String ultrasoundSvc) {
		this.services.setUltrasoundSvc(ultrasoundSvc);
	}


	public boolean hasAtLeastOneImagingServie()
	{
		if (StringUtils.isNotEmpty(getMriSvc()))
		{
			return true;
		}
		if (StringUtils.isNotEmpty(getContrastRadiographySvc()))
		{
			return true;
		}
		if (StringUtils.isNotEmpty(getComputedTomographySvc()))
		{
			return true;
		}
		if (StringUtils.isNotEmpty(getRadiographyFluoroscopy()))
		{
			return true;
		}
		if (StringUtils.isNotEmpty(getUltrasoundSvc()))
		{
			return true;
		}
		return false;
	}
	
	public Map<AmiServiceCategory, String> getSelectedImagingServices()
	{ 
		Map<AmiServiceCategory, String>  services = new HashMap<AmiServiceCategory, String>();
		if(isInterpretationOnly())
		{
			services.put(AmiServiceCategory.INTERPRETATION_ONLY,AmiServiceCategory.MISC_SERVICE.getName());
		}
		if (StringUtils.isNotEmpty(getMriSvc()))
		{
			services.put(AmiServiceCategory.MRI,getMriSvc());
		}
		if (StringUtils.isNotEmpty(getContrastRadiographySvc()))
		{
			services.put(AmiServiceCategory.CONTRASTEDRADIOGRAPHY,getContrastRadiographySvc());
		}
		if (StringUtils.isNotEmpty(getComputedTomographySvc()))
		{
			services.put(AmiServiceCategory.COMPUTEDTOMOGRAPHY,getComputedTomographySvc());
		}
		if (StringUtils.isNotEmpty(getRadiographyFluoroscopy()))
		{
			services.put(AmiServiceCategory.RADIOGRAPHYFLUOROSCOPY,getRadiographyFluoroscopy());
		}
		if (StringUtils.isNotEmpty(getUltrasoundSvc()))
		{
			services.put(AmiServiceCategory.ULTRASOUND,getUltrasoundSvc());
		}
		return services;
	}
	
	public boolean isInterpretationOnly() {
		return interpretationOnly;
	}


	public void setInterpretationOnly(boolean interpretationOnly) {
		this.interpretationOnly = interpretationOnly;
	}


	public boolean isStat() {
		return stat;
	}


	public void setStat(boolean stat) {
		this.stat = stat;
	}



//	public DateTime getSubmittedDate() {
//		return submittedDate;
//	}
//
//
//	public void setSubmittedDate(DateTime submittedDate) {
//		this.submittedDate = submittedDate;
//	}


	public DateTime getInProgressDate() {
		return inProgressDate;
	}


	public void setInProgressDate(DateTime inProgressDate) {
		this.inProgressDate = inProgressDate;
	}
	
	@Transient
	public boolean isInProgress()
	{
		return this.inProgressDate != null;
	}
	
	@Transient
	public boolean isNotYetInProgress()
	{
		return this.inProgressDate == null;
	}
	
	@Transient
	public boolean isComplete()
	{
		return this.trascribedDate != null;
	}
	
	@Transient
	public boolean isTranscribed()
	{
		return this.trascribedDate!= null;
	}



	public Services getServices() {
		return services;
	}


	public void setServices(Services services) {
		this.services = services;
	}


	public ExamAttribute getExamAttribute() {
		return examAttribute;
	}


	public void setExamAttribute(ExamAttribute examAttribute) {
		this.examAttribute = examAttribute;
	}


	public Long getHospitalId() {
		return hospitalId;
	}


	public void setHospitalId(Long hospitalId) {
		this.hospitalId = hospitalId;
	}
	
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public DateTime getTrascribedDate() {
		return trascribedDate;
	}


	public void setTrascribedDate(DateTime trascribedDate) {
		this.trascribedDate = trascribedDate;
	}
	
	@Transient
	public String getStatus()
	{
		if(isComplete())
		{
			return "Complete";
		}
		if(isTranscribed())
		{
			return "Transcribed";
		}
		if(isInProgress())
		{
			return "In Progress";
		}
		return "Requested";
		
	}


	public Long getCaseId() {
		return caseId;
	}


	public void setCaseId(Long caseId) {
		this.caseId = caseId;
	}


	public String getCaseStatus() {
		return caseStatus;
	}


	public void setCaseStatus(String caseStatus) {
		this.caseStatus = caseStatus;
	}


	public DateTime getReadingInCompleteDate() {
		return readingInCompleteDate;
	}


	public void setReadingInCompleteDate(DateTime readingInCompleteDate) {
		this.readingInCompleteDate = readingInCompleteDate;
	}


	public String getRequestNumber() {
		return requestNumber;
	}

	public void setRequestNumber(String requestNumber) {
		
		this.requestNumber = requestNumber;
	}
	
	@Transient
	public boolean hasRequestNumber()
	{
		if(this.requestNumber!=null && this.requestNumber.length()>0)
		{
			return true;
		}
		return false;
	}


	@Transient
	public String getAcronym() {
		return this.hospitalAttribute.getAcronym();
	}

	@Transient
	public void setAcronym(String acronym) {
		this.hospitalAttribute.setAcronym(acronym);
	}


	public int getPriority() {
		return priority;
	}


	public void setPriority(int priority) {
		this.priority = priority;
	}


	public boolean isHasDocuments() {
		return hasDocuments;
	}


	public void setHasDocuments(boolean hasDocuments) {
		this.hasDocuments = hasDocuments;
	}

	
	public void upperCaseFields()
	{
		if( getVeterinarian()!=null)
		{
			setVeterinarian(getVeterinarian().toUpperCase().trim());
		}
		
		if( getRequestBy()!=null)
		{
			setRequestBy(getRequestBy().toUpperCase().trim());
		}
		if( getClientFirstName()!=null)
		{
			setClientFirstName(getClientFirstName().toUpperCase().trim());
		}
		
		if( getClientLastName()!=null)
		{
			setClientLastName(getClientLastName().toUpperCase().trim());
		}
		
		if( getClientId()!=null)
		{
			setClientId(getClientId().trim());
		}
		if( getPatientName()!=null)
		{
			setPatientName(getPatientName().toUpperCase().trim());
		}
		if( getPatientAge()!=null)
		{
			setPatientAge(getPatientAge().toUpperCase().trim());
		}
	}
	

	public boolean isUnderContract() {
		return underContract;
	}


	public void setUnderContract(boolean underContract) {
		this.underContract = underContract;
	}


	
	public String isValidPatientAge() {
		
		final String INVALID_AGE = "Invalid Age";
		String age = getPatientAge();
		
		if(age==null|| age.isEmpty())
		{
			return "Age Cannot be empty";
		}
		age = age.toLowerCase().trim();
		age = age.replaceAll(" ", "");
		
		boolean hasY = age.contains("y");
		boolean hasM = age.contains("m");
		boolean hasD = age.contains("d");
		
		int indexY = age.indexOf("y");
		int indexM = age.indexOf("m");
		int indexD = age.indexOf("d");
		
		
		int maxLength = 0;
		int ageLength = age.length();
		
		
		if(hasY && hasM && hasD)
		{
			//100y11m31d
			maxLength = 10;
			if(ageLength > maxLength)
			{
				return INVALID_AGE;
			}
			
			String numOfY  = age.substring(0,indexY);
			String numOfM = age.substring(indexY+1,indexM);
			String numOfD   = age.substring(indexM+1,indexD);
			
			if(!StringUtils.isNumeric(numOfY) ||!StringUtils.isNumeric(numOfM)||!StringUtils.isNumeric(numOfD))
			{
				return INVALID_AGE;
			}
			return null;
		}
		if(hasM && hasD)
		{
			//11m31d
			maxLength = 6;
			if(ageLength > maxLength)
			{
				return INVALID_AGE;
			}
			
			boolean daysBeforeMonths= indexD < indexM;
			if(daysBeforeMonths)
			{
				return "Months must come after days";
			}
			
			String numOfM = age.substring(0,indexM);
			String numOfD   = age.substring(indexM+1,indexD);
			
			if(!StringUtils.isNumeric(numOfM)||!StringUtils.isNumeric(numOfD))
			{
				return INVALID_AGE;
			}
			return null;
		}
		if(hasY && hasM )
		{
			//100y11m
			maxLength = 7;
			if(ageLength > maxLength)
			{
				return INVALID_AGE;
			}
			
			boolean monthBeforeYears = indexM < indexY;
			if(monthBeforeYears)
			{
				return "Years must come after months";
			}
			
			String numOfY = age.substring(0,indexY);
			String numOfM   = age.substring(indexY+1,indexM);
			
			if(!StringUtils.isNumeric(numOfY)||!StringUtils.isNumeric(numOfM))
			{
				return INVALID_AGE;
			}
			return null;
		}
		if(hasY && hasD )
		{
			//100y31d
			maxLength = 7;
			if(ageLength > maxLength)
			{
				return INVALID_AGE;
			}
			
			boolean daysBeforeYears= indexD < indexY;
			if(daysBeforeYears)
			{
				if(age.toLowerCase().indexOf("days")>0)
				{
					return INVALID_AGE;
				}
				
				return "Years must come after days";
			}
			
			String numOfY = age.substring(0,indexY);
			String numOfD   = age.substring(indexY+1,indexD);
			
			if(!StringUtils.isNumeric(numOfY)||!StringUtils.isNumeric(numOfD))
			{
				return INVALID_AGE;
			}
			return null;
		}
		if(hasY)
		{
			//100y
			maxLength = 4;
			
			boolean shouldbUpdate = false;
			if(age.length() > indexY)
			{
				age = age.substring(0, indexY +1);
				shouldbUpdate = true;
			}
			
			if(ageLength > maxLength)
			{
				return INVALID_AGE;
			}
			
			String numOfY = age.substring(0,indexY);
			
			if(!StringUtils.isNumeric(numOfY))
			{
				return INVALID_AGE;
			}
			if(shouldbUpdate)
			{
				setPatientAge(age);
			}
			return null;
		}
		if(hasM)
		{
			//11m
			maxLength = 3;
			
			
			boolean shouldbUpdate = false;
			if(age.length() > indexM)
			{
				age = age.substring(0, indexM +1);
				shouldbUpdate = true;
			}
			
			if(ageLength > maxLength)
			{
				return INVALID_AGE;
			}
			
			String numOfM = age.substring(0,indexM);
			
			if(!StringUtils.isNumeric(numOfM))
			{
				return INVALID_AGE;
			}
			
			if(shouldbUpdate)
			{
				setPatientAge(age);
			}
			return null;
		}
		if(hasD)
		{
			//31d
			maxLength = 3;
			
			boolean shouldbUpdate = false;
			if(age.length() > indexD)
			{
				age = age.substring(0, indexD +1);
				shouldbUpdate = true;
			}
			
			if(ageLength > maxLength)
			{
				return INVALID_AGE;
			}
			
			String numOfD = age.substring(0,indexD);
			
			if(!StringUtils.isNumeric(numOfD))
			{
				return INVALID_AGE;
			}
			
			if(shouldbUpdate)
			{
				setPatientAge(age);
			}
			return null;
		}
		
		
		
		return "Missing the time unit y (years), m (months), d (days)";
	}


	public boolean isPaperDocs()
	{
		return paperDocs;
	}


	public void setPaperDocs(boolean paperDocs)
	{
		this.paperDocs = paperDocs;
	}


	public String getDocsNotes()
	{
		return docsNotes;
	}


	public void setDocsNotes(String docsNotes)
	{
		this.docsNotes = docsNotes;
	}


	public boolean isDocByEmail()
	{
		return docByEmail;
	}


	public void setDocByEmail(boolean docByEmail)
	{
		this.docByEmail = docByEmail;
	}


	public boolean isDocByUpload()
	{
		return docByUpload;
	}


	public void setDocByUpload(boolean docByUpload)
	{
		this.docByUpload = docByUpload;
	}


	public boolean isDocByCarrier()
	{
		return docByCarrier;
	}


	public void setDocByCarrier(boolean docByCarrier)
	{
		this.docByCarrier = docByCarrier;
	}


	public boolean isDoneUploadingDocs()
	{
		return doneUploadingDocs;
	}


	public void setDoneUploadingDocs(boolean doneUploadingDocs)
	{
		this.doneUploadingDocs = doneUploadingDocs;
	}

}
