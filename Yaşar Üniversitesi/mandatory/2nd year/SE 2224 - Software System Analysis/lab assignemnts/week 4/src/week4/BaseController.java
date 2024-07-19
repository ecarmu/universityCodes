package week4;

import java.util.ArrayList;

public interface BaseController {
    boolean validatee(Flight flight);
    boolean validatee(Ticket ticket);
    void create(Flight flight);
    void create(Ticket ticket);
    ArrayList<Flight> showInput();
    void showData();
}
