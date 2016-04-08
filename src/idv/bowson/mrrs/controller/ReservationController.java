package idv.bowson.mrrs.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ReservationController {

    @RequestMapping(value = "/Reservation", method = RequestMethod.GET)
    public ModelAndView showReservation(HttpServletRequest request) {

        return new ModelAndView("reservation");
    }
}
