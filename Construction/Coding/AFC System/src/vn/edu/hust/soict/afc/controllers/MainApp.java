package vn.edu.hust.soict.afc.controllers;

import java.sql.SQLException;

import hust.soict.se.customexception.InvalidIDException;
import vn.edu.hust.soict.afc.boundaries.CardScannerBoundary;
import vn.edu.hust.soict.afc.entities.PrepaidCard;
import vn.edu.hust.soict.afc.entities.Station;
import vn.edu.hust.soict.afc.services.StationService;

/**
 * @author Professor
 *
 */
public class MainApp {
    public static final double BASE_FARE = 10000;

    /**
     *
     */
    public MainApp() {
        // TODO Auto-generated constructor stub
    }

    public void start() {
        String pseudoBarcode = "ABCDEFGH";

        // convert pseudoBarcode -> cardCode
        String cardCode;
        try {
            cardCode = CardScannerBoundary.getCode(pseudoBarcode);
        } catch (InvalidIDException e) {
            System.out.println("Can't scanner");
            return;
        }

        // get station checkin
        Station station = new Station();
        try {
            station = StationService.getStationInfo(3);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        // get infor Prepaid Card
        PrepaidCard prepaidCard;
        try {
            prepaidCard = PrePaidCardController.getPrepaidCardInfo(cardCode);
            if(prepaidCard == null) {
                System.out.println("Can't find your Prepaid Card, this may be a wrong Prepaid Card");
                return;
            }
        } catch (SQLException e) {
            System.out.println("Can't find your Prepaid Card, this may be a wrong Prepaid Card");
            return;
        }

        if (prepaidCard.getBalance() < BASE_FARE) {
            System.out.println("The balance on the card is less than the base fare");
            return;
        }

        if (prepaidCard.isCheckedIn()) {
            System.out.println("This card is now only for checking out");
            return;
        }

        try {
            if (PrePaidCardController.checkIn(prepaidCard, station)) {
                System.out.println("Opening Gate...");
                System.out.println("Prepaid Card: " + prepaidCard.getId());
                System.out.println("Balance: " + prepaidCard.getBalance());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * @param args
     */
    public static void main(String[] args) {
        MainApp mainApp = new MainApp();
        mainApp.start();
    }

}