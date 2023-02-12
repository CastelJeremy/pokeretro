package net.pokeretro.shop.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(
        value = HttpStatus.NOT_FOUND,
        reason = "Category not found : try 'base' or 'community'"
)
public class CategoryNotFoundException extends Exception{
}
