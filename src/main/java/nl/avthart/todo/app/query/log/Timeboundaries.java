package nl.avthart.todo.app.query.log;

import lombok.AllArgsConstructor;
import lombok.Value;

import java.time.Instant;

@Value
@AllArgsConstructor
public class Timeboundaries {
    private Instant from;
    private Instant to;
}
