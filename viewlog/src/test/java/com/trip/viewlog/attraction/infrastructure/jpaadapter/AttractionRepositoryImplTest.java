package com.trip.viewlog.attraction.infrastructure.jpaadapter;

import com.trip.viewlog.attraction.domain.Attraction;
import com.trip.viewlog.attraction.infrastructure.jpaadapter.entity.AttractionEntity;
import com.trip.viewlog.attraction.infrastructure.jpaadapter.entity.ContentTypeEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.lenient;

@ExtendWith(MockitoExtension.class)
public class AttractionRepositoryImplTest {
	@Mock
    private AttractionJpaRepository jpaRepo;

    @InjectMocks
    private AttractionRepositoryImpl repoImpl;

    @BeforeEach
    void setup() {
    	ContentTypeEntity contentTypeEntity = new ContentTypeEntity(12,"관광지");
    	
    	AttractionEntity sample1 = new AttractionEntity(1,12,"제주 해변",contentTypeEntity,1,1
    			,null,null,null,33.45,126.56,null,"제주도","서귀포시",null,null);
    	
    	AttractionEntity sample2 = new AttractionEntity(2,12,"한라산",contentTypeEntity,1,1
    			,null,null,null,33.36,126.53,null,"제주도","한라산로",null,null);
    	
    	lenient().when(jpaRepo.findByLatitudeBetweenAndLongitudeBetween(33.4, 33.5, 126.5, 126.6))
    	.thenReturn(List.of(sample1));
    	lenient().when(jpaRepo.findByLatitudeBetweenAndLongitudeBetween(33.3, 33.4, 126.5, 126.6))
    	.thenReturn(List.of(sample2));
    	
    	lenient().when(jpaRepo.findByAddr1ContainingOrAddr2Containing("제주", "서귀포"))
    	.thenReturn(List.of(sample1));
    	lenient().when(jpaRepo.findByAddr1ContainingOrAddr2Containing("제주", "한라산로"))
    	.thenReturn(List.of(sample2));
    	
    	lenient().when(jpaRepo.findByTitleContaining("한라")).thenReturn(List.of(sample2));
    	
    }
    
    @Test @DisplayName("위경도 33.4~33.5 사이 도메인 검색")
    void testFindByLatLngHighRange() {
      List<Attraction> results = repoImpl.findByLatitudeBetweenAndLongitudeBetween(33.4, 33.5, 126.5, 126.6);
      assertThat(results).isNotEmpty();
      assertThat(results.get(0)).isInstanceOf(Attraction.class);
    }
    
    @Test @DisplayName("위경도 33.3~33.4 사이 도메인 검색")
    void testFindByLatLngLowRange() {
      List<Attraction> results = repoImpl.findByLatitudeBetweenAndLongitudeBetween(33.3, 33.4, 126.5, 126.6);
      assertThat(results).isNotEmpty();
      assertThat(results.get(0)).isInstanceOf(Attraction.class);
    }
    
    @Test
    @DisplayName("주소 키워드로 검색")
    void 주소_키워드_검색() {
    	List<Attraction> results = repoImpl.findByAddr1ContainingOrAddr2Containing("제주", "서귀포");
        assertThat(results).extracting("title").contains("제주 해변");
    	
        results = repoImpl.findByAddr1ContainingOrAddr2Containing("제주", "한라산로");
        assertThat(results).extracting("title").contains("한라산");
    }
    
    @Test
    @DisplayName("타이틀 키워드로 검색")
    void 제목_키워드_검색() {
        List<Attraction> results = repoImpl.findByTitleContaining("한라");
        assertThat(results).hasSize(1);
        assertThat(results.get(0).getTitle()).contains("한라");
    }

}
