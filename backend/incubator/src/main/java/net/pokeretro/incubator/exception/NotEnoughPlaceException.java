package net.pokeretro.incubator.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(
        value = HttpStatus.NOT_ACCEPTABLE,
        reason = "Not enough place (Weight > 3000)"
)
public class NotEnoughPlaceException extends Exception {
}
