package com.sk.hw_1.message;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.google.common.hash.Hashing;

@RestController
public class MessagesController {
    private Map<String, Digest> messagesPosted = new HashMap<>();
    private Map<String, Message> messagesByDigest = new HashMap<>();

    @RequestMapping(path="/messages", method=RequestMethod.POST)
    public ResponseEntity<Digest> publish(@RequestBody Message msg) {
        if (messagesPosted.containsKey(msg.getMessage())) {
            return new ResponseEntity<>(
                    messagesPosted.get(msg.getMessage()), HttpStatus.OK);
        }

        String sha256hex = Hashing.sha256()
                .hashString(msg.getMessage(), StandardCharsets.UTF_8)
                .toString();
        messagesByDigest.put(sha256hex, msg);
        Digest digest = new Digest(sha256hex);
        messagesPosted.put(msg.getMessage(), digest);
        return new ResponseEntity<>(digest, HttpStatus.OK);
    }

    @RequestMapping(path="/messages/{digest}", method=RequestMethod.GET)
    public ResponseEntity<Message> retrieve(@PathVariable String digest) {
        if (!messagesByDigest.containsKey(digest)) {
            return new ResponseEntity<>(new Message("Not found"), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(messagesByDigest.get(digest), HttpStatus.OK);
    }
}
