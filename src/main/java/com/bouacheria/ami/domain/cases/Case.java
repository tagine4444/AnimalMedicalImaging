package com.bouacheria.ami.domain.cases;

import java.util.Locale;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Transient;

import net.sf.cglib.core.Local;

import org.hibernate.annotations.Type;
import org.joda.time.DateTime;
import org.springframework.format.datetime.DateFormatter;

import com.bouacheria.ami.domain.Services;
import com.bouacheria.ami.domain.client.ClientAttribute;
import com.bouacheria.ami.domain.exam.ExamAttribute;
import com.bouacheria.ami.domain.hospital.HospitalAttribute;
import com.bouacheria.ami.domain.pet.PetAttribute;
import com.bouacheria.ami.domain.request.ServiceRequest;

@Entity
@Table(schema = "amischema", name = "AMICASE")
public class Case 
{
	@Id
	@GeneratedValue
	protected Long id = null;
	
	private final static String YES = "Yes";
	private final static String NO =  "No";
	
	@Column(name="REQUEST_ID", length=60)
	private Long requestId;
	
	@Column(name="REQUEST_DATE")
	@Type(type="org.joda.time.contrib.hibernate.PersistentDateTime")
	private DateTime requestDate = null;
	
	@Column(name="REQUEST_BY")
	private String requestBy = null;
	
	@Column(name="priority", length=50 )
	private int priority =0;
	
	@Column(name="REQUEST_NUMBER", length=200)
	private String requestNumber;
	
	
	@Column(name="CONSULTATION", length=3000)
	private String consultation= null;
	
	@Column(name="TENTATIVE_DIAGNOSIS", length=3000)
	private String tentativeDiagnosis= null;
	
	@Column(name="VETERINARIAN", length=300)
	private String veterinarian= null;
	
	@Column(name="INTERPRETATION_ONLY")
	private boolean interpretationOnly = false; //should not be defaulted to yes like ServiceRequest
	
	@Column(name="STAT")
	private boolean stat = false;
	
	@Column(name="READING_INPROGRESS_DATE")
	@Type(type="org.joda.time.contrib.hibernate.PersistentDateTime")
	private DateTime readingInProgressDate= null;
	
	@Column(name="READING_COMPLETE_DATE")
	@Type(type="org.joda.time.contrib.hibernate.PersistentDateTime")
	private DateTime readingInCompleteDate= null;
	
	@Column(name="TRANSCRIPTION_COMPLETE_DATE")
	@Type(type="org.joda.time.contrib.hibernate.PersistentDateTime")
	private DateTime transcriptionCompleteDate= null;
	
	
	@Column(name="AMENDED_VET_NOTES", length=3000)
	private String amendedVetNotes;
	
	
	@Column(name="RADIO_IMPRESSION", length=3000)
	private String radioImpression;
	
	@Column(name="RADIO_INTERPRETATION", length=3000)
	private String radioInterpretation;
	
	@Column(name="RECOMMENDATIONS", length=3000)
	private String recommendations;
	
	@Column(name="HOSPITAL_ID", nullable = false)
	private Long hospitalId = null;

	@Column(name="UNDER_CONTRACT", nullable=false)
	private boolean underContract=false;
	
	@Embedded
	private HospitalAttribute hospitalAttribute;
	
	@Embedded
	private ClientAttribute clientAttribute;
	
	@Embedded
	private PetAttribute petAttribute;
	
	@Embedded
	private Services services;
	
	@Embedded
	private ExamAttribute examAttribute;
	
	@Column(name="CAPTURED_IN_QUICKBOOK")
	private boolean capturedInQuickBook = false; //should not be defaulted to yes like ServiceRequest
	
	@Column(name="notes", length=3000)
	private String notes;
	
	
	public Case() {
		this.hospitalAttribute = new HospitalAttribute();
		this.clientAttribute = new ClientAttribute();
		this.petAttribute = new PetAttribute();
		this.services = new Services();
		this.examAttribute = new ExamAttribute();
		
	}
	

	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}

	public Long getHospitalId() {
		return hospitalId;
	}


	public void setHospitalId(Long hospitalId) {
		this.hospitalId = hospitalId;
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
	public String getAnesthesizedStringString(){
		if(isAnesthesized()){
			return YES;
		}
		return NO;
	}
	
	@Transient
	public String getSedatedString(){
		if(isSedated()){
			return YES;
		}
		return NO;
	}
	
	@Transient
	public String getFastedString(){
		if(isFasted()){
			return YES;
		}
		return NO;
	}
	
	@Transient
	public String getEnemaString(){
		if(isEnema()){
			return YES;
		}
		return NO;
	}
	
	@Transient
	public String getPainfulString(){
		if(isPainful()){
			return YES;
		}
		return NO;
	}
	
	@Transient
	public String getFractiousString(){
		if(isFractious()){
			return YES;
		}
		return NO;
	}
	
	@Transient
	public String getShockyString(){
		if(isShocky()){
			return YES;
		}
		return NO;
	}
	
	@Transient
	public String getDyspneicString(){
		if(isDyspneic()){
			return YES;
		}
		return NO;
	}
	
	@Transient
	public String getDiedString(){
		if(isDied()){
			return YES;
		}
		return NO;
	}
	
	@Transient
	public String getEuthanizedString(){
		if(isEuthanized()){
			return YES;
		}
		return NO;
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

	@Transient
	public void setClientId(String clientId) {
		this.clientAttribute.setClientId(clientId);
	}
	
	@PrePersist
	public void setRequestDateToNow()
	{
		this.requestDate = new DateTime();
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
	
	@Transient
	public String getPatientName() {
		return petAttribute.getPetName();
	}

	@Transient
	public void setPatientName(String name) {
		this.petAttribute.setPetName(name);
	}

	@Transient
	public String getPatientSex() {
		return this.petAttribute.getSex();
	}

	@Transient
	public void setPatientSex(String sex) {
		this.petAttribute.setSex(sex);
	}

	@Transient
	public String getPatientSpecies() {
		return this.petAttribute.getSpecies();
	}

	@Transient
	public void setPatientSpecies(String species) {
		this.petAttribute.setSpecies(species);
	}

	@Transient
	public String getPatientBreed() {
		return this.petAttribute.getBreed();
	}

	@Transient
	public void setPatientBreed(String breed) {
		this.petAttribute.setBreed(breed);
	}

	@Transient
	public String getPatientAge() {
		return this.petAttribute.getPatientAge();
	}

	@Transient
	public void setPatientAge(String age) {
		this.petAttribute.setPatientAge(age);
	}

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
	public void setPatientWeightuom(String uom) {
		this.petAttribute.getWeight().setUom(uom);
	}
	
	@Transient
	public String getClientFirstName() {
		return this.clientAttribute.getFirstName();
	}

	@Transient
	public void setClientFirstName(String firstName) {
		this.clientAttribute.setFirstName(firstName);
	}

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


//	public String getCaseNumber() {
//		return caseNumber;
//	}
//
//
//	public void setCaseNumber(String caseNumber) {
//		this.caseNumber = caseNumber;
//	}


	public DateTime getReadingInProgressDate() {
		return readingInProgressDate;
	}
	
	public String format(DateTime date)
	{
		if(date==null)
		{
			return "";
		}
		return date.toString("dd MMM yyyy hh:ss");
	}


	public void setReadingInProgressDate(DateTime readingInProgressDate) {
		this.readingInProgressDate = readingInProgressDate;
	}


	public DateTime getReadingInCompleteDate() {
		return readingInCompleteDate;
	}


	public void setReadingInCompleteDate(DateTime readingInCompleteDate) {
		this.readingInCompleteDate = readingInCompleteDate;
	}


	public String getAmendedVetNotes() {
		return amendedVetNotes;
	}
	
	public boolean getHasAmendedNotes() {
		return amendedVetNotes!=null && amendedVetNotes.length()>0;
	}


	public void setAmendedVetNotes(String amendedVetNotes) {
		this.amendedVetNotes = amendedVetNotes;
	}


	public Long getRequestId() {
		return requestId;
	}


	public void setRequestId(Long requestId) {
		this.requestId = requestId;
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
	
	
	public boolean isReadingComplete()
	{
		return readingInCompleteDate!=null;
	}
	
	public boolean isReadingNotDone()
	{
		return readingInCompleteDate==null;
	}
	
	public boolean isTranscriptionNotDone()
	{
		return transcriptionCompleteDate==null;
	}
	
	public boolean isTranscriptionDone()
	{
		return transcriptionCompleteDate!=null;
	}
	
	
	public DateTime getTranscriptionCompleteDate() {
		return transcriptionCompleteDate;
	}


	public void setTranscriptionCompleteDate(DateTime transcriptionCompleteDate) {
		this.transcriptionCompleteDate = transcriptionCompleteDate;
	}

	
	public void copy(ServiceRequest serviceReq) {
		
		setHospitalId(serviceReq.getHospitalId());
		setRequestNumber(serviceReq.getRequestNumber());
		setPriority(serviceReq.getPriority());
		setRequestId(serviceReq.getId());	
		setHospitalId(serviceReq.getHospitalId());
		setHospitalAttribute(serviceReq.getHospitalAttribute()) ;
		setServices(serviceReq.getServices());
		setExamAttribute(serviceReq.getExamAttribute());
		setPetAttribute(serviceReq.getPetAttribute()); 
		setRequestDate(serviceReq.getRequestDate()); 
		setRequestBy(serviceReq.getRequestBy()); 
		setConsultation(serviceReq.getConsultation()); 
		setTentativeDiagnosis(serviceReq.getTentativeDiagnosis()); 
		setClientAttribute(serviceReq.getClientAttribute()); 
		setVeterinarian(serviceReq.getVeterinarian()); 
		setInterpretationOnly(serviceReq.isInterpretationOnly());
		setStat(serviceReq.isStat());
		setUnderContract(serviceReq.isUnderContract());
		setCapturedInQuickBook(false);
		
	}


	public int getPriority() {
		return priority;
	}


	public void setPriority(int priority) {
		this.priority = priority;
	}


	public String getRequestNumber() {
		return requestNumber;
	}


	public void setRequestNumber(String requestNumber) {
		this.requestNumber = requestNumber;
	}


	public String getRadioImpression() {
		return radioImpression;
	}


	public void setRadioImpression(String radioImpression) {
		this.radioImpression = radioImpression;
	}


	public String getRecommendations() {
		return recommendations;
	}


	public void setRecommendations(String recommendations) {
		this.recommendations = recommendations;
	}


	public String getRadioInterpretation() {
		return radioInterpretation;
	}


	public void setRadioInterpretation(String radioInterpretation) {
		this.radioInterpretation = radioInterpretation;
	}


	public boolean isUnderContract() {
		return underContract;
	}


	public void setUnderContract(boolean underContract) {
		this.underContract = underContract;
	}


	public boolean isCapturedInQuickBook() {
		return capturedInQuickBook;
	}


	public void setCapturedInQuickBook(boolean capturedInQuickBook) {
		this.capturedInQuickBook = capturedInQuickBook;
	}


	public String getNotes() {
		return notes;
	}


	public void setNotes(String notes) {
		this.notes = notes;
	}

}
