package com.packtpub.wflydevelopment.chapter3.control;

import java.util.concurrent.TimeUnit;

import javax.annotation.PostConstruct;
import javax.ejb.AccessTimeout;
import javax.ejb.EJB;
import javax.ejb.Remote;
import javax.ejb.Stateful;

import org.jboss.logging.Logger;

@Stateful
@Remote(TheatreBookerRemote.class)
@AccessTimeout(value = 5, unit = TimeUnit.MINUTES)
public class TheatreBooker implements TheatreBookerRemote {
	private static final Logger logger = Logger.getLogger(TheatreBooker.class);
	@EJB
	private TheatreBox theatreBox;
	private int money;

	@PostConstruct
	public void createCustomer() {
		this.money = 100;
	}

	public int getAccountBalance() {
		return money;
	}

	public String bookSeat(int seatId) throws SeatBookedException, NotEnoughMoneyException, NoSuchSeatException {
		final int seatPrice = theatreBox.getSeatPrice(seatId);
		if (seatPrice > money) {
			throw new NotEnoughMoneyException("You don¡¯t have enough money to buy this " + seatId + " seat!");
		}
		theatreBox.buyTicket(seatId);
		money = money - seatPrice;
		logger.infov("Seat {0} booked.", seatId);
		return "Seat booked.";
	}
}
