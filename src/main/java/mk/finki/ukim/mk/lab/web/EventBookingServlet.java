package mk.finki.ukim.mk.lab.web;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mk.finki.ukim.mk.lab.service.EventBookingService;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring6.SpringTemplateEngine;
import org.thymeleaf.web.IWebExchange;
import org.thymeleaf.web.servlet.JakartaServletWebApplication;

import java.io.IOException;

@WebServlet(name="EBServlet", urlPatterns = "/eventBooking")
public class EventBookingServlet extends HttpServlet {
    private final EventBookingService bookingService;
    private final SpringTemplateEngine springTemplateEngine;

    public EventBookingServlet(EventBookingService bookingService, SpringTemplateEngine springTemplateEngine) {
        this.bookingService = bookingService;
        this.springTemplateEngine = springTemplateEngine;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        IWebExchange webExchange = JakartaServletWebApplication.buildApplication(getServletContext()).buildExchange(req, resp);
        WebContext webContext = new WebContext(webExchange);
        String eventName = (String) req.getSession().getAttribute("selectedEvent");
        String numTickets = (String) req.getSession().getAttribute("numTickets");
        String attendeeName = "Bozidar Bozinovski";
        String attendeeAddress = req.getRemoteAddr();
        webContext.setVariable("booking", bookingService.placeBooking(eventName, attendeeName, attendeeAddress, Integer.parseInt(numTickets)));
        webContext.setVariable("address", attendeeAddress);
        springTemplateEngine.process("bookingConfirmation.html", webContext, resp.getWriter());
    }
}
