package net.pokeretro.inventory.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.UUID;

@ResponseStatus(
        value = HttpStatus.NOT_FOUND,
        reason = "Not enough money !"
)
public class InventoryNotFoundException extends Exception {
}
