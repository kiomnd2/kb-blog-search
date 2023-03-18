package kb.kiomnd2.kbblogsearch.service.impl;

import kb.kiomnd2.kbblogsearch.dto.BlogSearchResultDto;
import kb.kiomnd2.kbblogsearch.dto.SearchRequestDto;
import kb.kiomnd2.kbblogsearch.dto.kakao.KakaoBlogRequestDto;
import kb.kiomnd2.kbblogsearch.dto.kakao.KakaoBlogResponseDto;
import kb.kiomnd2.kbblogsearch.jpa.entity.Search;
import kb.kiomnd2.kbblogsearch.jpa.repository.SearchRepository;
import kb.kiomnd2.kbblogsearch.mapper.kakao.KakaoRequestMapper;
import kb.kiomnd2.kbblogsearch.service.BlogApiClient;
import kb.kiomnd2.kbblogsearch.service.BlogSearchService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public class BlogSearchServiceImpl implements BlogSearchService {

    private final BlogApiClient<KakaoBlogRequestDto, KakaoBlogResponseDto> client;

    private final SearchRepository searchRepository;

    @Transactional
    @Override
    public BlogSearchResultDto search(SearchRequestDto request) {
        try {
            KakaoBlogRequestDto requestDto = KakaoRequestMapper.INSTANCE.fromRequest(request);
            KakaoBlogResponseDto kakaoBlogResponseDto = client.sendRequest(requestDto);
            this.updateSearch(request.getKeyword());
            return KakaoRequestMapper.INSTANCE.toResponse(kakaoBlogResponseDto);
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    @Transactional
    public void updateSearch(String keyword) {
        searchRepository.findByKeyword(keyword).ifPresentOrElse(Search::plusCount,
                () -> searchRepository.save(
                Search.builder()
                        .keyword(keyword)
                        .count(1)
                        .createAt(LocalDateTime.now())
                        .build()));

    }
}
