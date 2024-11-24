package conn.ra.service;

import conn.ra.model.entity.Categories;

import java.util.List;

public interface CategoriesService {
    List<Categories> getAll();

    Categories findById(Long id);

    Boolean add(Categories categories);

    Boolean edit(Categories categories);

    void delete(Long id);

    List<Categories> getByStatus();
}
