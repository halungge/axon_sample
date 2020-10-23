package ch.mlz.axon.todo.app.query.log;

import org.axonframework.eventhandling.replay.GenericResetContext;

public class TimedContext extends GenericResetContext<Timeboundaries> {

    public TimedContext(Timeboundaries payload) {
        super(payload);
    }
}
