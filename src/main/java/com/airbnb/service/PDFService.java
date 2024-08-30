package com.airbnb.service;

import com.airbnb.dto.BookingDto;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfWriter;
import org.springframework.stereotype.Service;

import java.io.FileOutputStream;

@Service
public class PDFService {

    private static final String PDF_DIRECTORY= "/path/to/your/pdf/directory/";

    public boolean generatePDF(String fileName , BookingDto dto){
        try {
            Document document = new Document();
            PdfWriter.getInstance(document, new FileOutputStream(fileName));

            document.open();
            Font font = FontFactory.getFont(FontFactory.COURIER, 16, BaseColor.BLACK);
            Chunk bookingConfirmation = new Chunk("Booking Confirmation: ", font);
            Chunk guestName    = new Chunk("GuestName: "+dto.getGuestName(), font);
            Chunk checkInTime  = new Chunk("CheckIntime: "+dto.getCheckInTime(), font);
            Chunk checkOutTime = new Chunk("CheckOutTime: "+dto.getCheckOutTime(), font);
            Chunk CheckinDate  = new Chunk("CheckInDate: "+dto.getCheckInDate(), font);
            Chunk checkOutDate = new Chunk("ChekcOutDate: "+dto.getCheckoutDate(), font);
            Chunk totalNights  = new Chunk("TotalNights: "+dto.getTotalNights(), font);
            Chunk totalPrice   = new Chunk("TotalPrice: "+dto.getTotalPrice(), font);
            Chunk mobile       = new Chunk("Mobile: "+dto.getMobile(), font);
            Chunk status       = new Chunk("Status: "+dto.getStatus(), font);


            document.add(bookingConfirmation);
            document.add(new Paragraph("\n"));
            document.add(guestName);
            document.add(new Paragraph("\n"));
            document.add(checkInTime);
            document.add(new Paragraph("\n"));
            document.add(checkOutTime);
            document.add(new Paragraph("\n"));
            document.add(CheckinDate);
            document.add(new Paragraph("\n"));
            document.add(checkOutDate);
            document.add(new Paragraph("\n"));
            document.add(totalNights);
            document.add(new Paragraph("\n"));
            document.add(totalPrice);
            document.add(new Paragraph("\n"));
            document.add(mobile);
            document.add(new Paragraph("\n"));
            document.add(status);

            document.close();
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

}
