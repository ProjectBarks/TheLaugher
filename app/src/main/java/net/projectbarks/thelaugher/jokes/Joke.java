package net.projectbarks.thelaugher.jokes;

import net.projectbarks.thelaugher.joketype.JokeType;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Created by brandon on 10/19/14.
 */
public class Joke {
    @Getter
    private String UUID;
    @Getter
    private String joke;
    @Getter
    private JokeType type;

    public Joke(String joke, String uuid, JokeType type) {
        this.joke = joke;
        this.UUID = uuid;
        this.type = type;
    }
}
