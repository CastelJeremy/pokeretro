package net.pokeretro.incubator.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(
        value = HttpStatus.BAD_REQUEST,
        reason = "Failed to remove egg from trainer inventory"
)
public class InventoryRemovalException extends Exception {
    
}
