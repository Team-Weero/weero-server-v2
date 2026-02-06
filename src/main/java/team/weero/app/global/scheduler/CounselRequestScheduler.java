package team.weero.app.global.scheduler;

import java.time.LocalDateTime;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import team.weero.app.adapter.out.counsel.entity.CounselRequestJpaEntity;
import team.weero.app.adapter.out.counsel.repository.CounselRequestRepository;
import team.weero.app.domain.counsel.type.Status;

@Slf4j
@Component
@RequiredArgsConstructor
public class CounselRequestScheduler {

  private final CounselRequestRepository counselRequestRepository;

  @Scheduled(cron = "0 0 2 * * *")
  public void cleanupCompletedCounselRequests() {
    LocalDateTime cutoffDate = LocalDateTime.now().minusDays(30);

    List<CounselRequestJpaEntity> completedRequests =
        counselRequestRepository.findAllByStatusAndDeletedAtIsNullAndUpdatedAtBefore(
            Status.COMPLETED, cutoffDate);

    completedRequests.forEach(CounselRequestJpaEntity::markDeleted);

    counselRequestRepository.saveAll(completedRequests);

    log.info(
        "Cleaned up {} completed counsel requests older than 30 days", completedRequests.size());
  }
}
