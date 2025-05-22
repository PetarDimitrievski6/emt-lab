package mk.ukim.finki.emtlab.listeners;

import mk.ukim.finki.emtlab.events.HostChangeEvent;
import mk.ukim.finki.emtlab.service.domain.HostService;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class HostEventHandlers {
    private final HostService hostService;

    public HostEventHandlers(HostService hostService) {
        this.hostService = hostService;
    }

    @EventListener
    public void handleHostChange(HostChangeEvent event){
        this.hostService.refreshMaterializedViews();
    }
}
