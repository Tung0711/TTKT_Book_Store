package conn.ra.service;

import conn.ra.model.entity.Categories;
import conn.ra.repository.CatalogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoriesServiceImpl implements CategoriesService {
    @Autowired
    private CatalogRepository catalogRepository;

    @Override
    public List<Categories> getAll() {
        return catalogRepository.findAll ();
    }

    @Override
    public Categories findById(Long id) {
        return catalogRepository.findById ( id ).orElse ( null );
    }

    @Override
    public Boolean add(Categories categories) {
        try {
            catalogRepository.save ( categories );
            return true;
        }catch (Exception e){
            e.printStackTrace ();
        }
        return false;
    }

    @Override
    public Boolean edit(Categories categories) {
        catalogRepository.save ( categories );
        return true;
    }

    @Override
    public void delete(Long id) {
        catalogRepository.deleteById ( id );
    }
}
