package net.pokeretro.incubator.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(
        value = HttpStatus.BAD_REQUEST,
        reason = "Egg must wait in the incubator"
)
public class NotReadyToHatchException extends Exception {
}
