package com.softuni.sportify.factory;

import com.softuni.sportify.domain.entities.Event;
import com.softuni.sportify.domain.models.service_models.EventServiceModel;

import static com.softuni.sportify.factory.SportFactory.createValidSport;
import static com.softuni.sportify.factory.SportFactory.createValidSportServiceModel;

public abstract class EventFactory {

    public static EventServiceModel createValidEventServiceModel() {

        EventServiceModel eventServiceModel = new EventServiceModel();

        eventServiceModel.setSport(createValidSportServiceModel());
        eventServiceModel.setLevel("Beginner");
        eventServiceModel.setFloor(9);
        eventServiceModel.setHall("Aula");
        eventServiceModel.setMaxCapacity(50);
        eventServiceModel.setStartTime("10:00");
        return eventServiceModel;
    }

    public static Event createValidEvent() {

        Event event = new Event();
        event.setSport(createValidSport());
        event.setLevel("Beginner");
        event.setFloor(9);
        event.setHall("Aula");
        event.setMaxCapacity(50);
        event.setStartTime("10:00");
        return event;
    }
}
