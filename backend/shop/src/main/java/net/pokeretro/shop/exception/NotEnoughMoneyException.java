package net.pokeretro.shop.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(
        value = HttpStatus.NOT_ACCEPTABLE,
        reason = "Not enough money !"
)
public class NotEnoughMoneyException extends Exception {
}
