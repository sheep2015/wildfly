package com.packtpub.wflydevelopment.chapter3.control;

public interface TheatreBookerRemote {

	public int getAccountBalance();
	
	public String bookSeat(int seatId) throws SeatBookedException,
	NotEnoughMoneyException, NoSuchSeatException;
}
