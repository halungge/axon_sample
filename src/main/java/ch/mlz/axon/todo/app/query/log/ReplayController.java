package ch.mlz.axon.todo.app.query.log;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;

@RestController
@RequiredArgsConstructor
@Slf4j
public class ReplayController {

    private final ResetService resetService;

    @RequestMapping(value = "/api/reset", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void replayLogHandler(@RequestParam Instant to, @RequestParam Instant from){
      log.info("replaying logHandler for period = {} to {}", from, to);
        Timeboundaries bd = new Timeboundaries(from, to);
        resetService.replay("logHandler", 0L, new TimedContext(bd));

    }


}
