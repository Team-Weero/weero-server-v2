package team.weero.app.adapter.in.web.notice;

import jakarta.validation.Valid;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import team.weero.app.application.port.in.notice.*;
import team.weero.app.application.service.notice.dto.request.CreateNoticeRequest;
import team.weero.app.application.service.notice.dto.request.UpdateNoticeRequest;
import team.weero.app.application.service.notice.dto.response.NoticeResponse;

@RestController
@RequestMapping("/notices")
@RequiredArgsConstructor
public class NoticeController {

  private final CreateNoticeUseCase createNoticeUseCase;
  private final GetAllNoticesUseCase getAllNoticesUseCase;
  private final GetNoticeByIdUseCase getNoticeByIdUseCase;
  private final GetMyNoticesUseCase getMyNoticesUseCase;
  private final UpdateNoticeUseCase updateNoticeUseCase;
  private final DeleteNoticeUseCase deleteNoticeUseCase;

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public void createNotice(
      @RequestBody @Valid CreateNoticeRequest request, Authentication authentication) {
    createNoticeUseCase.execute(request, authentication.getName());
  }

  @GetMapping
  public Page<NoticeResponse> getAllNotices(@PageableDefault(size = 20) Pageable pageable) {
    return getAllNoticesUseCase.execute(pageable);
  }

  @GetMapping("/{id}")
  public NoticeResponse getNoticeById(@PathVariable UUID id) {
    return getNoticeByIdUseCase.execute(id);
  }

  @GetMapping("/my")
  public List<NoticeResponse> getMyNotices(Authentication authentication) {
    return getMyNoticesUseCase.execute(authentication.getName());
  }

  @PutMapping("/{id}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void updateNotice(
      @PathVariable UUID id,
      @RequestBody @Valid UpdateNoticeRequest request,
      Authentication authentication) {
    updateNoticeUseCase.execute(id, request, authentication.getName());
  }

  @DeleteMapping("/{id}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void deleteNotice(@PathVariable UUID id, Authentication authentication) {
    deleteNoticeUseCase.execute(id, authentication.getName());
  }
}
