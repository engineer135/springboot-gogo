package com.springboot.gogo.first.service.posts;

import com.springboot.gogo.first.domain.posts.Posts;
import com.springboot.gogo.first.domain.posts.PostsRepository;
import com.springboot.gogo.first.web.dto.PostsResponseDto;
import com.springboot.gogo.first.web.dto.PostsSaveRequestDto;
import com.springboot.gogo.first.web.dto.PostsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class PostsService {
    private final PostsRepository postsRepository;

    @Transactional
    public Long save(PostsSaveRequestDto requestDto){
        return postsRepository.save(requestDto.toEntity()).getId();
    }

    @Transactional
    public Long update(Long id, PostsUpdateRequestDto requestDto){
        Posts posts = postsRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id="+id));
        // 특이한 점은 데이터베이스에 쿼리 날리는 부분이 없음!
        // 트랜잭션 안에서 데이터페이스에서 데이터를 가져오면, 영속성 컨텍스트가 유지되다가 해당 데이터 값을 변경하면 트랜잭션 끝날때 해당 테이블의 변경분을 반영!
        // 이게 더티 체킹이라는 개념임
        posts.update(requestDto.getTitle(), requestDto.getContent());
        return id;
    }

    @Transactional
    public PostsResponseDto findById(Long id){
        Posts entity = postsRepository.findById(id)
                .orElseThrow(()->new IllegalArgumentException("해당 게시글이 없습니다. id="+id));
        return new PostsResponseDto(entity);
    }
}
