package com.jyoon.server.springcommunity;

import com.jyoon.server.springcommunity.domain.Board;
import com.jyoon.server.springcommunity.domain.User;
import com.jyoon.server.springcommunity.domain.enums.BoardType;
import com.jyoon.server.springcommunity.repository.BoardRepository;
import com.jyoon.server.springcommunity.repository.UserRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

@RunWith(SpringRunner.class)
@DataJpaTest
public class JpaMappingTest {
    private final String boardTestTitle = "테스트";
    private final String email = "test@gmail.com";

    @Autowired
    UserRepository userRepository;

    @Autowired
    BoardRepository boardRepository;

    @Before
    public void init(){
        User user = userRepository.save(User.builder()
                .name("havi")
                .password("test")
                .email(email)
                .createdDate(LocalDateTime.now())
                .build()
        );

        boardRepository.save(Board.builder()
                .title(boardTestTitle)
                .subTitle("subtitle")
                .content("content")
                .boardType(BoardType.free)
                .createdDate(LocalDateTime.now())
                .updatedDate(LocalDateTime.now())
                .user(user).build()
        );
    }

    @Test
    public void 제대로_생성_테스트(){
        User user = userRepository.findByEmail(email);
        assertThat(user.getName(), is("havi"));
        assertThat(user.getPassword(), is("test"));
        assertThat(user.getEmail(), is(email));

        Board board = boardRepository.findByUser(user);
        assertThat(board.getTitle(), is(boardTestTitle));
        assertThat(board.getSubTitle(), is("subtitle"));
        assertThat(board.getContent(), is("content"));
        assertThat(board.getBoardType(), is(BoardType.free));
    }
}
