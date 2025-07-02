package com.url.shortener.controller;

import com.url.shortener.model.Url;
import com.url.shortener.service.UrlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Optional;

@RestController
@RequestMapping("/api/urls")
public class UrlController {
    @Autowired
    private UrlService urlService;

    @PostMapping
    public ResponseEntity<Url> shortenUrl(@RequestBody String originalUrl) {
        Url url = urlService.shortenUrl(originalUrl);
        return new ResponseEntity<>(url, HttpStatus.CREATED);
    }

    @GetMapping("/{shortUrl}")
    public ResponseEntity<String> getOriginalUrl(@PathVariable String shortUrl) {
        Optional<Url> url = urlService.getOriginalUrl(shortUrl);
        if (url.isPresent()) {
            urlService.incrementVisitCount(shortUrl);
            return ResponseEntity.ok(url.get().getOriginalUrl());
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/stats/{shortUrl}")
    public ResponseEntity<Url> getUrlStats(@PathVariable String shortUrl) {
        Optional<Url> url = urlService.getOriginalUrl(shortUrl);
        if (url.isPresent()) {
            return ResponseEntity.ok(url.get());
        }
        return ResponseEntity.notFound().build();
    }
}
