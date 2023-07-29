package com.sc.lock.entity;

import java.time.LocalDateTime;
import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Version;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "bus_details")
public class BusDetails {

	@Id
	@GeneratedValue
	private Long id;

	private LocalDateTime deparDateTime;

	private Integer capacity;
	
	private String number;
	
	@Version
	private Long version;

	@OneToMany(mappedBy = "busDetails")
	private Set<Ticket> tickets;

	public void addTicket(Ticket ticket) {
		ticket.setBusDetails(this);
		getTickets().add(ticket);
	}

}
