package team.weero.app.adapter.in.web;

import jakarta.validation.Valid;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import team.weero.app.adapter.in.web.dto.CreateNoticeRequest;
import team.weero.app.adapter.in.web.dto.UpdateNoticeRequest;
import team.weero.app.application.port.in.CreateNoticeCommand;
import team.weero.app.application.port.in.CreateNoticeUseCase;
import team.weero.app.application.port.in.DeleteNoticeUseCase;
import team.weero.app.application.port.in.GetNoticeListUseCase;
import team.weero.app.application.port.in.GetNoticeUseCase;
import team.weero.app.application.port.in.NoticeListResponse;
import team.weero.app.application.port.in.NoticeResponse;
import team.weero.app.application.port.in.UpdateNoticeCommand;
import team.weero.app.application.port.in.UpdateNoticeUseCase;

@RestController
@RequestMapping("/api/notices")
@RequiredArgsConstructor
public class NoticeController {

  private final CreateNoticeUseCase createNoticeUseCase;
  private final UpdateNoticeUseCase updateNoticeUseCase;
  private final DeleteNoticeUseCase deleteNoticeUseCase;
  private final GetNoticeUseCase getNoticeUseCase;
  private final GetNoticeListUseCase getNoticeListUseCase;

  @PostMapping
  public ResponseEntity<NoticeResponse> createNotice(
      @RequestBody @Valid CreateNoticeRequest request) {
    CreateNoticeCommand command = new CreateNoticeCommand(request.title(), request.content());
    NoticeResponse response = createNoticeUseCase.create(command);
    return ResponseEntity.status(HttpStatus.CREATED).body(response);
  }

  @PutMapping("/{id}")
  public ResponseEntity<NoticeResponse> updateNotice(
      @PathVariable UUID id, @RequestBody @Valid UpdateNoticeRequest request) {
    UpdateNoticeCommand command = new UpdateNoticeCommand(id, request.title(), request.content());
    NoticeResponse response = updateNoticeUseCase.update(command);
    return ResponseEntity.ok(response);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteNotice(@PathVariable UUID id) {
    deleteNoticeUseCase.delete(id);
    return ResponseEntity.noContent().build();
  }

  @GetMapping("/{id}")
  public ResponseEntity<NoticeResponse> getNotice(@PathVariable UUID id) {
    NoticeResponse response = getNoticeUseCase.getById(id);
    return ResponseEntity.ok(response);
  }

  @GetMapping
  public ResponseEntity<NoticeListResponse> getNoticeList(
      @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
    NoticeListResponse response = getNoticeListUseCase.getList(page, size);
    return ResponseEntity.ok(response);
  }
}
