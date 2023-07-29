package com.sc.lock.controller;

import java.time.LocalDateTime;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.commons.lang3.function.FailableRunnable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sc.lock.entity.BusDetails;
import com.sc.lock.repository.BusRepository;
import com.sc.lock.service.BookingService;

@RestController
@RequestMapping("/api")
public class LockingDemoController {

	@Autowired
	private BookingService bookingService;
	
	@Autowired
	private BusRepository busRepository;
	
	@GetMapping("/bookTicket")
	public void bookTicket() {
		ExecutorService executor = Executors.newFixedThreadPool(2);
		executor.execute(run(bookingService::bookTicket));
		executor.execute(run(bookingService::bookTicket1));
		executor.shutdown();
	}
	
	@GetMapping("/addBus")
	public void addBus(@RequestParam String number, @RequestParam int capacity) {
		BusDetails busDetails = new BusDetails();
		busDetails.setCapacity(capacity);
		busDetails.setNumber(number);
		busDetails.setDeparDateTime(LocalDateTime.now());
		busRepository.save(busDetails);
	}

	private Runnable run(FailableRunnable<Exception> runnable) {
		return () -> {
			try {
				runnable.run();
			} catch (Exception e) {
				e.printStackTrace();
			}
		};
	}
}
