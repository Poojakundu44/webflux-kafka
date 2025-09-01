package org.example.service;

import org.example.model.Event;
import org.example.repository.EventRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.publisher.Sinks;

import java.time.Instant;

@Service
public class EventService {

    private final EventRepository eventRepo;
    private final Sinks.Many<Event> sink;


    public EventService(EventRepository eventRepo, Sinks.Many<Event> sink) {
        this.eventRepo = eventRepo;
        this.sink = Sinks.many().multicast().onBackpressureBuffer();

    }

    public Mono<Event> createEvent(String message){
        Event event = new Event(message , Instant.now());
        return eventRepo.save(event).doOnNext(saved -> sink.tryEmitNext(saved));
    }

    public Flux<Event> streamEvents(){
        return sink.asFlux();
    }

    public Mono<Event> getEvent(Long id){
        return eventRepo.findById(id);
    }

}
