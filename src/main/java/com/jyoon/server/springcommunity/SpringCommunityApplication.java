package com.jyoon.server.springcommunity;

import com.jyoon.server.springcommunity.domain.Board;
import com.jyoon.server.springcommunity.domain.User;
import com.jyoon.server.springcommunity.domain.enums.BoardType;
import com.jyoon.server.springcommunity.repository.BoardRepository;
import com.jyoon.server.springcommunity.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDateTime;
import java.util.stream.IntStream;

@SpringBootApplication
public class SpringCommunityApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringCommunityApplication.class, args);
	}

	@Bean
	public CommandLineRunner runner(UserRepository userRepository, BoardRepository boardRepository) throws Exception{
		return (args) ->{
			User user = userRepository.save(User.builder()
					.name("ydy1128")
					.password("test")
					.email("ydy1128@gmail.com")
					.createdDate(LocalDateTime.now())
					.build()
			);

			IntStream.rangeClosed(1, 200).forEach(index ->
					boardRepository.save(Board.builder()
							.title("게시글"+index)
							.subTitle(index+"번글")
							.content("콘텐츠"+index)
							.boardType(BoardType.free)
							.createdDate(LocalDateTime.now())
							.updatedDate(LocalDateTime.now())
							.build()
					)
			);
		};
	}

}
