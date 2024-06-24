package com.example.urlaliasertrial.services;

import com.example.urlaliasertrial.models.Alias;
import com.example.urlaliasertrial.models.Link;
import com.example.urlaliasertrial.repositories.AliasRepository;
import com.example.urlaliasertrial.repositories.LinkRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AliasServiceImpl implements AliasService{

    private final AliasRepository aliasRepository;
    private final LinkRepository linkRepository;
    @Override
    public List<Alias> getAll() {
        return aliasRepository.findAll();
    }

    public Optional<Alias> getAlias(String alias){
        return aliasRepository.findAliasByAlias(alias);
    }
    @Override
    public Optional<Alias> getAlias(Integer id) {
        return aliasRepository.findById(id);
    }

    @Override
    public void incrementHitCount(Integer id) {
        aliasRepository.incrementHitCount(id);
    }

    @Override
    public void deleteAlias(Integer id) {
        aliasRepository.deleteById(id);
    }

    private String generateSecretCode() {
        int code = (int) (Math.random() * 10000);
        return String.format("%04d", code);
    }

    @Override
    public Optional<Alias> createAlias(String url, String alias) {
        if(aliasRepository.existAliasByAlias(alias)){
            return Optional.empty();
        }

        Alias aliasEntity = new Alias();
        aliasEntity.setAlias(alias);
        aliasEntity.setSecretCode(generateSecretCode());

        Optional<Link> link = linkRepository.findLinkByUrl(url);

        if(link.isPresent()){
            aliasEntity.setLink(link.get());
        }else{
            Link linkEntity = new Link();
            linkEntity.setUrl(url);
            linkRepository.save(linkEntity);
            aliasEntity.setLink(linkEntity);
        }
        aliasRepository.save(aliasEntity);
        return Optional.of(aliasEntity);
    }


}
