package week4;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

public class FlightController extends JFrame implements BaseController{

    /*
    all_flights initally empty

flight code, departure city, destination city will be gathered from CREATE FLIGHT Frame.!!!
flight number, seat number, and luggage will be gathered from Buy TICKET Frame.!!!
     */

    private Scanner input;
    private static ArrayList<Flight> all_flights;
    private ArrayList<String> departure_cities;
    private ArrayList<String> destination_cities;

    public FlightController() {
        all_flights = new ArrayList<Flight>();
        departure_cities = new ArrayList<>();
        destination_cities = new ArrayList<>();
        FileOperation.readCities("C:\\Users\\ardah\\IdeaProjects\\SE-2224_week 3\\src\\week4\\cities.txt", departure_cities, destination_cities);
    }

    public boolean validatee(Flight flight){
        /*
        validation rules for existing information;(VALIDATION method for FlightController)

* if flight code is already exist in flights ; throw Exception with message Error : Flight code is exist
* if departure is not in the departure_cities ;  throw Exception with message Error : " + ..the city name.. + " is not a valid departure city
* if destination is not in the destination_cities ;  throw Exception with message Error : " + ..the city name.. + " is not a valid destination city
         */




        try{
            boolean found = false;
            for (Flight f : all_flights) {
                if (f.getFlight_code().equals(flight.getFlight_code())) {
                    throw new Exception("Flight code is exist");
                }
            }


            for(String st: departure_cities){
                if(Objects.equals(flight.getDeparture(), st)){
                    found = true;
                    break;
                }

            }
            if(!found)
                throw new Exception(flight.getDeparture() + " is not a valid departure city");
            found = false;


            for(String st: destination_cities){
                if(Objects.equals(st, flight.getDestination())){
                    found = true;
                    break;
                }

            }
            if(!found)
                throw new Exception(flight.getDestination() + " is not a valid destination city");
            found = false;



        }
        catch (Exception e){
            e.printStackTrace();
            return false;
        }

        return true;
    }

    @Override
    public boolean validatee(Ticket ticket) {
        return false;
    }

    public void create(Flight flight){

        if(validatee(flight)){
            System.out.println("Current added flight: " + flight.toString());
            all_flights.add(flight);
        }


    }

    @Override
    public void create(Ticket ticket) {

    }

    public ArrayList<Flight> showInput(){

        return null;
    }
    public void showData(){

        Flight[] flightsList = new Flight[all_flights.size()];

        for (int i = 0; i<all_flights.size(); i++) {
            flightsList[i] = all_flights.get(i);
        }
        LIST_OF_FLIGHTS list = new LIST_OF_FLIGHTS(flightsList);
        /*
        !!the showData prints,flights with their corresponding index values !!! As an example;
if the first flight in all_flights it ; TRY101,Adana,Paris it will print

TRY101 Adana Paris
...
         */

    }


    public static Flight[] getFlightList(){
        Flight[] list;

        if(all_flights == null){
            list = new Flight[0];
        }
        else{
            list = new Flight[all_flights.size()];
            for(int i = 0; i < all_flights.size() ; i++){
                list[i] = all_flights.get(i);
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


