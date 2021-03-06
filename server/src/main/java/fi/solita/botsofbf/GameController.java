package fi.solita.botsofbf;

import fi.solita.botsofbf.dto.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.UUID;


@RestController
public class GameController {

    @Autowired
    private GameEngine gameEngine;

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public RegisterResponse register(@RequestBody Registration registration) {
        return gameEngine.registerPlayer(registration.playerName, registration.url);
    }

    @RequestMapping("/foo")
    public @ResponseBody String foo() throws IOException {
        return "bar";
    }

    @RequestMapping(value = "/{playerId}/say", method = RequestMethod.POST)
    public void say(@PathVariable String playerId, @RequestBody String message) {
        gameEngine.say(UUID.fromString(playerId), message);
    }

    @RequestMapping(value = "/restart", method = RequestMethod.POST)
    public void restart() {
        gameEngine.restart();
    }

    @RequestMapping(value = "/changemap", method = RequestMethod.POST)
    public void changeMap(@RequestBody String map) throws IOException {
        gameEngine.changeMap(map);
    }

    /*
    @RequestMapping(value = "/move", method = RequestMethod.POST)
    public @ResponseBody GameState move(@RequestBody PlayerMove move) {
        return gameEngine.movePlayer(UUID.fromString(move.id), move.move);
    }
    */

}
