package com.bouacheria.ami.repository.uploads;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.bouacheria.ami.domain.uploads.Uploads;

public interface UploadsRepository extends JpaRepository<Uploads, Long>, JpaSpecificationExecutor<Uploads>
{
	List<Uploads>findByRequestId(long requestId);
}
