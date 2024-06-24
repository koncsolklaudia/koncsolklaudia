package com.example.urlaliasertrial.controllers;

import com.example.urlaliasertrial.dtos.AliasDTO;
import com.example.urlaliasertrial.dtos.SecretDTO;
import com.example.urlaliasertrial.models.Alias;
import com.example.urlaliasertrial.services.AliasService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class APIController {

    private final AliasService aliasService;

    //If the alias exists it should increment the hit count and redirect to the URL otherwise respond with 404 status code

    @GetMapping("/a/{alias}")
    public ResponseEntity<?> getAlias(@PathVariable String alias){
        Optional<Alias> aliasOptional = aliasService.getAlias(alias);

        if(aliasOptional.isEmpty()){
            return ResponseEntity.notFound().build();
        }

        aliasService.incrementHitCount(aliasOptional.get().getId());

        return ResponseEntity.status(HttpStatus.FOUND).header(HttpHeaders.LOCATION, aliasOptional.get().getLink().getUrl()). build();

    }

    //It should respond with the stored entries in the following JSON format
    //    "id": 1,
    //    "url": "http://reddit.com",
    //    "alias": "bye-bye-time",
    //    "hitCount": 0
    //DTO

    @GetMapping("api/aliases")
    public List<AliasDTO> getAliases(){
        return aliasService.getAll().stream()
                .map(x-> AliasDTO.builder()
                        .alias(x.getAlias())
                        .id(x.getId())
                        .hitCount(x.getHitCount())
                        .url(x.getLink().getUrl())
                        .build())
                .toList();
    }

    //the secret code should be in the request's body in JSON format
    //{
    //  "secretCode": "0483"
    //}
    //If the alias doesn't exist respond with 404 status code
    //If it exists but the provided secret code doesn't match respond with 403 status code
    //If it exists and the provided secret code matches delete the entry from the database and respond with 204 status code

    @DeleteMapping("/api/aliases/{id}")
    public ResponseEntity<?> deleteByAliasId(@PathVariable Integer id, @RequestBody SecretDTO secretCode){
    var alias = aliasService.getAlias(id);
    if (alias.isEmpty()){
        return ResponseEntity.notFound().build();
    }
    if(secretCode.getSecretCode() == null || !secretCode.getSecretCode().equals(alias.get().getSecretCode())){
        return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
    }

    aliasService.deleteAlias(alias.get().getId());
    return ResponseEntity.noContent().build();
    }
}
