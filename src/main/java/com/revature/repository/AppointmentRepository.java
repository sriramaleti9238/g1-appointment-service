package com.revature.repository;

import java.util.List;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.PathVariable;

import com.revature.dto.AppointmentDto;
import com.revature.entity.Appointment;

public interface AppointmentRepository extends JpaRepository<Appointment,Integer>{
	
	
	Appointment findByPatientIdAndAcceptanceOrderByDateDesc(int id,String Status,PageRequest page);
	
	List<Appointment> findAllByPatientId(int PatientId);

	List<Appointment> findByPhysicianEmailAndDateAndAcceptance(String physicianEmail,String date,String acceptance);

	List<Appointment> findByAcceptance(String acceptance);

	Object findTopByPatientIdAndAcceptanceOrderByDateDesc(int id, String status);
	
	List<AppointmentDto> findByAcceptanceAndDate( String status,String date,PageRequest page);
	
	@Query(value = "select count(acceptance) from appointment where acceptance = 'pending' and physcian_email=?1",nativeQuery = true)
	public long countPendingAppointment(@PathVariable String email);
	
	@Query(value = "select count(acceptance) from appointment where physcian_email=?1",nativeQuery = true)
	public long countAppointment(@PathVariable String email);
	
	@Query(value = "select count(acceptance) from appointment where acceptance = 'acceptance' and physcian_email=?1",nativeQuery = true)
	public long countAcceptedAppointment(@PathVariable String email);

	@Query(value= "select count(acceptance) from appointment where acceptance = 'completed' and patient_id = ?1",nativeQuery = true)
	public long countCompletedAppointmentByPatientId(@PathVariable int id);
	
	@Query(value= "select count(acceptance) from appointment where acceptance = 'pending' and patient_id = ?1",nativeQuery = true)
	public long countPendingAppointmentByPatientId(@PathVariable int id);
	
	@Query(value= "select count(acceptance) from appointment where acceptance = 'acceptance' and date=?1",nativeQuery = true)
	public long countAllAcceptanceAppointment(@PathVariable String date);

	List<AppointmentDto> findByPatientId(int id);

}
