package conn.ra.service.impl;

import conn.ra.model.dto.request.WishlistRequest;
import conn.ra.model.entity.Book;
import conn.ra.model.entity.User;
import conn.ra.model.entity.WishList;
import conn.ra.repository.WishlistRepository;
import conn.ra.service.BookService;
import conn.ra.service.UserService;
import conn.ra.service.WishlistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WishlistServiceImpl implements WishlistService {
    @Autowired
    private WishlistRepository wishListRepository;
    @Autowired
    private UserService userService;
    @Autowired
    private BookService bookService;

    @Override
    public WishList add(User user, WishlistRequest wishlistRequest) {

        Book books = bookService.findById ( wishlistRequest.getBookId () );

        if (books == null) {
            throw new RuntimeException ( "không tồn tại sách" );
        }

        WishList wishList = WishList.builder ()
                .users ( user )
                .books ( books )
                .build ();
        return wishListRepository.save ( wishList );
    }

    @Override
    public List<WishList> getAll(User user) {
        return wishListRepository.getAllByUsers ( user );
    }

    @Override
    public void delete(Long wishlistId, User user) {
        wishListRepository.deleteByIdAndUsers ( wishlistId, user );
    }

    @Override
    public WishList findByUserAndBook(User user, Book books) {
        return wishListRepository.findByUsersAndBooks ( user, books );
    }
}
