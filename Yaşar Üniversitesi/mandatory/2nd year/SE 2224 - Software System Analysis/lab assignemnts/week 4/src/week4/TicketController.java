package week4;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Scanner;

public class TicketController extends JFrame implements BaseController{
    private FlightController f_c;

    //all_tickets initially empty;
    private static ArrayList<Ticket> all_tickets;
    private Scanner input;

    TicketController(){
        all_tickets = new ArrayList<>();
        f_c = new FlightController();
    }

    @Override
    public boolean validatee(Flight flight) {
        return true;
    }

    public boolean validatee(Ticket ticket){
        /*
        validation rules for existing information;(VALIDATION method for TicketController)
* ** luggage will be defined in validate method of TicketController
* if luggage is smaller than 1 ; throw Exception with message Error : Luggage cant be smaller than 1 kg
* if flight is cancelled ; throw Exception with message Error : This flight is canceled you can not buy ticket
* if the desired seat is already bought ; throw Exception with message Error : This seat is taken please choose new seat
         */

        try{
            if(ticket.getLuggage() < 1)
                throw new Exception("Luggage cant be smaller than 1 kg");
            if(ticket.getFlight().isCanceled())
                throw new Exception("This flight is canceled you can not buy ticket");
            for (Ticket t: all_tickets) {
                if(t.getSeat_number() == ticket.getSeat_number())
                    throw new Exception("This seat is taken please choose new seat");
            }
        }
        catch (Exception e){
            e.printStackTrace();
            return false;
        }

        return true;
    }

    @Override
    public void create(Flight flight) {

    }

    public void create(Ticket ticket){
        if(validatee(ticket)){
            all_tickets.add(ticket);
        }

    }
    public ArrayList<Flight> showInput(){

        return null;
    }
    public void showData(){
        Ticket[] ticketList = new Ticket[all_tickets.size()];

        for (int i = 0; i<all_tickets.size(); i++) {
            System.out.println(i);
            ticketList[i] = all_tickets.get(i);
        }
        LIST_OF_TICKETS list = new LIST_OF_TICKETS(ticketList);

    }

    public static Ticket[] getTicketList(){
        Ticket[] list;

        if(all_tickets == null){
            list = new Ticket[0];
        }
        else
        {
            list = new Ticket[all_tickets.size()];
            for(int i = 0; i < all_tickets.size() ; i++) {
                list[i] = all_tickets.get(i);
            }
        }

        return list;

    }


}

/*
validate function will validate info based on restrictions (that will be explain in below for each class) and
throw Exception with specific message if conditions are not met

create function will create desired class instance if validation is not throw an error and
add the instance to its list

show Input prints necessary text to console to create desired instance, collects that info then
stores in ArrayList,finally returns it.

showData prints instances in the list.
 */