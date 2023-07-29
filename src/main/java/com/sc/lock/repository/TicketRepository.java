package com.sc.lock.repository;

import org.springframework.data.repository.CrudRepository;

import com.sc.lock.entity.Ticket;

public interface TicketRepository extends CrudRepository<Ticket, Long> {

}
