package com.example.urlaliasertrial.services;


import com.example.urlaliasertrial.models.Alias;

import java.util.List;
import java.util.Optional;

public interface AliasService {
    List<Alias> getAll();
    Optional<Alias> createAlias(String url, String alias);
    Optional<Alias> getAlias(Integer id);
    Optional<Alias> getAlias(String alias);
    void incrementHitCount(Integer id);
    void deleteAlias(Integer id);
}
