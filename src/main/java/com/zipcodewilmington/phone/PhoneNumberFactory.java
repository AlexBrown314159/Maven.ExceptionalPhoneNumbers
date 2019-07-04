package com.zipcodewilmington.phone;

import com.zipcodewilmington.exceptions.InvalidPhoneNumberFormatException;

import java.util.logging.Logger;

/**
 * Created by leon on 5/1/17.
 */
public final class PhoneNumberFactory {
    private static final Logger logger = Logger.getGlobal();

    private PhoneNumberFactory() {
        /** This constructor is private
         *  This class is uninstantiable */
    }


    /**
     * @param phoneNumberCount - number of PhoneNumber objects to instantiate
     * @return array of randomly generated PhoneNumber objects
     */ //TODO - Implement logic
    public static PhoneNumber[] createRandomPhoneNumberArray(int phoneNumberCount) {

        Integer areaCode, centralOfficeCode, phoneLineCode;


        PhoneNumber[] phoneNumbers = new PhoneNumber[phoneNumberCount];

        for (int i = 0; i < phoneNumberCount; i++) {

            phoneNumbers[i] = createRandomPhoneNumber();

        }

        return phoneNumbers;
    }


    /**
     * @return an instance of PhoneNumber with randomly generated phone number value
     */ //TODO - Implement logic
    public static PhoneNumber createRandomPhoneNumber() {

        int areaCode = 201 + (int) (798.0 * Math.random());
        int centralOfficeCode = (int) (999.0 * Math.random());
        int phoneLineCode = (int) (9999.0 * Math.random());

        return createPhoneNumberSafely(areaCode, centralOfficeCode, phoneLineCode);
    }


    /**
     * @param areaCode          - 3 digit code
     * @param centralOfficeCode - 3 digit code
     * @param phoneLineCode     - 4 digit code
     * @return a new phone number object
     */ //TODO - if input is valid, return respective PhoneNumber object, else return null
    public static PhoneNumber createPhoneNumberSafely(int areaCode, int centralOfficeCode, int phoneLineCode) {

        String phoneNumberString = "";

        if ((areaCode < 201) || (areaCode > 999)) {
            return null;
        }

        if ((centralOfficeCode < 0) || (centralOfficeCode > 999)) {
            return null;
        }

        if ((phoneLineCode < 0) || (phoneLineCode > 9999)) {
            return null;
        }

        String areaCodeString = "000" + String.valueOf(areaCode);
        areaCodeString = areaCodeString.substring(areaCodeString.length() - 3, areaCodeString.length());

        String centralOfficeCodeString = "000" + String.valueOf(centralOfficeCode);
        centralOfficeCodeString = centralOfficeCodeString.substring(centralOfficeCodeString.length() - 3, centralOfficeCodeString.length());

        String phoneLineCodeString = "0000" + String.valueOf(phoneLineCode);
        phoneLineCodeString = phoneLineCodeString.substring(phoneLineCodeString.length() - 4, phoneLineCodeString.length());

        phoneNumberString = "(" + areaCodeString + ")-" + centralOfficeCodeString + "-" + phoneLineCodeString;



        logger.info("Attempting to create a new PhoneNumber object with a value of " + phoneNumberString);
        try {
            return createPhoneNumber(phoneNumberString);
        } catch (InvalidPhoneNumberFormatException e) {
            logger.info(phoneNumberString + " is not a valid phone number");
        }
        return null;
    }


    /**
     * @param phoneNumberString - some String corresponding to a phone number whose format is `(###)-###-####`
     * @return a new phone number object
     * @throws InvalidPhoneNumberFormatException - thrown if phoneNumberString does not match acceptable format
     */ // TODO - Add throws statement to method signature
    public static PhoneNumber createPhoneNumber(String phoneNumberString) throws InvalidPhoneNumberFormatException {

        //validate phone number with format `(###)-###-####`
        if (!phoneNumberString.matches("\\(\\d{3}\\)-\\d{3}-\\d{4}")) {
            throw new InvalidPhoneNumberFormatException();
        }

        PhoneNumber phoneNumber = new PhoneNumber(phoneNumberString);

        return phoneNumber;
    }

}


