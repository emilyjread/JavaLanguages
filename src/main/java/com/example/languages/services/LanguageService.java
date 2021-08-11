package com.example.languages.services;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;
import com.example.languages.models.Language;
import com.example.languages.repositories.LanguageRepository;

@Service
public class LanguageService {
	private final LanguageRepository languageRepository;
	
	public LanguageService(LanguageRepository languageRepository) {
		this.languageRepository= languageRepository;
	}
	
	 public List<Language> allLanguages() {
	        return languageRepository.findAll();
	    }

	    public Language createLanguage(Language l) {
	        return languageRepository.save(l);
	    }

	    public Language findLanguage(Long id) {
	        Optional<Language> optionalLanguage = languageRepository.findById(id);
	        if(optionalLanguage.isPresent()) {
	            return optionalLanguage.get();
	        } else {
	            return null;
	        }
	    }
	    
	    public Language updateLanguage(Long id, String name, String creator, String currentVersion) {
	    	Language l= findLanguage(id);
	    	l.setName(name);
	    	l.setCreator(creator);
	    	l.setCurrentVersion(currentVersion);
	    
	        return languageRepository.save(l);
	    }
	    
	    public void deleteLanguage(Long id) {
	        languageRepository.deleteById(id);
	        
	    }

}
