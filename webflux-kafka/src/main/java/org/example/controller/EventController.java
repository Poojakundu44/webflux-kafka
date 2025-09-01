package org.example.controller;

import org.example.model.Event;
import org.example.service.EventService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/events")
public class EventController {

    private final EventService eventService;

    public EventController(EventService eventService) {
        this.eventService = eventService;
    }


    //create event
    @PostMapping
    public Mono<Event> createEvent(@RequestBody String event){
        return eventService.createEvent(event);
    }

    @GetMapping("/{id}")
    public Mono<Event> getEvent(@PathVariable Long id){
        return eventService.getEvent(id);
    }

    @GetMapping(value = "/stream" ,produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<Event> streamEvents(){
        return eventService.streamEvents();
    }
}
