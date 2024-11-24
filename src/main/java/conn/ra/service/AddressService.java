package conn.ra.service;


import conn.ra.model.dto.request.AddressRequest;
import conn.ra.model.entity.Address;
import conn.ra.model.entity.User;

import java.util.List;

public interface AddressService {
    void add(AddressRequest addressRequest);

    void edit(AddressRequest addressRequest, Long id);

    void delete(Long addressId, User user);

    List<Address> getAll(Long id);

    Address findById(Long addressId);

    List<Address> getAllByUser(User user);

}
