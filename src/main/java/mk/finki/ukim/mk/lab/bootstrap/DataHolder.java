package mk.finki.ukim.mk.lab.bootstrap;

import jakarta.annotation.PostConstruct;
import mk.finki.ukim.mk.lab.model.Event;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DataHolder {
    public static List<Event> events = new ArrayList<>();

    @PostConstruct
    public void init() {
        events.add(new Event("Summer Festival", "An outdoor music festival featuring local and international artists.", 8.9));
        events.add(new Event("Tech Conference", "Annual conference for tech enthusiasts, covering software, AI, and hardware advancements.", 9.2));
        events.add(new Event("Food Expo", "A showcase of culinary delights from around the world.", 7.8));
        events.add(new Event("Film Screening", "A weekend screening of classic films under the stars.", 6.5));
        events.add(new Event("Art Exhibition", "An exhibition of modern art by emerging and established artists.", 7.4));
        events.add(new Event("Marathon Run", "A city-wide marathon promoting health and wellness.", 8.3));
        events.add(new Event("Book Fair", "An event featuring book signings, author talks, and a large collection of new releases.", 6.9));
        events.add(new Event("Science Workshop", "Interactive science experiments and learning sessions for kids and families.", 8.7));
        events.add(new Event("Charity Gala", "An elegant evening event aimed at raising funds for local charities.", 8.5));
        events.add(new Event("Fashion Show", "A glamorous fashion show featuring designers from around the world.", 8.1));
    }
}
