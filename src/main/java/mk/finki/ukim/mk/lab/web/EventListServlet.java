package mk.finki.ukim.mk.lab.web;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mk.finki.ukim.mk.lab.service.EventService;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring6.SpringTemplateEngine;
import org.thymeleaf.web.IWebExchange;
import org.thymeleaf.web.servlet.JakartaServletWebApplication;

import java.io.IOException;

@WebServlet(name="Servlet", urlPatterns = "/")
public class EventListServlet extends HttpServlet {
    private final EventService eventService;
    private final SpringTemplateEngine springTemplateEngine;

    public EventListServlet(EventService eventService, SpringTemplateEngine springTemplateEngine) {
        this.eventService = eventService;
        this.springTemplateEngine = springTemplateEngine;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        IWebExchange webExchange = JakartaServletWebApplication.buildApplication(getServletContext()).buildExchange(req, resp);
        WebContext webContext = new WebContext(webExchange);
        webContext.setVariable("events", eventService.listAll());
        String search = (String) req.getParameter("search");
        String rating = (String) req.getParameter("rating");
        if (search!=null && search.isEmpty()) {
            search = null;
        }
        if (rating!=null && rating.isEmpty()) {
            rating = null;
        }


        if (search != null) {
            if (rating != null) {
                webContext.setVariable("events", eventService.eventSearch(search, Double.parseDouble(rating)));
            }
        } else {
            webContext.setVariable("events", eventService.listAll());
        }
        springTemplateEngine.process("listEvents.html", webContext, resp.getWriter());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String eventName = req.getParameter("selectedEvent");
        String numTickets = req.getParameter("numTickets");
        req.getSession().setAttribute("selectedEvent", eventName);
        req.getSession().setAttribute("numTickets", numTickets);
        resp.sendRedirect("/eventBooking");
    }
}
