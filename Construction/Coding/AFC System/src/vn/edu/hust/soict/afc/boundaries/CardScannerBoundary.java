package vn.edu.hust.soict.afc.boundaries;

import hust.soict.se.customexception.InvalidIDException;
import hust.soict.se.scanner.CardScanner;

public class CardScannerBoundary {
    private static CardScanner cardScanner = CardScanner.getInstance();

    public static String getCode(String pseudoBarcode) throws InvalidIDException {
        String cardCode = null;
        try {
            cardCode = cardScanner.process(pseudoBarcode);
        } catch (InvalidIDException e) {
            throw e;
        }
        return cardCode;
    }

}
