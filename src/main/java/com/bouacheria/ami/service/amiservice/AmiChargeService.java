package com.bouacheria.ami.service.amiservice;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bouacheria.ami.domain.amiservices.AmiCharge;
import com.bouacheria.ami.domain.amiservices.AmiChargeTotal;
import com.bouacheria.ami.domain.amiservices.AmiMilageFeeModel;
import com.bouacheria.ami.domain.amiservices.AmiService;
import com.bouacheria.ami.repository.amiservices.AmiChargeRepository;
import com.bouacheria.ami.service.datatype.AmiServiceCategory;


@Service
public class AmiChargeService {

	@Autowired
	private AmiChargeRepository repo;
	
	@Autowired
	private AmiChargeTotalService amiChargeTotalService;
	
	public List<AmiCharge> findAll()
	{
		return repo.findAll();
	}

	@Transactional
	public AmiCharge save(AmiCharge amiService) {
		return repo.save(amiService);
	}
	
	@Transactional
	public void delete(long id) {
		repo.delete(id);
	}
	
	public AmiCharge findById(long id) {
		return repo.findOne(id);
	}
	
	public List<AmiCharge>findByAmiCaseId(long amiCaseId)
	{
		return repo.findByAmiCaseId(amiCaseId);
	}
//	public AmiCharge findByAmiCaseIdAndCategory(long amiCaseId, String category)
//	{
//		return repo.findByAmiCaseIdAndCategory(amiCaseId, category);
//	}
	public AmiCharge findMilageFeeByAmiCaseId(long amiCaseId)
	{
		return repo.findByAmiCaseIdAndCategory(amiCaseId, AmiServiceCategory.MILAGE_FEE.getCode());
	}

	@Transactional
	public void save(List<AmiCharge> amiCharges) 
	{
		if(amiCharges== null || amiCharges.isEmpty())
		{
			return ;
		}
		
		for (AmiCharge amiCharge : amiCharges) 
		{
			save(amiCharge);
		}
		
	}
	
	
	public AmiChargeTotal getTotal(List<AmiCharge> amiCharges, long caseId) 
	{
		
		double totalPrice = 0;
		StringBuilder amiChargesId = new StringBuilder();

		AmiChargeTotal anAmiChargeTotal = amiChargeTotalService.findByAmiCaseId(caseId);
		
		if(anAmiChargeTotal==null)
		{
			anAmiChargeTotal = new AmiChargeTotal();
		}
		anAmiChargeTotal.setAmiCaseId(caseId);
		anAmiChargeTotal.setPrice(totalPrice);
		
		if(amiCharges== null || amiCharges.isEmpty())
		{
			return anAmiChargeTotal;
		}
		
		
		for (AmiCharge amiCharge : amiCharges) 
		{
			amiChargesId.append(amiCharge.getId() + ", ");
			totalPrice = totalPrice + amiCharge.getPrice();
		}
		
		anAmiChargeTotal.setPrice(totalPrice);
		anAmiChargeTotal.setAmiChargesId(amiChargesId.toString());
		return anAmiChargeTotal;
		
	}
	
	public List<AmiCharge> getChargesFromService(List<AmiService> services, long amiCaseId, long serviceRequestId)
	{
		
		if(services==null || services.isEmpty())
		{
			return new ArrayList<AmiCharge>();
		}
		List<AmiCharge> charges = new ArrayList<AmiCharge>();
		for (AmiService amiService : services) 
		{
			AmiCharge anAmiCharge = getChargeFromService(amiService, amiCaseId, serviceRequestId);
			charges.add(anAmiCharge);
		}
		return charges;
	}
	
	
	public AmiCharge getChargeFromService(AmiService amiService, long amiCaseId, long serviceRequestId)
	{
		AmiCharge anAmiCharge = new AmiCharge();
		
		anAmiCharge.basedOnAmiService(amiService);
		anAmiCharge.setAmiCaseId(amiCaseId);
		anAmiCharge.setServiceRequestId(serviceRequestId);
		
		return anAmiCharge;
	}
	
	public AmiCharge getChargeFromFee(AmiMilageFeeModel milageFeeModel)
	{
		AmiCharge anAmiCharge = new AmiCharge();
		anAmiCharge.basedOnAmiService(milageFeeModel);
		return anAmiCharge;
	}
	
}
