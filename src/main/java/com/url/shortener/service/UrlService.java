package com.url.shortener.service;

import com.url.shortener.model.Url;
import com.url.shortener.repository.UrlRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UrlService {
    @Autowired
    private UrlRepository urlRepository;

    public Url shortenUrl(String originalUrl) {
        // Check if URL already exists
        Optional<Url> existingUrl = urlRepository.findByOriginalUrl(originalUrl);
        if (existingUrl.isPresent()) {
            return existingUrl.get();
        }

        // Create new URL
        Url url = new Url(originalUrl);
        return urlRepository.save(url);
    }

    public Optional<Url> getOriginalUrl(String shortUrl) {
        return urlRepository.findByShortUrl(shortUrl);
    }

    public void incrementVisitCount(String shortUrl) {
        Optional<Url> url = urlRepository.findByShortUrl(shortUrl);
        url.ifPresent(u -> {
            u.setVisitCount(u.getVisitCount() + 1);
            urlRepository.save(u);
        });
    }
}
