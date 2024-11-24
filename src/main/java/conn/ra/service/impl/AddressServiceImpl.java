package conn.ra.service.impl;

import conn.ra.model.dto.request.AddressRequest;
import conn.ra.model.entity.Address;
import conn.ra.model.entity.User;
import conn.ra.repository.AddressRepository;
import conn.ra.security.UserDetail.UserLoggedIn;
import conn.ra.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddressServiceImpl implements AddressService {
    @Autowired
    private AddressRepository addressRepository;
    @Autowired
    private UserLoggedIn userLoggedIn;

    @Override
    public void add(AddressRequest addressRequest) {
        User user = userLoggedIn.getUserLoggedIn ();
        Address address = Address.builder ()
                .users ( user )
                .address ( addressRequest.getReceiveAddress () )
                .phone ( addressRequest.getReceivePhone () )
                .receiveName ( addressRequest.getReceiveName () )
                .build ();
        addressRepository.save ( address );
    }

    @Override
    public void edit(AddressRequest addressRequest, Long id) {
        User user = userLoggedIn.getUserLoggedIn ();
        Address address = Address.builder ()
                .users ( user )
                .address ( addressRequest.getReceiveAddress () )
                .phone ( addressRequest.getReceivePhone () )
                .receiveName ( addressRequest.getReceiveName () )
                .build ();
        address.setId ( id );
        addressRepository.save ( address );
    }

    @Override
    public void delete(Long addressId, User user) {
        addressRepository.deleteByIdAndUsers ( addressId, user );
    }

    @Override
    public List<Address> getAll(Long id) {
        return addressRepository.findAllByUserId ( id );
    }

    @Override
    public Address findById(Long addressId) {
        return addressRepository.findById ( addressId ).orElse ( null );
    }

    @Override
    public List<Address> getAllByUser(User user) {
        return addressRepository.findAllByUsers ( user );
    }
}
